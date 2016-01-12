import javax.swing.ImageIcon;
import java.util.*;
import java.awt.*;

public class Trap {

  private int xPos, yPos, width, height;
  public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, NONE = 4;
  private int direction;
  private boolean triggered;
  private LinkedList<ImageIcon> traps = new LinkedList<ImageIcon>();
  private ImageIcon trap;

  public Trap(int x, int y, int type, int dir) {
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
        yPos -= 13;
      } else if (direction == 1) {
        yPos += 7;
      } else if (direction == 2) {
        xPos -= 7;
      } else if (direction == 3) {
        xPos += 7;
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
