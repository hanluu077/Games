import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> compPosition   = new ArrayList<Integer>();

    public static void main(String[] args) {
        // Create a game board with two arrays. 1x column and 1x rows
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};
        // Introduction
        System.out.println("Welcome to TicTacToe!");
        // Display intro game
        printGameBoard(gameBoard);
        System.out.println();

        // Ask users for their position
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("Enter your position (1-9): ");
            int playerPos = scan.nextInt();
            System.out.println("YOU:");
            // refer to switchCase to place player's and computer's position

            while (playerPosition.contains(playerPos) || compPosition.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            Random rand = new Random();
            int compPos = rand.nextInt(9) + 1;      // random [1, 9]
            while (playerPosition.contains(compPos) || compPosition.contains(compPos)) {
                compPos = rand.nextInt(9) + 1;
            }
            System.out.println("\nCOMPUTER:");
            placePiece(gameBoard, compPos, "comp");
            System.out.println();

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            // need to add for each element in the char to print that out too
            for (char elem : row) {
                System.out.print(elem);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = ' ';
        if (user.equals("player")){
            symbol = 'X';
            playerPosition.add(pos);
        }
        else if (user.equals("comp")){
            symbol = 'O';
            compPosition.add(pos);
        }

        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
        printGameBoard(gameBoard);

    }
    public static String checkWinner(){

        List topRow   = Arrays.asList (1,2,3);
        List midRow   = Arrays.asList (4,5,6);
        List botRow   = Arrays.asList (7,8,9);
        List leftCol  = Arrays.asList (1,4,7);
        List midCol   = Arrays.asList (2,5,8);
        List RightCol = Arrays.asList (3,6,9);
        List cross1   = Arrays.asList (1,5,8);
        List cross2   = Arrays.asList (3,5,7);

        // add these into a list to check more easily
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(RightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l:winning){
            if(playerPosition.containsAll(l)){
                return "Congratulations! You won!";
            }
            else if (compPosition.containsAll(l)){
                return "Sorry, you lost :( Computer wins!";
            }
            else if (playerPosition.size() + compPosition.size() == 9){
                return "Tie result! Everyone is a winner!";
            }
        }
        return "";

    }
}

// Reference: A. Lee, 2019, Youtube, "Tic Tac Toe Java Game - Build a Tic Tac Toe Game in 30 Minutes".
