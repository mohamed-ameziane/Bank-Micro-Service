package ma.enset.banqueservice.service;

import ma.enset.banqueservice.DTOs.CompteRequestDTO;
import ma.enset.banqueservice.DTOs.CompteResponseDTO;

import java.util.List;

public interface CompteService {
    CompteResponseDTO addCompte(CompteRequestDTO compteRequestDTO);
    CompteResponseDTO updateCompte(Integer id, CompteRequestDTO compteRequestDTO);
    void deleteCompte(Integer id);
    List<CompteResponseDTO> getAllComptes();
}