package api.models.demoqa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserResponse(
        @JsonProperty("userID") String userId,
        String username,
        List<Book> books
) {
}
