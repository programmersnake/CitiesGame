package com.kostin.UI.JavaFx;

import com.kostin.Data.CitiesHandler;
import com.kostin.Data.DataHandler;
import com.kostin.Logic.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class MainController extends Application {

    private DataHandler dataHandler = new CitiesHandler();
    private CheckerWords checkerWords;
    private int numberOfPlayerFromPlayers;
    private int usersForGame = 1;
    private int computersForGame = 1;
    private Player[] players;
    private int Hod = 0;
    private String globalTextForGame = "Привет в игре!!!";

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
    private Button addNewWord;

    private Player[] createPlayers(int numberOfUserPlayer, int numberOfComputerPlayer) {
        if (numberOfUserPlayer < 0)
            numberOfUserPlayer = 0;
        if (numberOfComputerPlayer < 0)
            numberOfComputerPlayer = 0;
        if (numberOfComputerPlayer + numberOfUserPlayer <= 1)
            players = new Player[]{new UserPlayerWithUI(1), new ComputerPlayerWithUI(1)};
        else {
            players = new Player[numberOfComputerPlayer + numberOfUserPlayer];
            for (int i = 0; i < numberOfUserPlayer; i++) {
                players[i] = new UserPlayerWithUI(i+1);
            }
            for (int i = numberOfUserPlayer; i < numberOfComputerPlayer + numberOfUserPlayer; i++) {
                players[i] = new ComputerPlayerWithUI(i+1-numberOfUserPlayer);
            }
        }
        return players;
    }

    @FXML
    void initialize() {
        AtomicReference<String> lastUsedWord= new AtomicReference<>( "" );

        startGame_Button.setOnAction( actionEvent -> {
            Platform.runLater( () -> {
                checkerWords = new CheckerWords();
                globalTextForGame = "Привет в игре!!!";
                MainAnchorPane.setVisible( true );
                TextAreaForRulesAndInfo.setVisible( false );
                LabelForExeptionsAndErrors.setVisible( false );
                endGame_Button.setDisable( false );
                startGame_Button.setDisable( true );
                rules_Button.setDisable( true );
                infoAboutGame_Button.setDisable( true );
                NumberOfUsers_SplitMenuButton.setDisable( true );
                NumberOfComputers_SplitMenuButton.setDisable( true );
                TextFieldForWritingWords.setPromptText( "User[0] вводить сюда название города" );
                MainTextAreaForEveryThingTexts.setText("Привет в игре!!!");
            } );
            dataHandler = new CitiesHandler();
            lastUsedWord.set( "" );
            players = createPlayers( usersForGame, computersForGame );
            System.out.println("Players created, they lenght is = "+players.length);
        } );

        Send_Button.setOnAction( actionEvent -> {
            Platform.runLater( () -> {
                if(LabelForExeptionsAndErrors.isVisible())
                    LabelForExeptionsAndErrors.setVisible( false );
                if(addNewWord.isVisible())
                    addNewWord.setDisable( false );

                String userWord = TextFieldForWritingWords.getText();
                TextFieldForWritingWords.setText( "" );

                //GameLogic after button activation
                if (players[Hod].getId()!=-1 ) {

                    String tempUseWord = players[Hod].say( userWord, lastUsedWord.get() );
                    if (tempUseWord.equals( "Поле пустое" )) {
                        System.out.println(tempUseWord);
                        LabelForExeptionsAndErrors.setText( tempUseWord );
                        LabelForExeptionsAndErrors.setVisible( true );
                    } else if (tempUseWord.equals( "Слово было использовано!" )) {
                        LabelForExeptionsAndErrors.setText( tempUseWord );
                        LabelForExeptionsAndErrors.setVisible( true );
                    } else if (tempUseWord.startsWith( "Ваше слово не начинается на правильную букву!" )) {
                        System.out.println(tempUseWord);
                        LabelForExeptionsAndErrors.setText( tempUseWord );
                        LabelForExeptionsAndErrors.setVisible( true );
                    } else if (tempUseWord.equals( "Этого слова нет! Можете добавить его." )) {
                        System.out.println(tempUseWord);
                        LabelForExeptionsAndErrors.setText( tempUseWord );
                        LabelForExeptionsAndErrors.setVisible( true );
                        addNewWord.setVisible( true );
                    } else {
                        System.out.println(tempUseWord + Hod);
                        globalTextForGame+=System.lineSeparator()+"User [" + players[Hod].getId() + "]: "+tempUseWord;
                        lastUsedWord.set( tempUseWord );

                        System.out.println(players[Hod].getId());
                        MainTextAreaForEveryThingTexts.setText( globalTextForGame );
                        MainTextAreaForEveryThingTexts.selectEnd();
                        Hod++;

                        // Logic for Computer Players
                        if(players[Hod].getId()==-1) {
                            while(true) {
                                if (Hod>=players.length) break;

                                System.out.println(players[Hod].getId());

                                tempUseWord = players[Hod].say( "", lastUsedWord.get() );
                                if (tempUseWord.equals( "Я больше не знаю слов эту букву" )) {
                                    System.out.println("Computer: Я больше не знаю слов эту букву");
                                    globalTextForGame += System.lineSeparator() + "Computer больше не знает слов на эту букву! Игра закончена!";
                                    Send_Button.setDisable( true );
                                    MainTextAreaForEveryThingTexts.setText( globalTextForGame );
                                    MainTextAreaForEveryThingTexts.selectEnd();
                                } else {
                                    System.out.println("Computer [" + (players[Hod].getId()+2) + "]: " + tempUseWord);
                                    lastUsedWord.set( tempUseWord );
                                    globalTextForGame += System.lineSeparator() + "Computer [" + players[Hod].getId() + "]: " + tempUseWord;
                                    MainTextAreaForEveryThingTexts.setText( globalTextForGame );
                                    Hod++;
                                }
                            }
                        }
                    }

                }
                else {
                    LabelForExeptionsAndErrors.setText( "Первым ходит компьютер..." );
                }

                if(Hod>=players.length-1)
                    Hod=0;

            } );
        } );

        addNewWord.setOnAction( actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("walter");
            dialog.setTitle("Добавить новое слово");
            dialog.setHeaderText("Введите слово которое хотите добавить и нажмите ОК");
            dialog.setContentText("Пожалуйста введите слово сюда:");

            Optional<String> result = dialog.showAndWait();

            if(result.isPresent()){
                System.out.println("Добавлено слово " + result.get());
                dataHandler.addNewWordToFile( result.get() );
            }
        } );

        rules_Button.setOnAction( actionEvent -> {
            Platform.runLater( () -> {
                TextAreaForRulesAndInfo.setText( dataHandler.getRules() );
            });
        });

        infoAboutGame_Button.setOnAction( actionEvent -> {
            Platform.runLater( () -> {
               TextAreaForRulesAndInfo.setText( dataHandler.getInfo() );
            });
        } );

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
                checkerWords = null;
                MainAnchorPane.setVisible( false );
                TextAreaForRulesAndInfo.setVisible( true );
                TextAreaForRulesAndInfo.setText( "Добро пожаловать в Игру Города!!!" );
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
