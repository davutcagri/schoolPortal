package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/save")
    public GenericResponse saveNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return new GenericResponse("Note saved.");
    }

}
