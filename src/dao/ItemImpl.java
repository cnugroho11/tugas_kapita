/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;
/**
 *
 * @author USER
 */
public interface ItemImpl {
    public ArrayList<Item> getAll() throws SQLException;
    public Item findById(String id) throws SQLException;
    public void update(String id, String nama, int price, int stock, String supplier) throws SQLException;
    public void add(String id, String nama, int price, int stock, String supplier) throws SQLException;
    public void delete(String id) throws SQLException;
}
