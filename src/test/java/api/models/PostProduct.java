package api.models;

public record PostProduct(
        String title,
        double price,
        String description,
        String category,
        String image
){}
