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
    int mousx, mousy;
    int ii;

    //Offline:
    public Draw(Player Player1, Player Player2, List l) {

        super("Dots and Squares");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        go = new GameOver(this);
        jp = new GPanel(Player1, Player2, l, go);

        jp.setVisible(true);
        jp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GPanel Temp = (GPanel) jp;
                Temp.isTurn = true;
                mousx = e.getX();
                mousy = e.getY();
                int i = mousx;
                int j = mousy;
                if (needsLine(i, j)) {
                    if (((i + ii - 20) % 60) == 0) {
                        i = i + ii - 20;
                        int yyy = (j - 20) / 60;
                        yyy = yyy * 60 + 20;
                        if (yyy >= 0 && yyy < 281) {
                            l.add((i + 27) + " " + (yyy + 7) + " " + (i + 27) + " " + (yyy + 60 + 7));
                            repaint();
                        }
                    }
                    if (((j + ii - 20) % 60) == 0 && i + ii <= 340) {
                        j = j + ii - 20;
                        int xxx = (i - 20) / 60;
                        xxx = xxx * 60 + 20;
                        if (xxx >= 0 && xxx < 281) {
                            l.add((xxx + 7) + " " + (j + 27) + " " + (xxx + 60 + 7) + " " + (j + 27));
                            repaint();
                        }
                    }
                }
                if (Temp.gameOver) {
                    setVisible(false);
                }

            }

        });
        Component add = add(jp);
        jp.setVisible(true);
    }

    boolean first = true;
    myCollections mc = new myCollections(100);

    public Draw(Player Player1, Player Player2, List l, DataInputStream in, DataOutputStream out, GpanelM gp) throws IOException {

        super("Dots and Squares");
        setSize(800, 400);
        go = new GameOver(this);
        String lines;
        jp = gp;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Component add2;
        add2 = add(jp);
        jp.setVisible(true);

    }

    boolean needsLine(int x, int y) {
        int xx = x - 20;
        int yy = y - 20;
        for (ii = -15; ii <= 0; ii++) {
            if ((((xx + ii) % 60 == 0) || ((yy + ii) % 60 == 0)) && (!((xx % 60 == 0) && (yy % 60 == 0))) && (xx + ii) >= 0 && (xx + ii) <= 320 && (yy + ii) >= 0 && (yy + ii) <= 320) {
                return true;
            }
        }
        return false;
    }

    void close() {
        setVisible(false);
    }
}
