import java.awt.*;


public class Splatter {

    private BloodParticles[] splatter;

    public Splatter() {
        splatter = new BloodParticles[500];

    }

    public void splatter() {
        for (int i = 0; i < splatter.length; i++) {
            splatter[i].move();
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < splatter.length; i++) {
            g.drawImage(splatter[i].getImage(), splatter[i].getX(),
                    splatter[i].getY(), null);
        }
    }

    public void setPoint(int x, int y) {
        for (int i = 0; i < splatter.length; i++) {
            splatter[i] = new BloodParticles(x, y);
        }
    }

    public void reset() {
        for (int i = 0; i < splatter.length; i++) {
            splatter[i] = null;
        }
    }

}
