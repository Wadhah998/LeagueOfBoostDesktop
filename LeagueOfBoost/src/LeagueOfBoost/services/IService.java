/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.ReservationC;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Andrew
 */
public interface IService {
    public void ajouterReservation(ReservationC r);
      public void modifierReservation(ReservationC r);
      public void supprimerReservation(ReservationC r);
      public ObservableList<ReservationC> afficherReservation();

  
}
