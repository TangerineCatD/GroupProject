package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.scene.Gamepage;
import sample.scene.homepage;

public class Director {

    public static final double width = 490, HEIGHT = 660;

    private static Director instance = new Director();
    private Stage stage;

    private Director() {
    }

    public static Director getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, width, HEIGHT);
        stage.setTitle("Matching Games");//窗口名字
        stage.getIcons().add(new Image("/sample/image/Piexlicon.gif"));//窗口图标
        stage.setResizable(false);//不能移动窗口
        stage.setScene(scene);
        this.stage = stage;
        tohomepage();
        stage.show();

    }

    public void tohomepage() { homepage.load(stage); }

    public void togamepage() {
        Gamepage.load(stage);
    }


    public static void Time() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // 每秒钟执行一次的代码
                Label timeLabel = new Label();
                int time = 0;
                timeLabel.setText("Time: " + Integer.toString(time));
                time++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

}
