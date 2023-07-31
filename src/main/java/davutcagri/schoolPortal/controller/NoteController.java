package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/save")
    public void saveNote(@RequestBody Note note) {
        noteService.saveNote(note);
    }

    @GetMapping("/findall")
    public Stream<NoteDTO> findAll() {
        return noteService.findAll();
    }

    @PutMapping("/{id}/update/mark")
    public NoteDTO updateMark(@PathVariable Long id, Double newMark) {
        return noteService.updateMark(id, newMark);
    }

    @DeleteMapping("/{id}/delete")
    public GenericResponse delete(@PathVariable Long id) {
        noteService.delete(id);
        return new GenericResponse("Note deleted.");
    }

}
