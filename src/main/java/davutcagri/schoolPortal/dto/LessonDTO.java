package davutcagri.schoolPortal.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    private String name;
    private String teacher;
    private Double averageMark;
    private List<String> students;
    private List<String> passedStudentsName;
    private List<String> failedStudentName;
    private List<NoteDTO> notes;

}
