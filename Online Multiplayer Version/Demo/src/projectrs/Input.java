
package projectrs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Input extends Thread {
    private DataInputStream in;
    
    @Override
    public void run(){
        try {
            takeinput();
        } catch (IOException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setInput(DataInputStream a){
        in = a;
    }
    
    String takeinput() throws IOException{
        return in.readUTF();
    }
}
