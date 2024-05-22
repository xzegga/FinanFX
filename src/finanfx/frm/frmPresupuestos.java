package finanfx.frm;

import finanfx.dao.Presupuestos;
import finanfx.models.Presupuesto;
import finanfx.models.User;
import finanfx.state.LoggedInUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Ander
 */
public class frmPresupuestos extends javax.swing.JPanel {
    Presupuestos budgetService = new Presupuestos();
    
    /**
     * Creates new form frmPresupuestos
     */
    public frmPresupuestos() {
        initComponents();
        loadData();
        addPeriodListener();
    }

    public final void loadData() {
        try {
            User loggedUser = LoggedInUser.getInstance().getUser();
            int idUsuario = loggedUser.getID();
            ArrayList<Presupuesto> budgets = budgetService.listBudgetsByUserId(idUsuario);

            DefaultTableModel model = (DefaultTableModel) jTable_Budgets.getModel();
            model.setRowCount(0);

            for (Presupuesto budget : budgets) {
                Object[] row = {
                    budget.getID_Presupuesto(),
                    budget.getPeriodo(),
                    budget.getInicio(),
                    budget.getCategoria(),
                    budget.getMonto_Presupuestado()
                };
                model.addRow(row); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateBudget() {
        if (!validateFields()) {
            return;
        }

        User loggedUser = LoggedInUser.getInstance().getUser();
        int idUsuario = loggedUser.getID();

        String id = txtId.getText();
        int idBudget = (id != null && id.matches("\\d+")) ? Integer.parseInt(id) : 0;

        String textMonto = clearNonNumeric(txtMonto.getText());
        Double monto = Double.valueOf(textMonto);
        String command = btnSaveoOrUpdate.getText();

        Presupuesto budget;
        budget = new Presupuesto(
                idBudget,
                idUsuario,
                (String) cboPeriodo.getSelectedItem(),
                (String) cboStart.getSelectedItem(),
                (String) cboCategories.getSelectedItem(),
                monto
        );
        try {
            if (command.equals("Actualizar")) {
                budgetService.updateBudget(budget);
                JOptionPane.showMessageDialog(this, "Presupuesto actualizado con éxito");
            } else {
                budgetService.createBudget(budget);
                JOptionPane.showMessageDialog(this, "Presupuesto guardado con éxito");
            }
            loadData();
            clearTextBox();
        } catch (SQLException ex) {
            Logger.getLogger(frmPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        // Método para eliminar un presupuesto por su ID desde la base de datos
    private void removeBudget() {
        String id = txtId.getText();
        int idBudget = (id != null && id.matches("\\d+")) ? Integer.parseInt(id) : 0;

        if (idBudget == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un presupuesto para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de querer eliminar el presupuesto seleccionado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try {
                budgetService.deleteBudget(idBudget);
                JOptionPane.showMessageDialog(this, "Presupuesto eliminado con éxito");
                loadData();
                clearTextBox();
            } catch (SQLException ex) {
                Logger.getLogger(frmPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al eliminar el Presupuesto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public final void addPeriodListener() {
        // Escuchar cambios en cboPeriodo y llenar cboStart en consecuencia
        cboPeriodo.addActionListener(e -> getPeriodStart(cboPeriodo, cboStart));

        // Ejemplo de uso: seleccionar un periodo (por ejemplo, "Mensual") y luego imprimir los elementos de cboStart
        cboPeriodo.setSelectedIndex(0);
        getPeriodStart(cboPeriodo, cboStart);
        System.out.println("Elementos de cboStart para Mensual: " + cboStart.getItemCount());
    }

    public void getPeriodStart(JComboBox<String> cboPeriodo, JComboBox<String> cboStart) {
        cboStart.removeAllItems(); // Limpiar los elementos existentes en cboStart

        String periodoSeleccionado = cboPeriodo.getSelectedItem().toString(); // Obtener el periodo seleccionado

        // Llenar cboStart según el periodo seleccionado
        switch (periodoSeleccionado) {
            case "Mensual", "Quincenal" -> {
                for (int i = 1; i <= 30; i++) {
                    cboStart.addItem(String.valueOf(i)); // Agregar números del 1 al 30
                }
            }
            case "Semanal" -> {
                String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
                for (String dia : diasSemana) {
                    cboStart.addItem(dia); // Agregar días de la semana
                }
            }
            default -> {
            }
        }
        // Si el periodo seleccionado no coincide con ninguno de los casos anteriores, no se agrega nada a cboStart
    }

    public boolean validateFields() {
        if (cboPeriodo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un periodo específico", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cboCategories.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una categoría", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("".equals(txtMonto.getText())) {
            JOptionPane.showMessageDialog(this, "Ingresa un monto presupuestado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
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
        txtMonto.setText(null);
        cboPeriodo.setSelectedIndex(0);
        cboCategories.setSelectedIndex(0);
        btnSaveoOrUpdate.setText("Guardar");
        btnRemove.setEnabled(false);        
        addPeriodListener();
    }
        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSaveoOrUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        cboCategories = new javax.swing.JComboBox<>();
        txtMonto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Budgets = new javax.swing.JTable();
        cboPeriodo = new javax.swing.JComboBox<>();
        cboStart = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Presupuestos");
        jLabel2.setPreferredSize(new java.awt.Dimension(148, 32));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Periodo");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Categoría");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Monto presupuestado");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ID Presupuesto");

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

        btnCancel.setText("Cancelar");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        btnRemove.setText("Eliminar");
        btnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveMouseClicked(evt);
            }
        });

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        cboCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Alimentación", "Vivienda", "Transporte", "Entretenimiento", "Salud", "Educación", "Servicios públicos", "Ropa", "Viajes", "Ahorros e inversiones", "Otros gastos" }));
        cboCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCategoriesActionPerformed(evt);
            }
        });

        jTable_Budgets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Periodo", "Inicio", "Categoría", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Budgets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BudgetsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Budgets);
        if (jTable_Budgets.getColumnModel().getColumnCount() > 0) {
            jTable_Budgets.getColumnModel().getColumn(1).setResizable(false);
            jTable_Budgets.getColumnModel().getColumn(2).setResizable(false);
            jTable_Budgets.getColumnModel().getColumn(3).setResizable(false);
            jTable_Budgets.getColumnModel().getColumn(4).setResizable(false);
        }

        cboPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Seleccione una opción ---", "Semanal", "Quincenal", "Mensual", " " }));
        cboPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPeriodoActionPerformed(evt);
            }
        });

        cboStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStartActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Día o fecha de inicio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboStart, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnSaveoOrUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove)))
                .addGap(68, 68, 68))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboCategories, txtMonto});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboStart, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveoOrUpdate)
                    .addComponent(btnCancel)
                    .addComponent(btnRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveoOrUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveoOrUpdateMouseClicked
        updateBudget();
    }//GEN-LAST:event_btnSaveoOrUpdateMouseClicked

    private void btnSaveoOrUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveoOrUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveoOrUpdateActionPerformed

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        clearTextBox();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseClicked
        removeBudget();
    }//GEN-LAST:event_btnRemoveMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void cboCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCategoriesActionPerformed

    private void jTable_BudgetsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BudgetsMouseClicked
        int index = jTable_Budgets.getSelectedRow();
        TableModel model = jTable_Budgets.getModel();

        txtId.setText(model.getValueAt(index, 0).toString());
        cboPeriodo.setSelectedItem(model.getValueAt(index, 1).toString());
        cboStart.setSelectedItem(model.getValueAt(index, 2).toString());
        cboCategories.setSelectedItem(model.getValueAt(index, 3).toString());
        txtMonto.setText(model.getValueAt(index, 4).toString());

        btnSaveoOrUpdate.setText("Actualizar");
        btnRemove.setEnabled(true);
        
    }//GEN-LAST:event_jTable_BudgetsMouseClicked

    private void cboPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPeriodoActionPerformed

    private void cboStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboStartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSaveoOrUpdate;
    private javax.swing.JComboBox<String> cboCategories;
    private javax.swing.JComboBox<String> cboPeriodo;
    private javax.swing.JComboBox<String> cboStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Budgets;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
