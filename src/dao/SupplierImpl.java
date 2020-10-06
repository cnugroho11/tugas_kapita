/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author USER
 */
public interface SupplierImpl {
    public void getAll();
    public void findById(String id);
    public void update(String id, String nama, String joinDate);
    public void insert(String id, String nama, String joinDate);
    public void delete(String id);
}
