package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTrangChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private FrameQuanLyKhachHang frameQLKH;
	private FrameQuanLySanPham frameQLSP;
	private FrameQuanLyChiTietHoaDon frameCTHD;
	private FrameQuanLyNhanVien frameQLNV;
	private FrameDanhSachHoaDon frameDSHD;
	private FrameThongTin frameTT;
	private FrameChinhSuaThongTin frameCSTT;
	private FrameThongKeSanPham frameTKSP;
	private FrameThongKeKhachHang frameTKKH;
	private FrameThongKeDoanhThu frameTKDT;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon\\store.png"));
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 630);
		setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel btnThongKeSanPham = new JLabel("");
		btnThongKeSanPham.setIcon(new ImageIcon("image\\market.jpg"));
		getContentPane().add(btnThongKeSanPham);
		
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
		        getContentPane().add(btnThongKeSanPham, BorderLayout.CENTER);
		        revalidate();
		        repaint();
			}
		});
		btnTrangChu.setIcon(new ImageIcon("icon\\homepage.png"));
		btnHeThong.add(btnTrangChu);
		
		JMenuItem btnChinhSua = new JMenuItem("Chỉnh sửa");
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCSTT = new FrameChinhSuaThongTin(maNV);
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameCSTT, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnChinhSua.setIcon(new ImageIcon("icon\\editprofile.png"));
		btnHeThong.add(btnChinhSua);
		
		JMenuItem btnDangXuat = new JMenuItem("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(FrameTrangChu.this, "Bạn có muốn đăng xuất ứng dụng không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
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
				int a = JOptionPane.showConfirmDialog(FrameTrangChu.this, "Bạn có muốn thoát ứng dụng không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
		        if (a == JOptionPane.YES_OPTION) {
		        	dispose();
		        }
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
		
		JMenu btnHoaDon = new JMenu("Hóa đơn");
		btnHoaDon.setIcon(new ImageIcon("icon\\bill.png"));
		btnDanhMuc.add(btnHoaDon);
		
		JMenuItem btnDanhSachHoaDon = new JMenuItem("Danh sách hóa đơn");
		btnDanhSachHoaDon.setIcon(new ImageIcon("icon\\list.png"));
		btnDanhSachHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameDSHD = new FrameDanhSachHoaDon();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameDSHD, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		
		JMenuItem btnTaoHoaDon = new JMenuItem("Tạo hóa đơn");
		btnTaoHoaDon.setIcon(new ImageIcon("icon\\create.png"));
		btnTaoHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCTHD = new FrameQuanLyChiTietHoaDon();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameCTHD, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnHoaDon.add(btnTaoHoaDon);
		btnHoaDon.add(btnDanhSachHoaDon);
		
		JMenu btnThongKe = new JMenu("Thống kê");
		btnThongKe.setIcon(new ImageIcon("icon\\static.png"));
		menuBar.add(btnThongKe);
		
		JMenuItem btnThongKeDoanhThu = new JMenuItem("Doanh thu");
		btnThongKeDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTKDT = new FrameThongKeDoanhThu();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameTKDT, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnThongKeDoanhThu.setIcon(new ImageIcon("icon\\static_revenue.png"));
		btnThongKe.add(btnThongKeDoanhThu);
		
		JMenuItem btnThongKeKhachHang = new JMenuItem("Khách hàng");
		btnThongKeKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTKKH = new FrameThongKeKhachHang();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameTKKH, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnThongKeKhachHang.setIcon(new ImageIcon("icon\\static_customer.png"));
		btnThongKe.add(btnThongKeKhachHang);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Sản phẩm");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTKSP = new FrameThongKeSanPham();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameTKSP, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		mntmNewMenuItem_8.setIcon(new ImageIcon("icon\\static_product.png"));
		btnThongKe.add(mntmNewMenuItem_8);
		
		JMenu btnInfo = new JMenu("Thông tin");
		btnInfo.setIcon(new ImageIcon("icon\\info.png"));
		menuBar.add(btnInfo);
		
		JMenuItem btnThongTin = new JMenuItem("Thông tin phần mềm");
		btnThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTT = new FrameThongTin();
				getContentPane().removeAll();
				getContentPane().add(menuBar, BorderLayout.NORTH);
				getContentPane().add(frameTT, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		btnThongTin.setIcon(new ImageIcon("icon\\info_phanmem.png"));
		btnInfo.add(btnThongTin);
		
		JMenuItem btnDuongDan = new JMenuItem("Đường dẫn");
		btnDuongDan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(java.net.URI.create("https://github.com/tbm077861/Quan-Li-Ban-Hang-TapHoa_MTP-"));
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnDuongDan.setIcon(new ImageIcon("icon\\link.png"));
		btnInfo.add(btnDuongDan);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		menuBar.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel(chucVu + " " + maNV);
		lblUsername.setIcon(new ImageIcon("icon\\user.png"));
		lblUsername.setBounds(1004, 0, 128, 39);
		panel.add(lblUsername);
		
		if ("Nhân Viên".equals(chucVu)) {
	        btnNhanVien.setEnabled(false);
	    }
		
		this.setVisible(true);
	}
}
