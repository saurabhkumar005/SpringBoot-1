package in.saurabh.StudentServer.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateStudentResponseDTO {
    int id;
    String name;
    int age;
    String department;

}
