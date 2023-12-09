package com.example.stick_hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    public static String abs_path = "E:\\Programming\\Assignments_Sem3\\Stick_Hero\\src\\main\\resources\\com\\example\\stick_hero\\";

    @Override
    public void start(Stage stage) throws IOException {
        try {
            //setting up the background sound that was needed in the game.

//            String background_musicfile = "Interstellar-Theme.mp3";
//            URL resource = getClass().getResource(background_musicfile);
//            Media bgsound = new Media(resource.toString());
//            MediaPlayer bgPlayer = new MediaPlayer(bgsound);
//            bgPlayer.play();

            Media media = new Media(new File(HelloApplication.abs_path+ "Interstellar-Theme.mp3").toURI().toString());
            MediaPlayer bgPlayer = new MediaPlayer(media);
            //bgPlayer.play();



            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Stick Hero");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}