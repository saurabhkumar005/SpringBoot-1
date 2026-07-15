package in.saurabh.StudentServer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> storeStudent(@RequestBody Student student){
        Student result = studentService.studentValidator(student);

        if(result == null){
            return ResponseEntity.status(400).body(result);
        }
        return ResponseEntity.status(201).body(result);
 }
}
