package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import entity.SanPham;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import com.toedter.components.JSpinField;

import DAO.SanPhamDAO;
import connectDB.ConnectDB;

import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSlider;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class FrameQuanLySanPham extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtMoTa;
	private JTextField txtDonGia;
	private JDateChooser txtNgaySX;
	private JDateChooser dateTim;
	private JTable tableSanPham;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnHuy;
	private JButton btnXuat;
	private JButton btnLoc;
	private JButton btnTaiLai;
	private JButton btnTim;
	private JButton btnSua;
	private JTextField txtMaTim;
	private JTextField txtEnd;
	private JTextField txtStart;
	private JSpinner txtSoLuong;
	private SanPhamDAO sanPhamDAO = new SanPhamDAO();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQuanLySanPham frame = new FrameQuanLySanPham();
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
	public FrameQuanLySanPham() {
//Frame
//		setTitle("Quản lí sản phẩm\r\n");
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
//		panel.setBounds(0, 221, 1586, 89);
//		panel.setBackground(new Color(255, 128, 64));
//		pnlBackGround.add(panel);
//		panel.setLayout(null);
//		
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1545, 854);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 221, 1545, 89);
		panel.setBackground(new Color(242, 132, 123));
		pnlBackGround.add(panel);
		panel.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("icon\\btnThem.png"));
		btnThem.setBackground(new Color(222, 77, 134));
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(67, 20, 156, 45);
		panel.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("icon\\btnSua.png"));
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.setBackground(new Color(222, 77, 134));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBounds(385, 20, 156, 45);
		panel.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setBackground(new Color(222, 77, 134));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(704, 20, 156, 45);
		panel.add(btnXoa);

		btnHuy = new JButton("Hủy");
		btnHuy.setIcon(new ImageIcon("icon\\btnCancel.png"));
		btnHuy.setForeground(new Color(0, 0, 0));
		btnHuy.setBackground(new Color(222, 77, 134));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBounds(1010, 20, 156, 45);
		panel.add(btnHuy);

		btnXuat = new JButton("Xuất File");
		btnXuat.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnXuat.setForeground(new Color(0, 0, 0));
		btnXuat.setBackground(new Color(222, 77, 134));
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBounds(1321, 20, 156, 45);
		panel.add(btnXuat);

		JLabel lblMota = new JLabel("Mô tả:");
		lblMota.setBounds(55, 147, 160, 40);
		lblMota.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMota);

		JLabel lblMaHang = new JLabel("Mã hàng:");
		lblMaHang.setBounds(55, 27, 160, 40);
		lblMaHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaHang);

		JLabel lblTenHang = new JLabel("Tên hàng:");
		lblTenHang.setBounds(55, 86, 160, 40);
		lblTenHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblTenHang);

		JLabel lblDonGia = new JLabel("Đơn giá bán:");
		lblDonGia.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDonGia.setBounds(561, 27, 160, 40);
		pnlBackGround.add(lblDonGia);

		JLabel lblSoluong = new JLabel("Số lượng:");
		lblSoluong.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSoluong.setBounds(561, 86, 160, 40);
		pnlBackGround.add(lblSoluong);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySX.setBounds(561, 147, 160, 40);
		pnlBackGround.add(lblNgaySX);

		txtMaHang = new JTextField();
		txtMaHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaHang.setBounds(176, 33, 258, 40);
		pnlBackGround.add(txtMaHang);
		txtMaHang.setColumns(10);
		
		// Auto uppercase

		txtMaHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtMaHang.getText();
				txtMaHang.setText(text.toUpperCase());
			}
		});
		
		txtTenHang = new JTextField();
		txtTenHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(176, 97, 258, 40);
		pnlBackGround.add(txtTenHang);

		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(176, 158, 258, 40);
		pnlBackGround.add(txtMoTa);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(718, 33, 259, 40);
		pnlBackGround.add(txtDonGia);

		txtNgaySX = new JDateChooser();
		txtNgaySX.setBounds(718, 147, 259, 40);
		pnlBackGround.add(txtNgaySX);

		txtNgaySX.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					txtNgaySX.setDateFormatString(dateFormat.toPattern());
				}
			}
		});

		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("image\\logoMTP 1.png"));
		logoMTP.setBounds(1054, -3, 403, 214);
		pnlBackGround.add(logoMTP);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 338, 1147, 407);
		pnlBackGround.add(scrollPane);

		tableSanPham = new JTable();
		JTableHeader header = tableSanPham.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));
		tableSanPham.setFont(new Font("Arial", Font.PLAIN, 16));
		tableSanPham.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã hàng", "Tên hàng", "Mô tả", "Đơn giá ", "Số lượng", "Ngày sản xuất" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Double.class, Integer.class,
					String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPane.setViewportView(tableSanPham);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		TableColumn dateColumn = tableSanPham.getColumnModel().getColumn(5);
		dateColumn.setCellRenderer(rightRenderer);

		txtSoLuong = new JSpinner();
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoLuong.setBounds(718, 88, 259, 40);
		pnlBackGround.add(txtSoLuong);

		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBackground(new Color(242, 132, 123));
		panelTimKiem.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTimKiem.setBounds(1168, 335, 362, 410);
		pnlBackGround.add(panelTimKiem);
		panelTimKiem.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		panelTimKiem.setBorder(titledBorder);

		JLabel lblNewLabel = new JLabel("Mã hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(31, 41, 136, 25);
		panelTimKiem.add(lblNewLabel);

		txtMaTim = new JTextField();
		txtMaTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaTim.setBounds(186, 45, 136, 25);
		panelTimKiem.add(txtMaTim);
		txtMaTim.setColumns(10);

		// Auto uppercase

		txtMaTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtMaTim.getText();
				txtMaTim.setText(text.toUpperCase());
			}
		});

		btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(255, 0, 0));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTim.setBounds(131, 183, 125, 40);
		panelTimKiem.add(btnTim);
		JLabel lblNewLabel_1 = new JLabel("Lọc theo giá");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(116, 233, 163, 37);
		panelTimKiem.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Từ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(38, 294, 45, 25);
		panelTimKiem.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 58));
		lblNewLabel_3.setBounds(197, 294, 45, 13);
		panelTimKiem.add(lblNewLabel_3);

		txtEnd = new JTextField();
		txtEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEnd.setBounds(238, 288, 96, 31);
		panelTimKiem.add(txtEnd);
		txtEnd.setColumns(10);

		txtStart = new JTextField();
		txtStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStart.setColumns(10);
		txtStart.setBounds(93, 288, 96, 31);
		panelTimKiem.add(txtStart);

		btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(this);

		btnLoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLoc.setBackground(new Color(255, 0, 0));
		btnLoc.setBounds(59, 347, 125, 40);
		panelTimKiem.add(btnLoc);

		JLabel lblNewLabel_4 = new JLabel("Tên hàng:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(31, 93, 116, 25);
		panelTimKiem.add(lblNewLabel_4);

		txtTenTim = new JTextField();
		txtTenTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenTim.setColumns(10);
		txtTenTim.setBounds(186, 93, 136, 25);
		panelTimKiem.add(txtTenTim);

		JLabel lblNewLabel_4_1 = new JLabel("Ngày sản xuất:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(31, 144, 185, 25);
		panelTimKiem.add(lblNewLabel_4_1);

		dateTim = new JDateChooser();
		dateTim.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					dateTim.setDateFormatString(dateFormat.toPattern());
				}
			}
		});
		dateTim.setBounds(186, 144, 136, 25);
		panelTimKiem.add(dateTim);

		btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTaiLai.setBackground(new Color(255, 0, 0));
		btnTaiLai.setBounds(209, 347, 125, 40);
		panelTimKiem.add(btnTaiLai);

		// Khởi tạo danh sách
