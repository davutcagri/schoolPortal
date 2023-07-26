package davutcagri.schoolPortal.dto;

import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.model.Student;
import lombok.Data;

import java.util.stream.Stream;

@Data
public class StudentDto {

    private String name;
    private String email;
    private Stream<String> lessons;
    private Stream<Double> notes;

    public StudentDto(Student student) {
        this.setName(student.getName());
        this.setEmail(student.getEmail());
        this.setLessons(student.getLessons().stream().map(lesson -> lesson.getName()));
        this.setNotes(student.getNotes().stream().map(note -> note.getMark()));
    }

}
