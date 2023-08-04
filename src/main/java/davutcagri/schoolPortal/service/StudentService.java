package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.dto.StudentDTO;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            //StudentDTO
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setLessons(student.getLessons().stream().map(Lesson::getName).collect(Collectors.toList()));
            studentDTO.setNotes(student.getNotes().stream().map(note -> {
                //StudentDTO --> NoteDTO
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setMark(note.getMark());
                noteDTO.setStudentName(note.getStudent().getName());
                noteDTO.setLessonName(note.getLesson().getName());
                return noteDTO;
            }).collect(Collectors.toList()));
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public StudentDTO addLesson(Long studentId, Long lessonId) {
        //Find student
        Student student = studentRepository.getReferenceById(studentId);
        //Find lesson
        Lesson lesson = lessonRepository.getReferenceById(lessonId);
        //Add lesson
        student.addLesson(lesson);
        //Save student
        studentRepository.save(student);

        //StudentDTO
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setLessons(student.getLessons().stream().map(Lesson::getName).collect(Collectors.toList()));
        studentDTO.setNotes(student.getNotes().stream().map(note -> {
            //StudentDTO --> NoteDTO
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(note.getStudent().getName());
            noteDTO.setLessonName(note.getLesson().getName());
            return noteDTO;
        }).collect(Collectors.toList()));
        return studentDTO;
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
