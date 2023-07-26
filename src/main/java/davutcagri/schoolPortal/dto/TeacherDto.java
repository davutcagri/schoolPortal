package davutcagri.schoolPortal.dto;

import davutcagri.schoolPortal.model.Teacher;
import lombok.Data;

import java.util.stream.Stream;

@Data
public class TeacherDto {

    private String name;
    private String email;
    private Stream<String> lessons;

    public TeacherDto(Teacher teacher) {
        this.setName(teacher.getName());
        this.setEmail(teacher.getEmail());
        this.setLessons(teacher.getLessons().stream().map(lesson -> lesson.getName()));
    }

}
