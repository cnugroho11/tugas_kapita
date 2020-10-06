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
import java.sql.Statement;
import java.util.ArrayList;
import models.Transaction;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class TransactionDao {
    final String GET_ALL = "SELECT * FROM transaction";
    final String FIND_BY_ID = "SELECT * FROM transaction where ID=?";
    final String ADD = "INSERT INTO transaction VALUES (?,?)";
    final String UPDATE = "UPDATE transaction SET OrderDate=?, WHERE Id=?";
    final String DELETE = "DELETE FROM transaction WHERE Id=?";
    
    private Connection con;
    
    public TransactionDao(){
        this.con = Koneksi.getKoneksi();
    }
    
    public ArrayList<Transaction> getAll() throws SQLException{
        ArrayList<Transaction> transactions = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(GET_ALL);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(res.getString("ID").toString());
            transaction.setOrderDate(Date.valueOf(res.getString("OrderDate").toString()));
            transactions.add(transaction);
        }
        return transactions;
    }
    
    public Transaction findById(String id) throws SQLException{
        Transaction transaction = new Transaction();
        PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
        ResultSet res = ps.executeQuery();
        while(res.next ()){
            transaction.setId(res.getString("ID"));
            transaction.setOrderDate(Date.valueOf(res.getString("OrderDate").toString()));
            break;
        }
        return transaction;
    }
    
    public void add (Transaction transaction) throws SQLException{
        PreparedStatement ps = con.prepareStatement(ADD);
        ps.setString(1, transaction.getId());
        ps.setString(2, transaction.getOrderDate().toString());
        ps.executeUpdate();
    }
    
    public void update(Transaction transaction) throws SQLException{
        PreparedStatement ps = con.prepareStatement(UPDATE);
        ps.setString(1, transaction.getOrderDate().toString());
        ps.setString(2, transaction.getId());
        ps.executeUpdate();
    }
    
    public void delete(String id) throws SQLException, SQLException{
        PreparedStatement ps = con.prepareStatement(DELETE);
        ps.executeUpdate();
    }
    
}
