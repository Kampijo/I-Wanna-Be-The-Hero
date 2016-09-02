import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;
import java.util.LinkedList;

public class Platform {

    private int xPos, yPos, width, height, direction, speed, initialPosition, turnPosition, type;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, NONE = 4, LEFTRIGHT = 5, RIGHTLEFT = 6;
    private boolean triggered, turn;
    private ImageIcon platform;
    private LinkedList<ImageIcon> platformTypes = new LinkedList<ImageIcon>();


    public Platform(int x, int y, int type, int speed, int dir) {
        xPos = x;
        yPos = y;
        direction = dir;
        triggered = false;
        this.speed = speed;
        this.type = type;
        addImages();
    }

    public Platform(int x, int y, int type, int speed, int turnPosition, int dir, boolean triggered) {
        xPos = x;
        yPos = y;
        direction = dir;
        initialPosition = x;
        turn = false;
        this.triggered = triggered;
        this.speed = speed;
        this.turnPosition = turnPosition;
        this.type = type;
        addImages();
    }

    public void addImages(){
        platformTypes.add(new ImageIcon("resources/platforms/ground.png"));
        for (int i = 1; i < 14; i++) {
            platformTypes.add(new ImageIcon("resources/platforms/platform" + i + ".png"));
        }
        platform = platformTypes.get(type);
        width = platform.getIconWidth();
        height = platform.getIconHeight();
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
            } else if (direction == 6) {
                if (xPos + width >= turnPosition && !turn) {
                    xPos -= speed;
                } else {
                    turn = true;
                    xPos += speed;
                    if (xPos >= initialPosition) {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int dir) {
        direction = dir;
    }

    public void triggered() {
        triggered = true;
    }
}
