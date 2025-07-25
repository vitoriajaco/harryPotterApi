package vitoria.harryPotterApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vitoria.harryPotterApi.client.HarryPotterApiClient;
import vitoria.harryPotterApi.entity.Hogwarts;
import vitoria.harryPotterApi.entity.Spell;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/harrypotter")
public class Controller {

    public final HarryPotterApiClient harryPotterApiClient;

    public Controller(HarryPotterApiClient harryPotterApiClient) {
        this.harryPotterApiClient = harryPotterApiClient;
    }

    @GetMapping("/all")
    public List<Hogwarts>todosPersonagens() throws IOException, InterruptedException {
        List<Hogwarts> response = harryPotterApiClient.pegarTodosPersonagens();
        return response;
    }

    @GetMapping("/students")
    public List<Hogwarts>allStudents() throws IOException, InterruptedException {
        List<Hogwarts> response = harryPotterApiClient.todosEstudantes();
        return response;
    }

    @GetMapping("/spells")
    public List<Spell>allSpells() throws IOException, InterruptedException {
        List<Spell> response = harryPotterApiClient.allSpells();
        return response;
    }

    @GetMapping("/staff")
    public List<Hogwarts> allStaff(){
        List<Hogwarts> response = harryPotterApiClient.allStaff();
        return response;
    }
}
