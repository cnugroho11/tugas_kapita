/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Supplier;
import tools.Koneksi;

/**
 *
 * @author gilang
 */
public class SupplierController {
    Koneksi koneksi;
    public SupplierController(Koneksi konseksi){
        this.koneksi = koneksi;
    }
    public ArrayList<Supplier> getAll() throws SQLException{
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM supplier";
        ResultSet res = stat.executeQuery(sql);
        while(res.next ()){
            Supplier supplier = new Supplier();
            Object[ ] obj = new Object[3];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Nama");
            obj[2] = res.getString("JoinDate");
            supplier.setId(obj[0].toString());
            supplier.setId(obj[1].toString());
            supplier.setJoinDate(Date.valueOf(obj[2].toString()));
        }
        return suppliers;
    }
    public Supplier findById(String id) throws SQLException{
        Supplier supplier = new Supplier();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM supplier where ID="+id;
        ResultSet res = stat.executeQuery(sql);
        while(res.next ()){
            Object[ ] obj = new Object[3];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Nama");
            obj[2] = res.getString("JoinDate");
            supplier.setId(obj[0].toString());
            supplier.setId(obj[1].toString());
            supplier.setJoinDate(Date.valueOf(obj[2].toString()));
            break;
        }
        return supplier;
    }
    public void update(Supplier supplier) throws SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        stat.executeUpdate("UPDATE supplier set " 
            + "Nama='"       + supplier.getNama() + "', "
            + "JoinDate='"      + supplier.getJoinDate() + "'"
            + "WHERE Id = '"+supplier.getId()+"'");
    }
    
    public void delete(String id) throws SQLException, SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "DELETE FROM supplier where Id = '"+id+"'";
        stat.executeUpdate(sql);
    }
}
