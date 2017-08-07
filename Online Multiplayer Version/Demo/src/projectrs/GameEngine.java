/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectrs;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kakas
 */
public class GameEngine {
    //GameOver go;
    GameEngine() {
        
        Player player1,player2;
        JFrame board;
        player1 = new Player();
        player2 = new Player();
        List l = new LinkedList();
        player1.name = JOptionPane.showInputDialog(null, "Enter the name of Player 1" , "Player 1");
        player2.name = JOptionPane.showInputDialog(null, "Enter the name of Player 2" , "Player 2");
        if(player1.name == null)
        {
            player1.name = "Player 1";
        }
        if(player2.name == null)
        {
            player2.name = "Player 2";
        }
        //go = new GameOver();
        board = new Draw(player1, player2,l);
        
        board.setVisible(true);
        
    }
}
