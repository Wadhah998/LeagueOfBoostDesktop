/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost;

import LeagueOfBoost.entities.Reclamation;
import LeagueOfBoost.services.ServiceReclamation;
import LeagueOfBoost.entities.Message;
import LeagueOfBoost.services.ServiceMessage;
import LeagueOfBoost.utils.MyDB;
import LeagueOfBoost.gui.leagueOfBoost;


/**
 *
 * @author Andrew
 */
public class LeagueOfBoost {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      
       ServiceReclamation sr = new ServiceReclamation();
       ServiceMessage sm =new ServiceMessage();
      //ServiceTeam st = new ServiceTeam();

      //Reclamation t1 = new Reclamation(26,3,true, 0, "bbbb", "bbbb", "bbbbbb");
      //Message m1=new Message(4,26,3,1,"CCCCCCCCCCCCCCCC");
      //sr.Ajouter(t1);
      //sr.Affichertout();
      //sr.AfficherByUser_Id(3);
      //sr.afficherReclamation();
      //sr.Supprimerrec(24);
      //System.out.println(sr.afficherReclamation());
      
      //sr.AfficherById(t1);
      //sr.Modifier(t1);
      //sr.AfficherById(t1);
      //sm.Ajouter(r1);
      
      //sm.AfficherMessages(t1);
      //sm.AfficherByreclamationId(26);
      //sm.Supprimermess(5);
      //sm.AffichermessById(m1);
      //sm.Modifier(m1);
      
        System.out.println(sr.afficherReclamationbyuser());
      
      
      
    }
    
}
    