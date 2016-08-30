import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Platform {

    private int xPos, yPos, width, height;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, NONE = 4, LEFTRIGHT = 5;
    private int direction, speed, initialPosition, turnPosition;
    private boolean triggered, turn;
    private ImageIcon platform;
    private LinkedList<ImageIcon> type = new LinkedList<ImageIcon>();


    public Platform(int x, int y, int type, int speed, int dir) {
        xPos = x;
        yPos = y;
        this.type.add(new ImageIcon("resources/platforms/ground.png"));
        for (int i = 1; i < 13; i++) {
            this.type.add(new ImageIcon("resources/platforms/platform" + i + ".png"));
        }
        platform = this.type.get(type);
        width = platform.getIconWidth();
        height = platform.getIconHeight();
        direction = dir;
        this.speed = speed;
    }

    public Platform(int x, int y, int type, int speed, int turnPosition, int dir) {
        xPos = x;
        yPos = y;
        this.type.add(new ImageIcon("resources/platforms/ground.png"));
        for (int i = 1; i < 13; i++) {
            this.type.add(new ImageIcon("resources/platforms/platform" + i + ".png"));
        }
        platform = this.type.get(type);
        width = platform.getIconWidth();
        height = platform.getIconHeight();
        direction = dir;
        this.speed = speed;
        this.turnPosition = turnPosition;
        initialPosition = x;
        turn = false;
    }

    public Image getImg() {
        return platform.getImage();
    }

    public void setPoint(int x, int y) {
        xPos = x;
        yPos = y;
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
            } else if (direction == 5) {
                if (xPos + width <= turnPosition && !turn) {
                    xPos += speed;
                } else {
                    turn = true;
                    xPos -= speed;
                    if (xPos <= initialPosition) {
                        turn = false;
                    }
                }
            }
        }
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

    public int getSpeed() {
        return speed;
    }

    public void triggered() {
        triggered = true;
    }
}
