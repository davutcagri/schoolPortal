package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public List<NoteDTO> findAll() {
        return noteRepository.findAll().stream().map(note -> {
            //NoteDTO
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(note.getStudent().getName());
            noteDTO.setLessonName(note.getLesson().getName());
            return noteDTO;
        }).collect(Collectors.toList());
    }

    public NoteDTO updateMark(Long id, Double mark) {
        //Find note
        Note note = noteRepository.getReferenceById(id);
        //Set new mark
        note.setMark(mark);
        //Save note
        noteRepository.save(note);
        //NoteDTO
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
