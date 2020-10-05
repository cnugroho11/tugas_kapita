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
import models.Transaction;
import tools.Koneksi;

/**
 *
 * @author MangUjang
 */
public class TransactionController {
    public TransactionController(Koneksi konseksi){
    }
    public ArrayList<Transaction> getAll() throws SQLException{
        ArrayList<Transaction> transactions = new ArrayList<>();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            Transaction transaction = new Transaction();
            Object[] obj = new Object[2];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("OrderDate");
            transaction.setId(obj[0].toString());
            transaction.setOrderDate(Date.valueOf(obj[1].toString()));
            transactions.add(transaction);
        }
        return transactions;
    }
    
    public Transaction findById(String id) throws SQLException{
        Transaction transaction = new Transaction();
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "SELECT * FROM transaction where id="+id;
        ResultSet res = stat.executeQuery(sql);
        while(res.next ()){
            Object[ ] obj = new Object[3];
            obj[0] = res.getString("ID");
            obj[1] = res.getString("OrderDate");
            transaction.setId(obj[0].toString());
            transaction.setOrderDate(Date.valueOf(obj[1].toString()));
            break;
        }
        return transaction;
    }
    
     public void update(Transaction transaction) throws SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        stat.executeUpdate("UPDATE transaction set " 
            + "OrderDate='"      + transaction.getOrderDate() + "'"
            + "WHERE Id = '"+transaction.getId()+"'");
    }
    public void delete(String id) throws SQLException, SQLException{
        Statement stat = (Statement) Koneksi.getKoneksi().createStatement();
        String sql = "DELETE FROM transaction where Id = '"+id+"'";
        stat.executeUpdate(sql);
    }
    
}
