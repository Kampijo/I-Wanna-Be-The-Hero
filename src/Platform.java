import javax.swing.ImageIcon;
import java.util.*;
import java.awt.*;

public class Platform {

  private int xPos, yPos, width, height;
  public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, NONE = 4;
  private int direction;
  private boolean triggered;
  private ImageIcon platform;
  private LinkedList<ImageIcon> type = new LinkedList<ImageIcon>();


  public Platform(int x, int y, int type, int dir) {
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
        yPos -= 7;
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

  public void triggered() {
    triggered = true;
  }
}
