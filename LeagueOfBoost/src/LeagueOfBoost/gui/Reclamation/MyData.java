/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui.Reclamation;

import java.util.Date;


class MyData {

    private int idu;
    private String message ;
    private int date;
    
    private int id;

    MyData(int id, int user_id, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    MyData(int id, int user_id, String message, String dateString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    MyData(int id, int user_id, String message, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdu() {
        return idu;
    }

    public String getMessage() {
        return message;
    }

    public int getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public MyData(int id,int idu, String message, int date) {
        this.idu = idu;
        this.message = message;
        this.date = date;
        this.id = id;
    }

    

   
}