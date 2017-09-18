/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.entities.Etudiant;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
    // requetes personlisés mais comprehensible par jpa car respecte la convention
    //find : select | By: where | nom: nom
    public List<Etudiant> findByNom(String n);
    public Page<Etudiant> findByNom(String n, Pageable p);
    
    // requete complexes on utilise Query
    @Query("SELECT e FROM Etudiant e WHERE e.nom like :x")
    public Page<Etudiant> chercherEtudiants(@Param("x")String mc, Pageable pageable);
    
    @Query("SELECT e From Etudiant e WHERE e.dateNaissance > :x AND e.dateNaissance < :y")
    public List<Etudiant> chercherEtudiants(@Param("x")Date d1, @Param("y")Date d2);
}
