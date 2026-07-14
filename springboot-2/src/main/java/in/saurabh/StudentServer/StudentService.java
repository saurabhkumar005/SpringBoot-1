package in.saurabh.StudentServer;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student studentValidator(Student student){
        int id = student.getId();
        String name = student.getName();
        String department = student.getDepartment();
        int age = student.getAge();

        if(id<0 || name==null || age<=0 || department==null ){
            return null;
        }

        Student res = studentRepository.save(student);
        return res;
    }
}

