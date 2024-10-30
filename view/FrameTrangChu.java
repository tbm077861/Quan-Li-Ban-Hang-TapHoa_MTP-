package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameTrangChu extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTrangChu frame = new FrameTrangChu();
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
	public FrameTrangChu() {
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 630);
		setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuHeThong = new JMenu("Hệ thống");
		menuHeThong.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\system.png"));
		menuBar.add(menuHeThong);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Trang chủ");
		mntmNewMenuItem_5.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\homepage.png"));
		menuHeThong.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Chỉnh sửa");
		mntmNewMenuItem_1.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\editprofile.png"));
		menuHeThong.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Đăng xuất");
		mntmNewMenuItem_3.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\signout.png"));
		menuHeThong.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Thoát");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\exit.png"));
		menuHeThong.add(mntmNewMenuItem_4);
		
		JMenu menuKhachHang = new JMenu("Khách Hàng");
		menuKhachHang.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\customer.png"));
		menuBar.add(menuKhachHang);
		
		JMenuItem menuKhachHang_DanhSach = new JMenuItem("Danh Sách Khách Hàng");
		menuKhachHang_DanhSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLyKhachHang JFrameQLKH = new quanLyKhachHang();
				JFrameQLKH.show();
				dispose();
				
			}
		});
		menuKhachHang.add(menuKhachHang_DanhSach);
		
		JMenu menuSanPham = new JMenu("Sản phẩm");
		menuSanPham.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\product.png"));
		menuBar.add(menuSanPham);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Danh Sách Sản Phẩm");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLySanPham JFrameQLSP = new quanLySanPham();
				JFrameQLSP.show();
				dispose();
			}
		});
		menuSanPham.add(mntmNewMenuItem);
		
		JMenu menuHoaDon = new JMenu("Hóa đơn");
		menuHoaDon.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\bill.png"));
		menuBar.add(menuHoaDon);
		
		JMenuItem menu = new JMenuItem("Danh sách hóa đơn");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLyHoaDon JFrameQLHD = new quanLyHoaDon();
				JFrameQLHD.show();
				dispose();
			}
		});
		menuHoaDon.add(menu);
		
		JMenu menuNhanVien = new JMenu("Nhân Viên");
		menuNhanVien.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\employee.png"));
		menuBar.add(menuNhanVien);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Danh sách nhân viên");
		menuNhanVien.add(mntmNewMenuItem_2);
		
		JMenu menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\static.png"));
		menuBar.add(menuThongKe);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Doanh thu");
		mntmNewMenuItem_6.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\static_revenue.png"));
		menuThongKe.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Khách hàng");
		mntmNewMenuItem_7.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\static_customer.png"));
		menuThongKe.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Sản phẩm");
		mntmNewMenuItem_8.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\static_product.png"));
		menuThongKe.add(mntmNewMenuItem_8);
		
		JMenu menuThongTin = new JMenu("Thông tin");
		menuThongTin.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\info.png"));
		menuBar.add(menuThongTin);
		
		JLabel background_image = new JLabel("");
		background_image.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\market.jpg"));
		getContentPane().add(background_image, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

}
