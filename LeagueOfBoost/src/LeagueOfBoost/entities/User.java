/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.entities;

import javafx.scene.control.Button;

/**
 *
 * @author Andrew
 */
public class User {
    int id,prix,solde;
    String firstname ,lastname,username,password,voie,lien_opgg,description,reset_token,email,roles;
    public Button supprimer;

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    boolean disponibility;
    public User() {
    }

    public User( String firstname, String lastname, String username, String email,String password ) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User(int id, String firstname, String lastname, String username, String email,String roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public User(int prix, int solde, String firstname, String lastname, String username, String password, String voie, String lien_opgg, String description, String reset_token, String email, String roles, boolean disponibility) {
        this.prix = prix;
        this.solde = solde;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.voie = voie;
        this.lien_opgg = lien_opgg;
        this.description = description;
        this.reset_token = reset_token;
        this.email = email;
        this.roles = roles;
        this.disponibility = disponibility;
    }

    public User(int id, int prix, int solde, String firstname, String lastname, String username, String password, String voie, String lien_opgg, String description, String reset_token, String email, String roles, boolean disponibility) {
        this.id = id;
        this.prix = prix;
        this.solde = solde;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.voie = voie;
        this.lien_opgg = lien_opgg;
        this.description = description;
        this.reset_token = reset_token;
        this.email = email;
        this.roles = roles;
        this.disponibility = disponibility;
    }

    public User( int prix, int solde, String voie, String lien_opgg, String description, boolean disponibility) {

        this.prix = prix;
        this.solde = solde;
        this.voie = voie;
        this.lien_opgg = lien_opgg;
        this.description = description;
        this.disponibility = disponibility;
    }

    public User( int prix, int solde, String voie, String lien_opgg, String description) {

        this.prix = prix;
        this.solde = solde;
        this.voie = voie;
        this.lien_opgg = lien_opgg;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", prix=" + prix +
                ", solde=" + solde +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", voie='" + voie + '\'' +
                ", lien_opgg='" + lien_opgg + '\'' +
                ", description='" + description + '\'' +
                ", reset_token='" + reset_token + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", disponibility=" + disponibility +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getLien_opgg() {
        return lien_opgg;
    }

    public void setLien_opgg(String lien_opgg) {
        this.lien_opgg = lien_opgg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }


}
