package davutcagri.schoolPortal.repository;

import davutcagri.schoolPortal.model.Lesson;
import davutcagri.schoolPortal.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findLessonByNameAndTeacher(String name, Teacher teacher);

}
