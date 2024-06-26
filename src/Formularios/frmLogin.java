/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;
/**
 *
 * @author Ander
 */
import finanfx.dao.Login;
import finanfx.models.User;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class frmLogin extends javax.swing.JFrame {

    User usuarios = new User();
    
    public frmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);//Para centrar el form
    
    }
    
    static int Control = 0; 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        lblCambiarClave = new javax.swing.JLabel();
        lblRegistrarCuenta = new javax.swing.JLabel();
        btnAcceder = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setMinimumSize(new java.awt.Dimension(450, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bienvenido");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 10, 170, 32);
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(130, 60, 198, 22);

        jLabel2.setText("Usuario");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 66, 50, 16);

        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        getContentPane().add(txtClave);
        txtClave.setBounds(130, 100, 198, 22);

        jLabel3.setText("Contraseña");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(39, 106, 70, 16);

        lblCambiarClave.setText("Recuperar contraseña");
        lblCambiarClave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCambiarClaveMouseClicked(evt);
            }
        });
        getContentPane().add(lblCambiarClave);
        lblCambiarClave.setBounds(175, 178, 140, 16);

        lblRegistrarCuenta.setText("¿No tienes una cuenta?");
        lblRegistrarCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarCuentaMouseClicked(evt);
            }
        });
        getContentPane().add(lblRegistrarCuenta);
        lblRegistrarCuenta.setBounds(167, 200, 140, 30);

        btnAcceder.setText("Acceder");
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });
        getContentPane().add(btnAcceder);
        btnAcceder.setBounds(130, 143, 95, 23);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(231, 143, 97, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
        // TODO add your handling code here:
        int validate;
        try
        {
            Login logeo = new Login();
            
            validate=logeo.validateUserLogin(txtUsuario.getText(),txtClave.getText());
            
            if(validate>0)
            {
                frmMenu mnu = new frmMenu();
                mnu.setVisible(true);
                this.hide();
                Control = 1;

            }else
            {
                JOptionPane.showMessageDialog(null, "Acceso Denegado!");
            }
            
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Acceso Denegado! Error=" + ex.getMessage());
        }
    }//GEN-LAST:event_btnAccederActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void lblCambiarClaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCambiarClaveMouseClicked
        // TODO add your handling code here:
        frmClave contra = new frmClave();
        contra.setVisible(true);
        this.hide();
    }//GEN-LAST:event_lblCambiarClaveMouseClicked

    private void lblRegistrarCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarCuentaMouseClicked
        // TODO add your handling code here:
        frmRegistroUsuario ruser = new frmRegistroUsuario();
        ruser.setVisible(true);
        this.hide();
    }//GEN-LAST:event_lblRegistrarCuentaMouseClicked

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCambiarClave;
    private javax.swing.JLabel lblRegistrarCuenta;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
