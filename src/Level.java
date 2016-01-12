import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

import javax.swing.ImageIcon;


public class Level {

  private LinkedList<Platform> platform;
  private LinkedList<Trap> traps;
  private LinkedList<Background> deco;
  private ImageIcon ground;
  private int frameWidth, frameHeight;

  public Level(int x, int y) {
    frameWidth = x;
    frameHeight = y;
    ground = new ImageIcon("resources/platforms/ground.png");
    platform = new LinkedList<Platform>();
    traps = new LinkedList<Trap>();
    deco = new LinkedList<Background>();
    platform.add(new Platform(50, frameHeight - 120, 1, Platform.NONE));
    platform.add(new Platform(50, frameHeight - 225, 1, Platform.NONE));
    platform.add(new Platform(200, frameHeight - 240, 11, Platform.NONE));
    platform.add(new Platform(550, frameHeight - 240, 4, Platform.NONE));
    platform.add(new Platform(710, frameHeight - 240, 2, Platform.DOWN));
    platform.add(new Platform(875, frameHeight - 270, 12, Platform.UP));
    platform.add(new Platform(998, frameHeight - 120, 7, Platform.NONE));
    traps.add(new Trap(50, frameHeight - 120, 2, 1));
    deco.add(new Background(200, frameHeight - 407, 1));
    deco.add(new Background(420, frameHeight - 407, 0));
    deco.add(new Background(110, 85, 2));
    deco.add(new Background(350, 100, 3));
    deco.add(new Background(525, 45, 3));
    deco.add(new Background(650, 130, 2));
    deco.add(new Background(900, 90, 3));
    deco.add(new Background(1150, frameHeight - 287, 4));

    traps.add(new Trap(220, frameHeight - 360, 0, Trap.NONE));
    traps.add(new Trap(270, frameHeight - 365, 0, Trap.DOWN));
    traps.add(new Trap(360, frameHeight - 360, 0, Trap.DOWN));
    traps.add(new Trap(325, frameHeight - 340, 0, Trap.NONE));
    traps.add(new Trap(400, frameHeight - 350, 0, Trap.DOWN));
    traps.add(new Trap(435, frameHeight - 380, 0, Trap.DOWN));
    traps.add(new Trap(460, frameHeight - 360, 0, Trap.NONE));
    traps.add(new Trap(500, frameHeight - 370, 0, Trap.DOWN));

    int spikelength = 200;
    for (int i = 0; i < 35; i++) {
      if (i == 11 || i == 12) {
        traps.add(new Trap(spikelength, frameHeight - 56, 1, Trap.UP));
      } else {
        traps.add(new Trap(spikelength, frameHeight - 56, 1, Trap.NONE));
      }

      spikelength += 28;
    }
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
          Hero.grounded = true;
          Hero.gravity = 0;
        }
      }
      if (h.getX() + h.getWidth() / 2 >= platform.get(i).getX()
          && h.getX() + h.getWidth() / 2 <= platform.get(i).getX()
              + platform.get(i).getWidth()
          && h.getY() <= platform.get(i).getY() + platform.get(i).getHeight()
          && h.getY() >= platform.get(i).getY()) {
        h.setY(platform.get(i).getY() + platform.get(i).getHeight());
        Hero.hasJumped = false;
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
    if (h.getX() + h.getWidth() >= frameWidth) {
      HeroAdventure.finish = true;
      h.setX(frameWidth - h.getWidth());
    }

  }

  public void triggerTrap(Hero h) {
    for (int i = 0; i < traps.size(); i++) {
      if (h.getX() + h.getWidth() >= traps.get(i).getX()
          && h.getX() + h.getWidth() <= traps.get(i).getX()
              + traps.get(i).getWidth()) {
        traps.get(i).triggered();
      }
      if (traps.get(i).getRect().intersects(h.getRect())) {
        Hero.dead = true;
      }
      traps.get(i).move();
    }
  }

  public void reset() {
    platform.clear();
    traps.clear();
    deco.clear();
    platform.add(new Platform(50, frameHeight - 120, 1, Platform.NONE));
    platform.add(new Platform(50, frameHeight - 225, 1, Platform.NONE));
    platform.add(new Platform(200, frameHeight - 240, 11, Platform.NONE));
    platform.add(new Platform(550, frameHeight - 240, 4, Platform.NONE));
    platform.add(new Platform(710, frameHeight - 240, 2, Platform.DOWN));
    platform.add(new Platform(875, frameHeight - 270, 12, Platform.UP));
    platform.add(new Platform(998, frameHeight - 120, 7, Platform.NONE));
    traps.add(new Trap(50, frameHeight - 120, 2, 1));
    deco.add(new Background(200, frameHeight - 407, 1));
    deco.add(new Background(420, frameHeight - 407, 0));
    deco.add(new Background(110, 85, 2));
    deco.add(new Background(350, 100, 3));
    deco.add(new Background(525, 45, 3));
    deco.add(new Background(650, 130, 2));
    deco.add(new Background(900, 90, 3));
    deco.add(new Background(1150, frameHeight - 287, 4));

    traps.add(new Trap(220, frameHeight - 360, 0, Trap.NONE));
    traps.add(new Trap(270, frameHeight - 365, 0, Trap.DOWN));
    traps.add(new Trap(360, frameHeight - 360, 0, Trap.DOWN));
    traps.add(new Trap(325, frameHeight - 340, 0, Trap.NONE));
    traps.add(new Trap(400, frameHeight - 350, 0, Trap.DOWN));
    traps.add(new Trap(435, frameHeight - 380, 0, Trap.DOWN));
    traps.add(new Trap(460, frameHeight - 360, 0, Trap.NONE));
    traps.add(new Trap(500, frameHeight - 370, 0, Trap.DOWN));

    int spikelength = 200;
    for (int i = 0; i < 35; i++) {
      if (i == 11 || i == 12) {
        traps.add(new Trap(spikelength, frameHeight - 56, 1, Trap.UP));
      } else {
        traps.add(new Trap(spikelength, frameHeight - 56, 1, Trap.NONE));
      }

      spikelength += 28;
    }
  }
}
