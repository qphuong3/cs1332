//In order to help learn course concepts, I worked on the homework with, discussed homework topics
//and issues with, and/or consulted related material that can be found at
//https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
//https://www.geeksforgeeks.org/javafx-alert-with-examples/
//https://www.geeksforgeeks.org/javafx-slider-class/
//https://www.javaer101.com/en/article/451645.html
//https://stackoverflow.com/questions/32866937/how-to-check-if-textfield-is-empty
//http://tutorials.jenkov.com/javafx/media.html
//https://beginnersbook.com/2013/12/how-to-sort-arraylist-in-java/
//https://stackoverflow.com/questions/27711923/javafx-sort-listview
//https://dzone.com/articles/javafx-collections-observablelist-and-observablema
//https://www.tutorialspoint.com/javafx/javafx_images.htm
//https://www.geeksforgeeks.org/javafx-textinputdialog/
//https://www.tutorialspoint.com/javafx/javafx_text.htm
//https://stackoverflow.com/questions/17412933/always-show-vertical-scrollbar-for-javafx-listview
//https://stackoverflow.com/questions/35864241/how-to-set-minimum-size-of-container-in-fxml
import javafx.application.Application;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.FileInputStream;
import java.io.File;
/**
 * This class represents the StarterUpper object.
 * @author Que Le
 * @version 1.0
**/
public class StarterUpper extends Application {
    /**
     * creates the stage
     * @param s stage
     * @throws FileNotFoundException throws exception when file is not found
     */
    public void start(Stage s) throws FileNotFoundException {
        s.setTitle("Problem Ideation Form");
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
            + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #0a4861;");
        HBox hb = new HBox(5);
        hb.setPadding(new Insets(10));
        Image image = new Image(new FileInputStream("//Users/quele/Downloads/Idea_Exchange.png")); //write file path
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        DropShadow ds = new DropShadow();
        Text t = new Text();
        setDropShadow(ds, t, 40);
        t.setText("Problem Ideation Form");
        hb.getChildren().addAll(imageView, t);
        HBox root = new HBox(5);
        root.setPadding(new Insets(10));
        VBox vBox1 = new VBox(10);
        vBox1.setPadding(new Insets(40));
        vBox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
            + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #2295b2;");
        final TextField problem = new TextField();
        setTextField(problem, 40, "What is the problem?");
        final TextField customer = new TextField();
        setTextField(customer, 30, "Who is the target customer?");
        Slider slider = new Slider(1, 10, 5);
        Label l = new Label(" ");
        final Label need = new Label("How badly does the customer NEED this problem fixed (1-10)?");
        setSlider(slider, l);
        final TextField people = new TextField();
        intOnly(people);
        setTextField(people, 40, "How many people do you know might experience this problem?");
        final TextField market = new TextField();
        intOnly(market);
        setTextField(market, 25, "How big is the target market?");
        final TextField solutions = new TextField();
        setTextField(solutions, 30, "Who are the competitors/existing solutions?");
        final TextField seed = new TextField();
        intOnly(seed);
        setTextField(seed, 20, "How much money does this project need?");
        final TextField usp = new TextField();
        setTextField(usp, 30, "What is your unique selling point?");
        final TextField sell = new TextField();
        setTextField(sell, 40, "How will you market your product?");
        final TextField cost = new TextField();
        setTextField(cost, 40, "How will you price your product?");
        HBox hbox = new HBox(10);
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        Button addButton = new Button("Add Idea");
        addButton.setEffect(ds);
        ArrayList<StartUpIdea> ideas = new ArrayList<StartUpIdea>();
        ObservableList<StartUpIdea> observableList = FXCollections.observableList(ideas);
        ArrayList<StartUpIdea> ideas2 = new ArrayList<StartUpIdea>();
        ObservableList<StartUpIdea> observableList2 = FXCollections.observableList(ideas2);
        Label printProblem = new Label();
        EventHandler<ActionEvent> handler = new EventHandler<>() {
            public void handle(ActionEvent event) {
                if (checkEmpty(problem, customer, people, market) || checkEmpty2(solutions, seed, usp, sell, cost)) {
                    String message = "";
                    message += printEmpty(problem, customer, people, market);
                    message += printEmpty2(solutions, seed, usp, sell, cost);
                    a.setContentText(message);
                    a.showAndWait();
                } else {
                    ideas.add(createArray1(problem, customer, slider, people, market, seed, solutions));
                    ideas2.add(createArray2(usp, sell, cost, slider, people, market));
                    setList(ideas, printProblem);
                    clearField(problem, customer, people, market);
                    clearField2(solutions, seed, usp, sell, cost);
                }
            }
        };
        addButton.setOnAction(handler);
        File f = new File("StartUpIdeas.txt");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Button delete = new Button("Delete");
        delete.setEffect(ds);
        delete.setOnAction(event -> {
                alert.setContentText("Are you sure you want to delete the file?");
                alert.setTitle("Delete file");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (FileUtil.saveIdeasToFile(ideas, ideas2, f)) {
                        f.delete();
                    }
                    clearReset(printProblem, ideas, ideas2);
                    clearField(problem, customer, people, market);
                    clearField2(solutions, seed, usp, sell, cost);
                    try {
                        playSound("/Users/quele/Downloads/shred.wav");
                    } catch (FileNotFoundException e) {
                        e.getStackTrace();
                    }
                }
            });
        Button reset = new Button("Reset");
        reset.setEffect(ds);
        reset.setOnAction(event -> {
                alert.setContentText("Are you sure you want to reset list?");
                alert.setTitle("Reset List");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    clearReset(printProblem, ideas, ideas2);
                    clearField(problem, customer, people, market);
                    clearField2(solutions, seed, usp, sell, cost);
                }
            });
        Button sort = new Button("Sort List");
        sort.setEffect(ds);
        sort.setOnAction(event -> {
                Collections.sort(ideas);
                Collections.sort(ideas2);
                setList(ideas, printProblem);
            });
        Button saves = new Button("Save List");
        saves.setEffect(ds);
        saves.setOnAction(event -> FileUtil.saveIdeasToFile(ideas, ideas2, f));
        Button remove = new Button("Remove Idea");
        remove.setEffect(ds);
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter the index of the idea you wish to remove");
        remove.setOnAction(event -> removeAction(a, td, ideas, ideas2, printProblem));
        Button info = new Button("Info");
        info.setEffect(ds);
        TextInputDialog ti = new TextInputDialog();
        ti.setHeaderText("Enter the index of the idea you wish to get info about");
        info.setOnAction(event -> infoAction(a, ti, ideas, ideas2));
        hbox.getChildren().addAll(addButton, remove, info, sort, saves, reset, delete);
        vBox1.getChildren().addAll(problem, customer, need, slider, l, people,
                                market, seed, solutions, usp, sell, cost, hbox);
        VBox vBox2 = new VBox(5);
        final Label idea = new Label("Problem List:");
        idea.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        idea.setUnderline(true);
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setMinViewportHeight(100);
        scrollpane.setMinViewportWidth(300);
        scrollpane.setContent(vBox2);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        vBox2.getChildren().addAll(idea, printProblem);
        root.getChildren().addAll(vBox1, scrollpane);
        vBox.getChildren().addAll(hb, root);
        Scene sc = new Scene(vBox, 950, 650);
        s.setScene(sc);
        s.show();
    }
    /**
     * creates a StartUpIdea
     * @param problem description
     * @param customer target customer
     * @param slider 1-10 rating of need
     * @param people people you know with the problem
     * @param market number of potential customer
     * @param seed amount of money needed
     * @param solutions current competitors/solutions
     * @return StartUpIdea
     */
    public StartUpIdea createArray1(TextField problem, TextField customer, Slider slider, TextField people,
                            TextField market, TextField seed, TextField solutions) {
        String p = problem.getCharacters().toString();
        String c = customer.getCharacters().toString();
        String sol = solutions.getCharacters().toString();
        int m = Integer.parseInt(market.getCharacters().toString());
        int peo = Integer.parseInt(people.getCharacters().toString());
        int see = Integer.parseInt(seed.getCharacters().toString());
        return new StartUpIdea(p, c, (int) slider.getValue(), peo, m, see, sol);
    }
    /**
     * creates StartUpIdea2
     * @param usp unique selling point
     * @param sell how to sell
     * @param cost how to price
     * @param slider 1-10 rating of need
     * @param people people you know with the problem
     * @param market number of potential customer
     * @return StartUpIdea
     */
    public StartUpIdea createArray2(TextField usp, TextField sell, TextField cost,
                                Slider slider, TextField people, TextField market) {
        String u = usp.getCharacters().toString();
        String se = sell.getCharacters().toString();
        String co = cost.getCharacters().toString();
        int m = Integer.parseInt(market.getCharacters().toString());
        int peo = Integer.parseInt(people.getCharacters().toString());
        return new StartUpIdea(u, se, co, (int) slider.getValue(), peo, m);
    }
    /**
     * prints the message if any textfield is empty
     * @param problem description
     * @param customer target customer
     * @param people people you know with the problem
     * @param market number of potential customer
     * @return message
     */
    public String printEmpty(TextField problem, TextField customer, TextField people, TextField market) {
        String message = "";
        if (problem.getText().trim().isEmpty() || customer.getText().trim().isEmpty()
            || people.getText().trim().isEmpty() || market.getText().trim().isEmpty()) {
            if (problem.getText().trim().isEmpty()) {
                message += "Please enter a problem\n";
            }
            if (customer.getText().trim().isEmpty()) {
                message += "Please enter the target customer\n";
            }
            if (people.getText().trim().isEmpty()) {
                message += "Please enter the known people with this problem\n";
            }
            if (market.getText().trim().isEmpty()) {
                message += "Please enter the market size\n";
            }
        }
        return message;
    }
    /**
     * prints the message if any textfield is empty
     * @param solutions current competitors/solutions
     * @param seed amount of money needed
     * @param usp unique selling point
     * @param sell how to sell product
     * @param cost how to price product
     * @return message
     */
    public String printEmpty2(TextField solutions, TextField seed, TextField usp, TextField sell, TextField cost) {
        String message = "";
        if (solutions.getText().trim().isEmpty() || seed.getText().trim().isEmpty() || usp.getText().trim().isEmpty()
            || sell.getText().trim().isEmpty() || cost.getText().trim().isEmpty()) {
            if (solutions.getText().trim().isEmpty()) {
                message += "Please enter the competitors\n";
            }
            if (seed.getText().trim().isEmpty()) {
                message += "Please enter the amount of money needed\n";
            }
            if (usp.getText().trim().isEmpty()) {
                message += "Please enter your unique selling point\n";
            }
            if (sell.getText().trim().isEmpty()) {
                message += "Please enter how to market your product\n";
            }
            if (cost.getText().trim().isEmpty()) {
                message += "Please enter how to price your product";
            }
        }
        return message;
    }
    /**
     * check if any textfield is empty
     * @param problem description
     * @param customer target customer
     * @param people people you know with the problem
     * @param market number of potential customer
     * @return if any textfield is empty
     */
    public boolean checkEmpty(TextField problem, TextField customer, TextField people, TextField market) {
        if (problem.getText().trim().isEmpty() || customer.getText().trim().isEmpty()
            || people.getText().trim().isEmpty() || market.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * check if any textfield is empty
     * @param solutions current competitors/solutions
     * @param seed amount of money needed
     * @param usp unique selling point
     * @param sell how to sell product
     * @param cost how to price product
     * @return if any textfield is empty
     */
    public boolean checkEmpty2(TextField solutions, TextField seed, TextField usp, TextField sell, TextField cost) {
        if (solutions.getText().trim().isEmpty() || seed.getText().trim().isEmpty() || usp.getText().trim().isEmpty()
            || sell.getText().trim().isEmpty() || cost.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * clears textfield
     * @param problem description
     * @param customer target customer
     * @param people people you know with the problem
     * @param market number of potential customer
     */
    public void clearField(TextField problem, TextField customer, TextField people, TextField market) {
        problem.clear();
        customer.clear();
        people.clear();
        market.clear();
    }
    /**
     * clears textfield
     * @param solutions current competitors/solutions
     * @param seed amount of money needed
     * @param usp unique selling point
     * @param sell how to sell product
     * @param cost how to price product
     */
    public void clearField2(TextField solutions, TextField seed, TextField usp, TextField sell, TextField cost) {
        solutions.clear();
        seed.clear();
        usp.clear();
        sell.clear();
        cost.clear();
    }
    /**
     * prints the problem list
     * @param ideas arraylist with problem
     * @param printProblem label that has all problem descriptions printed
     */
    public void setList(ArrayList<StartUpIdea> ideas, Label printProblem) {
        String temp = "";
        for (int i = 0; i < ideas.size(); i++) {
            temp += (i + 1) + ": " + ideas.get(i).getProblem() + "\n\n";
        }
        printProblem.setText(temp);
    }
    /**
     * the remove button action
     * @param a alert
     * @param td text to get index remove
     * @param ideas idea list
     * @param ideas2 continuation of idea
     * @param printProblem label that has all problem descriptions printed
     */
    public void removeAction(Alert a, TextInputDialog td, ArrayList<StartUpIdea> ideas,
                            ArrayList<StartUpIdea> ideas2, Label printProblem) {
        td.showAndWait();
        String temp = td.getEditor().getText();
        try {
            int index = Integer.parseInt(temp);
            if (index - 1 < ideas.size() && index - 1 >= 0) {
                ideas.remove(index - 1);
                ideas2.remove(index - 1);
                try {
                    playSound("/Users/quele/Downloads/shred.wav");
                } catch (FileNotFoundException e) {
                    e.getStackTrace();
                }
            } else {
                a.setHeaderText("Invald index");
                a.setContentText("Please enter a valid index");
                a.showAndWait();
            }
            setList(ideas, printProblem);
        } catch (NumberFormatException e) {
            a.setHeaderText("Invald index");
            a.setContentText("Please enter a valid index");
            a.showAndWait();
        } finally {
            td.getEditor().clear();
        }
    }
    /**
     * the info button action
     * @param a alert
     * @param td text to get index
     * @param ideas idea list
     * @param ideas2 continuation of idea list
     */
    public void infoAction(Alert a, TextInputDialog td, ArrayList<StartUpIdea> ideas,
                            ArrayList<StartUpIdea> ideas2) {
        td.showAndWait();
        String temp = td.getEditor().getText();
        try {
            int index = Integer.parseInt(temp);
            if (index - 1 < ideas.size() && index - 1 >= 0) {
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                String idea = ideas.get(index - 1).toString() + ideas2.get(index - 1).toString2();
                b.setContentText(idea);
                b.setTitle(ideas.get(index - 1).getProblem());
                b.setHeaderText("Info");
                b.showAndWait();
            } else {
                a.setHeaderText("Invald index");
                a.setContentText("Please enter a valid index");
                a.showAndWait();
            }
        } catch (NumberFormatException e) {
            a.setHeaderText("Invald index");
            a.setContentText("Please enter a valid index");
            a.showAndWait();
        } finally {
            td.getEditor().clear();
        }
    }
    /**
     * sets up the slider
     * @param slider the need slider
     * @param l the need value
     */
    public void setSlider(Slider slider, Label l) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener((obs, oldval, newVal) -> slider.setValue(newVal.intValue()));
        slider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    l.setText("Value: " + newValue);
                }
            });
    }
    /**
     * set the textfield to have these properites
     * @param temp textfield
     * @param len length of text want to see
     * @param prompt sets the prompt
     */
    public void setTextField(TextField temp, int len, String prompt) {
        temp.setPrefColumnCount(len);
        temp.setStyle("-fx-text-inner-color: #627b85;");
        temp.setPromptText(prompt);
    }
    /**
     * creates drop shadow for text
     * @param ds creates drop shadow
     * @param t text
     * @param size size of the font
     */
    public void setDropShadow(DropShadow ds, Text t, int size) {
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFont(Font.font(null, FontWeight.BOLD, size));
        t.setFill(Color.STEELBLUE);
    }
    /**
     * makes the textfield only accepts numbers
     * @param temp a textfield
     */
    public void intOnly(TextField temp) {
        temp.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    temp.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    /**
     * create sound effect
     * @param string get the file
     * @throws FileNotFoundException if file is not found
     */
    public void playSound(String string) throws FileNotFoundException {
        File mediaFile = new File(string);
        try {
            Media media = new Media(mediaFile.toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayer.play();
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
    }
    /**
     * clears the list
     * @param printProblem list of problems
     * @param ideas startUpIdea
     * @param ideas2 cont. of startUpIdea
     */
    public void clearReset(Label printProblem, ArrayList<StartUpIdea> ideas, ArrayList<StartUpIdea> ideas2) {
        printProblem.setText("");
        ideas.clear();
        ideas2.clear();
    }
}