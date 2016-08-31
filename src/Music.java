import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music implements LineListener {
    private File bg1, bg2, bg3;
    private Clip sound1, sound2, sound3;
    private AudioInputStream audioIn1, audioIn2, audioIn3;
    private boolean switchMusic, stopMusic;


    public Music() {

        try {
            bg1 = new File("resources/music/bgmusic1.wav");
            bg2 = new File("resources/music/bgmusic2.wav");
            bg3 = new File("resources/music/victory.wav");
            audioIn1 = AudioSystem.getAudioInputStream(bg1);
            audioIn2 = AudioSystem.getAudioInputStream(bg2);
            audioIn3 = AudioSystem.getAudioInputStream(bg3);
            sound1 = AudioSystem.getClip();
            sound2 = AudioSystem.getClip();
            sound3 = AudioSystem.getClip();
            sound1.addLineListener(this);
            sound2.addLineListener(this);
            sound3.addLineListener(this);
            sound1.open(audioIn1);
            sound2.open(audioIn2);
            sound3.open(audioIn3);

            switchMusic = true;
            stopMusic = false;

        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }

    }

    public void stopBG() {
        stopMusic = true;
        sound1.stop();
        sound2.stop();
        sound1.flush();
        sound2.flush();
    }

    public void playBG() {
        stopMusic = false;
        sound1.start();
    }

    public void victory() {
        sound3.start();
    }

    public void stopVictory() {
        sound3.stop();
        sound3.flush();
    }
    public void update(LineEvent line){
        LineEvent.Type type = line.getType();
        if(type == LineEvent.Type.STOP && !stopMusic){
            if(switchMusic){
                sound2.start();
                switchMusic = false;
            }
            else{
                sound1.start();
                switchMusic = true;
            }
        }

    }

}
