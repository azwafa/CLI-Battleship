import java.util.ArrayList;

public class Board {
    // Arraylists store each player's ships
    ArrayList<Ship> p1Ships = new ArrayList<>();
    ArrayList<Ship> p2Ships = new ArrayList<>();

    // Board is made up of squares represented by 2D array
    Square[][] grid = new Square[10][10];
    Ship emptyShip = new Ship();

    public Board() {
        this.makeBoard();
    }

    // Creates empty squares
    public void makeBoard() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                grid[x][y] = new Square();
                setShipOnSpace(emptyShip, x, y);
                emptyShip.setName("\033[34m" + " \u2022 " + "\033[0m");
                emptyShip.setHP(-1);
            }
        }
    }

    // Indicates player miss on board
    public void miss(int x, int y) {
        Ship m = new Ship();
        m.setName(" M ");
        setShipOnSpace(m, x, y);
    }

    // Places ship on indicated square
    public void setShipOnSpace(Ship ship, int x, int y) {
        grid[x][y].setShip(ship);
    }

    // !! Implement function to remove ship from space
    public void removeShipFromSpace(Ship ship, int x, int y) {
        Ship sunk = new Ship();
        sunk.setName("\033[33m" + " S " + "\033[0m");
        sunk.setHP(-1);
        grid[x][y].setShip(sunk);
    }

    // Displays the board
    public ArrayList<Ship> displayBoard() {
        ArrayList<Ship> ship_list = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.print(grid[x][y].getShip().getName());
                ship_list.add(grid[x][y].getShip());
            }
            System.out.println();
        }
        System.out.println("\nTo end the game, type 3. To restart, type 4.\n");
        return ship_list;
    }
}
