package com.epam.task09.main.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDataReader implements DataReader {

    private static final Logger LOGGER = LogManager.getLogger();

    public String read(String fileName) throws DataException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                data.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
        return data.toString();
    }
}