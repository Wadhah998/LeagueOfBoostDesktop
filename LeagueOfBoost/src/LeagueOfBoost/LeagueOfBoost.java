/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost;

import LeagueOfBoost.entities.ReservationC;
import LeagueOfBoost.entities.SessionC;
import LeagueOfBoost.services.ServiceReservationC;
import LeagueOfBoost.services.ServiceSessionC;
import LeagueOfBoost.utils.MyDB;
import java.util.Date;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 *
 * @author Andrew
 */
public class LeagueOfBoost {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ServiceReservationC sp = new ServiceReservationC();
      ServiceSessionC sc= new ServiceSessionC();
      
    // Ajouter une nouvelle réservation
        ReservationC r1 = new ReservationC(222, 3333, "ajoutRes");
        sp.ajouterReservation(r1);
        
        // Modifier une réservation existante
        ReservationC r2 = new ReservationC(70, 50, "ModificationRes");
        sp.modifierReservation(30, r2);
        
        // Supprimer une réservation existante
        sp.supprimerReservation(31);
        
        // Afficher toutes les réservations
         sp.afficherReservations();
        
      
      Date date1 = new Date(99, 9, 7);
      Date date2 = new Date(101, 11, 3);
      Date date3 = new Date(101, 2, 25);
      SessionC s1= new SessionC("test", "ajoutSes", 500, date1);
        sc.ajouterSessionCoaching(s1);
        SessionC s2= new SessionC("test","modificationSes",100, date2);
        sc.modifierSessionCoaching(26,s2);
        SessionC s3= new SessionC("test","modificationSes",100, date3);
        sc.modifierSessionCoaching(27,s3);
        sc.supprimerSessionCoaching(28);
        sc.afficherSessionCoach();


    
    }
    
}
