package Com.Renan.Spring.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class IndexController {

    @GetMapping(value="/*")
    public String getMethodName() {
        return "Index da aplicação";
    }
    
}