package projectrs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GPanel extends JPanel {
        List l1;
        GameOver go;
        boolean[][] completedSqua = new boolean[7][7];
        int[][][] colory= new int[100][100][100];
        int[][][] colorx= new int[100][100][100];
        boolean isTurn=false, gameOver = false;
        boolean done=false;
        JLabel score3 = new JLabel();
        JLabel score4 = new JLabel();
        JLabel turn = new JLabel();
        boolean firstsquare = true;
        Player Player1, Player2;
        int prevValid=0;
        int cntMoves = 0;
        myCollections mc = new myCollections(100);
        
        
        
        public GPanel(Player P1, Player P2,List l, GameOver t) {
            Player1 = P1;
            Player2 = P2;
            Player1.turn = true;
            Player2.turn = false;
            go=t;
            setLayout(null);
            setPreferredSize(new Dimension(800, 400));
            l1=l;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for(int i=20;i<=379;i+=60)
            {
                for(int j=20;j<=379;j+=60)
                {
                    g.drawOval(i, j, 15, 15);
                    g.setColor(Color.BLACK);
                    g.fillOval(i, j, 15, 15);
                }
            }
            ListIterator <String> ll = null;            
            ll= l1.listIterator();
            
            int x1=0,y1=0,x2=0,y2=0,co=0;
            while(ll.hasNext()){
                String current = ll.next();
                String now = "";
                
                int flag = 0;
                for(int i=0;i<current.length();i++)
                {
                    char u = current.charAt(i);
                    if(u==' ')
                    {
                        //System.out.println(now);
                        flag++;
                        
                        if(flag==1)x1=Integer.parseInt(now);
                        if(flag==2)y1=Integer.parseInt(now);
                        if(flag==3)x2=Integer.parseInt(now);
                  
                        now ="";
                    }
                    else now = now + u;
                }
                y2=Integer.parseInt(now);
                if(x1 == x2) {
                    int cy1 = (y1-20)/60;
                    int cy2 = (y2-20)/60;
                    int cy3 = (x1-20)/60;
                    if(colory[cy1][cy2][cy3] == 0 || colory[cy2][cy1][cy3] == 0) {
                 
                        
                        if(Player1.turn){
                            colory[cy1][cy2][cy3] = 1;
                            colory[cy2][cy1][cy3] = 1;
                        }
                        else{
                            colory[cy1][cy2][cy3] = 2;
                            colory[cy2][cy1][cy3] = 2;
                        }
                    }
                    if(colory[cy1][cy2][cy3] == 1 || colory[cy2][cy1][cy3] == 1) {
                        g.setColor(Color.WHITE);
                    }
                    else if(colory[cy1][cy2][cy3] == 2 || colory[cy2][cy1][cy3] == 2) {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                }
                else if(y1 == y2) {
                    int cy1 = (x1-20)/60;
                    int cy2 = (x2-20)/60;
                    int cy3 = (y1-20)/60;
                    if(colorx[cy1][cy2][cy3] == 0 || colorx[cy2][cy1][cy3] == 0) {
                        
                        if(Player1.turn){
                            colorx[cy1][cy2][cy3] = 1;
                            colorx[cy2][cy1][cy3] = 1;
                        }
                        else {
                            colorx[cy1][cy2][cy3] = 2;
                            colorx[cy2][cy1][cy3] = 2;
                        }
                    }
                    if(colorx[cy1][cy2][cy3] == 1 || colorx[cy2][cy1][cy3] == 1) {
                        g.setColor(Color.WHITE);
                    }
                    else if(colorx[cy1][cy2][cy3] == 2 || colorx[cy2][cy1][cy3] == 2) {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                }
                
                co++;
                
                
                g.drawLine(x1,y1,x2,y2);
                
                Line l = new Line(x1, y1, x2, y2);
                
                
                
                if(l.getTrueValue(mc)==false)
                {
                    
                    
                    if(Player1.turn)
                    {
                        Player1.turn = false;
                        Player2.turn = true;
                    //g.setColor(Color.black);
                    }
                else
                {
                    Player2.turn = false;
                    Player1.turn = true;
                    //g.setColor(Color.white);
                }
                    
                    mc.addLine(l);
                    //if(mc.lineCount%2==0)turnofPlayer1=true;
                }
                else if(l.getTrueValue(mc) == true){
                    
                    
                    
                }
            }
            int valid =0;
            boolean squareFound;
            squareFound=true;
            for(int i=0;i<mc.lineCount;i++)
            {
                for(int j=i+1;j<mc.lineCount;j++)
                {
                    for(int k=j+1;k<mc.lineCount;k++)
                    {
                        for(int h=k+1;h<mc.lineCount;h++)
                        {
                            if(i!=j && j!=k && k!=h && i!=k && i!=h && j!=h)
                            {
                                Square sr = new Square(mc.line[i],mc.line[j],mc.line[k],mc.line[h]);
                                
                                if(sr.ValidSquare())
                                {
                                    valid++;
                                    
                                    if(completedSqua[(sr.one.xf-20)/60][(sr.one.yf-20)/60] == false ) {
                                        //System.out.println((sr.one.xf )/ 60+" "+(sr.one.yf)/60);
                                    if(Player1.turn && squareFound || Player2.turn && !squareFound){
                                        JLabel playa = new JLabel();
                                        /*if(firstsquare) {*/
                                            if(squareFound)
                                            {
                                                Player1.turn=false;
                                                Player2.turn=true;
                                                squareFound = false;
                                            }
                                            
                                        
                                        //playa.setVisible(false);
                                        playa.setText("P2");
                                        playa.setToolTipText(Player2.name);
                                        playa.setFont(new Font("Serif", Font.BOLD, 40));
                                        playa.setForeground(Color.GRAY);
                                        playa.setBounds(new Rectangle(new Point(sr.one.xf+7,sr.one.yf+3), playa.getPreferredSize()));
                                        add(playa);
                                        completedSqua[(sr.one.xf-20)/60][(sr.one.yf-20)/60] = true;
                                        //cy[(sr.one.yf-20)/60] = true;
                                        sr.printed=true;
                                        mc.addCompletedSquare(new Ppoint(sr.one.xf, sr.one.yf));
                                        //cntMoves--;
                                        playa.setVisible(true);
                                    }
                                    else if(Player2.turn && squareFound || Player1.turn && !squareFound){
                                        JLabel playa = new JLabel();
                                            if(squareFound)
                                            {
                                                Player1.turn = true;
                                                Player2.turn = false;
                                                squareFound = false;
                                            }
                                        
                                        //playa.setVisible(false);
                                        playa.setText("P1");
                                        playa.setToolTipText(Player1.name);
                                        playa.setFont(new Font("Serif", Font.BOLD, 40));
                                        playa.setForeground(Color.LIGHT_GRAY);
                                        playa.setBounds(new Rectangle(new Point(sr.one.xf+7,sr.one.yf+3), playa.getPreferredSize()));
                                        add(playa);
                                        completedSqua[(sr.one.xf-20)/60][(sr.one.yf-20)/60] = true;
                                        //cy[(sr.one.yf-20)/60] = true;
                                        
                                        sr.printed = true;
                                        mc.addCompletedSquare(new Ppoint(sr.one.xf, sr.one.yf));
                                        //cntMoves--;
                                        playa.setVisible(true);
                                    }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            int score = valid - prevValid;
            //boolean isTurn=false;
            
            int prevcnt = cntMoves;
            if(score==0) {
                if(cntMoves==0&&done==false)//Initial Label Print
                {
                done = true;
                cntMoves--;
                turn.setText(Player1.name+"'s Turn");
                turn.setToolTipText(Player1.name+"'s Turn");
                turn.setFont(new Font("Serif", Font.PLAIN, 20));
                turn.setBounds(new Rectangle(new Point(500, 50), turn.getPreferredSize()));
                add(turn);
                turn.setVisible(true);
                score3.setText(Player1.name+ "'s Score : "+Player1.score);
                score3.setFont(new Font("Serif", Font.PLAIN, 20));
                score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
                add(score3);
                score3.setVisible(true);
                
                score4.setText(Player2.name+ "'s Score : "+Player2.score);
                score4.setFont(new Font("Serif", Font.PLAIN, 20));
                score4.setBounds(new Rectangle(new Point(500, 150), score4.getPreferredSize()));
                add(score4);
                score4.setVisible(true);
                repaint();
                
                
                }
                cntMoves++;
                
            }
            prevValid = valid;
            if(Player2.turn){
                Player2.score = Player2.score+score;
                
            }
            else {
                Player1.score = Player1.score + score;
                
            }
            if(score==0 && isTurn) {
                turn.setVisible(false);
                if(Player2.turn) {
                    turn.setText(Player2.name+"'s Turn");
                    turn.setToolTipText(Player2.name+"'s Turn");
                }
                else{
                    turn.setText(Player1.name+"'s Turn");
                    turn.setToolTipText(Player1.name+"'s Turn");
                }
                turn.setFont(new Font("Serif", Font.PLAIN, 20));
                turn.setBounds(new Rectangle(new Point(500, 50), turn.getPreferredSize()));
                add(turn);
                turn.setVisible(true);
                repaint();
                isTurn = false;
                cntMoves--;
            }
                
            if(score != 0) {
                score3.setVisible(false);
                score4.setVisible(false);
                
                if(Player1.score+Player2.score == 25){
                    gameOver = true;
                    String Winner;
                    go.execute();
                    //super.setVisible(false);
                    //super.removeAll();
                    if(Player1.score<Player2.score) {
                        
                        Player2.isWinner = true;
                        Winner = Player2.name;
                    }
                    else {
                        Player1.isWinner = true;
                        Winner = Player1.name;
                    }
                    String[] buttons = {"Return to main menu"};
                    int gameover = JOptionPane.showOptionDialog(null, Winner+" Wins!", "Game Over", JOptionPane.PLAIN_MESSAGE,1,null,buttons,buttons[0]);
                    Menu menu = new Menu();
                }
                else {
                    //System.out.println("Player 1 score : "+Player1.score);
                //System.out.println("Player 2 score : "+Player2.score);
                
                score3.setText(Player1.name+ "'s Score : "+Player1.score);
                score3.setFont(new Font("Serif", Font.PLAIN, 20));
                score3.setBounds(new Rectangle(new Point(500, 100), score3.getPreferredSize()));
                add(score3);
                score3.setVisible(true);
                
                score4.setText(Player2.name+ "'s Score : "+Player2.score);
                score4.setFont(new Font("Serif", Font.PLAIN, 20));
                score4.setBounds(new Rectangle(new Point(500, 150), score4.getPreferredSize()));
                add(score4);
                score4.setVisible(true);
                repaint();
                cntMoves--;
            
                }
            }
            //System.out.println(cntMoves);
            
     }
     
              
}


