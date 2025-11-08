package dto.lists.list;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class DataSource {

    @NotNull
    public Boolean filter;

    @JsonCreator
    public DataSource(
            @JsonProperty(value = "filter", required = true) Boolean filter
    ) {
        this.filter = filter;
    }

    public DataSource() {
    }
}
