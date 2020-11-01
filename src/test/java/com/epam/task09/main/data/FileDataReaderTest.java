package com.epam.task09.main.data;

import org.junit.Assert;
import org.junit.Test;

public class FileDataReaderTest {

    private static final String VALID_INPUT_FILE = "src/test/resources/ships.json";
    private static final String INVALID_INPUT_FILE = "src/test/resources/invalid.json";

    private final FileDataReader reader = new FileDataReader();

    @Test
    public void testReadShouldReturnStringWhenFileExists() throws DataException {
        //given
        String expected = "{\"ships\":[{\"id\":1,\"loaded\":true},{\"id\":2,\"loaded\":false}]}";
        //when
        String actual = reader.read(VALID_INPUT_FILE);
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DataException.class) //then
    public void testReadShouldThrowExceptionWhenFileNotExists() throws DataException {
        //when
        reader.read(INVALID_INPUT_FILE);
    }
}