package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.TeacherDto;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/save")
    public GenericResponse saveTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return new GenericResponse("Teacher saved.");
    }

    @GetMapping("/findall")
    public Stream<TeacherDto> findAll() {
        return teacherService.findAll().stream().map(teacher -> {
            return new TeacherDto(teacher);
        });
    }

    @PutMapping("/{id:[0-9]+}/update/email")
    public GenericResponse updateEmail(@PathVariable Long id, @RequestBody String newEmail) {
        Teacher teacher = teacherService.updateEmail(id, newEmail);
        return new GenericResponse("Teacher's email updated, new email: " + teacher.getEmail());
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public GenericResponse deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return new GenericResponse("Teacher deleted.");
    }

}
