package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.LessonDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

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
    public Stream<LessonDTO> findAll() {
        return lessonService.findAll();
    }

    @PutMapping("/{lessonId:[0-9]+}/update/teacher")
    public LessonDTO updateTeacher(@PathVariable Long lessonId, @RequestBody Long teacherId ) {
        return lessonService.updateTeacher(lessonId, teacherId);
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public GenericResponse delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new GenericResponse("Lesson deleted.");
    }

}
