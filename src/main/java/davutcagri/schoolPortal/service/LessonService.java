package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.LessonDTO;
import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;

    public LessonService(LessonRepository lessonRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    public LessonDTO saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setName(lesson.getName());
        lessonDTO.setTeacher(lesson.getTeacher().getName());
        lessonDTO.setStudents(lesson.getStudents().stream().map(Student::getName));
        lessonDTO.setNotes(lesson.getNotes().stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(note.getStudent().getName());
            noteDTO.setLessonName(note.getLesson().getName());
            return noteDTO;
        }));
        return lessonDTO;
    }

    public Stream<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setName(lesson.getName());
            lessonDTO.setTeacher(lesson.getTeacher().getName());
            lessonDTO.setNotes(lesson.getNotes().stream().map(note -> {
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setMark(note.getMark());
                noteDTO.setLessonName(note.getLesson().getName());
                noteDTO.setStudentName(note.getStudent().getName());
                return noteDTO;
            }));
            lessonDTO.setStudents(lesson.getStudents().stream().map(Student::getName));
            return lessonDTO;
        });
    }

    public LessonDTO updateTeacher(Long lessonId, Long teacherId) {
        Lesson lesson = lessonRepository.getReferenceById(lessonId);
        Teacher teacher = teacherRepository.getReferenceById(teacherId);
        lesson.setTeacher(teacher);
        lessonRepository.save(lesson);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setName(lesson.getName());
        lessonDTO.setTeacher(lesson.getTeacher().getName());
        lessonDTO.setStudents(lesson.getStudents().stream().map(Student::getName));
        lessonDTO.setNotes(lesson.getNotes().stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setLessonName(note.getLesson().getName());
            noteDTO.setStudentName(note.getStudent().getName());
            return noteDTO;
        }));
        return lessonDTO;
    }

    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

}
