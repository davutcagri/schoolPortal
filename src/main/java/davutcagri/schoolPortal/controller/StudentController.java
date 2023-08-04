package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.StudentDTO;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public StudentDTO saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/findall")
    public List<StudentDTO> findAll() {
        return studentService.findAll();
    }

    @PutMapping("/{studentId}/add/lesson")
    public StudentDTO addLesson(@PathVariable Long studentId, @RequestBody Long lessonId) {
        return studentService.addLesson(studentId, lessonId);
    }

    @DeleteMapping("/{id}/delete")
    public GenericResponse deleteTeacher(@PathVariable Long id) {
        studentService.delete(id);
        return new GenericResponse("Teacher deleted.");
    }

}
