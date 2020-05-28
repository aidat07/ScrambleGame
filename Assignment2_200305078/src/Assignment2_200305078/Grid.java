package Assignment2_200305078;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Grid extends StackPane {

    // instance variables
    private Text text = new Text(); // text
    private boolean isClicked = false; // isClicked

    public Grid() {
        Rectangle border = new Rectangle(50.0D, 50.0D); // double type equal size for squares
        border.setFill(Color.PALEVIOLETRED); // pale violet red fill for the squares
        border.setStroke(Color.BLACK); // black outline for the squares
        this.text.setFont(Font.font(50.0D)); // font set to 50
        this.setAlignment(Pos.CENTER); // position all in center
        this.getChildren().addAll(new Node[]{border, this.text}); // get child from the parent
        this.setOnMouseClicked((event) -> { // calling the mouse button to be clicked
            if (event.getButton() == MouseButton.PRIMARY && !this.isClicked) {
                this.isClicked = true;
                System.out.println("Clicked"); // is clicked - successful
            }

        });
    }

    // setText defines the text displayed
    public void setText(Character text) {
        this.text.setText(text+"");
    }
}

