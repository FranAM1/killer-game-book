import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ScoreServer {
    HighScores hs;
    static final int PORT = 1234;

    public ScoreServer() {
        hs = new HighScores();
        try {
            ServerSocket serverSock = new ServerSocket(PORT);
            Socket clientSock;
            BufferedReader in;
            PrintWriter out;

            while (true) {
                System.out.println("Waiting for a client...");
                clientSock = serverSock.accept();
                System.out.println("Client connection from " + clientSock.getInetAddress().getHostAddress());
                in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                out = new PrintWriter(clientSock.getOutputStream(), true);
                processClient(in, out); // interact with a client
                clientSock.close();
                System.out.println("Client connection closed\n");
                hs.saveScores();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void processClient(BufferedReader in, PrintWriter out) {
        String line;
        boolean done = false;
        try {
            while (!done) {
                if ((line = in.readLine()) == null)
                    done = true;
                else {
                    System.out.println("Client msg: " + line);
                    if (line.trim().equals("bye"))
                        done = true;
                    else
                        doRequest(line, out);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void doRequest(String line, PrintWriter out)
    {
        if (line.trim().toLowerCase().equals("get")) {
            System.out.println("Processing 'get'");
            out.println( hs.toString( ) );
        }
        else if ((line.length( ) >= 6) && (line.substring(0,5).toLowerCase( ).equals("score"))) {
            System.out.println("Processing 'score'");
            hs.addScore( line.substring(5) );
        }
        else {
            System.out.println("Ignoring input line");
        }
    }
}
