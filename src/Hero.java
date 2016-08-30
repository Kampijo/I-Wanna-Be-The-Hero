import javax.swing.*;
import java.awt.*;

public class Hero {

    public static int direction, jumpLimit;
    public static boolean hasJumped, moveLeft, moveRight, grounded, dead, moving;
    public static final int EAST = 0;
    public static final int WEST = 1;
    private int xPos, yPos, width, height;
    private double constant = 0.05, multiplier = 7;
    public static double gravity = 1, speed;
    private ImageIcon hero;

    public Hero() {
        xPos = 0;
        yPos = 450;
        hero = new ImageIcon("resources/hero/eastIdle.gif");
        direction = EAST;
        width = hero.getIconWidth();
        height = hero.getIconHeight();
        jumpLimit = 0;
        direction = EAST;
        hasJumped = false;
        dead = false;
        moving = false;
    }

    public void draw(Graphics g) {
        if (direction == EAST) {
            if (hasJumped) {
                hero = new ImageIcon("resources/hero/eastJump.png");
            } else if (!grounded) {
                hero = new ImageIcon("resources/hero/eastFall.png");
            } else if (moveRight) {
                hero = new ImageIcon("resources/hero/eastMoving.gif");
            } else {
                hero = new ImageIcon("resources/hero/eastIdle.gif");
            }
        } else if (direction == WEST) {
            if (hasJumped) {
                hero = new ImageIcon("resources/hero/westJump.png");
            } else if (!grounded) {
                hero = new ImageIcon("resources/hero/westFall.png");
            } else if (moveLeft) {
                hero = new ImageIcon("resources/hero/westMoving.gif");
            } else {
                hero = new ImageIcon("resources/hero/westIdle.gif");
            }
        }
        g.drawImage(hero.getImage(), xPos, yPos, null);
    }

    public void movement() {
        if (hasJumped) {
            grounded = false;
            gravity -= constant;
            speed = gravity * multiplier;
            yPos -= speed;
            if (moveLeft || moveRight) {
                move();
            }
        } else if (!hasJumped) {
            gravity += constant;
            speed = gravity * multiplier;
            yPos += speed;
            if (grounded) {
                jumpLimit = 0;
                yPos += 0;
            }
            if (moveLeft || moveRight) {
                move();
            }
        }
    }

    public void move() {
        moving = true;
        if (direction == 0) {
            xPos += 2.5;
        }
        if (direction == 1) {
            xPos -= 2.5;
        }

    }

    public void reset() {
        xPos = 0;
        yPos = 450;
        hero = new ImageIcon("resources/hero/eastIdle.gif");
        direction = EAST;
        width = hero.getIconWidth();
        height = hero.getIconHeight();
        jumpLimit = 0;
        direction = EAST;
        hasJumped = false;
        dead = false;
        gravity = 0;
        grounded = false;
    }

    public Rectangle getRect() {
        return new Rectangle(xPos, yPos, width, height);
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public boolean isDead() {
        return dead;
    }

}
