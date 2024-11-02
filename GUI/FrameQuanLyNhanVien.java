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
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameQuanLyNhanVien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtCanCuoc;
	private JTextField txtEmailNhanVien;
	private JTable tableNhanVien;
	private JTextField txtMaNhanVienTim;
	private JTextField txtTenNhanVienTim;
	private JTextField txtCanCuocTim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameQuanLyNhanVien frame = new FrameQuanLyNhanVien();
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
	public FrameQuanLyNhanVien() {
		setTitle("Quản lí nhân viên\r\n");
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
		
		JButton btnXuat = new JButton("Xuất");
		btnXuat.setIcon(new ImageIcon("D:\\WorkSpaceHSK\\TapHoa_MTP\\src\\test\\resources\\icons\\btnprint.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXuat.setBackground(new Color(167, 62, 20));
		btnXuat.setBounds(1226, 20, 156, 45);
		panel.add(btnXuat);
		
		JLabel lblCCCD = new JLabel("Căn cước:");
		lblCCCD.setBounds(55, 147, 160, 40);
		lblCCCD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblCCCD);
		
		JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setBounds(55, 27, 160, 40);
		lblMaNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblMaNhanVien);
		
		JLabel lblHoTenNhanVien = new JLabel("Họ và tên:");
		lblHoTenNhanVien.setBounds(55, 86, 160, 40);
		lblHoTenNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlBackGround.add(lblHoTenNhanVien);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblGioiTinh.setBounds(588, 27, 160, 40);
		pnlBackGround.add(lblGioiTinh);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEmail.setBounds(588, 90, 160, 40);
		pnlBackGround.add(lblEmail);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgaySinh.setBounds(588, 154, 160, 40);
		pnlBackGround.add(lblNgaySinh);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(225, 31, 317, 43);
		pnlBackGround.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(225, 86, 317, 43);
		pnlBackGround.add(txtTenNhanVien);
		
		txtCanCuoc = new JTextField();
		txtCanCuoc.setColumns(10);
		txtCanCuoc.setBounds(225, 151, 317, 43);
		pnlBackGround.add(txtCanCuoc);
		
		txtEmailNhanVien = new JTextField();
		txtEmailNhanVien.setColumns(10);
		txtEmailNhanVien.setBounds(740, 90, 259, 43);
		pnlBackGround.add(txtEmailNhanVien);
		
		JDateChooser txtNgaySinhNhanVien = new JDateChooser();
		txtNgaySinhNhanVien.setBounds(740, 157, 259, 40);
		pnlBackGround.add(txtNgaySinhNhanVien);
		
		JLabel logoMTP = new JLabel("");
		logoMTP.setIcon(new ImageIcon("D:\\Download\\logoMTP 1.png"));
		logoMTP.setBounds(1009, 10, 403, 214);
		pnlBackGround.add(logoMTP);
		
		JScrollPane scrollPaneNhanVien = new JScrollPane();
		scrollPaneNhanVien.setBounds(0, 348, 844, 292);
		pnlBackGround.add(scrollPaneNhanVien);
		
		tableNhanVien = new JTable();
		tableNhanVien.setModel(new DefaultTableModel(
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
				"M\u00E3 nh\u00E2n vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "Gi\u1EDBi t\u00EDnh", "Email", "Ng\u00E0y sinh", "CCCD"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JTableHeader header = tableNhanVien.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
		scrollPaneNhanVien.setViewportView(tableNhanVien);
		
		JComboBox txtGioiTinhNhanVien = new JComboBox();
		txtGioiTinhNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtGioiTinhNhanVien.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		txtGioiTinhNhanVien.setBounds(740, 27, 148, 36);
		pnlBackGround.add(txtGioiTinhNhanVien);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(new Color(255, 128, 64));
		pnlTacVu.setBounds(897, 348, 515, 338);
		pnlBackGround.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Tác vụ");
		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 18));
		pnlTacVu.setBorder(titledBorder);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 50, 180, 34);
		pnlTacVu.add(lblNewLabel);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTnNhnVin.setBounds(10, 94, 180, 34);
		pnlTacVu.add(lblTnNhnVin);
		
		JLabel lblaCh = new JLabel("Căn cước:");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblaCh.setBounds(10, 138, 180, 34);
		pnlTacVu.add(lblaCh);
		
		JLabel lblGiiTnh = new JLabel("Giới tính:");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGiiTnh.setBounds(10, 182, 180, 34);
		pnlTacVu.add(lblGiiTnh);
		
		txtMaNhanVienTim = new JTextField();
		txtMaNhanVienTim.setBounds(168, 50, 198, 31);
		pnlTacVu.add(txtMaNhanVienTim);
		txtMaNhanVienTim.setColumns(10);
		
		txtTenNhanVienTim = new JTextField();
		txtTenNhanVienTim.setColumns(10);
		txtTenNhanVienTim.setBounds(168, 94, 198, 31);
		pnlTacVu.add(txtTenNhanVienTim);
		
		txtCanCuocTim = new JTextField();
		txtCanCuocTim.setColumns(10);
		txtCanCuocTim.setBounds(168, 138, 198, 31);
		pnlTacVu.add(txtCanCuocTim);
		
		JComboBox txtGioiTinhTim = new JComboBox();
		txtGioiTinhTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtGioiTinhTim.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		txtGioiTinhTim.setBounds(168, 182, 115, 34);
		pnlTacVu.add(txtGioiTinhTim);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setBackground(new Color(255, 128, 128));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(121, 254, 115, 40);
		pnlTacVu.add(btnTim);
		
		JButton btnNewButton_1_1 = new JButton("Tải lại");
		btnNewButton_1_1.setBackground(new Color(255, 128, 128));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(312, 254, 115, 40);
		pnlTacVu.add(btnNewButton_1_1);
//		 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		 gd.setFullScreenWindow(this);
	}
}
