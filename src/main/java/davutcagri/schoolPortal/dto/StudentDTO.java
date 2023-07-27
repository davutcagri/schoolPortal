package davutcagri.schoolPortal.dto;

import davutcagri.schoolPortal.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String name;
    private Stream<String> lessons;
    private Stream<Double> notes;

}
