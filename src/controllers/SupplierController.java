/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import models.Supplier;
import tools.Koneksi;
import views.SupplierListPage;

/**
 *
 * @author gilang
 */
public class SupplierController {

    private SupplierListPage view;
    private SupplierImpl supplierImpl;

    public SupplierController(SupplierListPage view) {
        this.view = view;
        this.supplierImpl = new SupplierDao();
        refreshView();
    }

    public void refreshView() {
        view.model.getDataVector().removeAllElements();
        view.model.fireTableDataChanged();
        try {
            for (Supplier supp : supplierImpl.getAll()) {
                view.model.addRow(new Object[]{supp.getId(), supp.getNama(), supp.getJoinDate()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTableRowData(int indexRow) {
        view.getTxtId().setText(view.getTblSupplier().getValueAt(view.getTblSupplier().getSelectedRow(), 0).toString());
        view.getTxtNama().setText(view.getTblSupplier().getValueAt(view.getTblSupplier().getSelectedRow(), 1).toString());
        view.getTxtJoinDate().setText(view.getTblSupplier().getValueAt(view.getTblSupplier().getSelectedRow(), 2).toString());
    }

    public void insert() {
        try {
            supplierImpl.add(view.getTxtId().getText(), view.getTxtNama().getText(), view.getTxtJoinDate().getText());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }

    public void delete() {
        try {
            supplierImpl.delete(view.getTxtId().getText());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }

    public void update() {
        try {
            supplierImpl.update(view.getTxtId().getText(), view.getTxtNama().getText(), view.getTxtJoinDate().getText());
            refreshView();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshView();
    }

}
