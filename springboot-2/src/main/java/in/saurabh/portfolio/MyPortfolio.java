package in.saurabh.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPortfolio {

    @GetMapping("/myself")
    public String mySelf(){
        return """
                <h1>MySelf</h1>
                <p> Coder | Developer | Problem Solver | Learner </p>
                <ul>
                <li>github : www.github.com/saurabhkumar005 </li>
                <li> Linkedin: www.linkedin.com/saurabhkumar005 </li>
                </ul>
                """;
    }
}
