package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

import controller.QuanLiSanPham;
import model.SanPham;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

public class FrameQuanLySanPham extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtMoTa;
	private JTextField txtDonGia;
	private JDateChooser txtNgaySX;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameQuanLySanPham frame = new FrameQuanLySanPham();
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
	public FrameQuanLySanPham() {
		setTitle("Quản lí sản phẩm\r\n");
		setResizable(true);
		setSize(1440,1024);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pnlBackGround = new JPanel();
		pnlBackGround.setBackground(new Color(255, 187, 53));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 221, 1586, 89);
		panel.setBackground(new Color(255, 128, 64));
		pnlBackGround.add(panel);
		panel.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnThem.png"));
		btnThem.setBackground(new Color(167, 62, 20));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(55, 20, 156, 45);
		panel.add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnSua.png"));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBackground(new Color(167, 62, 20));
		btnSua.setBounds(300, 20, 156, 45);
		panel.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnXoa.png"));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBackground(new Color(167, 62, 20));
		btnXoa.setBounds(565, 20, 156, 45);
		panel.add(btnXoa);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnCancel.png"));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBackground(new Color(167, 62, 20));
		btnHuy.setBounds(862, 20, 156, 45);
		panel.add(btnHuy);
		
		btnXuat = new JButton("Xuất");
		btnXuat.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnprint.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBackground(new Color(167, 62, 20));
		btnXuat.setBounds(1166, 20, 156, 45);
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
		lblNgaySX.setBounds(561, 157, 160, 40);
		pnlBackGround.add(lblNgaySX);
		
		txtMaHang = new JTextField();
		txtMaHang.setEditable(false);
		txtMaHang.setBounds(155, 40, 229, 30);
		pnlBackGround.add(txtMaHang);
		txtMaHang.setColumns(10);
		
		txtTenHang = new JTextField();
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(155, 96, 229, 30);
		pnlBackGround.add(txtTenHang);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(155, 160, 229, 30);
		pnlBackGround.add(txtMoTa);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(718, 33, 259, 40);
		pnlBackGround.add(txtDonGia);
		
		txtNgaySX = new JDateChooser();
		txtNgaySX.setBounds(718, 157, 259, 40);
		pnlBackGround.add(txtNgaySX);
		
		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("D:\\Download\\logoMTP 1.png"));
		logoMTP.setBounds(1054, -3, 403, 214);
		pnlBackGround.add(logoMTP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 348, 996, 292);
		pnlBackGround.add(scrollPane);
		
		tableSanPham = new JTable();
		tableSanPham.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"M\u00E3 h\u00E0ng", "T\u00EAn h\u00E0ng", "M\u00F4 t\u1EA3", "\u0110\u01A1n gi\u00E1 ", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y s\u1EA3n xu\u1EA5t"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Double.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		JTableHeader header = tableSanPham.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        tableSanPham.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(tableSanPham);
		
		txtSoLuong = new JSpinner();
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoLuong.setBounds(718, 88, 127, 40);
		pnlBackGround.add(txtSoLuong);
		
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBackground(new Color(255, 128, 0));
		panelTimKiem.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTimKiem.setBounds(1054, 348, 362, 470);
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
		txtMaTim.setBounds(186, 45, 136, 25);
		panelTimKiem.add(txtMaTim);
		txtMaTim.setColumns(10);
		
		btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(255, 0, 0));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTim.setBounds(120, 179, 85, 25);
		panelTimKiem.add(btnTim);
		
		JLabel lblNewLabel_1 = new JLabel("Lọc theo giá");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(95, 236, 151, 25);
		panelTimKiem.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Từ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 294, 45, 25);
		panelTimKiem.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 58));
		lblNewLabel_3.setBounds(157, 295, 45, 13);
		panelTimKiem.add(lblNewLabel_3);
		
		txtEnd = new JTextField();
		txtEnd.setBounds(202, 288, 96, 31);
		panelTimKiem.add(txtEnd);
		txtEnd.setColumns(10);
		
		txtStart = new JTextField();
		txtStart.setColumns(10);
		txtStart.setBounds(51, 288, 96, 31);
		panelTimKiem.add(txtStart);
		
		btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(this);
			
		btnLoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLoc.setBackground(Color.RED);
		btnLoc.setBounds(62, 347, 85, 25);
		panelTimKiem.add(btnLoc);
		
		JLabel lblNewLabel_4 = new JLabel("Tên hàng:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(31, 93, 116, 25);
		panelTimKiem.add(lblNewLabel_4);
		
		txtTenTim = new JTextField();
		txtTenTim.setColumns(10);
		txtTenTim.setBounds(186, 93, 136, 25);
		panelTimKiem.add(txtTenTim);
		
		JLabel lblNewLabel_4_1 = new JLabel("Ngày sản xuất:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(31, 144, 185, 25);
		panelTimKiem.add(lblNewLabel_4_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(186, 144, 136, 25);
		panelTimKiem.add(dateChooser);
		
		btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTaiLai.setBackground(Color.RED);
		btnTaiLai.setBounds(194, 347, 104, 25);
		panelTimKiem.add(btnTaiLai);

		
		//Full Screen
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
		
		//Khởi tạo danh sách
		txtMaHang.setText(sanPhamDAO.getNextMaHang());
		txtTenHang.requestFocus();
		txtTenHang.selectAll();
		loadDataToTable();
		//Hiển thị dữ liệu để chỉnh sửa

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
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String) tableSanPham.getValueAt(selectedRow, 5));
                txtNgaySX.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
});

		//Action Listener
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXuat.addActionListener(this);
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
            if (addSanPhamToDatabase(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                clearInputFields();
                clearDataToTable();
                loadDataToTable();
                txtMaHang.setText(sanPhamDAO.getNextMaHang());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
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
                loadDataToTable();
                txtMaHang.setText(sanPhamDAO.getNextMaHang());
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
                txtMaHang.setText(sanPhamDAO.getNextMaHang());
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại");
            }
        }
    }
	if(o.equals(btnHuy)) {
		clearInputFields();
	}
	if(o.equals(btnXuat)) {
		exportToExcel();
    }
}




