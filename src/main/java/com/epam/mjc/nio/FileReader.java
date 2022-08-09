package com.epam.mjc.nio;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileReader {

    public static final String PROFILE_PARAM_VALUE_DELIMETER = ": ";

    //This method will be invoked for testing your code
    public Profile getDataFromFile(File file) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(file.toURI()))) {
            Profile profile = new Profile();
            reader.lines().forEach(string -> setValue(profile, string));
            return profile;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValue(Profile profile, String line) {
        Pair<String, String> keyValue = parseIntoParamValue(line);
        String paramName = keyValue.getLeft();
        String paramValue = keyValue.getRight();
        if (paramName.equalsIgnoreCase("name")) {
            profile.setName(paramValue);
        } else if (paramName.equalsIgnoreCase("age")) {
            profile.setAge(Integer.parseInt(paramValue));
        } else if (paramName.equalsIgnoreCase("email")) {
            profile.setEmail(paramValue);
        } else if (paramName.equalsIgnoreCase("phone")) {
            profile.setPhone(Long.parseLong(paramValue));
        }
    }

    private Pair<String, String> parseIntoParamValue(String line) {
        List<String> parsedString = Arrays.stream(line.split(PROFILE_PARAM_VALUE_DELIMETER)).collect(Collectors.toList());
        return Pair.of(parsedString.get(0), parsedString.get(1));
    }
}
