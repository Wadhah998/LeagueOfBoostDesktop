/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost;

import LeagueOfBoost.entities.User;
import LeagueOfBoost.services.ServicePersonne;
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
      /* A a1 = A.getInstance();
       A a2 = A.getInstance();
       
       
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());*/
      
      
        MyDB.createorgetInstance();
        
        User p1 =new User("Kalbousi", "Yassine", "Yassine@email.com","Yassine12","Yassine123","[ROLE_USER]");

        ServicePersonne sp = new ServicePersonne();



        sp.Ajouter(p1);
       // sp.Ajouter2(p2);

    }
    
}
