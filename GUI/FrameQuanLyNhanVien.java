package quanLyCuaHangTienLoi;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

public class FrameQuanLyNhanVien extends JFrame {

private static final long serialVersionUID = 1L;
private JPanel pnlBackGround;
private JTextField txtMaNhanVien;
private JTextField txtTenNhanVien;
private JTextField txtCanCuoc;
private JTextField txtEmailNhanVien;
private JTable tableNhanVien;
private JTextField txtMaNhanVienTim;
private JTextField txtTenNhanVienTim;
private JTextField txtCanCuocTim;
private JDateChooser txtNgaySinhNhanVien;
private JComboBox<String> txtGioiTinhTim, txtChucVu;
private DefaultTableModel tableModel;
private JTextField txtMatKhau;
private boolean isEditing = false;

// Cập nhật dữ liệu vào table
private void loadDataToTable() {
    tableModel = (DefaultTableModel) tableNhanVien.getModel();
    tableModel.setRowCount(0);

    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
         PreparedStatement stmt = conn.prepareStatement(
            "SELECT MANV, HoTen, GioiTinh, Email, NgaySinh, CCCD, MatKhau, ChucVu FROM TaiKhoanNV")) {

        ResultSet rs = stmt.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            // Chuyển đổi ngày sinh từ cơ sở dữ liệu sang định dạng yyyy-MM-dd
            String ngaySinh = sdf.format(rs.getDate("NgaySinh"));

            Object[] row = {
                rs.getString("MANV"),
                rs.getString("HoTen"),
                rs.getString("GioiTinh"),
                rs.getString("Email"),
                ngaySinh,
                rs.getString("CCCD"),
                rs.getString("MatKhau"),
                rs.getString("ChucVu")
            };
            tableModel.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
    }
}






public class KiemTraNhap {
    public static String validateID(String id) {
        if (id == null || id.isEmpty()) {
            return "Mã nhân viên không được để trống!";
        }
        String idRegex = "^NV\\d+$";
        if (!Pattern.matches(idRegex, id)) {
            return "Mã nhân viên không hợp lệ! Mã nhân viên phải bắt đầu bằng 'NV' và theo sau là các chữ số.";
        }
        return null;
    }

    public static String validateCCCD(String cccd) {
        if (cccd == null || cccd.isEmpty()) {
            return "Căn cước không được để trống!";
        }
        String cccdRegex = "^\\d{12}$";
        if (!Pattern.matches(cccdRegex, cccd)) {
            return "Căn cước không hợp lệ! Căn cước phải là chuỗi gồm 12 chữ số.";
        }
        return null;
    }

    public static String validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return "Số điện thoại không được để trống!";
        }
        String phoneRegex = "^\\d{10}$";
        if (!Pattern.matches(phoneRegex, phone)) {
            return "Số điện thoại không hợp lệ! Số điện thoại phải là chuỗi gồm 10 chữ số.";
        }
        return null;
    }

    public static String validateDate(String date) {
        if (date == null || date.isEmpty()) {
            return "Ngày không được để trống!";
        }
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(dateRegex, date)) {
            return "Ngày không hợp lệ! Ngày phải theo định dạng yyyy-MM-dd.";
        }
        return null;
    }

    public static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return "Họ và tên không được để trống!";
        }
        String nameRegex = "^[\\p{L} ]+$";
        if (!Pattern.matches(nameRegex, name)) {
            return "Họ và tên không hợp lệ! Họ và tên chỉ được chứa các ký tự chữ cái và khoảng trắng.";
        }
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Mật khẩu không được để trống!";
        }
        return null;
    }

    public static String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Email không được để trống!";
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!Pattern.matches(emailRegex, email)) {
            return "Email không hợp lệ! Vui lòng nhập đúng định dạng email.";
        }
        return null;
    }
}


private boolean isDuplicateCCCD(String cccd) {
    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TaiKhoanNV WHERE CCCD = ?")) {
        pstmt.setString(1, cccd);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra CCCD: " + e.getMessage());
    }
    return false;
}

private boolean isDuplicateEmail(String email) {
    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
         PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TaiKhoanNV WHERE Email = ?")) {
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra Email: " + e.getMessage());
    }
    return false;
}


// Sự kiện nút Lưu



