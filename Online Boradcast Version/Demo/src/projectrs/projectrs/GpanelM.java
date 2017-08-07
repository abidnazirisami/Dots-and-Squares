/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectrs;

/**
 *
 * @author kakas
 */
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GpanelM extends JPanel {

    List l1;
    GameOver go;
    int init = 0;
    boolean[][] completedSqua = new boolean[7][7];
    JLabel score3 = new JLabel();
    JLabel score4 = new JLabel();
    JLabel turn = new JLabel();

    Player Player1, Player2;
    int prevValid = 0;
    int prevCnt = 0;
    int Cnt = 0;
    int cntMoves = 0;
    int ok = 0;
    DataInputStream in;
    DataOutputStream out;
    myCollections mc;
    String det = null;
    int ccnt;
    int score;

    public void updateGPanel(List l, String score1, String score2, String userTurn) {
        l1 = l;
        score3.setText(score1);
        System.out.println(score1);
        score4.setText(score2);
        System.out.println(score2);
        turn.setText(userTurn);
        System.out.println(userTurn);
        repaint();
        revalidate();
    }

    public GpanelM(Player p1, Player p2, List l, GameOver t/*, DataInputStream inn, DataOutputStream outt, myCollections mC*/) {
        Player1 = p1;
        Player2 = p2;
        go = t;
        /*in = inn;
        out = outt;
         mc = mC;*/
        ccnt = 0;
        score = 0;
        setLayout(null);
        setPreferredSize(new Dimension(800, 400));
        l1 = l;
        score3.setText(Player1.name + "'s Score : " + Player1.score);
        score4.setText(Player2.name + "'s Score : " + Player2.score);
        turn.setText("Player 1's turn");
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 20; i <= 379; i += 60) {
            for (int j = 20; j <= 379; j += 60) {
                g.drawOval(i, j, 15, 15);
                g.setColor(Color.BLACK);
                g.fillOval(i, j, 15, 15);
            }
        }
        
        ccnt = 0;
        ListIterator<String> ll = null;
        ll = l1.listIterator();
        
        turn.setFont(new Font("Serif", Font.PLAIN, 20));
        turn.setBounds(new Rectangle(new Point(500, 50), score3.getPreferredSize()));
        add(turn);
        turn.setVisible(true);

        score3.setFont(new Font("Serif", Font.PLAIN, 20));
        score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
        add(score3);
        score3.setVisible(true);

        score4.setFont(new Font("Serif", Font.PLAIN, 20));
        score4.setBounds(new Rectangle(new Point(500, 150), score4.getPreferredSize()));
        add(score4);
        score4.setVisible(true);
        repaint();

        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        int Cnt = 0;
        int valid = 0;
        ok = init;
        boolean squarehoise = false;
        //System.out.println("Alist Size= " + l1.size());
        while (ll.hasNext()) {
            //System.out.println("Blist Size= " + l1.size());
            ccnt++;
            String Current = ll.next();
            String now = "";
            int flag = 0;
            ok++;
            for (int i = 0; i < Current.length(); i++) {
                char u = Current.charAt(i);
                if (u == ' ') {
                    flag++;
                    if (flag == 1) {
                        x1 = Integer.parseInt(now);
                    }
                    if (flag == 2) {
                        y1 = Integer.parseInt(now);
                    }
                    if (flag == 3) {
                        x2 = Integer.parseInt(now);
                    }

                    now = "";
                } else {
                    now = now + u;
                }
            }
            boolean one = false;

            y2 = Integer.parseInt(now);
            g.drawLine(x1, y1, x2, y2);
            
        }

        
        if (score != 0) {
            score3.setVisible(false);
            score4.setVisible(false);

            if (Player1.score + Player2.score == 25) {
                String Winner;
                go.execute();
                if (Player1.score < Player2.score) {
                    Player2.isWinner = true;
                    Winner = Player2.name;
                } else {
                    Player1.isWinner = true;
                    Winner = Player1.name;
                }
                String[] buttons = {"Return to main menu"};
                int gameover = JOptionPane.showOptionDialog(null, Winner + " Wins!", "Game Over", JOptionPane.PLAIN_MESSAGE, 1, null, buttons, buttons[0]);
                Menu menu = new Menu();
            } else {
                //score3.setText(Player1.name + "'s Score : " + Player1.score);
                score3.setFont(new Font("Serif", Font.PLAIN, 20));
                score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
                add(score3);
                score3.setVisible(true);

                //score4.setText(Player2.name + "'s Score : " + Player2.score);
                score4.setFont(new Font("Serif", Font.PLAIN, 20));
                score4.setBounds(new Rectangle(new Point(500, 150), score4.getPreferredSize()));
                add(score4);
                score4.setVisible(true);
                JButton b = new JButton("Test");
                b.setVisible(true);
                add(b);
                repaint();
            }
        }
    }
}
