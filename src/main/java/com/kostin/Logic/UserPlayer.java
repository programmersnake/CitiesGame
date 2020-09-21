package com.kostin.Logic;

import java.util.Scanner;

public class UserPlayer implements Player {

    private CheckerWords checkerWords;
    private int id;

    public UserPlayer (int id) {
        this.id = id;
        checkerWords = new CheckerWords();
    }

    @Override
    public String say(String myWord, String lastUserWord) {
        lastUserWord = lastUserWord.toUpperCase();
        while(true) {
            System.out.print( "User ["+id+"]: " );

            int index = lastUserWord.length()-1;
            if (lastUserWord.endsWith( "ь" ))
                 index = lastUserWord.length()-2;
            else if (lastUserWord.endsWith( "ъ" ))
                index = lastUserWord.length()-2;
            else if (lastUserWord.endsWith( "ы" ))
                index = lastUserWord.length()-2;

            if(lastUserWord.isEmpty() || myWord.startsWith( String.valueOf( lastUserWord.charAt(index)))) {
                boolean b = checkerWords.checkAndDeleteIfThisWordIsTrueInAllCitiesNames( myWord ).equals( "Yes" );
                if(b==true)
                    break;
                else if(checkerWords.checkAndDeleteIfThisWordIsTrueInAllCitiesNames( myWord ).equals( "Такого города нет либо такой команды нет" ))
                    return "Такого города нет";
                else continue;
            } else {
                System.err.println( "Слово не начинается на последнюю букву предыдущего слова! [" + lastUserWord.charAt( index ) + "]" );
                return "Слово не начинается на последнюю букву предыдущего слова! [" + lastUserWord.charAt( index ) + "]";
            }
        }

        return myWord;
    }

}
