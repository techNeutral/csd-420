//Brett Fuller
//CSD-420 Assignment 7.2
//4/27/25

//the purpose of this assignment is to utilize an external CSS file to assist in the styling of a JavaFX program

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FullerAssignment_7_2 extends Application {
    public void start(Stage primaryStage){
        //Create the basic elements for the assignment
        HBox hbox = new HBox(5);
        Scene scene = new Scene(hbox, 400, 250);
        //link to CSS file
        scene.getStylesheets().add("FullerStyles.css");
        //utilize 3 panes on ehtat is blank for clear space, one for a border that surrounds a single circle, and a third
        //for the last three circles
        Pane blankPane = new Pane();
        blankPane.setPrefSize(20, 300);
        Pane borderedPane = new Pane();
        borderedPane.setPrefSize(70, 300);
        //link the bordered pane to the border class in the css file
        borderedPane.getStyleClass().add("border");
        Pane clearPane = new Pane();
        Circle firstCircle = new Circle(35, 125, 30);
        //link the first and second circles to the empty circle and circle border classes in the css file
        firstCircle.getStyleClass().addAll("emptyCircle", "circleborder");
        Circle secondCircle = new Circle(35, 125, 30);
        secondCircle.getStyleClass().addAll("emptyCircle", "circleborder");
        Circle thirdCircle = new Circle(105, 125, 30);
        //link the third circle to the redcircle id in the css file
        thirdCircle.setId("redcircle");
        Circle fourthCircle = new Circle(175, 125, 30);
        //link the fourthcircle to the greencircle class in the css file
        fourthCircle.setId("greencircle");
        //tie all the elements together to render in the primary stage.
        borderedPane.getChildren().addAll(firstCircle);
        clearPane.getChildren().addAll(secondCircle, thirdCircle, fourthCircle);
        hbox.getChildren().addAll(blankPane, borderedPane, clearPane);
        primaryStage.setTitle("Fuller Assignment 7.2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}