package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTrangChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private FrameQuanLyKhachHang frameQLKH;
	private FrameQuanLySanPham frameQLSP;
	private FrameQuanLyHoaDon frameQLHD;
	private FrameQuanLyNhanVien frameQLNV;
	public String maNV, chucVu;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTrangChu frame = new FrameTrangChu(maNV, chucVu);
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
	public FrameTrangChu(String maNV, String chucVu) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("image\\store.png"));
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 630);
		setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel background_image = new JLabel("");
		background_image.setIcon(new ImageIcon("image\\market.jpg"));
		getContentPane().add(background_image);
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setBackground(new Color(192, 192, 192));
		
		JMenu btnHeThong = new JMenu("Hệ thống");
		btnHeThong.setIcon(new ImageIcon("icon\\system.png"));
		menuBar.add(btnHeThong);
		
		JMenuItem btnTrangChu = new JMenuItem("Trang chủ");
		btnTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
		        getContentPane().add(background_image, BorderLayout.CENTER);
		        revalidate();
		        repaint();
			}
		});
		btnTrangChu.setIcon(new ImageIcon("icon\\homepage.png"));
		btnHeThong.add(btnTrangChu);
		
		JMenuItem btnChinhSua = new JMenuItem("Chỉnh sửa");
		btnChinhSua.setIcon(new ImageIcon("icon\\editprofile.png"));
		btnHeThong.add(btnChinhSua);
		
		JMenuItem btnDangXuat = new JMenuItem("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(FrameTrangChu.this, "Bạn có muốn thoát ứng dụng không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
		        if (a == JOptionPane.YES_OPTION) {
		        	dispose();
			        FrameDangNhap Framelogin = new FrameDangNhap();
			        Framelogin.setVisible(true); 
		        }
				
			}
		});
		btnDangXuat.setIcon(new ImageIcon("icon\\signout.png"));
		btnHeThong.add(btnDangXuat);
		
		JMenuItem btnThoat = new JMenuItem("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThoat.setIcon(new ImageIcon("icon\\exit.png"));
		btnHeThong.add(btnThoat);
		
		JMenu btnDanhMuc = new JMenu("Danh mục");
		btnDanhMuc.setIcon(new ImageIcon("icon\\catalog.png"));
		menuBar.add(btnDanhMuc);
		
		JMenuItem btnKhachHang = new JMenuItem("Khách hàng");
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameQLKH = new FrameQuanLyKhachHang();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameQLKH, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		
		btnKhachHang.setIcon(new ImageIcon("icon\\customer.png"));
		btnDanhMuc.add(btnKhachHang);
		
		JMenuItem btnNhanVien = new JMenuItem("Nhân viên");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameQLNV = new FrameQuanLyNhanVien();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameQLNV, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnNhanVien.setIcon(new ImageIcon("icon\\employee.png"));
		btnDanhMuc.add(btnNhanVien);
		
		JMenuItem btnSanPham = new JMenuItem("Sản phẩm");
		btnSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameQLSP = new FrameQuanLySanPham();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameQLSP, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnSanPham.setIcon(new ImageIcon("icon\\product.png"));
		btnDanhMuc.add(btnSanPham);
		
		JMenuItem btnHoaDon = new JMenuItem("Hóa đơn");
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameQLHD = new FrameQuanLyHoaDon();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameQLHD, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnHoaDon.setIcon(new ImageIcon("icon\\bill.png"));
		btnDanhMuc.add(btnHoaDon);
		
		JMenu menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon("icon\\static.png"));
		menuBar.add(menuThongKe);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Doanh thu");
		mntmNewMenuItem_6.setIcon(new ImageIcon("icon\\static_revenue.png"));
		menuThongKe.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Khách hàng");
		mntmNewMenuItem_7.setIcon(new ImageIcon("icon\\static_customer.png"));
		menuThongKe.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Sản phẩm");
		mntmNewMenuItem_8.setIcon(new ImageIcon("icon\\static_product.png"));
		menuThongKe.add(mntmNewMenuItem_8);
		
		JMenu menuThongTin = new JMenu("Thông tin");
		menuThongTin.setIcon(new ImageIcon("icon\\info.png"));
		menuBar.add(menuThongTin);
		
		JMenuItem btnThongTin = new JMenuItem("Thông tin phần mềm");
		btnThongTin.setIcon(new ImageIcon("icon\\info_phanmem.png"));
		menuThongTin.add(btnThongTin);
		
		JMenuItem btnDuongDan = new JMenuItem("Đường dẫn");
		btnDuongDan.setIcon(new ImageIcon("icon\\link.png"));
		menuThongTin.add(btnDuongDan);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		menuBar.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel(chucVu + " " + maNV);
		lblUsername.setIcon(new ImageIcon("icon\\user.png"));
		lblUsername.setBounds(1004, 0, 128, 39);
		panel.add(lblUsername);
		
		
		
		this.setVisible(true);
	}
}
