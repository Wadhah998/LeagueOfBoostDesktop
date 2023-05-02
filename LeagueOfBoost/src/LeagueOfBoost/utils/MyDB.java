//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package LeagueOfBoost.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String url = "jdbc:mysql://localhost:3306/lob";
    final String username = "root";
    final String pwd = "";
    private Connection conx;
    public static MyDB instance;

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }

        return instance;
    }

    private MyDB() {
        try {
            this.conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/lob", "root", "");
            System.out.println("Connexion établie");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public Connection getConx() {
        return this.conx;
    }
}
