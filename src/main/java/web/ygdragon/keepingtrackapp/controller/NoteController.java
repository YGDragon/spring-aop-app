package web.ygdragon.keepingtrackapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.ygdragon.keepingtrackapp.model.Note;
import web.ygdragon.keepingtrackapp.service.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    /**
     * Getting all notes
     *
     * @return List of available notes
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.findAllNotes(), HttpStatus.OK);
    }

    /**
     * Getting one note by ID
     *
     * @param noteId ID of findable note
     * @return Findable note and Http status
     */
    @GetMapping("/{noteId}")
    public ResponseEntity<Optional<Note>> getNoteByID(@PathVariable Long noteId) {
        return new ResponseEntity<>(noteService.findNoteByID(noteId), HttpStatus.OK);
    }

    /**
     * Adding new note to the track
     *
     * @param note Addable note
     * @return New note and Http status
     */
    @PostMapping
    public ResponseEntity<Note> addNewNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    /**
     * Update data of note
     * @param note New data from body
     * @param noteId ID note from request
     * @return Updatable note and Http status
     */
    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNoteByID(@RequestBody Note note, @PathVariable Long noteId) {
        return new ResponseEntity<>(noteService.updateNote(note, noteId), HttpStatus.OK);
    }

    /**
     * Deleting note by ID
     *
     * @param noteId ID of deletable note
     * @return Http status
     */
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteByID(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok().build();
    }
}
