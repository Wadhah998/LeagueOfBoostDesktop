/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeagueOfBoost.gui;


class MyData {

    private int idu;
    private String message ;
    private int date;
    
    private int id;

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