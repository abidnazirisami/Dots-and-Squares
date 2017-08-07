
package projectrs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kakas
 */
public class Draw extends JFrame {
    JPanel jp;
    GameOver go;
    int mousx,mousy;
    int ii;
    
    
    //Offline:
    
    public Draw(Player Player1, Player Player2,List l) {
        
        super("Dots and Squares");    
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        go = new GameOver(this);
        jp = new GPanel(Player1, Player2, l,go);
        
        jp.setVisible(true);
        jp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GPanel Temp = (GPanel) jp;
                Temp.isTurn = true;
                mousx = e.getX();
                mousy = e.getY();
                    int i = mousx;
                    int j = mousy;
                    if(needsLine(i,j))
                    {
                           if(((i+ii-20)%60)==0)
                           {
                               i = i + ii - 20;
                               int yyy = (j-20)/60;
                               yyy=yyy*60 + 20;
                               if(yyy>=0 && yyy < 281)
                               {
                                    l.add((i+27)+" "+ (yyy+7)+" " +(i+27)+" "+(yyy+60+7));
                                    repaint();
                               }
                           }
                           if(((j+ii-20)%60)==0 && i+ii<=340)
                            {
                               j = j +  ii -20;
                               int xxx = (i-20)/60;
                               xxx=xxx*60 +20;
                               if(xxx>=0 && xxx < 281)
                               {
                                    l.add((xxx+7)+" "+ (j+27)+" " +(xxx+60+7)+" "+(j+27));
                                    repaint();
                              }    
                            }
                     }
                     if(Temp.gameOver)
                    setVisible(false);
                    
                    }
            
        });
        Component add = add(jp);
        jp.setVisible(true);
    }
    
    
    
    
//Online:
    
    void detectMouse(Player Player1, Player Player2, List l, DataInputStream in, DataOutputStream out, JPanel jp) {
        jp.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                   GpanelM Temp = (GpanelM) jp;
                   mousx = e.getX();
                   mousy = e.getY();
                   int i=mousx;
                   int j=mousy;
                   if(needsLine(i,j))
                   {
                       if(((i+ii-20)%60)==0)
                       {
                           i = i+ii-20;
                           int yyy = (j-20)/60;
                           yyy = yyy*60+20;
                           if(yyy>=0 && yyy <281)
                           {
                               Line tmp = new Line((i+27),(yyy+7),(i+27),(yyy+60+7));
                               if(tmp.getTrueValue(mc)==false)
                               {
                                   if((Player1.current && Player1.turn) || (Player2.current && Player2.turn)) {
                                   l.add((i+27)+" "+(yyy+7)+" "+(i+27)+" "+(yyy+60+7));
                                   Player1.square=false;
                                   Player2.square=false;
                                   try {
                                       out.writeUTF((i+27)+" "+(yyy+7)+" "+(i+27)+" "+(yyy+60+7));
                                       String s= in.readUTF();
                                       if("p1".equals(s)) {
                                           Player1.turn = false;
                                           Player2.turn = true;
                                           s=in.readUTF();
                                       }
                                       if("p2".equals(s)) {
                                           Player2.turn=false;
                                           Player1.turn = true;
                                           s=in.readUTF();
                                       }
                                       if(Player1.turn && Player1.current && !"bla".equals(s))
                                       {
                                           Player1.turn = false;
                                           Player2.turn = true;
                                           
                                       }
                                       else if(Player2.turn && Player2.current && !"bla".equals(s))
                                       {
                                           Player2.turn = false;
                                           Player1.turn = true;
                                           
                                       }
                                       
                                   } catch (IOException ex) {
                                       Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                                   
                               }
                            }
                               //repaint();
                           }
                       }
                       else if(((j+ii-20)%60)==0 && i+ii <=340)
                       {
                           j = j+ii-20;
                           int xxx = (i-20)/60;
                           xxx=xxx*60+20;
                           if(xxx>=0 && xxx < 281)
                           {
                                Line tmp = new Line((xxx+7),(j+27),(xxx+60+7),(j+27));
                                if(tmp.getTrueValue(mc)==false)
                                {
                                    if((Player1.current && Player1.turn )||(Player2.turn && Player2.current)) {
                                    l.add((xxx+7)+" "+(j+27)+" "+(xxx+60+7)+" "+(j+27));
                                    try {
                                       out.writeUTF((xxx+7)+" "+(j+27)+" "+(xxx+60+7)+" "+(j+27));
                                       String s= in.readUTF();
                                      if(Player1.turn && !"bla".equals(s))
                                       {
                                           Player1.turn = false;
                                           Player2.turn = true;
                                          
                                       }
                                       else if(Player2.turn && !"bla".equals(s))
                                       {
                                           Player2.turn = false;
                                           Player1.turn = true;
                                           
                                       }
                                       
                                   } catch (IOException ex) {
                                       Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                               }
                               //repaint();
                           }
                           }
                       }
                       
                   }
                   }
              public void ismouseReleased(MouseEvent e)
              {
                  repaint();
                  
              }
            });
        }
    
    boolean first = true;
    myCollections mc = new myCollections(100);
    public Draw(Player Player1, Player Player2, List l, DataInputStream in, DataOutputStream out) throws IOException {
        
        super("Dots and Squares");
        setSize(800, 400);
        go = new GameOver(this);
        String lines;
        jp = new GpanelM(Player1, Player2, l, go, in, out, mc);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Component add2;
        add2 = add(jp);
        jp.setVisible(true);
        

    detectMouse(Player1,Player2,l,in,out,jp);

        
    }
    
    boolean needsLine(int x,int y)
    {
        int xx = x- 20 ;
        int yy = y -20 ;
        for(ii=-15;ii<=0;ii++)
            if((((xx+ii)%60 == 0)|| ((yy+ii)%60==0)) && (!((xx%60 ==0) && (yy%60==0))) && (xx+ii)>=0 && (xx+ii) <=320 && (yy+ii)>=0 && (yy+ii) <=320)return true;
        return false;
    }
    void close(){
        setVisible(false);
    }
}