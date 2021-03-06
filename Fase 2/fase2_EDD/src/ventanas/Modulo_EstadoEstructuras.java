/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases_proyecto.Capa;
import clases_proyecto.Cliente;
import clases_proyecto.Imagen;
import estructuras.arbolB.ArbolB;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Melissa
 */
public class Modulo_EstadoEstructuras extends javax.swing.JFrame {

    Cliente clienteRegistado;
    ArbolB<Cliente> arbolClientes = null;
    String ultimaDireccion = "";

    /**
     * Creates new form Modulo_EstadoEstructuras
     */
    public Modulo_EstadoEstructuras() {
        initComponents();
    }

    /**
     * Constructor de mi JForm
     *
     * @param clienteRegistado
     */
    public Modulo_EstadoEstructuras(Cliente clienteRegistado, ArbolB<Cliente> arbolClientes) {
        this.clienteRegistado = clienteRegistado;
        this.arbolClientes = arbolClientes;
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonRegresar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Visualizar estructura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver ??rbol de im??genes", "Ver ??rbol de capas", "Ver listado de ??lbumes", "Ver capa", "Ver imagen y ??rbol de capas" }));

        jLabel1.setText("ID de capa o imagen");

        jButtonRegresar.setText("Regresar");
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton1)))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonRegresar)
                        .addGap(150, 150, 150)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(43, 43, 43))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String rutaCarpetaCliente = "./Clientes/Cliente_" + clienteRegistado.getDPI();
        if (jComboBox1.getSelectedItem().toString().equals("Ver ??rbol de im??genes")) {
            rutaCarpetaCliente += "/Imagenes/Arbol_AVL_Imagenes/Arbol_AVL_Imagenes.png";
            ultimaDireccion = rutaCarpetaCliente;
            mostrarImagen(rutaCarpetaCliente);
        } else if (jComboBox1.getSelectedItem().toString().equals("Ver ??rbol de capas")) {
            rutaCarpetaCliente += "/Capas/Arbol_ABB_Capas/Arbol_ABB_Capas.png";
            ultimaDireccion = rutaCarpetaCliente;
            mostrarImagen(rutaCarpetaCliente);
        } else if (jComboBox1.getSelectedItem().toString().equals("Ver listado de ??lbumes")) {
            rutaCarpetaCliente += "/Albumes/Lista_Albumes.png";
            ultimaDireccion = rutaCarpetaCliente;
            mostrarImagen(rutaCarpetaCliente);
        } else if (jComboBox1.getSelectedItem().toString().equals("Ver capa")) {
            if (!jTextField1.getText().equals("")) {
                try {
                    int idCapa = Integer.valueOf(jTextField1.getText());
                    Capa capaBuscada = new Capa(idCapa);
                    capaBuscada = clienteRegistado.getArbol_CapasGenerales().getValue(capaBuscada);
                    if (capaBuscada != null) {
                        rutaCarpetaCliente += "/Capas/Imagenes_Con_Conexiones/" + String.valueOf(idCapa) + "_ConConexiones.png";
                        ultimaDireccion = rutaCarpetaCliente;
                        mostrarImagenCompacta(rutaCarpetaCliente);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una capa con el ID ingresado.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID v??lido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Se necesita que llene el campo ID.");
            }
        } else {
            if (!jTextField1.getText().equals("")) {
                try {
                    //Aqu?? ??ria la imagen del ??rbol de im??genes junto con sus capas.
                    Imagen imagenSeleccionada = new Imagen(Integer.valueOf(jTextField1.getText()));
                    imagenSeleccionada = clienteRegistado.getArbol_Imagenes().getValue(imagenSeleccionada);
                    if (imagenSeleccionada != null) {
                        rutaCarpetaCliente += "/Imagenes";
                        ultimaDireccion = rutaCarpetaCliente;
                        clienteRegistado.getArbol_Imagenes().crearFicheroDot_Arbol("ImagenConCapa", rutaCarpetaCliente, rutaCarpetaCliente, imagenSeleccionada);
                        JOptionPane.showMessageDialog(this, "Imagen generada con ??xito");
                        mostrarImagen(rutaCarpetaCliente + "/ImagenConCapa.png");
                    }else{
                        JOptionPane.showMessageDialog(this, "No existe una imagen con ese id.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID v??lido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Se necesita que llene el campo ID.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed
        Modulo_Cliente ma = new Modulo_Cliente(clienteRegistado, arbolClientes);
        ma.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonRegresarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrarImagenCompacta(ultimaDireccion);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * M??todo para mostrar la imagen en JscrollPane tal cual como es.
     *
     * @param rutaImagen
     */
    public void mostrarImagen(String rutaImagen) {
        ImageIcon imagen = new ImageIcon(rutaImagen);
        try {
            BufferedImage bimg = ImageIO.read(new File(rutaImagen));
            ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(bimg.getWidth(), bimg.getHeight(), Image.SCALE_SMOOTH));
            jScrollPane1.setViewportView(new JLabel(icono));
        } catch (Exception error) {

        }
    }

    /**
     * M??todo para mostrar la imagen en JscrollPane pero con el tama??o de este.
     *
     * @param rutaImagen
     */
    public void mostrarImagenCompacta(String rutaImagen) {
        ImageIcon imagen = new ImageIcon(rutaImagen);
        ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(jScrollPane1.getWidth(), jScrollPane1.getHeight(), Image.SCALE_SMOOTH));
        JLabel etiqueta = new JLabel(icono);
        jScrollPane1.setViewportView(etiqueta);
    }

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
            java.util.logging.Logger.getLogger(Modulo_EstadoEstructuras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_EstadoEstructuras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_EstadoEstructuras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_EstadoEstructuras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_EstadoEstructuras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
