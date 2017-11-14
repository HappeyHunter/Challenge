package com.happeyhunter.test.challenge;

import com.happeyhunter.challenge.data.Coordinates;
import com.happeyhunter.challenge.data.Event;
import com.happeyhunter.challenge.data.Ticket;
import com.happeyhunter.challenge.data.WorldMap;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the WorldMap class
 *
 */
public class WorldMapTest {

    @Test
    public void addEventTest() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);
        Event anEvent = new Event(1, coordsA);

        aMap.addEvent(anEvent);

        assertEquals(1, aMap.getEvents().size());
        assertEquals(anEvent, aMap.getEvents().get(coordsA));
    }

    @Test
    public void addSecondEventTest() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);
        Event eventA = new Event(1, coordsA);

        aMap.addEvent(eventA);

        Coordinates coordsB = new Coordinates(6, -2);
        Event eventB = new Event(2, coordsB);

        aMap.addEvent(eventB);

        assertEquals(2, aMap.getEvents().size());
        assertEquals(eventA, aMap.getEvents().get(coordsA));
        assertEquals(eventB, aMap.getEvents().get(coordsB));
    }

    @Test
    public void addDuplicateId() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);
        Event anEvent = new Event(1, coordsA);

        aMap.addEvent(anEvent);

        Coordinates coordsB = new Coordinates(8, 8);
        Event aDupEvent = new Event(1, coordsB);

        aMap.addEvent(aDupEvent);

        assertEquals(1, aMap.getEvents().size());
        assertEquals(anEvent, aMap.getEvents().get(coordsA));
    }

    @Test
    public void addDuplicateCoord() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);
        Event anEvent = new Event(1, coordsA);

        aMap.addEvent(anEvent);

        Event aDupEvent = new Event(2, coordsA);

        aMap.addEvent(aDupEvent);

        assertEquals(1, aMap.getEvents().size());
        assertEquals(anEvent, aMap.getEvents().get(coordsA));
    }

    @Test
    public void closestEventsZero() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);

        Event[] closest = aMap.getClosest(coordsA, 5);

        assertEquals(0, closest.length);
    }

    @Test
    public void closestEventsOrdering() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5); // 17
        Event eventA = new Event(1, coordsA);

        Ticket aTicketA = new Ticket(10.0);
        eventA.addTicket(aTicketA);

        aMap.addEvent(eventA);

        Coordinates coordsB = new Coordinates(8, 8); // 23
        Event eventB = new Event(2, coordsB);

        Ticket aTicketB = new Ticket(10.0);
        eventB.addTicket(aTicketB);

        aMap.addEvent(eventB);

        Coordinates coordsC = new Coordinates(6, -2); // 11
        Event eventC = new Event(3, coordsC);

        Ticket aTicketC = new Ticket(10.0);
        eventC.addTicket(aTicketC);

        aMap.addEvent(eventC);

        Coordinates coordsD = new Coordinates(2, -9);

        Event[] closest = aMap.getClosest(coordsD, 5);

        assertEquals(3, closest.length);
        assertEquals(eventA, closest[1]);
        assertEquals(eventB, closest[2]);
        assertEquals(eventC, closest[0]);
    }

    @Test
    public void closestEventsAboveLimit() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5); // 17
        Event eventA = new Event(1,  coordsA);

        Ticket aTicketA = new Ticket(10.0);
        eventA.addTicket(aTicketA);

        aMap.addEvent(eventA);

        Coordinates coordsB = new Coordinates(8, 8); // 23
        Event eventB = new Event(2, coordsB);

        Ticket aTicketB = new Ticket(10.0);
        eventB.addTicket(aTicketB);

        aMap.addEvent(eventB);

        Coordinates coordsC = new Coordinates(6, -2); // 11
        Event eventC = new Event(3, coordsC);

        Ticket aTicketC = new Ticket(10.0);
        eventC.addTicket(aTicketC);

        aMap.addEvent(eventC);

        Coordinates coordsD = new Coordinates(5, -2); // 10
        Event eventD = new Event(4, coordsD);

        Ticket aTicketD = new Ticket(10.0);
        eventD.addTicket(aTicketD);

        aMap.addEvent(eventD);

        Coordinates coordsQuery = new Coordinates(2, -9);

        Event[] closest = aMap.getClosest(coordsQuery, 3);

        assertEquals(3, closest.length);
        assertEquals(eventA, closest[2]);
        assertEquals(eventC, closest[1]);
        assertEquals(eventD, closest[0]);
    }

    @Test
    public void noTicketEvent() {
        WorldMap aMap = new WorldMap();

        Coordinates coordsA = new Coordinates(5, 5);
        Event anEvent = new Event(1, coordsA);

        aMap.addEvent(anEvent);

        Event[] closest = aMap.getClosest(coordsA, 5);

        assertEquals(1, aMap.getEvents().size());
        assertEquals(0, closest.length);
    }
}
