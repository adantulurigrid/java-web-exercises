package com.bobocode.mvc.api;

import com.bobocode.mvc.data.Notes;
import com.bobocode.mvc.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteRestController {

    private final Notes notes;

    @GetMapping
    public List<Note> getNotes() {
        return notes.getAll();
    }

    @PostMapping
    public void addNote(@RequestBody Note note) {
        notes.add(note);
    }
}