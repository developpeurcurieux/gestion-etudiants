/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controllers;

import com.dao.EtudiantRepository;
import com.entities.Etudiant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.validation.Valid;
import org.apache.commons.compress.utils.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController { 
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Value("${dir.images}")
    private String imageDir;
    
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
    
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String formEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "FormEtudiant";
    }
    
    @RequestMapping(value="/saveEtudiant", method=RequestMethod.POST)
    public String save(@Valid Etudiant etudiant, 
            BindingResult bindingResult,
            @RequestParam(name="picture")MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()) {
            return "FormEtudiant";
        }
        
        if( !(file.isEmpty())) {
            etudiant.setPhoto(file.getOriginalFilename());    
        }
        
      
        
        if( !(file.isEmpty() )) {
            etudiant.setPhoto(file.getOriginalFilename());
             etudiantRepository.save(etudiant);
            file.transferTo(new File(imageDir + etudiant.getId()));
            
        }
        return "redirect:index";
    }
    
    @RequestMapping(value="/getPhoto", produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody() // on ne renvoit pas le nom de la vue, mais une photo donnees binaires
    public byte[] getPhoto(Long id) throws Exception {
        File f = new File(imageDir + id);
        return IOUtils.toByteArray(new FileInputStream(f));
    }
    
    
    @RequestMapping(value="/supprimer")
    public String supprimer(@RequestParam("id")Long id) {
        etudiantRepository.delete(id);
        return"redirect:index";
    }
    
    @RequestMapping(value="/edit")
    public String edit(Model model, @RequestParam("id")Long id) {
        Etudiant et = etudiantRepository.getOne(id);
        model.addAttribute("etudiant", et);
        return "EditEtudiant";
    }
    
    @RequestMapping(value="/updateEtudiant", method=RequestMethod.POST)
    public String update(@Valid Etudiant etudiant, 
            BindingResult bindingResult,
            @RequestParam(name="picture")MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()) {
            return "EditEtudiant";
        }
        
        if( !(file.isEmpty())) {
            etudiant.setPhoto(file.getOriginalFilename());    
        }
        
        etudiantRepository.save(etudiant);
        
        if( !(file.isEmpty() )) {
            etudiant.setPhoto(file.getOriginalFilename());
            etudiantRepository.save(etudiant);
            file.transferTo(new File(imageDir + etudiant.getId()));
            
        }
        return "redirect:index";
    }
}
