/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectrs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kakas
 */
public class Input extends Thread {

    private DataInputStream in;
    private GpanelM gp;

    public void run() {
        while (true) {
            try {
                takeinput();
            } catch (IOException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Input(DataInputStream a, GpanelM gp) {
        in = a;
        this.gp = gp;
        
    }

    synchronized public void takeinput() throws IOException, ClassNotFoundException {
        
        List newList = Converter.receiveList(in);
        String score1, score2, userTurn;
        score1 = in.readUTF();
        
        score2 = in.readUTF();
        userTurn = in.readUTF();
        gp.updateGPanel(newList, score1, score2, userTurn);
        
    }
}
