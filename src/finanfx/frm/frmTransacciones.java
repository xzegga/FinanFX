package finanfx.frm;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import finanfx.dao.Transacciones;
import finanfx.models.Transaccion;
import finanfx.models.User;
import finanfx.state.LoggedInUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ander
 */
public class frmTransacciones extends javax.swing.JPanel {
    private final frmMenu menu;
    
    User loggedUser = LoggedInUser.getInstance().getUser();
    int idUsuario;

    /**
     * Creates new form frmTransacciones
     *
     * @param menu
     * @throws java.sql.SQLException
     */
    public frmTransacciones(frmMenu menu) throws SQLException {
        this.menu = menu;
        initComponents();
        idUsuario = loggedUser.getID();
        cleanForm();
        loadData();
    }

    public final void loadData() {
        try {

            ArrayList<Transaccion> transactions = Transacciones.listTransactions(idUsuario);

            DefaultTableModel model = (DefaultTableModel) jTable_Transactions.getModel();
            model.setRowCount(0);

            for (Transaccion Transaccion : transactions) {
                Object[] row = {
                    Transaccion.getID_Transaccion(),
                    Transaccion.getCantidad(),
                    Transaccion.getFecha(),
                    Transaccion.getCategoria(),
                    Transaccion.getForma_Pago(),
                    Transaccion.getDescripcion(),};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveTransaction() {
        // Validar que los campos no estén vacíos
        if (!isValidValues()) {
            return;
        }

        try {
            Transaccion transaction = new Transaccion(
                    idUsuario,
                    Double.parseDouble(txtAmount.getText()),
                    java.sql.Date.valueOf(txtDate.getText()),
                    (String) jcbCategories.getSelectedItem(),
                    txtDescription.getText(),
                    (String) jcbPaymentMethod.getSelectedItem()
            );
            // Llamar al método saveTransaction del DAO

            String idTransactionStr = txtIDTransacction.getText();

            if (idTransactionStr.isEmpty()) {
                Transacciones.saveTransaction(transaction);
                JOptionPane.showMessageDialog(this, "Transacción guardada con éxito");
            } else {
                transaction.setID_Transaccion(Integer.parseInt(idTransactionStr));
                Transacciones.updateTransaction(transaction);
                JOptionPane.showMessageDialog(this, "Transacción actualizada con éxito");
            }

            cleanForm();
            loadData();
        } catch (SQLException ex) {
            // Manejar la excepción
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la transacción: " + ex.getMessage());
        }
    }

    public void deleteTransaction() {
        String idTransactionStr = txtIDTransacction.getText();

        if (idTransactionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la transacción a eliminar.");
            return;
        }

        try {
            int idTransaction = Integer.parseInt(idTransactionStr);

            int response = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta transacción?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                Transacciones.deleteTransactions(idTransaction);

                JOptionPane.showMessageDialog(this, "Transacción eliminada con éxito");

                cleanForm();
                loadData();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción válido.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la transacción: " + ex.getMessage());
        }
    }

    public final void cleanForm() {
        txtIDTransacction.setText("");
        txtDate.setText("");
        txtAmount.setText("");
        jcbCategories.setSelectedIndex(0);
        jcbPaymentMethod.setSelectedIndex(0);
        txtDescription.setText("");
        btnSaveoOrUpdate.setText("Guardar");
        btnDelete.setEnabled(false);
        btnNote.setEnabled(false);
    }

    public boolean isValidValues() {
        if (txtAmount.getText().isEmpty()
                || txtDate.getText().isEmpty()
                || ((String) jcbCategories.getSelectedItem()).isEmpty()
                || ((String) jcbPaymentMethod.getSelectedItem()).isEmpty()
                || txtDescription.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }

        return true;
    }

    public void loadTableToForm() {
        int index = jTable_Transactions.getSelectedRow();
        TableModel model = jTable_Transactions.getModel();

        txtIDTransacction.setText(model.getValueAt(index, 0).toString());
        txtAmount.setText(model.getValueAt(index, 1).toString());
        txtDate.setText(model.getValueAt(index, 2).toString());
        jcbCategories.setSelectedItem(model.getValueAt(index, 3).toString());
        jcbPaymentMethod.setSelectedItem(model.getValueAt(index, 4).toString());
        txtDescription.setText(model.getValueAt(index, 5).toString());

        btnSaveoOrUpdate.setText("Actualizar");
        btnDelete.setEnabled(true);        
        btnNote.setEnabled(true);
    }
    
    public void addNote(){
         String idTransactionStr = txtIDTransacction.getText();
         menu.loadNotesForm(Integer.parseInt(idTransactionStr));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSaveoOrUpdate = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        txtAmount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtIDTransacction = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jcbCategories = new javax.swing.JComboBox<>();
        jcbPaymentMethod = new javax.swing.JComboBox<>();
        txtDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Transactions = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnNote = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(255, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(540, 600));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Forma de pago");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha");

        btnSaveoOrUpdate.setText("Guardar");
        btnSaveoOrUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveoOrUpdateActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Categoria");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Monto");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Descripción");

        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtAmount.setPreferredSize(new java.awt.Dimension(64, 16));
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Transacciones");

        txtIDTransacction.setEnabled(false);
        txtIDTransacction.setPreferredSize(new java.awt.Dimension(64, 16));
        txtIDTransacction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDTransacctionActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID transaccion");

        jcbCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Alimentación", "Vivienda", "Transporte", "Entretenimiento", "Salud", "Educación", "Servicios públicos", "Ropa", "Viajes", "Ahorros e inversiones", "Otros gastos" }));
        jcbCategories.setPreferredSize(new java.awt.Dimension(64, 16));

        jcbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Efectivo", "Tarjeta", "Cheque" }));
        jcbPaymentMethod.setPreferredSize(new java.awt.Dimension(64, 16));

        txtDate.setPreferredSize(new java.awt.Dimension(64, 16));

        jLabel10.setText("yyyy-mm-dd");

        jTable_Transactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Monto", "Categoría", "Forma de Pago", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Transactions.setPreferredSize(new java.awt.Dimension(450, 250));
        jTable_Transactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TransactionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Transactions);
        if (jTable_Transactions.getColumnModel().getColumnCount() > 0) {
            jTable_Transactions.getColumnModel().getColumn(0).setResizable(false);
            jTable_Transactions.getColumnModel().getColumn(1).setResizable(false);
            jTable_Transactions.getColumnModel().getColumn(2).setResizable(false);
            jTable_Transactions.getColumnModel().getColumn(3).setResizable(false);
            jTable_Transactions.getColumnModel().getColumn(4).setResizable(false);
            jTable_Transactions.getColumnModel().getColumn(5).setResizable(false);
        }

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        btnCancel.setText("Cancelar");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        btnNote.setText("Notas");
        btnNote.setToolTipText("");
        btnNote.setEnabled(false);
        btnNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNoteMouseClicked(evt);
            }
        });
        btnNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(txtIDTransacction, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnSaveoOrUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNote))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbCategories, jcbPaymentMethod, txtAmount, txtDate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txtIDTransacction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSaveoOrUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)
                    .addComponent(btnNote))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbCategories, jcbPaymentMethod, txtAmount, txtIDTransacction});

    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveoOrUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveoOrUpdateActionPerformed
        saveTransaction();
    }//GEN-LAST:event_btnSaveoOrUpdateActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteTransaction();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtIDTransacctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDTransacctionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTransacctionActionPerformed

    private void jTable_TransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TransactionsMouseClicked
        loadTableToForm();
    }//GEN-LAST:event_jTable_TransactionsMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        cleanForm();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoteActionPerformed

    private void btnNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNoteMouseClicked
        addNote();
    }//GEN-LAST:event_btnNoteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNote;
    private javax.swing.JButton btnSaveoOrUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Transactions;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcbCategories;
    private javax.swing.JComboBox<String> jcbPaymentMethod;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtIDTransacction;
    // End of variables declaration//GEN-END:variables

}
