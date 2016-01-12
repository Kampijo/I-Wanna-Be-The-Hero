import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;

public class BloodParticles {

  private int xPos, yPos, xSpeed, ySpeed, direction;
  private Random rnd;
  private ImageIcon particle;

  public BloodParticles() {
    xPos = 0;
    yPos = 0;
    rnd = new Random();
    xSpeed = rnd.nextInt(75) + 1;
    ySpeed = rnd.nextInt(75) + 1;
    direction = rnd.nextInt(4);
    particle = new ImageIcon("resources/misc/bloodparticle.png");
  }

  public BloodParticles(int x, int y) {
    xPos = x;
    yPos = y;
    rnd = new Random();
    xSpeed = rnd.nextInt(75) + 1;
    ySpeed = rnd.nextInt(75) + 1;
    direction = rnd.nextInt(4);
    particle = new ImageIcon("resources/misc/bloodparticle.png");
  }

  public Image getImage() {
    return particle.getImage();
  }

  public void move() {
    if (direction == 0) {
      xPos += xSpeed;
      yPos += ySpeed;
    } else if (direction == 1) {
      xPos += xSpeed;
      yPos -= ySpeed;
    } else if (direction == 2) {
      xPos -= xSpeed;
      yPos += ySpeed;
    } else {
      xPos -= xSpeed;
      yPos -= ySpeed;
    }

  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }


}
