package com.kostin.Logic;

import java.util.Scanner;

public class Game extends Thread {

    private HandlerUserCommandInterface handlerUserCommand;
    private Player[] players;

    public Game() {}

    public Game(int numberOfUserPlayer, int numberOfComputerPlayer) {
        init( numberOfUserPlayer, numberOfComputerPlayer );
    }

    private void init(int numberOfUserPlayer, int numberOfComputerPlayer) {
        handlerUserCommand = new HandlerUserCommand();

        createPlayers( numberOfUserPlayer, numberOfComputerPlayer );

        System.out.println( "Добро пожаловать в Игру Города!!!" );
        System.out.println( "/start - Для того, чтобы начать игру" );
        System.out.println( "/rules - Для того, чтобы узнать правила" );
        System.out.println( "/info  - Для того, чтобы узнать больше информации про игру" );
        System.out.println( "/end   - Для того, чтобы завершить игру" );
    }

    public Player[] createPlayers(int numberOfUserPlayer, int numberOfComputerPlayer) {
        if (numberOfUserPlayer < 0)
            numberOfUserPlayer = 0;
        if (numberOfComputerPlayer < 0)
            numberOfComputerPlayer = 0;
        if (numberOfComputerPlayer + numberOfUserPlayer <= 1)
            players = new Player[]{new UserPlayer(1), new ComputerPlayer(1)};
        else {
            players = new Player[numberOfComputerPlayer + numberOfUserPlayer];
            for (int i = 0; i < numberOfUserPlayer; i++) {
                players[i] = new UserPlayer(i+1);
            }
            for (int i = numberOfUserPlayer; i < numberOfComputerPlayer + numberOfUserPlayer; i++) {
                players[i] = new ComputerPlayer(i+1-numberOfUserPlayer);
            }
        }
        return players;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner( System.in );
        String theLatestWord = "";
        boolean isGame = false;
        while (true) {

            System.out.print( "user: " );
            String userCommand = scanner.nextLine();
            if (!isGame) {
                isGame = handlerUserCommand.handle( userCommand );
            } else {
                for (Player player : players) {
                    String tempTheLatestWord = player.say(userCommand, theLatestWord );
                    if(tempTheLatestWord.equals( "Такого города нет" )){
                        while (true) {
                            System.out.print( "user: " );
                            String usersssCommand = scanner.nextLine();
                            String tempsssTheLatestWord = player.say(usersssCommand, theLatestWord );
                            if(!tempTheLatestWord.equals( "Такого города нет" )) {theLatestWord = tempsssTheLatestWord; break;}
                            else {continue;}
                        }
                    }
                    else {
                        theLatestWord = tempTheLatestWord;
                    }
                }
            }
        }
    }
}
