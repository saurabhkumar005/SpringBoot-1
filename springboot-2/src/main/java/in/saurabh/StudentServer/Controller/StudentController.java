package in.saurabh.StudentServer.Controller;

import in.saurabh.StudentServer.DTO.CreateStudentRequestDTO;
import in.saurabh.StudentServer.DTO.CreateStudentResponseDTO;
import in.saurabh.StudentServer.Service.StudentService;
import in.saurabh.StudentServer.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> storeStudent(@RequestBody Student student){
//        Student result = studentService.studentValidator(student);
//
//        if(result == null){
//            return ResponseEntity.status(400).body("Invalid Input. Student data Not created!");
//        }
//        return ResponseEntity.status(201).body(result);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> storeStudent(@RequestBody CreateStudentRequestDTO student){
        CreateStudentResponseDTO result = studentService.studentValidator(student);

        if(result == null){
            return ResponseEntity.status(400).body("Invalid Input. Student data Not created!");
        }
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student){
        Student res = studentService.updateStudentByID(id, student);
        if(res==null){
            return ResponseEntity.status(400).body("Invalid data, Updation Failed");
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id){
        Student res = studentService.getStudentByID(id);
        if(res==null){
            return ResponseEntity.status(400).body("Invalid ID, User Not Found!");
        }
        return ResponseEntity.status(200).body(res);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        Student res = studentService.deleteStudentByID(id);
        if(res==null){
            return ResponseEntity.status(400).body("Student deletion failed! Check ID and retry.");
        }

        return ResponseEntity.status(200).body(res);
    }




}
