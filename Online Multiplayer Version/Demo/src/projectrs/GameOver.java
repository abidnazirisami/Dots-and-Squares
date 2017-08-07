
package projectrs;

import javax.swing.JFrame;


public class GameOver {
    Draw a;
    GameOver(JFrame b){
        a = (Draw) b;
    }
    
    void execute(){
        a.close();
    }
    
}
