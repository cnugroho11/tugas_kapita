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
    Koneksi koneksi;
    public ItemController(Koneksi konseksi){
        this.koneksi = koneksi;
    }
    public ArrayList<Item> getAll() throws SQLException{
        ArrayList<Item> items = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM item";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            Item item = new Item();
            Object[] obj = new Object[5];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Nama");
            obj[2] = res.getString("Price");
            obj[3] = res.getString("Stock");
            obj[4] = res.getString("Supplier_ID");
            item.setId(obj[0].toString());
            item.setNama(obj[1].toString());
            item.setPrice(Integer.parseInt(obj[2].toString()));
            item.setStock(Integer.parseInt(obj[3].toString()));
            item.setSupplier(obj[4].toString());
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
            Object[] obj = new Object[5];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Nama");
            obj[2] = res.getString("Price");
            obj[3] = res.getString("Stock");
            obj[4] = res.getString("Supplier_ID");
            item.setId(obj[0].toString());
            item.setNama(obj[1].toString());
            item.setPrice(Integer.parseInt(obj[2].toString()));
            item.setStock(Integer.parseInt(obj[3].toString()));
            item.setSupplier(obj[4].toString());
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