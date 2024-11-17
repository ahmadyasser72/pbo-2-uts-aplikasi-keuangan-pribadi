/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ahmad.yasser.uts.aplikasi.keuangan.pribadi;

import ahmad.yasser.uts.aplikasi.keuangan.pribadi.database.Account;
import ahmad.yasser.uts.aplikasi.keuangan.pribadi.database.TransactionType;
import ahmad.yasser.uts.aplikasi.keuangan.pribadi.database.Transactions;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author x
 */
public class JFrameAplikasiKeuanganPribadi extends javax.swing.JFrame {

    private List<Transactions> records;
    private TransactionTableModel tableModel = new TransactionTableModel();

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final Utilities utils;

    // Konstruktor untuk JFrame aplikasi keuangan pribadi
    // - Menginisialisasi komponen GUI
    // - Menentukan lokasi jendela agar muncul di tengah layar
    // - Membuat objek utilitas untuk mempermudah beberapa operasi umum
    // - Menginisialisasi koneksi ke database dan keluar jika terjadi kesalahan
    // - Mengisi data pada ComboBox dan mengatur ulang formulir
    public JFrameAplikasiKeuanganPribadi() {
        initComponents(); // Menginisialisasi komponen GUI dari form
        this.setLocationRelativeTo(null); // Menentukan lokasi jendela di tengah layar
        this.utils = new Utilities(this); // Membuat instance utilitas untuk berbagai fungsi

        try {
            Transactions.initializeConnection(); // Menginisialisasi koneksi ke database
        } catch (SQLException ex) {
            // Menampilkan pesan error dan keluar jika koneksi SQLite gagal
            utils.showErrorDialog(ex, "koneksi ke sqlite");
            System.exit(1);
        }

        this.populateComboBoxItems(); // Mengisi item pada ComboBox
        this.resetForm(); // Mengatur ulang tampilan awal formulir
    }

    // Mengisi ComboBox dengan data dari enum Account dan TransactionType
    private void populateComboBoxItems() {
        var accountModel = (DefaultComboBoxModel) jComboBoxAccount.getModel();
        var accountFilterModel = (DefaultComboBoxModel) jComboBoxAccountFilter.getModel();
        for (var value : Account.values()) {
            accountModel.addElement(value.getName()); // Tambahkan nama akun ke ComboBox akun
            accountFilterModel.addElement(value.getName()); // Tambahkan nama akun ke ComboBox filter akun
        }

        var transactionTypeModel = (DefaultComboBoxModel) jComboBoxTransactionType.getModel();
        var transactionTypeFilterModel = (DefaultComboBoxModel) jComboBoxTransactionTypeFilter.getModel();
        for (var value : TransactionType.values()) {
            transactionTypeModel.addElement(value.getName()); // Tambahkan jenis transaksi ke ComboBox jenis transaksi
            transactionTypeFilterModel.addElement(value.getName()); // Tambahkan jenis transaksi ke ComboBox filter transaksi
        }
    }

    // Mengambil semua data transaksi dari database dan menyimpannya dalam properti `records`
    private void populateRecords() {
        try {
            this.records = Transactions.getAllTransactions(); // Mengambil semua transaksi dari database
        } catch (SQLException ex) {
            utils.showErrorDialog(ex, "mendapatkan data transaksi"); // Menampilkan dialog error jika terjadi kesalahan
        }
    }

    // Memperbarui tampilan tabel berdasarkan filter akun dan tipe transaksi
    private void updateTableView() {
        var filterAkun = (String) jComboBoxAccountFilter.getSelectedItem(); // Filter akun yang dipilih
        var filterTipeTransaksi = (String) jComboBoxTransactionTypeFilter.getSelectedItem(); // Filter tipe transaksi yang dipilih

        var filteredRecords = new ArrayList<Transactions>(); // Daftar untuk menyimpan transaksi yang telah difilter
        for (var entry : this.records) { // Iterasi setiap transaksi
            // Tambahkan transaksi ke daftar jika memenuhi kriteria filter
            if ((filterAkun.equals("-") || filterAkun.equals(entry.account.getName()))
                    && (filterTipeTransaksi.equals("-") || filterTipeTransaksi.equals(entry.transactionType.getName()))) {
                filteredRecords.add(entry);
            }
        }

        this.tableModel.setRecords(filteredRecords); // Mengisi model tabel dengan data yang telah difilter
        jTable1.getSelectionModel().clearSelection(); // Menghapus seleksi di tabel
    }

