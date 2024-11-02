package GUI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class FrameDangKy extends JFrame {

    private JLabel JTitle;
    private JButton btnThem, btnThoat, btnXoa;
    private JLabel jCCCD, jGioiTinh, jHoTen, jMail, jMatKhau, jNgaySinh, jXNMK, jChucVu;
    private JPanel jPanel1;
    private JRadioButton RadNu, RadNam;
    private JComboBox<String> cmbChucVu;
    private JTextField txtCCCD, txtHoTen, txtMail, txtNgaySinh;
    private JPasswordField txtMatKhau, txtXNMK;
    private ButtonGroup genderGroup;
    private String backgroundPath = "image\\BG_SignIn.jpg";

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private float opacity;

        public BackgroundPanel(String imagePath, float opacity) {
            this.opacity = opacity;
            try {
                File file = new File(imagePath);
                if (file.exists()) {
                    backgroundImage = new ImageIcon(imagePath).getImage();
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Không tìm thấy file hình nền: " + imagePath,
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    setBackground(new Color(240, 240, 240));
                }
            } catch (Exception e) {
                e.printStackTrace();
                setBackground(new Color(240, 240, 240));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        }
    }

    public FrameDangKy() {
        setResizable(false);
        setTitle("Quản lý cửa hàng tiện lợi");
        initComponents();
    }

    private void initComponents() {
        Color mainColor = new Color(60, 179, 113);
        Color btnColor = new Color(50, 150, 90);
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        BackgroundPanel mainPanel = new BackgroundPanel(backgroundPath, 0.1f);
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        jPanel1 = new JPanel(new GridLayout(9, 2, 10, 10));
        jPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setOpaque(false);

        JTitle = new JLabel("THÊM TÀI KHOẢN MỚI", SwingConstants.CENTER);
        JTitle.setFont(new Font("Arial", Font.BOLD, 28));
        JTitle.setForeground(Color.BLACK);
        JTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Thiết lập label và font
        jHoTen = new JLabel("Họ tên:");
        jNgaySinh = new JLabel("Ngày sinh (dd/MM/yyyy):");
        jMail = new JLabel("Email:");
        jGioiTinh = new JLabel("Giới tính:");
        jCCCD = new JLabel("CCCD:");
        jMatKhau = new JLabel("Mật khẩu:");
        jXNMK = new JLabel("Nhập lại mật khẩu:");
        jChucVu = new JLabel("Chức vụ:");

        JLabel[] labels = {jHoTen, jNgaySinh, jMail, jGioiTinh, jCCCD, jMatKhau, jXNMK, jChucVu};
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(Color.BLACK);
        }

        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        txtHoTen = new JTextField(15);
        txtNgaySinh = new JTextField(15);
        txtMail = new JTextField(15);
        txtCCCD = new JTextField(15);
        txtMatKhau = new JPasswordField(15);
        txtXNMK = new JPasswordField(15);

        // ComboBox cho chức vụ
        cmbChucVu = new JComboBox<>(new String[]{"Quản lý", "Nhân viên"});
        cmbChucVu.setFont(inputFont);

        // Radio button cho giới tính
        RadNam = new JRadioButton("Nam");
        RadNu = new JRadioButton("Nữ");
        RadNam.setFont(inputFont);
        RadNu.setFont(inputFont);
        RadNam.setOpaque(false);
        RadNu.setOpaque(false);
        genderGroup = new ButtonGroup();
        genderGroup.add(RadNam);
        genderGroup.add(RadNu);
        JPanel genderPanel = new JPanel();
        genderPanel.setOpaque(false);
        genderPanel.add(RadNam);
        genderPanel.add(RadNu);

        // Các nút chức năng
        btnThem = new JButton("Đăng ký");
        btnXoa = new JButton("Xóa");
        btnThoat = new JButton("Thoát");

        JButton[] buttons = {btnThem, btnXoa, btnThoat};
        Dimension buttonSize = new Dimension(120, 35);
        for (JButton button : buttons) {
            button.setBackground(btnColor);
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setPreferredSize(buttonSize);
        }

        btnThem.addActionListener(evt -> btnThemActionPerformed());
        btnXoa.addActionListener(evt -> btnXoaActionPerformed());
        btnThoat.addActionListener(evt -> btnThoatActionPerformed());

        // Thêm các thành phần vào panel
        jPanel1.add(jHoTen);
        jPanel1.add(txtHoTen);
        jPanel1.add(jNgaySinh);
        jPanel1.add(txtNgaySinh);
        jPanel1.add(jMail);
        jPanel1.add(txtMail);
        jPanel1.add(jGioiTinh);
        jPanel1.add(genderPanel);
        jPanel1.add(jCCCD);
        jPanel1.add(txtCCCD);
        jPanel1.add(jMatKhau);
        jPanel1.add(txtMatKhau);
        jPanel1.add(jXNMK);
        jPanel1.add(txtXNMK);
        jPanel1.add(jChucVu);
        jPanel1.add(cmbChucVu);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnThoat);

        mainPanel.add(JTitle, BorderLayout.NORTH);
        mainPanel.add(jPanel1, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private boolean kiemTraTrungCCCD(String cCCD) {
        String sql = "SELECT COUNT(*) FROM TaiKhoanNV WHERE CCCD = ?";
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cCCD);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Trả về true nếu có ít nhất 1 bản ghi
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    private void btnThemActionPerformed() {
        // Lấy thông tin từ các trường nhập liệu
        String hoTen = txtHoTen.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String email = txtMail.getText().trim();
        String cCCD = txtCCCD.getText().trim();
        String matKhau = new String(txtMatKhau.getPassword()).trim();
        String xacNhanMatKhau = new String(txtXNMK.getPassword()).trim();
        String chucVu = (String) cmbChucVu.getSelectedItem();

        // Kiểm tra lựa chọn giới tính
        String gioiTinh = null;
        if (RadNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (RadNu.isSelected()) {
            gioiTinh = "Nữ";
        }

        // Kiểm tra tính hợp lệ của dữ liệu nhập vào
        if (hoTen.isEmpty() || ngaySinh.isEmpty() || email.isEmpty() || cCCD.isEmpty() || matKhau.isEmpty() || gioiTinh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra định dạng ngày sinh và email bằng regex
        if (!Pattern.matches("^\\d{2}/\\d{2}/\\d{4}$", ngaySinh)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra mật khẩu khớp nhau
        if (!matKhau.equals(xacNhanMatKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Kiểm tra trùng CCCD
        if (kiemTraTrungCCCD(cCCD)) {
            JOptionPane.showMessageDialog(this, "CCCD đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo đối tượng TaiKhoan
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setHoTen(hoTen);
        taiKhoan.setNgaySinh(ngaySinh);
        taiKhoan.setEmail(email);
        taiKhoan.setGioiTinh(gioiTinh);
        taiKhoan.setCCCD(cCCD);
        taiKhoan.setMatKhau(matKhau);
        taiKhoan.setChucVu(chucVu);  // Đặt chức vụ

        // Gọi phương thức để thêm tài khoản vào cơ sở dữ liệu
        if (themTaiKhoanVaoDatabase(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            btnXoaActionPerformed(); // Xóa trắng các trường sau khi đăng ký thành công
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean themTaiKhoanVaoDatabase(TaiKhoan taiKhoan) {
        String sql = "INSERT INTO TaiKhoanNV (MANV, HoTen, NgaySinh, Email, GioiTinh, cCCD, MatKhau, ChucVu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String maNV = generateMaNV(conn);
            pstmt.setString(1, maNV);
            pstmt.setString(2, taiKhoan.getHoTen());
            pstmt.setString(3, taiKhoan.getNgaySinh());
            pstmt.setString(4, taiKhoan.getEmail());
            pstmt.setString(5, taiKhoan.getGioiTinh());
            pstmt.setString(6, taiKhoan.getCCCD());
            pstmt.setString(7, taiKhoan.getMatKhau());
            pstmt.setString(8, taiKhoan.getChucVu());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    private String generateMaNV(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TOP 1 MANV FROM TaiKhoanNV ORDER BY MANV DESC")) {
            if (rs.next()) {
                String lastMaNV = rs.getString("MANV");
                int num = Integer.parseInt(lastMaNV.substring(2)) + 1;
                return String.format("NV%d", num);
            }
            return "NV1";
        }
    }

    private void btnXoaActionPerformed() {
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtMail.setText("");
        genderGroup.clearSelection();
        txtCCCD.setText("");
        txtMatKhau.setText("");
        txtXNMK.setText("");
        cmbChucVu.setSelectedIndex(0);
    }

    private void btnThoatActionPerformed() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrameDangKy().setVisible(true);
        });
    }
}

