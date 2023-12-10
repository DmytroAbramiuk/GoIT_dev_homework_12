package com.example.demo.services;

import com.example.demo.entity.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class NoteRepos {
    private final List<Note> notes = new ArrayList<>();

    public Note addNote(Note note){
        note.setId(UUID.randomUUID());
        this.notes.add(note);
        return note;
    }

    public List<Note> listAllNotes(){
        return this.notes;
    }

    public void deleteNoteById(UUID id){
        Optional<Note> optionalNote = this.notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();

        if (optionalNote.isPresent()) {
            this.notes.remove(optionalNote.get());
        } else {
            throw new RuntimeException("Note with id: " + id + " does not exist");
        }
    }

    public void updateNote(Note updatedNote){
        Optional<Note> optionalNote = this.notes.stream()
                .filter(note -> note.getId().equals(updatedNote.getId()))
                .findFirst();

        if (optionalNote.isPresent()) {
            optionalNote.get().setTitle(updatedNote.getTitle());
            optionalNote.get().setContent(updatedNote.getContent());
        } else {
            throw new RuntimeException("Note with id: " + updatedNote.getId() + " does not exist");
        }
    }

    public Note findNoteById(UUID id){
        Optional<Note> optionalNote = this.notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();

        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new RuntimeException("Note with id: " + id + " does not exist");
        }
    }
}
