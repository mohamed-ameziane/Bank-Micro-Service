package ma.enset.banqueservice.web;

import ma.enset.banqueservice.DTOs.CompteRequestDTO;
import ma.enset.banqueservice.entities.Compte;
import ma.enset.banqueservice.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class CompteGraphQLController {

    Random random = new Random();

    @Autowired
    private CompteRepository compteRepository;

    // Correspond à "accountList" dans le schéma
    @QueryMapping
    public List<Compte> accountList(){
        return compteRepository.findAll();
    }

    // Correspond à "accountById" dans le schéma
    @QueryMapping
    public Compte accountById(@Argument Integer id){
        return compteRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Compte %s introuvable", id)));
    }

    // Correspond à "addAccount" dans le schéma
    @MutationMapping
    public Compte addAccount(@Argument CompteRequestDTO compte){
        Compte c = Compte.builder()
                .id(random.nextInt(100000))
                .solde(compte.solde())
                .currency(compte.currency())
                .type(compte.type())
                .dateCreation(new Date())
                .build();
        return compteRepository.save(c);
    }

    // Correspond à "updateAccount"
    @MutationMapping
    public Compte updateAccount(@Argument Integer id, @Argument CompteRequestDTO compte){
        Compte c = compteRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Compte %s introuvable", id)));

        if(compte.solde()!=null) c.setSolde(compte.solde());
        if(compte.currency()!=null) c.setCurrency(compte.currency());
        if(compte.type()!=null) c.setType(compte.type());

        return compteRepository.save(c);
    }

    // Correspond à "deleteAccount"
    @MutationMapping
    public Integer deleteAccount(@Argument Integer id){
        compteRepository.deleteById(id);
        return id;
    }
}