import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Music {
  private File bg, bg1;
  private Clip sound, sound1;
  private AudioInputStream audioIn;
  private AudioInputStream audioIn1;

  public Music() {

    try {
      bg = new File("resources/music/bgmusic.wav");
      bg1 = new File("resources/music/victory.wav");
      audioIn = AudioSystem.getAudioInputStream(bg);
      audioIn1 = AudioSystem.getAudioInputStream(bg1);
      sound = AudioSystem.getClip();
      sound1 = AudioSystem.getClip();
      sound.open(audioIn);
      sound1.open(audioIn1);

    } catch (UnsupportedAudioFileException e) {
    } catch (IOException e) {
    } catch (LineUnavailableException e) {
    }

  }

  public void stopBG() {
    sound.stop();
    sound.flush();
  }

  public void playBG() {
    sound.start();
    sound.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void victory() {
    sound1.start();
  }

  public void stopVictory() {
    sound1.stop();
    sound1.flush();
  }

}
