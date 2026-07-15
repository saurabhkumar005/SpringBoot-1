package in.saurabh.StudentServer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {



//    public Student save(Student student){
//        System.out.println("Student Information Saved!");
//        System.out.println(student);
//        return student;
//    }
}
