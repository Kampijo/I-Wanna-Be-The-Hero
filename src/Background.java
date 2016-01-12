import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import java.util.*;
import java.awt.*;

public class Background {

  private int xPos, yPos, width, height;
  private ImageIcon decoration;
  private LinkedList<ImageIcon> type = new LinkedList<ImageIcon>();


  public Background(int x, int y, int type) {
    xPos = x;
    yPos = y;
    for (int i = 0; i < 4; i++) {
      this.type.add(new ImageIcon("resources/misc/deco" + i + ".png"));
    }
    this.type.add(new ImageIcon("resources/misc/victory.gif"));
    decoration = this.type.get(type);
    width = decoration.getIconWidth();
    height = decoration.getIconHeight();
  }

  public Image getImg() {
    return decoration.getImage();
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
}
