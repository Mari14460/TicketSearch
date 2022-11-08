package ru.netology.TicketSearch;
import ru.netology.TicketRepo.Ticket;
import ru.netology.TicketRepo.TicketRepo;

import java.util.Arrays;

public class TicketManager {

    TicketRepo repository;

    public TicketManager(TicketRepo repository) {
        this.repository = repository;
    }

    public void addTicketToRepo(Ticket ticket){
        repository.addTicket(ticket);
    }

    public void deleteTicketFromRepoById(int id){
        repository.deleteById(id);
    }

    public Ticket findTicketById(int id){
        return repository.findById(id);
    }

    public Ticket[] findAll(String from, String to){
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] temp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                temp[result.length] = ticket;
                result = temp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    /*
    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] temp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                temp[result.length] = ticket;
                result = temp;
            }
        }
        Arrays.sort(result);
        return result;
    }
    */
    // метод определения соответствия билета запросу search
    public boolean matches(Ticket ticket, String from, String to) {
        if (ticket.getDeparture().equals(from) && ticket.getArrival().equals(to)) {
            return true;
        } else {
            return false;
        }
    }
}
