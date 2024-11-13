package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import DAO.ChinhSuaNhanVienDAO;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrameChinhSuaThongTin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	private JTextField txtHoTen;
	private JTextField txtMaNhanVien;
	private JTextField txtEmail;
	private JTextField txtCCCD;
	private JTextField txtMatKhau;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxGioiTinh, comboBoxChucVu;
	private ChinhSuaNhanVienDAO chinhSuaDao = new ChinhSuaNhanVienDAO();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameChinhSuaThongTin frame = new FrameChinhSuaThongTin();
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
	public FrameChinhSuaThongTin(String maNV) {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 1542, 767);
		pnlBackGround.setBackground(new Color(254, 222, 192));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JLabel lbHoTen = new JLabel("Họ Tên");
		lbHoTen.setBackground(new Color(255, 128, 0));
		lbHoTen.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbHoTen.setBounds(95, 182, 194, 48);
		pnlBackGround.add(lbHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHoTen.setBounds(358, 184, 326, 48);
		pnlBackGround.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNgySinh.setBounds(95, 313, 194, 48);
		pnlBackGround.add(lblNgySinh);
		
		JLabel lblMNhnVin = new JLabel("Mã Nhân Viên");
		lblMNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblMNhnVin.setBounds(95, 59, 194, 48);
		pnlBackGround.add(lblMNhnVin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblEmail.setBounds(95, 423, 194, 48);
		pnlBackGround.add(lblEmail);
		
		comboBoxGioiTinh = new JComboBox();
		comboBoxGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		comboBoxGioiTinh.setBounds(1107, 61, 150, 48);
		pnlBackGround.add(comboBoxGioiTinh);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblGiiTnh.setBounds(871, 59, 194, 48);
		pnlBackGround.add(lblGiiTnh);
		
		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblCccd.setBounds(871, 182, 194, 48);
		pnlBackGround.add(lblCccd);
		
		JLabel lblMtKhu = new JLabel("Chức Vụ");
		lblMtKhu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblMtKhu.setBounds(871, 313, 194, 48);
		pnlBackGround.add(lblMtKhu);
		
		JLabel lblMtKhu_1 = new JLabel("Mật Khẩu");
		lblMtKhu_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblMtKhu_1.setBounds(871, 423, 194, 48);
		pnlBackGround.add(lblMtKhu_1);
		
		comboBoxChucVu = new JComboBox();
		comboBoxChucVu.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên"}));
		comboBoxChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBoxChucVu.setBounds(1107, 315, 150, 48);
		pnlBackGround.add(comboBoxChucVu);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(358, 59, 326, 48);
		pnlBackGround.add(txtMaNhanVien);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(358, 423, 326, 48);
		pnlBackGround.add(txtEmail);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(358, 313, 326, 48);
		pnlBackGround.add(dateChooser);
		
		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(1107, 184, 326, 48);
		pnlBackGround.add(txtCCCD);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(1107, 425, 326, 48);
		pnlBackGround.add(txtMatKhau);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(e -> btnSuaAction());
		btnSua.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\icon\\btnSua.png"));
		btnSua.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSua.setBounds(510, 567, 174, 48);
		pnlBackGround.add(btnSua);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(e -> btnLuuAction());
		btnLuu.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\icon\\btnLuu.png"));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLuu.setBounds(871, 567, 174, 48);
		pnlBackGround.add(btnLuu);
		
		txtMaNhanVien.setEditable(false);
		loadDataToTextField(maNV);
		LockTextField();
	}
	
	// Load dữ liệu vào các textfield
	private void loadDataToTextField(String maNV) {
        List<Object[]> nhanVienList = chinhSuaDao.getNhanVien(maNV);
        if (nhanVienList != null && !nhanVienList.isEmpty()) {
            Object[] nhanVien = nhanVienList.get(0);
            txtMaNhanVien.setText((String) nhanVien[0]);
            txtHoTen.setText((String) nhanVien[1]);
            dateChooser.setDate(java.sql.Date.valueOf((String) nhanVien[2]));
            comboBoxGioiTinh.setSelectedItem((String) nhanVien[3]);
            txtCCCD.setText((String) nhanVien[4]);
            txtEmail.setText((String) nhanVien[5]);
            comboBoxChucVu.setSelectedItem((String) nhanVien[6]);
            txtMatKhau.setText((String) nhanVien[7]);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên!");
        }
    }
	
	// Mở editable cho các textfield
	private void btnSuaAction() {
		txtHoTen.setEditable(true);
		txtEmail.setEditable(true);
		txtCCCD.setEditable(true);
		txtMatKhau.setEditable(true);
		dateChooser.setEnabled(true);
		comboBoxGioiTinh.setEnabled(true);
		comboBoxChucVu.setEnabled(true);
	}
	
	// Lưu thông tin đã chỉnh sửa
	private void btnLuuAction() {
		String maNV = txtMaNhanVien.getText();
		String hoTen = txtHoTen.getText();
		String gioiTinh = (String) comboBoxGioiTinh.getSelectedItem();
		String email = txtEmail.getText();
		String ngaySinh = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String cccd = txtCCCD.getText();
		String matKhau = txtMatKhau.getText();
		String chucVu = (String) comboBoxChucVu.getSelectedItem();
		chinhSuaDao.updateNhanVien(maNV, hoTen, ngaySinh, email, gioiTinh, cccd, matKhau, chucVu);
		loadDataToTextField(maNV);
		LockTextField();
	}
	
	// Khóa các textfield
	private void LockTextField() {
		txtHoTen.setEditable(false);
		txtEmail.setEditable(false);
		txtCCCD.setEditable(false);
		txtMatKhau.setEditable(false);
		dateChooser.setEnabled(false);
		comboBoxGioiTinh.setEnabled(false);
		comboBoxChucVu.setEnabled(false);
	}
}
