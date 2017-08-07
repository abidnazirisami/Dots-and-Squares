
package projectrs;


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
    int init=0;
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

    public GpanelM(Player p1, Player p2, List l, GameOver t, DataInputStream inn, DataOutputStream outt, myCollections mC) {
        Player1 = p1;
        Player2 = p2;
        go = t;
        in = inn;
        out = outt;
        mc = mC;
        ccnt = 0;
        setLayout(null);
        setPreferredSize(new Dimension(800, 400));
        l1 = l;

        
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
        //repaint();
        if ((Player1.current == true && Player1.turn == false) || (Player2.current == true && Player2.turn == false)) {

            
            String s = null;
            try {
                s = in.readUTF();
                
            } catch (IOException ex) {
                Logger.getLogger(GpanelM.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if ("p1".equals(s)) {
                Player1.turn = true;
                Player2.turn = false;

            } else if ("p2".equals(s)) {
                Player2.turn = true;
                Player1.turn = false;

            } else if("bla".equals(s)) {
                
            } 
            else {
                
                l1.add(s);
                
                if (Player1.turn) {
                    det = "1";
                    Player2.turn = true;
                    Player1.turn = false;
                    } else if (Player2.turn) {
                    det = "2";
                    Player1.turn = true;
                    Player2.turn = false;
                    }
            }
        }
        ccnt = 0;
        ListIterator<String> ll = null;
        ll = l1.listIterator();

        
        score3.setText(Player1.name + "'s Score : " + Player1.score);
        score3.setFont(new Font("Serif", Font.PLAIN, 20));
        score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
        add(score3);
        score3.setVisible(true);

        score4.setText(Player2.name + "'s Score : " + Player2.score);
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
        while (ll.hasNext()) {
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
            Line nou = new Line(x1, y1, x2, y2);
            if (nou.getTrueValue(mc) == false) {

                mc.addLine(nou);
                for (int i = 0; i < mc.lineCount; i++) {
                    for (int j = i + 1; j < mc.lineCount; j++) {
                        for (int k = j + 1; k < mc.lineCount; k++) {
                            for (int h = k + 1; h < mc.lineCount; h++) {
                                if (i != j && j != k && k != h && i != k && i != h && j != h) {
                                    Square sr = new Square(mc.line[i], mc.line[j], mc.line[k], mc.line[h]);

                                    if (sr.ValidSquare()) {
                                        valid++;
                                        squarehoise = true;
                                        if (completedSqua[(sr.one.xf - 20) / 60][(sr.one.yf - 20) / 60] == false) {
                                            if (ok % 2 == 1) {

                                                JLabel playa = new JLabel();
                                                //playa.setVisible(false);
                                                playa.setText("P1");
                                                Player1.square = true;
                                                
                                                playa.setFont(new Font("Serif", Font.BOLD, 40));
                                                playa.setBounds(new Rectangle(new Point(sr.one.xf + 7, sr.one.yf + 3), playa.getPreferredSize()));
                                                add(playa);
                                                completedSqua[(sr.one.xf - 20) / 60][(sr.one.yf - 20) / 60] = true;
                                                sr.printed = true;
                                                mc.addCompletedSquare(new Ppoint(sr.one.xf, sr.one.yf));
                                                //cntMoves--;
                                                playa.setVisible(true);
                                                Player1.score++;
                                            } else {
                                                JLabel playa = new JLabel();
                                                //playa.setVisible(false);
                                                playa.setText("P2");
                                                Player2.square = true;
                                                
                                                playa.setFont(new Font("Serif", Font.BOLD, 40));
                                                playa.setBounds(new Rectangle(new Point(sr.one.xf + 7, sr.one.yf + 3), playa.getPreferredSize()));
                                                add(playa);
                                                completedSqua[(sr.one.xf - 20) / 60][(sr.one.yf - 20) / 60] = true;
                                                sr.printed = true;
                                                mc.addCompletedSquare(new Ppoint(sr.one.xf, sr.one.yf));
                                                //cntMoves--;
                                                Player2.score++;
                                                playa.setVisible(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Cnt++;
        }

        if (squarehoise) {
            if (Player2.square && !Player1.square) {
                Player1.turn = false;
                Player2.turn = true;
                Player1.square=true;
                try {
                    out.writeUTF("p2");
                } catch (IOException ex) {
                    Logger.getLogger(GpanelM.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(Player1.square && !Player2.square) {
                Player1.turn = true;
                Player2.turn = false;
                Player2.square=true;
                try {
                    out.writeUTF("p1");
                } catch (IOException ex) {
                    Logger.getLogger(GpanelM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int score = valid - prevValid;

        prevValid = valid;
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
                score3.setText(Player1.name + "'s Score : " + Player1.score);
                score3.setFont(new Font("Serif", Font.PLAIN, 20));
                score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
                add(score3);
                score3.setVisible(true);

                score4.setText(Player2.name + "'s Score : " + Player2.score);
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
