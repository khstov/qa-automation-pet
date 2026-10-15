package api.models.demoqa;

import java.util.List;

public record BooksResponse(
        List<Book> books
) {
}
