package davutcagri.schoolPortal.dto;

import davutcagri.schoolPortal.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private String name;
    private Stream<String> lessons;

}
