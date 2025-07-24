package vitoria.harryPotterApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vitoria.harryPotterApi.client.Service;
import vitoria.harryPotterApi.entity.Hogwarts;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/harrypotter")
public class Controller {

    public final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Hogwarts>todosPersonagens() throws IOException, InterruptedException {
        List<Hogwarts> response = service.pegarTodosPersonagens();
        return response;
    }

    @GetMapping("/students")
    public List<Hogwarts>allStudents() throws IOException, InterruptedException {
        List<Hogwarts> response = service.todosEstudantes();
        return response;
    }
}
