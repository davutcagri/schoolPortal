package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.LessonDto;
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
    public GenericResponse saveLesson(@RequestBody Lesson lesson) {
        lessonService.saveLesson(lesson);
        return new GenericResponse("Lesson saved.");
    }

    @GetMapping("/findall")
    public Stream<LessonDto> findAll() {
        return lessonService.findAll().stream().map(lesson -> {
            return new LessonDto(lesson);
        });
    }

    @PutMapping("/{lessonId:[0-9]+}/update/teacher")
    public GenericResponse updateTeacher(@PathVariable Long lessonId, @RequestBody Long teacherId ) {
        Lesson lesson = lessonService.updateTeacher(lessonId, teacherId);
        return new GenericResponse("Lesson's teacher updated, new teacher: " + lesson.getTeacher().getName());
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public GenericResponse delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new GenericResponse("Lesson deleted.");
    }

}
