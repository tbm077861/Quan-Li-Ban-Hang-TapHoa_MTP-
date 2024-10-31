package quanLyCuaHangTienLoi;

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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;



public class frmDangKy extends JFrame {

    private JLabel JTitle;
    private JButton btnThem;
    private JButton btnThoat;
    private JButton btnXoa;
    private JLabel jDiaChi;
    private JLabel jGioiTinh;
    private JLabel jHoTen;
    private JLabel jMail;
    private JLabel jMatKhau;
    private JLabel jNgaySinh;
    private JLabel jXNMK;
    private JPanel jPanel1;
    private JRadioButton RadNu;
    private JRadioButton RadNam;
    private JTextField txtDiaChi;
    private JTextField txtHoTen;
    private JTextField txtMail;
    private JPasswordField txtMatKhau;
    private JTextField txtNgaySinh;
    private JPasswordField txtXNMK;
    private ButtonGroup genderGroup;
    private String backgroundPath = "hinhanh\\BG_SignIn.jpg"; // Đường dẫn mặc định

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private float opacity;

        public BackgroundPanel(String imagePath, float opacity) {
            this.opacity = opacity;
            try {
                File file = new File(imagePath);
                if(file.exists()) {
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

    public frmDangKy() {
        setResizable(false);
        setTitle("Quản lý cửa hàng tiện lợi");
        initComponents();
    }

    private void initComponents() {
        // Thiết lập màu nền và font chữ
        Color mainColor = new Color(60, 179, 113);
        Color btnColor = new Color(50, 150, 90);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        
        // Tạo panel nền với hình ảnh
        BackgroundPanel mainPanel = new BackgroundPanel(backgroundPath, 0.1f);
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        
        jPanel1 = new JPanel(new GridLayout(8, 2, 10, 10)); // Giảm xuống 8 hàng vì bớt 1 trường
        jPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setOpaque(false);
        
        JTitle = new JLabel("THÊM TÀI KHOẢN MỚI", SwingConstants.CENTER);
        JTitle.setFont(new Font("Arial", Font.BOLD, 28));
        JTitle.setForeground(Color.BLACK);
        JTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Thiết lập font và màu cho các label
        jHoTen = new JLabel("Họ tên:");
        jNgaySinh = new JLabel("Ngày sinh (dd/MM/yyyy):");
        jMail = new JLabel("Email:");
        jGioiTinh = new JLabel("Giới tính:");
        jDiaChi = new JLabel("Địa chỉ:");
        jMatKhau = new JLabel("Mật khẩu:");
        jXNMK = new JLabel("Nhập lại mật khẩu:");

        JLabel[] labels = {jHoTen, jNgaySinh, jMail, jGioiTinh, jDiaChi, jMatKhau, jXNMK};
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(Color.BLACK);
        }
        
        // Thiết lập các trường nhập liệu
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        txtHoTen = new JTextField(15);
        txtNgaySinh = new JTextField(15);
        txtMail = new JTextField(15);
        txtDiaChi = new JTextField(15);
        txtMatKhau = new JPasswordField(15);
        txtXNMK = new JPasswordField(15);

        JTextField[] textFields = {txtHoTen, txtNgaySinh, txtMail, txtDiaChi};
        for (JTextField field : textFields) {
            field.setFont(inputFont);
        }
        txtMatKhau.setFont(inputFont);
        txtXNMK.setFont(inputFont);
        
        // Thiết lập radio buttons
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
        
        // Thiết lập các nút
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
        
        // Sắp xếp các thành phần trên giao diện
        jPanel1.add(jHoTen);
        jPanel1.add(txtHoTen);
        jPanel1.add(jNgaySinh);
        jPanel1.add(txtNgaySinh);
        jPanel1.add(jMail);
        jPanel1.add(txtMail);
        jPanel1.add(jGioiTinh);
        jPanel1.add(genderPanel);
        jPanel1.add(jDiaChi);
        jPanel1.add(txtDiaChi);
        jPanel1.add(jMatKhau);
        jPanel1.add(txtMatKhau);
        jPanel1.add(jXNMK);
        jPanel1.add(txtXNMK);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnThoat);
        
        // Thiết lập layout chính cho giao diện
        mainPanel.add(JTitle, BorderLayout.NORTH);
        mainPanel.add(jPanel1, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private boolean themTaiKhoanVaoDatabase(TaiKhoan taiKhoan) {
        String sql = "INSERT INTO TaiKhoan (HoTen, NgaySinh, Email, GioiTinh, DiaChi, MatKhau) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection("QuanLyCuaHangTienLoi");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, taiKhoan.getHoTen());
            pstmt.setString(2, taiKhoan.getNgaySinh());
            pstmt.setString(3, taiKhoan.getEmail());
            pstmt.setString(4, taiKhoan.getGioiTinh());
            pstmt.setString(5, taiKhoan.getDiaChi());
            pstmt.setString(6, taiKhoan.getMatKhau());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi thêm tài khoản: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void btnThemActionPerformed() {
        // Kiểm tra dữ liệu đầu vào
        if (txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống.");
            txtHoTen.requestFocus();
            return;
        }

        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống.");
            txtNgaySinh.requestFocus();
            return;
        } else if (!txtNgaySinh.getText().matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd/MM/yyyy.");
            txtNgaySinh.requestFocus();
            return;
        }

        if (txtMail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống.");
            txtMail.requestFocus();
            return;
        } else if (!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(txtMail.getText()).matches()) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng.");
            txtMail.requestFocus();
            return;
        }

        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
            txtDiaChi.requestFocus();
            return;
        }

        if (String.valueOf(txtMatKhau.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống.");
            txtMatKhau.requestFocus();
            return;
        }

        if (String.valueOf(txtXNMK.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại mật khẩu.");
            txtXNMK.requestFocus();
            return;
        }

        if (!String.valueOf(txtMatKhau.getPassword()).equals(String.valueOf(txtXNMK.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không khớp. Vui lòng nhập lại.");
            txtXNMK.requestFocus();
            return;
        }

        String hoTen = txtHoTen.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String email = txtMail.getText().trim();
        String gioiTinh = RadNam.isSelected() ? "Nam" : "Nữ";
        String diaChi = txtDiaChi.getText().trim();
        String matKhau = String.valueOf(txtMatKhau.getPassword());

        TaiKhoan taiKhoan = new TaiKhoan(hoTen, ngaySinh, email, gioiTinh, diaChi, matKhau);

        if (themTaiKhoanVaoDatabase(taiKhoan)) {
            JOptionPane.showMessageDialog(this, 
                "Đăng ký thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
            btnXoaActionPerformed(); // Clear form
        } else {
            JOptionPane.showMessageDialog(this,
                "Đăng ký thất bại. Vui lòng thử lại.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnXoaActionPerformed() {
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtMail.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        txtXNMK.setText("");
        genderGroup.clearSelection();
    }

    private void btnThoatActionPerformed() {
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ứng dụng không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> new frmDangKy().setVisible(true));
    }
}