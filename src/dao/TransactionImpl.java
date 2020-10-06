/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Transaction;
import models.Transaction;
/**
 *
 * @author USER
 */
public interface TransactionImpl {
    public ArrayList<Transaction> getAll() throws SQLException;
    public Transaction findById(String id) throws SQLException;
    public void update(Transaction transaction) throws SQLException;
    public void add(Transaction transaction) throws SQLException;
    public void delete(String id) throws SQLException;
}
