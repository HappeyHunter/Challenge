package com.happeyhunter.challenge;

import com.happeyhunter.challenge.data.Coordinates;
import com.happeyhunter.challenge.data.Event;
import com.happeyhunter.challenge.data.WorldMap;

import java.util.Scanner;

/**
 * Handles input and output to the user
 *
 */
public class EventFetcher {

    private static final int EVENT_LIMIT = 5;

    private static final String COORDINATES_SEPARATOR = ",";

    private static final String EXIT_COMMAND = "exit";

    private static final String INPUT_REQUEST = "Please Input Coordinates:";
    private static final String CLOSEST_EVENTS_MESSAGE = "Closest Events to (%d,%d):\n";
    private static final String INVALID_INPUT = "Invalid input: %s\n";
    private static final String CLOSE_EVENT_LINE = "%s, Distance %d\n";

    private static final String COORDINATES_REQUIRED = "Coordinates required e.g.\"-5,5\"";
    private static final String INTEGERS_REQUIRED = "Coordinates must be whole numbers between %d and %d";

    private WorldMap worldMap;

    public EventFetcher(WorldMap aWorldMap) {
        worldMap = aWorldMap;
    }

    /**
     * Takes control of the Scanner input
     *
     */
    public void clHandler() {
        Scanner scn = null;
        String input;

        try {
            scn = new Scanner(System.in);

            while(true) {
                System.out.println(INPUT_REQUEST);

                input = scn.nextLine();

                if(EXIT_COMMAND.equalsIgnoreCase(input.trim())) {
                    // Exit command given. So exit.
                    break;
                }

                handleInput(input);
            }

        } finally {
            if(scn != null) {
                scn.close();
            }
        }
    }

    /**
     * Handles the coordinates and gets the events from the map.
     * Outputs the results it finds.
     *
     * @param input
     */
    private void handleInput(String input) {

        if(validateInput(input)) {
            String[] coordinatesStrings = input.split(COORDINATES_SEPARATOR);

            int x = Integer.parseInt(coordinatesStrings[0].trim());
            int y = Integer.parseInt(coordinatesStrings[1].trim());
            Coordinates queryCoordinates = new Coordinates(x, y);

            Event[] closeEvents = worldMap.getClosest(queryCoordinates, EVENT_LIMIT);

            System.out.format(CLOSEST_EVENTS_MESSAGE, queryCoordinates.getxCoord(), queryCoordinates.getyCoord());

            if(closeEvents != null && closeEvents.length > 0) {
                for(Event anEvent : closeEvents) {
                    System.out.format(CLOSE_EVENT_LINE, anEvent.getInfo(), anEvent.getDistanceFrom(queryCoordinates));
                }
            }
        }
    }

    /**
     * Validate the input is coordinates.
     * Prints some messages if invalid.
     *
     * @param input
     * @return      true if input is valid
     */
    private boolean validateInput(String input) {
        boolean valid = true;
        String errorString = "";

        String[] splitString = input.split(COORDINATES_SEPARATOR);

        // Split string should be two coordinates
        if(splitString.length != 2) {
            valid = false;
            errorString = COORDINATES_REQUIRED;
        } else {
            int x = 0;
            int y = 0;

            try {
                // Coordinates should both be integers
                x = Integer.parseInt(splitString[0].trim());
                y = Integer.parseInt(splitString[1].trim());
            } catch(NumberFormatException nfe) {
                valid = false;
                errorString = String.format(INTEGERS_REQUIRED, WorldMap.MAP_RADIUS, WorldMap.MAP_RADIUS*-1);
            }

            // Coordinates should be within the map radius
            if(valid && !(Math.abs(x) <= worldMap.MAP_RADIUS && Math.abs(y) <= WorldMap.MAP_RADIUS)) {
                valid = false;
                errorString = String.format(INTEGERS_REQUIRED, WorldMap.MAP_RADIUS, WorldMap.MAP_RADIUS*-1);
            }
        }

        if(!valid) {
            System.out.format(INVALID_INPUT, errorString);
        }

        return valid;
    }
}
