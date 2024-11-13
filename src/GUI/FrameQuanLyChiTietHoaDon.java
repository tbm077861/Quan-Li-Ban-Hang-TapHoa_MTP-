package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import DAO.ChiTietHoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import connectDB.ConnectDB;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import entity.SanPham;
import java.util.List;
import java.util.Locale;

public class FrameQuanLyChiTietHoaDon extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTable tableSanPham;
	private JTable tableChiTiet;
	private JTextField txtMaHD;
	private JButton btnIn;
	private JButton btnLuu;
	private JButton btnXoa;
	private JLabel txtTongTien;
	private JComboBox<String> txtTenKH;
	private JComboBox<String> txtMaKH;
	private KhachHangDAO khachHangDAO;
	private JComboBox<String> txtTenNV;
	private JComboBox<String> txtMaNV;
	private NhanVienDAO nhanVienDAO;
	private SanPhamDAO sanPhamDAO = new SanPhamDAO();
	public static final int NO_SUCH_PAGE = 1;
	public static final int PAGE_EXISTS = 0;
	/**
	 * Launch the application.
	 */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    FrameQuanLyChiTietHoaDon frame = new FrameQuanLyChiTietHoaDon();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
	private TableColumn dateColumn;
	private JTextField txtTimSanPham;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public FrameQuanLyChiTietHoaDon() {
//        setTitle("Quản lí hóa đơn\r\n");
//        setResizable(true);
//        setSize(1440, 1024);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        pnlBackGround = new JPanel();
//        pnlBackGround.setBackground(new Color(255, 187, 53));
//        pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        setContentPane(pnlBackGround);
//        pnlBackGround.setLayout(null);
//    	
//        JPanel panel = new JPanel();
//        panel.setBounds(0, 245, 1586, 65);
//        panel.setBackground(new Color(255, 128, 64));
//        pnlBackGround.add(panel);
//        panel.setLayout(null);

		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1559, 761);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 245, 1594, 75);
		panel.setBackground(new Color(242, 132, 123));
		JLabel lblMaHD = new JLabel("Mã hóa đơn :");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMaHD.setBounds(1086, 9, 160, 45);
		panel.setLayout(null);
		pnlBackGround.add(panel);
		panel.add(lblMaHD);

		txtMaHD = new JTextField();
		txtMaHD.setText(generateMaHD());
		txtMaHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaHD.setEditable(false);
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(1256, 12, 264, 43);
		panel.add(txtMaHD);

		JLabel lblNgayLap = new JLabel("Ngày lập :");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNgayLap.setBounds(578, 9, 110, 45);
		panel.add(lblNgayLap);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd"); // format datechooser
		dateChooser.setDate(new Date()); // set datechooser thành ngày hiện tại
		dateChooser.setEnabled(false); // không cho phép chỉnh sửa ngày trên datechooser
		dateChooser.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 16)); // set font chữ cho
																									// datechooser
		dateChooser.setBounds(724, 11, 264, 43);
		panel.add(dateChooser);
		
		txtTimSanPham = new JTextField();
		txtTimSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimSanPham.setBounds(10, 14, 264, 43);
		panel.add(txtTimSanPham);
		txtTimSanPham.setColumns(10);
		
		JButton btnTimSanPham = new JButton("Tìm");
		btnTimSanPham.addActionListener(e -> btnTimSanPhamAction());
		btnTimSanPham.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnTimSanPham.setBounds(295, 18, 97, 34);
		panel.add(btnTimSanPham);
		
		JButton btnTaiLaiTimSanPham = new JButton("Tải Lại");
		btnTaiLaiTimSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTimSanPham.setText("");
				loadDataToTable();
			}
		});
		btnTaiLaiTimSanPham.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnTaiLaiTimSanPham.setBounds(422, 17, 97, 37);
		panel.add(btnTaiLaiTimSanPham);

		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("image\\logoMTP 1.png"));
		logoMTP.setBounds(1191, 0, 403, 214);
		pnlBackGround.add(logoMTP);

		JPanel panel_left = new JPanel();
		TitledBorder titledBorderLeft = BorderFactory.createTitledBorder(new LineBorder(Color.RED, 2),
				"Danh sách sản phẩm");
		titledBorderLeft.setTitleFont(new Font("Arial", Font.BOLD, 17));
		panel_left.setBorder(titledBorderLeft);
		panel_left.setBackground(new Color(242, 132, 123));
		panel_left.setBounds(20, 341, 828, 403);
		pnlBackGround.add(panel_left);
		panel_left.setLayout(null);

		JScrollPane scrollPaneSanPham = new JScrollPane();
		scrollPaneSanPham.setBounds(24, 28, 783, 347);
		panel_left.add(scrollPaneSanPham);

		tableSanPham = new JTable();
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader headerSanPham = tableSanPham.getTableHeader();
		headerSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		tableSanPham.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Hàng", "Tên Hàng", "Mô Tả", "Đơn Giá", "Số lượng", "Ngày Sản Xuất" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class, Integer.class,
					String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
