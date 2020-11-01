package com.epam.task09.main.data;

import com.epam.task09.main.logic.Port;
import com.epam.task09.main.logic.Ship;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonShipCreator implements ShipCreator {

    private static final String SHIPS = "ships";
    private static final String ID = "id";
    private static final String LOADED = "loaded";

    private final Port port;

    public JsonShipCreator(Port port) {
        this.port = port;
    }

    public List<Ship> createShips(String data) {
        JSONObject shipsObject = new JSONObject(data);
        JSONArray shipsArray = shipsObject.getJSONArray(SHIPS);
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < shipsArray.length(); i++) {
            JSONObject shipObject = shipsArray.getJSONObject(i);
            Ship ship = createShip(shipObject);
            ships.add(ship);
        }
        return ships;
    }

    private Ship createShip(JSONObject shipObject) {
        int id = shipObject.getInt(ID);
        boolean loaded = shipObject.getBoolean(LOADED);
        return new Ship(id, loaded, port);
    }
}