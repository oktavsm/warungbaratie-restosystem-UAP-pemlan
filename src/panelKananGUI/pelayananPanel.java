package panelKananGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Queue;
import java.util.List; 
import java.util.ArrayList; 
import service.*;
import actor.*;
import app.*;



public class pelayananPanel extends javax.swing.JPanel {

    private AntrianService antrianService;
    private DefaultTableModel tableModel;
    private MainFrame mainFrame;

    public pelayananPanel(AntrianService antrianService, MainFrame mainFrame) {
        this.antrianService = antrianService;
        this.mainFrame = mainFrame;
        initComponents();
        initTableModel();
        refreshTampilanAntrian();

        
        layaniButton.addActionListener(e -> layaniPelangganAction());
    }

    private void initTableModel() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"No. Antrian", "ID Pesanan", "Nama Pelanggan", "Pesanan", "Waktu Daftar"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        antrianTable.setModel(tableModel);
         
        antrianTable.getColumnModel().getColumn(0).setPreferredWidth(60);  
        antrianTable.getColumnModel().getColumn(0).setMaxWidth(80);
        antrianTable.getColumnModel().getColumn(1).setPreferredWidth(80);  
        antrianTable.getColumnModel().getColumn(2).setPreferredWidth(150); 
        antrianTable.getColumnModel().getColumn(3).setPreferredWidth(180); 
        antrianTable.getColumnModel().getColumn(4).setPreferredWidth(80);  
    }

    public void refreshTampilanAntrian() {
        tableModel.setRowCount(0); 
        Queue<Pelanggan> semuaAntrian = antrianService.getSemuaAntrian();
        
        
        List<Pelanggan> antrianList = new ArrayList<>(semuaAntrian);

        if (antrianList.isEmpty()) {
            pelangganBerikutnyaLabel.setText("Antrian Kosong");
            infoBerikutnyaLabel.setText("");

        } else {
            for (int i = 0; i < antrianList.size(); i++) {
                Pelanggan p = antrianList.get(i);
                tableModel.addRow(new Object[]{
                        (i + 1), 
                        p.getIdPesanan(),
                        p.getNamaPelanggan(),
                        p.getPesananDisplay(), 
                        p.getWaktuDaftarFormatted()
                });
            }
            Pelanggan berikutnya = antrianService.lihatBerikutnya();
            if (berikutnya != null) {
                pelangganBerikutnyaLabel.setText("Berikutnya: " + berikutnya.getNamaPelanggan());
                infoBerikutnyaLabel.setText("(ID: " + berikutnya.getIdPesanan() + ")");
            } else {
                pelangganBerikutnyaLabel.setText("Antrian Kosong");
                 infoBerikutnyaLabel.setText("");
            }
        }
    }

    private void layaniPelangganAction() {
        try {
            Pelanggan dilayani = antrianService.layaniPelanggan();
            StringBuilder pesanDialog = new StringBuilder();
            pesanDialog.append("Melayani pelanggan:\n");
            pesanDialog.append("ID Pesanan: ").append(dilayani.getIdPesanan()).append("\n");
            pesanDialog.append("Nama: ").append(dilayani.getNamaPelanggan()).append("\n");
            pesanDialog.append("Pesanan: ").append(dilayani.getPesananDisplay()).append("\n");
            pesanDialog.append("Waktu Daftar: ").append(dilayani.getWaktuDaftarFormatted()).append("\n\n");
            pesanDialog.append("Sisa antrian: ").append(antrianService.getSemuaAntrian().size()).append(" pelanggan");

            JOptionPane.showMessageDialog(this, pesanDialog.toString(), "Pelanggan Dilayani", JOptionPane.INFORMATION_MESSAGE);
            refreshTampilanAntrian(); 

            
            
            
            

        } catch (AntrianKosongException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error - Antrian Kosong", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); 
        }
    }


    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        antrianTable = new javax.swing.JTable();
        pelangganBerikutnyaLabel = new javax.swing.JLabel();
        infoBerikutnyaLabel = new javax.swing.JLabel();
        layaniButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setMaximumSize(new java.awt.Dimension(589, 575));
        setMinimumSize(new java.awt.Dimension(589, 575));
        setPreferredSize(new java.awt.Dimension(589, 575));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Daftar Antrian Pelanggan");

        antrianTable.setBackground(new java.awt.Color(204, 204, 204));
        antrianTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. Antrian", "ID Pesanan", "Nama Pelanggan", "Pesanan", "Waktu Daftar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        antrianTable.setFillsViewportHeight(true);
        antrianTable.setGridColor(new java.awt.Color(51, 51, 51));
        antrianTable.setRowHeight(22);
        antrianTable.setSelectionBackground(new java.awt.Color(0, 102, 102));
        antrianTable.setShowGrid(true);
        antrianTable.setShowHorizontalLines(true);
        antrianTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(antrianTable);
        if (antrianTable.getColumnModel().getColumnCount() > 0) {
            antrianTable.getColumnModel().getColumn(0).setResizable(false);
            antrianTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            antrianTable.getColumnModel().getColumn(1).setResizable(false);
            antrianTable.getColumnModel().getColumn(2).setResizable(false);
            antrianTable.getColumnModel().getColumn(3).setResizable(false);
            antrianTable.getColumnModel().getColumn(4).setResizable(false);
        }

        pelangganBerikutnyaLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        pelangganBerikutnyaLabel.setForeground(new java.awt.Color(255, 255, 255));
        pelangganBerikutnyaLabel.setText("Pelanggan Berikutnya : ");

        infoBerikutnyaLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); 
        infoBerikutnyaLabel.setForeground(new java.awt.Color(220, 220, 220));
        infoBerikutnyaLabel.setText("Detail berikutnya...");

        layaniButton.setBackground(new java.awt.Color(0, 80, 80));
        layaniButton.setFont(new java.awt.Font("Segoe UI", 3, 14)); 
        layaniButton.setForeground(new java.awt.Color(255, 255, 255));
        layaniButton.setText("Layani Pelanggan");
        layaniButton.setBorderPainted(false);
        layaniButton.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoBerikutnyaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pelangganBerikutnyaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(layaniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pelangganBerikutnyaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoBerikutnyaLabel))
                    .addComponent(layaniButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
        );
    }


    
    private javax.swing.JTable antrianTable;
    private javax.swing.JLabel infoBerikutnyaLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton layaniButton;
    private javax.swing.JLabel pelangganBerikutnyaLabel;
    
}