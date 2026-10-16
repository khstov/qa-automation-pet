package api.models;

public record CreateProductRequest(
        String title,
        double price,
        String description,
        String category,
        String image
) {
}
