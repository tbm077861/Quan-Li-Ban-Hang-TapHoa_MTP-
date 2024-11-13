package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import DAO.NhanVienDAO;

public class FrameQuanLyNhanVien extends JPanel {

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
	private JComboBox<String> txtGioiTinhTim,txtGioiTinh, txtChucVu;
	private DefaultTableModel tableModel;
	private JTextField txtMatKhau;
	private boolean isEditing = false;
	private NhanVienDAO nhanVienDAO = new NhanVienDAO();

	// Cập nhật dữ liệu vào table
	private void loadDataToTable() {
		List<Object[]> data = nhanVienDAO.loadDataToTable();
		tableModel = (DefaultTableModel) tableNhanVien.getModel();
		tableModel.setRowCount(0);
		for (Object[] row : data) {
			tableModel.addRow(row);
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

	private boolean isDuplicateCCCD(String cccd, String maNV) {
		return nhanVienDAO.isDuplicateCCCD(cccd, maNV);
	}

	private boolean isDuplicateEmail(String email, String maNV) {
		return nhanVienDAO.isDuplicateEmail(email, maNV);
	}

	// Sự kiện nút Lưu
	private void btnLuuActionPerformed() {
		// Set the txtMaNhanVien text field to be editable
		txtMaNhanVien.setEditable(true);

		// Existing code for adding a new employee
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
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtMaNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateName(hoTen);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtTenNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateEmail(email);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtEmailNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateCCCD(cccd);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtCanCuoc.requestFocus();
			return;
		}

		error = KiemTraNhap.validatePassword(matKhau);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtMatKhau.requestFocus();
			return;
		}

		if (isDuplicateCCCD(cccd, maNV)) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Căn cước đã tồn tại! Vui lòng kiểm tra lại thông tin.");
			txtCanCuoc.requestFocus();
			return;
		}

