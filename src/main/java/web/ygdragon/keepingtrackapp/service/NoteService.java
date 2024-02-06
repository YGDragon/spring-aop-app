package web.ygdragon.keepingtrackapp.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.ygdragon.keepingtrackapp.aspects.TrackUserAction;
import web.ygdragon.keepingtrackapp.exception.NoteNotFoundException;
import web.ygdragon.keepingtrackapp.model.Note;
import web.ygdragon.keepingtrackapp.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    /**
     * Finding one note by ID
     *
     * @param id ID finding note
     * @return Findable note
     */
    public @NonNull Optional<Note> findNoteByID(Long id) {
        return noteRepository.findById(id);
    }

    /**
     * Find all notes
     *
     * @return List of available notes
     */
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Creating new note
     *
     * @param note Data for new note
     * @return New note
     */
    @TrackUserAction
    public Note createNote(Note note) {
        Note newNote = new Note();
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        return noteRepository.save(newNote);
    }

    /**
     * Updating note
     *
     * @param note   Updatable note
     * @param noteId ID of finding note
     * @return Updated notes
     */
    @TrackUserAction
    public Note updateNote(Note note, Long noteId) {
        Note uNote;
        try {
            uNote = noteRepository.findById(noteId).orElse(note);
            if (!uNote.equals(note)) {
                uNote.setTitle(note.getTitle());
                uNote.setContent(note.getContent());
                return noteRepository.save(uNote);
            } else {
                System.out.println("Error>> ID not found");
            }
        } catch (NoteNotFoundException e) {
            throw new NoteNotFoundException(noteId);
        }
        return uNote;
    }

    /**
     * Deleting note
     *
     * @param id ID of deleting note
     */
    @TrackUserAction
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
