package com.happeyhunter.challenge;

import com.happeyhunter.challenge.data.Coordinates;
import com.happeyhunter.challenge.data.Event;
import com.happeyhunter.challenge.data.Ticket;
import com.happeyhunter.challenge.data.WorldMap;

import java.util.Random;

/**
 *
 * Assumptions:
 *      If an event has no tickets then it is ignored in the search
 *      Error messages are desired
 *      Tickets are endless
 */

public class Challenge {

    public static Random rand = new Random();

    public static void main(String[] args) {
        // Build the map and add the events for this run
	    WorldMap aMap = new WorldMap();

	    // Add some events (20 to 30)
        int eventCount = rand.nextInt(10) + 20;
        int ticketCount;
        Event anEvent;
        Ticket aTicket;
        Coordinates aCoords;
	    for(int i = 0; i < eventCount; i++) {
            aCoords = new Coordinates(rand.nextInt(21)-10, rand.nextInt(21)-10);
            anEvent = new Event(i, aCoords);

            // Add some tickets, maybe
            ticketCount = rand.nextInt(5);
            for(int j = 0; j < ticketCount; j++) {
                aTicket = new Ticket(rand.nextInt(30) + 50.0);
                anEvent.addTicket(aTicket);
            }

            aMap.addEvent(anEvent);
        }

	    // Create the event fetcher and supply the generated map
	    EventFetcher anEventFetcher = new EventFetcher(aMap);

	    // Run it
        anEventFetcher.clHandler();
    }
}
