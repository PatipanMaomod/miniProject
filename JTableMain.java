import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableMain extends javax.swing.JFrame {
    private Connection connection;

    public JTableMain() {
        initComponents();
        connectDatabase();
        loadData(); // Load data from the database at startup
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TextName = new javax.swing.JTextField();
        TextQuan = new javax.swing.JTextField();
        TextPrice = new javax.swing.JTextField();
        bbAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bbDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel1.setText("Product Name");

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel2.setText("Quantity");

        jLabel3.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel3.setText("Price");

        bbAdd.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        bbAdd.setText("ADD");
        bbAdd.setToolTipText("");
        bbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbAddActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Product Name", "Qantity", "Price"
                }));
        jScrollPane1.setViewportView(jTable1);

        bbDelete.setFont(new java.awt.Font("SimSun-ExtB", 1, 14)); // NOI18N
        bbDelete.setText("Delete");
        bbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(bbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TextName, javax.swing.GroupLayout.DEFAULT_SIZE, 101,
                                                Short.MAX_VALUE)
                                        .addComponent(TextQuan)
                                        .addComponent(TextPrice)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(TextQuan, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(TextPrice, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bbAdd)
                                                        .addComponent(bbDelete, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(69, Short.MAX_VALUE)));

        pack();
    }

    private void connectDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/mydatabase"; // Adjust the database name as needed
            String username = "root"; // Adjust username if necessary
            String password = ""; // Adjust password if necessary
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + ex.getMessage());
            System.exit(1);
        }
    }

    private void updateID() {
        String updateSQL = "SET @row_number := 0;"; // ตั้งค่าตัวแปร @row_number
        String updateIDSQL = "UPDATE chair SET ID = (@row_number := @row_number + 1) ORDER BY id;"; // ปรับลำดับหมายเลข

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(updateSQL); // รันคำสั่งตั้งค่าตัวแปร
            stmt.executeUpdate(updateIDSQL); // อัปเดตหมายเลขผลิตภัณฑ์
            System.out.println("Updated successfully.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating product numbers: " + ex.getMessage());
        }
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT * FROM chair"; // SQL command to retrieve data from the chair table

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("ProductsName");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                model.addRow(new Object[] { id, name, quantity, price }); // Add new row to JTable
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }

    private void insertData(String productName, int quantity, double price) {
        String insertSQL = "INSERT INTO chair (ProductsName, Quantity, Price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, productName);
            pstmt.setInt(2, quantity);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully.");
            updateID();
            loadData(); // Reload data from the database after adding data
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage());
        }
    }

    private void deleteData(int id) {
        String deleteSQL = "DELETE FROM chair WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product deleted successfully.");
            updateID();
            loadData(); // Reload data to update the table
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + ex.getMessage());
        }
    }

    private void bbAddActionPerformed(java.awt.event.ActionEvent evt) {
        String productName = TextName.getText();
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(TextQuan.getText());
            price = Double.parseDouble(TextPrice.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and price.");
            return;
        }

        insertData(productName, quantity, price);

        // Clear fields after adding
        TextName.setText("");
        TextQuan.setText("");
        TextPrice.setText("");
    }

    private void bbDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow(); // Get selected row index
        if (selectedRow != -1) {
            int id = (int) jTable1.getValueAt(selectedRow, 0); // Get ID of the selected product
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteData(id); // Call the deleteData method
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new JTableMain().setVisible(true));
    }

    private javax.swing.JTextField TextName;
    private javax.swing.JTextField TextPrice;
    private javax.swing.JTextField TextQuan;
    private javax.swing.JButton bbAdd;
    private javax.swing.JButton bbDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
