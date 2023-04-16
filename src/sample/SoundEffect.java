package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffect {
    private static MediaPlayer mediaPlayer;
    public static void soundeffectplay(String src)  {
        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(src).toString());
        audioClip.play();

    }
    public static void bgm(String src){
        Media media = new Media(SoundEffect.class.getResource(src).toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer =mediaPlayer1;
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.setVolume(0.7);

        mediaPlayer1.play();
    }
    public static void stop(){
        mediaPlayer.stop();
    }

}
//        mediaPlayer1.setRate(1.1);
//        MediaPlayer mediaPlayer =new MediaPlayer()
//        URL url = SoundEffect.class.getClassLoader().getResource(src);
//        System.out.println(url.toExternalForm());
//        AudioClip ac = new AudioClip(url.toExternalForm());
//        String s = SoundEffect.class.getResource(src).toString();
//        System.out.println(s);
//        AudioClip audioClip = new AudioClip(s);
//        audioClip.play();

//        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(src).toString());
//        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(src).toString());
//        AudioClip audioClip = new AudioClip("D:\\IdeaProjects\\untitled6\\src\\resource\\sound\\click1.wav");
//        audioClip.getSource();

//        String s = SoundEffect.class.getResource(src).toString();
//        System.out.println(s);
//        AudioClip audioClip = new AudioClip(s);
//        audioClip.play();