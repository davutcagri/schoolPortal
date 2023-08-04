package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.TeacherDTO;
import davutcagri.schoolPortal.exception.TeacherNotFoundException;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/save")
    public TeacherDTO saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("/findall")
    public List<TeacherDTO> findAll() throws TeacherNotFoundException {//Stream kaldırılacak
        return teacherService.findAll();
    }

    @PutMapping("/{id}/update/lesson")
    public TeacherDTO updateLesson(@PathVariable Long id, @RequestBody String name) {
        return teacherService.updateLesson(id, name);
    }

    @DeleteMapping("/{id}/delete")
    public GenericResponse deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return new GenericResponse("Teacher deleted.");
    }

}
