/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ItemDao;
import dao.ItemImpl;
import dao.SupplierDao;
import dao.SupplierImpl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Item;
import models.Supplier;
import tools.Koneksi;
import views.InventoryPage;
import views.SupplierListPage;

/**
 *
 * @author USER
 */
public class ItemController {

    private InventoryPage view;
    private ItemImpl itemImpl;
    private SupplierImpl supplierImpl;

    public ItemController(InventoryPage view) {
        this.view = view;
        this.itemImpl = new ItemDao();
        this.supplierImpl = new SupplierDao();
        refreshView();
    }

    public void refreshView() {
        view.model.getDataVector().removeAllElements();
        view.model.fireTableDataChanged();

        try {
            for (Item itm : itemImpl.getAll()) {
                view.model.addRow(new Object[]{itm.getId(), itm.getNama(), itm.getPrice(), itm.getStock(), itm.getSupplier()});
            }
            for (Supplier supplier : supplierImpl.getAll()) {
                view.getCbSupplier().addItem(supplier.getId()+" "+supplier.getNama());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTableRowData(int indexRow) {
        view.getTxtId().setText(view.getTblItem().getValueAt(view.getTblItem().getSelectedRow(), 0).toString());
        view.getTxtNama().setText(view.getTblItem().getValueAt(view.getTblItem().getSelectedRow(), 1).toString());
        view.getTxtPrice().setText(view.getTblItem().getValueAt(view.getTblItem().getSelectedRow(), 2).toString());
        view.getTxtStock().setText(view.getTblItem().getValueAt(view.getTblItem().getSelectedRow(), 3).toString());
        view.getCbSupplier().setSelectedItem(view.getTblItem().getValueAt(view.getTblItem().getSelectedRow(), 4).toString());
    }

    public void insert() {
        try {
            itemImpl.add(view.getTxtId().getText(), view.getTxtNama().getText(), Integer.parseInt(view.getTxtPrice().getText()), Integer.parseInt(view.getTxtStock().getText()), view.getCbSupplier().getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }
    
    public void caridata(){
        try {
            for (Item itm : itemImpl.getSearch(view.getTxtCari().getText())) {
                view.model.addRow(new Object[]{itm.getId(), itm.getNama(), itm.getPrice(), itm.getStock(), itm.getSupplier()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            itemImpl.delete(view.getTxtId().getText());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }

    public void update() {
        try {
            itemImpl.update(view.getTxtId().getText(), view.getTxtNama().getText(), Integer.parseInt(view.getTxtPrice().getText().toString()), Integer.parseInt(view.getTxtStock().getText().toString()), view.getCbSupplier().toString());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshView();
    }

}