private void showMessage(String message, JTextField txt) {
    txt.requestFocus();
    txt.selectAll();
    JOptionPane.showMessageDialog(this, message);
}
//Format chuỗi ngày
private String getFormattedDate(JDateChooser dateChooser) {
 Date date = dateChooser.getDate();
 if (date != null) {
     SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
     return formatter.format(date);
 } else {
     return "";
 }
}

String maHang,tenHang,moTa,ngaySX; 
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
    tenHang = txtTenHang.getText();
    moTa = txtMoTa.getText();
    String str_donGia = txtDonGia.getText();
    ngaySX = getFormattedDate(txtNgaySX);
    soLuong = Integer.parseInt(txtSoLuong.getValue().toString());

    // Regex to include Vietnamese characters
    String vietnamesePattern = "^[\\p{L}\\s]+$";

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
private boolean addSanPhamToDatabase(SanPham sp) {
    String sql = "INSERT INTO DanhSachSanPham (MaHang, TenHang, MoTa, DonGia, SoLuong, NgaySanXuat) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, sp.getMaHang());
        pstmt.setString(2, sp.getTenHang());
        pstmt.setString(3, sp.getMoTa());
        pstmt.setDouble(4, sp.getDonGia());
        pstmt.setInt(5, sp.getSoLuong());
        pstmt.setString(6, sp.getNgaySX());
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
        model.addRow(new Object[] { sp.getMaHang(), sp.getTenHang(), sp.getMoTa(), sp.getDonGia(), sp.getSoLuong(), sp.getNgaySX() });
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
    String[] headers = {"Mã hàng", "Tên hàng", "Mô tả", "Đơn giá", "Số lượng", "Ngày sản xuất"};
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


}
