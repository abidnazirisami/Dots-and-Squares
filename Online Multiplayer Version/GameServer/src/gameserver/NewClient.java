package gameserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class NewClient implements Runnable{
    
    private DataInputStream in1,in2;
    private DataOutputStream out1, out2;
    boolean turn = false;
    String name1,name2;
    NewClient(DataInputStream c1,DataOutputStream c2, DataInputStream c3, DataOutputStream c4)
    {
        in1 = c1;
        out1 = c2;
        in2 = c3;
        out2 = c4;
                
        
    }
    @Override
    public void run()
    {
        System.out.println("New Game Begins");
        TakeInput i1 = new TakeInput(in1,out1,out2,turn,"Player 1");   
        TakeInput i2 = new TakeInput(in2,out1,out2,turn,"Player 2"); 
        i2.start();
        i1.start();
        
    }
}
