package davutcagri.schoolPortal.repository;

import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Note;
import davutcagri.schoolPortal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteByStudentAndLesson(Student student, Lesson lesson);

}
