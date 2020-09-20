package com.kostin.Logic;

import lombok.SneakyThrows;
import org.w3c.dom.ls.LSOutput;

import java.sql.Time;
import java.util.Timer;

public class ComputerPlayer implements Player {

    private CheckerWords checkerWords;
    private int id;

    public ComputerPlayer (int id) {
        checkerWords = new CheckerWords();
        this.id = id;
    }

    @SneakyThrows
    @Override
    public String say(String lastUserWord) {
        Thread.sleep(1000);
        String word = "";
        if(lastUserWord.equals( "" )) {
            word = checkerWords.getWord( "А" );
        } else
            word = checkerWords.getWord( lastUserWord );
        System.out.println( "Computer [" + id + "]: " + word );
        if(word.equals( "Я больше не знаю слов эту букву" ))
            System.exit( 0 );
        return word;
    }

}
