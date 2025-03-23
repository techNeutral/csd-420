//Brett Fuller
//CSD-420 Assignment 1.3
//3/23/25
/*
 *
 Utilized some code from Professor Darrell Payne's Examples 3 and 12 as a beginning framework then modified that heavily.
 *
 */
//This code displays four random cards and refreshes the cards with four new random cards when the refresh
//button is clicked.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FullerAssignment_1_3 extends Application {

    //method that gets four random images from a directory.
    public Image[] randomizeImageCards(){
        Random rand = new Random();
        //Get random numeber between 1 and 52 and ensure that it is unique between the 4 cards (may not be necessary
        // but is how an actual deck of cards would work).
        int c1 = rand.nextInt(51) + 1;

        int c2 = c1;
        while (c2 == c1){
            c2 = rand.nextInt(51) + 1;
        }

        int c3 = c1;
        while (c3 == c1 || c3 == c2){
            c3 = rand.nextInt(51) + 1;
        }

        int c4 = c1;
        while (c4 == c1 || c4 == c2 || c4 == c3){
            c4 = rand.nextInt(51) + 1;
        }

        //Convert random number into path to the image
        String cardPath1 = "cards/" + c1 + ".png";
        String cardPath2 = "cards/" + c2 + ".png";
        String cardPath3 = "cards/" + c3 + ".png";
        String cardPath4 = "cards/" + c4 + ".png";

        //Create images from image paths
        Image i1 = new Image(cardPath1);
        Image i2 = new Image(cardPath2);
        Image i3 = new Image(cardPath3);
        Image i4 = new Image(cardPath4);
        //Add images to an array
        Image[] images = {i1, i2, i3 ,i4};
        return images;
    }

    //Consulted https://www.w3schools.com/java/ref_arraylist_foreach.asp for help with the lambda expression.
    //method that updates the imageViews with new cards
    public void updateImageViews(Image[] images, ArrayList<ImageView> imageViews){

        ArrayList<Integer> lambdaIndexes = new ArrayList<Integer>();
        lambdaIndexes.add(0);
        lambdaIndexes.add(1);
        lambdaIndexes.add(2);
        lambdaIndexes.add(3);
        //lambda expression to update images.
        lambdaIndexes.forEach( (n) -> {imageViews.get(n).setImage(images[n]);});
    }


    @Override
    public void start(Stage primaryStage) {

        // Create VBox Pane to hold Images

        VBox pane = new VBox(25);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setAlignment(Pos.CENTER);

        //get images from randomizeImageCards method
        Image[] images = randomizeImageCards();
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

        //Create new ImageViews
        ImageView imageView1 = new ImageView(images[0]);
        ImageView imageView2 = new ImageView(images[1]);
        ImageView imageView3 = new ImageView(images[2]);
        ImageView imageView4 = new ImageView(images[3]);

        //Add ImageViews to the ArrayList
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);

        //lambda expression to add Imageviews to the pane
        imageViews.forEach( (n) -> {pane.getChildren().add(n);});

        //Create a button and add it to the pane
        Button button = new Button("Refresh");
        pane.getChildren().add(button);

        //Generate new cards on button click
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Image[] randImages = randomizeImageCards();
                updateImageViews(randImages, imageViews);
            }
        });


        // Create Scene, place in Stage
        Scene scene = new Scene(pane, 325, 600);
        // Set Stage title
        primaryStage.setTitle("Fuller Asignment 1.3 - Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
