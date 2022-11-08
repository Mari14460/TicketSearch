package ru.netology.TicketRepo;

public class TicketRepo {
    private Ticket[] tickets = new Ticket[0];

    public void addTicket(Ticket product) {
        Ticket[] temp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            temp[i] = tickets[i];
        }
        temp[tickets.length] = product;
        tickets = temp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Ticket with id=" + id + " doesn't exist");
        }
        Ticket[] temp = new Ticket[tickets.length - 1];
        int copyToIndex = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                temp[copyToIndex] = ticket;
                copyToIndex++;
            }
        }
        tickets = temp;
    }
}
