package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class FrameThongTin extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackGround;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameThongTin frame = new FrameThongTin();
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
	public FrameThongTin() {
		setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setBounds(-91, 0, 1622, 755);
		pnlBackGround.setBackground(new Color(255, 255, 255));
		pnlBackGround.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JPanel panel = new JPanel();
		pnlBackGround.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("F:\\iuh 22-26\\HK5\\Huong su kien Java\\BTTH\\BTL_QuanLyBanHang\\image\\logo_IUH.png"));
		lblNewLabel.setBounds(684, 175, 378, 217);
		pnlBackGround.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TRƯỜNG ĐẠI HỌC CÔNG NGHIỆP TP.HCM");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(540, 84, 639, 68);
		pnlBackGround.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MÔN: LẬP TRÌNH HƯỚNG SỰ KIỆN VỚI CÔNG NGHỆ JAVA");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(509, 402, 724, 41);
		pnlBackGround.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ĐỀ TÀI: QUẢN LÝ BÁN HÀNG TẠI CỬA HÀNG TIỆN LỢI");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_3.setBounds(553, 453, 626, 49);
		pnlBackGround.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("KHOA CÔNG NGHỆ THÔNG TIN");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(632, 143, 474, 68);
		pnlBackGround.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Sinh viên thực hiện : ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(576, 599, 189, 49);
		pnlBackGround.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Đỗ Thành Minh Phú  - 22701391");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(775, 649, 266, 32);
		pnlBackGround.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Trần Bình Minh          - 22678911");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5_1.setBounds(775, 607, 266, 32);
		pnlBackGround.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Nguyễn Nhật Trường - 22647201");
		lblNewLabel_5_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5_2.setBounds(775, 691, 266, 32);
		pnlBackGround.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6 = new JLabel("Giảng Viên Hướng dẫn :  Phạm Thanh Hùng");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(576, 557, 385, 32);
		pnlBackGround.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Nhóm thực hiện :             Nhóm 6");
		lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6_1.setBounds(576, 512, 385, 32);
		pnlBackGround.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("BỘ CÔNG THƯƠNG");
		lblNewLabel_6_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_6_1_1.setBounds(697, 33, 302, 41);
		pnlBackGround.add(lblNewLabel_6_1_1);
	}
}
