import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Trap extends Platform{

    private int width, height;
    private LinkedList<ImageIcon> traps = new LinkedList<ImageIcon>();
    private ImageIcon trap;

    //TYPES OF TRAPS, 0 = APPLE, 1 = UPWARDS SPIKE, 2 = DOWNWARDS SPIKE, 3 = LIGHTNING

    public Trap(int x, int y, int type, int speed, int dir) {
        super(x, y, type, speed, dir);
        for (int i = 0; i < 4; i++) {
            traps.add(new ImageIcon("resources/platforms/trap" + i + ".gif"));
        }
        trap = traps.get(type);
        width = trap.getIconWidth();
        height = trap.getIconHeight();
    }

    public Image getImg() {
        return trap.getImage();
    }

    public Rectangle getRect() {
        return new Rectangle(super.getX(), super.getY(), width, height);

    }

}