		if (isDuplicateEmail(email, maNV)) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Email đã tồn tại! Vui lòng kiểm tra lại thông tin.");
			txtEmailNhanVien.requestFocus();
			return;
		}

		Date ngaySinh = txtNgaySinhNhanVien.getDate();
		if (ngaySinh == null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Ngày sinh không hợp lệ! Vui lòng chọn ngày sinh.");
			txtNgaySinhNhanVien.requestFocus();
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinhStr = sdf.format(ngaySinh);

		nhanVienDAO.saveNhanVien(false, maNV, hoTen, ngaySinhStr, email, gioiTinh, cccd, matKhau, chucVu);
		loadDataToTable();
		clearFields();
	}

	// Sự kiện nút Sửa
	private void btnSuaActionPerformed() {
		int selectedRow = tableNhanVien.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!");
			return;
		}

		// Set the txtMaNhanVien text field to be non-editable
		txtMaNhanVien.setEditable(false);

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
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtMaNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateName(hoTen);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtTenNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateEmail(email);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtEmailNhanVien.requestFocus();
			return;
		}

		error = KiemTraNhap.validateCCCD(cccd);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtCanCuoc.requestFocus();
			return;
		}

		error = KiemTraNhap.validatePassword(matKhau);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this, "Error: " + error);
			txtMatKhau.requestFocus();
			return;
		}

		if (nhanVienDAO.isDuplicateCCCD(cccd, maNV)) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Căn cước đã tồn tại! Vui lòng kiểm tra lại thông tin.");
			txtCanCuoc.requestFocus();
			return;
		}

		if (isDuplicateEmail(email, maNV)) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Email đã tồn tại! Vui lòng kiểm tra lại thông tin.");
			txtEmailNhanVien.requestFocus();
			return;
		}

		Date ngaySinh = txtNgaySinhNhanVien.getDate();
		if (ngaySinh == null) {
			JOptionPane.showMessageDialog(FrameQuanLyNhanVien.this,
					"Error: Ngày sinh không hợp lệ! Vui lòng chọn ngày sinh.");
			txtNgaySinhNhanVien.requestFocus();
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinhStr = sdf.format(ngaySinh);

		nhanVienDAO.saveNhanVien(true, maNV, hoTen, ngaySinhStr, email, gioiTinh, cccd, matKhau, chucVu);
		loadDataToTable();
		clearFields();
	}

	// Sự kiện nút Xóa
	private void btnXoaActionPerformed() {
		int selectedRow = tableNhanVien.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			String maNV = tableModel.getValueAt(selectedRow, 0).toString();
			nhanVienDAO.deleteNhanVien(maNV);
			loadDataToTable();
			clearFields();
		}
	}

	// Sự kiện nút Tìm kiếm



	private void btnTimActionPerformed() {
	    String maNV = txtMaNhanVienTim.getText().trim();
	    String hoTen = txtTenNhanVienTim.getText().trim();
	    String cccd = txtCanCuocTim.getText().trim();
	    String gioiTinh = txtGioiTinhTim.getSelectedItem().toString().trim(); // Retrieve selected gender
	
	    // Print the selected gender to the console
	    System.out.println("Selected gender: " + gioiTinh);
	
	    List<Object[]> data = nhanVienDAO.searchNhanVien(maNV, hoTen, cccd, gioiTinh);
	    tableModel.setRowCount(0);
	    for (Object[] row : data) {
	        tableModel.addRow(row);
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
		txtMaNhanVien.setEditable(true);

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

	
	// Sự kiện nút Xuất file
	private void btnXuatFileAction() {
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("DanhSachNhanVien");

	    // Create header row
	    Row headerRow = sheet.createRow(0);
	    String[] headers = {"Mã nhân viên", "Họ tên", "Ngày Sinh",  "Email", "Giới tính", "CCCD", "Chức vụ"};
	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	    }

	    // Populate data rows
	    List<Object[]> List = nhanVienDAO.getAllNhanVien();
	    int rowNum = 1;
	    for (Object[] nv : List) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue((String) nv[0]);
	        row.createCell(1).setCellValue((String) nv[1]);
	        row.createCell(2).setCellValue((String) nv[2]);
	        row.createCell(3).setCellValue((String) nv[3]);
	        row.createCell(4).setCellValue((String) nv[4]);
	        row.createCell(5).setCellValue((String) nv[5]);
	        row.createCell(6).setCellValue((String) nv[6]);
	    }

	    // Write the output to a file
	    try (FileOutputStream fileOut = new FileOutputStream("DanhSachNhanVien.xlsx")) {
	        workbook.write(fileOut);
	        JOptionPane.showMessageDialog(this, "Xuất file Excel thành công");
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Xuất file Excel thất bại");
	    } finally {
	        try {
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/**
	 * Launch the application.
	 */
//public static void main(String[] args) {
//    EventQueue.invokeLater(new Runnable() {
//        public void run() {
//            try {
//                FrameQuanLyNhanVien frame = new FrameQuanLyNhanVien();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    });
//}
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public FrameQuanLyNhanVien() {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1538, 755);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 284, 1538, 74);
		panel.setBackground(new Color(242, 132, 123));
		pnlBackGround.add(panel);
		panel.setLayout(null);

		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon("icon\\btnSua.png"));
		btnSa.setForeground(new Color(0, 0, 0));
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSa.setBackground(new Color(167, 62, 20));
		btnSa.setBounds(372, 13, 177, 50);
		panel.add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXa.setForeground(new Color(0, 0, 0));
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXa.setBackground(new Color(167, 62, 20));
		btnXa.setBounds(695, 13, 177, 50);
		panel.add(btnXa);

		JButton btnHy = new JButton("Hủy");
		btnHy.setIcon(new ImageIcon("icon\\btnCancel.png"));
		btnHy.setForeground(new Color(0, 0, 0));
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHy.setBackground(new Color(167, 62, 20));
		btnHy.setBounds(1001, 13, 177, 50);
		panel.add(btnHy);

		JButton btnXuat = new JButton("Xuất");
		btnXuat.addActionListener(e -> btnXuatFileAction());
		btnXuat.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnXuat.setForeground(new Color(0, 0, 0));
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBackground(new Color(167, 62, 20));
		btnXuat.setBounds(1318, 13, 177, 50);
		panel.add(btnXuat);

		JButton btnLu = new JButton("Thêm");
		btnLu.setBounds(61, 13, 177, 50);
		panel.add(btnLu);
		btnLu.setIcon(new ImageIcon("icon\\btnThem.png"));
		btnLu.setForeground(new Color(0, 0, 0));
		btnLu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLu.setBackground(new Color(167, 62, 20));
		btnLu.addActionListener(e -> btnLuuActionPerformed());

		JLabel lblCCCD = new JLabel("Căn cước:");
		lblCCCD.setBounds(55, 154, 160, 40);
		lblCCCD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblCCCD);

		JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setBounds(55, 31, 160, 40);
		lblMaNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaNhanVien);

		JLabel lblHoTenNhanVien = new JLabel("Họ và tên:");
		lblHoTenNhanVien.setBounds(55, 90, 160, 40);
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
		lblNgaySinh.setBounds(55, 216, 160, 40);
		pnlBackGround.add(lblNgaySinh);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblChucVu.setBounds(648, 216, 160, 40);
		pnlBackGround.add(lblChucVu);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblMatKhau.setBounds(648, 154, 160, 40);
		pnlBackGround.add(lblMatKhau);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtMaNhanVien.setBounds(225, 31, 317, 43);
		pnlBackGround.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		// Auto uppercase

		txtMaNhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtMaNhanVien.getText();
				txtMaNhanVien.setText(text.toUpperCase());
			}
		});

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(225, 86, 317, 43);
		pnlBackGround.add(txtTenNhanVien);

		txtCanCuoc = new JTextField();
		txtCanCuoc.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtCanCuoc.setColumns(10);
		txtCanCuoc.setBounds(225, 151, 317, 43);
		pnlBackGround.add(txtCanCuoc);

		txtEmailNhanVien = new JTextField();
		txtEmailNhanVien.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtEmailNhanVien.setColumns(10);
		txtEmailNhanVien.setBounds(800, 90, 259, 43);
		pnlBackGround.add(txtEmailNhanVien);

		txtChucVu = new JComboBox<>();
		txtChucVu.setModel(new DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
		txtChucVu.setBounds(800, 218, 259, 43);
		txtChucVu.setFont(new Font("Dialog", Font.PLAIN, 18)); //
		pnlBackGround.add(txtChucVu);

		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(800, 158, 259, 43);
		pnlBackGround.add(txtMatKhau);

		txtNgaySinhNhanVien = new JDateChooser();
		txtNgaySinhNhanVien.setDateFormatString("yyyy-MM-dd");
		txtNgaySinhNhanVien.setBounds(225, 216, 317, 40);
		pnlBackGround.add(txtNgaySinhNhanVien);
		txtNgaySinhNhanVien.getDateEditor().getUiComponent().setFont(new Font("Dialog", Font.PLAIN, 18));

		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("image\\logoMTP 1.png"));
		logoMTP.setBounds(1052, 10, 360, 214);
		pnlBackGround.add(logoMTP);

		JScrollPane scrollPaneNhanVien = new JScrollPane();
		scrollPaneNhanVien.setBounds(10, 368, 1125, 377);
		pnlBackGround.add(scrollPaneNhanVien);

		tableNhanVien = new JTable();
		tableNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableNhanVien.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã nhân viên", "Họ tên",
				"Giới tính", "Email", "Ngày sinh", "CCCD", "Mật khẩu", "Chức vụ" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					Integer.class, String.class, String.class };

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
		txtGioiTinh = new JComboBox();
		txtGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		txtGioiTinh.setBounds(800, 27, 148, 36);
		pnlBackGround.add(txtGioiTinh);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(new Color(242, 132, 123));
		pnlTacVu.setBounds(1145, 368, 383, 377);
		pnlBackGround.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		pnlTacVu.setBorder(titledBorder);

		JLabel lblMaNhanVienTim = new JLabel("Mã nhân viên:");
		lblMaNhanVienTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaNhanVienTim.setBounds(10, 44, 180, 34);
		pnlTacVu.add(lblMaNhanVienTim);

		JLabel lblTenNhanVienTim = new JLabel("Tên nhân viên:");
		lblTenNhanVienTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTenNhanVienTim.setBounds(10, 107, 180, 34);
		pnlTacVu.add(lblTenNhanVienTim);

		JLabel lblCanCuocTim = new JLabel("Căn cước:");
		lblCanCuocTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCanCuocTim.setBounds(10, 166, 180, 34);
		pnlTacVu.add(lblCanCuocTim);

		JLabel lblGioiTinhTim = new JLabel("Giới tính:");
		lblGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGioiTinhTim.setBounds(10, 226, 180, 34);
		pnlTacVu.add(lblGioiTinhTim);

		txtMaNhanVienTim = new JTextField();
		txtMaNhanVienTim.setBounds(168, 50, 198, 31);
		pnlTacVu.add(txtMaNhanVienTim);
		txtMaNhanVienTim.setColumns(10);

		txtTenNhanVienTim = new JTextField();
		txtTenNhanVienTim.setColumns(10);
		txtTenNhanVienTim.setBounds(168, 113, 198, 31);
		pnlTacVu.add(txtTenNhanVienTim);

		txtCanCuocTim = new JTextField();
		txtCanCuocTim.setColumns(10);
		txtCanCuocTim.setBounds(168, 172, 198, 31);
		pnlTacVu.add(txtCanCuocTim);

		txtGioiTinhTim = new JComboBox();
		txtGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtGioiTinhTim.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		txtGioiTinhTim.setBounds(168, 228, 115, 34);
		pnlTacVu.add(txtGioiTinhTim);

		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setBackground(new Color(255, 128, 128));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(54, 287, 115, 40);
		pnlTacVu.add(btnTim);

		JButton btnTaiLai_1_1 = new JButton("Tải lại");
		btnTaiLai_1_1.setBackground(new Color(255, 128, 128));
		btnTaiLai_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTaiLai_1_1.setBounds(206, 287, 115, 40);
		pnlTacVu.add(btnTaiLai_1_1);

		btnHy.addActionListener(e -> clearFields());
		btnSa.addActionListener(e -> btnSuaActionPerformed());
		btnXa.addActionListener(e -> btnXoaActionPerformed());
		btnTim.addActionListener(e -> btnTimActionPerformed());
		btnTaiLai_1_1.addActionListener(e -> btnTaiLaiActionPerformed());

		tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRow = tableNhanVien.getSelectedRow();
				if (selectedRow >= 0) {
					txtMaNhanVien.setText(tableModel.getValueAt(selectedRow, 0).toString());
					txtMaNhanVien.setEditable(false);

					txtTenNhanVien.setText(tableModel.getValueAt(selectedRow, 1).toString());
					txtGioiTinh.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
					txtEmailNhanVien.setText(tableModel.getValueAt(selectedRow, 3).toString());
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date ngaySinh = sdf.parse(tableModel.getValueAt(selectedRow, 4).toString());
						txtNgaySinhNhanVien.setDate(ngaySinh);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày sinh!");
					}
					txtCanCuoc.setText(tableModel.getValueAt(selectedRow, 5).toString());
					txtMatKhau.setText(tableModel.getValueAt(selectedRow, 6).toString());
					txtChucVu.setSelectedItem(tableModel.getValueAt(selectedRow, 7).toString());
				}
			}
		});

		// Load dữ liệu ban đầu
		loadDataToTable();
	}
}
