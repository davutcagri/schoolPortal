package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.dto.StudentDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public StudentService(StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    public StudentDTO saveStudent(Student student) {
        studentRepository.save(student);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        return studentDTO;
    }

    public Stream<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setLessons(student.getLessons().stream().map(Lesson::getName));
            studentDTO.setNotes(student.getNotes().stream().map(note -> {
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setMark(note.getMark());
                noteDTO.setStudentName(note.getStudent().getName());
                noteDTO.setLessonName(note.getLesson().getName());
                return noteDTO;
            }));
            return studentDTO;
        });
    }

    public StudentDTO addLesson(Long studentId, Long lessonId) {
        Student student = studentRepository.getReferenceById(studentId);
        Lesson lesson = lessonRepository.getReferenceById(lessonId);
        student.addLesson(lesson);
        studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setLessons(student.getLessons().stream().map(Lesson::getName));
        studentDTO.setNotes(student.getNotes().stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(note.getStudent().getName());
            noteDTO.setLessonName(note.getLesson().getName());
            return noteDTO;
        }));
        return studentDTO;
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
