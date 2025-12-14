package ma.enset.banqueservice;

import ma.enset.banqueservice.entities.Compte;
import ma.enset.banqueservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BanqueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // 1. Créer des données (GIVEN)
            Compte c1 = Compte.builder()
                    .id(1)
                    .dateCreation(new Date())
                    .solde(8000.0)
                    .currency("MAD")
                    .type("COURANT")
                    .build();

            Compte c2 = Compte.builder()
                    .id(1)
                    .dateCreation(new Date())
                    .solde(1200.0)
                    .currency("MAD")
                    .type("EPARGNE")
                    .build();

            // 2. Sauvegarder dans la base (WHEN)
            compteRepository.save(c1);
            compteRepository.save(c2);

            // 3. Afficher pour vérifier (THEN)
            System.out.println("---------------------------------");
            System.out.println("Comptes trouvés dans la base :");
            compteRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
            System.out.println("---------------------------------");
        };
    }
}
