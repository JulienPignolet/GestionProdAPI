package univ.lorraine.GestionProdAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRestController {


    @GetMapping("/movies")
    public String coucou(){
        return "coucou";
    }

}
