package com.example.demo.services;

import com.example.demo.entity.Note;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepos noteRepos;

    @Override
    public List<Note> listAll() {
        return noteRepos.listAllNotes();
    }

    @Override
    public Note add(Note note) {
        return noteRepos.addNote(note);
    }

    @Override
    public void deleteById(UUID id) {
        noteRepos.deleteNoteById(id);
    }

    @Override
    public void update(Note note) {
        noteRepos.updateNote(note);
    }

    @Override
    public Note getById(UUID id) {
        return noteRepos.findNoteById(id);
    }

    @PostConstruct
    public void init(){
        Note firstNote = new Note("first", "1");
        Note secondNote = new Note("second", "2");
        Note thirdNote = new Note("third", "3");

        System.out.println("Adding notes...");
        add(firstNote);
        add(secondNote);
        add(thirdNote);
        System.out.println("Added successfully");

        System.out.println("full list: ");
        List<Note> noteList = listAll();
        noteList.forEach(note -> {
            System.out.println(note.getContent());
        });

        System.out.println("updating note...");
        Note noteForUpdate = new Note("new note", "with some updates");
        noteForUpdate.setId(firstNote.getId());
        update(noteForUpdate);
        System.out.println("updated successfully");
        listAll().forEach(note -> System.out.println(note.getContent()));

        System.out.println("getting by id...");
        System.out.println("getById(noteList.get(1).getId()).getContent() = " + getById(noteList.get(1).getId()).getContent());

        System.out.println("delete by id...");
        deleteById(noteList.get(1).getId());
        System.out.println("delete successfully");
        listAll().forEach(note -> System.out.println(note.getContent()));
    }
}
