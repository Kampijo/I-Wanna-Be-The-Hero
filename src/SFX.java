import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SFX {

  private File bg, jump1, jump2, death;
  private Clip sound, sound1, sound2, sound3;
  private AudioInputStream audioIn1, audioIn2, audioIn3;

  public SFX() {

    try {
      jump1 = new File("resources/music/jump1.wav");
      jump2 = new File("resources/music/jump2.wav");
      death = new File("resources/music/death.wav");
      audioIn1 = AudioSystem.getAudioInputStream(jump1);
      audioIn2 = AudioSystem.getAudioInputStream(jump2);
      audioIn3 = AudioSystem.getAudioInputStream(death);
      sound1 = AudioSystem.getClip();
      sound2 = AudioSystem.getClip();
      sound3 = AudioSystem.getClip();
      sound1.open(audioIn1);
      sound2.open(audioIn2);
      sound3.open(audioIn3);
      sound1.start();
      sound2.start();
    } catch (UnsupportedAudioFileException e) {
    } catch (IOException e) {
    } catch (LineUnavailableException e) {
    }


  }

  public void firstJump() {
    sound1.loop(1);
  }

  public void secondJump() {
    sound2.loop(1);
  }

  public void death() {
    sound3.start();
  }

  public void stop() {
    sound1.stop();
    sound2.stop();
    sound3.stop();
    sound1.flush();
    sound2.flush();
    sound3.flush();
  }

}
