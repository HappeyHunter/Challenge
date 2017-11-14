package com.happeyhunter.challenge.data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * World map
 *
 */
public class WorldMap {

    public final static int MAP_RADIUS = 10;

    private Map<Coordinates, Event> eventsMap = new HashMap<Coordinates, Event>();
    private List<Integer> eventIds = new ArrayList<Integer>();

    public Map<Coordinates, Event> getEvents() {
        return eventsMap;
    }

    /**
     * Add an event to the world.
     * Event is validated for a unique id and that an event is not already present at the coodinates
     *
     * @param anEvent   Event to be added
     * @return          Whether the event was added successfully
     */
    public boolean addEvent(Event anEvent) {
        boolean success = false;

        if(eventIds.contains(anEvent.getId())) {
            System.out.format("Event with id '%d' already exists\n", anEvent.getId());
        } else if(eventsMap.containsKey(anEvent.getCoordinates())) {
            System.out.format("Event already exists at coordinates %d, %d\n", anEvent.getCoordinates().getxCoord(), anEvent.getCoordinates().getyCoord());
        } else if(Math.abs(anEvent.getCoordinates().getyCoord()) <= MAP_RADIUS
                && Math.abs(anEvent.getCoordinates().getxCoord()) <= MAP_RADIUS) {
            eventIds.add(anEvent.getId());
            eventsMap.put(anEvent.getCoordinates(), anEvent);
            success = true;
        }

        return success;
    }

    /**
     * Get the closest events to the Coordinates provided up to the event limit provided
     *
     * @param queryCoordinates  Coordinates used for the query
     * @param eventLimit        Maximum number of events that will be returned
     * @return                  Array containing the closest events
     */
    public Event[] getClosest(Coordinates queryCoordinates, int eventLimit) {
        Event[] closestEvents = null;

        List<Event> eventsList = eventsMap.entrySet().stream()
                .filter((event) -> event.getValue().getTickets().size() > 0)
                .sorted((eventA, eventB) -> eventA.getValue().getDistanceFrom(queryCoordinates)
                                            .compareTo(eventB.getValue().getDistanceFrom(queryCoordinates)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        Event[] orderedEvents = eventsList.toArray(new Event[0]);

        if(orderedEvents != null) {
            if(orderedEvents.length > eventLimit) {
                closestEvents = Arrays.copyOf(orderedEvents, eventLimit);
            } else {
                closestEvents = orderedEvents;
            }
        }

        return closestEvents;
    }
}
