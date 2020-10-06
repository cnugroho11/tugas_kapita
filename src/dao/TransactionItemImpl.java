/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.TransactionItem;

/**
 *
 * @author USER
 */
public interface TransactionItemImpl {
    public void getAll();
    public void findById(String id);
    public void update(int id, int quantity, String transaction, String item);
    public void add(int id, int quantity, String transaction, String item);
    public void delete(String id);
}
