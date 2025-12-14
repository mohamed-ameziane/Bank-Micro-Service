package ma.enset.banqueservice.DTOs;

public record CompteRequestDTO(
        Double solde,
        String currency,
        String type
) {}