package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        Optional<Student> studentDB = studentRepository.findStudentByEmail(student.getEmail());
        if(!studentDB.isPresent()) {
            studentRepository.save(student);
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id, String newEmail) {
        Student student = studentRepository.getById(id);
        if(student == null) {
            throw new IllegalStateException();
        }
        student.setEmail(newEmail);
        studentRepository.save(student);
        return student;
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
