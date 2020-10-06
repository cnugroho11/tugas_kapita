/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import models.Transaction;
import models.Transaction;
/**
 *
 * @author USER
 */
public interface TransactionImpl {
    public ArrayList<Transaction> getAll();
    public Transaction findById(String id);
    public void update(Transaction transaction);
    public void add(Transaction transaction);
    public void delete(String id);
}
