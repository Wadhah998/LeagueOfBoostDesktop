/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost;

import LeagueOfBoost.entities.Game;
import LeagueOfBoost.entities.Team;
import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import LeagueOfBoost.services.SeviceGame;
import LeagueOfBoost.services.ServiceTeam;
import LeagueOfBoost.utils.MyDB;

/**
 *
 * @author Andrew
 */
public class LeagueOfBoost {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      SeviceGame sg = new SeviceGame();
      //ServiceTeam st = new ServiceTeam();

      Game g1 = new Game(5,"aaa","aaa",111,12);
      sg.Ajouter(g1);
      sg.AfficherById(g1);
      sg.Modifier(g1);
      sg.AfficherById(g1);
      sg.Supprimer(g1);
      //Team t1 = new Team(67,4,"Team wadhah","the best","rechpa","rechpa","rechpa","rechpa","rechpa");
      //st.AfficherById(t1);
      //st.Modifier(t1);
      //st.AfficherById(t1);


      
    }
    
}
