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
        String query = "SELECT * FROM transaction_item";
        ResultSet res = stat.executeQuery(query);
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
        Statement query = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction_item where ID='" + id + "'";
        ResultSet res = query.executeQuery(sql);
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
        PreparedStatement query = Koneksi.con.prepareStatement("UPDATE transaction_item SET Quantity=?, Transaction_Id=?, Item_Id=? WHERE Id=?");
        query.setInt(4, id);
        query.setInt(1, quantity);
        query.setString(2, transaction);
        query.setString(3, item);
        query.executeUpdate();
    }

    public void add(int id, int quantity, String transaction, String item) throws SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("INSERT INTO transaction_item VALUES (?,?,?,?)");
        query.setInt(1, id);
        query.setInt(2, quantity);
        query.setString(3, transaction);
        query.setString(4, item);
        query.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement query = Koneksi.con.prepareStatement("DELETE FROM transaction_item WHERE Id = ?");
        query.setString(1, id);
        query.executeUpdate();
    }
}
