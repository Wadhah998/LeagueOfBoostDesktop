/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public interface IService<T> {
    public void Ajouter(T t );

    public void Modifier(T t);
    public void Supprimer(T t);

  
}
