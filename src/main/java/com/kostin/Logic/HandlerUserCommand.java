package com.kostin.Logic;

import com.kostin.Data.CitiesHandler;
import com.kostin.Data.DataHandler;

import java.util.List;
import java.util.Map;

public class HandlerUserCommand implements HandlerUserCommandInterface{
    private DataHandler handler;
    private boolean isStart = false;

    public HandlerUserCommand() {
        handler = new CitiesHandler();
    }

    @Override
    public boolean handle(String userCommand) {
        if(userCommand.toLowerCase().equals("/end")) {
            System.exit(0);
        }
        else if(userCommand.toLowerCase().equals("/help")) {
            System.out.println("/start - Для того, чтобы начать игру");
            System.out.println("/rules - Для того, чтобы узнать правила");
            System.out.println("/info  - Для того, чтобы узнать больше информации про игру");
            System.out.println("/end   - Для того, чтобы завершить игру");
        }
        else if(userCommand.toLowerCase().equals("/info")) {
            System.out.println(handler.getInfo());
        }
        else if(userCommand.toLowerCase().equals("/rules")) {
            System.out.println(handler.getRules());
        }
        else if(userCommand.toLowerCase().equals("/start")) {
            isStart = true;
            System.out.println("Ваш ход: ");
        }
        else if(!isStart) {
            System.err.println("Неизвестная команда");
        }

        return isStart;
    }

}
