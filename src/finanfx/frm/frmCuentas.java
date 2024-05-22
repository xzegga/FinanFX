package finanfx.frm;

import finanfx.dao.Cuentas;
import finanfx.models.Cuenta;
import finanfx.models.User;
import finanfx.state.LoggedInUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ander
 */
public class frmCuentas extends javax.swing.JPanel {

    Cuentas accountService = new Cuentas();

    /**
     * Creates new form frmCuentas
     */
    public frmCuentas() {
        initComponents();
        clearTextBox();
        loadData();
    }

    public final void loadData() {
        try {
            User loggedUser = LoggedInUser.getInstance().getUser();
            int idUsuario = loggedUser.getID();
            ArrayList<Cuenta> cuentas = accountService.getAccounntsByUserId(idUsuario);

            DefaultTableModel model = (DefaultTableModel) jTable_Accounts.getModel();
            model.setRowCount(0);

            for (Cuenta cuenta : cuentas) {
                Object[] row = {
                    cuenta.getIdCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getNumeroCuenta(),
                    cuenta.getNombreBanco(),
                    cuenta.getSaldoInicial()
                };
                model.addRow(row); // Add new row to the table
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validateFields() {

        if (txtTipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccoinar unt tipo de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("".equals(txtCuenta.getText())) {
            JOptionPane.showMessageDialog(this, "Ingresa un número de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cboBanco.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccoinar el banco para la cuenta ingresada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void updateAccount() {
        if (!validateFields()) {
            return;
        }

        User loggedUser = LoggedInUser.getInstance().getUser();
        int idUsuario = loggedUser.getID();

        String id = txtId.getText();
        int idCuenta = (id != null && id.matches("\\d+")) ? Integer.parseInt(id) : 0;

        String textSaldo = clearNonNumeric(txtSaldo.getText());
        Double saldo = Double.valueOf(textSaldo);
        String command = btnSaveoOrUpdate.getText();

        Cuenta cuenta;
        cuenta = new Cuenta(
                idCuenta,
                idUsuario,
                (String) txtTipo.getSelectedItem(),
                txtCuenta.getText(),
                (String) cboBanco.getSelectedItem(),
                saldo
        );
        try {
            if (command.equals("Actualizar")) {
                accountService.updateCuenta(cuenta, idCuenta);
                JOptionPane.showMessageDialog(this, "Cuenta actualizada con éxito");
            } else {
                accountService.createCuenta(cuenta, idUsuario);
                JOptionPane.showMessageDialog(this, "Cuenta guardada con éxito");
            }
            loadData();
            clearTextBox();
        } catch (SQLException ex) {
            Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeAccount() {
        String id = txtId.getText();
        int idCuenta = (id != null && id.matches("\\d+")) ? Integer.parseInt(id) : 0;

        if (idCuenta == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cuenta para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de querer eliminar la cuenta seleccionada?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try {
                accountService.removeAccount(idCuenta);
                JOptionPane.showMessageDialog(this, "Cuenta eliminada con éxito");
                loadData();
                clearTextBox();
            } catch (SQLException ex) {
                Logger.getLogger(frmCuentas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String clearNonNumeric(String text) {
        // Get the character entered
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private void clearTextBox() {
        txtId.setText(null);
        txtCuenta.setText(null);
        txtSaldo.setText(null);
        txtTipo.setSelectedIndex(0);
        cboBanco.setSelectedIndex(0);
        btnSaveoOrUpdate.setText("Guardar");
        btnRemove.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtCuenta = new javax.swing.JTextField();
        btnSaveoOrUpdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboBanco = new javax.swing.JComboBox<>();
        btnRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Accounts = new javax.swing.JTable();
        txtTipo = new javax.swing.JComboBox<>();
        txtSaldo = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 204));

        txtCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaActionPerformed(evt);
            }
        });

        btnSaveoOrUpdate.setText("Guardar");
        btnSaveoOrUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveoOrUpdateMouseClicked(evt);
            }
        });
        btnSaveoOrUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveoOrUpdateActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Cuentas");
        jLabel5.setPreferredSize(new java.awt.Dimension(148, 32));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("No de Cuenta");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Banco");

        jLabel4.setText("Saldo Inicial");

        cboBanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Banco de America Central", "Banco Agrícola", "Banco Cuscatlan", "Banco Hipotecario", "Banco Promerica", "Banco Azul" }));
        cboBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBancoActionPerformed(evt);
            }
        });

        btnRemove.setText("Eliminar");
        btnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveMouseClicked(evt);
            }
        });

        jLabel1.setText("Tipo de cuenta");

        btnCancel.setText("Cancelar");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel7.setText("ID Cuenta");

        jTable_Accounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Numero", "Banco", "Saldo Inicial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Accounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_AccountsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Accounts);
        if (jTable_Accounts.getColumnModel().getColumnCount() > 0) {
            jTable_Accounts.getColumnModel().getColumn(0).setResizable(false);
            jTable_Accounts.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable_Accounts.getColumnModel().getColumn(1).setResizable(false);
            jTable_Accounts.getColumnModel().getColumn(2).setResizable(false);
            jTable_Accounts.getColumnModel().getColumn(3).setResizable(false);
            jTable_Accounts.getColumnModel().getColumn(4).setResizable(false);
        }

        txtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Ahorros", "Corriente", "Tarjeta de Crédito" }));
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });

        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });
        txtSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaldoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboBanco, 0, 206, Short.MAX_VALUE)
                                    .addComponent(txtCuenta)
                                    .addComponent(txtTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSaldo))
                                .addGap(1, 1, 1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSaveoOrUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemove))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboBanco, txtCuenta});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveoOrUpdate)
                    .addComponent(btnCancel)
                    .addComponent(btnRemove))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboBanco, txtCuenta, txtId});

    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveoOrUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveoOrUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveoOrUpdateActionPerformed

    private void txtCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void cboBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboBancoActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void jTable_AccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_AccountsMouseClicked
        int index = jTable_Accounts.getSelectedRow();
        TableModel model = jTable_Accounts.getModel();

        txtId.setText(model.getValueAt(index, 0).toString());
        txtTipo.setSelectedItem(model.getValueAt(index, 1).toString());
        txtCuenta.setText(model.getValueAt(index, 2).toString());
        cboBanco.setSelectedItem(model.getValueAt(index, 3).toString());
        txtSaldo.setText(model.getValueAt(index, 4).toString());
        btnSaveoOrUpdate.setText("Actualizar");
        btnRemove.setEnabled(true);
    }//GEN-LAST:event_jTable_AccountsMouseClicked

    private void btnSaveoOrUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveoOrUpdateMouseClicked
        updateAccount();
    }//GEN-LAST:event_btnSaveoOrUpdateMouseClicked

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

    private void txtSaldoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyPressed

    }//GEN-LAST:event_txtSaldoKeyPressed

    private void txtSaldoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyReleased
    }//GEN-LAST:event_txtSaldoKeyReleased

    private void txtSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyTyped
    }//GEN-LAST:event_txtSaldoKeyTyped

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        clearTextBox();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseClicked
        removeAccount();
    }//GEN-LAST:event_btnRemoveMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSaveoOrUpdate;
    private javax.swing.JComboBox<String> cboBanco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_Accounts;
    private javax.swing.JTextField txtCuenta;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JComboBox<String> txtTipo;
    // End of variables declaration//GEN-END:variables
}
