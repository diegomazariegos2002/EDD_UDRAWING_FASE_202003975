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
import estructuras.linkedlist.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Melissa
 */
public class Modulo_ReportesCliente extends javax.swing.JFrame {

    Cliente clienteRegistrado = null;
    ArbolB<Cliente> arbolClientes = null;
    DefaultTableModel dtm = new DefaultTableModel();

    /**
     * Creates new form Modulo_ReportesUsuario
     */
    public Modulo_ReportesCliente() {
        initComponents();
    }

    /**
     * Constructor de mi JFrame
     */
    public Modulo_ReportesCliente(Cliente clienteRegistrado, ArbolB<Cliente> arbolClientes) {
        this.clienteRegistrado = clienteRegistrado;
        this.arbolClientes = arbolClientes;
        initComponents();
        String[] titulo = new String[]{"Id imagen", "Cantidad de capas"};
        dtm.setColumnIdentifiers(titulo);
        jTable1.setModel(dtm);
        jComboBox2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jButton2.setText("Generar reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Top 5 de imágenes con más número de capas", "Todas las capas que son hojas", "Profundidad de árbol de capas", "Listar capas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "preorden", "inorden", "postorden" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Modulo_Cliente mc = new Modulo_Cliente(clienteRegistrado, arbolClientes);
        mc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int filas = dtm.getRowCount();
        for (int i = 0; i < filas; i++) {
            dtm.removeRow(0);
        }
        if (jComboBox1.getSelectedItem().equals("Top 5 de imágenes con más número de capas")) {
            jComboBox2.setVisible(false);
            String[] titulo = new String[]{"Id imagen"};
            dtm.setColumnIdentifiers(titulo);
            jTable1.setModel(dtm);
            LinkedList<Imagen> listaImagenes = clienteRegistrado.getArbol_Imagenes().getLinkedList_PreOrden();
            listaImagenes.ordenar_InsertionSort_MayorMenorNumeroCapas();
            Object[] arrayImagenes = listaImagenes.getArrayfromLinkedList();
            Object[] row;
            for (int i = 0; i < arrayImagenes.length && i < 5; i++) {
                row = new Object[1];
                row[0] = arrayImagenes[i].toString();
                dtm.addRow(row);
            }
        } else if (jComboBox1.getSelectedItem().equals("Todas las capas que son hojas")) {
            jComboBox2.setVisible(false);
            String[] titulo = new String[]{"Id capas que son imágenes"};
            dtm.setColumnIdentifiers(titulo);
            jTable1.setModel(dtm);
            LinkedList<Capa> listaHojas = clienteRegistrado.getArbol_CapasGenerales().getNodosHijos();
            Object[] arrayHojas = listaHojas.getArrayfromLinkedList();
            Object[] row;
            for (int i = 0; i < arrayHojas.length; i++) {
                row = new Object[1];
                row[0] = arrayHojas[i].toString();
                dtm.addRow(row);
            }
            
        } else if (jComboBox1.getSelectedItem().equals("Profundidad de árbol de capas")) {
            jComboBox2.setVisible(false);
            String[] titulo = new String[]{"Profundidad de árbol de capas"};
            dtm.setColumnIdentifiers(titulo);
            jTable1.setModel(dtm);
            Object[] arrow = new Object[]{clienteRegistrado.getArbol_CapasGenerales().getProfunidadArbol()};
            dtm.addRow(arrow);
            
        } else {
            String[] titulo = new String[]{"Id imagen"};
            dtm.setColumnIdentifiers(titulo);
            jTable1.setModel(dtm);
            LinkedList<Capa> listaCapas;
            if (jComboBox2.getSelectedItem().equals("preorden")) {
                listaCapas = clienteRegistrado.getArbol_CapasGenerales().getLinkedList_PreOrden();
                System.out.println("");
                listaCapas.imprimir();
            } else if (jComboBox2.getSelectedItem().equals("inorden")) {
                listaCapas = clienteRegistrado.getArbol_CapasGenerales().getLinkedList_InOrden();
                System.out.println("");
                listaCapas.imprimir();
            } else {
                listaCapas = clienteRegistrado.getArbol_CapasGenerales().getLinkedList_PostOrden();
                System.out.println("");
                listaCapas.imprimir();
            }
            Object[] arrayCapas = listaCapas.getArrayfromLinkedList();
            Object[] row;
            for (int i = 0; i < arrayCapas.length; i++) {
                row = new Object[1];
                row[0] = arrayCapas[i].toString();
                dtm.addRow(row);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedItem().equals("Top 5 de imágenes con más número de capas")) {
            jComboBox2.setVisible(false);
        } else if (jComboBox1.getSelectedItem().equals("Todas las capas que son hojas")) {
            jComboBox2.setVisible(false);
        } else if (jComboBox1.getSelectedItem().equals("Profundidad de árbol de capas")) {
            jComboBox2.setVisible(false);
        } else {
            jComboBox2.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(Modulo_ReportesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo_ReportesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulo_ReportesCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}