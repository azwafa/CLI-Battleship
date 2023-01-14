import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Game implements Serializable {
    boolean isOver = false;

    // Main gameplay loop code
    public void startGame() {
        String menuSelect = "";

        // Main driver loop
        while (!menuSelect.equals("3")) {
            // Main menu
            System.out.print("""

                    Welcome to CLI Battleship! Choose an option below to get started:

                    1. New Game
                    2. Load Game
                    3. Quit

                    >\s""");

            Scanner scanner = new Scanner(System.in);
            menuSelect = scanner.nextLine();
            if (menuSelect.equals("3"))
                this.isOver = true;

            // Forces acceptable input
            while (!menuSelect.equals("1") && !menuSelect.equals("2") && !menuSelect.equals("3")) {
                System.out.println("Invalid selection.");

                System.out.print("""

                        Welcome to CLI Battleship! Choose an option below to get started:

                        1. New Game
                        2. Load Game
                        3. Quit

                        >\s""");

                menuSelect = scanner.nextLine();
                if (menuSelect.equals("3"))
                    this.isOver = true;
            }

            if (menuSelect.equals("1")) {
                this.newGame();
            }
        }
    }

    public void newGame() {
        // Initialize objects
        Game newGame = new Game();
        Board p1Grid = new Board();
        Board p2Grid = new Board();
        Player p1 = new Player();
        Player p2 = new Player();
        ArrayList<Ship> p1ships = new ArrayList<>(3);
        ArrayList<Ship> p2ships = new ArrayList<>(3);
        Ship a = new Ship();
        Ship b = new Ship();
        Ship c = new Ship();
        Ship d = new Ship();
        Ship e = new Ship();
        Ship f = new Ship();
        p1ships.add(a);
        p1ships.add(b);
        p1ships.add(c);
        p2ships.add(d);
        p2ships.add(e);
        p2ships.add(f);

        // Ship placement phase
        p1Grid.displayBoard();

        for (Ship i: p1ships) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Player 1 Turn"); // Must be implemented in OOP
            System.out.print("Place Ships phase: Enter x and y coordinates (e.g. 1,3): ");
            String p1Input = scanner.nextLine();

            while (p1Input.matches(".*[a-zA-Z].*") || p1Input.length() != 3) {
                System.out.print("Invalid input. Re-enter coordinates (e.g. 1,3): ");
                p1Input = scanner.nextLine();
            }
            
            p1.setxInput(p1Input.substring(0,1));
            p1.setyInput(p1Input.substring(2,3));

            int x_input = parseInt(p1.getxInput());
            int y_input = parseInt(p1.getyInput());

            while (!p1Input.equalsIgnoreCase("up") &&
                    !p1Input.equalsIgnoreCase("down") &&
                    !p1Input.equalsIgnoreCase("left") &&
                    !p1Input.equalsIgnoreCase("right")) {
                System.out.print("Enter the direction you want your ship placed,\n" +
                        "(e.g) 'up', 'down', 'left', or 'right': ");
                p1Input = scanner.nextLine();
            }

            // Handles placing ships on multiple squares
            for (int j = 0; j < i.getSize(); j++) {
                try {
                    p1Grid.setShipOnSpace(i, x_input,y_input);
                } catch (Exception ex) {
                    System.out.print("Error: Ship placed out of bounds. Re-enter direction: ");
                    p1Input = scanner.nextLine();
                }

                switch (p1Input) {
                    case "up":
                        x_input--;
                        break;
                    case "down":
                        x_input++;
                        break;
                    case "left":
                        y_input--;
                        break;
                    case "right":
                        y_input++;
                        break;
                }
            }

            p1Grid.displayBoard();
        }

        p2Grid.displayBoard();

        for (Ship i: p2ships) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Player 2 Turn"); // Must be implemented in OOP
            System.out.print("Place Ships phase: Enter x and y coordinates (e.g. 1,3): ");
            String p2Input = scanner.nextLine();

            while (p2Input.matches(".*[a-zA-Z].*") || p2Input.length() != 3) {
                System.out.print("Invalid input. Re-enter coordinates (e.g. 1,3): ");
                p2Input = scanner.nextLine();
            }

            p2.setxInput(p2Input.substring(0,1));
            p2.setyInput(p2Input.substring(2,3));

            p2Grid.setShipOnSpace(i, parseInt(p2.getxInput()), parseInt(p2.getyInput()));
            p2Grid.displayBoard();
        }

        // Turn-based attack phase
        while (p1ships.size() > 0 || p2ships.size() > 0) {
            System.out.println();
            System.out.println("Player 1 Turn\n"); // Must be implemented in OOP
            p1Grid.displayBoard();
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Attack phase: Enter x and y coordinates (e.g. 1,3): ");
            String p1Input = scanner.nextLine();

            while (p1Input.matches(".*[a-zA-Z].*") && p1Input.length() != 3) {
                System.out.print("Invalid input. Re-enter coordinates (e.g. 1,3): ");
                p1Input = scanner.nextLine();
            }

            p1.setxInput(p1Input.substring(0,1));
            p1.setyInput(p1Input.substring(2,3));

        /* If ship exists at position,
        Get ship at given position (Square.getShip?)
        Lower HP of given ship (implemented)
        Else call Board.miss() on position (implemented) */

            Ship ship = p2Grid.grid[parseInt(p1.getxInput())][parseInt(p1.getyInput())].getShip();
            int x1 = parseInt(p1.getxInput()); // Prevents exception?
            int x2 = parseInt(p1.getyInput()); // Prevents exception?

            if (ship.getHP() > -1) {
                ship.hit();
            }
            else p2Grid.miss(x1, x2);

            // When ship HP is 0, ship is removed from board
            // and becomes null in list

            if (ship.getHP() == 0) {
                p2Grid.removeShipFromSpace(ship, x1, x2);
                p2ships.remove(ship);
            }

            // Player 2 turn not implemented
        }

        // Whichever player's list still has ships wins
        // Encase core gameplay into while loop (while lists are not empty)

        System.out.println("\nGame Over.");
        this.isOver = true;

        if (p1ships.isEmpty())
            System.out.println("Player 1 wins!");
        else
            System.out.println("Player 2 wins!");

        System.out.print("\nType 3 to quit.\nType 4 to return to the menu: ");
        Scanner scanner = new Scanner(System.in);
        String endInput = scanner.nextLine();

        while (!endInput.equals("3") || !endInput.equals("4")) {
            System.out.print("Invalid selection, re-enter: ");
            endInput = scanner.nextLine();
        }
    }

    // Saves game information to a Java binary file
    public void saveGame() {
        try {
            FileOutputStream st = new FileOutputStream("Battleship.bin");
            ObjectOutputStream ot = new ObjectOutputStream(st);
            ot.writeObject(this);
            ot.close();
        } catch (Exception ex) {
            System.out.println("Error: The game could not be saved.");
        }
    }

    // Loads game information from a Java binary file
    public static Game loadGame() {
        Game newGame = new Game();
        try {
            FileInputStream st = new FileInputStream("Battleship.bin");
            ObjectInputStream ot = new ObjectInputStream(st);
            newGame = (Game) ot.readObject();
        } catch (Exception ex) {
            System.out.println("Error: The game could not be loaded.");
        }
        return newGame;
    }

    public static void main(String[] args) {
        Game newGame = new Game(); // Permanent line
        //ArrayList<Ship> game_ships = new ArrayList<>();
        //Board newBoard = new Board();
        //game_ships = newBoard.displayBoard();
        newGame.startGame();  // Permanent line

        /* Tester code

        Ship e = new Ship();
        e.setName(" \uD83D\uDEA2");
        newBoard.setShipOnSpace(e, 1, 3);
        newBoard.displayBoard();
        e.hit();
        e.hit();

        newBoard.miss(0,0);
        System.out.println(e.getHP());
        */

        newGame.saveGame();
        Game nextGame = Game.loadGame();
    }
}