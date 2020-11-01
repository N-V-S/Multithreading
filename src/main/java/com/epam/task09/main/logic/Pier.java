package com.epam.task09.main.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pier {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Port port;

    public Pier(Port port){
        this.port = port;
    }

    public void process(Ship ship) {
        boolean loaded = ship.isLoaded();
        ship.setLoaded(!loaded);
    }
}