package com.example.stick_hero;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager implements SceneManagerInterface {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPauseMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToGame(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        AnchorPane gamePane = new AnchorPane();
//        Image bgImg = new Image("com/example/stick_hero/MainGame_Art scenery cartoon style game background vector image on VectorStock.jpeg");
//        ImageView backgroundImg = new ImageView(bgImg);
//        backgroundImg.setFitWidth(527.0);
//        backgroundImg.setFitHeight(609.0);
//        backgroundImg.setPickOnBounds(true);
//        backgroundImg.setSmooth(false);
//
//
//        //this one is not really a pillar just a piece of rectangle at the bottom of the scene
//        Rectangle sceneBase = Pillars.makePillar(0.0, 565.0, 527.0, 45.0);
//
//        //creating the first rectangle at the starting of the game
//        Rectangle startRect = Pillars.makePillar(150.0, 297.0, 115.0, 196.0);
//
//
//        //adding the hero to the top of the initial first pillar
//        Image heroImg = new Image("com/example/stick_hero/hero1.png");
//        ImageView heroImg1 = new ImageView(heroImg);
//        heroImg1.setFitHeight(51);
//        heroImg1.setFitWidth(41);
//        heroImg1.setLayoutX(218.0);
//        heroImg1.setLayoutY(253.0);
//        heroImg1.setPreserveRatio(true);
//        heroImg1.setPickOnBounds(true);

    }

    // Code for the saving the number of cherries and all might be pending
    public void exitGame() throws IOException {
        Platform.exit();
    }

    public void switchToInstructions(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Instructions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //need to add code for savin the score and cherries and all
    public void switchToMainMenuFromGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHighScorePage(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoadSaveMenu(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loadSave.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
