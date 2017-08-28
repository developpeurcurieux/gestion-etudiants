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
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;




@Entity
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="nom", length=30)
    @NotEmpty
    @Size(min=2, max=30, message="Je ne veux pas")
    private String nom;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    @NotEmpty
    @Email
    private String email;
    private String photo;
    private byte[] fichierPhoto;
    
    // pour la db
//    private byte[] photo;

    public Etudiant() {
    }
    
    public Etudiant(String nom) {
        this.nom = nom;
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

    public byte[] getFichierPhoto() {
        return fichierPhoto;
    }

    public void setFichierPhoto(byte[] fichierPhoto) {
        this.fichierPhoto = fichierPhoto;
    }
    
    
}
