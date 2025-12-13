package ma.enset.banqueservice.web;

import ma.enset.banqueservice.entities.Compte;
import ma.enset.banqueservice.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
public class CompteRestController {

    Random random = new Random();

    @Autowired
    private CompteRepository compteRepository;

    @GetMapping("/comptes")
    public List<Compte> listComptes(){
        return compteRepository.findAll();
    }

    @GetMapping("/compte/{idCompte}")
    public Compte getCompte(@PathVariable Integer idCompte){
        return compteRepository.findById(idCompte).orElseThrow(() -> new RuntimeException("Compte introuvable!"));
    }

    @PostMapping("/comptes")
    public Compte save (@RequestBody Compte compte){
        if(compte.getId() == null){
            compte.setId(random.nextInt(10000000));
        }
        return compteRepository.save(compte);
    }

    @PutMapping("/compte/{idCompte}")
    public Compte modify (@PathVariable Integer idCompte, @RequestBody Compte compte){
        Compte compteExistant = compteRepository.findById(idCompte)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        if (compte.getSolde() != null) compteExistant.setSolde(compte.getSolde());
        if (compte.getDateCreation() != null) compteExistant.setDateCreation(compte.getDateCreation());
        if (compte.getType() != null) compteExistant.setType(compte.getType());
        if (compte.getCurrency() != null) compteExistant.setCurrency(compte.getCurrency());

        return compteRepository.save(compteExistant);
    }

    @DeleteMapping("/compte/{idCompte}")
    public void delete (@PathVariable Integer idCompte){
        compteRepository.deleteById(idCompte);
    }

}
