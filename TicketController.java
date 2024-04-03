package com.java.ticket.Controller;

import com.java.ticket.Model.Ticket;
import com.java.ticket.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> add(@RequestBody Ticket ticket) {
        Ticket newticket = ticketService.createticket(ticket);
        if (newticket != null) {
            return new ResponseEntity<>(newticket, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAlltickets() {
        List<Ticket> tickets = ticketService.getAlltickets();
        if (!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> updateticket(@PathVariable int ticketId, @RequestBody Ticket ticket) {
        boolean updated = ticketService.updateticket(ticketId, ticket);
        if (updated) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<Boolean> deleteticket(@PathVariable int ticketId) {
        boolean deleted = ticketService.deleteticket(ticketId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}