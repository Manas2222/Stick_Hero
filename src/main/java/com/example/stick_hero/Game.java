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
import javafx.scene.transform.Translate;
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

    private Timeline timeline_stickGrow;
    private Timeline timeline_herosuccessful;
    private Timeline timeline_herofailure;
    private Timeline next_iter_prep;
    private Timeline stick_fall_timeline;
    private Timeline hero_fall_timeline;

    private boolean flagOnMouseReleaseMethod=true;
    private boolean flagOnMousePushMethod=true;
    private boolean isInitialFlag;
    private boolean cherry_collected = false;

    private Rectangle pillar1;
    private Rectangle pillar2;
    private Stick activeStick;
    private ImageView hero1;
    private Rectangle rectangle_stick;

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

        //adding hero to the game
        hero1 = Hero.getInstance();
        anchor_pane_game.getChildren().add(hero1);

        //adding the stick to the game
        rectangle_stick = Stick.makeStick();
        anchor_pane_game.getChildren().add(rectangle_stick);
        rectangle_stick.setWidth(5);
        rectangle_stick.setHeight(0);



        timeline_stickGrow = new Timeline(new KeyFrame(Duration.millis(5), e->{

            rectangle_stick.setY(rectangle_stick.getY()-1);
            rectangle_stick.setHeight(rectangle_stick.getHeight()+1);

        }));
        timeline_stickGrow.setCycleCount(Animation.INDEFINITE);

        Rotate stick_fall = new Rotate(0, rectangle_stick.getX()+rectangle_stick.getWidth()/2, rectangle_stick.getHeight()+rectangle_stick.getY());
        rectangle_stick.getTransforms().add(stick_fall);
        stick_fall_timeline = new Timeline(new KeyFrame(Duration.millis(10),e->{
            while (true) {
//                if(stick_fall.getAngle()>90){
//                    stick_fall_timeline.stop();
//                    break;
//                }
//                else{
//                    stick_fall.setAngle(stick_fall.getAngle()+1);
//                }
                if(stick_fall.getAngle()<90){
                    stick_fall.setAngle(stick_fall.getAngle()+1);
                }
                else{
                    stick_fall_timeline.stop();
                    heroMove_success();
                }
            }
        }));


        stick_fall_timeline.setOnFinished(e->{
            boolean heroReaches = this.isInPillar(pillar1,pillar2,rectangle_stick);
            heroMove_success();
//            if(heroReaches == true){
//                heroMove_success();
//            }
//            else{
//                heroMove_fail();
//            }
        });


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


    }
    private void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(x,-y));
        node.setTranslateX(x);
        node.setTranslateY(y);
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

//            movePivot(rectangle_stick,rectangle_stick.getHeight()/2, 0);

            stickFallTransition.play();
            //stick_fall_timeline.play();

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

    //make sure to remove the commented code
    public void setCherry(){
        Random rand1 = new Random();
        boolean cherryhere = rand1.nextBoolean();
        System.out.println("Cherry placed in this iteration.");
            double cherryX = getCherryRandX();
            score_cherry.setLayoutX(cherryX);
            if(score_cherry.isVisible()==false){
                score_cherry.setVisible(true);
            }
//        if(cherryhere== false){
//            System.out.println("No cherry in this iteration. ");
//        }
//        else{
//            System.out.println("Cherry placed in this iteration.");
//            double cherryX = getCherryRandX();
//            score_cherry.setLayoutX(cherryX);
//            if(score_cherry.isVisible()==false){
//                score_cherry.setVisible(true);
//            }
//        }
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
