package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher) {
        Optional<Teacher> teacherDB = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if (!teacherDB.isPresent()) {
            teacherRepository.save(teacher);
        }
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher updateEmail(Long id, String newEmail) {
        Teacher teacher = teacherRepository.getById(id);
        if(teacher == null) {
            throw new IllegalStateException();
        }
        teacher.setEmail(newEmail);
        teacherRepository.save(teacher);
        return teacher;
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
