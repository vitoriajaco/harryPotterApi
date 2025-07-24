package vitoria.harryPotterApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hogwarts {


    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "species")
    String species;
    @JsonProperty(value = "gender")
    String gender;
    @JsonProperty(value = "wizard")
    Boolean wizard;


    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getWizard() {
        return wizard;
    }
}
