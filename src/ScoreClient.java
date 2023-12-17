import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ScoreClient extends JFrame {
    private static final int PORT = 1234;
    private static final String HOST = "localhost";

    private Socket sock;
    private BufferedReader in; //
    private PrintWriter out;

    private JButton jbGetScores;
    private JTextField jtfName;
    private JTextField jtfScore;
    private JTextArea jtaMesgs;

    public ScoreClient( )
    { super( "High Score Client" );
        initializeGUI( );
        makeContact( );
        addWindowListener( new WindowAdapter( ) {
            public void windowClosing(WindowEvent e)
            { closeLink( ); }
        });
        setSize(300,450);
        setVisible(true);
    }

    private void initializeGUI( ) {

    }


    private void makeContact( ) {
        try {
            sock = new Socket(HOST, PORT);
            in = new BufferedReader( new InputStreamReader( sock.getInputStream()));
            out = new PrintWriter( sock.getOutputStream( ), true );
        }
        catch(Exception e)
        { System.out.println(e); }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbGetScores)
            sendGet();
        else if (e.getSource( ) == jtfScore){
            sendScore();
        }
    }

    private void sendGet( )
    {
        try {
            out.println("get");
            String line = in.readLine( );
            System.out.println(line);
            if ((line.length( ) >= 7) && (line.substring(0,6).equals("HIGH$$")))
                showHigh(line.substring(6).trim());
            else {
                jtaMesgs.append( line + "\n");
            }
        }
        catch(Exception ex) {
            jtaMesgs.append("Problem obtaining high scores\n");
            System.out.println(ex);
        }
    }



    private void closeLink( )
    { try {
        out.println("bye");
        sock.close( );
    }
    catch(Exception e)
    { System.out.println( e ); }
        System.exit( 0 );
    }
}
