package davutcagri.schoolPortal.dto;

import davutcagri.schoolPortal.model.Lesson;
import lombok.Data;

import java.util.stream.Stream;

@Data
public class LessonDto {

    private String name;
    private String teacher;
    private Stream<String> students;
    private Stream<Double> notes;

    public LessonDto(Lesson lesson) {
        this.setName(lesson.getName());
        this.setTeacher(lesson.getTeacher().getName());
        this.setStudents(lesson.getStudents().stream().map(student -> student.getName()));
        this.setNotes(lesson.getNotes().stream().map(note -> note.getMark()));
    }

}