private void btnLuuActionPerformed() {
    String maNV = txtMaNhanVien.getText().trim();
    String hoTen = txtTenNhanVien.getText().trim();
    String email = txtEmailNhanVien.getText().trim();
    String cccd = txtCanCuoc.getText().trim();
    String gioiTinh = txtGioiTinhTim.getSelectedItem().toString();
    String matKhau = txtMatKhau.getText().trim();
    String chucVu = txtChucVu.getSelectedItem().toString();

    String error;

    error = KiemTraNhap.validateID(maNV);
    if (error != null) {
        JOptionPane.showMessageDialog(this, "Error: " + error);
        txtMaNhanVien.requestFocus();
        return;
    }

    error = KiemTraNhap.validateName(hoTen);
    if (error != null) {
        JOptionPane.showMessageDialog(this, "Error: " + error);
        txtTenNhanVien.requestFocus();
        return;
    }

    error = KiemTraNhap.validateEmail(email);
    if (error != null) {
        JOptionPane.showMessageDialog(this, "Error: " + error);
        txtEmailNhanVien.requestFocus();
        return;
    }

    error = KiemTraNhap.validateCCCD(cccd);
    if (error != null) {
        JOptionPane.showMessageDialog(this, "Error: " + error);
        txtCanCuoc.requestFocus();
        return;
    }

    error = KiemTraNhap.validatePassword(matKhau);
    if (error != null) {
        JOptionPane.showMessageDialog(this, "Error: " + error);
        txtMatKhau.requestFocus();
        return;
    }

    if (!isEditing && isDuplicateCCCD(cccd)) {
        JOptionPane.showMessageDialog(this, "Error: Căn cước đã tồn tại! Vui lòng kiểm tra lại thông tin.");
        txtCanCuoc.requestFocus();
        return;
    }

    if (!isEditing && isDuplicateEmail(email)) {
        JOptionPane.showMessageDialog(this, "Error: Email đã tồn tại! Vui lòng kiểm tra lại thông tin.");
        txtEmailNhanVien.requestFocus();
        return;
    }

    Date ngaySinh = txtNgaySinhNhanVien.getDate();
    if (ngaySinh == null) {
        JOptionPane.showMessageDialog(this, "Error: Ngày sinh không hợp lệ! Vui lòng chọn ngày sinh.");
        txtNgaySinhNhanVien.requestFocus();
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String ngaySinhStr = sdf.format(ngaySinh);

    try (Connection conn = ConnectDB.getConnection("DB_QLBH")) {
        String sql;
        if (isEditing) {
            sql = "UPDATE TaiKhoanNV SET HoTen=?, NgaySinh=?, Email=?, GioiTinh=?, CCCD=?, MatKhau=?, ChucVu=? WHERE MANV=?";
        } else {
            sql = "INSERT INTO TaiKhoanNV (MANV, HoTen, NgaySinh, Email, GioiTinh, CCCD, MatKhau, ChucVu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (isEditing) {
                pstmt.setString(1, hoTen);
                pstmt.setString(2, ngaySinhStr);
                pstmt.setString(3, email);
                pstmt.setString(4, gioiTinh);
                pstmt.setString(5, cccd);
                pstmt.setString(6, matKhau);
                pstmt.setString(7, chucVu);
                pstmt.setString(8, maNV);
            } else {
                maNV = generateMaNV(conn);
                pstmt.setString(1, maNV);
                pstmt.setString(2, hoTen);
                pstmt.setString(3, ngaySinhStr);
                pstmt.setString(4, email);
                pstmt.setString(5, gioiTinh);
                pstmt.setString(6, cccd);
                pstmt.setString(7, matKhau);
                pstmt.setString(8, chucVu);
            }

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, isEditing ? "Cập nhật thành công!" : "Thêm mới thành công!");
            loadDataToTable();
            clearFields();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
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

// Sự kiện nút Sửa
private void btnSuaActionPerformed() {
    int selectedRow = tableNhanVien.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!");
        return;
    }

    isEditing = true;
    txtMaNhanVien.setEditable(false);

    // Load dữ liệu từ bảng vào form
    txtMaNhanVien.setText(tableModel.getValueAt(selectedRow, 0).toString());
    txtTenNhanVien.setText(tableModel.getValueAt(selectedRow, 1).toString());
    txtGioiTinhTim.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
    txtEmailNhanVien.setText(tableModel.getValueAt(selectedRow, 3).toString());
    txtCanCuoc.setText(tableModel.getValueAt(selectedRow, 5).toString());
    txtMatKhau.setText(tableModel.getValueAt(selectedRow, 6).toString());
    txtChucVu.setSelectedItem(tableModel.getValueAt(selectedRow, 7));

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = sdf.parse(tableModel.getValueAt(selectedRow, 4).toString());
        txtNgaySinhNhanVien.setDate(ngaySinh);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày sinh!");
    }
}

