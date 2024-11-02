package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import Dao.QuanLiSanPham;
import entity.SanPham;

public class FrameQuanLySanPham extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtMoTa;
	private JTextField txtDonGia;
	private JDateChooser txtNgaySX;
	private JTable tableSanPham;
	private QuanLiSanPham dsSanPham;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnHuy;
	private JButton btnXuat;
	private JTextField txtMaTim;
	private JTextField txtEnd;
	private JTextField txtStart;
	private JSpinner txtSoLuong;

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
	 * Create the panel.
	 */
	public FrameQuanLySanPham() {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1540, 755);
		pnlBackGround.setBackground(new Color(255, 187, 53));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 221, 1586, 89);
		panel.setBackground(new Color(255, 128, 64));
		pnlBackGround.add(panel);
		panel.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("icon\\btnThem.png"));
		btnThem.setBackground(new Color(167, 62, 20));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(55, 20, 156, 45);
		panel.add(btnThem);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon("icon\\btnLuu.png"));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLuu.setBackground(new Color(167, 62, 20));
		btnLuu.setBounds(278, 20, 156, 45);
		panel.add(btnLuu);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("icon\\btnSua.png"));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBackground(new Color(167, 62, 20));
		btnSua.setBounds(498, 20, 156, 45);
		panel.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("icon\\btnXoa.png"));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBackground(new Color(167, 62, 20));
		btnXoa.setBounds(716, 20, 156, 45);
		panel.add(btnXoa);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setIcon(new ImageIcon("icon\\btnCancel.png"));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBackground(new Color(167, 62, 20));
		btnHuy.setBounds(961, 20, 156, 45);
		panel.add(btnHuy);
		
		btnXuat = new JButton("Xuất");
		btnXuat.setIcon(new ImageIcon("icon\\btnprint.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBackground(new Color(167, 62, 20));
		btnXuat.setBounds(1226, 20, 156, 45);
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
		logoMTP.setIcon(new ImageIcon("logoMTP 1.png"));
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
		
		JButton btnTim = new JButton("Tìm");
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
		
		JButton btnLoc = new JButton("Lọc");
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
		
		JButton btnTaiLai = new JButton("Tải lại");
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTaiLai.setBackground(Color.RED);
		btnTaiLai.setBounds(194, 347, 104, 25);
		panelTimKiem.add(btnTaiLai);

		
		//Full Screen
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
		
		//Khởi tạo danh sách
		dsSanPham = new QuanLiSanPham();
		txtMaHang.setText(generateMaHang());
		txtTenHang.requestFocus();
		//Action Listener
		btnThem.addActionListener(this);
		btnLoc.addActionListener(this);
		btnXoa.addActionListener(this);
		btnHuy.addActionListener(this);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object o = e.getSource();
	if (o.equals(btnThem)) {
		addDataToTable();
	}
	if(o.equals(btnXoa)) {
		deleteData();
	}
	
}


private void deleteData() {
	// TODO Auto-generated method stub
	int row = tableSanPham.getSelectedRow();
	if(row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa");
        return;
	}
	String maHang = tableSanPham.getValueAt(row, 0).toString();
	if(dsSanPham.removeSanPham(maHang)) {
        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.removeRow(row);
        txtMaHang.setText(generateMaHang());
        JOptionPane.showMessageDialog(this, "Xóa thành công");
        }else {
			JOptionPane.showMessageDialog(this, "Xóa thất bại");
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
//Tự động cập nhật mã hàng
private String generateMaHang() {
int size = dsSanPham.getList().size(); 
return "SP" + (size + 1);
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

public boolean validateData() {
	 tenHang = txtTenHang.getText();
	 moTa = txtMoTa.getText();
	 String str_donGia = txtDonGia.getText();
	 ngaySX = getFormattedDate(txtNgaySX);
	 soLuong = Integer.parseInt(txtSoLuong.getValue().toString());
	if (!(tenHang.length() > 0 && tenHang.matches("[a-zA-Z]+"))) {
		showMessage("Error:Mã hàng không theo mẫu [a-zA-Z ]+", txtTenHang);
		return false;
	}
	if (!(moTa.length() > 0 && moTa.matches("[a-zA-Z ]+"))) {
        showMessage("Error:Mô tả không theo mẫu [a-zA-Z ]+", txtMoTa);
        return false;
	}
	if (!(str_donGia.length() > 0 && str_donGia.matches("[0-9]+"))) {
        showMessage("Error:Đơn giá phải là số", txtDonGia);
        return false;
    }
	if (!(soLuong > 0)) {
         JOptionPane.showMessageDialog(this,"Số lượng phải lớn hơn 0");
        return false;
    }
	return true;
}
private void addDataToTable() {
	if (validateData()) {
		SanPham sp = revertData();
		if(dsSanPham.addSanPham(sp)){
			txtMaHang.setText(generateMaHang());
			DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
			model.addRow(new Object[] { sp.getMaHang(), sp.getTenHang(), sp.getMoTa(), sp.getDonGia(), sp.getSoLuong(),
					sp.getNgaySX() });
		}else{
            JOptionPane.showMessageDialog(this,"Thêm sản phẩm thất bại");
        }
	}
}
}
