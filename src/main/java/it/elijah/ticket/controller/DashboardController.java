package it.elijah.ticket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.elijah.ticket.model.Ticket;
import it.elijah.ticket.repository.TicketRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    TicketRepository ticketRepository;

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

        return "/dashboard/ticket";
    }

}
