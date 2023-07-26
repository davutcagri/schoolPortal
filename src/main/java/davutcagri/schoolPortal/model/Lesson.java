package davutcagri.schoolPortal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany(mappedBy = "lessons")
    private List<Student> students = new ArrayList<Student>();
    @OneToMany(mappedBy = "lesson")
    private List<Note> notes = new ArrayList<Note>();

}
