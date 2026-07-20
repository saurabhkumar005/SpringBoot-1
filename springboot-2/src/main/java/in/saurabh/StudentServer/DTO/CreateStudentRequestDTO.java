package in.saurabh.StudentServer.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateStudentRequestDTO {

    String name;
    int age;
    String department;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
