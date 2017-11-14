package com.happeyhunter.challenge.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds information on the event and is used to calculate the distance between the event and some Coordinates
 *
 */
public class Event {

    private static final String INFO_STRING = "Event %03d - $%2.2f";

    private int id;
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private Coordinates eventCoordinates;

    /**
     * Constructs an Event object
     *
     * @param id                id for the event
     * @param eventCoordinates  coordinates for the event
     */
    public Event(int id, Coordinates eventCoordinates) {
        this.id = id;
        this.eventCoordinates = eventCoordinates;
    }

    /**
     * Calculates the distance from the Event to the provided coordinates
     *
     * @param queryCoords
     * @return              Manhattan distance to the coordinates
     */
    public Integer getDistanceFrom(Coordinates queryCoords) {
        // Get the x difference and the y difference and add them together
        return Math.abs(queryCoords.getxCoord() - eventCoordinates.getxCoord())
               + Math.abs(queryCoords.getyCoord() - eventCoordinates.getyCoord());
    }

    /**
     * Formats the information on the event into a string with the event id and the cheapest ticket.
     *
     * "Event {id} - ${price}"
     *
     * @return  Formatted information on the event
     */
    public String getInfo() {
        return String.format(INFO_STRING, getId(), getCheapestTicket());
    }

    private Double getCheapestTicket() {
        return tickets.size() > 0 ? tickets.get(0).getTicketPrice() : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return eventCoordinates;
    }

    public void setCoordinates(Coordinates eventCoordinates) {
        this.eventCoordinates = eventCoordinates;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Adds a ticket to the event tickets
     *
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        Collections.sort(tickets);
    }
}
