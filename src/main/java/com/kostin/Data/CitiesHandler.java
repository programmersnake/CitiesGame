package com.kostin.Data;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader( new File (getResourcePath("\\allCities"))));
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


    String getResourcePath(String file) {
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
}
