package davutcagri.schoolPortal.controller;

import davutcagri.schoolPortal.dto.StudentDto;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.response.GenericResponse;
import davutcagri.schoolPortal.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public GenericResponse saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new GenericResponse("Student saved.");
    }

    @GetMapping("/findall")
    public Stream<StudentDto> findAll() {
        return studentService.findAll().stream().map(student -> {
            return new StudentDto(student);
        });
    }

    @PutMapping("/{id:[0-9]+}/update/email")
    public GenericResponse updateEmail(@PathVariable Long id, @RequestBody String newEmail) {
        Student student = studentService.getById(id, newEmail);
        return new GenericResponse("Teacher's email updated, new email: " + student.getEmail());
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public GenericResponse deleteTeacher(@PathVariable Long id) {
        studentService.delete(id);
        return new GenericResponse("Teacher deleted.");
    }

}
