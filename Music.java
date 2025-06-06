import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {





        public static void backgroundmusic(){
                try{
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\sedat\\Desktop\\PortScanner\\musics\\tribe-drum-loop-103173.wav"));
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        floatControl.setValue(-5.0f);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        clip.start();

                }catch (Exception e){
                        e.printStackTrace();
                }
        }

        public static void hitmusic() {
                try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\sedat\\Desktop\\PortScanner\\musics\\powerful-cannon-shot-352459.wav"));
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        floatControl.setValue(-10.0f);
                        clip.start();
                }catch (Exception  e){
                        e.printStackTrace();
                }
        }


}
