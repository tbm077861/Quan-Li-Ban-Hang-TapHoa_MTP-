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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

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
		
		JLabel lblMota = new JLabel("Tên khách hàng:");
		lblMota.setBounds(55, 147, 160, 40);
		lblMota.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMota);
		
		JLabel lblMaHang = new JLabel("Mã hóa đơn:");
		lblMaHang.setBounds(55, 27, 160, 40);
		lblMaHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaHang);
		
		JLabel lblTenHang = new JLabel("Mã khách hàng:");
		lblTenHang.setBounds(55, 86, 160, 40);
		lblTenHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblTenHang);
		
		JLabel lblDonGia = new JLabel("Ngày lập:");
		lblDonGia.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDonGia.setBounds(588, 27, 160, 40);
		pnlBackGround.add(lblDonGia);
		
		JLabel lblNhaSX = new JLabel("Tổng hóa đơn:");
		lblNhaSX.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNhaSX.setBounds(588, 86, 160, 40);
		pnlBackGround.add(lblNhaSX);
		
		JLabel lblNgaySX = new JLabel("Chiết khấu(%):");
		lblNgaySX.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySX.setBounds(588, 147, 160, 40);
		pnlBackGround.add(lblNgaySX);
		
		textField = new JTextField();
		textField.setBounds(235, 31, 317, 43);
		pnlBackGround.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(235, 90, 317, 43);
		pnlBackGround.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(235, 151, 317, 43);
		pnlBackGround.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(740, 151, 259, 43);
		pnlBackGround.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(740, 90, 259, 43);
		pnlBackGround.add(textField_4);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(740, 27, 259, 40);
		pnlBackGround.add(dateChooser);
		
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
				"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Ngày lập", "Tổng hóa đơn", "Chiết khấu"
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
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
	}
}
