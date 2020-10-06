/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Supplier;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class SupplierDao {
    final String GET_ALL = "SELECT * FROM supplier";
    final String FIND_BY_ID = "SELECT * FROM supplier where ID = ?";
    final String INSERT = "INSERT INTO supplier VALUES (?,?,?)";
    final String UPDATE = "UPDATE supplier SET Nama=?, JoinDate=? WHERE Id=?";
    final String DELETE = "DELETE FROM supplier WHERE Id = ?";
    
    private Connection con;
    
    public SupplierDao(){
        this.con = Koneksi.getKoneksi();
    }
    
    public ArrayList<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(GET_ALL);
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
        PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
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
        PreparedStatement stat = con.prepareStatement(UPDATE);
        stat.setString(3, id);
        stat.setString(1, nama);
        stat.setString(2, joinDate);
        stat.executeUpdate();
    }

    public void insert(String id, String nama, String joinDate) throws SQLException {
        PreparedStatement stat = con.prepareStatement(INSERT);
        stat.setString(1, id);
        stat.setString(2, nama);
        stat.setString(3, joinDate);
        stat.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement(DELETE);
        stat.setString(1, id);
        stat.executeUpdate();
    }
}
