package com.example.notes2.services;

import com.example.notes2.models.Note;
import com.example.notes2.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NotesRepository notesRepository;

    public List<Note> findAll() {
        return notesRepository.findAll();
    }

    public void save(Note note) {
        note.setCreatedAt(new Date());
        notesRepository.save(note);
    }

    public void update(Long id, Note updatedNote) {
        updatedNote.setId(id);
        updatedNote.setCreatedAt(new Date());
        notesRepository.save(updatedNote);
    }

    public void delete(Long id) {
        notesRepository.deleteById(id);
    }

    public Note findById(Long id) {
        return notesRepository.findById(id).orElse(null);
    }
}
