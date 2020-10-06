/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.TransactionItemDao;
import dao.TransactionItemImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Transaction;
import models.TransactionItem;
import tools.Koneksi;
import views.TransactionPage;

/**
 *
 * @author MangUjang
 */
public class TransactionItemController {

    private TransactionPage view;
    private TransactionItemImpl transactionItemImpl;

    public TransactionItemController(TransactionPage view) {
        this.view = view;
        this.transactionItemImpl = new TransactionItemDao();

        refreshView();
    }
    
    public void caridata(){
        try {
            for (TransactionItem trans : transactionItemImpl.getSeacrh(view.getTxtCari().getText())) {
                view.model.addRow(new Object[]{trans.getId(), trans.getQuantity(), trans.getTransaction(), trans.getItem()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshView() {
        view.model.getDataVector().removeAllElements();
        view.model.fireTableDataChanged();
        try {
            for (TransactionItem trans : transactionItemImpl.getAll()) {
                view.model.addRow(new Object[]{trans.getId(), trans.getQuantity(), trans.getTransaction(), trans.getItem()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTableRowData(int indexRow) {
        view.getTxtId().setText(view.getTblTransaction().getValueAt(view.getTblTransaction().getSelectedRow(), 0).toString());
        view.getTxtQuantity().setText(view.getTblTransaction().getValueAt(view.getTblTransaction().getSelectedRow(), 1).toString());
        view.getCbBoxTransaction().setSelectedItem(view.getTblTransaction().getValueAt(view.getTblTransaction().getSelectedRow(), 2).toString());
        view.getCbBoxItem().setSelectedItem(view.getTblTransaction().getValueAt(view.getTblTransaction().getSelectedRow(), 2).toString());
    }

    public void insert() {
        try {
            transactionItemImpl.add(Integer.parseInt(view.getTxtId().getText()), Integer.parseInt(view.getTxtQuantity().getText()), view.getCbBoxTransaction().getSelectedItem().toString(), view.getCbBoxItem().getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }

    public void delete() {
        try {
            transactionItemImpl.delete(view.getTxtId().getText());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshView();
    }
    
    public void update() {
        try {
            transactionItemImpl.update(Integer.parseInt(view.getTxtId().getText()), Integer.parseInt(view.getTxtQuantity().toString()), view.getCbBoxTransaction().getSelectedItem().toString(), view.getCbBoxItem().getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshView();
    }
}
