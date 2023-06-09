/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.services;

import java.sql.*;
import java.util.Properties;


import LeagueOfBoost.entities.User;
import LeagueOfBoost.utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
            pre.setString(6,"[\"ROLE_USER\"]");
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(User u) {
        String sql="update user set firstname=?, lastname=?,username=?,email=? where id=? ";
        try {
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setString(1, u.getFirstname());
            ste.setString(2, u.getLastname());
            ste.setString(3, u.getUsername());
            ste.setString(4, u.getEmail());
            ste.setInt(5, u.getId());

            ste.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }
    @Override
    public void Supprimer(User u) {
        String sql = "delete from user where id=?";
        try {
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, u.getId());
            ste.executeUpdate();
            System.out.println("Utilisateur supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void SupprimerById(int t) {
    try {
        String sql = "DELETE FROM `LOB`.`User` WHERE id=?";
        PreparedStatement ste = con.prepareStatement(sql);
        ste.setInt(1, t);
        
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    
    
    public User findUserByLogin(String username, String password) {
        User u = null;

        try {
            String sql = "select * from user where username=?  and password=?";
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setString(1,username);
            ste.setString(2,password);
            ResultSet s = ste.executeQuery();
            if (s.next()) {

                u = new User(s.getInt(1),
                            s.getString("firstname"),
                        s.getString("lastname"),
                            s.getString("username"),
                        s.getString("email"),
                        s.getString("roles"),
                         s.getString("voie"),
                        s.getString("lien_opgg"),
                        s.getString("description"),
                        s.getInt("prix"),
                        s.getInt("solde"),
                        s.getBoolean("disponibility"));

                }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
        public ObservableList<User> afficherUtilisateurs() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "select * from user where roles != '[\"ROLE_ADMIN\"]' ";
            Statement ste = con.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u1 = new User(s.getInt(1),
                        s.getString("firstname"),
                        s.getString("lastname"),
                        s.getString("username"),
                        s.getString("email"),
                        s.getString("roles"));

                users.add(u1);

            }
        } catch (SQLException ex) {
            
        System.out.println("listeee::"+users);
        }
        return users;
    }
    public ObservableList<User> afficherBoostersDemands() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM user WHERE roles = '[\"ROLE_USER\"]' AND disponibility IS NOT NULL";
            Statement ste = con.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u1 = new User(s.getInt(1),
                        s.getString("firstname"),
                        s.getString("lastname"),
                        s.getString("username"),
                        s.getString("email"),
                        s.getString("roles"));

                users.add(u1);

            }
        } catch (SQLException ex) {

            System.out.println("listeee::"+users);
        }
        return users;
    }
    public ObservableList<User> afficherChoachDemands() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM user WHERE roles = '[\"ROLE_USER\"]' AND disponibility IS NULL AND voie IS NOT NULL";
            Statement ste = con.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u1 = new User(s.getInt(1),
                        s.getString("firstname"),
                        s.getString("lastname"),
                        s.getString("username"),
                        s.getString("email"),
                        s.getString("roles"));

                users.add(u1);

            }
        } catch (SQLException ex) {

            System.out.println("listeee::"+users);
        }
        return users;
    }
    public boolean userExist(String username ) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        return result.next();
    }
    public void changeToCoach(User u) {
        try {
            String sql="update user set roles='[\"ROLE_CHOACH\"]'  where id=? ";
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setInt(1, u.getId());


            ste.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }
    public void changeToBooster(User u) {
        try {
            String sql="update user set roles='[\"ROLE_BOOSTER\"]'  where id=? ";
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setInt(1, u.getId());


            ste.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }
    public void BeBooster(User u) {
        String sql="update user set prix=?, solde=?,voie=?,lien_opgg=?,description=?,disponibility=? where id=? ";
        try {
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setInt(1, u.getPrix());
            ste.setInt(2, 0);
            ste.setString(3, u.getVoie());
            ste.setString(4, u.getLien_opgg());
            ste.setString(5, u.getDescription());
            ste.setBoolean(6, true );
            ste.setInt(7, u.getId());

            ste.executeUpdate();
            System.out.println("demande succeded");
        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }
    public void BeCoach(User u) {
        String sql = "update user set prix=?, solde=?,voie=?,lien_opgg=?,description=? where id=? ";
        try {
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, u.getPrix());
            ste.setInt(2, 0);
            ste.setString(3, u.getVoie());
            ste.setString(4, u.getLien_opgg());
            ste.setString(5, u.getDescription());
            ste.setInt(6, u.getId());

            ste.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }
    public User getByID(int id) {
        User U = new User();
        try {
            String req = "Select * from user where id = '" + id+"'";
            Statement st = con.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                U.setId(RS.getInt(1));
                U.setFirstname(RS.getString(4));
                U.setLastname(RS.getString(5));
                U.setUsername(RS.getString(2));
                U.setEmail(RS.getString(6));
                U.setPassword(RS.getString(3));
                U.setRoles(RS.getString(7));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return U;

    }

    public void sendMail(User user,String message,String subject) throws SQLException {



        System.out.println(user.getEmail());

        if (user == null) {
            System.out.println("User with ID " + user.getId() + " not found!");
            return;
        } else {
        }

        final String username = "wadhah.daoud@esprit.tn";
        final String password = "223JMT6406";
        String recipientEmail = user.getEmail();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            System.out.println("Email notification sent successfully.");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public int countBoosters() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM user WHERE roles = '[\"ROLE_BOOSTER\"]'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public int countCoaches() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM user WHERE roles = '[\"ROLE_CHOACH\"]'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public int countUsers() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM user WHERE roles = '[\"ROLE_USER\"]'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }


    public ObservableList<User> afficherCoach() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "select * from user where roles = '[\"ROLE_CHOACH\"]' ";
            Statement ste = con.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u1 = new User(s.getInt("id"),s.getString("firstname"), s.getString("lastname"), s.getString("voie"), s.getInt("prix"));

                users.add(u1);

            }
        } catch (SQLException ex) {
            
        System.out.println("listeee::"+users);
        }
        return users;
    }
    public ObservableList<User> afficherBooster() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "select * from user where roles = '[\"ROLE_BOOSTER\"]' ";
            Statement ste = con.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u1 = new User(s.getInt("id"),s.getString("firstname"), s.getString("lastname"), s.getString("voie"), s.getInt("prix"));

                users.add(u1);

            }
        } catch (SQLException ex) {

            System.out.println("listeee::"+users);
        }
        return users;
    }

}
