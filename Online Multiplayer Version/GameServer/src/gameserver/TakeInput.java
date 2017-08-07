package gameserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TakeInput extends Thread{
    //Socket socket1,socket2;
    DataInputStream in;
    DataOutputStream out1,out2;
    boolean turn;
    String name;
    public TakeInput(DataInputStream one,DataOutputStream two,DataOutputStream three,boolean t,String n)
    {
        in = one;
        out1 =two;
        out2 = three;
        turn = t;
        name = n;
    }
    synchronized public void inputS() throws IOException
    {
        String s = in.readUTF();
        
        if(!"p1".equals(s)) {
        out1.writeUTF(s);
        }
        if(!"p2".equals(s)) {
        out2.writeUTF(s);
        }
        if("p1".equals(s)){
            out1.writeUTF("bla");
        }
        if("p2".equals(s)) {
            out2.writeUTF("bla");
        }
    }
    public void run()
    {
        while(true)
        {
            try {
                 inputS();
            } catch (IOException ex) {
                 Logger.getLogger(TakeInput.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
    }
}
