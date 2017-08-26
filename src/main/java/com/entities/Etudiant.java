/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="nom", length=30)
    private String nom;
    
    private Date dateNaissance;
    private String email;
    private String photo;
    
    // pour la db
//    private byte[] photo;

    public Etudiant() {
    }

    public Etudiant(String nom, Date dateNaissance, String email, String photo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
}
