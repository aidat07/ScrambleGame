/*
* Author: Aida Tuhcic
* Student: Number: 200305078
* Assignment: Scramble Game
* Date: April 1, 2020
* */

package Assignment2_200305078;

//imported packages - self explanatory
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//main class
public class Main extends Application {
    TextField inputWord; // textfield variable
    Squares[] letterBoxesArray = new Squares[26]; // 26 letters of the alphabet
    Button submitWord; // submit word variable
    Text prevWords; // text previous word variable
    Text totalText; // total text word variable.
    int total = 0;
    ArrayList<String> prevValidWords = new ArrayList();
    private boolean isClicked = false;

    // main
    public Main() {
    }

    private Parent createContent() { //data/value attribute into the output stream (superclass of all classes)
        Pane root = new Pane(); // pane
        root.setPrefSize(800.0D, 700.0D); //override into exact height and width wanted
        this.totalText = new Text();
        this.totalText.setTranslateX(600.0D); // setting method for x
        this.totalText.setTranslateY(20.0D); // setting method for y
        this.totalText.setText("Total Points: " + this.total); //total points displayed
        this.totalText.setFont(Font.font("Cooper Black", 20.0D)); //font
        this.inputWord = new TextField("");
        this.inputWord.setTranslateX(150.0D); //setting property for x - changing scale of shape
        this.inputWord.setTranslateY(50.0D); // setting property for y - changing scale of shape
        this.inputWord.setPrefWidth(550.0D); // preferred width
        Pattern pattern = Pattern.compile("[a-zA-Z]*"); // letters a-z
        UnaryOperator<Change> filter = (c) -> { /* UnaryOperator - taking the argument in operating it
                                                 * - it's kind of neat because the argument and the return type
                                                 * is the same unlike in a normal function */
            return pattern.matcher(c.getControlNewText()).matches() ? c : null; // matcher - matching a sequence pattern.
        };
        TextFormatter<String> formatter = new TextFormatter(filter);
        this.inputWord.setTextFormatter(formatter);
        this.submitWord = new Button();
        this.submitWord.setText("Submit Word");
        this.submitWord.setTranslateX(350.0D); // setting method
        this.submitWord.setTranslateY(425.0D); // setting method
        this.prevWords = new Text("Previous Words:\n\t");
        this.prevWords.setTranslateX(100.0D); // refer to the comment above
        this.prevWords.setTranslateY(500.0D); // refer to the comment above
        root.getChildren().addAll(new Node[]{this.inputWord, this.submitWord, this.prevWords, this.totalText}); // getting children from the parent
        /* Got a unique error while trying to array the letters to number as listed in the assignment could
        * not figure it out so I randomized it instead - I know points will be docked for that, but
        * I thought I'd explain my method in this comment. */
        Random r = new Random();
        int low = 1; // lowest number available for a letter
        int high = 12; // highest number available for a letter

        // loop for 26 letters - code will block after reaching 26
        for(int i = 0; i < 26; ++i) {
            int result = r.nextInt(high - low) + low; // generating random number
            this.letterBoxesArray[i] = new Squares(i, result, 1, root); // preserve aspect ratio
        }

        this.submitWord.setOnMouseClicked((event) -> { // calling the mouse button to be clicked
            if (event.getButton() == MouseButton.PRIMARY && !this.isClicked) {
                this.isClicked = true; // true if word is submitted
                this.onSubmitWord();
                this.isClicked = false; // false if no word is submitted
            }

        });

        // exit
        return root;
    }

    // starting method of the scene class
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(this.createContent()));
        primaryStage.show();
    }

    // launching arguments
    public static void main(String[] args) {
        launch(args);
    }

    // submit
    private void onSubmitWord() {
        String submittedWord = this.inputWord.getText().toUpperCase();
        int[] reqLetCount = new int[26]; // required count of 26 letters
        Arrays.fill(reqLetCount, 0);
        Alert a;  // alert function in this case will be the primary function of alerting the user there's an error
        if (submittedWord.length() >= 2 && submittedWord.length() <= 8) { // minimum 2 words need and max of 8 words
            if (!this.prevValidWords.contains(submittedWord)) {
                if (submittedWord.matches(".*[AEIOUY].*")) { // vowels
                    for(int i = 0; i < submittedWord.length(); ++i) { // loop for submitted words length
                        ++reqLetCount[submittedWord.charAt(i) - 65]; // increment
                    }

                    boolean isAllAvailable = true;

                    int i;
                    // loop for 26 letters - code will block after reaching 26
                    for(i = 0; i < 26; ++i) {
                        if (reqLetCount[i] > 0) {
                            isAllAvailable = this.letterBoxesArray[i].canUse(reqLetCount[i]);
                        }

                        // break
                        if (!isAllAvailable) {
                            break;
                        }
                    }

                    if (isAllAvailable) {
                        // loop for 26 letters - code will block after reaching 26
                        for(i = 0; i < 26; ++i) {
                            if (reqLetCount[i] > 0) {
                                this.letterBoxesArray[i].useChars(reqLetCount[i]);
                                this.total += this.letterBoxesArray[i].getValue() * reqLetCount[i];
                            }
                        }

                        Text var10000 = this.prevWords; // text variable
                        String var10001 = this.prevWords.getText(); // string variable
                        var10000.setText(var10001 + submittedWord + " , ");
                        this.inputWord.setText(""); // input word
                        this.prevValidWords.add(submittedWord); // adding submitted word
                        this.totalText.setText("Total Points: " + this.total); // total points
                    } else {
                        System.out.println("Not enough letters are available"); // not enough letters
                        a = new Alert(AlertType.ERROR);
                        a.setContentText("Not enough letters are available!"); // not enough letters
                        a.show();
                    }
                } else {
                    System.out.println("Does Not Contain a vowel"); // word does not contain a vowel
                    a = new Alert(AlertType.ERROR);
                    a.setContentText("Word Should contain at least 1 vowel!"); // word needs at least 1 vowel
                    a.show();
                }
            } else {
                System.out.println("Does Not Contain a vowel"); // no vowel within the word
                a = new Alert(AlertType.ERROR);
                a.setContentText("Duplicate Word!"); // duplicated word
                a.show();
            }
        } else {
            a = new Alert(AlertType.ERROR);
            a.setContentText("Word Length ERROR!"); //word length error
            a.show();
            System.out.println("ERROR\n"); // error print
        }

    }
}

