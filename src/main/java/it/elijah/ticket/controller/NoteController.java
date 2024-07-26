package it.elijah.ticket.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.elijah.ticket.model.Note;
import it.elijah.ticket.model.User;
import it.elijah.ticket.repository.NoteRepository;
import it.elijah.ticket.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public String createNote(@Valid @ModelAttribute("note") Note note,BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ticket", note.getTicket());
            model.addAttribute("note", note);
            return "/dashboard/ticket";
        }
        Optional<User> user = userRepository.findByUsername(principal.getName());
        note.setUser(user.get());

        noteRepository.save(note);

        return "redirect:/dashboard/ticket/" + note.getTicket().getId();
    }

}
