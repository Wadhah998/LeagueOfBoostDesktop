/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LeagueOfBoost.services;

import LeagueOfBoost.entities.Reservationb;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface IService {
    public void ajouterReservation(Reservationb r);
      public void modifierReservation(Reservationb r);
      public void supprimerReservation(Reservationb r);
      public ObservableList<Reservationb> afficherReservation();
}
