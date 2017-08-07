
package projectrs;

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
