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
import java.sql.Statement;
import java.util.ArrayList;
import models.TransactionItem;
import tools.Koneksi;

/**
 *
 * @author USER
 */
public class TransactionItemDao {
    final String GET_ALL = "SELECT * FROM transaction_item";
    final String FIND_BY_ID = "SELECT * FROM transaction_item where ID=?";
    final String ADD = "INSERT INTO transaction_item VALUES (?,?,?,?)";
    final String UPDATE = "UPDATE transaction_item SET Quantity=?, Transaction_Id=?, Item_Id=? WHERE Id=?";
    final String DELETE = "DELETE FROM transaction WHERE Id = ?";
    
    private Connection con;
    
    public TransactionItemDao(){
        this.con = Koneksi.getKoneksi();
    }
    
    public ArrayList<TransactionItem> getAll() throws SQLException {
        ArrayList<TransactionItem> transactionitems = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(GET_ALL);
        ResultSet res = ps.executeQuery();
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
        PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
        ps.setString(1, id);
        ResultSet res = ps.executeQuery();
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
        PreparedStatement stat = con.prepareStatement(UPDATE);
        stat.setInt(4, id);
        stat.setInt(1, quantity);
        stat.setString(2, transaction);
        stat.setString(3, item);
        stat.executeUpdate();
    }

    public void add(int id, int quantity, String transaction, String item) throws SQLException {
        PreparedStatement stat = con.prepareStatement(ADD);
        stat.setInt(1, id);
        stat.setInt(2, quantity);
        stat.setString(3, transaction);
        stat.setString(4, item);
        stat.executeUpdate();
    }

    public void delete(String id) throws SQLException, SQLException {
        PreparedStatement stat = con.prepareStatement(DELETE);
        stat.setString(1, id);
        stat.executeUpdate();
    }
}
