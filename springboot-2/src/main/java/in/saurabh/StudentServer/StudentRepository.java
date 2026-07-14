package in.saurabh.StudentServer;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public Student save(Student student){
        System.out.println("Student Information Saved!");
        System.out.println(student);
        return student;
    }
}
