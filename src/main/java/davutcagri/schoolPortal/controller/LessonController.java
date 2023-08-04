package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.LessonDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/save")
    public LessonDTO saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @GetMapping("/findall")
    public List<LessonDTO> findAll() {
        return lessonService.findAll();
    }

    @PutMapping("/{lessonId}/update/teacher")
    public LessonDTO updateTeacher(@PathVariable Long lessonId, @RequestBody Long teacherId ) {
        return lessonService.updateTeacher(lessonId, teacherId);
    }

    @DeleteMapping("/{id}/delete")
    public GenericResponse delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new GenericResponse("Lesson deleted.");
    }

}
