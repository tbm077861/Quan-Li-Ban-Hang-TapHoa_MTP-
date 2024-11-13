package GUI;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import DAO.KhachHangDAO;
import connectDB.ConnectDB;
import entity.KhachHang;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class FrameQuanLyKhachHang extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtDienThoai;
	private JTextField txtEmail;
	private JDateChooser txtNgaySinh;
	private JTable table;
	private JTextField txtMaKhachHangTim;
	private JTextField btnTenKhachHangTim;
	private JTextField txtDienThoaiTim;
	private JTextField txtDiaChiTim;
	private DefaultTableModel tableModel;
	private boolean isEditting = false;
	private KhachHangDAO khachHangDAO = new KhachHangDAO();

	// Cập nhật dữ liệu vào table
	private void loadDataToTable() {
		List<Object[]> data = khachHangDAO.loadDataToTable();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (Object[] rowData : data) {
			tableModel.addRow(rowData);
		}
	}

	// Kiểm tra dữ liệu nhập
	public class kiemTraNhap {
		public static String validateID(String id) {
			if (id == null || id.isEmpty()) {
				return "Mã khách hàng không được để trống!";
			}
			String idRegex = "^KH\\d+$";
			if (!Pattern.matches(idRegex, id)) {
				return "Mã khách hàng không hợp lệ! Mã khách hàng phải bắt đầu bằng 'KH' và theo sau là các chữ số.";
			}
			return null;
		}

		public static String validateName(String name) {
			if (name == null || name.isEmpty()) {
				return "Tên khách hàng không được để trống!";
			}
			return null;
		}

		public static String validatePhone(String phone) {
			if (phone == null || phone.isEmpty()) {
				return "Số điện thoại không được để trống!";
			}
			String phoneRegex = "^0\\d{9,10}$";
			if (!Pattern.matches(phoneRegex, phone)) {
				return "Số điện thoại không hợp lệ! Số điện thoại phải bắt đầu bằng số 0 và theo sau là 9 hoặc 10 chữ số.";
			}
			return null;
		}

		public static String validateEmail(String email) {
			if (email == null || email.isEmpty()) {
				return "Email không được để trống!";
			}
			String emailRegex = "^[\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
			if (!Pattern.matches(emailRegex, email)) {
				return "Email không hợp lệ! Email phải theo định dạng x@x.x";
			}
			return null;
		}

		public static String validateAddress(String address) {
			if (address == null || address.isEmpty()) {
				return "Địa chỉ không được để trống!";
			}
			return null;
		}
	}

	// Thêm khách hàng
	private void btnThemAction() {
		txtMaKhachHang.setEditable(true);

		String maKH = txtMaKhachHang.getText().trim();
		String tenKH = txtTenKhachHang.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String dienThoai = txtDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		Date ngaySinh = txtNgaySinh.getDate();

		String error;

		error = kiemTraNhap.validateID(maKH);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi" + error);
			txtMaKhachHang.requestFocus();
			return;
		}

		error = kiemTraNhap.validateName(tenKH);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtTenKhachHang.requestFocus();
			return;
		}

		error = kiemTraNhap.validateAddress(diaChi);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtDiaChi.requestFocus();
			return;
		}

		error = kiemTraNhap.validatePhone(dienThoai);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtDienThoai.requestFocus();
			return;
		}

		error = kiemTraNhap.validateEmail(email);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtEmail.requestFocus();
			return;
		}

		if (khachHangDAO.isDuplicateDienThoai(dienThoai, maKH)) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Số điện thoại đã tồn tại!");
			txtDienThoai.requestFocus();
			return;
		}

		if (khachHangDAO.isDuplicateEmail(email, maKH)) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Email đã tồn tại!");
			txtEmail.requestFocus();
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinhStr = sdf.format(ngaySinh);

		khachHangDAO.saveKhachHang(false, maKH, tenKH, diaChi, dienThoai, email, ngaySinhStr);
		loadDataToTable();
		clearInput();
	}

	// Sửa thông tin khách hàng
	private void btnSuaAction() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Vui lòng chọn khách hàng để sửa!");
			return;
		}

		txtMaKhachHang.setEditable(false);

		String maKH = txtMaKhachHang.getText().trim();
		String tenKH = txtTenKhachHang.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String dienThoai = txtDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		Date ngaySinh = txtNgaySinh.getDate();

		String error;

		error = kiemTraNhap.validateID(maKH);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtMaKhachHang.requestFocus();
			return;
		}

		error = kiemTraNhap.validateName(tenKH);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtTenKhachHang.requestFocus();
			return;
		}

		error = kiemTraNhap.validateAddress(diaChi);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtDiaChi.requestFocus();
			return;
		}

		error = kiemTraNhap.validatePhone(dienThoai);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtDienThoai.requestFocus();
			return;
		}

		error = kiemTraNhap.validateEmail(email);
		if (error != null) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi! " + error);
			txtEmail.requestFocus();
			return;
		}

		if (khachHangDAO.isDuplicateDienThoai(dienThoai, maKH)) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Số điện thoại đã tồn tại!");
			txtDienThoai.requestFocus();
			return;
		}

		if (khachHangDAO.isDuplicateEmail(email, maKH)) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Email đã tồn tại!");
			txtEmail.requestFocus();
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinhStr = sdf.format(ngaySinh);

		khachHangDAO.saveKhachHang(true, maKH, tenKH, diaChi, dienThoai, email, ngaySinhStr);
		loadDataToTable();
		clearInput();
	}

	// Xóa khách hàng
	private void btnXoaAction() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Vui lòng chọn khách hàng để xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(FrameQuanLyKhachHang.this,
				"Bạn có chắc chắn muốn xóa khách hàng này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			String maKH = (String) table.getValueAt(selectedRow, 0);
			khachHangDAO.deleteKhachHang(maKH);
			loadDataToTable();
			clearInput();
		}
	}

	private void btnHuyAction() {
		clearInput();
	}

	// Tìm kiếm khách hàng
	private void btnTimAction() {
		String maKH = txtMaKhachHangTim.getText().trim();
		String tenKH = btnTenKhachHangTim.getText().trim();
		String dienThoai = txtDienThoaiTim.getText().trim();
		String diaChi = txtDiaChiTim.getText().trim();

		List<Object[]> data = khachHangDAO.searchKhachHang(maKH, tenKH, dienThoai, diaChi);
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (Object[] rowData : data) {
			tableModel.addRow(rowData);
		}
	}

	// Tải lại dữ liệu vào table
	private void btnTaiLaiAction() {
		clearSearch();
		loadDataToTable();
	}

	// Xóa dữ liệu trong các ô nhập
	private void clearInput() {
		txtMaKhachHang.setText("");
		txtMaKhachHang.setEditable(true);

		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtDienThoai.setText("");
		txtEmail.setText("");
		txtNgaySinh.setDate(new Date());
		isEditting = false;
	}

	// Xóa dữ liệu trong các ô tìm kiếm
	private void clearSearch() {
		txtMaKhachHangTim.setText("");
		btnTenKhachHangTim.setText("");
		txtDienThoaiTim.setText("");
		txtDiaChiTim.setText("");
	}
	
	
	// Sự kiện nút Xuất file
		private void btnXuatFileAction() {
		    Workbook workbook = new XSSFWorkbook();
		    Sheet sheet = workbook.createSheet("DanhSachKhachHang");

		    // Create header row
		    Row headerRow = sheet.createRow(0);
		    String[] headers = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Email", "Ngày sinh"};
		    for (int i = 0; i < headers.length; i++) {
		        Cell cell = headerRow.createCell(i);
		        cell.setCellValue(headers[i]);
		    }

		    // Populate data rows
		    List<Object[]> List = khachHangDAO.getAllKhachHang();
		    int rowNum = 1;
		    for (Object[] kh : List) {
		        Row row = sheet.createRow(rowNum++);
		        row.createCell(0).setCellValue((String) kh[0]);
		        row.createCell(1).setCellValue((String) kh[1]);
		        row.createCell(2).setCellValue((String) kh[2]);
		        row.createCell(3).setCellValue((String) kh[3]);
		        row.createCell(4).setCellValue((String) kh[4]);
		        row.createCell(5).setCellValue((String) kh[5]);
		    }

		    // Write the output to a file
		    try (FileOutputStream fileOut = new FileOutputStream("DanhSachKhachHang.xlsx")) {
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
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQuanLyKhachHang frame = new FrameQuanLyKhachHang();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrameQuanLyKhachHang() {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1543, 755);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 221, 1586, 89);
		panel.setBackground(new Color(242, 132, 123));
		pnlBackGround.add(panel);
		panel.setLayout(null);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(167, 62, 20));
		btnThem.setIcon(new ImageIcon("icon\\btnThem.png"));
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(71, 20, 156, 45);
		panel.add(btnThem);
		btnThem.addActionListener(e -> btnThemAction());

		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("icon\\btnSua.png"));
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBackground(new Color(167, 62, 20));
		btnSua.setBounds(356, 20, 156, 45);
		panel.add(btnSua);
		btnSua.addActionListener(e -> btnSuaAction());

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBackground(new Color(167, 62, 20));
		btnXoa.setBounds(657, 20, 156, 45);
		panel.add(btnXoa);
		btnXoa.addActionListener(e -> btnXoaAction());

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setIcon(new ImageIcon("icon\\btnCancel.png"));
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBackground(new Color(167, 62, 20));
		btnHuy.setBounds(980, 20, 156, 45);
		panel.add(btnHuy);
		btnHuy.addActionListener(e -> btnHuyAction());

		JButton btnIn = new JButton("Xuất File");
		btnIn.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnIn.setForeground(Color.BLACK);
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnIn.setBackground(new Color(167, 62, 20));
		btnIn.setBounds(1287, 20, 156, 45);
		panel.add(btnIn);
		btnIn.addActionListener(e -> btnXuatFileAction());

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(55, 147, 160, 40);
		lblDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblDiaChi);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setBounds(55, 27, 160, 40);
		lblMaKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaKhachHang);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setBounds(55, 86, 160, 40);
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblTenKhachHang);

		JLabel lblSoDienThoai = new JLabel("Điện thoại:");
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSoDienThoai.setBounds(588, 27, 160, 40);
		pnlBackGround.add(lblSoDienThoai);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEmail.setBounds(588, 90, 160, 40);
		pnlBackGround.add(lblEmail);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySinh.setBounds(588, 154, 160, 40);
		pnlBackGround.add(lblNgaySinh);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaKhachHang.setBounds(225, 31, 317, 43);
		pnlBackGround.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);

		// Auto uppercase

		txtMaKhachHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtMaKhachHang.getText();
				txtMaKhachHang.setText(text.toUpperCase());
			}
		});

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(225, 90, 317, 43);
		pnlBackGround.add(txtTenKhachHang);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(225, 151, 317, 43);
		pnlBackGround.add(txtDiaChi);

		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(740, 31, 259, 43);
		pnlBackGround.add(txtDienThoai);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(740, 90, 259, 43);
		pnlBackGround.add(txtEmail);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(740, 154, 259, 40);
		pnlBackGround.add(txtNgaySinh);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image\\logoMTP 1.png"));
		lblNewLabel.setBounds(1047, 0, 407, 211);
		pnlBackGround.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 320, 1006, 425);
		pnlBackGround.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại", "Email", "Ngày Sinh" }) {
			private static final long serialVersionUID = -5324374389820629878L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPane.setViewportView(table);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(new Color(242, 132, 123));
		pnlTacVu.setBounds(1026, 320, 496, 425);
		pnlBackGround.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		pnlTacVu.setBorder(titledBorder);

		JLabel lblNewLabel_1 = new JLabel("Mã KH:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(30, 44, 71, 34);
		pnlTacVu.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên KH:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(30, 130, 93, 34);
		pnlTacVu.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(29, 214, 145, 34);
		pnlTacVu.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Địa chỉ:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(30, 297, 103, 34);
		pnlTacVu.add(lblNewLabel_1_4);

		txtMaKhachHangTim = new JTextField();
		txtMaKhachHangTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaKhachHangTim.setBounds(206, 37, 259, 45);
		pnlTacVu.add(txtMaKhachHangTim);
		txtMaKhachHangTim.setColumns(10);

		btnTenKhachHangTim = new JTextField();
		btnTenKhachHangTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTenKhachHangTim.setColumns(10);
		btnTenKhachHangTim.setBounds(206, 119, 259, 45);
		pnlTacVu.add(btnTenKhachHangTim);

		txtDienThoaiTim = new JTextField();
		txtDienThoaiTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDienThoaiTim.setColumns(10);
		txtDienThoaiTim.setBounds(206, 203, 259, 45);
		pnlTacVu.add(txtDienThoaiTim);

		txtDiaChiTim = new JTextField();
		txtDiaChiTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChiTim.setColumns(10);
		txtDiaChiTim.setBounds(206, 286, 259, 45);
		pnlTacVu.add(txtDiaChiTim);

		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(255, 128, 128));
		btnTim.setForeground(new Color(255, 0, 0));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(88, 358, 117, 57);
		pnlTacVu.add(btnTim);
		btnTim.addActionListener(e -> btnTimAction());

		JButton btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setBackground(new Color(255, 128, 128));
		btnTaiLai.setForeground(new Color(255, 0, 0));
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTaiLai.setBounds(294, 358, 122, 57);
		pnlTacVu.add(btnTaiLai);
		btnTaiLai.addActionListener(e -> btnTaiLaiAction());

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		// Create a custom cell renderer that centers the text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Apply the renderer to each column of the table
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				txtMaKhachHang.setText((String) table.getValueAt(selectedRow, 0));
				txtMaKhachHang.setEditable(false);
				txtTenKhachHang.setText((String) table.getValueAt(selectedRow, 1));
				txtDiaChi.setText((String) table.getValueAt(selectedRow, 2));
				txtDienThoai.setText((String) table.getValueAt(selectedRow, 3));
				txtEmail.setText((String) table.getValueAt(selectedRow, 4));
				try {
					Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getValueAt(selectedRow, 5));
					txtNgaySinh.setDate(ngaySinh);
				} catch (ParseException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(FrameQuanLyKhachHang.this, "Lỗi định dạng ngày sinh: ");
				}
			}
		});
		loadDataToTable();
	}

}