// Sự kiện nút Xóa
private void btnXoaActionPerformed() {
    int selectedRow = tableNhanVien.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Bạn có chắc chắn muốn xóa nhân viên này?",
        "Xác nhận xóa",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        String maNV = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM TaiKhoanNV WHERE MANV = ?")) {

            pstmt.setString(1, maNV);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadDataToTable();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
        }
    }
}

// Sự kiện nút Tìm kiếm
private void btnTimActionPerformed() {
    String maNV = txtMaNhanVienTim.getText().trim();
    String hoTen = txtTenNhanVienTim.getText().trim();
    String cccd = txtCanCuocTim.getText().trim();
    String gioiTinh = txtGioiTinhTim.getSelectedItem().toString();

    StringBuilder sql = new StringBuilder(
        "SELECT MANV, HoTen, GioiTinh, Email, NgaySinh, CCCD FROM TaiKhoanNV WHERE 1=1");
    List<Object> params = new ArrayList<>();

    if (!maNV.isEmpty()) {
        sql.append(" AND MANV LIKE ?");
        params.add("%" + maNV + "%");
    }
    if (!hoTen.isEmpty()) {
        sql.append(" AND HoTen LIKE ?");
        params.add("%" + hoTen + "%");
    }
    if (!cccd.isEmpty()) {
        sql.append(" AND CCCD LIKE ?");
        params.add("%" + cccd + "%");
    }
    if (!gioiTinh.isEmpty()) {
        sql.append(" AND GioiTinh = ?");
        params.add(gioiTinh);
    }

    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
         PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

        for (int i = 0; i < params.size(); i++) {
            pstmt.setObject(i + 1, params.get(i));
        }

        ResultSet rs = pstmt.executeQuery();
        tableModel.setRowCount(0);

        while (rs.next()) {
            Object[] row = {
                rs.getString("MANV"),
                rs.getString("HoTen"),
                rs.getString("GioiTinh"),
                rs.getString("Email"),
                rs.getString("NgaySinh"),
                rs.getString("CCCD")
            };
            tableModel.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
    }
}

// Sự kiện nút Tải lại
private void btnTaiLaiActionPerformed() {
    clearSearchFields();
    loadDataToTable();
}

// Phương thức hỗ trợ xóa trắng form
private void clearFields() {
    txtMaNhanVien.setText("");
    txtTenNhanVien.setText("");
    txtEmailNhanVien.setText("");
    txtCanCuoc.setText("");
    txtMatKhau.setText("");
    txtNgaySinhNhanVien.setDate(new Date());
    txtGioiTinhTim.setSelectedIndex(0);
    txtChucVu.setSelectedIndex(0);
    isEditing = false;
}

// Phương thức hỗ trợ xóa trắng form tìm kiếm
private void clearSearchFields() {
    txtMaNhanVienTim.setText("");
    txtTenNhanVienTim.setText("");
    txtCanCuocTim.setText("");
    txtGioiTinhTim.setSelectedIndex(0);
}

/**
 * Launch the application.
 */
