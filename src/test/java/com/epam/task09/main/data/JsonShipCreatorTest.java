package com.epam.task09.main.data;

import com.epam.task09.main.logic.Port;
import com.epam.task09.main.logic.Ship;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class JsonShipCreatorTest {

    private static final String INPUT_STRING = "{\"ships\":[{\"id\":1,\"loaded\":true},{\"id\":2,\"loaded\":false}]}";

    private static final Port port = Mockito.mock(Port.class);
    private static final List<Ship> EXPECTED_SHIP_LIST = Arrays.asList(
            new Ship(1, true, port),
            new Ship(2, false, port)
    );

    private final JsonShipCreator creator = new JsonShipCreator(port);

    @Test
    public void testCreateShipsShouldReturnShipsListWhenGetData() {
        //when
        List<Ship> actual = creator.createShips(INPUT_STRING);
        //then
        Assert.assertEquals(EXPECTED_SHIP_LIST, actual);
    }
}