//		txtMaHang.setText(sanPhamDAO.getNextMaHang());
		txtTenHang.requestFocus();
		txtTenHang.selectAll();
		loadDataToTable();
		// Hiển thị dữ liệu để chỉnh sửa

		tableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRow = tableSanPham.getSelectedRow();
				if (selectedRow != -1) {
					txtMaHang.setText((String) tableSanPham.getValueAt(selectedRow, 0));
					txtTenHang.setText((String) tableSanPham.getValueAt(selectedRow, 1));
					txtMoTa.setText((String) tableSanPham.getValueAt(selectedRow, 2));
					txtDonGia.setText(tableSanPham.getValueAt(selectedRow, 3).toString());
					txtSoLuong.setValue(tableSanPham.getValueAt(selectedRow, 4));
					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd")
								.parse((String) tableSanPham.getValueAt(selectedRow, 5));
						txtNgaySX.setDate(date);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Action Listener
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXuat.addActionListener(this);
		btnTaiLai.addActionListener(this);
		btnTim.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (isMaHangDuplicate(txtMaHang.getText())) {
				JOptionPane.showMessageDialog(this, "Mã hàng đã tồn tại");
				return;
			}
			if (validateData()) {
				SanPham sp = revertData();
				try {
					if (addSanPhamToDatabase(sp)) {
						JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
						clearInputFields();
						clearDataToTable();
						loadDataToTable();
//                    txtMaHang.setText(sanPhamDAO.getNextMaHang());
					} else {
						JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		if (o.equals(btnXoa)) {
			int selectedRow = tableSanPham.getSelectedRow();
			if (selectedRow != -1) {
				String maHang = (String) tableSanPham.getValueAt(selectedRow, 0);
				if (deleteSanPhamFromDatabase(maHang)) {
					JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
					clearDataToTable();
					clearInputFields();
					loadDataToTable();
//                txtMaHang.setText(sanPhamDAO.getNextMaHang());
				} else {
					JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
			}
		}
		if (o.equals(btnSua)) {
			if (validateData()) {
				SanPham sp = revertData();
				if (updateSanPhamInDatabase(sp)) {
					JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
					clearInputFields();
					clearDataToTable();
					loadDataToTable();
//                txtMaHang.setText(sanPhamDAO.getNextMaHang());
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại");
				}
			}
		}
		if (o.equals(btnHuy)) {
			clearInputFields();
		}
		if (o.equals(btnXuat)) {
			exportToExcel();
		}
		if (o.equals(btnTim)) {
			btnTimActionPerformed();
		}
		if (btnTaiLai.equals(o)) {
			clearSearchFields();
			clearDataToTable();
			loadDataToTable();
		}
		if (btnLoc.equals(o)) {
			btnLocActionPerformed();
		}
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, message);
	}

//Format chuỗi ngày
	private String getFormattedDate(JDateChooser dateChooser) {
		Date date = (Date) dateChooser.getDate();
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		} else {
			return "";
		}
	}

	String maHang, tenHang, moTa, ngaySX;
	Double donGia;
	int soLuong;
	private JTextField txtTenTim;

	private SanPham revertData() {
		maHang = txtMaHang.getText();
		tenHang = txtTenHang.getText();
		moTa = txtMoTa.getText();
		donGia = Double.parseDouble(txtDonGia.getText());
		soLuong = Integer.parseInt(txtSoLuong.getValue().toString());
		ngaySX = getFormattedDate(txtNgaySX);
		return new SanPham(maHang, tenHang, moTa, donGia, soLuong, ngaySX);
	}

	private boolean validateData() {
		maHang = txtMaHang.getText();
		tenHang = txtTenHang.getText();
		moTa = txtMoTa.getText();
		String str_donGia = txtDonGia.getText();
		ngaySX = getFormattedDate(txtNgaySX);
		soLuong = Integer.parseInt(txtSoLuong.getValue().toString());

		// Regex to include Vietnamese characters
		String vietnamesePattern = "^[\\p{L}\\s]+$";
		String reg_mahang = "MH\\d+";
		if (maHang.isEmpty()) {
			showMessage("Error: Mã hàng không được để trống", txtMaHang);
			return false;
		}
		if (!maHang.matches(reg_mahang)) {
			showMessage("Error: Mã hàng không theo mẫu SP? ", txtTenHang);
			return false;
		}
		if (tenHang.isEmpty()) {
			showMessage("Error: Tên hàng không được để trống", txtTenHang);
			return false;
		}
		if (!tenHang.matches(vietnamesePattern)) {
			showMessage("Error: Tên hàng không theo mẫu [a-zA-Z\\p{L}\\s]+", txtTenHang);
			return false;
		}
		if (moTa.isEmpty()) {
			showMessage("Error: Mô tả không được để trống", txtMoTa);
			return false;
		}
		if (!moTa.matches(vietnamesePattern)) {
			showMessage("Error: Mô tả không theo mẫu [a-zA-Z\\p{L}\\s]+", txtMoTa);
			return false;
		}
		if (str_donGia.isEmpty()) {
			showMessage("Error: Đơn giá không được để trống", txtDonGia);
			return false;
		}
		if (!str_donGia.matches("[0-9]+")) {
			showMessage("Error: Đơn giá phải là số", txtDonGia);
			return false;
		}
		if (soLuong <= 0) {
			JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
			return false;
		}
		return true;
	}

	public boolean isMaHangDuplicate(String maHang) {
		String sql = "SELECT COUNT(*) FROM DanhSachSanPham WHERE MaHang = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maHang);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean deleteSanPhamFromDatabase(String maHang) {
		String sql = "DELETE FROM DanhSachSanPham WHERE MaHang = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maHang);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean addSanPhamToDatabase(SanPham sp) throws ParseException {
		String sql = "INSERT INTO DanhSachSanPham (MaHang, TenHang, MoTa, DonGia, SoLuong, NgaySanXuat) VALUES (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, sp.getMaHang());
			pstmt.setString(2, sp.getTenHang());
			pstmt.setString(3, sp.getMoTa());
			pstmt.setDouble(4, sp.getDonGia());
			pstmt.setInt(5, sp.getSoLuong());
			java.util.Date utilDate = dateFormat.parse(sp.getNgaySX());
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(6, sqlDate);
			pstmt.setDate(6, sqlDate);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void loadDataToTable() {
		List<SanPham> sanPhamList = sanPhamDAO.getAllSanPham();
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		for (SanPham sp : sanPhamList) {
			model.addRow(new Object[] { sp.getMaHang(), sp.getTenHang(), sp.getMoTa(), sp.getDonGia(), sp.getSoLuong(),
					sp.getNgaySX() });
		}
	}

	private boolean updateSanPhamInDatabase(SanPham sp) {
		String sql = "UPDATE DanhSachSanPham SET TenHang = ?, MoTa = ?, DonGia = ?, SoLuong = ?, NgaySanXuat = ? WHERE MaHang = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, sp.getTenHang());
			pstmt.setString(2, sp.getMoTa());
			pstmt.setDouble(3, sp.getDonGia());
			pstmt.setInt(4, sp.getSoLuong());
			pstmt.setString(5, sp.getNgaySX());
			pstmt.setString(6, sp.getMaHang());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void clearDataToTable() {
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		model.setRowCount(0);
	}

	private void clearInputFields() {
		txtMaHang.setText("");
		txtTenHang.setText("");
		txtMoTa.setText("");
		txtDonGia.setText("");
		txtSoLuong.setValue(0);
		txtNgaySX.setDate(null);
	}

	private void exportToExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("DanhSachSanPham");

		// Create header row
		Row headerRow = sheet.createRow(0);
		String[] headers = { "Mã hàng", "Tên hàng", "Mô tả", "Đơn giá", "Số lượng", "Ngày sản xuất" };
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		// Populate data rows
		List<SanPham> sanPhamList = sanPhamDAO.getAllSanPham();
		int rowNum = 1;
		for (SanPham sp : sanPhamList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(sp.getMaHang());
			row.createCell(1).setCellValue(sp.getTenHang());
			row.createCell(2).setCellValue(sp.getMoTa());
			row.createCell(3).setCellValue(sp.getDonGia());
			row.createCell(4).setCellValue(sp.getSoLuong());
			row.createCell(5).setCellValue(sp.getNgaySX());
		}

		// Write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream("DanhSachSanPham.xlsx")) {
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
//Sự kiện nút tìm kiếm

	private void btnTimActionPerformed() {
		String maHang = txtMaTim.getText().trim();
		String tenHang = txtTenTim.getText().trim();
		String ngaySX = getFormattedDate(txtNgaySX);
		String ngayTim = getFormattedDate(dateTim);

		StringBuilder sql = new StringBuilder("SELECT * FROM DanhSachSanPham WHERE 1=1");
		List<Object> params = new ArrayList<>();

		if (!maHang.isEmpty()) {
			sql.append(" AND MaHang LIKE ?");
			params.add("%" + maHang + "%");
		}
		if (!tenHang.isEmpty()) {
			sql.append(" AND TenHang LIKE ?");
			params.add("%" + tenHang + "%");
		}
		if (!ngaySX.isEmpty()) {
			sql.append(" AND NgaySanXuat = ?");
			params.add(ngaySX);
		}
		if (!ngayTim.isEmpty()) {
			sql.append(" AND NgaySanXuat = ?");
			params.add(ngayTim);
		}

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = pstmt.executeQuery()) {
				DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
				model.setRowCount(0);
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("MaHang"), rs.getString("TenHang"), rs.getString("MoTa"),
							rs.getDouble("DonGia"), rs.getInt("SoLuong"), rs.getString("NgaySanXuat") });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//Phương thức lọc sản phẩm theo giá

	private void btnLocActionPerformed() {
		String maHang = txtMaTim.getText().trim();
		String tenHang = txtTenTim.getText().trim();
		String ngaySX = getFormattedDate(txtNgaySX);
		String ngayTim = getFormattedDate(dateTim);
		String startPriceStr = txtStart.getText().trim();
		String endPriceStr = txtEnd.getText().trim();

		StringBuilder sql = new StringBuilder("SELECT * FROM DanhSachSanPham WHERE 1=1");
		List<Object> params = new ArrayList<>();

		if (!maHang.isEmpty()) {
			sql.append(" AND MaHang LIKE ?");
			params.add("%" + maHang + "%");
		}
		if (!tenHang.isEmpty()) {
			sql.append(" AND TenHang LIKE ?");
			params.add("%" + tenHang + "%");
		}
		if (!ngaySX.isEmpty()) {
			sql.append(" AND NgaySanXuat = ?");
			params.add(ngaySX);
		}
		if (!ngayTim.isEmpty()) {
			sql.append(" AND NgaySanXuat = ?");
			params.add(ngayTim);
		}
		if (!startPriceStr.isEmpty() && !endPriceStr.isEmpty()) {
			try {
				double startPrice = Double.parseDouble(startPriceStr);
				double endPrice = Double.parseDouble(endPriceStr);
				sql.append(" AND DonGia BETWEEN ? AND ?");
				params.add(startPrice);
				params.add(endPrice);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Giá phải là số hợp lệ");
				return;
			}
		}

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = pstmt.executeQuery()) {
				DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
				model.setRowCount(0);
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("MaHang"), rs.getString("TenHang"), rs.getString("MoTa"),
							rs.getDouble("DonGia"), rs.getInt("SoLuong"), rs.getString("NgaySanXuat") });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//Phương thức hỗ trợ xóa trắng form tìm kiếm
	private void clearSearchFields() {
		txtMaTim.setText("");
		txtTenTim.setText("");
		txtStart.setText("");
		txtEnd.setText("");
		dateTim.setDate(null);
	}

}
