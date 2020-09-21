package com.kostin.Logic;

public class ComputerPlayerWithUI implements Player {
    private CheckerWords checkerWords;
    private int id;

    public ComputerPlayerWithUI(int id) {
        checkerWords = new CheckerWords();
        this.id = id;
        System.out.println("Computer "+id+" was created");
    }

    @Override
    public String say(String myWord, String lastUserWord) {
        String myWordPC;
        if (lastUserWord.equals( "" ))
            myWordPC = checkerWords.getWord( "А" );
        else {
            myWordPC = checkerWords.getWord( lastUserWord );
        }

        return myWordPC;
    }


}
