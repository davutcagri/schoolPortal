package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;

    public LessonService(LessonRepository lessonRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    public void saveLesson(Lesson lesson) {
        Optional<Lesson> lessonDB = lessonRepository.findLessonByNameAndTeacher(lesson.getName(), lesson.getTeacher());
        if (!lessonDB.isPresent()) {
            lessonRepository.save(lesson);
        }
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson updateTeacher(Long lessonId, Long teacherId) {
        Lesson lesson = lessonRepository.getById(lessonId);
        if(lesson == null) {
            throw new IllegalStateException();
        }
        Teacher teacher = teacherRepository.getById(teacherId);
        lesson.setTeacher(teacher);
        lessonRepository.save(lesson);
        return lesson;
    }

    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

}
