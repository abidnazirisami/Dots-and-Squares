package projectrs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    Menu() {
        JFrame Frame = new JFrame();
        Frame.setLayout(new BorderLayout());
        Frame.setSize(250, 300);
        Frame.setTitle("Menu");
        Frame.setName("Demo");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel empty2 = new JLabel("                                                                   ");
        JLabel empty3 = new JLabel("                                                                   ");
        JLabel empty4 = new JLabel("                                                                   ");
        JButton newGame = new JButton("New Game(Offline)");
        JButton multi = new JButton("Spectate Game(Online)");
        newGame.setSize(200, 100);
        JLabel empty = new JLabel("                                                                   ");
        JLabel empty1 = new JLabel("                                                                  ");
        JButton howToPlay = new JButton("How To Play");
        howToPlay.setSize(200, 100);

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame.setVisible(false);
                GameEngine gameengine = new GameEngine();
            }

        });
        howToPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame.setVisible(false);
                Tutorial newtutorial = new Tutorial();
            }
        });

        multi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Frame.setVisible(false);
                    Socket socket = new Socket("0.0.0.0", 8080);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    String s = in.readUTF();
                    Multiplayer m = new Multiplayer(s, socket);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        panel.add(empty3);
        panel.add(empty4);
        panel.add(newGame);

        panel.add(empty1);
        panel.add(multi);
        panel.add(empty);
        panel.add(howToPlay);
        Frame.add(panel);

        Frame.setVisible(true);
    }
}
