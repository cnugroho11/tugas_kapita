/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
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

    public ArrayList<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(query);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Supplier supplier = new Supplier();
            supplier.setId(res.getString("ID"));
            supplier.setNama(res.getString("Nama"));
            supplier.setJoinDate(Date.valueOf(res.getString("JoinDate")));
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public Supplier findById(String id) throws SQLException {
        Supplier supplier = new Supplier();
        String query = "SELECT * FROM supplier where ID='" + id + "'";
        PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(query);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            supplier.setId(res.getString("ID"));
            supplier.setNama(res.getString("Nama"));
            supplier.setJoinDate(Date.valueOf(res.getString("JoinDate")));
            break;
        }
        return supplier;
    }

    public void update(String id, String nama, String joinDate) throws SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("UPDATE supplier SET Nama=?, JoinDate=? WHERE Id=?");
        query.setString(3, id);
        query.setString(1, nama);
        query.setString(2, joinDate);
        query.executeUpdate();
    }

    public void add(String id, String nama, String joinDate) throws SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("INSERT INTO supplier VALUES (?,?,?)");
        query.setString(1, id);
        query.setString(2, nama);
        query.setString(3, joinDate);
        query.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("DELETE FROM supplier WHERE Id = ?");
        query.setString(1, id);
        query.executeUpdate();
    }
}