//        tableSanPham.getColumnModel().getColumn(2).setResizable(false);
		scrollPaneSanPham.setViewportView(tableSanPham);
		JPanel panel_right = new JPanel();
		TitledBorder titledBorderRight = BorderFactory.createTitledBorder(new LineBorder(Color.RED, 2),
				"Chi tiết hóa đơn");
		titledBorderRight.setTitleFont(new Font("Arial", Font.BOLD, 17));
		panel_right.setBorder(titledBorderRight);
		panel_right.setBackground(new Color(242, 132, 123));
		panel_right.setBounds(873, 341, 651, 403);
		pnlBackGround.add(panel_right);
		panel_right.setLayout(null);

		JScrollPane scrollPaneChiTiet = new JScrollPane();
		scrollPaneChiTiet.setBounds(20, 25, 621, 258);
		panel_right.add(scrollPaneChiTiet);

		tableChiTiet = new JTable();
		tableChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader headerChiTiet = tableChiTiet.getTableHeader();
		headerChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPaneChiTiet.setViewportView(tableChiTiet);
		tableChiTiet.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Hàng", "Tên Hàng", "Mô Tả", "Đơn giá", "Số lượng" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class,
					Integer.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		btnIn = new JButton("In");
		btnIn.setBounds(506, 348, 122, 42);
		panel_right.add(btnIn);
		btnIn.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnIn.setForeground(new Color(64, 0, 64));
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIn.setBackground(new Color(167, 62, 20));

		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(347, 348, 122, 42);
		panel_right.add(btnLuu);
		btnLuu.setIcon(new ImageIcon("icon\\btnLuu.png"));
		btnLuu.setForeground(new Color(64, 0, 64));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLuu.setBackground(new Color(167, 62, 20));

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(183, 348, 122, 42);
		panel_right.add(btnXoa);
		btnXoa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXoa.setForeground(new Color(64, 0, 64));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXoa.setBackground(new Color(167, 62, 20));

		JLabel lblTongTien = new JLabel("Tổng tiền :");
		lblTongTien.setBounds(173, 293, 122, 45);
		panel_right.add(lblTongTien);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 20));

		txtTongTien = new JLabel("");
		txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTongTien.setBounds(323, 293, 248, 45);
		panel_right.add(txtTongTien);

		JButton btnTaoMoi = new JButton("Tạo");
		btnTaoMoi.addActionListener(e -> btnTaoMoiAction());
		btnTaoMoi.setIcon(new ImageIcon("icon\\btnThem.png"));
		btnTaoMoi.setForeground(new Color(64, 0, 64));
		btnTaoMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTaoMoi.setBackground(new Color(167, 62, 20));
		btnTaoMoi.setBounds(20, 348, 122, 42);
		panel_right.add(btnTaoMoi);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(75, 23, 494, 191);
		pnlBackGround.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTenKH = new JLabel("Tên khách hàng :");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenKH.setBounds(27, 27, 160, 45);
		panel_1.add(lblTenKH);

		JLabel lblMaKH = new JLabel("Mã khách hàng :");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(27, 100, 160, 45);
		panel_1.add(lblMaKH);

		txtTenKH = new JComboBox();
		txtTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTenKH.setBounds(197, 27, 237, 45);
		panel_1.add(txtTenKH);

		txtMaKH = new JComboBox();
		txtMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaKH.setBounds(197, 100, 237, 45);
		panel_1.add(txtMaKH);

		// Thêm data vào combobox khách hàng
		khachHangDAO = new KhachHangDAO();
		Map<String, String> khachHangData = khachHangDAO.getKhachHangData();
		for (Map.Entry<String, String> entry : khachHangData.entrySet()) {
			txtMaKH.addItem(entry.getKey());
			txtTenKH.addItem(entry.getValue());
		}

		txtTenKH.addActionListener(e -> {
			String selectedTenKH = (String) txtTenKH.getSelectedItem();
			for (Map.Entry<String, String> entry : khachHangData.entrySet()) {
				if (entry.getValue().equals(selectedTenKH)) {
					txtMaKH.setSelectedItem(entry.getKey());
					break;
				}
			}
		});

		txtMaKH.addActionListener(e -> {
			String selectedMaKH = (String) txtMaKH.getSelectedItem();
			txtTenKH.setSelectedItem(khachHangData.get(selectedMaKH));
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(762, 23, 500, 191);
		pnlBackGround.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTenNV = new JLabel("Tên nhân viên :");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNV.setBounds(25, 23, 160, 45);
		panel_2.add(lblTenNV);

		JLabel lblMaNV = new JLabel("Mã nhân viên :");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(25, 106, 160, 45);
		panel_2.add(lblMaNV);

		txtTenNV = new JComboBox();
		txtTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTenNV.setBounds(182, 23, 237, 45);
		panel_2.add(txtTenNV);

		txtMaNV = new JComboBox();
		txtMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaNV.setBounds(182, 106, 237, 45);
		panel_2.add(txtMaNV);
		// Thêm data vào combobox nhân viên
		nhanVienDAO = new NhanVienDAO();
		Map<String, String> nhanVienData = nhanVienDAO.getNhanVienData();
		for (Map.Entry<String, String> entry : nhanVienData.entrySet()) {
			txtMaNV.addItem(entry.getKey());
			txtTenNV.addItem(entry.getValue());
		}

		txtTenNV.addActionListener(e -> {
			String selectedTenNV = (String) txtTenNV.getSelectedItem();
			for (Map.Entry<String, String> entry : nhanVienData.entrySet()) {
				if (entry.getValue().equals(selectedTenNV)) {
					txtMaNV.setSelectedItem(entry.getKey());
					break;
				}
			}
		});

		txtMaNV.addActionListener(e -> {
			String selectedMaNV = (String) txtMaNV.getSelectedItem();
			txtTenNV.setSelectedItem(nhanVienData.get(selectedMaNV));
		});
		// Tải dữ liệu vào bảng sản phẩm
		loadDataToTable();

		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableSanPham.getSelectedRow();
				if (selectedRow != -1) {
					String maHang = (String) tableSanPham.getValueAt(selectedRow, 0);
					String tenHang = (String) tableSanPham.getValueAt(selectedRow, 1);
					String moTa = (String) tableSanPham.getValueAt(selectedRow, 2);
					double donGia = (double) tableSanPham.getValueAt(selectedRow, 3);
					int soLuong = (int) tableSanPham.getValueAt(selectedRow, 4);

					String input = JOptionPane.showInputDialog("Nhập số lượng:");
					if (input != null && !input.isEmpty()) {
						try {
							int soLuongNhap = Integer.parseInt(input);
							if (soLuongNhap > 0 && soLuongNhap <= soLuong) {
								DefaultTableModel chiTietModel = (DefaultTableModel) tableChiTiet.getModel();
								chiTietModel.addRow(new Object[] { maHang, tenHang, moTa, donGia, soLuongNhap });
								tableChiTiet.getColumnModel().getColumn(3)
										.setCellRenderer(new DefaultTableCellRenderer() {
											private static final long serialVersionUID = 1L;
											private NumberFormat currencyFormat = NumberFormat
													.getCurrencyInstance(new Locale("vi", "VN"));

											@Override
											protected void setValue(Object value) {
												if (value instanceof Number) {
													value = currencyFormat.format((Number) value);
												}
												super.setValue(value);
											}
										});
								// Update quantity in tableSanPham
								tableSanPham.setValueAt(soLuong - soLuongNhap, selectedRow, 4);

								// Calculate total amount
								double tongTien = 0;
								for (int i = 0; i < chiTietModel.getRowCount(); i++) {
									double gia = (double) chiTietModel.getValueAt(i, 3);
									int soLuongMua = (int) chiTietModel.getValueAt(i, 4);
									tongTien += gia * soLuongMua;
								}

								// Update txtTongTien
								NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
								txtTongTien.setText(currencyFormat.format(tongTien));
							} else {
								JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ!");
						}
					}
				}
			}
		});

		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnIn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnIn)) {
			printInvoice();
		}
		if (o.equals(btnLuu)) {
			saveChiTietHDToDatabase();
		}
		if (o.equals(btnXoa)) {
			int selectedRow = tableChiTiet.getSelectedRow();
			if (selectedRow != -1) {
				DefaultTableModel chiTietModel = (DefaultTableModel) tableChiTiet.getModel();
				String maHang = (String) chiTietModel.getValueAt(selectedRow, 0);
				int soLuongNhap = (int) chiTietModel.getValueAt(selectedRow, 4);
				chiTietModel.removeRow(selectedRow);
				DefaultTableModel sanPhamModel = (DefaultTableModel) tableSanPham.getModel();
				for (int i = 0; i < sanPhamModel.getRowCount(); i++) {
					if (sanPhamModel.getValueAt(i, 0).equals(maHang)) {
						int currentSoLuong = (int) sanPhamModel.getValueAt(i, 4);
						sanPhamModel.setValueAt(currentSoLuong + soLuongNhap, i, 4);
						break;
					}
				}
				calculateTotalCost();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa!");
			}
		}
	}

	private void loadDataToTable() {
		SanPhamDAO sanPhamDAO = new SanPhamDAO();
		List<SanPham> sanPhamList = sanPhamDAO.getAllSanPham();
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();

		// Clear existing rows
		model.setRowCount(0);

		// Add rows to the table model
		for (SanPham sp : sanPhamList) {
			model.addRow(new Object[] { sp.getMaHang(), sp.getTenHang(), sp.getMoTa(), sp.getDonGia(), sp.getSoLuong(),
					sp.getNgaySX() });
		}

		// Format the "Đơn giá" column to display currency format
		tableSanPham.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

			@Override
			protected void setValue(Object value) {
				if (value instanceof Number) {
					value = currencyFormat.format((Number) value);
				}
				super.setValue(value);
			}
		});
	}

	private void saveChiTietHDToDatabase() {
		String maHD = txtMaHD.getText();
		String maNV = txtMaNV.getSelectedItem().toString();
		String maKhachHang = txtMaKH.getSelectedItem().toString();
		Date ngayLap = new Date();

		String sqlHoaDon = "INSERT INTO HoaDon (MaHD, MANV, maKhachHang, NgayLap) VALUES (?, ?, ?, ?)";
		String sqlChiTietHD = "INSERT INTO ChiTietHD (MaHD, MaHang, SoLuongSanPham, DonGia) VALUES (?, ?, ?, ?)";
		String sqlUpdateSanPham = "UPDATE DanhSachSanPham SET SoLuong = SoLuong - ? WHERE MaHang = ?";

		try (Connection connection = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement statementHoaDon = connection.prepareStatement(sqlHoaDon);
				PreparedStatement statementChiTietHD = connection.prepareStatement(sqlChiTietHD);
				PreparedStatement statementUpdateSanPham = connection.prepareStatement(sqlUpdateSanPham)) {

			// Insert into HoaDon table
			statementHoaDon.setString(1, maHD);
			statementHoaDon.setString(2, maNV);
			statementHoaDon.setString(3, maKhachHang);
			statementHoaDon.setDate(4, new java.sql.Date(ngayLap.getTime()));
			statementHoaDon.executeUpdate();

			// Insert into ChiTietHD table and update DanhSachSanPham table
			for (int i = 0; i < tableChiTiet.getRowCount(); i++) {
				String maHang = (String) tableChiTiet.getValueAt(i, 0);
				int soLuongSanPham = (int) tableChiTiet.getValueAt(i, 4);
				String donGiaStr = tableChiTiet.getValueAt(i, 3).toString();

				// Remove currency formatting
				donGiaStr = donGiaStr.replaceAll("[^\\d.]", "");
				double donGia = Double.parseDouble(donGiaStr);

				statementChiTietHD.setString(1, maHD);
				statementChiTietHD.setString(2, maHang);
				statementChiTietHD.setInt(3, soLuongSanPham);
				statementChiTietHD.setDouble(4, donGia);
				statementChiTietHD.addBatch();

				// Update product quantity in DanhSachSanPham table
				statementUpdateSanPham.setInt(1, soLuongSanPham);
				statementUpdateSanPham.setString(2, maHang);
				statementUpdateSanPham.addBatch();
			}
			statementChiTietHD.executeBatch();
			statementUpdateSanPham.executeBatch();
			JOptionPane.showMessageDialog(null, "Lưu hóa đơn và chi tiết hóa đơn thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn và chi tiết hóa đơn!");
		}
	}

	private void printInvoice() {
		// check if the MaHD is available
		if (!isHoaDonExists(txtMaHD.getText())) {
			JOptionPane.showMessageDialog(null, "Hóa đơn không tồn tại!");
			return;
		}
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable((graphics, pageFormat, pageIndex) -> {
			if (pageIndex > 0) {
				return NO_SUCH_PAGE;
			}

			// Print the header
			graphics.drawString("Hóa Đơn", 100, 100);
			graphics.drawString("Mã Hóa Đơn: " + txtMaHD.getText(), 100, 120);
			graphics.drawString("Tên Khách Hàng: " + txtTenKH.getSelectedItem().toString(), 100, 140);
			graphics.drawString("Tên Nhân Viên: " + txtTenNV.getSelectedItem().toString(), 100, 160);
			graphics.drawString("Ngày Lập: " + new java.sql.Date(new Date().getTime()), 100, 180);

			// Print the table
			graphics.drawString("Mã Hàng", 100, 200);
			graphics.drawString("Tên Hàng", 200, 200);
			graphics.drawString("Đơn Giá", 350, 200);
			graphics.drawString("Số lượng", 480, 200);
			JTable table = tableChiTiet;
			TableModel model = table.getModel();
			int y = 220;
			for (int i = 0; i < model.getRowCount(); i++) {
				graphics.drawString(model.getValueAt(i, 0).toString(), 100, y);
				graphics.drawString(model.getValueAt(i, 1).toString(), 200, y);
				graphics.drawString(model.getValueAt(i, 3).toString(), 350, y);
				graphics.drawString(model.getValueAt(i, 4).toString(), 480, y);
				y += 20;
			}

			// Print the total amount
			graphics.drawString("Thành tiền: " + txtTongTien.getText(), 100, y + 20);

			return PAGE_EXISTS;
		});

		boolean doPrint = job.printDialog();
		if (doPrint) {
			try {
				job.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}

	private String generateMaHD() {
		String newMaHD = "HD001"; // Default value if no records are found
		String sql = "SELECT MAX(MaHD) FROM ChiTietHD";

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				String maxMaHD = rs.getString(1);
				if (maxMaHD != null) {
					int num = Integer.parseInt(maxMaHD.substring(2)) + 1;
					newMaHD = String.format("HD%03d", num);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newMaHD;
	}

	// Clear Table after clicked button Lưu
	private void clearTableChiTietHoaDon() {
		DefaultTableModel model = (DefaultTableModel) tableChiTiet.getModel();
		model.setRowCount(0);
	}

	private boolean isHoaDonExists(String maHD) {
		String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maHD);
			ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Tính tổng tiền
	private void calculateTotalCost() {
		DefaultTableModel chiTietModel = (DefaultTableModel) tableChiTiet.getModel();
		double tongTien = 0;
		for (int i = 0; i < chiTietModel.getRowCount(); i++) {
			double gia = (double) chiTietModel.getValueAt(i, 3);
			int soLuongMua = (int) chiTietModel.getValueAt(i, 4);
			tongTien += gia * soLuongMua;
		}
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		txtTongTien.setText(currencyFormat.format(tongTien));
	}

	// Tạo mới hóa đơn
	private void btnTaoMoiAction() {
		clearTableChiTietHoaDon();
		txtMaHD.setText(generateMaHD());
		calculateTotalCost();
	}
	
	// Tìm sản phẩm
	private void btnTimSanPhamAction() {
		String tenSanPham = txtTimSanPham.getText();
		List<SanPham> sanPhamList = sanPhamDAO.searchSanPham(tenSanPham);
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		model.setRowCount(0);
		for (SanPham sp : sanPhamList) {
			model.addRow(new Object[] { sp.getMaHang(), sp.getTenHang(), sp.getMoTa(), sp.getDonGia(), sp.getSoLuong(),
					sp.getNgaySX() });
		}
	}
}
