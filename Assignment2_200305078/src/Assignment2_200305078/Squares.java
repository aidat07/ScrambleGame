package Assignment2_200305078;

//imported packages - self explanatory
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Squares {

    // instance variables
    int value; // value
    int gridNum; // grid number
    Grid grid; // grid
    int remainCount; // remaining letters left
    Text letterCount; // letter count
    boolean isAvailable; // availability

    // constructor
    public Squares(int gridNum, int letterCount,int value, Pane root) {
        this.gridNum = gridNum; // grid number
        this.value=value; // value
        this.grid = new Grid(); // grid
        this.letterCount = new Text();
        int x=100 + (gridNum%7)*100; // calculation through percentage
        int y=100 + (gridNum/7)*75; // calculation through percentage
        this.grid.setText((char)(gridNum+65));
        this.grid.setTranslateX(x); // setting method
        this.grid.setTranslateY(y); // setting method
        this.remainCount = letterCount; // letter number
        this.letterCount.setText(letterCount+"");
        this.letterCount.setTranslateX(x+25); // adding operators for x
        this.letterCount.setTranslateY(y+70); // adding operators for y

        // again - getting the list of the children from the parent
        root.getChildren().addAll(this.grid,this.letterCount);
    }

    // get
    public Text getLetterCount() {
        return letterCount;
    }

    // set
    public void setLetterCount(int letterCount) {
        this.letterCount.setText(letterCount+"");
    }

    // get
    public boolean isAvailable() {
        return isAvailable;
    }

    // set
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // get (technically) - if statement
    public boolean canUse(int count){
        if(this.remainCount>=count){
            return true;
        }
        return false;
    }

    // set
    public void useChars(int count){
        this.remainCount-=count;
        this.letterCount.setText(remainCount+"");
    }

    // get
    public int getValue() {
        return value;
    }
}


