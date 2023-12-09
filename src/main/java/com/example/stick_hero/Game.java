package com.example.stick_hero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Game implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int highScore=0;


    @FXML
    private AnchorPane anchor_pane_game;
    @FXML
    private Rectangle starter_pillar;
    @FXML
    private Text current_score;
    @FXML
    private Text cherry_count;
    @FXML
    private ImageView score_cherry;
    @FXML
    private Text main_high;


    private int s=292;
    private int y=0;

    private Timeline timeline_stickGrow;
    private Timeline timeline_herosuccessful;
    private Timeline timeline_herofailure;
    private Timeline next_iter_prep;

    private boolean flagOnMouseReleaseMethod=true;
    private boolean flagOnMousePushMethod=true;
    private boolean isInitialFlag;
    private boolean cherry_collected = false;

    private Rectangle pillar1;
    private Rectangle pillar2;
    private Stick activeStick;
    private ImageView hero1;
    private Rectangle rectangle_stick;
    SequentialTransition sequential1;

    private int this_game_score=0;
    private RotateTransition stickFallTransition;

    public void switchToPauseMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Game(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Add the first random pillar that you will add to the scene.
        pillar1 = starter_pillar;
        pillar2 = Pillars.makePillar();
        anchor_pane_game.getChildren().add(pillar2);
        isInitialFlag = true;

        rectangle_stick = Stick.makeStick();
        anchor_pane_game.getChildren().add(rectangle_stick);


        //adding hero to the game
        hero1 = Hero.getInstance();
        anchor_pane_game.getChildren().add(hero1);

        rectangle_stick.setWidth(5);


        timeline_stickGrow = new Timeline(new KeyFrame(Duration.millis(5), e->{
            y+=1;
            s-=1;
            rectangle_stick.setLayoutY(s);
            rectangle_stick.setHeight(y);
        }));
        timeline_stickGrow.setCycleCount(Animation.INDEFINITE);


        //setting up the RotateTransition to start the stick fall transition.
        stickFallTransition = new RotateTransition(Duration.seconds(1), rectangle_stick);
        stickFallTransition.setByAngle(90);
        stickFallTransition.setAutoReverse(false);
        stickFallTransition.setCycleCount(1);
        stickFallTransition.setOnFinished(e-> {
            boolean heroReaches = this.isInPillar(pillar1,pillar2,rectangle_stick);
            heroMove_success();
//            if(heroReaches == true){
//                heroMove_success();
//            }
//            else{
//                heroMove_fail();
//            }
        });

        //sequential1 = new SequentialTransition( stickFallTransition);


    }

    public void startGrowingStickMethod(MouseEvent event) throws IOException{
        if(flagOnMousePushMethod){
            timeline_stickGrow.play();
            flagOnMousePushMethod = false;
        }
    }
    public void stopGrowingStickMethod(MouseEvent event) throws IOException {
        if (flagOnMouseReleaseMethod) {
            timeline_stickGrow.stop();
            stickFallTransition.play();

            flagOnMouseReleaseMethod = false;
        }
    }

    private boolean isInPillar(Rectangle pillar_initial, Rectangle pillar_final , Rectangle stick){

        double minXreq = pillar_final.getLayoutX() - (pillar_initial.getLayoutX()+ pillar_initial.getWidth());
        double width = pillar_final.getWidth();
        double maxXreq = minXreq + width;
        if(stick.getHeight() > minXreq && stick.getHeight() < maxXreq){
            return true;
        }
        else{
            return false;
        }

    }

    private void heroMove_success(){
        int heroMoveCycleCount = (int) ((int) (pillar2.getX() + pillar2.getWidth()) - (pillar1.getX()+pillar1.getWidth()) - 5);

        timeline_herosuccessful = new Timeline(new KeyFrame(Duration.millis(2), e->{
            int x=1;
            hero1.setLayoutX(hero1.getLayoutX()+x);
        }
        ));
        timeline_herosuccessful.setCycleCount(heroMoveCycleCount);
        timeline_herosuccessful.play();
        timeline_herosuccessful.setOnFinished(e->{
            update_score();
            nextIterPrep();
        });
    }

    private void heroMove_fail(){
        timeline_herosuccessful = new Timeline(new KeyFrame(Duration.millis(1), e->{
            boolean transl_ongoing = true;
            double initialX = hero1.getLayoutX();
            while(transl_ongoing){
                int x=1;
                hero1.setLayoutX(hero1.getLayoutX()+x);
                if( (hero1.getLayoutX() - initialX) == rectangle_stick.getHeight()) {
                    timeline_herosuccessful.stop();
                    transl_ongoing = false;
                }
            }
        }));
        //update_high_score();

    }

    private void update_high_score(){
        int score = Integer.parseInt(current_score.getText());

        if(score > highScore){
            highScore = score;
        }
    }

    private void update_score(){
        this_game_score+=1;
        current_score.setText(String.valueOf(this_game_score));

        if(cherry_collected == true){
            int cherry = Integer.parseInt(cherry_count.getText());
            cherry+=1;
            cherry_count.setText(String.valueOf(cherry));

        }
    }

    private void nextIterPrep(){

        int nextIterCycleCount = (int) (pillar2.getX()+pillar2.getWidth() - pillar1.getX()+pillar1.getWidth());
        next_iter_prep = new Timeline(new KeyFrame(Duration.millis(5), e->{
            int x=1;
            hero1.setLayoutX(hero1.getLayoutX()-x);
            rectangle_stick.setLayoutX(rectangle_stick.getLayoutX()-x);
            pillar1.setLayoutX(pillar1.getLayoutX()-x);
            pillar2.setLayoutX(pillar2.getLayoutX()-x);
        }));
        next_iter_prep.setCycleCount(nextIterCycleCount);
        next_iter_prep.play();
        next_iter_prep.setOnFinished(e->{
            add_rem_elem();
        });

    }

    private void add_rem_elem(){
        //maybe I will need to add some of the
        Rectangle trash = pillar1;
        anchor_pane_game.getChildren().remove(trash);
        pillar1 = pillar2;

        //adding a new pillar to the game for next iteration of the game.
        pillar2 = Pillars.makePillar();
        anchor_pane_game.getChildren().add(pillar2);

        //add the new stick to the iteration.
        rectangle_stick = Stick.makeStick();
        anchor_pane_game.getChildren().add(rectangle_stick);
        //setting the flags for the next iteration.
        flagOnMouseReleaseMethod=true;
        flagOnMousePushMethod=true;

        //Setting cherry for the next iteration.
        setCherry();
        cherry_collected = false;

    }

    //this function returns the x coordinate of the cherry.
    private double getCherryRandX(){
        double minX = pillar1.getLayoutX()+pillar1.getWidth();
        double maxX = pillar2.getLayoutX();

        Random randomXcalc = new Random();
        return minX + (maxX-minX)* randomXcalc.nextDouble();
    }

    public void setCherry(){
        Random rand1 = new Random();
        boolean cherryhere = rand1.nextBoolean();
        if(cherryhere== false){
            System.out.println("No cherry in this iteration. ");
        }
        else{
            System.out.println("Cherry placed in this iteration.");
            double cherryX = getCherryRandX();
            score_cherry.setLayoutX(cherryX);
            if(score_cherry.isVisible()==false){
                score_cherry.setVisible(true);
            }
        }
        return;
    }


    public void showHighScore(MouseEvent event){
        main_high.setText(String.valueOf(highScore));

    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reviveHero(ActionEvent event){
        int cherry = Integer.parseInt(cherry_count.getText());
        if(cherry>2){
            cherry=cherry-2;
            //how should I revive the game?
        }
        cherry_count.setText(String.valueOf(cherry));
    }
}
