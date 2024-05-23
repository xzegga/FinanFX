package finanfx.frm;

/*
 * @author Ander
 */
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import finanfx.data.DatabaseConnection;
import finanfx.dao.Notas;
import finanfx.models.Nota;

public class frmNotas extends javax.swing.JPanel {

    /**
     * Creates new form frmNotas
     */
    public frmNotas() {
        initComponents();
    }

    private void clearData() {
        txtIDTransaccion.setText("");
        txtNota.setText("");
        txtIDNota.setText("");
    }

    public void saveNote() {
        try {
            int idTransaccion = Integer.parseInt(txtIDTransaccion.getText());
            String GNotas = txtNota.getText();

            Connection conn = DatabaseConnection.getConnection();

            String sql = "{call SP_CrearNotaTransaccion(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, idTransaccion);
            stmt.setString(2, GNotas);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "Nota realizada exitosamente.");
            clearData();
        } catch (Exception x) {
            JOptionPane.showMessageDialog(this, "Error al guardar la Nota: " + x.getMessage());
        }
    }

    public void updateNote() {
        try {
            int idnota = Integer.parseInt(txtIDNota.getText());
            String GNotas = txtNota.getText();

            Connection conn = DatabaseConnection.getConnection();

            String sql = "{call SP_ActualizarNotaTransaccion (?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, idnota);
            stmt.setString(2, GNotas);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "Nota actualizada exitosamente.");
            clearData();
        } catch (Exception x) {
            JOptionPane.showMessageDialog(this, "Error al editar la Nota: " + x.getMessage());
        }
    }

    public void removeNote() {
        try {
            int idnota = Integer.parseInt(txtIDNota.getText());

            Connection conn = DatabaseConnection.getConnection();

            String sql = "{call SP_EliminarNotaTransaccion (?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, idnota);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "Nota Eliminada exitosamente.");
            clearData();

        } catch (Exception x) {
            JOptionPane.showMessageDialog(this, "Error Eliminando nota.");
        }
    }

    public void searchNote() {
        String searchTransactionsIDStr = txtIDTransaccion.getText();
        if (searchTransactionsIDStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción.");
            return;
        }
        try {
            int searchTransactionID = Integer.parseInt(searchTransactionsIDStr);

            Nota[] notas = Notas.searchNotes(searchTransactionID);

            if (notas.length > 0) {
                Nota nota = notas[0];

                txtIDNota.setText(String.valueOf(nota.getID_Nota()));
                txtIDTransaccion.setText(String.valueOf(nota.getID_Transaccion()));
                txtNota.setText(nota.getNota());

                JOptionPane.showMessageDialog(this, "Nota encontrada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna nota con ese ID de transacción.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de transacción válido.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar la nota: " + ex.getMessage());
        }
    }

    public void cleanForm() {
        txtIDNota.setText("");
        txtIDTransaccion.setText("");
        txtNota.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIDNota = new javax.swing.JTextField();
        txtIDTransaccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        btnClean = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 204));

        txtIDTransaccion.setAutoscrolls(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Notas");
        jLabel1.setPreferredSize(new java.awt.Dimension(148, 32));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nota");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ID nota");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID Transaccion");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtNota.setColumns(20);
        txtNota.setRows(5);
        txtNota.setAutoscrolls(false);
        jScrollPane1.setViewportView(txtNota);
        txtNota.getAccessibleContext().setAccessibleName("");

        btnClean.setText("Limpiar");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
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
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIDTransaccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtIDNota))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClean)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDNota, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addGap(12, 12, 12)
                        .addComponent(txtIDTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnClean))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        txtIDNota.getAccessibleContext().setAccessibleName("");
        txtIDTransaccion.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        saveNote();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        updateNote();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        removeNote();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        searchNote();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnCleanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtIDNota;
    private javax.swing.JTextField txtIDTransaccion;
    private javax.swing.JTextArea txtNota;
    // End of variables declaration//GEN-END:variables
}
