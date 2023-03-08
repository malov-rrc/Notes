package com.example.notes2.controllers;

import com.example.notes2.models.Note;
import com.example.notes2.services.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping()
    public String notes(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "notes";
    }

    @GetMapping("/new")
    public String newNote(@ModelAttribute("note") Note note) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("note") @Valid Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("note", noteService.findById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("note") @Valid Note note, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "edit";

        noteService.update(id, note);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        noteService.delete(id);
        return "redirect:/notes";
    }
}
