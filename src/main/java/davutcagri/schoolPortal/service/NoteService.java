package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void saveNote(Note note) {
        Optional<Note> noteDB = noteRepository.findNoteByStudentAndLesson(note.getStudent(), note.getLesson());
        if (!noteDB.isPresent()) {
            noteRepository.save(note);
        }
    }

}
