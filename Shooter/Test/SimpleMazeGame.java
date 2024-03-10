import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimpleMazeGame extends JFrame implements ActionListener, KeyListener {
    private static final int SIZE = 40;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private int playerX = 0;
    private int playerY = 0;

    private int enemyX = 9;
    private int enemyY = 9;

    private Timer timer;

    public SimpleMazeGame() {
        setTitle("Simple Maze Game");
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

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                getContentPane().add(cell);
            }
        }

        // Set player and enemy
        setCellColor(playerX, playerY, Color.BLUE);
        setCellColor(enemyX, enemyY, Color.RED);
    }

    private void setCellColor(int x, int y, Color color) {
        Component[] components = getContentPane().getComponents();
        int index = y * WIDTH + x;
        if (index >= 0 && index < components.length) {
            components[index].setBackground(color);
        }
    }

    private void movePlayer(int deltaX, int deltaY) {
        if (isValidMove(playerX + deltaX, playerY + deltaY)) {
            setCellColor(playerX, playerY, Color.WHITE);
            playerX += deltaX;
            playerY += deltaY;
            setCellColor(playerX, playerY, Color.BLUE);
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private void moveEnemyTowardsPlayer() {
        if (enemyX < playerX) {
            moveEnemy(1, 0);
        } else if (enemyX > playerX) {
            moveEnemy(-1, 0);
        }

        if (enemyY < playerY) {
            moveEnemy(0, 1);
        } else if (enemyY > playerY) {
            moveEnemy(0, -1);
        }
    }

    private void moveEnemy(int deltaX, int deltaY) {
        if (isValidMove(enemyX + deltaX, enemyY + deltaY)) {
            setCellColor(enemyX, enemyY, Color.WHITE);
            enemyX += deltaX;
            enemyY += deltaY;
            setCellColor(enemyX, enemyY, Color.RED);
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
        SwingUtilities.invokeLater(() -> new SimpleMazeGame().setVisible(true));
    }
}
