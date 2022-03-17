/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases_proyecto.Cliente;


/**
 *
 * @author Melissa
 */
public class Modulo_Cliente extends javax.swing.JFrame {

    Cliente clienteRegistrado = null;
    
    /**
     * Constructor de mi JForm.
     */
    public Modulo_Cliente() {
        initComponents();
    }
    
    /**
     * Constructor de mi JForm pero con el cliente registrado actualmente.
     * @param clienteRegistrado
     */
    public Modulo_Cliente(Cliente clienteRegistrado){
        initComponents();
        this.clienteRegistrado = clienteRegistrado;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReporteUsuarios = new javax.swing.JButton();
        jButtonGestionImagenes = new javax.swing.JButton();
        jButtonCargaMasiva = new javax.swing.JButton();
        jButtonCerrarSesion = new javax.swing.JButton();
        jButtonReporteEstructuras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonReporteUsuarios.setText("Reportes de usuario");

        jButtonGestionImagenes.setText("Control de imágenes");
        jButtonGestionImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionImagenesActionPerformed(evt);
            }
        });

        jButtonCargaMasiva.setText("Carga masiva");
        jButtonCargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargaMasivaActionPerformed(evt);
            }
        });

        jButtonCerrarSesion.setText("Cerrar sesión");

        jButtonReporteEstructuras.setText("Reporte de estructuras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonReporteEstructuras)
                    .addComponent(jButtonCerrarSesion)
                    .addComponent(jButtonCargaMasiva)
                    .addComponent(jButtonGestionImagenes)
                    .addComponent(jButtonReporteUsuarios))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButtonReporteUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonReporteEstructuras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGestionImagenes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCargaMasiva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCerrarSesion)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargaMasivaActionPerformed
        Modulo_Cliente_CargaMasiva mca = new Modulo_Cliente_CargaMasiva(this.clienteRegistrado);
        mca.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCargaMasivaActionPerformed

    private void jButtonGestionImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionImagenesActionPerformed
        Cliente_GestionImagenes cgi = new Cliente_GestionImagenes(this.clienteRegistrado);
        cgi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonGestionImagenesActionPerformed

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
            java.util.logging.Logger.getLogger(Modulo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_Cliente().setVisible(true);
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCargaMasiva;
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonGestionImagenes;
    private javax.swing.JButton jButtonReporteEstructuras;
    private javax.swing.JButton jButtonReporteUsuarios;
    // End of variables declaration//GEN-END:variables
}
