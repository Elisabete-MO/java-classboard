package edu.ada.ClassBoard.repository;

import edu.ada.ClassBoard.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s.name FROM tb_subject s JOIN s.tb_student st WHERE st.id" +
            " = :studentId")
    List<String> findSubjectNamesByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT s.name FROM tb_subject s JOIN s.tb_teacher st WHERE st.id" +
            " = :teacherId")
    List<String> findSubjectNamesByTeacherId(@Param("teacherId") Long teacherId);
}
