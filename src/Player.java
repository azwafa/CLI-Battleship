import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable {
    private String number; // e.g. player1 or player2
    private boolean turn;
    private String xInput;
    private String yInput;
    //private ArrayList<Ship> shipList;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getxInput() {
        return xInput;
    }

    public void setxInput(String xInput) {
        this.xInput = xInput;
    }

    public String getyInput() {
        return yInput;
    }

    public void setyInput(String yInput) {
        this.yInput = yInput;
    }
}
