/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author isaac
 */
public class Consultas extends javax.swing.JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/tintoreria";
    private static final String USER = "root";
    private static final String PASSWORD = "kekistan";

    public Consultas() {
        initComponents();
        setVisible(true);
    }

    private void extraerFolios(int folioP) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return;
        }

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Prepare the stored procedure call

            Statement st= connection.createStatement();
            String consultaFolio = String.format("SELECT\n" +
                    "    s.folio,\n" +
                    "    s.fecha AS fecha_servicio,\n" +
                    "    c.nombre AS nombre_cliente,\n" +
                    "    c.paterno AS paterno_cliente,\n" +
                    "    c.materno AS materno_cliente,\n" +
                    "    e.nombre AS nombre_empleado,\n" +
                    "    e.paterno AS paterno_empleado,\n" +
                    "    e.materno AS materno_empleado\n" +
                    "FROM\n" +
                    "    Servicio s\n" +
                    "JOIN\n" +
                    "    Clientes c ON s.idCliente = c.idCliente\n" +
                    "JOIN\n" +
                    "    Empleados e ON s.noPersonal = e.noPersonal\n" +
                    "WHERE\n" +
                    "    s.folio = %d;",folioP);

            ResultSet rs= st.executeQuery(consultaFolio);

            System.out.println("HOLA, SOY EXTRAERFOLIOS");
            // Check if the result set is empty
            if (!rs.isBeforeFirst()) {
                // Result set is empty, add an empty row
                DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                tblModel.addRow(new Object[]{});
            } else {
                // Result set is not empty, process the rows
                while (rs.next()) {
                    String folio=rs.getString("folio");
                    String fecha=rs.getString("fecha_servicio");
                    String clienteCompleto=rs.getString("nombre_cliente")+" "+rs.getString("paterno_cliente")+" "+rs.getString("materno_cliente");
                    String empleadoCompleto=rs.getString("nombre_empleado")+" "+rs.getString("paterno_empleado")+" "+rs.getString("materno_empleado");

                    String tbData[] = {folio,fecha,clienteCompleto,empleadoCompleto};
                    DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                    tblModel.addRow(tbData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            javax.swing.JOptionPane.showMessageDialog(null, "Numero de folio faltante/no valido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void extraerVista(int folio) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return;
        }

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Prepare the stored procedure call

            Statement st= connection.createStatement();
            String viewVistaTotalServicioCall = String.format("SELECT * FROM VistaTotalServicio WHERE FolioServicio=%d",folio);
            ResultSet rs= st.executeQuery(viewVistaTotalServicioCall);

            // Check if the result set is empty
            if (!rs.isBeforeFirst()) {
                // Result set is empty, add an empty row
                DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                tblModel.addRow(new Object[]{});
            } else {
                // Result set is not empty, process the rows
                while (rs.next()) {
                    System.out.println("HOLA, SOY EXTRAER VISTA");
                    String p_folio = String.valueOf(rs.getInt("FolioServicio"));
                    String costo = String.valueOf(rs.getFloat("TotalServicio"));
                    String tbData[] = {p_folio, costo};
                    DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                    tblModel.addRow(tbData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Numero de folio faltante/no valido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void extraerFlojos() {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return;
        }

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Prepare the stored procedure call

            Statement st= connection.createStatement();
            String findFlojos = String.format("SELECT e.noPersonal,e.nombre,e.paterno,e.materno,e.turno\n" +
                    "FROM Empleados e\n" +
                    "WHERE e.idPuestos = 3\n" +
                    "AND NOT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM Servicio s\n" +
                    "    WHERE s.noPersonal = e.noPersonal\n" +
                    ");");
            ResultSet rs= st.executeQuery(findFlojos);

            // Check if the result set is empty
            if (!rs.isBeforeFirst()) {
                // Result set is empty, add an empty row
                DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                tblModel.addRow(new Object[]{});
            } else {
                // Result set is not empty, process the rows
                while (rs.next()) {
                    String noPersonal=rs.getString("noPersonal");
                    String nombre=rs.getString("nombre");
                    String paterno=rs.getString("paterno");
                    String materno=rs.getString("materno");
                    String turno=rs.getString("turno");

                    String tbData[] = {noPersonal,nombre,paterno,materno,turno};
                    DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();
                    tblModel.addRow(tbData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Numero de folio faltante/no valido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},

                },
                new String [] {
                        "Folio", "Fecha", "Cliente", "Empleado"
                }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones disponibles", "Total de servicios", "Servicios cliente", "Empleados sin servicios" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Num. Folio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton1)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField1))
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBox1.getSelectedItem().equals("Total de servicios")){
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

            jButton1.setText("Buscar");
            jTextField1.setVisible(true);
            jLabel1.setText("Num. Folio");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                            {null, null},

                    },
                    new String [] {
                            "Folio", "Total"
                    }
            ));
            jScrollPane2.setViewportView(jTable2);
        } else if (jComboBox1.getSelectedItem().equals("Servicios cliente")) {
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                            {null, null, null, null},

                    },
                    new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                    }
            ));
            jScrollPane1.setViewportView(jTable1);

            jButton1.setText("Buscar");
            jTextField1.setVisible(true);
            jLabel1.setText("Num. Folio");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                            {null, null, null, null},

                    },
                    new String [] {
                            "Folio", "Fecha", "Cliente", "Empleado"
                    }
            ));
            jScrollPane2.setViewportView(jTable2);
        } else if (jComboBox1.getSelectedItem().equals("Empleados sin servicios")) {
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                            {null, null, null, null},

                    },
                    new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                    }
            ));
            jScrollPane1.setViewportView(jTable1);

            jButton1.setText("Actualizar");
            jTextField1.setVisible(false);
            jLabel1.setText("");
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                            {null, null, null, null, null},

                    },
                    new String [] {
                            "No Personal", "Nombre", "A Paterno", "A materno", "Turno"
                    }
            ));
        }else {

        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel tblModel=(DefaultTableModel) jTable2.getModel();
        tblModel.setRowCount(0);

        if (jComboBox1.getSelectedItem().equals("Total de servicios")){
            extraerVista(Integer.parseInt(jTextField1.getText()));
        } else if (jComboBox1.getSelectedItem().equals("Servicios cliente")) {
            extraerFolios(Integer.parseInt(jTextField1.getText()));
        } else if (jComboBox1.getSelectedItem().equals("Empleados sin servicios")) {
            extraerFlojos();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}
