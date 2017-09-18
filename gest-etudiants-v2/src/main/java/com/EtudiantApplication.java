package com;

import com.dao.EtudiantRepository;
import com.entities.Etudiant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// TODO Ne pas oublier de desactiver devTools en fin de projet

@SpringBootApplication
public class EtudiantApplication implements CommandLineRunner {
    @Autowired
    EtudiantRepository etudiantRepository;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EtudiantApplication.class, args);
                
        }
        
        
    @Override
    public void run(String... strings) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        
        etudiantRepository.save(
                new Etudiant("Ahmed", df.parse("1988-11-03"), "ahmed@gmail.com", "ahmed.jpeg"));
        etudiantRepository.save(
                new Etudiant("Mohamed", df.parse("1986-05-13"), "mohamed@gmail.com", "mohamed.jpeg"));
        etudiantRepository.save(
                new Etudiant("Ibrahim", df.parse("1985-01-07"), "ibrahim@gmail.com", "ibrahim.jpeg"));
        
        Page<Etudiant> etds = etudiantRepository.chercherEtudiants("%B%", new PageRequest(0, 5));
        etds.forEach(e -> System.out.println(e.getNom()));
        
    }
    
}
