package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Director;
import sample.SoundEffect;

public class homepageC {
    @FXML
    private ImageView startGame;

    @FXML
    void MouseClickedStartGame(MouseEvent event) {
        SoundEffect.stop();
        Director.getInstance().togamepage();
//        SoundEffect.play("resources/sound/sound1.mp3");
//        SoundEffect.play("/sound/click1.wav");
//        String s = homepageC.class.getResource("/sound/sound1.mp3").toString();
//        AudioClip ac = new AudioClip(s);

//        SoundEffect.play("/resources/sound/sound1.mp3");
//        SoundEffect.play("/Users/orangecat/IdeaProjects/untitled7/src/resources/sound/sound1.mp3");

//        /Users/orangecat/IdeaProjects/untitled7/src/resources/sound/click1.wav

    }

    @FXML
    void MouseEnteredStartGame(MouseEvent event) {
        startGame.setOpacity(0.8);
//        SoundEffect.play("/sound/click1.wav");
//        String s = this.getClass().getResource("sound/click1.wav").toString();
//        System.out.println(s);
//        AudioClip audioClip = new AudioClip(s);
//        System.out.println(s);
//        audioClip.play();
//        String bgmFile = "soundSample/click1.wav";
        // 創建 Media 對象
//        Media bgmMedia = new Media(getClass().getResource(bgmFile).toString());
//        AudioClip audioClip = new AudioClip(getClass().getResource(bgmFile).toString());
//        audioClip.play();
        SoundEffect.soundeffectplay("soundSample/click1.wav");


//        AudioClip audioClip = new AudioClip(getClass().getResource("resource/sound/sound1.mp3").toString());
//        audioClip.play();


    }

    @FXML
    void MouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }
}
