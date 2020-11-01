package com.epam.task09.main;

import com.epam.task09.main.data.DataException;
import com.epam.task09.main.data.DataReader;
import com.epam.task09.main.data.FileDataReader;
import com.epam.task09.main.data.JsonShipCreator;
import com.epam.task09.main.logic.Port;
import com.epam.task09.main.logic.Ship;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final String INPUT_FILE = "data/ships.json";

    public static void main(String[] args) throws DataException {
        DataReader reader = new FileDataReader();
        String data = reader.read(INPUT_FILE);

        Port port = Port.getInstance();
        JsonShipCreator creator = new JsonShipCreator(port);
        List<Ship> ships = creator.createShips(data);

        int shipsCount = ships.size();
        ExecutorService service = Executors.newFixedThreadPool(shipsCount);
        ships.forEach(service::execute);
        service.shutdown();
    }
}