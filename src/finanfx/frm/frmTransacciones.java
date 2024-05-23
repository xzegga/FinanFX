package finanfx.frm;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import finanfx.dao.Transacciones;
import finanfx.models.Transaccion;

/**
 *
 * @author Ander
 */
public class frmTransacciones extends javax.swing.JPanel {

    /**
     * Creates new form frmTransacciones
     *
     * @throws java.sql.SQLException
     */
    public frmTransacciones() throws SQLException {
        initComponents();
    }

    private void clearData() {
        txtIDTransacction.setText("");
        txtIDUser.setText("");
        jcbTransactionType.setSelectedIndex(0);
        txtDate.setText("");
        txtAmount.setText("");
        jcbCategories.setSelectedIndex(0);
        jcbPaymentMethod.setSelectedIndex(0);
        txtDescription.setText("");
    }

    public void searchTransaction() {
        String searchTransactionIDStr = txtIDTransacction.getText();
        if (searchTransactionIDStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción.");
            return;
        }
        try {
            int searchTransactionID = Integer.parseInt(searchTransactionIDStr);

            Transaccion[] transactions = Transacciones.listTransactions(searchTransactionID);

            if (transactions.length > 0) {

                Transaccion transaction = transactions[0];

                txtIDTransacction.setText(String.valueOf(transaction.getID_Transaccion()));
                txtIDUser.setText(String.valueOf(transaction.getID_Usuario()));
                jcbTransactionType.setSelectedItem(transaction.getTipo_Transaccion());
                txtDate.setText(transaction.getFecha().toString());
                txtAmount.setText(String.valueOf(transaction.getCantidad()));
                jcbCategories.setSelectedItem(transaction.getCategoria());
                txtDescription.setText(transaction.getDescripcion());
                jcbPaymentMethod.setSelectedItem(transaction.getForma_Pago());

                JOptionPane.showMessageDialog(this, "Transacción encontrada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna transacción con ese ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción válido.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar la transacción: " + ex.getMessage());
        }
    }

    public void saveTransaction() {
        String idUserStr = txtIDUser.getText();
        String transactionType = (String) jcbTransactionType.getSelectedItem();
        String amountStr = txtAmount.getText();
        String dateStr = txtDate.getText();
        String category = (String) jcbCategories.getSelectedItem();
        String paymentMethod = (String) jcbPaymentMethod.getSelectedItem();
        String description = txtDescription.getText();

        // Validar que los campos no estén vacíos
        if (idUserStr.isEmpty() || transactionType.isEmpty() || amountStr.isEmpty() || dateStr.isEmpty() || category.isEmpty() || paymentMethod.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try {
            java.sql.Date Fecha = null;
            // Crear un objeto Transaccion con los datos del formulario
            Transaccion transaction = new Transaccion(WIDTH, transactionType, WIDTH, Fecha, category, description, description);
            transaction.setID_Usuario(Integer.parseInt(idUserStr));
            transaction.setTipo_Transaccion(transactionType);
            transaction.setCantidad(Double.parseDouble(amountStr));
            transaction.setFecha(java.sql.Date.valueOf(dateStr));
            transaction.setCategoria(category);
            transaction.setDescripcion(description);
            transaction.setForma_Pago(paymentMethod);

            // Llamar al método saveTransaction del DAO
            Transacciones.saveTransaction(transaction);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Transacción guardada con éxito");

            // Limpiar los campos del formulario
            txtIDUser.setText("");
            jcbTransactionType.setSelectedIndex(-1); // Seleccionar nada
            txtAmount.setText("");
            txtDate.setText("");
            jcbCategories.setSelectedIndex(-1); // Seleccionar nada
            txtDescription.setText("");
            jcbPaymentMethod.setSelectedIndex(-1); // Seleccionar nada

        } catch (SQLException ex) {
            // Manejar la excepción
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la transacción: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            // Manejar excepción si el formato de algún número es incorrecto
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        } catch (IllegalArgumentException ex) {
            // Manejar excepción si la fecha no es válida
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha válida en formato YYYY-MM-DD.");
        }
    }

    public void updateTransaction() {

        String idUserStr = txtIDUser.getText();
        String transactionType = (String) jcbTransactionType.getSelectedItem();
        String amountStr = txtAmount.getText();
        String dateStr = txtDate.getText();
        String category = (String) jcbCategories.getSelectedItem();
        String paymentMethod = (String) jcbPaymentMethod.getSelectedItem();
        String description = txtDescription.getText();

        if (idUserStr.isEmpty() || transactionType.isEmpty() || amountStr.isEmpty() || dateStr.isEmpty() || category.isEmpty() || paymentMethod.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try {
            java.sql.Date Fecha = null;
            Transaccion transaction = new Transaccion(WIDTH, transactionType, WIDTH, Fecha, category, description, description);
            transaction.setID_Usuario(Integer.parseInt(idUserStr));
            transaction.setTipo_Transaccion(transactionType);
            transaction.setCantidad(Double.parseDouble(amountStr));
            transaction.setFecha(java.sql.Date.valueOf(dateStr));
            transaction.setDescripcion(description);
            transaction.setForma_Pago(paymentMethod);

            Transacciones.updateTransaction(transaction);

            JOptionPane.showMessageDialog(this, "Transacción actualizada con éxito");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la transacción: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha válida en formato YYYY-MM-DD.");
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

                clearData();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción válido.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la transacción: " + ex.getMessage());
        }
    }

    public void cleanForm() {
        txtIDTransacction.setText("");
        txtIDUser.setText("");
        jcbTransactionType.setSelectedIndex(0);
        txtDate.setText("");
        txtAmount.setText("");
        jcbCategories.setSelectedIndex(0);
        jcbPaymentMethod.setSelectedIndex(0);
        txtDescription.setText("");
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
        txtDescription = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtAmount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtIDTransacction = new javax.swing.JTextField();
        txtIDUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcbTransactionType = new javax.swing.JComboBox<>();
        jcbCategories = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        jcbPaymentMethod = new javax.swing.JComboBox<>();
        txtDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(255, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(540, 470));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Forma de pago");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha");

        txtDescription.setMinimumSize(new java.awt.Dimension(450, 450));
        txtDescription.setPreferredSize(new java.awt.Dimension(64, 16));
        txtDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionActionPerformed(evt);
            }
        });

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Categoria");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tipo de transaccion");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Cantidad");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Descripción");

        btnUpdate.setText("Editar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Eliminar");
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

        txtIDTransacction.setPreferredSize(new java.awt.Dimension(64, 16));
        txtIDTransacction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDTransacctionActionPerformed(evt);
            }
        });

        txtIDUser.setPreferredSize(new java.awt.Dimension(64, 16));
        txtIDUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDUserActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID transaccion");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("ID usuario");

        jcbTransactionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Deposito Bancario", "Transferencia Bancaria", "Pago de servicios", "Cobro de salario", "Transferencia 365", "Recarga de celular", "Otro" }));
        jcbTransactionType.setPreferredSize(new java.awt.Dimension(64, 16));
        jcbTransactionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTransactionTypeActionPerformed(evt);
            }
        });

        jcbCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Alimentación", "Vivienda", "Transporte", "Entretenimiento", "Salud", "Educación", "Servicios públicos", "Ropa", "Viajes", "Ahorros e inversiones", "Otros gastos" }));
        jcbCategories.setPreferredSize(new java.awt.Dimension(64, 16));

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClean.setText("Limpiar");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        jcbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Efectivo", "Tarjeta", "Cheque" }));
        jcbPaymentMethod.setPreferredSize(new java.awt.Dimension(64, 16));

        txtDate.setPreferredSize(new java.awt.Dimension(64, 16));

        jLabel10.setText("yyyy-mm-dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(jcbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIDTransacction, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnClean)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbCategories, jcbPaymentMethod, jcbTransactionType, txtAmount, txtDate, txtIDTransacction, txtIDUser});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txtIDTransacction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txtIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClean))
                .addGap(15, 15, 15))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbCategories, jcbPaymentMethod, jcbTransactionType, txtAmount, txtIDTransacction, txtIDUser});

    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveTransaction();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateTransaction();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescriptionActionPerformed

    private void txtIDUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDUserActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchTransaction();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteTransaction();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jcbTransactionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTransactionTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTransactionTypeActionPerformed

    private void txtIDTransacctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDTransacctionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTransacctionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcbCategories;
    private javax.swing.JComboBox<String> jcbPaymentMethod;
    private javax.swing.JComboBox<String> jcbTransactionType;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtIDTransacction;
    private javax.swing.JTextField txtIDUser;
    // End of variables declaration//GEN-END:variables

    private static class dateChooser {

        public dateChooser() {
        }
    }
}
