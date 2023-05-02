package LeagueOfBoost;

import LeagueOfBoost.entities.Reservationb;
import LeagueOfBoost.entities.SessionBoosting;
import LeagueOfBoost.services.ServiceReservationB;
import LeagueOfBoost.services.ServiceSessionBoosting;

/**
 * Main class for the LeagueOfBoost application
 * 
 * This class provides a simple example of how to use the ServiceReservationB class
 * to add, modify, delete, and display reservations in a database.
 * 
 * @author Andrew
 */
public class LeagueOfBoost {

    public static void main(String[] args) {
        
        ServiceReservationB sp = new ServiceReservationB();
        ServiceSessionBoosting sb= new ServiceSessionBoosting();
        /*
        // Ajouter une nouvelle réservation
        Reservationb r1 = new Reservationb(69, "top", "bronze");
        sp.ajouterReservation(r1);
        
        // Modifier une réservation existante
        Reservationb r2 = new Reservationb(69, "top", "silver");
        sp.modifierReservation(1, r2);
        
        // Supprimer une réservation existante
        sp.supprimerReservation(1);
        
        // Afficher toutes les réservations
        sp.afficherReservations();*/
   /* SessionBoosting s1= new SessionBoosting(4, "description", "titre", 0,0);
    sb.ajouterSessionBoosting(s1);
    SessionBoosting s2= new SessionBoosting(4, "description", "titre", 0,0);
    sb.modifierSessionBoosting(4,s2);
    sb.supprimerSessionBoosting(13);
    }
   
    */
}
}
