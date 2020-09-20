package com.kostin.UI.JavaFx;

import com.kostin.Logic.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Application {

    private Game game;
    private int usersForGame = 1;
    private int computersForGame = 1;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load( new File("C:/Users/kosti/IdeaProjects/CitiesGame/src/main/resources/javafx/main.fxml" ).toURL());
        stage.setTitle("Cities Game");
        stage.setScene(new Scene(root));
        stage.setResizable( false );
        stage.show();
    }

    public static void main(String[] args) {
        launch( args );
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="startGame_Button"
    private Button startGame_Button;

    @FXML // fx:id="rules_Button"
    private Button rules_Button;

    @FXML // fx:id="infoAboutGame_Button"
    private Button infoAboutGame_Button;

    @FXML // fx:id="endGame_Button"
    private Button endGame_Button; // Value injected by FXMLLoader

    @FXML // fx:id="NumberOfUsers_SplitMenuButton"
    private SplitMenuButton NumberOfUsers_SplitMenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="OneUser"
    private MenuItem OneUser; // Value injected by FXMLLoader

    @FXML // fx:id="TwoUser"
    private MenuItem TwoUser; // Value injected by FXMLLoader

    @FXML // fx:id="ThreeUser"
    private MenuItem ThreeUser; // Value injected by FXMLLoader

    @FXML // fx:id="FourUser"
    private MenuItem FourUser; // Value injected by FXMLLoader

    @FXML // fx:id="FiveUser"
    private MenuItem FiveUser; // Value injected by FXMLLoader

    @FXML // fx:id="NumberOfComputers_SplitMenuButton"
    private SplitMenuButton NumberOfComputers_SplitMenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="OnePC"
    private MenuItem OnePC; // Value injected by FXMLLoader

    @FXML // fx:id="TwoPC"
    private MenuItem TwoPC; // Value injected by FXMLLoader

    @FXML // fx:id="ThreePC"
    private MenuItem ThreePC; // Value injected by FXMLLoader

    @FXML // fx:id="FourPC"
    private MenuItem FourPC; // Value injected by FXMLLoader

    @FXML // fx:id="FivePC"
    private MenuItem FivePC; // Value injected by FXMLLoader

    @FXML // fx:id="MainAnchorPane"
    private AnchorPane MainAnchorPane; // Value injected by FXMLLoader

    @FXML // fx:id="MainTextAreaForEveryThingTexts"
    private TextArea MainTextAreaForEveryThingTexts; // Value injected by FXMLLoader

    @FXML
    private TextArea TextAreaForRulesAndInfo;

    @FXML // fx:id="AnchorPaneWithTextFieldAndSendButton"
    private AnchorPane AnchorPaneWithTextFieldAndSendButton; // Value injected by FXMLLoader

    @FXML // fx:id="Send_Button"
    private Button Send_Button; // Value injected by FXMLLoader

    @FXML // fx:id="TextFieldForWritingWords"
    private TextField TextFieldForWritingWords; // Value injected by FXMLLoader

    @FXML // fx:id="LabelForExeptionsAndErrors"
    private Label LabelForExeptionsAndErrors; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        startGame_Button.setOnAction( actionEvent -> {
            Platform.runLater( () -> {
                MainAnchorPane.setVisible( true );
                TextAreaForRulesAndInfo.setVisible( false );
                endGame_Button.setDisable( false );
                startGame_Button.setDisable( true );
                rules_Button.setDisable( true );
                infoAboutGame_Button.setDisable( true );
                NumberOfUsers_SplitMenuButton.setDisable( true );
                NumberOfComputers_SplitMenuButton.setDisable( true );
            } );


            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return null;
                }
            }

            Thread gameThread = new Thread(game);
            gameThread.start();

        } );

        NumberOfUsers_SplitMenuButton.setOnAction( actionEvent -> usersForGame = 0 );

        NumberOfComputers_SplitMenuButton.setOnAction( actionEvent -> computersForGame = 0 );

        OneUser.setOnAction( actionEvent -> {
            NumberOfUsers_SplitMenuButton.setText( "1 User" );
            usersForGame = 1;
        } );

        TwoUser.setOnAction( actionEvent -> {
            NumberOfUsers_SplitMenuButton.setText( "2 Users" );
            usersForGame = 2;
        } );

        ThreeUser.setOnAction( actionEvent -> {
            NumberOfUsers_SplitMenuButton.setText( "3 Users" );
            usersForGame = 3;
        } );

        FourUser.setOnAction( actionEvent -> {
            NumberOfUsers_SplitMenuButton.setText( "4 User" );
            usersForGame = 4;
        } );

        FiveUser.setOnAction( actionEvent -> {
            NumberOfUsers_SplitMenuButton.setText( "5 User" );
            usersForGame = 5;
        } );

        OnePC.setOnAction( actionEvent -> {
            NumberOfComputers_SplitMenuButton.setText( "1 PC" );
            computersForGame = 1;
        } );

        TwoPC.setOnAction( actionEvent -> {
            NumberOfComputers_SplitMenuButton.setText( "2 PC" );
            computersForGame = 2;
        } );

        ThreePC.setOnAction( actionEvent -> {
            NumberOfComputers_SplitMenuButton.setText( "3 PC" );
            computersForGame = 3;
        } );

        FourPC.setOnAction( actionEvent -> {
            NumberOfComputers_SplitMenuButton.setText( "4 PC" );
            computersForGame = 4;
        } );

        FivePC.setOnAction( actionEvent -> {
            NumberOfComputers_SplitMenuButton.setText( "5 PC" );
            computersForGame = 5;
        } );

        endGame_Button.setOnAction( actionEvent -> {

            Platform.runLater( () -> {
                MainAnchorPane.setVisible( false );
                TextAreaForRulesAndInfo.setVisible( true );
                endGame_Button.setDisable( true );
                startGame_Button.setDisable( false );
                rules_Button.setDisable( false );
                infoAboutGame_Button.setDisable( false );
                NumberOfUsers_SplitMenuButton.setDisable( false );
                NumberOfComputers_SplitMenuButton.setDisable( false );
            } );

        } );
    }
}
