package api.models.demoqa;

import java.util.List;

public record AddBooksRequest(
        String userId,
        List<IsbnRequest> collectionOfIsbns
) {
}
