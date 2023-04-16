/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;


import LeagueOfBoost.entities.User;

import java.util.ArrayList;

//import java.util.ArrayList;
//import java.util.List;



/**
 *
 * @author Andrew
 */
public interface IService<T> {
    public void Ajouter(T t );
    public void Modifier(T t);

    //void Modifier(User u);

    public void Supprimer(T t);
    //public void Afficher();
    //public void AfficherById(T t);


    //void Supprimer(User u);
}
