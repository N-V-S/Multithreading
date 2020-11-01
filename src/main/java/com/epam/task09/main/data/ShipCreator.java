package com.epam.task09.main.data;

import com.epam.task09.main.logic.Ship;

import java.util.List;

public interface ShipCreator {

    List<Ship> createShips(String fileName);
}