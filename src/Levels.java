import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class Levels {

    private LinkedList<Platform> platform;
    private LinkedList<Trap> traps;
    private LinkedList<Background> deco;
    private ImageIcon ground;
    private int frameWidth, frameHeight, levelNumber;

    public Levels(int x, int y) {
        frameWidth = x;
        frameHeight = y;
        ground = new ImageIcon("resources/platforms/ground.png");
        platform = new LinkedList<Platform>();
        traps = new LinkedList<Trap>();
        deco = new LinkedList<Background>();
        levelNumber = 1;
        reset();
    }


    public void draw(Graphics g) {

        g.drawImage(ground.getImage(), 0, frameHeight - 30, null);
        g.drawImage(ground.getImage(), 700, frameHeight - 30, null);

        for (int i = 0; i < deco.size(); i++) {
            g.drawImage(deco.get(i).getImg(), deco.get(i).getX(), deco.get(i).getY(),
                    null);
        }
        for (int i = 0; i < traps.size(); i++) {
            g.drawImage(traps.get(i).getImg(), traps.get(i).getX(), traps.get(i)
                    .getY(), null);
        }
        for (int i = 0; i < platform.size(); i++) {
            g.drawImage(platform.get(i).getImg(), platform.get(i).getX(), platform
                    .get(i).getY(), null);
        }


    }

    public void intersect(Hero h) {

        for (int i = 0; i < platform.size(); i++) {
            if (h.getX() + h.getWidth() / 2 >= platform.get(i).getX()
                    && h.getX() + h.getWidth() / 2 <= platform.get(i).getX()
                    + platform.get(i).getWidth()) {
                if (h.getY() + h.getHeight() >= platform.get(i).getY()
                        && h.getY() + h.getHeight() <= platform.get(i).getY() + 10) {

                    platform.get(i).triggered();
                    if (Hero.grounded) {
                        h.setY(platform.get(i).getY() - h.getHeight());
                    }
                    if (platform.get(i).getSpeed() > 0 && !Hero.moving) {
                        h.setX(platform.get(i).getX());

                    }

                    Hero.grounded = true;
                    Hero.gravity = 0;
                }

                if (h.getY() <= platform.get(i).getY() + platform.get(i).getHeight()
                        && h.getY() >= platform.get(i).getY()) {
                    h.setY(platform.get(i).getY() + platform.get(i).getHeight());
                    Hero.hasJumped = false;
                }

            }
            platform.get(i).move();

        }
        if (h.getY() + h.getHeight() >= frameHeight - 30) {
            Hero.grounded = true;
            h.setY(frameHeight - h.getHeight() - 30);
        }
        if (h.getY() <= 0) {
            Hero.dead = true;
        }
        if (h.getX() <= 0) {
            h.setX(0);
        }
        if (h.getX() + h.getWidth() >= frameWidth && levelNumber == 2){
            HeroAdventure.finish = true;
            h.setX(frameWidth-h.getWidth());
        }

    }

    public void triggerTrap(Hero h) {
        for (int i = 0; i < traps.size(); i++) {
            if (h.getX() + h.getWidth() >= traps.get(i).getX()
                    && h.getX() + h.getWidth() <= traps.get(i).getX()
                    + traps.get(i).getWidth() && (traps.get(i).getDirection() == Platform.UP ||
                    traps.get(i).getDirection() == Platform.DOWN)) {
                traps.get(i).triggered();
            }
            if(h.getY() >= traps.get(i).getY() && (traps.get(i).getDirection() == Platform.LEFT ||
                    traps.get(i).getDirection() == Platform.RIGHT)){
                traps.get(i).triggered();
            }
            if (traps.get(i).getRect().intersects(h.getRect())) {
                Hero.dead = true;
            }
            traps.get(i).move();
        }
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber(){ return levelNumber; }

    public void reset() {
        platform.clear();
        traps.clear();
        deco.clear();

        if (levelNumber == 1) {
            platform.add(new Platform(50, frameHeight - 120, 1, 0, Platform.NONE));
            platform.add(new Platform(50, frameHeight - 225, 1, 0, Platform.NONE));
            platform.add(new Platform(200, frameHeight - 240, 11, 0, Platform.NONE));
            platform.add(new Platform(550, frameHeight - 240, 4, 0, Platform.NONE));
            platform.add(new Platform(710, frameHeight - 240, 2, 7, Platform.DOWN));
            platform.add(new Platform(870, frameHeight - 250, 12, 7, Platform.UP));
            platform.add(new Platform(1020, frameHeight - 120, 7, 0, Platform.NONE));

            deco.add(new Background(200, frameHeight - 407, 1));
            deco.add(new Background(420, frameHeight - 407, 0));
            deco.add(new Background(110, 85, 5));
            deco.add(new Background(350, 100, 4));
            deco.add(new Background(525, 45, 4));
            deco.add(new Background(650, 130, 5));
            deco.add(new Background(900, 90, 4));
            deco.add(new Background(1150, frameHeight - 287, 6));

            traps.add(new Trap(50, frameHeight - 120, 2, 7, Trap.DOWN));
            traps.add(new Trap(220, frameHeight - 360, 0, 0, Trap.NONE));
            traps.add(new Trap(270, frameHeight - 365, 0, 7, Trap.DOWN));
            traps.add(new Trap(360, frameHeight - 360, 0, 7, Trap.DOWN));
            traps.add(new Trap(325, frameHeight - 340, 0, 0, Trap.NONE));
            traps.add(new Trap(400, frameHeight - 350, 0, 7, Trap.DOWN));
            traps.add(new Trap(435, frameHeight - 380, 0, 7, Trap.DOWN));
            traps.add(new Trap(460, frameHeight - 360, 0, 0, Trap.NONE));
            traps.add(new Trap(500, frameHeight - 370, 0, 7, Trap.DOWN));

            int spikePosition = 200;
            for (int i = 0; i < 35; i++) {
                if (i == 11 || i == 12) {
                    traps.add(new Trap(spikePosition, frameHeight - 56, 1, 13, Trap.UP));
                } else {
                    traps.add(new Trap(spikePosition, frameHeight - 56, 1, 0, Trap.NONE));
                }

                spikePosition += 28;
            }

        }
        if (levelNumber == 2) {
            traps.add(new Trap(120, 85, 3, 20, Trap.DOWN));
            traps.add(new Trap(175, 485, 0, 40, Trap.RIGHT));
            traps.add(new Trap(350, 100, 3, 20, Trap.DOWN));
            traps.add(new Trap(420, 375, 1, 0, Trap.NONE));

            platform.add(new Platform(110, 80, 12, 0, Platform.NONE));
            platform.add(new Platform(120, 95, 12, 0, Platform.NONE));
            platform.add(new Platform(0, frameHeight - 120, 7, 0, Platform.NONE));
            platform.add(new Platform(220, 400, 1, 1, 400, Platform.LEFTRIGHT, true));
            platform.add(new Platform(620, 400, 1, 1, 495, Platform.RIGHTLEFT, true));
            platform.add(new Platform(420, 400, 1, 0, Platform.NONE));
            platform.add(new Platform(340, 90, 12,0, Platform.NONE));
            platform.add(new Platform(335, 105, 13, 0, Platform.NONE));
            platform.add(new Platform(690, 350, 2, 7, Platform.DOWN));
            platform.add(new Platform(790, 300, 2, 7, Platform.DOWN));
            platform.add(new Platform(910, 250, 2, 7, Platform.UP));
            platform.add(new Platform(1090, 350, 4, 0, Platform.NONE));


            deco.add(new Background(950, 405, 1));
            deco.add(new Background(1000, 120, 5));
            deco.add(new Background(525, 45, 4));
            deco.add(new Background(650, 120, 5));
            deco.add(new Background(900, 90, 4));
            deco.add(new Background(1170, 183, 6));

            int spikePosition = 320;
            for (int i = 0; i < 31; i++) {

                traps.add(new Trap(spikePosition, frameHeight - 56, 1, 0, Trap.NONE));
                spikePosition += 28;
            }


        }

    }
}
