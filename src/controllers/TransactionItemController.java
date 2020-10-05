/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

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
    public TransactionItemController(Koneksi konseksi){
    }
    public ArrayList<TransactionItem> getAll() throws SQLException{
        ArrayList<TransactionItem> transactionitems = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction_item";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            TransactionItem transactionitem = new TransactionItem();
            Object[] obj = new Object[5];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Quantity");
            obj[2] = res.getString("Transaction");
            obj[3] = res.getString("item");
            transactionitem.setId(Integer.parseInt(obj[0].toString()));
            transactionitem.setQuantity(Integer.parseInt(obj[1].toString()));
            transactionitem.setTransaction(obj[2].toString());
            transactionitem.setItem(obj[3].toString());
            transactionitems.add(transactionitem);
        }
        return transactionitems;
    }
    
    public TransactionItem findById(String id) throws SQLException{
        TransactionItem transactionitem = new TransactionItem();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction_item where ID='"+id+"'";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            Object[] obj = new Object[5];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("Quantity");
            obj[2] = res.getString("Transaction");
            obj[3] = res.getString("item");
            transactionitem.setId(Integer.parseInt(obj[0].toString()));
            transactionitem.setQuantity(Integer.parseInt(obj[1].toString()));
            transactionitem.setTransaction(obj[2].toString());
            transactionitem.setItem(obj[3].toString());
            break;
        }
        return transactionitem;
    }
    
    public void update(TransactionItem transactionitem) throws SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        stat.executeUpdate("UPDATE transaction_item set " 
            + "Quantity='"       + transactionitem.getQuantity() + "', "
            + "Transaction='"      + transactionitem.getTransaction() + "', "
            + "item='"      + transactionitem.getItem() + "',"
            + "WHERE Id = '"+transactionitem.getId()+"'");
    }
    public void delete(String id) throws SQLException, SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "DELETE FROM transaction_item where Id = '"+id+"'";
        stat.executeUpdate(sql);
    }
}
