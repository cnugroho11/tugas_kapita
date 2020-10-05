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
    public ArrayList<Supplier> getAll() throws SQLException{
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM supplier";
        ResultSet res = stat.executeQuery(sql);
        while(res.next ()){
            Supplier supplier = new Supplier();
            supplier.setId(res.getString("ID"));
            supplier.setNama(res.getString("Nama"));
            supplier.setJoinDate(Date.valueOf(res.getString("JoinDate")));
            suppliers.add(supplier);
        }
        return suppliers;
    }
    public Supplier findById(String id) throws SQLException{
        Supplier supplier = new Supplier();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM supplier where ID='"+id+"'";
        ResultSet res = stat.executeQuery(sql);
        while(res.next ()){
            supplier.setId(res.getString("ID"));
            supplier.setNama(res.getString("Nama"));
            supplier.setJoinDate(Date.valueOf(res.getString("JoinDate")));
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
