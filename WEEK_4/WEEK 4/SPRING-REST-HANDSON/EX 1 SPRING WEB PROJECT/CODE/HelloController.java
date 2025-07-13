package cognizant.com.spring_learn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to Spring Learn!";
    }
}
