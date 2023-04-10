/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import LeagueOfBoost.entities.User;
import LeagueOfBoost.utils.MyDB;

/**
 *
 * @author Andrew
 */
public class  ServicePersonne  implements IService<User>{
    
    Connection con ; 
    Statement ste;
     
    
    public ServicePersonne() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }



    @Override
    public void Ajouter(User t) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `LOB`.`User` (`firstname`,`lastname`,`email`,`username`,`password`,`roles`) VALUES (?,?,?,?,?,?);");
            
            pre.setString(1, t.getFirstname());
            pre.setString(2, t.getLastname());
            pre.setString(3, t.getEmail());
            pre.setString(4,t.getUsername());
            pre.setString(5,t.getPassword());
            pre.setString(6,t.getRoles());
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Supprimer(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
