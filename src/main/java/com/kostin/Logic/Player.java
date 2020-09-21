package com.kostin.Logic;

public interface Player {
    String say(String myWord, String lastUserWord);
    default int getId() {
        return -1;
    }
}
