package view;

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

public class pageQuanLiKhachHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaHang;
	private JTextField txtTenHang;
	private JTextField txtMoTa;
	private JTextField txtDonGia;
	private JTextField txtNhaSX;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pageQuanLiKhachHang frame = new pageQuanLiKhachHang();
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
	public pageQuanLiKhachHang() {
		setTitle("Quản lí khách hàng\r\n");
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
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnThem.png"));
		btnNewButton.setBackground(new Color(167, 62, 20));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(55, 20, 156, 45);
		panel.add(btnNewButton);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnLuu.png"));
		btnLu.setForeground(Color.WHITE);
		btnLu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLu.setBackground(new Color(167, 62, 20));
		btnLu.setBounds(278, 20, 156, 45);
		panel.add(btnLu);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnSua.png"));
		btnSa.setForeground(Color.WHITE);
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSa.setBackground(new Color(167, 62, 20));
		btnSa.setBounds(498, 20, 156, 45);
		panel.add(btnSa);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnXoa.png"));
		btnXa.setForeground(Color.WHITE);
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXa.setBackground(new Color(167, 62, 20));
		btnXa.setBounds(716, 20, 156, 45);
		panel.add(btnXa);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnCancel.png"));
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
		lblDonGia.setBounds(588, 27, 160, 40);
		pnlBackGround.add(lblDonGia);
		
		JLabel lblNhaSX = new JLabel("Nhà sản xuất:");
		lblNhaSX.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNhaSX.setBounds(588, 86, 160, 40);
		pnlBackGround.add(lblNhaSX);
		
		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySX.setBounds(588, 147, 160, 40);
		pnlBackGround.add(lblNgaySX);
		
		txtMaHang = new JTextField();
		txtMaHang.setBounds(155, 27, 317, 43);
		pnlBackGround.add(txtMaHang);
		txtMaHang.setColumns(10);
		
		txtTenHang = new JTextField();
		txtTenHang.setColumns(10);
		txtTenHang.setBounds(155, 83, 317, 43);
		pnlBackGround.add(txtTenHang);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(155, 147, 317, 43);
		pnlBackGround.add(txtMoTa);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(740, 31, 259, 43);
		pnlBackGround.add(txtDonGia);
		
		txtNhaSX = new JTextField();
		txtNhaSX.setColumns(10);
		txtNhaSX.setBounds(740, 90, 259, 43);
		pnlBackGround.add(txtNhaSX);
		
		JDateChooser txtNgaySX = new JDateChooser();
		txtNgaySX.setBounds(740, 157, 259, 40);
		pnlBackGround.add(txtNgaySX);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Download\\logoMTP 1.png"));
		lblNewLabel.setBounds(1009, 10, 403, 214);
		pnlBackGround.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 348, 1426, 292);
		pnlBackGround.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
				"M\u00E3 h\u00E0ng", "T\u00EAn h\u00E0ng", "M\u00F4 t\u1EA3", "\u0110\u01A1n gi\u00E1 ", "Nh\u00E0 s\u1EA3n xu\u1EA5t", "Ng\u00E0y s\u1EA3n xu\u1EA5t"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Double.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("New label");
		label.setBounds(110, 728, 91, 33);
		pnlBackGround.add(label);
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
	}
}
