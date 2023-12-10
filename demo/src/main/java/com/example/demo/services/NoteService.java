package com.example.demo.services;

import com.example.demo.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<Note> listAll();
    Note add(Note note);
    void deleteById(UUID id);
    void update(Note note);
    Note getById(UUID id);
}
