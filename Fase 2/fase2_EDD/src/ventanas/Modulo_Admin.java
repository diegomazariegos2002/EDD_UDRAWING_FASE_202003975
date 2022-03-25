/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

//Librerías para leer JSON
import app.FuncionesJSON;
import app.Funciones_Ficheros;
import clases_proyecto.Cliente;
import java.io.FileReader;
import com.google.gson.*;
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
public class Modulo_Admin extends javax.swing.JFrame {

    /*=========================DECLARACIONES DE VARIABLES===============================*/
    Funciones_Ficheros fFicheros = new Funciones_Ficheros();
    FuncionesJSON fJSON = new FuncionesJSON();
    public ArbolB<Cliente> arbolClientes;

    /**
     * Creates new form Modulo_Admin
     */
    public Modulo_Admin() {
        initComponents();
    }

    /**
     * Constructor del Modulo_Admin
     *
     * @param arbolClientes
     */
    public Modulo_Admin(ArbolB<Cliente> arbolClientes) {
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

        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButtonCargaMasiva = new javax.swing.JButton();
        jButtonInsertarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDPI = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonModificarCliente = new javax.swing.JButton();
        jButtonEliminarCliente = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldDPI2 = new javax.swing.JTextField();
        jTextFieldPassword2 = new javax.swing.JTextField();
        jTextFieldNombre2 = new javax.swing.JTextField();
        jButtonBuscarCliente = new javax.swing.JButton();
        jButtonActualizarArbolPanel = new javax.swing.JButton();
        jButtonRegresar = new javax.swing.JButton();

        jButton2.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("BIENVENIDO AL MÓDULO DEL ADMINISTRADOR");

        jButtonCargaMasiva.setText("CARGA MASIVA DE CLIENTES");
        jButtonCargaMasiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargaMasivaActionPerformed(evt);
            }
        });

        jButtonInsertarCliente.setText("Ingresar cliente nuevo");
        jButtonInsertarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Registrar cliente");

        jLabel3.setText("DPI:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Contraseña:");

        jButtonModificarCliente.setText("Modificar cliente con DPI");

        jButtonEliminarCliente.setText("Eliminar cliente con DPI");

        jLabel10.setText("DPI:");

        jLabel12.setText("Nombre:");

        jLabel13.setText("Contraseña:");

        jButtonBuscarCliente.setText("Buscar cliente con DPI");
        jButtonBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClienteActionPerformed(evt);
            }
        });

        jButtonActualizarArbolPanel.setText("Actualizar árbol de clientes");
        jButtonActualizarArbolPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarArbolPanelActionPerformed(evt);
            }
        });

        jButtonRegresar.setText("Regresar");
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonCargaMasiva, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(409, 409, 409))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDPI)
                                    .addComponent(jTextFieldPassword)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldPassword2)
                                            .addComponent(jTextFieldNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldDPI2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonBuscarCliente))))
                                    .addComponent(jButtonInsertarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonActualizarArbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonRegresar))
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarArbolPanel)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(jButtonInsertarCliente)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDPI2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jButtonBuscarCliente))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jButtonModificarCliente))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCargaMasiva)
                    .addComponent(jButtonEliminarCliente))
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargaMasivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargaMasivaActionPerformed
        try {
            fFicheros.vaciarTodoDirectorio("./Clientes");
            fFicheros.crearNuevoDirectorio("./Clientes");
            arbolClientes = new ArbolB<>();
            fJSON.leerJSON_Clientes(this, fFicheros, "./Clientes");
            JOptionPane.showMessageDialog(this, "Carga masiva de clientes completada con éxito");
            mostrarImagen("./Clientes/Arbol_Clientes.png");
        } catch (Exception error) {
            System.out.println("Error en el método JSON CLIENTES");
        }
    }//GEN-LAST:event_jButtonCargaMasivaActionPerformed

    private void jButtonBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClienteActionPerformed
        if (!jTextFieldDPI2.getText().equals("")) {
            try {
                long dpi_ClienteBuscado = Long.valueOf(jTextFieldDPI2.getText());
                Cliente clienteTemp = new Cliente(dpi_ClienteBuscado, "", "");
                Cliente ClienteEncontrado = arbolClientes.getValorNodoB_byId(clienteTemp);
                if (ClienteEncontrado != null) {
                    jTextFieldNombre2.setText(ClienteEncontrado.getNombre());
                    jTextFieldPassword2.setText(ClienteEncontrado.getPassword());
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un cliente con ese ID.");
                }
            } catch (Exception error) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un dato correcto al campo DPI.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El campo del ID esta vacío para buscar un cliente se necesita que lo llene.");
        }
    }//GEN-LAST:event_jButtonBuscarClienteActionPerformed

    private void jButtonInsertarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarClienteActionPerformed
        if ((!jTextFieldDPI.getText().equals("") && !jTextFieldNombre.getText().equals("") && !jTextFieldPassword.getText().equals(""))) {
            try {
                long dpi_ClienteNuevo = Long.valueOf(jTextFieldDPI.getText());
                String nombre_ClienteNuevo = jTextFieldNombre.getText();
                String password_ClienteNuevo = jTextFieldPassword.getText();

                Cliente clienteNuevo = new Cliente(dpi_ClienteNuevo, nombre_ClienteNuevo, password_ClienteNuevo);

                arbolClientes.insertarEnArbol(clienteNuevo);
                fJSON.crearDirectoriosCliente(fFicheros, clienteNuevo);
                arbolClientes.graficarArbolB("Arbol_Clientes", "./Clientes", "./Clientes");
                JOptionPane.showMessageDialog(this, "Se registro el cliente nuevo con éxito!!!!");
                mostrarImagen("./Clientes/Arbol_Clientes.png");
            } catch (Exception error) {
                JOptionPane.showMessageDialog(this, "Verifique sus entradas (DPI únicamente números, Nombre cualquier caracter, Password cualquier caracter");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Es necesario llenar todos los campos para acceder a esta opción.");
        }
    }//GEN-LAST:event_jButtonInsertarClienteActionPerformed

    private void jButtonActualizarArbolPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarArbolPanelActionPerformed
        mostrarImagen("./Clientes/Arbol_Clientes.png");
    }//GEN-LAST:event_jButtonActualizarArbolPanelActionPerformed

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed
        Login lg = new Login(arbolClientes);
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(Modulo_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonActualizarArbolPanel;
    private javax.swing.JButton jButtonBuscarCliente;
    private javax.swing.JButton jButtonCargaMasiva;
    private javax.swing.JButton jButtonEliminarCliente;
    private javax.swing.JButton jButtonInsertarCliente;
    private javax.swing.JButton jButtonModificarCliente;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldDPI;
    private javax.swing.JTextField jTextFieldDPI2;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre2;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldPassword2;
    // End of variables declaration//GEN-END:variables
}
