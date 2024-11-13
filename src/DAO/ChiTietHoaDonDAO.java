package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;

import entity.SanPham;

public class ChiTietHoaDonDAO {

	public List<Object[]> getChiTietHoaDon(String maHD) {
		List<Object[]> chiTietHoaDonDetails = new ArrayList<>();
		String sql = "SELECT c.MaHang, d.TenHang, c.SoLuongSanPham, c.DonGia " + "FROM ChiTietHD c "
				+ "JOIN DanhSachSanPham d ON c.MaHang = d.MaHang " + "WHERE c.MaHD = ?";

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maHD);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Object[] details = new Object[4];
					details[0] = rs.getString("MaHang");
					details[1] = rs.getString("TenHang");
					details[2] = rs.getInt("DonGia");
					details[3] = rs.getInt("SoLuongSanPham");
					chiTietHoaDonDetails.add(details);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chiTietHoaDonDetails;
	}

}
