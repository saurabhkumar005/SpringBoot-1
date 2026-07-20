package in.saurabh.StudentServer.Service;

import in.saurabh.StudentServer.DTO.CreateStudentRequestDTO;
import in.saurabh.StudentServer.DTO.CreateStudentResponseDTO;
import in.saurabh.StudentServer.Entity.Student;
import in.saurabh.StudentServer.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

//    public Student studentValidator(Student student){
//        int id = student.getId();
//        String name = student.getName();
//        String department = student.getDepartment();
//        int age = student.getAge();
//
//        student.setCreatedAt(LocalDateTime.now());
//        student.setUpdatedAt(LocalDateTime.now());
//
//
//        if(id<0 || name==null || age<=0 || department==null ){
//            return null;
//        }
//
//        Student res = studentRepository.save(student);
//        return res;
//    }


    //using DTO
    public CreateStudentResponseDTO studentValidator(CreateStudentRequestDTO student){
        Student student1 = mapToStudent(student);
        studentRepository.save(student1);
        return mapToResponseDTO(student1);
    }

    private Student mapToStudent(CreateStudentRequestDTO student){
        Student student1 = new Student();

        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setDepartment(student.getDepartment());
        student1.setCreatedAt(LocalDateTime.now());
        student1.setUpdatedAt(LocalDateTime.now());

        return student1;

    }

    //update route

    public Student getStudentByID(int id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudentByID(int id, Student student){
        Student oldStudent = studentRepository.findById(id).orElse(null);
        if(oldStudent==null){
            return null;
        }
        oldStudent.setAge(student.getAge());
        oldStudent.setDepartment(student.getDepartment());
        oldStudent.setName(student.getName());
        oldStudent.setUpdatedAt(LocalDateTime.now());

        Student res = studentRepository.save(oldStudent);
        return res;

    }

    public Student deleteStudentByID(int id){
        Student res = studentRepository.findById(id).orElse(null);
        if(res==null){
            return null;
        }
        studentRepository.delete(res);
        return res;
    }

    private CreateStudentResponseDTO mapToResponseDTO(Student student){
        CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();

        createStudentResponseDTO.setId(student.getId());
        createStudentResponseDTO.setDepartment(student.getDepartment());
        createStudentResponseDTO.setAge(student.getAge());
        createStudentResponseDTO.setName(student.getName());

        return createStudentResponseDTO;
    }


}

