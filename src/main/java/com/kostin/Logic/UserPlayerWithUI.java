package com.kostin.Logic;

public class UserPlayerWithUI implements Player, GetIdIntefaceForPlayers{
    private CheckerWords checkerWords;
    private int id;

    public UserPlayerWithUI(int id) {
        checkerWords = new CheckerWords();
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String say(String myWord, String lastUserWord) {

        int indexForTryLatUserUsedWord = 1;

        if(lastUserWord.endsWith( "ы" )) {
            indexForTryLatUserUsedWord++;
        }
        else if (lastUserWord.endsWith( "ъ" ))
            indexForTryLatUserUsedWord++;
        else if (lastUserWord.endsWith( "ь" ))
            indexForTryLatUserUsedWord++;

        if (myWord.equals( "" ))
            return "Поле пустое";
        else if (checkerWords.checkTheWordOnUsing( myWord )) {
            System.out.println("Слово было использовано!");
            return "Слово было использовано!";
        }
        else if (checkerWords.checkTheWord( myWord )) {
            if (lastUserWord=="") {
                checkerWords.checkAndDeleteIfThisWordIsTrueInAllCitiesNames( myWord );
            }
            else if (!myWord.startsWith( String.valueOf( lastUserWord.charAt( lastUserWord.length()-indexForTryLatUserUsedWord ) ).toUpperCase() ) ) {
                return "Ваше слово не начинается на правильную букву! [" + lastUserWord.charAt( lastUserWord.length()-indexForTryLatUserUsedWord) + "]";
            }
            else if (myWord.startsWith( String.valueOf( lastUserWord.charAt( lastUserWord.length()-indexForTryLatUserUsedWord ) ) ) )
                checkerWords.checkAndDeleteIfThisWordIsTrueInAllCitiesNames( myWord );
            return myWord;

        }
        else {
            return "Этого слова нет! Можете добавить его.";
        }

    }
}