public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                FrameQuanLyNhanVien frame = new FrameQuanLyNhanVien();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
	/**
	 * Create the frame.
	 */
	public FrameQuanLyNhanVien() {
		
//		setTitle("Quản lí nhân viên\r\n");
//		setResizable(true);
//		setSize(1440,1024);
//		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		pnlBackGround = new JPanel();
//		pnlBackGround.setBackground(new Color(255, 187, 53));
//		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
//		
//		setContentPane(pnlBackGround);
//		pnlBackGround.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(0, 289, 1586, 89);
//		panel.setBackground(new Color(255, 128, 64));
//		pnlBackGround.add(panel);
//		panel.setLayout(null);
		setSize(1440,1024);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1540, 755);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 303, 1586, 89);
		panel.setBackground(new Color(242, 132, 123));
		pnlBackGround.add(panel);
		panel.setLayout(null);
		
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon("icons\\btnSua.png"));
		btnSa.setForeground(Color.WHITE);
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSa.setBackground(new Color(167, 62, 20));
		btnSa.setBounds(328, 13, 177, 65);
		panel.add(btnSa);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon("icons\\btnXoa.png"));
		btnXa.setForeground(Color.WHITE);
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXa.setBackground(new Color(167, 62, 20));
		btnXa.setBounds(576, 13, 177, 65);
		panel.add(btnXa);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setIcon(new ImageIcon("icons\\btnCancel.png"));
		btnHy.setForeground(Color.WHITE);
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHy.setBackground(new Color(167, 62, 20));
		btnHy.setBounds(840, 13, 177, 65);
		panel.add(btnHy);
		
		JButton btnXuat = new JButton("Xuất");
		btnXuat.setIcon(new ImageIcon("icons\\btnprint.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBackground(new Color(167, 62, 20));
		btnXuat.setBounds(1099, 13, 177, 65);
		panel.add(btnXuat);
		
		JButton btnLu = new JButton("Thêm");
		btnLu.setBounds(61, 13, 177, 65);
		panel.add(btnLu);
		btnLu.setIcon(new ImageIcon("icons\\btnThem.png"));
		btnLu.setForeground(Color.WHITE);
		btnLu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLu.setBackground(new Color(167, 62, 20));
		btnLu.addActionListener(e -> btnLuuActionPerformed());
		
		JLabel lblCCCD = new JLabel("Căn cước:");
		lblCCCD.setBounds(55, 147, 160, 40);
		lblCCCD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblCCCD);
		
		JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setBounds(55, 27, 160, 40);
		lblMaNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaNhanVien);
		
		JLabel lblHoTenNhanVien = new JLabel("Họ và tên:");
		lblHoTenNhanVien.setBounds(55, 86, 160, 40);
		lblHoTenNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblHoTenNhanVien);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblGioiTinh.setBounds(648, 27, 160, 40);
		pnlBackGround.add(lblGioiTinh);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEmail.setBounds(648, 90, 160, 40);
		pnlBackGround.add(lblEmail);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySinh.setBounds(55, 230, 160, 40);
		pnlBackGround.add(lblNgaySinh);
		
		
        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblChucVu.setBounds(648, 230, 160, 40);
        pnlBackGround.add(lblChucVu);

        
	
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblMatKhau.setBounds(648, 154, 160, 40);
        pnlBackGround.add(lblMatKhau);

        
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(225, 31, 317, 43);
		pnlBackGround.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(225, 86, 317, 43);
		pnlBackGround.add(txtTenNhanVien);
		
		txtCanCuoc = new JTextField();
		txtCanCuoc.setColumns(10);
		txtCanCuoc.setBounds(225, 151, 317, 43);
		pnlBackGround.add(txtCanCuoc);
		
		txtEmailNhanVien = new JTextField();
		txtEmailNhanVien.setColumns(10);
		txtEmailNhanVien.setBounds(800, 90, 259, 43);
		pnlBackGround.add(txtEmailNhanVien);
		
		txtChucVu = new JComboBox<>();
		txtChucVu.setModel(new DefaultComboBoxModel<>(new String[] {"Quản lý", "Nhân viên"})); // Sửa "Chức vụ" thành "Nhân viên" cho rõ nghĩa
		txtChucVu.setBounds(800, 232, 259, 43);
		txtChucVu.setFont(new Font("Tahoma", Font.PLAIN, 16)); // 
		pnlBackGround.add(txtChucVu);
		
        txtMatKhau = new JTextField();
        txtMatKhau.setColumns(10);
        txtMatKhau.setBounds(800, 158, 259, 43);
        pnlBackGround.add(txtMatKhau);

        
		txtNgaySinhNhanVien = new JDateChooser();
        txtNgaySinhNhanVien.setDateFormatString("yyyy-MM-dd");
        txtNgaySinhNhanVien.setBounds(225, 230, 317, 40);
        pnlBackGround.add(txtNgaySinhNhanVien);
		
		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\WorkSpace\\Java_Workspace\\Nam3\\MTP\\mtp\\image\\logoMTP 1.png"));
		logoMTP.setBounds(1052, 10, 360, 214);
		pnlBackGround.add(logoMTP);
		
		JScrollPane scrollPaneNhanVien = new JScrollPane();
		scrollPaneNhanVien.setBounds(10, 417, 1009, 338);
		pnlBackGround.add(scrollPaneNhanVien);
		
		tableNhanVien = new JTable();
		tableNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 nh\u00E2n vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "Gi\u1EDBi t\u00EDnh", "Email", "Ng\u00E0y sinh", "CCCD", "M\u1EADt kh\u1EA9u", "Ch\u1EE9c v\u1EE5"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(55);
		
		JTableHeader header = tableNhanVien.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 19));
        tableNhanVien.setRowHeight(30);
		scrollPaneNhanVien.setViewportView(tableNhanVien);
		// Create a custom cell renderer that centers the text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Apply the renderer to each column of the table
		for (int i = 0; i < tableNhanVien.getColumnCount(); i++) {
		    tableNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		txtGioiTinhTim = new JComboBox();
		txtGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtGioiTinhTim.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		txtGioiTinhTim.setBounds(800, 27, 148, 36);
		pnlBackGround.add(txtGioiTinhTim);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(new Color(255, 128, 64));
		pnlTacVu.setBounds(1029, 417, 383, 338);
		pnlBackGround.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		pnlTacVu.setBorder(titledBorder);
		
		JLabel lblMaNhanVienTim = new JLabel("Mã nhân viên:");
		lblMaNhanVienTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaNhanVienTim.setBounds(10, 50, 180, 34);
		pnlTacVu.add(lblMaNhanVienTim);
		
		JLabel lblTenNhanVienTim = new JLabel("Tên nhân viên:");
		lblTenNhanVienTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTenNhanVienTim.setBounds(10, 94, 180, 34);
		pnlTacVu.add(lblTenNhanVienTim);
		
		JLabel lblCanCuocTim = new JLabel("Căn cước:");
		lblCanCuocTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCanCuocTim.setBounds(10, 138, 180, 34);
		pnlTacVu.add(lblCanCuocTim);
		
		JLabel lblGioiTinhTim = new JLabel("Giới tính:");
		lblGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGioiTinhTim.setBounds(10, 182, 180, 34);
		pnlTacVu.add(lblGioiTinhTim);
		
		txtMaNhanVienTim = new JTextField();
		txtMaNhanVienTim.setBounds(168, 50, 198, 31);
		pnlTacVu.add(txtMaNhanVienTim);
		txtMaNhanVienTim.setColumns(10);
		
		txtTenNhanVienTim = new JTextField();
		txtTenNhanVienTim.setColumns(10);
		txtTenNhanVienTim.setBounds(168, 94, 198, 31);
		pnlTacVu.add(txtTenNhanVienTim);
		
		txtCanCuocTim = new JTextField();
		txtCanCuocTim.setColumns(10);
		txtCanCuocTim.setBounds(168, 138, 198, 31);
		pnlTacVu.add(txtCanCuocTim);
		
		JComboBox txtGioiTinhTim = new JComboBox();
		txtGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtGioiTinhTim.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		txtGioiTinhTim.setBounds(168, 182, 115, 34);
		pnlTacVu.add(txtGioiTinhTim);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setBackground(new Color(255, 128, 128));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(54, 254, 115, 40);
		pnlTacVu.add(btnTim);
		
		JButton btnTaiLai_1_1 = new JButton("Tải lại");
		btnTaiLai_1_1.setBackground(new Color(255, 128, 128));
		btnTaiLai_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTaiLai_1_1.setBounds(197, 254, 115, 40);
		pnlTacVu.add(btnTaiLai_1_1);
        btnSa.addActionListener(e -> btnSuaActionPerformed());
        btnXa.addActionListener(e -> btnXoaActionPerformed());
        btnTim.addActionListener(e -> btnTimActionPerformed());
        btnTaiLai_1_1.addActionListener(e -> btnTaiLaiActionPerformed());
        
        // Load dữ liệu ban đầu
        loadDataToTable();
	}
}


