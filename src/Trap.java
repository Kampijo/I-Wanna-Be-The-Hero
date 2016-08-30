import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Trap {

    private int xPos, yPos, width, height;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, NONE = 4;
    private int direction, speed;
    private boolean triggered;
    private LinkedList<ImageIcon> traps = new LinkedList<ImageIcon>();
    private ImageIcon trap;

    //TYPES OF TRAPS, 0 = APPLE, 1 = UPWARDS SPIKE, 2 = DOWNWARDS SPIKE, 3 = LIGHTNING

    public Trap(int x, int y, int type, int speed, int dir) {
        xPos = x;
        yPos = y;
        for (int i = 0; i < 4; i++) {
            traps.add(new ImageIcon("resources/platforms/trap" + i + ".gif"));
        }
        trap = traps.get(type);
        width = trap.getIconWidth();
        height = trap.getIconHeight();
        direction = dir;
        triggered = false;
        this.speed = speed;
    }

    public Image getImg() {
        return trap.getImage();
    }

    public Rectangle getRect() {
        return new Rectangle(xPos, yPos, width, height);

    }

    public void move() {
        if (triggered) {
            if (direction == 0) {
                yPos -= speed;
            } else if (direction == 1) {
                yPos += speed;
            } else if (direction == 2) {
                xPos -= speed;
            } else if (direction == 3) {
                xPos += speed;
            } else if (direction == 4) {
                xPos += 0;
                yPos += 0;
            }
        }

    }

    public void setPoint(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int yPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int dir) {
        direction = dir;
    }

    public void triggered() {
        triggered = true;
    }

    public int getDirection() {
        return direction;
    }

}
