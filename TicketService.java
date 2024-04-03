package com.java.ticket.Service;

import com.java.ticket.Model.Ticket;
import com.java.ticket.Repository.TicketRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public Ticket createticket(@NonNull Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public List<Ticket> getAlltickets() {
        return ticketRepo.findAll();
    }

    public Ticket getticketById(@NonNull Integer id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public boolean updateticket(int id, Ticket ticket) {
        if (getticketById(id) == null) {
            return false;
        }
        try {
            ticketRepo.save(ticket);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteticket(int id) {
        if (getticketById(id) == null) {
            return false;
        }
        ticketRepo.deleteById(id);
        return true;
    }
}