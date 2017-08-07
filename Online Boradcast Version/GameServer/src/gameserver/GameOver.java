/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import javax.swing.JFrame;

/**
 *
 * @author kakas
 */
public class GameOver {
    Draw a;
    GameOver(JFrame b){
        a = (Draw) b;
    }
    
    void execute(){
        a.close();
    }
    
}
