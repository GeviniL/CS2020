
    import java.util.Scanner;

    // This class represents a TicTacToe game.
    public class TicTacToe {
        // The game board.
        private char[][] board; 
        // The current player.
        private char currentPlayer;

        // Constructor for TicTacToe.
        public TicTacToe() {
            // Initialize the game board.
            board = new char[3][3];
            // Set the current player to 'x'.
            currentPlayer = 'x';
            // Initialize the game board.
            initializeBoard();
        }
    
        // This method initializes the game board.
        public void initializeBoard() {
            // Loop through rows.
            for (int i = 0; i < 3; i++) {
                // Loop through columns.
                for (int j = 0; j < 3; j++) {
                    // Set the cell to '-'.
                    board[i][j] = '-';
                }
            }
        }

        // This method changes the current player.
        public void changePlayer() {
            // If the current player is 'x', change it to 'o'.
            if (currentPlayer == 'x') {
                currentPlayer = 'o';
            } else {
                // If the current player is not 'x', change it to 'x'.
                currentPlayer = 'x';
            }
        }

        // This method places a mark at the cell specified by row and col.
        public boolean placeMark(int row, int col) {
            // Check if the row and col are valid.
            if ((row >= 0) && (row < 3)) {
                if ((col >= 0) && (col < 3)) {
                    // Check if the cell is not already marked.
                    if (board[row][col] == '-') {
                        // Mark the cell.
                        board[row][col] = currentPlayer;
                        return true;
                    }
                }
            }

            // Return false if the cell is not marked.
            return false;
        }

        // This method checks for a win.
        public boolean checkForWin() {
            // Check rows, columns, and diagonals for a win.
            return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
        }

        // This method checks the rows for a win.
        private boolean checkRowsForWin() {
            // Loop through rows.
            for (int i = 0; i < 3; i++) {
                // Check if the row has same character.
                if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                    return true;
                }
            }
            // Return false if no win is found.
            return false;
        }

        // This method checks the columns for a win.
        private boolean checkColumnsForWin() {
            // Loop through columns.
            for (int i = 0; i < 3; i++) {
                // Check if the column has same character.
                if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                    return true;
                }
            }
            // Return false if no win is found.
            return false;
        }

        // This method checks the diagonals for a win.
        private boolean checkDiagonalsForWin() {
            // Check the diagonals for a win.
            return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || checkRowCol(board[0][2], board[1][1], board[2][0]) == true);
        }

        // This method checks if the three cells have the same character that is not '-'.
        private boolean checkRowCol(char c1, char c2, char c3) {
            return ((c1 != '-') && (c1 == c2) && (c2 == c3));
        }

        // This method prints the game board.
        public void printBoard() {
            System.out.println("-------------");
            // Loop through rows.
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                // Loop through columns.
                for (int j = 0; j < 3; j++) {
                    // Print each cell.
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println();
                System.out.println("-------------");
            }
        }

        // This method returns the current player.
        public char getCurrentPlayer() {
            return currentPlayer;
        }

        // This method checks if the board is full.
        public boolean isBoardFull() {
            // Loop through rows.
            for (int i = 0; i < 3; i++) {
                // Loop through columns.
                for (int j = 0; j < 3; j++) {
                    // If any cell is not marked, return false.
                    if (board[i][j] == '-') {
                        return false;
                    }
                }
            }
            // Return true if all cells are marked.
            return true;
        }

        // This method makes a move for the current player.
        public void makeMove() {
            Scanner scanner = new Scanner(System.in);
            int row, col;
            do {
                // Prompt the player to make a move.
                System.out.println("Player " + getCurrentPlayer() + ", enter an empty row and column to place your mark!");
                // Get the row and column from the player.
                row = scanner.nextInt()-1;
                col = scanner.nextInt()-1;
            } while (!placeMark(row, col));
            scanner.close();
        }

        // The main method.
        public static void main(String[] args) {
            // Create a new game.
            TicTacToe game = new TicTacToe();
            // Flag to check if the game has ended.
            boolean gameEnded = false;
            // Game loop.
            while (!gameEnded) {
                // Print the game board.
                game.printBoard();
                // Change the current player.
                game.changePlayer();
                // Make a move.
                game.makeMove();
                // Check for a win.
                gameEnded = game.checkForWin();
                // If the game has ended, print the winner.
                if (gameEnded) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                } else if (game.isBoardFull()) {
                    // If the game is a draw, print a message.
                    gameEnded = true;
                    System.out.println("The game is a draw!");
                }
            }
        }
    }
