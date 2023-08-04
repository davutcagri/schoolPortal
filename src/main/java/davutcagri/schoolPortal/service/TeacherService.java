package davutcagri.schoolPortal.service;

import davutcagri.schoolPortal.dto.LessonDTO;
import davutcagri.schoolPortal.dto.NoteDTO;
import davutcagri.schoolPortal.dto.TeacherDTO;
import davutcagri.schoolPortal.exception.TeacherNotFoundException;
import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Student;
import davutcagri.schoolPortal.model.Teacher;
import davutcagri.schoolPortal.repository.LessonRepository;
import davutcagri.schoolPortal.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TeacherDTO> findAll() throws TeacherNotFoundException {
        List<TeacherDTO> teacherDTOs = teacherRepository.findAll().stream().map(teacher -> {
            //TeacherDTO
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setName(teacher.getName());
            teacherDTO.setLessons(teacher.getLessons().stream().map(lesson -> {
                //TeacherDTO --> LessonDTO
                LessonDTO lessonDTO = new LessonDTO();
                lessonDTO.setName(lesson.getName());
                lessonDTO.setStudents(lesson.getStudents().stream().map(Student::getName).collect(Collectors.toList()));
                lessonDTO.setTeacher(lesson.getTeacher().getName());
                lessonDTO.setNotes(lesson.getNotes().stream().map(note -> {
                    //TeacherDTO --> LessonDTO --> NoteDTO
                    NoteDTO noteDTO = new NoteDTO();
                    noteDTO.setMark(note.getMark());
                    noteDTO.setLessonName(note.getLesson().getName());
                    noteDTO.setStudentName(note.getStudent().getName());
                    return noteDTO;
                }).collect(Collectors.toList()));
                return lessonDTO;
            }).collect(Collectors.toList()));
            return teacherDTO;
        }).collect(Collectors.toList());

        if(teacherDTOs.isEmpty()) {
            throw new TeacherNotFoundException("Teacher cannot found!");
        }
        else {
            return teacherDTOs;
        }

    }

    public TeacherDTO updateLesson(Long id, String name) {
        //Find teacher
        Teacher teacher = teacherRepository.getReferenceById(id);
        //Create new lesson
        Lesson newLesson = new Lesson();
        newLesson.setName(name);
        newLesson.setTeacher(teacher);
        lessonRepository.save(newLesson);
        //TeacherDTO
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setName(teacher.getName());
        teacherDTO.setLessons(teacher.getLessons().stream().map(lesson -> {
            //TeacherDTO --> LessonDTO
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setName(lesson.getName());
            lessonDTO.setTeacher(lesson.getTeacher().getName());
            lessonDTO.setStudents(lesson.getStudents().stream().map(Student::getName).collect(Collectors.toList()));
            lessonDTO.setNotes(lesson.getNotes().stream().map(note -> {
                //TeacherDTO --> LessonDTO --> NoteDTO
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setMark(note.getMark());
                noteDTO.setLessonName(note.getLesson().getName());
                noteDTO.setStudentName(note.getStudent().getName());
                return noteDTO;
            }).collect(Collectors.toList()));
            return lessonDTO;
        }).collect(Collectors.toList()));
        return teacherDTO;
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
