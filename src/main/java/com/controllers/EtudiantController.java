/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controllers;

import com.dao.EtudiantRepository;
import com.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @RequestMapping(value="/index")
    public String index(Model model, 
            @RequestParam(name="page", defaultValue="0")int p, 
            @RequestParam(name="motCle", defaultValue="")String mc) {
        
        Page<Etudiant> pageEtudiants = etudiantRepository.chercherEtudiants("%"+mc+"%", new PageRequest(p, 5));
        int pagesCount = pageEtudiants.getTotalPages();
        int[] pages = new int[pagesCount];
        
        for(int i = 0; i < pagesCount; i++) {
            pages[i] = i;
        }
        
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("pageEtudiants", pageEtudiants);
        model.addAttribute("motCle", mc);
        return "etudiants";
    }
}
