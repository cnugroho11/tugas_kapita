/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.TransactionItem;
import tools.Koneksi;

/**
 *
 * @author MangUjang
 */
public class TransactionItemController {

    public ArrayList<TransactionItem> getAll() throws SQLException {
        ArrayList<TransactionItem> transactionitems = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction_item";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            TransactionItem transactionitem = new TransactionItem();
            Object[] obj = new Object[5];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Quantity");
            obj[2] = res.getString("Transaction_Id");
            obj[3] = res.getString("Item_Id");
            transactionitem.setId(Integer.parseInt(obj[0].toString()));
            transactionitem.setQuantity(Integer.parseInt(obj[1].toString()));
            transactionitem.setTransaction(obj[2].toString());
            transactionitem.setItem(obj[3].toString());
            transactionitems.add(transactionitem);
        }
        return transactionitems;
    }

    public TransactionItem findById(String id) throws SQLException {
        TransactionItem transactionitem = new TransactionItem();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction_item where ID='" + id + "'";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            Object[] obj = new Object[4];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Quantity");
            obj[2] = res.getString("Transaction");
            obj[3] = res.getString("Item");
            transactionitem.setId(Integer.parseInt(obj[0].toString()));
            transactionitem.setQuantity(Integer.parseInt(obj[1].toString()));
            transactionitem.setTransaction(obj[2].toString());
            transactionitem.setItem(obj[3].toString());
            break;
        }
        return transactionitem;
    }

    public void update(int id, int quantity, String transaction, String item) throws SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement("UPDATE transaction_item SET Quantity=?, Transaction_Id=?, Item_Id=? WHERE Id=?");
        stat.setInt(4, id);
        stat.setInt(1, quantity);
        stat.setString(2, transaction);
        stat.setString(3, item);
        stat.executeUpdate();
    }

    public void add(int id, int quantity, String transaction, String item) throws SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement("INSERT INTO transaction_item VALUES (?,?,?,?)");
        stat.setInt(1, id);
        stat.setInt(2, quantity);
        stat.setString(3, transaction);
        stat.setString(4, item);
        stat.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement stat = Koneksi.con.prepareStatement("DELETE FROM transaction_item WHERE Id = ?");
        stat.setString(1, id);
        stat.executeUpdate();
    }
}
