package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;

public class FrameQuanLyHoaDon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaHoaDon;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtChietKhau;
	private JTextField txtTongHoaDon;
	private JTable tableHoaDon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameQuanLyHoaDon frame = new FrameQuanLyHoaDon();
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
	public FrameQuanLyHoaDon() {
		setTitle("Quản lí hóa đơn\r\n");
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
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnThem.png"));
		btnThem.setBackground(new Color(167, 62, 20));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(55, 20, 156, 45);
		panel.add(btnThem);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnLuu.png"));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLuu.setBackground(new Color(167, 62, 20));
		btnLuu.setBounds(278, 20, 156, 45);
		panel.add(btnLuu);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnSua.png"));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBackground(new Color(167, 62, 20));
		btnSua.setBounds(498, 20, 156, 45);
		panel.add(btnSua);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnXoa.png"));
		btnXa.setForeground(Color.WHITE);
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXa.setBackground(new Color(167, 62, 20));
		btnXa.setBounds(716, 20, 156, 45);
		panel.add(btnXa);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnprint.png"));
		btnHy.setForeground(Color.WHITE);
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHy.setBackground(new Color(167, 62, 20));
		btnHy.setBounds(961, 20, 156, 45);
		panel.add(btnHy);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnExit.png"));
		btnThot.setForeground(Color.WHITE);
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThot.setBackground(new Color(167, 62, 20));
		btnThot.setBounds(1226, 20, 156, 45);
		panel.add(btnThot);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setBounds(55, 147, 160, 40);
		lblTenKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblTenKhachHang);
		
		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
		lblMaHoaDon.setBounds(55, 27, 160, 40);
		lblMaHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaHoaDon);
		
		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setBounds(55, 86, 160, 40);
		lblMaKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaKhachHang);
		
		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgayLap.setBounds(588, 27, 160, 40);
		pnlBackGround.add(lblNgayLap);
		
		JLabel lblTongHoaDon = new JLabel("Tổng hóa đơn:");
		lblTongHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTongHoaDon.setBounds(588, 86, 160, 40);
		pnlBackGround.add(lblTongHoaDon);
		
		JLabel lblChietKhau = new JLabel("Chiết khấu(%):");
		lblChietKhau.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblChietKhau.setBounds(588, 147, 160, 40);
		pnlBackGround.add(lblChietKhau);
		
		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setBounds(235, 31, 317, 43);
		pnlBackGround.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(235, 90, 317, 43);
		pnlBackGround.add(txtMaKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(235, 151, 317, 43);
		pnlBackGround.add(txtTenKhachHang);
		
		txtChietKhau = new JTextField();
		txtChietKhau.setColumns(10);
		txtChietKhau.setBounds(740, 151, 259, 43);
		pnlBackGround.add(txtChietKhau);
		
		txtTongHoaDon = new JTextField();
		txtTongHoaDon.setColumns(10);
		txtTongHoaDon.setBounds(740, 90, 259, 43);
		pnlBackGround.add(txtTongHoaDon);
		
		JDateChooser txtNgayLap = new JDateChooser();
		txtNgayLap.setBounds(740, 27, 259, 40);
		pnlBackGround.add(txtNgayLap);
		
		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("D:\\Download\\logoMTP 1.png"));
		logoMTP.setBounds(1009, 10, 403, 214);
		pnlBackGround.add(logoMTP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 348, 1426, 292);
		pnlBackGround.add(scrollPane);
		
		tableHoaDon = new JTable();
		tableHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y l\u1EADp", "T\u1ED5ng h\u00F3a \u0111\u01A1n", "Chi\u1EBFt kh\u1EA5u"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableHoaDon.getColumnModel().getColumn(2).setResizable(false);
		JTableHeader header = tableHoaDon.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPane.setViewportView(tableHoaDon);
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
	}
}
