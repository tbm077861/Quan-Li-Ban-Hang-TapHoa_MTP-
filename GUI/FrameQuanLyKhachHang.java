package GUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;

public class FrameQuanLyKhachHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtDienThoai;
	private JTextField txtEmail;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameQuanLyKhachHang frame = new FrameQuanLyKhachHang();
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
	public FrameQuanLyKhachHang() {
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
		
		JButton btnThot = new JButton("In");
		btnThot.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnprint.png"));
		btnThot.setForeground(Color.WHITE);
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThot.setBackground(new Color(167, 62, 20));
		btnThot.setBounds(1226, 20, 156, 45);
		panel.add(btnThot);
		
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
		txtMaKhachHang.setBounds(225, 31, 317, 43);
		pnlBackGround.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(225, 86, 317, 43);
		pnlBackGround.add(txtTenKhachHang);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(225, 151, 317, 43);
		pnlBackGround.add(txtDiaChi);
		
		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(740, 31, 259, 43);
		pnlBackGround.add(txtDienThoai);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(740, 90, 259, 43);
		pnlBackGround.add(txtEmail);
		
		JDateChooser txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(740, 157, 259, 40);
		pnlBackGround.add(txtNgaySinh);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Download\\logoMTP 1.png"));
		lblNewLabel.setBounds(1009, 10, 403, 214);
		pnlBackGround.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 348, 848, 292);
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
				{null, null, "", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, "", null},
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
				"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Email", "Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPane.setViewportView(table);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(new Color(255, 128, 64));
		pnlTacVu.setBounds(920, 332, 496, 465);
		pnlBackGround.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		pnlTacVu.setBorder(titledBorder);
		
		JLabel lblNewLabel_1 = new JLabel("Mã KH:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(30, 41, 71, 34);
		pnlTacVu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên KH:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(30, 85, 93, 34);
		pnlTacVu.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày sinh:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(30, 129, 117, 34);
		pnlTacVu.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(30, 173, 145, 34);
		pnlTacVu.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Địa chỉ:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(30, 217, 103, 34);
		pnlTacVu.add(lblNewLabel_1_4);
		
		textField = new JTextField();
		textField.setBounds(179, 41, 227, 31);
		pnlTacVu.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 85, 227, 31);
		pnlTacVu.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 176, 227, 31);
		pnlTacVu.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(179, 217, 227, 31);
		pnlTacVu.add(textField_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(179, 129, 227, 34);
		pnlTacVu.add(dateChooser);
		
		JButton btnNewButton_1 = new JButton("Tìm");
		btnNewButton_1.setBackground(new Color(255, 128, 128));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(90, 277, 117, 57);
		pnlTacVu.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Tải lại");
		btnNewButton_1_1.setBackground(new Color(255, 128, 128));
		btnNewButton_1_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(296, 277, 122, 57);
		pnlTacVu.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Lập hóa đơn");
		btnNewButton_1_2.setBackground(new Color(255, 255, 0));
		btnNewButton_1_2.setForeground(new Color(255, 0, 0));
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_2.setBounds(179, 346, 154, 64);
		pnlTacVu.add(btnNewButton_1_2);
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
	}
}
