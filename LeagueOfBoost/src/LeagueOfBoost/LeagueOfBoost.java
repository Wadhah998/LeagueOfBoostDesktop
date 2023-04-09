/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost;

import LeagueOfBoost.entities.Game;
import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
import LeagueOfBoost.services.SeviceGame;
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
      Game g1 = new Game(5,"aaa","aaa",111,12);
      sg.Supprimer(g1);
    }
    
}
