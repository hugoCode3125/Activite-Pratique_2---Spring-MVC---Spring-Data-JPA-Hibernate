package ma.enset.activite_pratique_2_spring_mvc;

import ma.enset.activite_pratique_2_spring_mvc.entities.Product;
import ma.enset.activite_pratique_2_spring_mvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActivitePratique2SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitePratique2SpringMvcApplication.class, args);
    }

    @Bean
    public  CommandLineRunner start(ProductRepository productRepository) {
        return args ->{
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(2500)
                    .quantity(3)
                    .build());
            productRepository.save(Product.builder()
                    .name("jeux video")
                    .price(500)
                    .quantity(2)
                    .build());
            productRepository.save(Product.builder()
                    .name("iphone")
                    .price(3500)
                    .quantity(1)
                    .build());

            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });

        };
    }
}
