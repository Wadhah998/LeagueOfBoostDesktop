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

import java.sql.SQLException;


/**
 *
 * @author Andrew
 */
public class LeagueOfBoost {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
      /* A a1 = A.getInstance();
       A a2 = A.getInstance();
       
       
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());*/
      
      
        MyDB.createorgetInstance();
        


        ServicePersonne sp = new ServicePersonne();



      // User p2=  sp.findUserByLogin("wadhah","$2y$13$1EVLfC.ZsK94yuO1Qw7kEeXpsW4ZWWCrKxVV6PSqKEOqf8OZL7L7");
       // sp.Ajouter2(p2);
       // User p1 = new User(46,"ww","ww","hhh","nnn","kk");
       
        System.out.println(sp.afficherUtilisateurs());
        //sp.changeToCoach(p1);
    }
    
}
