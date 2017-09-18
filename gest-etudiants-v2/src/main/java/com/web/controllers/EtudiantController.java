/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.web.controllers;

import com.dao.EtudiantRepository;
import com.entities.Etudiant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @RequestMapping("/Index")
    public String index(Model model, 
            @RequestParam(name="page", defaultValue="0") int p) {
        
        Page<Etudiant> pageEtudiants = etudiantRepository.findAll(
                new PageRequest(p, 5));
        
        int pageCounts = pageEtudiants.getTotalPages();
        int[] pages = new int[pageCounts];
        
        for(int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        
        model.addAttribute("pages", pages);
        model.addAttribute("pageEtudiants", pageEtudiants);
        model.addAttribute("pageCourante", p);
        return "etudiants";
    }
    
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String formEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant("Martin", new Date(), "martin@gmail.com", "image1.jpg"));
        return "formEtudiants";
    }
    
}
