/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases_proyecto.Album;
import clases_proyecto.Cliente;
import estructuras.arbolB.ArbolB;
import estructuras.linkedlist.LinkedList;
import estructuras.linkedlist.LinkedList_Node;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Melissa
 */
public class Modulo_ReportesAdmin extends javax.swing.JFrame {

    ArbolB arbolClientes;
    DefaultTableModel dtm = new DefaultTableModel();

    /**
     * Creates new form Modulo_ReportesAdmin
     */
    public Modulo_ReportesAdmin() {
        initComponents();
    }

    public Modulo_ReportesAdmin(ArbolB<Cliente> arbolClientes) {
        this.arbolClientes = arbolClientes;
        initComponents();
        String[] titulo = new String[]{"DPI", "Nombre", "Contraseña", "Cantidad de imágenes totales"};
        dtm.setColumnIdentifiers(titulo);
        jTable1.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonRegresar = new javax.swing.JButton();
        jButtonBuscarDPI = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldDPIbuscado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelContraseña = new javax.swing.JLabel();
        jLabelCantidadAlbumes = new javax.swing.JLabel();
        jLabelCantImagenesAlbumes = new javax.swing.JLabel();
        jLabelImagenesTotales = new javax.swing.JLabel();
        jLabelCapasTotales = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonRegresar.setText("Regresar");
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        jButtonBuscarDPI.setText("Buscar cliente mediante DPI");
        jButtonBuscarDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarDPIActionPerformed(evt);
            }
        });

        jLabel1.setText("DPI a buscar");

        jButton3.setText("Listar clientes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        jLabel2.setText("Nombre");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Cantidad álbumes");

        jLabel5.setText("Imágenes en álbumes");

        jLabel6.setText("Imágenes totales");

        jLabel7.setText("Capas totales");

        jLabelNombre.setText("...");

        jLabelContraseña.setText("...");

        jLabelCantidadAlbumes.setText("...");

        jLabelCantImagenesAlbumes.setText("...");

        jLabelImagenesTotales.setText("...");

        jLabelCapasTotales.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRegresar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonBuscarDPI)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDPIbuscado))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNombre)
                                    .addComponent(jLabelContraseña)
                                    .addComponent(jLabelCantidadAlbumes)
                                    .addComponent(jLabelCantImagenesAlbumes)
                                    .addComponent(jLabelImagenesTotales)
                                    .addComponent(jLabelCapasTotales))))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonRegresar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscarDPI)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldDPIbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNombre)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelContraseña)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCantidadAlbumes)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCantImagenesAlbumes)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelImagenesTotales)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCapasTotales))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed
        Modulo_Admin ma = new Modulo_Admin(arbolClientes);
        ma.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonRegresarActionPerformed

    private void jButtonBuscarDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarDPIActionPerformed
        if (!jTextFieldDPIbuscado.getText().equals("")) {
            try {
                long dpi_ClienteBuscado = Long.valueOf(jTextFieldDPIbuscado.getText());
                Cliente clienteTemp = new Cliente(dpi_ClienteBuscado, "", "");
                Cliente ClienteEncontrado = (Cliente) arbolClientes.getValorNodoB_byId(clienteTemp);
                if (ClienteEncontrado != null) {
                    jLabelNombre.setText(ClienteEncontrado.getNombre());
                    jLabelContraseña.setText(ClienteEncontrado.getPassword());
                    jLabelCantidadAlbumes.setText("" + ClienteEncontrado.getLista_Albumes().getlength());
                    /* Lo siguiente es para poder obtener el número de imágenes en todos los álbumes por eso necesitamos recorrer los álbumes*/
                    int contImagenes_En_Albumes = 0;
                    LinkedList_Node albumActual = ClienteEncontrado.getLista_Albumes().getCabezaLista();
                    while (albumActual != null) {
                        contImagenes_En_Albumes += ((Album) albumActual.getValor()).getListaImagenes().getlength();
                        albumActual = albumActual.siguiente;
                    }
                    jLabelCantImagenesAlbumes.setText("" + contImagenes_En_Albumes);
                    jLabelImagenesTotales.setText("" + ClienteEncontrado.getArbol_Imagenes().getLength());
                    jLabelCapasTotales.setText("" + ClienteEncontrado.getArbol_CapasGenerales().getLength());
                } else {
                    JOptionPane.showMessageDialog(this, "El cliente con el DPI solicitado no existe en el árbol de clientes.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ingrese un DPI válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El campo de DPI a buscar es obligatorio llenarlo.");
        }
    }//GEN-LAST:event_jButtonBuscarDPIActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int filas = dtm.getRowCount();
        for (int i = 0; i < filas; i++) {
            dtm.removeRow(0);
        }
        LinkedList<Cliente> listaClientes = arbolClientes.getLinkedList_RecorridoAmplitud();
        Object[] arrayCliente = listaClientes.getArrayfromLinkedList();
        Object[] row;
        for (int i = 0; i < arrayCliente.length; i++) {
            row = new Object[4];
            row[0] = ((Cliente)arrayCliente[i]).getDPI();
            row[1] = ((Cliente)arrayCliente[i]).getNombre();
            row[2] = ((Cliente)arrayCliente[i]).getPassword();
            row[3] = ((Cliente)arrayCliente[i]).getArbol_Imagenes().getLength();
            dtm.addRow(row);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Modulo_ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_ReportesAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBuscarDPI;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCantImagenesAlbumes;
    private javax.swing.JLabel jLabelCantidadAlbumes;
    private javax.swing.JLabel jLabelCapasTotales;
    private javax.swing.JLabel jLabelContraseña;
    private javax.swing.JLabel jLabelImagenesTotales;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldDPIbuscado;
    // End of variables declaration//GEN-END:variables
}
