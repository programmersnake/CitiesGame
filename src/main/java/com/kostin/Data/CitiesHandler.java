package com.kostin.Data;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class CitiesHandler implements DataHandler {

    private Map<Character, List<String>> citiesNames = new HashMap<>();
    private String info = "";
    private String rules = "";

    public CitiesHandler() {
        init();
    }

    private void init() {
        getAllCitiesFromFile();
        getInfoFromFile();
        getRulesFromFile();
    }

    @SneakyThrows
    private void getAllCitiesFromFile() {
        char firstCharacter = 'А';
        File file = new File (getResourcePath("\\allCities"));
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader( file ));
        while(reader.ready()) {
            String city = reader.readLine();
            if (city.startsWith( String.valueOf( firstCharacter ) )) {
                list.add( city );
            } else {
                citiesNames.put( firstCharacter, list );
                list = new ArrayList<>();
                firstCharacter = city.charAt( 0 );
                list.add( city );
            }
        }
        citiesNames.put( firstCharacter, list );
    }

    private String getResourcePath(String file) {
        return getClass().getResource("/").getPath() + "static_files/" + file;
    }

    @SneakyThrows
    private void getInfoFromFile(){
        BufferedReader reader = new BufferedReader(new FileReader( new File (getResourcePath("\\info.txt"))));
        while(reader.ready()) {
            String line = reader.readLine();
            if(info!="")
                info+=System.lineSeparator();
            info+=line;
            }
        }

    @SneakyThrows
    private void getRulesFromFile(){
        BufferedReader reader = new BufferedReader(new FileReader( new File (getResourcePath("\\rules.rules"))));
        while(reader.ready()) {
            String line = reader.readLine();
            if(rules!="")
                rules+=System.lineSeparator();
            rules+=line;
        }
    }

    @Override
    public Map<Character, List<String>> getCitiesNames() {
        getAllCitiesFromFile();
        return citiesNames;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getRules() {
        return rules;
    }

    @SneakyThrows
    @Override
    public void addNewWordToFile(String newWord) {
        char firstChar = newWord.charAt( 0 );
        if(citiesNames.containsKey( firstChar )) {
            List<String> list = citiesNames.get( firstChar );
            if(!list.contains( newWord )) {
                list.add( newWord );
                citiesNames.put( firstChar, list );
                saveNewWordAtFile();
            }
        }
    }

    @SneakyThrows
    private void saveNewWordAtFile() {
        BufferedWriter writer = new BufferedWriter( new FileWriter( new File( getResourcePath( "\\allCities" ) ) ) );
        writer.flush();
        writer.write( getAllCitiesName() );
        writer.close();
    }

    private String getAllCitiesName() {
        String allCitiesName="";
        for(List<String> listCities : citiesNames.values()) {
            for(String city : listCities) {
                if(!allCitiesName.equals( "" ) )
                    allCitiesName+=System.lineSeparator();
                allCitiesName+=city;
            }
        }
        return allCitiesName;
    }
}
