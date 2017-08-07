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
public class Player {
    int score;
    String name;
    boolean turn = false;
    boolean isWinner=false;
    boolean current=false;
    boolean square =false;
    Player() {
        score=0;
    }
    Player(String s)
    {
        score = 0;
        name = s;
    }
}
