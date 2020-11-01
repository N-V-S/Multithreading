package com.epam.task09.main.data;

public interface DataReader {

    String read(String fileName) throws DataException;
}