package com.kostin.Data;

import java.util.List;
import java.util.Map;

public interface DataHandler {

    Map<Character, List<String>> getCitiesNames();
    String getInfo();
    String getRules();

}
