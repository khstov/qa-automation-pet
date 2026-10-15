package api.models.demoqa;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserRequest(
        @JsonProperty("userName")
        String username,
        String password
) {

}
