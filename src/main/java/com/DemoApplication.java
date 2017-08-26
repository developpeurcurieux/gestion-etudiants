package com;

import com.dao.EtudiantRepository;
import com.entities.Etudiant;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
                EtudiantRepository er = ctx.getBean(EtudiantRepository.class);
                
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                
                er.save(new Etudiant("Ahmad", df.parse("1988-11-10"), "ahmed@gmail.com", "ahmed.jpg"));
                er.save(new Etudiant("Martin", df.parse("1988-10-10"), "martin@gmail.com", "martin.jpg"));
                er.save(new Etudiant("Jamil", df.parse("1988-09-10"), "jamil@gmail.com", "jamil.jpg"));
                
                Page<Etudiant> etds = er.chercherEtudiants("%J%", new PageRequest(0, 4));
                etds.forEach(e -> System.out.println(e.getNom()));
                
        }
        
}
