package co.ucompensar.smarttech.dto;

public record ExternalPostResponse(
        Long userId,
        Long id,
        String title,
        String body
) {
}