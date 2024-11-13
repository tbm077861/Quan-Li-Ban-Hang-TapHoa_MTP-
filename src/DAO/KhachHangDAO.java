package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import connectDB.*;

public class KhachHangDAO {

	public List<Object[]> loadDataToTable() {
		List<Object[]> data = new ArrayList<>();
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement stmt = conn.prepareStatement(
						"SELECT maKhachHang, tenKhachHang, diaChi, dienThoai, email, ngaySinh FROM KhachHang")) {

			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				String ngaySinh = sdf.format(rs.getDate("ngaySinh"));
				Object[] row = { rs.getString("maKhachHang"), rs.getString("tenKhachHang"), rs.getString("diaChi"),
						rs.getString("dienThoai"), rs.getString("email"), ngaySinh };
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage());
		}
		return data;
	}

	public Map<String, String> getKhachHangData() {
		Map<String, String> khachHangData = new HashMap<>();
		String sql = "SELECT maKhachHang, tenKhachHang FROM KhachHang";
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				khachHangData.put(rs.getString("maKhachHang"), rs.getString("tenKhachHang"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachHangData;
	}

	public boolean isDuplicateDienThoai(String dienThoai, String maKH) {
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE dienThoai = ?";
		if (maKH != null) {
			sql += " AND maKhachHang != ?";
		}
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dienThoai);
			if (maKH != null) {
				pstmt.setString(2, maKH);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra điện thoại: " + e.getMessage());
		}
		return false;
	}

	public boolean isDuplicateEmail(String email, String maKH) {
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE email = ?";
		if (maKH != null) {
			sql += " AND maKhachHang != ?";
		}
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			if (maKH != null) {
				pstmt.setString(2, maKH);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra Email: " + e.getMessage());
		}
		return false;
	}

	public void saveKhachHang(boolean isEditing, String maKH, String tenKH, String diaChi, String dienThoai,
			String email, String ngaySinh) {
		try (Connection conn = ConnectDB.getConnection("DB_QLBH")) {
			String sql;
			if (isEditing) {
				sql = "UPDATE KhachHang SET tenKhachHang=?, diaChi=?, dienThoai=?, email=?, ngaySinh=? WHERE maKhachHang=?";
			} else {
				sql = "INSERT INTO KhachHang (maKhachHang, tenKhachHang, diaChi, dienThoai, email, ngaySinh) VALUES (?, ?, ?, ?, ?, ?)";
			}

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				if (isEditing) {
					pstmt.setString(1, tenKH);
					pstmt.setString(2, diaChi);
					pstmt.setString(3, dienThoai);
					pstmt.setString(4, email);
					pstmt.setString(5, ngaySinh);
					pstmt.setString(6, maKH);
				} else {
					pstmt.setString(1, maKH);
					pstmt.setString(2, tenKH);
					pstmt.setString(3, diaChi);
					pstmt.setString(4, dienThoai);
					pstmt.setString(5, email);
					pstmt.setString(6, ngaySinh);
				}

				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, isEditing ? "Cập nhật thành công!" : "Thêm mới thành công!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
		}
	}

	public void deleteKhachHang(String maKH) {
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM KhachHang WHERE maKhachHang = ?")) {

			pstmt.setString(1, maKH);
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Xóa thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa: " + e.getMessage());
		}
	}

	public List<Object[]> searchKhachHang(String maKH, String tenKH, String dienThoai, String diaChi) {
		List<Object[]> data = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"SELECT maKhachHang, tenKhachHang, diaChi, dienThoai, email, ngaySinh FROM KhachHang WHERE 1=1");
		List<Object> params = new ArrayList<>();

		if (!maKH.isEmpty()) {
			sql.append(" AND maKhachHang LIKE ?");
			params.add("%" + maKH + "%");
		}
		if (!tenKH.isEmpty()) {
			sql.append(" AND tenKhachHang LIKE ?");
			params.add("%" + tenKH + "%");
		}
		if (!dienThoai.isEmpty()) {
			sql.append(" AND dienThoai LIKE ?");
			params.add("%" + dienThoai + "%");
		}
		if (!diaChi.isEmpty()) {
			sql.append(" AND diaChi LIKE ?");
			params.add("%" + diaChi + "%");
		}

		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Object[] row = { rs.getString("maKhachHang"), rs.getString("tenKhachHang"), rs.getString("diaChi"),
						rs.getString("dienThoai"), rs.getString("email"), rs.getString("ngaySinh") };
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + e.getMessage());
		}
		return data;
	}

	public List<Object[]> getAllKhachHang() {
		List<Object[]> List = new ArrayList<>();
		String sql = "SELECT * FROM KhachHang";
		try (Connection connection = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String maKH = resultSet.getString("maKhachHang");
				String tenKH = resultSet.getString("tenKhachHang");
				String diaChi = resultSet.getString("diaChi");
				String dienThoai = resultSet.getString("dienThoai");
				String email = resultSet.getString("email");
				String ngaySinh = resultSet.getString("ngaySinh");

				Object[] nv = { maKH, tenKH, diaChi, dienThoai, email, ngaySinh };
				List.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return List;
	}
}
