package sample.scene;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Director;
import sample.SoundEffect;
import sample.MatchingGame;

import java.io.IOException;

public class Gamepage {
    private static Group group;

    public Gamepage() {
//        group = test2.RandomRectangle();

    }


    public static void load(final Stage stage) {
        SoundEffect.bgm("soundSample/pixel-sky-129006.mp3");
        //静态方法不能调用getClass(),只能用load
        try {
//            if (group.getChildren().isEmpty()){
//                homepage.load(stage);
//            }else {
            Parent root = FXMLLoader.load(homepage.class.getResource("/sample/fxml/Gamepage.fxml"));
            stage.getScene().setRoot(root);
            group = MatchingGame.RandomRectangle();
            stage.setScene(new Scene(group, 700, 700));
            group.getChildren().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Node> c) {
                    if (group.getChildren().isEmpty()) {
                        // 當 Group 為空時，
                        SoundEffect.stop();
                        stage.setWidth(Director.width);
                        stage.setHeight(Director.HEIGHT);
                        homepage.load(stage);
//                        Director.getInstance().tohomepage();
                    }
                }
            });
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
