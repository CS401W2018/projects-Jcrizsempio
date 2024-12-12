import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; // Import Random class

// Abstract Spaceship class
abstract class Spaceship {
    protected Point position;
    protected int id;
    protected Image image; // Image for spaceship

    public Spaceship(int id, int startX, int startY, Image image) {
        this.position = new Point(startX, startY);
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void draw(Graphics g) {
        // Draw the spaceship image
        g.drawImage(image, position.x, position.y, 40, 60, null);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(id), position.x + 15, position.y + 35);
    }

    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, 40, 60);
    }
}

// BasicSpaceship class
class BasicSpaceship extends Spaceship {
    public BasicSpaceship(int id, int startX, int startY, Image image) {
        super(id, startX, startY, image);
    }
}

// Main Game Panel
public class Main extends JPanel implements ActionListener, KeyListener {
    private Spaceship spaceship;
    private Timer timer;
    private Rectangle[] asteroids;
    private boolean shouldMoveDown;
    private Image spaceshipImage;
    private Image asteroidImage;
    private Random random; // Random object for generating random positions

    public Main() {
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        // Load images
        spaceshipImage = new ImageIcon("SpaceshipGame/src/Spaceship.png").getImage(); // Replace with your image path
        asteroidImage = new ImageIcon("SpaceshipGame/src/Asteriod.png").getImage(); // Replace with your image path

        // Initialize random number generator
        random = new Random();

        // Initialize spaceship starting at the top-center
        spaceship = new BasicSpaceship(0, 380, 0, spaceshipImage);

        // Initialize asteroids with random positions
        asteroids = generateRandomAsteroids();

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the spaceship
        spaceship.draw(g);

        // Draw asteroids with images
        for (Rectangle asteroid : asteroids) {
            g.drawImage(asteroidImage, asteroid.x, asteroid.y, asteroid.width, asteroid.height, null);
        }

        // Draw green platform
        g.setColor(Color.GREEN);
        g.fillRect(350, 750, 100, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move spaceship down if the flag is true
        if (shouldMoveDown) {
            spaceship.move(0, 2);
        }

        // Check for collision with asteroids
        for (Rectangle asteroid : asteroids) {
            if (spaceship.getBounds().intersects(asteroid)) {
                JOptionPane.showMessageDialog(this, "You have hit an Asteroid. You Crashed!");
                resetGame();
                return;
            }
        }

        // Check for landing on the green platform
        Rectangle platform = new Rectangle(350, 750, 100, 20);
        if (spaceship.getBounds().intersects(platform)) {
            JOptionPane.showMessageDialog(this, "Congratulations on your journey. Welcome Home!");
            resetGame();
            return;
        }

        // Repaint the screen
        repaint();
    }

    private void resetGame() {
        // Reset spaceship to starting position
        spaceship = new BasicSpaceship(0, 380, 0, spaceshipImage);
        shouldMoveDown = false; // Reset downward movement flag

        // Generate new random positions for asteroids
        asteroids = generateRandomAsteroids();
    }

    private Rectangle[] generateRandomAsteroids() {
        // Fixed number of asteroids, always 10
        int numAsteroids = 10;
        Rectangle[] randomAsteroids = new Rectangle[numAsteroids];

        // Generate random positions for the asteroids
        for (int i = 0; i < randomAsteroids.length; i++) {
            int x, y;

            // Ensure the asteroid doesn't spawn at the same position as the spaceship
            do {
                x = random.nextInt(700); // Random x position within the screen width (0-700)
                y = random.nextInt(600); // Random y position within the screen height (0-600)
            } while (new Rectangle(x, y, 100, 100).intersects(spaceship.getBounds()));

            randomAsteroids[i] = new Rectangle(x, y, 100, 100); // Set random position with fixed size
        }
        return randomAsteroids;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            spaceship.move(-5, 0); // Move left
            shouldMoveDown = true; // Start moving down
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            spaceship.move(5, 0); // Move right
            shouldMoveDown = true; // Start moving down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spaceship Game");
        Main game = new Main();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
