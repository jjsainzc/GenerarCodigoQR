/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generarcodigoqr;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 *
 * @author alienware
 */
class Filtro extends FileFilter {
    private String[] ext;

    public Filtro(String[] extension) {
        ext = extension;
    }

    @Override
    public boolean accept(File f) {
        boolean resultado = false;
        for (String ex : ext) {
            resultado = resultado || f.getName().toLowerCase().endsWith("." + ex);
        }
        return (f.isDirectory() || resultado);
    }

    @Override
    public String getDescription() {
        return "Archivos (.png)";
    }
}

public final class GenerarCodigoQR extends javax.swing.JFrame {

    private PanelLogo panelLogo;
    private JFileChooser archivo;
    private File archivoTemp;

    public GenerarCodigoQR() {
        initComponents();
        limpiarCampos();

        archivo = new JFileChooser();
        archivo.setDialogType(JFileChooser.SAVE_DIALOG);
        archivo.setDialogTitle("Guardar imagen");

        archivo.setApproveButtonText("Guardar");
        archivo.setFileFilter(new Filtro(new String[]{"png"}));

        archivo.setCurrentDirectory(
                new java.io.File(System.getProperty("user.dir")));

        File f = new File("temp");
        if (!f.exists()) {
            f.mkdirs();
        }
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Controlar que accion hacer al cerrar el frame
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               limpiarCampos(); 
            }
        });
        
    }

    public String capitalizarTexto(String textoSinFormato) {
        StringBuilder textoFormateado = new StringBuilder();

        if (!textoSinFormato.isEmpty()) {
            String[] palabras = textoSinFormato.split("\\s+");
            for (String palabra : palabras) {
                textoFormateado.append(palabra.substring(0, 1).toUpperCase()
                        .concat(palabra.substring(1, palabra.length())
                        .toLowerCase()).concat(" "));
            }
        }
        return textoFormateado.toString();
    }

    void limpiarImagen() {
        jPanel1.removeAll();
        jPanel1.repaint();
    }
    
    void limpiarArchivoTemp() {
         if (archivoTemp != null) {
            archivoTemp.delete();
        }
    }
    
    void limpiarCampos() {
        nombre.setText("");
        apellidos.setText("");
        direccion.setText("");
        celular.setText("");
        telefono.setText("");
        email.setText("");
        http.setText("");

        limpiarImagen();
        limpiarArchivoTemp();
        exportar.setVisible(false);
    }
    
    void limpiarTexto(){
        texto.setText("");
    }

    String generarCodigo(String informacion) {
        String nombre_archivo = "temp/QR" + Math.random();

        ByteArrayOutputStream out = QRCode.from(informacion).withSize(200, 200)
                .to(ImageType.PNG).stream();
        try {
            try (FileOutputStream fout = new FileOutputStream(new File(nombre_archivo + ".png"))) {
                fout.write(out.toByteArray());
                fout.flush();
            }
        } catch (IOException e) {
        } finally {
            return nombre_archivo + ".png";
        }
    }

    void mostrarImagen(String ruta) {
        if (panelLogo == null) {
            panelLogo = new PanelLogo(ruta);
        } else {
            panelLogo.setImagenNombre(ruta);
        }
        panelLogo.setSize(jPanel1.getSize());
        panelLogo.repaint();

        jPanel1.add(panelLogo);
        jPanel1.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        celular = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        http = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        generar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        exportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextPane();
        limpiarTexto = new javax.swing.JButton();
        generarTexto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellidos");

        jLabel4.setText("Direccion");

        jLabel5.setText("Celular");

        jLabel6.setText("Telefono");

        jLabel7.setText("Email");

        jLabel8.setText("http://");

        apellidos.setText("jTextField1");

        jTextField2.setText("jTextField1");

        direccion.setText("jTextField1");

        celular.setText("jTextField1");

        telefono.setText("jTextField1");

        email.setText("jTextField1");

        http.setText("jTextField1");

        nombre.setText("jTextField1");

        generar.setText("Generar VCard");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar VCard");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );

        exportar.setText("Exportar");
        exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(texto);

        limpiarTexto.setText("Limpiar Texto");
        limpiarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarTextoActionPerformed(evt);
            }
        });

        generarTexto.setText("Generar Texto");
        generarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarTextoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(http, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addComponent(exportar)
                                .addGap(66, 66, 66))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(generar)
                        .addGap(18, 18, 18)
                        .addComponent(limpiar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(generarTexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(limpiarTexto)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(http, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(exportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generar)
                    .addComponent(limpiar))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limpiarTexto)
                    .addComponent(generarTexto))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Generador de codigo QR ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed
        StringBuilder informacion = new StringBuilder();

        informacion.append("BEGIN:VCARD").append("\n");

        if (!nombre.getText().isEmpty()) {
            informacion
                    .append("N:")
                    .append(capitalizarTexto(apellidos.getText()))
                    .append(";")
                    .append(capitalizarTexto(nombre.getText())).append("\n");
        } else {
            JOptionPane.showMessageDialog(this, "Nombre esta en blanco", "ERROR", JOptionPane.ERROR_MESSAGE);
            nombre.requestFocus();
            return;
        }
        if (!direccion.getText().isEmpty()) {
            informacion
                    .append("ADR;HOME:")
                    .append(direccion.getText()).append("\n");
        }
        if (!celular.getText().isEmpty()) {
            informacion
                    .append("TEL;CEL:")
                    .append(celular.getText()).append("\n");
        }
        if (!telefono.getText().isEmpty()) {
            informacion
                    .append("TEL;HOME:")
                    .append(telefono.getText()).append("\n");
        }
        if (!email.getText().isEmpty()) {
            if (!Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$").matcher(email.getText()).matches()) {
                JOptionPane.showMessageDialog(this, "Email es incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
                email.requestFocus();
                return;
            }
            informacion
                    .append("EMAIL:")
                    .append(email.getText().toLowerCase()).append("\n");
        }
        if (!http.getText().isEmpty()  )  {
           
            if (!Pattern.compile("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\?=.-]*)*\\/?$").matcher(http.getText()).matches()) {
                JOptionPane.showMessageDialog(this, "URL es incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
                http.requestFocus();
                return;
            }
            informacion
                    .append("URL:http://")
                    .append(http.getText().toLowerCase()).append("\n");
        }
        informacion.append("END:VCARD");

        if (archivoTemp != null) {
            archivoTemp.delete();
        }
        archivoTemp = new File(generarCodigo(informacion.toString()));
        mostrarImagen(archivoTemp.getAbsolutePath());
        exportar.setVisible(true);
    }//GEN-LAST:event_generarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarActionPerformed
        if (archivo.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            InputStream entrada = null;
            OutputStream salida = null;
            try {
                File archivoFinal = archivo.getSelectedFile();
                                        
                entrada = new FileInputStream(archivoTemp);
                salida = new FileOutputStream(archivoFinal.getAbsolutePath().concat(".png"));

                byte[] buf = new byte[2048];
                int len;
                while ((len = entrada.read(buf)) > 0) {
                    salida.write(buf, 0, len);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "En entrada/salida "+ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (entrada != null) entrada.close();
                    if (salida != null) salida.close();
                    archivoTemp.delete();
                } catch (IOException ex) {
                }
            }
        }
    }//GEN-LAST:event_exportarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        
    }//GEN-LAST:event_formWindowDeactivated

    private void limpiarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarTextoActionPerformed
        limpiarTexto();
        limpiarImagen();
        limpiarArchivoTemp();
        exportar.setVisible(false);
    }//GEN-LAST:event_limpiarTextoActionPerformed

    private void generarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarTextoActionPerformed
        if (archivoTemp != null) {
            archivoTemp.delete();
        }
        archivoTemp = new File(generarCodigo(texto.getText()));
        mostrarImagen(archivoTemp.getAbsolutePath());
        exportar.setVisible(true);
    }//GEN-LAST:event_generarTextoActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(GenerarCodigoQR.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarCodigoQR.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarCodigoQR.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarCodigoQR.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarCodigoQR().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    private javax.swing.JTextField celular;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField email;
    private javax.swing.JButton exportar;
    private javax.swing.JButton generar;
    private javax.swing.JButton generarTexto;
    private javax.swing.JTextField http;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton limpiarTexto;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextPane texto;
    // End of variables declaration//GEN-END:variables
}
