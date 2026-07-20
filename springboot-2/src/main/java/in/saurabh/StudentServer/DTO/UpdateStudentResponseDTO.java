package in.saurabh.StudentServer.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentResponseDTO {
    int id;
    String name;
    int age;
    String department;
}
