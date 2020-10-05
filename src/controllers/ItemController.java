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
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Supplier;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class ItemController {
    public ItemController(){
    }
    public ArrayList<Item> getAll() throws SQLException{
        ArrayList<Item> items = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM item";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            Item item = new Item();
            Object[] obj = new Object[5];
            item.setId(res.getString("ID"));
            item.setNama(res.getString("Nama"));
            item.setPrice(res.getInt("Price"));
            item.setStock(res.getInt("Price"));
            item.setSupplier(res.getString("Supplier_ID"));
            items.add(item);
        }
        return items;
    }
    public Item findById(String id) throws SQLException{
        Item item = new Item();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM item where ID='"+id+"'";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            item.setId(res.getString("ID"));
            item.setNama(res.getString("Nama"));
            item.setPrice(res.getInt("Price"));
            item.setStock(res.getInt("Price"));
            item.setSupplier(res.getString("Supplier_ID"));
        }
        return item;
    }
    public void update(Item item) throws SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        stat.executeUpdate("UPDATE item set " 
            + "Nama='"       + item.getNama() + "', "
            + "Price='"      + item.getPrice() + "', "
            + "Stock='"      + item.getStock() + "',"
            + "Supplier_Id='"      + item.getSupplier() + "'"
            + "WHERE Id = '"+item.getId()+"'");
    }
    public void delete(String id) throws SQLException, SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "DELETE FROM item where Id = '"+id+"'";
        stat.executeUpdate(sql);
    }
    
}