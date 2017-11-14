package com.happeyhunter.challenge.data;

/**
 * Holds some data for tickets
 *
 */
public class Ticket implements Comparable<Ticket> {

    private Double ticketPrice;

    public Ticket(Double price) {
        ticketPrice = price;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Compares ticket prices for sorting
     *
     * @param ticket
     * @return
     */
    @Override
    public int compareTo(Ticket ticket) {
        return getTicketPrice().compareTo(ticket.getTicketPrice());
    }
}
