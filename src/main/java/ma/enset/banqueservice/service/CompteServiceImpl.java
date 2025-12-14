package ma.enset.banqueservice.service;

import ma.enset.banqueservice.DTOs.CompteRequestDTO;
import ma.enset.banqueservice.DTOs.CompteResponseDTO;
import ma.enset.banqueservice.entities.Compte;
import ma.enset.banqueservice.mappers.CompteMapper;
import ma.enset.banqueservice.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    Random random = new Random();

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CompteMapper compteMapper;

    @Override
    public CompteResponseDTO addCompte(CompteRequestDTO compteRequestDTO) {
        // 1. Mapping DTO -> Entité
        Compte compte = Compte.builder()
                .id(random.nextInt(10000000))
                .dateCreation(new Date())
                .solde(compteRequestDTO.solde())
                .currency(compteRequestDTO.currency())
                .type(compteRequestDTO.type())
                .build();

        // 2. Sauvegarde
        Compte savedCompte = compteRepository.save(compte);

        // 3. Mapping Entité -> DTO Response
        return compteMapper.fromCompte(savedCompte);
    }

    @Override
    public List<CompteResponseDTO> getAllComptes() {
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream()
                .map(compte -> compteMapper.fromCompte(compte))
                .collect(Collectors.toList());
    }

    @Override
    public CompteResponseDTO updateCompte(Integer id, CompteRequestDTO compteRequestDTO) {
        // 1. Récupérer le compte existant
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));

        // 2. Mettre à jour les champs
        if (compteRequestDTO.solde() != null) {
            compte.setSolde(compteRequestDTO.solde());
        }
        if (compteRequestDTO.currency() != null) {
            compte.setCurrency(compteRequestDTO.currency());
        }
        if (compteRequestDTO.type() != null) {
            compte.setType(compteRequestDTO.type());
        }

        // 3. Sauvegarder les modifications
        Compte savedCompte = compteRepository.save(compte);

        return compteMapper.fromCompte(savedCompte);
    }

    @Override
    public void deleteCompte(Integer id) {
        compteRepository.deleteById(id);
    }
}