
package projectrs;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Multiplayer {
    //GameOver go;
    Multiplayer(String s,Socket socket) throws IOException {
        
        Player player1,player2;
        JFrame board;
        player1 = new Player();
        player2 = new Player();
        List l = new LinkedList();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        if("1".equals(s))
        {
            player1.name = JOptionPane.showInputDialog(null, "Enter the name of Player 1" , "Player 1");   
            out.writeUTF(player1.name);
            player1.current=true;
            player1.turn=true;
            player2.turn = false;
        }
        if("2".equals(s))
        {
            player2.name = JOptionPane.showInputDialog(null, "Enter the name of Player 2" , "Player 2");
            out.writeUTF(player2.name);
            player2.current=true;
            player2.turn=false;
            player1.turn=true;
        }
        if(player1.name == null)
        {
            player1.name = "Player 1";
            
        }
        if(player2.name == null)
        {
            player2.name = "Player 2";
        }
        //go = new GameOver();
        player1.name = in.readUTF();
        player2.name = in.readUTF();
        System.out.println(player1.name+" "+player2.name);
        board = new Draw(player1, player2,l,in,out);
        
        board.setVisible(true);
        
    }
}
