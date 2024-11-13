package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import DAO.ChiTietHoaDonDAO;
import DAO.DanhSachHoaDonDAO;
import connectDB.ConnectDB;
import entity.SanPham;

public class FrameDanhSachHoaDon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTable tableDSHD;
	private JTextField txtMaHoaDonTim;
	private JTextField txtMaNhanVienTim;
	private JTextField txtMaKhachHangTim;
	private DefaultTableModel tableModel;
	private JDateChooser dateChooserFrom, dateChooserTo;
	private DanhSachHoaDonDAO hoaDonDao = new DanhSachHoaDonDAO();
	private ChiTietHoaDonDAO chiTietHoaDonDao = new ChiTietHoaDonDAO();
	public static final int NO_SUCH_PAGE = 1;
	public static final int PAGE_EXISTS = 0;

	// Cập nhật dữ liệu vào table
	private void loadDataToTable() {
		List<Object[]> data = hoaDonDao.loadDataToTable();
		tableModel = (DefaultTableModel) tableDSHD.getModel();
		tableModel.setRowCount(0);
		for (Object[] rowData : data) {
			tableModel.addRow(rowData);
		}
	}

	// Xóa hóa đơn
	private void btnXoaAction() {
		int row = tableDSHD.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(FrameDanhSachHoaDon.this, "Vui lòng chọn hóa đơn cần xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(FrameDanhSachHoaDon.this,
				"Bạn có chắc chắn muốn xóa hóa đơn này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			String maHoaDon = tableDSHD.getValueAt(row, 0).toString();
			hoaDonDao.deleteHoaDon(maHoaDon);
			loadDataToTable();
		}
	}

	// Tìm kiếm hóa đơn
	private void btnTimAction() {
		String maHoaDon = txtMaHoaDonTim.getText().trim();
		String maNhanVien = txtMaNhanVienTim.getText().trim();
		String maKhachHang = txtMaKhachHangTim.getText().trim();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = dateChooserFrom.getDate() == null ? "" : dateFormat.format(dateChooserFrom.getDate());
		String toDate = dateChooserTo.getDate() == null ? "" : dateFormat.format(dateChooserTo.getDate());

		List<Object[]> data = hoaDonDao.searchHoaDon(maHoaDon, maNhanVien, maKhachHang, fromDate, toDate);
		tableModel = (DefaultTableModel) tableDSHD.getModel();
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

	// Xóa dữ liệu trong các ô tìm kiếm
	private void clearSearch() {
		txtMaHoaDonTim.setText("");
		txtMaNhanVienTim.setText("");
		txtMaKhachHangTim.setText("");
	}

	private String getTenNhanVien(String maNhanVien) {
		String tenNhanVien = "";
		String sql = "SELECT HoTen FROM TaiKhoanNV WHERE MANV = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maNhanVien);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					tenNhanVien = rs.getString("HoTen");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenNhanVien;
	}

	private String getTenKhachHang(String maKhachHang) {
		String tenKhachHang = "";
		String sql = "SELECT tenKhachHang FROM KhachHang WHERE maKhachHang = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maKhachHang);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					tenKhachHang = rs.getString("tenKhachHang");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenKhachHang;
	}
	
	// In hóa đơn
	private void btnInHoaDonAction() {

		int row = tableDSHD.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(FrameDanhSachHoaDon.this, "Vui lòng chọn hóa đơn cần in!");
			return;
		}

		String maHoaDon = tableDSHD.getValueAt(row, 0).toString();
		String tenKhachHang = getTenKhachHang(tableDSHD.getValueAt(row, 2).toString());
		String tenNhanVien = getTenNhanVien(tableDSHD.getValueAt(row, 1).toString());

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable((graphics, pageFormat, pageIndex) -> {
			if (pageIndex > 0) {
				return NO_SUCH_PAGE;
			}

			// Print the header
			graphics.drawString("Hóa Đơn", 100, 100);
			graphics.drawString("Mã Hóa Đơn: " + maHoaDon, 100, 120);
			graphics.drawString("Tên Khách Hàng: " + tenKhachHang, 100, 140);
			graphics.drawString("Tên Nhân Viên: " + tenNhanVien, 100, 160);
			graphics.drawString("Ngày Lập: " + new java.sql.Date(new Date().getTime()), 100, 180);

			// Print the table
			graphics.drawString("Mã Hàng", 100, 200);
			graphics.drawString("Tên Hàng", 200, 200);
			graphics.drawString("Đơn Giá", 350, 200);
			graphics.drawString("Số lượng", 480, 200);
			List<Object[]> chiTietList = chiTietHoaDonDao.getChiTietHoaDon(maHoaDon);
			int y = 220;
			for (Object[] rowChiTiet : chiTietList) {
				graphics.drawString(rowChiTiet[0].toString(), 100, y);
				graphics.drawString(rowChiTiet[1].toString(), 200, y);
				graphics.drawString(rowChiTiet[2].toString(), 350, y);
				graphics.drawString(rowChiTiet[3].toString(), 480, y);
				y += 20;
			}

			// Print the total amount
			graphics.drawString("Total: " + calculateTotalAmount(maHoaDon), 100, y + 20);

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

	// Tính tổng tiền

	private String calculateTotalAmount(String maHoaDon) {
		double totalAmount = 0;

		String sql = "SELECT SUM(ct.SoLuongSanPham * ct.DonGia) " + "FROM ChiTietHD ct "
				+ "JOIN HoaDon hd ON ct.MaHD = hd.MaHD " + "WHERE ct.MaHD = ?";

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maHoaDon);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					totalAmount = rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	    return currencyFormatter.format(totalAmount);
	}
	
	// Xuất File
	private void btnXuatFileAction() {
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("DanhSachHoaDon");

	    // Create header row
	    Row headerRow = sheet.createRow(0);
	    String[] headers = {"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập đơn"};
	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	    }

	    // Populate data rows
	    List<Object[]> HoaDonList = hoaDonDao.getAllHoaDon();
	    int rowNum = 1;
	    for (Object[] hoaDon : HoaDonList) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue((String) hoaDon[0]);
	        row.createCell(1).setCellValue((String) hoaDon[1]);
	        row.createCell(2).setCellValue((String) hoaDon[2]);
	        row.createCell(3).setCellValue((String) hoaDon[3]);
	    }

	    // Write the output to a file
	    try (FileOutputStream fileOut = new FileOutputStream("DanhSachHoaDon.xlsx")) {
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
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public FrameDanhSachHoaDon() {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1543, 751);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 1586, 89);
		panel.setBackground(new Color(242, 132, 123));
		pnlBackGround.add(panel);
		panel.setLayout(null);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(250, 20, 179, 48);
		panel.add(btnXoa);
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXoa.addActionListener(e -> btnXoaAction());

		JButton btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setBounds(685, 20, 179, 48);
		panel.add(btnXuatFile);
		btnXuatFile.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnXuatFile.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXuatFile.addActionListener(e -> btnXuatFileAction());

		JButton btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setBounds(1113, 20, 179, 48);
		panel.add(btnInHoaDon);
		btnInHoaDon.setIcon(new ImageIcon("icon\\hoadon.png"));
		btnInHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnInHoaDon.addActionListener(e -> btnInHoaDonAction());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 109, 1067, 632);
		pnlBackGround.add(scrollPane);

		tableDSHD = new JTable();
		tableDSHD.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập đơn" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JTableHeader header = tableDSHD.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPane.setViewportView(tableDSHD);

		JPanel PanelTimKiem = new JPanel();
		PanelTimKiem.setBackground(new Color(242, 132, 123));
		PanelTimKiem.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelTimKiem.setBounds(1098, 208, 422, 390);
		pnlBackGround.add(PanelTimKiem);
		PanelTimKiem.setLayout(null);

		JLabel lbMHoaDon = new JLabel("Mã hóa đơn");
		lbMHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbMHoaDon.setBounds(22, 38, 123, 34);
		PanelTimKiem.add(lbMHoaDon);

		txtMaHoaDonTim = new JTextField();
		txtMaHoaDonTim.setBounds(167, 41, 225, 34);
		PanelTimKiem.add(txtMaHoaDonTim);
		txtMaHoaDonTim.setColumns(10);

		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(32, 265, 169, 34);
		PanelTimKiem.add(dateChooserFrom);

		dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(242, 265, 169, 34);
		PanelTimKiem.add(dateChooserTo);

		JLabel lblNewLabel_4 = new JLabel("-");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(213, 265, 47, 34);
		PanelTimKiem.add(lblNewLabel_4);

		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(e -> btnTimAction());
		btnTim.setBackground(new Color(0, 0, 0));
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTim.setBounds(58, 329, 120, 40);
		PanelTimKiem.add(btnTim);

		JButton btnTaiLai = new JButton("Tải lại");
		btnTaiLai.addActionListener(e -> btnTaiLaiAction());
		btnTaiLai.setBackground(new Color(0, 0, 0));
		btnTaiLai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTaiLai.setBounds(252, 329, 120, 40);
		PanelTimKiem.add(btnTaiLai);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMKhchHng.setBounds(22, 157, 123, 34);
		PanelTimKiem.add(lblMKhchHng);

		txtMaNhanVienTim = new JTextField();
		txtMaNhanVienTim.setColumns(10);
		txtMaNhanVienTim.setBounds(167, 102, 225, 34);
		PanelTimKiem.add(txtMaNhanVienTim);

		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMNhnVin.setBounds(22, 99, 123, 34);
		PanelTimKiem.add(lblMNhnVin);

		txtMaKhachHangTim = new JTextField();
		txtMaKhachHangTim.setColumns(10);
		txtMaKhachHangTim.setBounds(167, 157, 225, 34);
		PanelTimKiem.add(txtMaKhachHangTim);

		JLabel lblNgyLpn = new JLabel("Ngày lập đơn");
		lblNgyLpn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgyLpn.setBounds(22, 210, 123, 34);
		PanelTimKiem.add(lblNgyLpn);

		loadDataToTable();

	}
}
