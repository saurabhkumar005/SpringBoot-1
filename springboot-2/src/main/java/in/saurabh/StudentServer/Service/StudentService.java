package in.saurabh.StudentServer.Service;

import in.saurabh.StudentServer.DTO.CreateStudentRequestDTO;
import in.saurabh.StudentServer.DTO.CreateStudentResponseDTO;
import in.saurabh.StudentServer.DTO.UpdateStudentRequestDTO;
import in.saurabh.StudentServer.DTO.UpdateStudentResponseDTO;
import in.saurabh.StudentServer.Entity.Student;
import in.saurabh.StudentServer.Exception.EmailAlredyExistException;
import in.saurabh.StudentServer.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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


    //POST - using DTO
    public CreateStudentResponseDTO studentValidator(CreateStudentRequestDTO student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new EmailAlredyExistException("Email Already Registered! Please try login.");
        }
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
        student1.setEmail(student.getEmail());

        return student1;
    }



    public Student getStudentByID(int id){
        Optional<Student> res = studentRepository.findById(id);
        return res.get();
    }

    //update route
//
//    public Student updateStudentByID(int id, Student student){
//        Student oldStudent = studentRepository.findById(id).orElse(null);
//        if(oldStudent==null){
//            return null;
//        }
//        oldStudent.setAge(student.getAge());
//        oldStudent.setDepartment(student.getDepartment());
//        oldStudent.setName(student.getName());
//        oldStudent.setUpdatedAt(LocalDateTime.now());
//
//        Student res = studentRepository.save(oldStudent);
//        return res;
//
//    }

    //Update using DTO


    public UpdateStudentResponseDTO updateStudentByID(int id, UpdateStudentRequestDTO student){
        Student oldStudent = studentRepository.findById(id).orElse(null);
        if(oldStudent==null){
            return null;
        }

        //Request mapping
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setUpdatedAt(LocalDateTime.now());

        //Save Updates to database
        studentRepository.save(oldStudent);

        //Response Creation and mapping
        UpdateStudentResponseDTO res = new UpdateStudentResponseDTO();
        res.setAge(oldStudent.getAge());
        res.setName(oldStudent.getName());
        res.setId(oldStudent.getId());
        res.setDepartment(oldStudent.getDepartment());
        res.setEmail(oldStudent.getEmail());

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
        createStudentResponseDTO.setEmail(student.getEmail());

        return createStudentResponseDTO;
    }


}

