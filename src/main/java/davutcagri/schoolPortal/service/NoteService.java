package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public Stream<NoteDTO> findAll() {
        return noteRepository.findAll().stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(note.getStudent().getName());
            noteDTO.setLessonName(note.getLesson().getName());
            return noteDTO;
        });
    }

    public NoteDTO updateMark(Long id, Double mark) {
        Note note = noteRepository.getReferenceById(id);
        note.setMark(mark);
        noteRepository.save(note);
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setMark(note.getMark());
        noteDTO.setStudentName(note.getStudent().getName());
        noteDTO.setLessonName(note.getLesson().getName());
        return noteDTO;
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
