package it.elijah.ticket.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.elijah.ticket.model.Note;
import it.elijah.ticket.model.Ticket;
import it.elijah.ticket.model.User;
import it.elijah.ticket.repository.TicketRepository;
import it.elijah.ticket.repository.UserRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String getIndex(Model model, @RequestParam(name = "search", required = false) String search) {

        List<Ticket> ticket = new ArrayList<>();

        if (search == null || search.isBlank()) {
            ticket = ticketRepository.findAll();
        } else {
            ticket = ticketRepository.findByTitleIgnoreCase(search);
        }

        model.addAttribute("ticket", ticket);

        return "/dashboard/index";
    }

    @GetMapping("/ticket/{id}")
    public String getTask(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(id).get());
        Note note = new Note();
        note.setTicket(ticketRepository.findById(id).get());
        note.setCreatedAt(LocalDate.now());
        model.addAttribute("note", note);

        return "/dashboard/ticket";
    }

    @GetMapping("/ticket/create")
    public String getTask(Model model, Principal principal) {
        Ticket ticket = new Ticket();
        Optional<User> user = userRepository.findByUsername(principal.getName());
        // DA INSERIRE IL MODELLO LIST PER RECUPERARE TUTTI GLI OPERATORI
        ticket.setUser(user.get());
        ticket.setState(0);
        model.addAttribute("ticket", ticket);

        return "/dashboard/createTicket";
    }

}
