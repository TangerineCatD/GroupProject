package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

//import javax.print.attribute.standard.Media;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchingGame {
    //記錄第一次點擊的圖案
    private static Rectangle firstRect ;
    //記錄第二次點擊的圖案
    private static Rectangle secondRect ;
    //定義一行有10個圖案
    private static final int numRows = 10;
    //定義一列有10個圖案
    private static final int numCols = 10;
    //每個圖案的大小
    private static double cellSize = 70;
    //用來幫助初始化100個正方形,放入圖片的數值 按Collections.shuffle(integers);隨機打亂後的數值index
    private static int RN = 0;
    //用來解決點擊自己圖案兩次bug的數值
    private static Rectangle lastClickedRect;


    public static Group RandomRectangle() {
        List<Integer> integers = new ArrayList();//創建數組放入1到50個數字 保證相同數字有一對組合
        for (int i = 1; i < 51; i++) {
            integers.add(i);
            integers.add(i);
        }

        final Group group = new Group();

        Collections.shuffle(integers);//將數組的數字隨便打亂

        //初始化100個正方形
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                //獲得圖案
                Image image = new Image("sample/image/Piexl" + integers.get(RN) + ".jpg");
                //將圖片變成Pattern 因為rectangle.setFill();只可以放入Pattern類型
                ImagePattern imagePattern = new ImagePattern(image);
                //創建正方形的
                final Rectangle rectangle = new Rectangle(col * cellSize, row * cellSize, cellSize, cellSize);
                //因為我們是通過id來判斷兩個圖案是不是一樣的 要設置id來識別
                rectangle.setId(integers.get(RN).toString());

                //創建每個正方形被鼠標點擊後的反應
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent even) {
                        //當第一次點擊正方形會進入
                        if (firstRect == null) {
                            //獲得點擊的正方形的對象
                            firstRect = getClickedRectangle(even);
                            //為解決點擊同一個正方形也可以消除的bug
                            lastClickedRect =firstRect;
                            //令點擊後正方形變淺色
                            firstRect.setOpacity(0.5);

                        }
                        //第二次點擊就會進入
                        else if (secondRect == null) {
                            SoundEffect.soundeffectplay("soundSample/error.mp3");
                            //獲得點擊的正方形的對象
                            secondRect = getClickedRectangle(even);
                            //為解決點擊同一個正方形也可以消除的bug
                            lastClickedRect=secondRect;
                            // 检查 ID 是否相同，如果相同则删除相同的 Rectangle ,同時判斷 上一次點擊的正方形不等於第一次點擊的正方形 解決bug
                            if (firstRect.getId().equals(secondRect.getId())&& lastClickedRect!=firstRect) {
//                                group.getChildren().removeAll(firstRect, secondRect);
                                //相同就會刪除第一個點擊的正方形
                                removeRectangle(firstRect, group);
                                //相同就會刪除第二個點擊的正方形
                                removeRectangle(secondRect, group);
                            }else {
                                //如果兩個正方形不一樣就將第一次點擊正方形的按鈕顏色變回原本的顏色
                                firstRect.setOpacity(1);
                            }
                            // 重置变量
                            firstRect = null;
                            secondRect = null;
                        }
                    }

                });
                //將圖片放入正方形
                rectangle.setFill(imagePattern);
                //將正方形的邊框顏色為黑色
                rectangle.setStroke(Color.BLACK);
                //將正方形的角會變圓
                rectangle.setArcWidth(10);
                //將正方形的角會變圓
                rectangle.setArcHeight(10);
                //邊框粗度
                rectangle.setStrokeWidth(1);
                //將100個圖案的放入group 每一個正方形是node
                group.getChildren().add(rectangle);
                //每次循環將打亂後的隨機數組的index加1
                RN++;
            }
        }
//        5,6,7,8
//        int n =1;
//        for (int col = 0; col < numCols; col++) {
//            for (int row = 0; row < numRows; row++) {
//                Image image = new Image("sample/image/Piexl" + n + ".jpg");
//                ImagePattern imagePattern = new ImagePattern(image);
//                Rectangle rectangle = new Rectangle(col * cellSize, row * cellSize, cellSize, cellSize);
//                rectangle.setFill(imagePattern);
//                rectangle.setStroke(Color.BLACK);
//                rectangle.setArcWidth(10);
//                rectangle.setArcHeight(10);
//                rectangle.setStrokeWidth(1);
//                group.getChildren().add(rectangle);
//                n++;
//            }
//        }

        return group;
    }
    private static void removeRectangle(final Rectangle rectangle1, final Group group1) {
        rectangle1.setOpacity(1.0); // 设置初始透明度为 1.0

        // 创建透明度动画
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), rectangle1);
        fadeTransition.setToValue(0.0);

        // 创建縮放动画
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), rectangle1);
        scaleTransition.setToX(0.0);
        scaleTransition.setToY(0.0);

        // 创建旋转动画
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), rectangle1);
        rotateTransition.setByAngle(360);

        // 組合多個動畫
        ParallelTransition parallelTransition = new ParallelTransition(rectangle1, fadeTransition, scaleTransition, rotateTransition);

        // 在动画完成时删除 Rectangle 对象
        parallelTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group1.getChildren().remove(rectangle1);
//                String bgmFile = "soundSample/Eliminate.wav";
                SoundEffect.soundeffectplay("soundSample/Eliminate.wav");
                // 創建 Media 對象
//        Media bgmMedia = new Media(getClass().getResource(bgmFile).toString());
//                AudioClip audioClip = new AudioClip(getClass().getResource(bgmFile).toString());
//                audioClip.play();
                // 播放音效
//                AudioClip sound = new AudioClip(new File("sample/soundSample/sound1.mp3").toURI().toString());
//
//                sound.play();
            }
        });
//        parallelTransition.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                group1.getChildren().remove(rectangle1);
//            }
//        });

        // 播放动画
        parallelTransition.play();
    }
//    private static void removeRectangle(final Rectangle rectangle1, final Group group1) {
//        rectangle1.setOpacity(1.0); // 设置初始透明度为 1.0
//        // 创建透明度动画
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(rectangle1.opacityProperty(), 1.0)),
//                new KeyFrame(Duration.seconds(1), new KeyValue(rectangle1.opacityProperty(), 0.0))
//        );
//        // 在动画完成时删除 Rectangle 对象
//        timeline.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                group1.getChildren().remove(rectangle1);
//            }
//        });
//        // 播放动画
//        timeline.play();
//    }
    private static Rectangle getClickedRectangle(MouseEvent event) {
        // 获取点击的 Rectangle 对象
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode instanceof Rectangle) {
            return (Rectangle) clickedNode;
        }
        return null;
    }

//    public  static boolean gameover(Group g){
//
//    }


}