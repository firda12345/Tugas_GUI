import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame implements java.awt.event.ActionListener, java.awt.event.MouseListener {

    private JTextField txtNama;
    private JTextField txtNIM;
    private JTextField txtNilai;
    private JButton btnProses, btnReset;
    private JTextArea txtOutput;
    private JLabel lblStatus;

    public MainFrame() {
        setTitle("Aplikasi Cek Nilai & Status Kelulusan Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 8));

        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JLabel lblNama = new JLabel("Nama Mahasiswa:");
        txtNama = new JTextField();

        JLabel lblNIM = new JLabel("NIM:");
        txtNIM = new JTextField();

        JLabel lblNilai = new JLabel("Nilai Angka (0-100):");
        txtNilai = new JTextField();

        btnProses = new JButton("Proses Nilai");
        btnReset = new JButton("Reset Form");

        panelForm.add(lblNama);
        panelForm.add(txtNama);
        panelForm.add(lblNIM);
        panelForm.add(txtNIM);
        panelForm.add(lblNilai);
        panelForm.add(txtNilai);
        panelForm.add(btnProses);
        panelForm.add(btnReset);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Riwayat Hasil Cek Nilai "));

        lblStatus = new JLabel(" Status: Ready");
        lblStatus.setPreferredSize(new Dimension(getWidth(), 25));

        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnProses.addActionListener(this);
        btnReset.addActionListener(this);

        btnProses.addMouseListener(this);
        btnReset.addMouseListener(this);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnProses) {
            String nama = txtNama.getText().trim();
            String nim = txtNIM.getText().trim();
            String strNilai = txtNilai.getText().trim();

            if (nama.isEmpty() || nim.isEmpty() || strNilai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mohon isi semua data dengan lengkap!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double nilai = Double.parseDouble(strNilai);
                if (nilai < 0 || nilai > 100) {
                    JOptionPane.showMessageDialog(this, "Nilai harus berada di antara 0 sampai 100!", "Error Nilai", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String hurufMutu;
                String status;

                if (nilai >= 85) {
                    hurufMutu = "A";
                    status = "LULUS (Sangat Baik)";
                } else if (nilai >= 70) {
                    hurufMutu = "B";
                    status = "LULUS (Baik)";
                } else if (nilai >= 65) {
                    hurufMutu = "C";
                    status = "LULUS (Cukup)";
                } else if (nilai >= 40) {
                    hurufMutu = "D";
                    status = "TIDAK LULUS";
                } else {
                    hurufMutu = "E";
                    status = "TIDAK LULUS";
                }

                txtOutput.append("NIM: " + nim + " | Nama: " + nama + "\n");
                txtOutput.append("-> Nilai Angka: " + nilai + " | Huruf Mutu: " + hurufMutu + " | Status: " + status + "\n");
                txtOutput.append("--------------------------------------------------------------------------\n");

                clearForm();
                lblStatus.setText(" Status: Data nilai berhasil diproses!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nilai angka harus diisi dengan format angka yang valid!", "Format Salah", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnReset) {
            clearForm();
            txtOutput.setText("");
            lblStatus.setText(" Status: Form di-reset");
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtNilai.setText("");
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        if (e.getSource() == btnProses) {
            lblStatus.setText(" Status: Kursor berada di atas tombol Proses Nilai");
        } else if (e.getSource() == btnReset) {
            lblStatus.setText(" Status: Kursor berada di atas tombol Reset Form");
        }
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        lblStatus.setText(" Status: Ready");
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {}
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {}
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}