package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage)  {
        Director.getInstance().init(primaryStage);


    }


    public static void main(String[] args) {
        Application.launch(args);



    }
}
