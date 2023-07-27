package davutcagri.schoolPortal.dto;

import lombok.Data;

import java.util.stream.Stream;

@Data
public class LessonDTO {

    private String name;
    private String teacher;
    private Stream<String> students;
    private Stream<Double> notes;

}
