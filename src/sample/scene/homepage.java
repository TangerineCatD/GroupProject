package sample.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SoundEffect;

import java.io.IOException;

public class homepage {

    public static void load(Stage stage){
        //静态方法不能调用getClass(),只能用load
        SoundEffect.bgm("soundSample/bmg.mp3");
        try {
            Parent root = FXMLLoader.load(homepage.class.getResource("/sample/fxml/homepage.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
