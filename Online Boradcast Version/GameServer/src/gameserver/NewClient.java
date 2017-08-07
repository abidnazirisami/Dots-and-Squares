package gameserver;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class NewClient implements Runnable{
    //private Socket client1,client2;
    private DataInputStream in1,in2;
    private DataOutputStream out1, out2;
    boolean turn = false;
    String name1,name2;
    List l;
    Player player1,player2;
    NewClient(DataInputStream c1,DataOutputStream c2, DataInputStream c3, DataOutputStream c4,String name11,String name22)
    {
        in1 = c1;
        out1 = c2;
        in2 = c3;
        out2 = c4;
        name1 = name11;
        name2 = name22;
        
    }
    @Override
    
    public void run()
    {
       
        
        player1 = new Player(name1);
        player2 = new Player(name2);
        List l = new LinkedList();
      
        Draw board = new Draw(player1,player2,l,  out1,  out2);
       // Draw board = new Draw(player1, player2,l);
        board.setVisible(true);

    }
}
