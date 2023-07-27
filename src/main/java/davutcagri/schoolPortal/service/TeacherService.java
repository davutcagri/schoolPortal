package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.TeacherDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;

    public TeacherService(TeacherRepository teacherRepository, LessonRepository lessonRepository) {
        this.teacherRepository = teacherRepository;
        this.lessonRepository = lessonRepository;
    }

    public TeacherDTO saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setName(teacher.getName());
        return teacherDTO;
    }

    public Stream<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(teacher -> {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setName(teacher.getName());
            teacherDTO.setLessons(teacher.getLessons().stream().map(Lesson::getName));
            return teacherDTO;
        });
    }

    public TeacherDTO updateLesson(Long id, String name) {
        Teacher teacher = teacherRepository.getReferenceById(id);

        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setTeacher(teacher);
        lessonRepository.save(lesson);

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setName(teacher.getName());
        teacherDTO.setLessons(teacher.getLessons().stream().map(Lesson::getName));
        return teacherDTO;
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
