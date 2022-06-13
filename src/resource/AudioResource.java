package resource;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioResource {
    private AudioInputStream audioInput;
    Clip clip = null;
    //for debugging
    String path;
    public AudioResource(String path){
        this.path = path;
        try {
            File musicFile = new File(path);
            if (musicFile.exists()){
                audioInput = AudioSystem.getAudioInputStream(musicFile);
            }
            else{
                System.out.println("geen file gevonden: " + path);
            }
            //voor getAudioInputStream()
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            //ook voor getAudioInputStream()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopMusic(){
        if (clip != null){
            if (clip.isOpen()){

                clip.stop();
                clip.close();
            }
            else{
                System.out.println("cant stop a nonopen audio: " + path);
            }
        }
        else {
            System.out.println("clip is null: " + path);
        }
    }
    public void playMusic(){
        if (clip != null){
            if (clip.isOpen()){

                clip.setFramePosition(0);
                clip.start();
            }
            else{
                System.out.println("cant stop a nonopen audio: " + path);
            }
        }
        else {

            try{
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }//voor open()
            catch (IOException e) {
                e.printStackTrace();
            } //voor getClip()
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        /*System.out.println(String.format("clip=%d/%d(%.2f)"
                ,clip.getFramePosition(), clip.getFrameLength()
                ,((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).getValue()));
        */
    }

}
