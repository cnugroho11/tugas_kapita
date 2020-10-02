/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cnugr
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Koneksi {
    public static Connection con;
    public static Statement stm;
    public static Connection getKoneksi() {
        try{
            String url="jdbc:mysql://localhost/tugas_kapita";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
            stm=con.createStatement();
            System.out.println("Koneksi mantap bos");
        } catch(Exception e){
            System.out.println("Koneksi gagal bos"+e.getMessage());
        }
        return con;
    }
}
