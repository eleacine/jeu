import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MazeGameWithWalls extends JFrame implements ActionListener, KeyListener {
    private static final int SIZE = 40;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private int playerX = 0;
    private int playerY = 0;

    private int enemyX = 9;
    private int enemyY = 9;

    private int[][] walls = new int[WIDTH][HEIGHT];

    private Timer timer;

    public MazeGameWithWalls() {
        setTitle("Maze Game with Walls");
        setSize(WIDTH * SIZE, HEIGHT * SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeMaze();

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(1000, this);
        timer.start();
    }

    private void initializeMaze() {
        getContentPane().setLayout(new GridLayout(HEIGHT, WIDTH));

        // Add walls
        setWall(3, 2);
        setWall(3, 3);
        setWall(3, 4);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Set wall color
                if (walls[i][j] == 1) {
                    cell.setBackground(Color.BLACK);
                }

                getContentPane().add(cell);
            }
        }

        // Set player and enemy
        setCellColor(playerX, playerY, Color.BLUE);
        setCellColor(enemyX, enemyY, Color.RED);
    }

    private void setWall(int x, int y) {
        if (isValidMove(x, y)) {
            walls[x][y] = 1;
        }
    }

    private void setCellColor(int x, int y, Color color) {
        Component[] components = getContentPane().getComponents();
        int index = y * WIDTH + x;
        if (index >= 0 && index < components.length) {
            components[index].setBackground(color);
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && walls[x][y] != 1;
    }

    private void movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;

        if (isValidMove(newX, newY)) {
            setCellColor(playerX, playerY, Color.WHITE);
            playerX = newX;
            playerY = newY;
            setCellColor(playerX, playerY, Color.BLUE);
        }
    }

    private void moveEnemyTowardsPlayer() {
        int deltaX = (enemyX < playerX) ? 1 : ((enemyX > playerX) ? -1 : 0);
        int deltaY = (enemyY < playerY) ? 1 : ((enemyY > playerY) ? -1 : 0);

        int newX = enemyX + deltaX;
        int newY = enemyY + deltaY;

        if (isValidMove(newX, newY)) {
            setCellColor(enemyX, enemyY, Color.WHITE);
            enemyX = newX;
            enemyY = newY;
            setCellColor(enemyX, enemyY, Color.RED);
        } else {
            // Si la case suivante est un mur, l'ennemi doit choisir une direction alternative
            int[] alternativeDirection = chooseAlternativeDirection(deltaX, deltaY);
            newX = enemyX + alternativeDirection[0];
            newY = enemyY + alternativeDirection[1];

            if (isValidMove(newX, newY)) {
                setCellColor(enemyX, enemyY, Color.WHITE);
                enemyX = newX;
                enemyY = newY;
                setCellColor(enemyX, enemyY, Color.RED);
            }
        }
    }

    private int[] chooseAlternativeDirection(int deltaX, int deltaY) {
        // Choisir une direction alternative pour contourner le mur
        if (deltaX != 0) {
            // Si le déplacement horizontal, essayer de se déplacer verticalement
            return new int[]{0, (deltaY < 0) ? 1 : -1};
        } else {
            // Si le déplacement vertical, essayer de se déplacer horizontalement
            return new int[]{(deltaX < 0) ? 1 : -1, 0};
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Timer action: Move the enemy towards the player
        moveEnemyTowardsPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle player movement
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                movePlayer(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                movePlayer(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                movePlayer(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                movePlayer(1, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGameWithWalls().setVisible(true));
    }
}
