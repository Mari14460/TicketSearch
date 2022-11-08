package ru.netology.TicketSearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.TicketRepo.Ticket;
import ru.netology.TicketRepo.TicketRepo;

public class TicketManagerTest {
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
        TicketManager ticketManager = new TicketManager(ticketRepo);
        String from = "LED";
        String to = "LAX";
        Ticket[] expected = {ticket1, ticket};
        Ticket[] actual = ticketManager.findAll(from, to);

        Assertions.assertArrayEquals(expected, actual);
    }

    void findById() {
        TicketManager ticketManager = new TicketManager(ticketRepo);
        Ticket expected = ticket1;
        Ticket actual = ticketManager.findTicketById(101);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addOneTicket() {
        Ticket ticket3 = new Ticket(103, 5000, "LAX", "HOU", 120);

        TicketManager ticketManager = new TicketManager(ticketRepo);
        ticketManager.addTicketToRepo(ticket3);

        Ticket[] expected = {ticket3};
        Ticket[] actual = ticketManager.findAll("LAX", "HOU");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void deleteOneProduct() {
        TicketManager ticketManager = new TicketManager(ticketRepo);
        ticketManager.deleteTicketFromRepoById(101);

        Ticket[] expected = {ticket};
        Ticket[] actual = ticketManager.findAll("LED", "LAX");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchProductOnlyOne() {
        TicketManager ticketManager = new TicketManager(ticketRepo);

        Ticket[] expected = {ticket2};
        Ticket[] actual = ticketManager.findAll("LED", "HOU");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchProductTwo() {
        TicketManager ticketManager = new TicketManager(ticketRepo);

        Ticket[] expected = {ticket1, ticket};
        Ticket[] actual = ticketManager.findAll("LED", "LAX");

        Assertions.assertArrayEquals(expected, actual);
    }
}
