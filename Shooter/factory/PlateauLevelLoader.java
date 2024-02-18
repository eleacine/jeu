package Shooter.factory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlateauLevelLoader{

    public static int[][] loadPlayingBoard(String filePath, int level) {
        int[][] currentBoard = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int line_level = -1;
            boolean isTargetLevel = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Level")) {
                    line_level++;
                    isTargetLevel = (line_level == level);
                }
                if (isTargetLevel && line.trim().startsWith("{")) {
                    if (currentBoard == null) {
                        currentBoard = parseLine(line);
                    } else {
                        int[][] newBoard = new int[currentBoard.length + 1][];
                        System.arraycopy(currentBoard, 0, newBoard, 0, currentBoard.length);
                        newBoard[currentBoard.length] = parseLine(line)[0];
                        currentBoard = newBoard;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentBoard;
    }

    private static int[][] parseLine(String line) {
        line = line.trim().replaceAll("[{}]", ""); // Remove curly braces
        String[] rowTokens = line.split("},?\\s*\\{?");

        int[][] board = new int[rowTokens.length][];
        for (int i = 0; i < rowTokens.length; i++) {
            String[] cellTokens = rowTokens[i].split(",\\s*");
            board[i] = new int[cellTokens.length];

            for (int j = 0; j < cellTokens.length; j++) {
                try {
                    board[i][j] = Integer.parseInt(cellTokens[j].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing integer: " + cellTokens[j].trim());
                    e.printStackTrace();
                }
            }
        }

        return board;
    }
    

    public static void main(String[] args) {
        String filePath = "C:\\\\Users\\\\Leono\\\\OneDrive\\\\Documentos\\\\UNI\\\\CS\\\\M\\\\jeu\\\\Shooter\\\\factory\\\\PlateauLevels.txt";
        int targetLevel = 1; // Set the desired level

        int[][] playingBoard = loadPlayingBoard(filePath, targetLevel);

        // Example: Print the loaded playing board
        System.out.println("Level " + targetLevel + ":");
        for (int[] row : playingBoard) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}