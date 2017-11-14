package com.happeyhunter.test.challenge;

import com.happeyhunter.challenge.data.Coordinates;
import com.happeyhunter.challenge.data.Event;
import com.happeyhunter.challenge.data.Ticket;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the Event class
 *
 */
public class EventTest {

    @Test
    public void testInfo() {
        Coordinates testEventCoordinates = new Coordinates(7, 7);

        Event testEvent = new Event(7, testEventCoordinates);
        Ticket aTicket20 = new Ticket(20.0);
        Ticket aTicket10 = new Ticket(10.0);

        testEvent.addTicket(aTicket20);
        testEvent.addTicket(aTicket10);

        assertEquals("Event 007 - $10.00", testEvent.getInfo());
    }

    @Test
    public void testDistanceFromSelf() {
        Coordinates testEventCoordinates = new Coordinates(7, 7);

        Event testEvent = new Event(0, testEventCoordinates);

        int fromItself = testEvent.getDistanceFrom(testEventCoordinates);

        assertEquals(0, fromItself);

        Coordinates testOpposite = new Coordinates(-7, -7);

        int fromOppositeItself = testEvent.getDistanceFrom(testEventCoordinates);

        assertEquals(0, fromItself);
    }

    @Test
    public void testDistanceFromOpposite() {
        Coordinates testEventCoordinates = new Coordinates(7, 7);

        Event testEvent = new Event(0, testEventCoordinates);

        Coordinates opposite = new Coordinates(-7, -7);

        int fromOpposite = testEvent.getDistanceFrom(opposite);

        assertEquals(28, fromOpposite);
    }

    @Test
    public void testDistanceFromOrigin() {
        Coordinates testEventCoordinates = new Coordinates(7, 7);

        Event testEvent = new Event(0, testEventCoordinates);

        Coordinates origin = new Coordinates(0, 0);

        int fromOrigin = testEvent.getDistanceFrom(origin);

        assertEquals(14, fromOrigin);
    }
}
