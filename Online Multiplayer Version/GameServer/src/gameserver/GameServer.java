package gameserver;

import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {

    public static void main(String[] args) throws IOException {
        ServerSocket myServer= new ServerSocket(8080);
        while(true){
            Socket player1= myServer.accept();
            DataInputStream in1 = new DataInputStream(player1.getInputStream());
            DataOutputStream out1 = new DataOutputStream(player1.getOutputStream());
             
            out1.writeUTF("1");
            String name1 = in1.readUTF();
            Socket player2= myServer.accept(); 
            DataInputStream in2 = new DataInputStream(player2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(player2.getOutputStream());
            out2.writeUTF("2");
            String name2 = in2.readUTF();
             
            out1.writeUTF(name1);
            out1.writeUTF(name2);
            
            out2.writeUTF(name1);
            out2.writeUTF(name2);
            
            NewClient c = new NewClient(in1,out1,in2,out2);
            Thread t = new Thread(c);
            t.start();
        }
    }
    
}
