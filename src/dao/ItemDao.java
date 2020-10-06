/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class ItemDao implements ItemImpl{
    final String GET_ALL = "SELECT * FROM item";
    final String GET_CARI = "SELECT * FROM item where id = ?";
    final String FIND_BY_ID = "SELECT * FROM item where ID = ?";
    final String ADD = "INSERT INTO item VALUES (?,?,?,?,?)";
    final String UPDATE = "UPDATE item SET Nama=?, Price=?, Stock=?, Supplier_Id=? WHERE Id=?";
    final String DELETE = "DELETE FROM item WHERE Id = ?";
    
    private Connection con;
    
    public ItemDao(){
        this.con = Koneksi.getKoneksi();
    }
    
    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(GET_ALL);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Item item = new Item();
            item.setId(res.getString("ID"));
            item.setNama(res.getString("Nama"));
            item.setPrice(res.getInt("Price"));
            item.setStock(res.getInt("Stock"));
            item.setSupplier(res.getString("Supplier_ID"));
            items.add(item);
        }
        return items;
    }

    public Item findById(String id) throws SQLException {
        Item item = new Item();
        PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            item.setId(res.getString("ID"));
            item.setNama(res.getString("Nama"));
            item.setPrice(res.getInt("Price"));
            item.setStock(res.getInt("Price"));
            item.setSupplier(res.getString("Supplier_ID"));
        }
        return item;
    }

    public void update(String id, String nama, int price, int stock, String supplier) throws SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement(UPDATE);
        stat.setString(5, id);
        stat.setString(1, nama);
        stat.setInt(2, price);
        stat.setInt(3, stock);
        stat.setString(4, supplier);
        stat.executeUpdate();
    }

    public void add(String id, String nama, int price, int stock, String supplier) throws SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement(ADD);
        stat.setString(1, id);
        stat.setString(2, nama);
        stat.setInt(3, price);
        stat.setInt(4, stock);
        stat.setString(5, supplier);
        stat.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement(DELETE);
        stat.setString(1, id);
        stat.executeUpdate();
    }

    
    public ArrayList<Item> getSearch(String id) throws SQLException {
      ArrayList<Item> items = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(GET_CARI);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Item item = new Item();
            item.setId(res.getString("ID"));
            item.setNama(res.getString("Nama"));
            item.setPrice(res.getInt("Price"));
            item.setStock(res.getInt("Stock"));
            item.setSupplier(res.getString("Supplier_ID"));
            items.add(item);
        }
        return items;
    }
}


