package com.runner;

import com.handler.GpsHandler;

/**
 * Created by lutsenko.d on 04.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        GpsHandler gpsHandler = new GpsHandler();
        gpsHandler.findAndSaveCoordinates();
    }
}
