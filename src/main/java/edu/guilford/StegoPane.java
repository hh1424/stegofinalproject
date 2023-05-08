package edu.guilford;

import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;

//This class will be the pane that holds the method for the user
//to input the message they want to encrypt and the image they want
//to encrypt it in
public class StegoPane extends Pane {
    
    //add an beginning label to the pane
    private Label beginningLabel;

    //add a text field for the user to input the message they want to encrypt
    private TextArea messageText;
    private Label messageLabel;
    private String message;
    //add a button for the user to click to send the message to the encrypt class
    private Button encrypt;
    private Label encryptLabel;
    
    //add a button for the user to click to select the image they want to encrypt
    private Button newImage;
    //add a button for the user to click to select a previously used image
    private Button prevImage;
    private TextArea imageText;
    //add an imageview to show the image chosen
    private ImageView imageView;
    
    //add a button for the user to click to save the encrypted image
    private Button send;
    private Label sendLabel;
    private Rectangle2D screenBounds;
    
    //Constructor for the StegoPane class
    public StegoPane() {

        screenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println("Width: " + screenBounds.getWidth() + "Height: " + screenBounds.getHeight());

        //Instantiate the textArea
        messageText = new TextArea();
        //change the size of the textArea
        messageText.setPrefSize(500, 250);
        //change the font of the textArea
        messageText.setStyle("-fx-font-size: 18px; -fx-font-type: Arial");
        //wrap the text in the textArea
        messageText.setWrapText(true);
        //Scroll the text in the textArea
        messageText.setScrollLeft(100);
        //Scan the messageText and save it in message
        // message = messageText.getText();

        imageText = new TextArea("Choose between either a new image or a previously used image to encrypt the message in");
        //Change the size and font of the imageText
        imageText.setStyle("-fx-font-size: 18px; -fx-font-type: Arial");
        //Change the length of the imageText
        imageText.setPrefSize(600, 100);
        imageText.setEditable(false);
        imageText.setWrapText(true);
        // imageText.setScrollLeft(0);

        //Instantiate the labels
        beginningLabel = new Label("Welcome to the Steganography App");
        //Change the size and font of the label
        beginningLabel.setStyle("-fx-font-size: 22px; -fx-font-type: Arial");
        messageLabel = new Label("Enter the message you want to encrypt");
        //Change the size and font of the label
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-type: Arial");
        encryptLabel = new Label("Click to encrypt the message");
        //Change the size and font of the label
        encryptLabel.setStyle("-fx-font-size: 18px; -fx-font-type: Arial");
        sendLabel = new Label("Click to send the encrypted message into the image");
        //Change the size and font of the label
        sendLabel.setStyle("-fx-font-size: 18px; -fx-font-type: Arial");

        //Instantiate the buttons
        encrypt = new Button("Encrypt");
        //Change the font size of the button
        encrypt.setStyle("-fx-font-size: 15px; -fx-font-type: Arial");
        newImage = new Button("New Image");
        prevImage = new Button("Previous Image");
        send = new Button("Send");
        //Change the font size of the button
        send.setStyle("-fx-font-size: 15px; -fx-font-type: Arial");

        //Set the location of the beginningLabel to the top center of the pane
        beginningLabel.relocate((screenBounds.getWidth() / 2.8), 0);

        //Set the location of the messageLabel and message
        messageLabel.relocate(0, 50);
        messageText.relocate(0, 100);

        //Set the location of the newImage and prevImage buttons and the imageText
        // imageText.relocate((screenBounds.getWidth() / 2), 50);
        // newImage.relocate((screenBounds.getWidth() / 3), 100);
        // prevImage.relocate((screenBounds.getWidth() / 2.5), 150);

        //Set the location of the encryptLabel and encrypt button
        // encryptLabel.relocate(0, (screenBounds.getHeight() / 1.8));
        // encrypt.relocate(0, (screenBounds.getHeight() / 1.6));

        //Set the location of the sendLabel and send button
        sendLabel.relocate(0, (screenBounds.getHeight() / 1.4));
        // this.add(send, 2, 4);

        //Add an event listener for the prevImage button to choose a previously used image
        // prevImage.setOnAction(e -> {
        //     //Instantiate a FileChooser object
        //     FileChooser fileChooser = new FileChooser();
        //     //Set the title of the fileChooser
        //     fileChooser.setTitle("Open Image File");
        //     //Set the initial directory of the fileChooser
        //     fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //     //Set the extension filter of the fileChooser
        //     fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //     //Instantiate a File object
        //     File selectedFile = fileChooser.showOpenDialog(null);
        //     //Instantiate an Image object
        //     Image image = new Image(selectedFile.toURI().toString());
        //     //Add the image to the imageView
        //     imageView = new ImageView(image);
        //     //Set the size of the ImageView
        //     imageView.setFitHeight(300);
        //     imageView.setFitWidth(300);
        //     //Set the location of the ImageView
        //     imageView.relocate(0, 0);
        // });

        //Add an event listener for the newImage button to open a file chooser from the user's computer
        newImage.setOnAction(e -> {
            // Instantiate a FileChooser object
            FileChooser fileChooser = new FileChooser();
            //Set the title of the fileChooser
            fileChooser.setTitle("Open Image File");
            //Set the initial directory of the fileChooser
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            //Set the extension filter of the fileChooser
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            //Instantiate a File object
            File selectedFile = fileChooser.showOpenDialog(null);
            //Instantiate an Image object
            Image image = new Image(selectedFile.toURI().toString());
            //Add the image to the imageView
            imageView = new ImageView(image);
            //Set the size of the ImageView
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            //Set the location of the ImageView
            imageView.relocate(0, 0);
        });


        this.getChildren().addAll(beginningLabel, messageLabel, messageText, 
        imageText, encryptLabel, encrypt, sendLabel, imageView, newImage, prevImage);
    }
}
