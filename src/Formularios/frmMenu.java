/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Ander
 */

public class frmMenu extends javax.swing.JFrame {

    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void Apagado() throws SQLException
    {
        frmCuentas cuenta = new frmCuentas();
        cuenta.setVisible(false);
        frmPerfil perfil = new frmPerfil();
        perfil.setVisible(false);
        frmPresupuestos presupuesto = new frmPresupuestos();
        presupuesto.setVisible(false);
        frmTransacciones Transacciones = new frmTransacciones();
        Transacciones.setVisible(false);
        frmNotas note =new frmNotas();
        note.setVisible(false);
        content.removeAll();
        frmLogin Logeo = new frmLogin();
        
        if(Logeo.Control == 0)
        {
            Logeo.setVisible(true);
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnTransacciones = new javax.swing.JMenuItem();
        btnPresupuesto = new javax.swing.JMenuItem();
        btnNotas = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        btnPerfil = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Bienvenido (Nombre de usuario)");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(328, 415, 172, 16);

        content.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(content);
        content.setBounds(0, 0, 550, 540);

        jMenu5.setText("Cuentas");

        jMenuItem2.setText("Bancarias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        btnTransacciones.setText("Transacciones");
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });
        jMenu5.add(btnTransacciones);

        btnPresupuesto.setText("Presupuestos");
        btnPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresupuestoActionPerformed(evt);
            }
        });
        jMenu5.add(btnPresupuesto);

        btnNotas.setText("Notas");
        btnNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotasActionPerformed(evt);
            }
        });
        jMenu5.add(btnNotas);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Ingresos y gastos");

        jMenuItem5.setText("Presupuesto");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Ajustes");
        jMenuBar1.add(jMenu3);

        jMenu11.setText("Perfil");

        btnPerfil.setText("Ajustes del perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        jMenu11.add(btnPerfil);

        jMenuBar1.add(jMenu11);

        jMenu7.setText("Notificaciones");
        jMenuBar1.add(jMenu7);

        jMenu4.setText("Cerrar Sesión");

        jMenuItem6.setText("Salir de la aplicación");
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmTransacciones Transaccion = null;
        try
        {
            Transaccion = new frmTransacciones();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        content.add(Transaccion);
        Dimension tCont = content.getSize();
        Dimension tiFrame = Transaccion.getSize();
        Transaccion.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        Transaccion.setVisible(true); 
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPerfil Info= new frmPerfil();
        content.add(Info);
        Dimension tCont = content.getSize();
        Dimension tiFrame = Info.getSize();
        Info.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        Info.setVisible(true); 
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmCuentas Banco = new frmCuentas();
        content.add(Banco);
        Dimension tCont = content.getSize();
        Dimension tiFrame = Banco.getSize();
        Banco.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        Banco.setVisible(true); 
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotasActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmNotas note = new frmNotas();
        content.add(note);
        Dimension tCont = content.getSize();
        Dimension tiFrame = note.getSize();
        note.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        note.setVisible(true); 
    }//GEN-LAST:event_btnNotasActionPerformed

    private void btnPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresupuestoActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPresupuestos Presupuestos = new frmPresupuestos();
        content.add(Presupuestos);
        Dimension tCont = content.getSize();
        Dimension tiFrame = Presupuestos.getSize();
        Presupuestos.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        Presupuestos.setVisible(true); 
    }//GEN-LAST:event_btnPresupuestoActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try
        {
            // TODO add your handling code here:
            Apagado();
        } catch (SQLException ex)
        {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPresupuestos Presupuestos = new frmPresupuestos();
        content.add(Presupuestos);
        Dimension tCont = content.getSize();
        Dimension tiFrame = Presupuestos.getSize();
        Presupuestos.setLocation((tCont.width - tiFrame.width)/2, (tCont.height - tiFrame.height)/2);
        content.revalidate();
        Presupuestos.setVisible(true); 
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnNotas;
    private javax.swing.JMenuItem btnPerfil;
    private javax.swing.JMenuItem btnPresupuesto;
    private javax.swing.JMenuItem btnTransacciones;
    private javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    // End of variables declaration//GEN-END:variables
}
