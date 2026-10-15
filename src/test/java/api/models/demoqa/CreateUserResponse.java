package api.models.demoqa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateUserResponse(
        @JsonProperty("userID") String userId,
        String username,
        List<Book> books
) {
}
