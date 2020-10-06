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
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Supplier;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class ItemController {

    public ItemController() {
    }

    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item";
        PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(query);
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
        String sql = "SELECT * FROM item where ID='" + id + "'";
        PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
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
        PreparedStatement query = Koneksi.con.prepareStatement("UPDATE item SET Nama=?, Price=?, Stock=?, Supplier_Id=? WHERE Id=?");
        query.setString(5, id);
        query.setString(1, nama);
        query.setInt(2, price);
        query.setInt(3, stock);
        query.setString(4, supplier);
        query.executeUpdate();
    }

    public void add(String id, String nama, int price, int stock, String supplier) throws SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");
        query.setString(1, id);
        query.setString(2, nama);
        query.setInt(3, price);
        query.setInt(4, stock);
        query.setString(5, supplier);
        query.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("DELETE FROM item WHERE Id = ?");
        query.setString(1, id);
        query.executeUpdate();
    }

}
