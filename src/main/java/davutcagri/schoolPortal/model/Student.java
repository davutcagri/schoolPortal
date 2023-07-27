package davutcagri.schoolPortal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Lesson>lessons = new ArrayList<Lesson>();
    @OneToMany(mappedBy = "student")
    private List<Note> notes = new ArrayList<Note>();

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

}
