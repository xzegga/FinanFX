/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package finanfx.frm;

/**
 *
 * @author Ander
 */
import finanfx.dao.Usuarios;
import javax.swing.JOptionPane;

public class frmClave extends javax.swing.JFrame {

    /**
     * Creates new form frmClave
     */
    public frmClave() {
        initComponents();
        this.setLocationRelativeTo(null);//Para centrar el form
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCorreo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        txtCambiarClave = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtVClave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        getContentPane().add(txtCorreo);
        txtCorreo.setBounds(150, 90, 200, 22);

        jLabel1.setText("Correo electronico");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 90, 120, 16);

        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        getContentPane().add(txtClave);
        txtClave.setBounds(150, 130, 200, 22);
        getContentPane().add(txtCambiarClave);
        txtCambiarClave.setBounds(150, 170, 200, 22);

        jLabel2.setText("Nueva contraseña");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 170, 120, 16);

        jLabel3.setText("Confirmar Contraseña");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 210, 140, 22);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmar);
        btnConfirmar.setBounds(150, 250, 90, 23);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar);
        btnRegresar.setBounds(260, 250, 90, 23);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Recuperar Contraseña");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 40, 262, 33);

        txtVClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVClaveActionPerformed(evt);
            }
        });
        getContentPane().add(txtVClave);
        txtVClave.setBounds(150, 210, 200, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        
        try
        {
            Usuarios obj1 = new Usuarios();
            
            obj1.changeClave(txtCorreo.getText(),txtClave.getText(),txtCambiarClave.getText(),txtVClave.getText());
             JOptionPane.showMessageDialog(this, "Clave cambiada exitosamente");
            
        }catch(Exception x)
        {
            JOptionPane.showMessageDialog(this, "Algo salio mal");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        frmLogin login = new frmLogin();
        login.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtVClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVClaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtCambiarClave;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtVClave;
    // End of variables declaration//GEN-END:variables
}
