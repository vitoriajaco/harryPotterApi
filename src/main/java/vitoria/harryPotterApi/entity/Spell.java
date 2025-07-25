package vitoria.harryPotterApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Spell {

    @JsonProperty(value="name")
    String name;

    @JsonProperty(value="description")
    String description;
}
