package com.isi.employemicroservice;

import com.isi.employemicroservice.client.DepartementRestClient;
import com.isi.employemicroservice.entity.Employe;
import com.isi.employemicroservice.repository.EmployeRepository;
import com.isi.employemicroservice.service.EmployeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceEmployeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEmployeApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner (EmployeRepository employeRepository, DepartementRestClient departementRestClient){
        return args -> {
            departementRestClient.allDepartements().forEach(departement -> {
                Employe employe1 = Employe.builder()
                        .nom("Kallo")
                        .prenom("Laye")
                        .email("kallo@gmail.com")
                        .adresse("Sogbe")
                        .dateNaissance(LocalDate.parse("1990-01-01"))
                        .telephone("00221786987654")
                        .departementId(departement.getIdDepartement())
                        .build();
                employeRepository.save(employe1);
            });
        };
    }

}
