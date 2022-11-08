package ru.netology.TicketSearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.TicketRepo.NotFoundException;
import ru.netology.TicketRepo.Ticket;
import ru.netology.TicketRepo.TicketRepo;

public class TicketRepoTest {

    TicketRepo ticketRepo = new TicketRepo();
    Ticket ticket = new Ticket(100, 140000, "LED", "LAX", 720);
    Ticket ticket1 = new Ticket(101, 110000, "LED", "LAX", 680);
    Ticket ticket2 = new Ticket(102, 100000, "LED", "HOU", 800);

    @BeforeEach
    public void setup () {
        ticketRepo.addTicket(ticket);
        ticketRepo.addTicket(ticket1);
        ticketRepo.addTicket(ticket2);
    }

    @Test
    void shouldReturnAll() {
        Ticket[] expected = {ticket, ticket1, ticket2};
        Ticket[] actual = ticketRepo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findById() {
        Ticket expected = ticket1;
        Ticket actual = ticketRepo.findById(101);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addOneTicket() {
        Ticket ticket3 = new Ticket(103, 5000, "LAX", "HOU", 120);
        ticketRepo.addTicket(ticket3);

        Ticket[] expected = {ticket, ticket1, ticket2, ticket3};
        Ticket[] actual = ticketRepo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void deleteOneTicket() {
        ticketRepo.deleteById(102);
        Ticket[] expected = {ticket, ticket1};
        Ticket[] actual = ticketRepo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void deleteTicketNotExists() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            ticketRepo.deleteById(105);
        });
    }


}
