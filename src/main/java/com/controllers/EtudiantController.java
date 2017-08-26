/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controllers;

import com.dao.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @RequestMapping(value="/index")
    public String index() {
        return "etudiants";
    }
}
