/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import models.TransactionItem;

/**
 *
 * @author USER
 */
public interface TransactionItemImpl {
    public ArrayList<TransactionItem> getAll() throws SQLException;
    public TransactionItem findById(String id) throws SQLException;
    public void update(int id, int quantity, String transaction, String item) throws SQLException;
    public void add(int id, int quantity, String transaction, String item) throws SQLException;
    public void delete(String id) throws SQLException;
    public ArrayList<TransactionItem> getSeacrh(String id) throws SQLException;
}
