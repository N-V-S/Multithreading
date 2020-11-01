package com.epam.task09.main.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Ship implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    private final int id;
    private boolean loaded;
    private final Port port;

    public Ship(int id, boolean loaded, Port port){
        this.id = id;
        this.loaded = loaded;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Port getPort() {
        return port;
    }

    public void run() {
        try {
            Pier pier = port.acquirePier();
            pier.process(this);
            port.releasePier(pier);
        } catch (PortException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return id == ship.id &&
                loaded == ship.loaded &&
                port.equals(ship.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loaded, port);
    }
}