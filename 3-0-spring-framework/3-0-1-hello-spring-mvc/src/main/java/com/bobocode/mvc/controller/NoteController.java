package com.bobocode.mvc.controller;

import com.bobocode.mvc.data.Notes;
import com.bobocode.mvc.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final Notes notes;

    @GetMapping
    public String getNotes(Model model) {
        model.addAttribute("noteList", notes.getAll());
        return "notes";
    }

    @PostMapping
    public String addNote(Note note) {
        notes.add(note);
        return "redirect:/notes";
    }
}