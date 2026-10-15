package api.models.demoqa;

public record TokenResponse(
        String token,
        String expires,
        String status,
        String result
) {
}
