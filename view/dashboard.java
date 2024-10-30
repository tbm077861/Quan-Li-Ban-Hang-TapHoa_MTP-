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

public class dashboard extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard frame = new dashboard();
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
	public dashboard() {
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 630);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuKhachHang = new JMenu("Khách Hàng");
		menuKhachHang.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\image\\customer.png"));
		menuBar.add(menuKhachHang);
		
		JMenuItem menuKhachHang_DanhSach = new JMenuItem("Danh Sách Khách Hàng");
		menuKhachHang.add(menuKhachHang_DanhSach);
		
		JMenu menuHangHoa = new JMenu("Hàng Hóa");
		menuHangHoa.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\image\\goods.png"));
		menuBar.add(menuHangHoa);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Danh Sách Hàng Hóa");
		menuHangHoa.add(mntmNewMenuItem);
		
		JMenu menuHoaDon = new JMenu("Hóa đơn");
		menuHoaDon.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\image\\bill.png"));
		menuBar.add(menuHoaDon);
		
		JMenuItem menu = new JMenuItem("Danh sách hóa đơn");
		menuHoaDon.add(menu);
		
		JMenu menuNhanVien = new JMenu("Nhân Viên");
		menuNhanVien.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\image\\employee.png"));
		menuBar.add(menuNhanVien);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Danh sách nhân viên");
		menuNhanVien.add(mntmNewMenuItem_2);
		
		JLabel background_image = new JLabel("");
		background_image.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\image\\market.jpg"));
		getContentPane().add(background_image, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

}
