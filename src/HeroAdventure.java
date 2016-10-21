import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HeroAdventure extends JPanel implements KeyListener,
        ActionListener {


    // SPACE TO JUMP, LEFT AND RIGHT ARROW KEYS TO MOVE
    // THIS IS A DIFFICULT GAME. IF YOU DIE, YOU'RE JUST REALLY BAD AT THE GAME.

    private Hero guy; // Declares the hero
    private Levels levels; // Declare the level
    private Music music; // Declare the music that will be played
    private SFX sfx; // Declare the sfx that will be played
    private ImageIcon gameover; // Declare the ImageIcon for game over
    public static Splatter death; // Declare the splatter class (death animation particles)
    public static boolean finish; // Declare a boolean that determines whether player reached the finish line

    // Declaring and initializing timers that keep track of player position and death splatter
    private Timer tracker = new Timer(15, this);
    private Timer splatter = new Timer(35, this);

    // Declaring and initializing panel width and height to position certain
    // elements of the game
    private final int PANEL_WIDTH = 1200, PANEL_HEIGHT = 600;

    // Main method of the program that starts the program
    public static void main(String[] args) {

        new HeroAdventure(); // Initializes new instance of the program
    }

    public HeroAdventure() {
        guy = new Hero(); // Initializes the hero
        levels = new Levels(PANEL_WIDTH, PANEL_HEIGHT); // Initializes the level
        gameover = new ImageIcon("resources/misc/gameover.png"); // Initializes game
        // over icon
        sfx = new SFX(); // Initializes sound fx
        music = new Music(); // Initializes the music
        death = new Splatter(); // Initializes Splatter class responsible for the
        // death splatter
        music.playBG(); // Starts playing the background music
        // Starts the tracking timer
        tracker.start();
        finish = false; // Sets finish line to false
        // Sets properties of the JFrame
        JFrame frame = new JFrame("I Wanna Be The Hero");
        frame.setContentPane(this);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.setBackground(new Color(195, 215, 247));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setVisible(true);

    }

    public void update(Graphics g) {

        paint(g); // paints to g
    }

    public void paint(Graphics g) {
        // Double buffer - Clearing the offscreen image, drawing onto offscreen
        // image and transferring to main screen
        // before clearing again with update()
        Graphics offgc;
        Image offscreen = null;
        Dimension d = getSize();
        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics(); // gives offgc a graphics context of the
        // Image to paint to
        offgc.clearRect(0, 0, d.width, d.height);
        levels.draw(offgc);
        // Draws to offscreen image
        if (!guy.isDead()) // draws hero only if he isn't dead
        {
            guy.draw(offgc);
        }
        if (guy.isDead()) // if he is dead, draw game over logo and draw death
        // splatter particles
        {
            offgc.drawImage(gameover.getImage(),
                    PANEL_WIDTH / 2 - gameover.getIconWidth() / 2, 100, null);
            death.draw(offgc);
        }
        if (finish) // if guy reach finish line, draw game over logo
        {
            offgc.drawImage(gameover.getImage(),
                    PANEL_WIDTH / 2 - gameover.getIconWidth() / 2, 100, null);
        }
        g.drawImage(offscreen, 0, 0, this); // Transfers offscreen image to main
        // screen

    }

    public void actionPerformed(ActionEvent e) {

        levels.triggerTrap(guy); // keep track of whether hero triggered a trap

        if (guy.getX() >= PANEL_WIDTH) {
            levels.setLevelNumber(levels.getLevelNumber() + 1);
            reset();
        }
        if (!finish) {
            guy.movement(); // run the hero's movements
            levels.intersect(guy); // check if guy intersects/lands/hits any platforms
        }
        if (e.getSource() == splatter) // if splatter is running
        {
            death.splatter(); // run the death splatter animation
        }
        if (e.getSource() == tracker) // if tracker is running
        {
            death.setPoint(guy.getX(), guy.getY()); // constantly track movement of
            // guy to give location
            // of where death splatter will occur
        }
        if (Hero.gravity <= 0) // if guy reaches the apex of his jump, set the
        // player to fall and reset the gravity constant
        {
            Hero.hasJumped = false; // sets the player to fall
            Hero.gravity = 0; // resets gravity constant
        }
        if (guy.isDead()) // if guy dies
        {
            tracker.stop(); // stop the tracker
            splatter.start(); // start splatter timer in order to run the death
            // splatter animation
            music.stopBG(); // stops background music
            sfx.death(); // Instead, plays death sound fx
            // Disables current movement
            Hero.moveLeft = false;
            Hero.moveRight = false;
        }
        if (finish) // if guy finishes/wins
        {
            // stops background music and plays victory music, while also disabling
            // any current movement
            music.stopBG();
            music.victory();
        }
        repaint(); // repaints the main screen

    }

    public void keyPressed(KeyEvent f) {

        if (!guy.isDead() && !finish) // only takes into account keys pressed if guy
        // is not dead
        {
            if (f.getKeyCode() == KeyEvent.VK_SPACE) // Jump
            {
                Hero.jumpLimit++; // adds to the jump limit counter
                if (Hero.jumpLimit == 1) {
                    sfx.firstJump(); // plays first jump sfx on first jump
                }
                if (Hero.jumpLimit == 2) {
                    sfx.secondJump(); // plays second jump sfx on second jump
                    Hero.gravity = 0.5;
                }
                if (Hero.jumpLimit <= 2) // if player hasn't reached jump limit of 2,
                // allow player to jump
                {
                    // allows player to jump
                    Hero.hasJumped = true;
                    Hero.gravity = 1;
                    if (Hero.jumpLimit == 2) {
                        Hero.gravity = 0.75;
                    }
                }
            }
            if (f.getKeyCode() == KeyEvent.VK_RIGHT) // if player presses right arrow
            // key
            {
                // Allow hero to move right and sets direction image to east
                Hero.moveRight = true;
                Hero.direction = Hero.EAST;
            }
            if (f.getKeyCode() == KeyEvent.VK_LEFT) // if left arrow key pressed
            {
                // Allow hero to move left and sets direction image to west
                Hero.moveLeft = true;
                Hero.direction = Hero.WEST;
            }
        }

        if (f.getKeyCode() == KeyEvent.VK_R) // if r is pressed, restarts the game
        {
            restart();
        }

        repaint(); // repaints the main screen

    }

    public void restart() // restarts the game by resetting all elements of the
    // game to their default values
    {
        // resets level, hero, music, death particles and stops death timer and
        // restarts movement and tracker timers
        levels.reset();
        guy.reset();

        music.reset();
        sfx.reset();
        death.reset();

        tracker.start();
        music.playBG();
        splatter.stop();
        finish = false;

    }

    public void reset() {
        levels.reset();
        guy.reset();
    }

    // if keys released
    public void keyReleased(KeyEvent g) {

        // if right key released, disable hero's right movement
        if (g.getKeyCode() == KeyEvent.VK_RIGHT) {
            Hero.moveRight = false;
            Hero.moving = false;
        }
        // if left key released, disable hero's left movement
        if (g.getKeyCode() == KeyEvent.VK_LEFT) {
            Hero.moveLeft = false;
            Hero.moving = false;
        }
    }

    public void keyTyped(KeyEvent arg0) {
    }


}
