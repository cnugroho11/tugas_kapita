/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Supplier;
/**
 *
 * @author USER
 */
public interface SupplierImpl {
    public ArrayList<Supplier> getAll() throws SQLException;
    public Supplier findById(String id) throws SQLException;
    public void update(String id, String nama, String joinDate) throws SQLException;
    public void add(String id, String nama, String joinDate) throws SQLException;
    public void delete(String id) throws SQLException;
}
