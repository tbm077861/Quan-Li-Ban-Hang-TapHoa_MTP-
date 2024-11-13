package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;

public class ChinhSuaNhanVienDAO {

	public List<Object[]> getNhanVien(String maNV) {
		String sql = "SELECT * FROM TaiKhoanNV WHERE MANV = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maNV);
			ResultSet rs = pstmt.executeQuery();
			List<Object[]> nhanVienList = new ArrayList<>();
			while (rs.next()) {
				nhanVienList.add(new Object[] { rs.getString("MANV"), rs.getString("HoTen"), rs.getString("NgaySinh"),
						rs.getString("GioiTinh"), rs.getString("CCCD"), rs.getString("Email"), rs.getString("ChucVu"),
						rs.getString("MatKhau") });
			}
			return nhanVienList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateNhanVien(String maNV, String hoTen, String ngaySinh, String email, String gioiTinh, String cccd, String matKhau, String chucVu) {
		String sql = "UPDATE TaiKhoanNV SET HoTen = ?, NgaySinh = ?, Email = ?, GioiTinh = ?, CCCD = ?, MatKhau = ?, ChucVu = ? WHERE MANV = ?";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, hoTen);
			pstmt.setString(2, ngaySinh);
			pstmt.setString(3, email);
			pstmt.setString(4, gioiTinh);
			pstmt.setString(5, cccd);
			pstmt.setString(6, matKhau);
			pstmt.setString(7, chucVu);
			pstmt.setString(8, maNV);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
		}
	}

}