    // Mengatur ulang formulir ke kondisi awal
    private void resetForm() {
        this.populateRecords(); // Muat ulang data transaksi
        this.updateTableView(); // Perbarui tampilan tabel
        this.resetControls(); // Reset kontrol form
    }

    // Mengatur ulang kontrol UI (tombol dan input)
    private void resetControls() {
        jButtonTambah.setEnabled(true); // Aktifkan tombol Tambah
        jButtonUpdate.setEnabled(false); // Nonaktifkan tombol Update
        jButtonHapus.setEnabled(false); // Nonaktifkan tombol Hapus
        utils.clearInput( // Kosongkan input form
                jDateChooserDate, jSpinnerAmount, jComboBoxAccount, jComboBoxTransactionType, jTextAreaNote
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextFieldId = new javax.swing.JTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserDate = new com.toedter.calendar.JDateChooser();
        jSpinnerAmount = new javax.swing.JSpinner();
        jComboBoxTransactionType = new javax.swing.JComboBox<>();
        jComboBoxAccount = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButtonTambah = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxTransactionTypeFilter = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxAccountFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(320);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Tanggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nominal (Rp. )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Tipe Transaksi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Pilih Akun");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel4, gridBagConstraints);

        jDateChooserDate.setDateFormatString("yyyy-MM-dd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jDateChooserDate, gridBagConstraints);

        jSpinnerAmount.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1000.0d));
        jSpinnerAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jSpinnerAmount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jComboBoxTransactionType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jComboBoxAccount, gridBagConstraints);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 88));

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Keterangan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel1.add(jLabel5, gridBagConstraints);

        jButtonTambah.setText("Tambah");
        jButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButtonTambah, gridBagConstraints);

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButtonUpdate, gridBagConstraints);

        jButtonHapus.setText("Hapus");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButtonHapus, gridBagConstraints);

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButtonReset, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 200));

        jTable1.setModel(tableModel);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel3.setLayout(new java.awt.GridLayout(1, 4, 4, 4));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Tipe Transaksi");
        jPanel3.add(jLabel6);

        jComboBoxTransactionTypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBoxTransactionTypeFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTransactionTypeFilterActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBoxTransactionTypeFilter);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Akun");
        jPanel3.add(jLabel7);

        jComboBoxAccountFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBoxAccountFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAccountFilterActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBoxAccountFilter);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahActionPerformed
        // Ambil transaksi dari input pengguna sebagai Optional
        var transaksiOpt = this.getTransactionFromInput();

        // Jika tidak ada transaksi valid, keluar dari metode
        if (transaksiOpt.isEmpty()) {
            return;
        }

        // Ambil transaksi dari Optional
        var transaksi = transaksiOpt.get();

        try {
            // Tambahkan transaksi ke database
            transaksi.insert();

            // Tampilkan pesan sukses dan reset formulir
            utils.showInformationDialog("data berhasil ditambah!");
            this.resetForm();
        } catch (SQLException ex) {
            // Tangani kesalahan SQL dengan dialog error
            utils.showErrorDialog(ex, "menambah data");
        }

    }//GEN-LAST:event_jButtonTambahActionPerformed

    private void jComboBoxTransactionTypeFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTransactionTypeFilterActionPerformed
        updateTableView();
        resetControls();
    }//GEN-LAST:event_jComboBoxTransactionTypeFilterActionPerformed

    private void jComboBoxAccountFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAccountFilterActionPerformed
        updateTableView();
        resetControls();
    }//GEN-LAST:event_jComboBoxAccountFilterActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        resetForm();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Ambil baris yang diklik pada tabel
        var rowIndex = jTable1.rowAtPoint(evt.getPoint());

        // Jika tidak ada baris yang dipilih, keluar dari metode
        if (rowIndex < 0) {
            return;
        }

        // Ambil transaksi yang sesuai dengan baris yang dipilih
        var transaksi = tableModel.getEntry(rowIndex);

        try {
            // Isi form dengan data transaksi yang dipilih
            jTextFieldId.setText(transaksi.id.toString());  // Set ID transaksi
            jDateChooserDate.setDate(dateFormatter.parse(transaksi.date));  // Set tanggal transaksi
            jSpinnerAmount.setValue(transaksi.amount);  // Set jumlah transaksi
            jComboBoxTransactionType.setSelectedItem(transaksi.transactionType.getName());  // Set tipe transaksi
            jComboBoxAccount.setSelectedItem(transaksi.account.getName());  // Set akun transaksi
            jTextAreaNote.setText(transaksi.note);  // Set catatan transaksi
        } catch (ParseException ex) {
            // Tangani kesalahan format tanggal jika parsing gagal
            utils.showErrorDialog(ex, "memilih entry");
        }

        // Atur tombol sesuai dengan status transaksi yang dipilih
        jButtonTambah.setEnabled(false);  // Nonaktifkan tombol Tambah
        jButtonUpdate.setEnabled(true);   // Aktifkan tombol Update
        jButtonHapus.setEnabled(true);    // Aktifkan tombol Hapus

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // Ambil transaksi dari input pengguna sebagai Optional
        var transaksiOpt = this.getTransactionFromInput();

        // Jika tidak ada transaksi valid, keluar dari metode
        if (transaksiOpt.isEmpty()) {
            return;
        }

        // Ambil transaksi dari Optional
        var transaksi = transaksiOpt.get();

        try {
            // Perbarui transaksi
            transaksi.update();

            // Tampilkan pesan sukses dan reset formulir
            utils.showInformationDialog("data berhasil diupdate!");
            this.resetForm();
        } catch (SQLException ex) {
            // Tangani kesalahan SQL dengan dialog error
            utils.showErrorDialog(ex, "update data");
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        // Ambil transaksi dari input pengguna sebagai Optional
        var transaksiOpt = this.getTransactionFromInput();

        // Jika tidak ada transaksi valid, keluar dari metode
        if (transaksiOpt.isEmpty()) {
            return;
        }

        // Ambil transaksi dari Optional
        var transaksi = transaksiOpt.get();

        try {
            // Tampilkan dialog konfirmasi sebelum menghapus transaksi
            if (utils.showConfirmDialog(
                    "hapus transaksi %s %s tanggal %s?".formatted(
                            transaksi.transactionType.getName(), // Tipe transaksi
                            transaksi.account.getName(), // Akun terkait
                            transaksi.date // Tanggal transaksi
                    )
            )) {
                // Hapus transaksi jika dikonfirmasi
                transaksi.delete();

                // Tampilkan pesan sukses dan reset formulir
                utils.showInformationDialog("data berhasil dihapus!");
                this.resetForm();
            }
        } catch (SQLException ex) {
            // Tangani kesalahan SQL dengan dialog error
            utils.showErrorDialog(ex, "hapus data");
        }

    }//GEN-LAST:event_jButtonHapusActionPerformed

    private Optional<Transactions> getTransactionFromInput() {
        if (utils.validasiTidakKosong(jDateChooserDate, "transaksi")
                || utils.validasiTidakKosong(jComboBoxTransactionType, "tipe transaksi")
                || utils.validasiTidakKosong(jComboBoxAccount, "tipe akun")
                || utils.validasiTidakKosong(jTextAreaNote, "keterangan")) {
            return Optional.empty();
        }

        // Mendapatkan tanggal dari input dan format jadi string
        var date = jDateChooserDate.getDate();
        var dateString = dateFormatter.format(date);

        // Mendapatkan nominal dari input
        var amount = (Double) jSpinnerAmount.getValue();

        // Mendapatkan tipe transaksi dari combo box
        var transactionTypeId = jComboBoxTransactionType.getSelectedIndex() + 1;
        var transactionType = TransactionType.fromId(transactionTypeId);

        // Mendapatkan akun dari combo box
        var accountId = jComboBoxAccount.getSelectedIndex() + 1;
        var account = Account.fromId(accountId);

        // Mendapatkan keterangan dari text area
        var note = jTextAreaNote.getText().trim();

        // Mendapatkan id dari hidden text field
        var id = jTextFieldId.getText();

        // Membuat dan mengembalikan objek Transactions
        return Optional.of(
                new Transactions(
                        id.isEmpty() ? null : Integer.valueOf(id),
                        dateString,
                        amount,
                        transactionType,
                        account,
                        note)
        );
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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAplikasiKeuanganPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameAplikasiKeuanganPribadi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonTambah;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxAccount;
    private javax.swing.JComboBox<String> jComboBoxAccountFilter;
    private javax.swing.JComboBox<String> jComboBoxTransactionType;
    private javax.swing.JComboBox<String> jComboBoxTransactionTypeFilter;
    private com.toedter.calendar.JDateChooser jDateChooserDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerAmount;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldId;
    // End of variables declaration//GEN-END:variables
}
