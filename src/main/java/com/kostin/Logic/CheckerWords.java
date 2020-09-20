package com.kostin.Logic;

import com.kostin.Data.CitiesHandler;
import com.kostin.Data.DataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckerWords {

    private DataHandler handler;
    private Map<Character, List<String>> citiesNames;
    private List<String> usedWords;

     public CheckerWords () {
        handler = new CitiesHandler();
        citiesNames = handler.getCitiesNames();
        usedWords = new ArrayList<>();
    }

    String getWord(String lastUsedWord) {
         lastUsedWord = lastUsedWord.toUpperCase();
         String cityName = "...";
         int i = lastUsedWord.length()-1;

         if (lastUsedWord.endsWith( "Ъ" ))
             i = lastUsedWord.length() - 2;
         else if (lastUsedWord.endsWith( "Ь" ))
            i = lastUsedWord.length() - 2;
         else if (lastUsedWord.endsWith( "Ы" ))
            i = lastUsedWord.length() - 2;

         List<String> list = citiesNames.get( Character.valueOf( lastUsedWord.charAt( i ) ) );
         if (list.size()>0) {
             int randomIndex = (int) (Math.random() * (list.size() - 1));
             cityName = list.get( randomIndex );
             checkAndDeleteIfThisWordIsTrueInAllCitiesNames( cityName );
         }
         else {
             cityName = ("Я больше не знаю слов эту букву");
         }

         return cityName;
    }

    public boolean checkAndDeleteIfThisWordIsTrueInAllCitiesNames(String cityName) {
        try {
            for(int i = 0; i<cityName.length(); i++) {
                List<String> list = citiesNames.get( Character.valueOf( cityName.charAt( i ) ) );
                if (list.contains( cityName )) {
                    list.remove( cityName );
                    usedWords.add( cityName );
                    citiesNames.put( Character.valueOf( cityName.charAt( 0 ) ), list );

                    return true;
                } else if (usedWords.contains( cityName )) {
                    System.err.println( "Checker: Это слово было использовано" );
                } else continue;
            }
        } catch (Exception ex) {
            System.err.println( "Такого города нет либо такой команды нет" );
        }
        return false;
    }

}
