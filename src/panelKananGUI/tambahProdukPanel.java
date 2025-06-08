package panelKananGUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import service.FileHandlerService;
import actor.Produk;
import app.MainFrame; 
import service.*;
public class tambahProdukPanel extends javax.swing.JPanel {

    private List<Produk> daftarProduk; 
    private FileHandlerService fileHandlerService;
    private MainFrame mainFrame; 

    
    private final String PRODUK_NAME_PLACEHOLDER = "Masukkan Nama Produk Baru";

    public tambahProdukPanel(List<Produk> daftarProduk, FileHandlerService fileHandlerService, MainFrame mainFrame) {
        this.daftarProduk = daftarProduk;
        this.fileHandlerService = fileHandlerService;
        this.mainFrame = mainFrame;
        initComponents();
        customizeComponentsForProduct(); 
        
        
        tambahPesananButton.addActionListener(e -> tambahProdukAction());
    }

    private void customizeComponentsForProduct() {
        jLabel1.setText("Tambah Produk Baru"); 

        
        idLabel.setVisible(false);
        idField.setVisible(false);
        genIDButton.setVisible(false);
        pilihPesananLabel.setVisible(false);
        pesananComboBox.setVisible(false);

        
        namaPelLabel.setText("Nama Produk");
        setPlaceholder(namaPelField, PRODUK_NAME_PLACEHOLDER);

        
        tambahPesananButton.setText("Simpan Produk Baru");
    }
    
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private String getTextFieldValue(JTextField textField, String placeholder) {
        return textField.getText().equals(placeholder) ? "" : textField.getText();
    }


    private void tambahProdukAction() {
        String namaProdukBaru = getTextFieldValue(namaPelField, PRODUK_NAME_PLACEHOLDER);

        if (namaProdukBaru.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama produk tidak boleh kosong!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        boolean exists = daftarProduk.stream()
                                     .anyMatch(p -> p.getNamaProduk().equalsIgnoreCase(namaProdukBaru));
        if (exists) {
            JOptionPane.showMessageDialog(this, "Produk '" + namaProdukBaru + "' sudah ada.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produk produkBaru = new Produk(namaProdukBaru);
        daftarProduk.add(produkBaru);
        fileHandlerService.tulisDataProduk(daftarProduk, ManajemenFile.PRODUK_FILE);

        JOptionPane.showMessageDialog(this, "Produk '" + namaProdukBaru + "' berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        setPlaceholder(namaPelField, PRODUK_NAME_PLACEHOLDER); 

        
        if (mainFrame != null) {
            mainFrame.produkDitambahkan();
        }
    }


    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        idField = new javax.swing.JTextField();
        genIDButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        namaPelLabel = new javax.swing.JLabel();
        namaPelField = new javax.swing.JTextField();
        pilihPesananLabel = new javax.swing.JLabel();
        pesananComboBox = new javax.swing.JComboBox<>();
        tambahPesananButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setMaximumSize(new java.awt.Dimension(589, 575));
        setMinimumSize(new java.awt.Dimension(589, 575));
        setPreferredSize(new java.awt.Dimension(589, 575));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pendaftaran Antrian"); 

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        idField.setBackground(new java.awt.Color(204, 204, 204));
        idField.setForeground(new java.awt.Color(153, 153, 153));
        idField.setText("ID Pesanan Otomatis");

        genIDButton.setBackground(new java.awt.Color(0, 51, 51));
        genIDButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); 
        genIDButton.setForeground(new java.awt.Color(255, 255, 255));
        genIDButton.setText("Generate ID");
        genIDButton.setBorderPainted(false);
        genIDButton.setOpaque(true);

        idLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        idLabel.setForeground(new java.awt.Color(255, 255, 255));
        idLabel.setText("ID Pesanan");

        namaPelLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        namaPelLabel.setForeground(new java.awt.Color(255, 255, 255));
        namaPelLabel.setText("Nama Pelanggan"); 

        namaPelField.setBackground(new java.awt.Color(204, 204, 204));
        namaPelField.setForeground(new java.awt.Color(153, 153, 153));
        namaPelField.setText("Masukkan Nama Pelanggan"); 

        pilihPesananLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        pilihPesananLabel.setForeground(new java.awt.Color(255, 255, 255));
        pilihPesananLabel.setText("Pilih Pesanan");

        pesananComboBox.setBackground(new java.awt.Color(204, 204, 204));
        pesananComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tambahPesananButton.setBackground(new java.awt.Color(0, 51, 51));
        tambahPesananButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); 
        tambahPesananButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahPesananButton.setText("Tambah Pesanan"); 
        tambahPesananButton.setBorderPainted(false);
        tambahPesananButton.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genIDButton, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
            .addComponent(namaPelField)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel)
                    .addComponent(namaPelLabel)
                    .addComponent(pilihPesananLabel))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pesananComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tambahPesananButton, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idLabel)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idField)
                    .addComponent(genIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namaPelLabel)
                .addGap(7, 7, 7)
                .addComponent(namaPelField, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pilihPesananLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesananComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tambahPesananButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }


    
    private javax.swing.JButton genIDButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField namaPelField;
    private javax.swing.JLabel namaPelLabel;
    private javax.swing.JComboBox<String> pesananComboBox;
    private javax.swing.JLabel pilihPesananLabel;
    private javax.swing.JButton tambahPesananButton;
    
}