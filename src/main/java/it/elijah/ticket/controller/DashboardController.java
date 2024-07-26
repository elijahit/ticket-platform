package it.elijah.ticket.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.elijah.ticket.model.Note;
import it.elijah.ticket.model.Roles;
import it.elijah.ticket.model.Ticket;
import it.elijah.ticket.model.User;
import it.elijah.ticket.repository.CategoryRepository;
import it.elijah.ticket.repository.RolesRepository;
import it.elijah.ticket.repository.TicketRepository;
import it.elijah.ticket.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping()
    public String getDashboard() {
        return "redirect:/dashboard";
    }
    

    @GetMapping("dashboard")
    public String getIndex(Model model, @RequestParam(name = "search", required = false) String search, Principal principal) {

        List<Ticket> ticket = new ArrayList<>();

        // GESTISCO IL PERMESSO DI VISUALIZZARE DETERMINATI TICKET
        // Se uno è Admin li visualizza tutti, se sei operatore visualizzi solo i tuoi
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Roles adminRole = rolesRepository.findByName("ADMIN").get();
        Roles operatorRole = rolesRepository.findByName("OPERATORE").get();

        if (user.get().getRoles().contains(adminRole)) {
            if (search == null || search.isBlank()) {
                ticket = ticketRepository.findAll();
            } else {
                ticket = ticketRepository.findByTitleIgnoreCase(search);
            }
        } else if (user.get().getRoles().contains(operatorRole)) {
            if (search == null || search.isBlank()) {
                ticket = ticketRepository.getTicketById(user.get());
            } else {
                ticket = ticketRepository.getTicketById(user.get(), search);
            }
        }

        ///////////////////////////////////////////////////////////////

        model.addAttribute("ticket", ticket);

        return "/dashboard/index";
    }

    @GetMapping("dashboard/ticket/{id}")
    public String getTask(@PathVariable("id") Integer id, Model model, Principal principal) {

        Ticket ticket = ticketRepository.findById(id).get();
        Note note = new Note();
        model.addAttribute("ticket", ticket);
        note.setTicket(ticketRepository.findById(id).get());
        note.setCreatedAt(LocalDate.now());
        model.addAttribute("note", note);

        // GESTISCO IL PERMESSO DI VISUALIZZARE DETERMINATI TICKET
        // Se uno è Admin li visualizza tutti, se sei operatore visualizzi solo i tuoi
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Roles adminRole = rolesRepository.findByName("ADMIN").get();
        Roles operatorRole = rolesRepository.findByName("OPERATORE").get();
        
        if (user.get().getRoles().contains(adminRole)) {
            return "/dashboard/ticket";

        } else if (user.get().getRoles().contains(operatorRole)) {
            if(Objects.equals(ticket.getUser().getId(), user.get().getId())) {
                return "/dashboard/ticket";
            }
        }

        return "redirect:/dashboard";

    ///////////////////////////////////////////////////////////////
    }

    @GetMapping("dashboard/ticket/create")
    public String createTask(Model model, Principal principal) {
        Ticket ticket = new Ticket();
        Optional<User> user = userRepository.findByUsername(principal.getName());
        model.addAttribute("allOperators", userRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        ticket.setUser(user.get());
        ticket.setState(0);
        model.addAttribute("ticket", ticket);

        return "/dashboard/createTicket";
    }

    @PostMapping("dashboard/ticket/create")
    public String createTaskPost(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allOperators", userRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "/dashboard/createTicket";
        }
        ticketRepository.save(ticket);
        return "redirect:/dashboard";
    }

    @PostMapping("dashboard/ticket/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id) {
        
        ticketRepository.delete(ticketRepository.findById(id).get());

        return "redirect:/dashboard";
    }

    @PostMapping("dashboard/ticket/edit/{id}")
    public String editTask(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model, @PathVariable("id") Integer id) {

        Note note = new Note();
        model.addAttribute("ticket", ticketRepository.findById(id).get());
        note.setTicket(ticketRepository.findById(id).get());
        note.setCreatedAt(LocalDate.now());
        model.addAttribute("note", note);

        if (bindingResult.hasErrors()) {
            return "/dashboard/ticket";
        }

        ticketRepository.save(ticket);
        return "redirect:/dashboard/ticket/"+id;
    }

}
