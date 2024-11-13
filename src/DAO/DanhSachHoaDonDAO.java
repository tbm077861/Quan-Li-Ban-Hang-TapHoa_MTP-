package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.SanPham;

public class DanhSachHoaDonDAO {
	public List<Object[]> loadDataToTable() {
        List<Object[]> data = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT maHD, MANV, maKhachHang, NgayLap FROM HoaDon")) {

            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                String ngayLap = sdf.format(rs.getDate("ngayLap"));
                Object[] row = { rs.getString("maHD"), rs.getString("MANV"), rs.getString("maKhachHang"), ngayLap };
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
        return data;
    }
	
	public void deleteHoaDon(String maHoaDon) {
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmtChiTietHD = conn.prepareStatement("DELETE FROM ChiTietHD WHERE MaHD = ?");
				PreparedStatement pstmtHoaDon = conn.prepareStatement("DELETE FROM HoaDon WHERE MaHD = ?")) {
			
			// Start transaction
			conn.setAutoCommit(false);
			
			// Delete from ChiTietHD
			pstmtChiTietHD.setString(1, maHoaDon);
			pstmtChiTietHD.executeUpdate();
			
			// Delete from HoaDon
			pstmtHoaDon.setString(1, maHoaDon);
			pstmtHoaDon.executeUpdate();
			
			// Commit transaction
			conn.commit();
			
			JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu: " + e.getMessage());
		}
	}
	
	public List<Object[]> searchHoaDon(String maHoaDon, String maNV, String maKH, String fromDate, String toDate) {
        List<Object[]> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT MaHD, MANV, maKhachHang, NgayLap FROM HoaDon WHERE 1 = 1");
        
        List<Object> params = new ArrayList<>();
        
		if (!maHoaDon.isEmpty()) {
			sql.append(" AND MaHD LIKE ?");
			params.add("%" + maHoaDon + "%");
		}
		if (!maNV.isEmpty()) {
			sql.append(" AND MANV LIKE ?");
			params.add("%" + maNV + "%");
		}
		if (!maKH.isEmpty()) {
			sql.append(" AND maKhachHang LIKE ?");
			params.add("%" + maKH + "%");
		}
		if (!fromDate.isEmpty() && !toDate.isEmpty()) {
			sql.append(" AND NgayLap BETWEEN ? AND ?");
			params.add(fromDate);
			params.add(toDate);
		}
		
		try (Connection conn = ConnectDB.getConnection("DB_QLBH");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Object[] row = { rs.getString("MaHD"), rs.getString("MANV"), rs.getString("maKhachHang"), rs.getDate("NgayLap")};
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm dữ liệu: " + e.getMessage());
		}
		return data;
	}
	
	public List<Object[]> getAllHoaDon() {
        List<Object[]> List = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try( Connection connection = ConnectDB.getConnection("DB_QLBH");
        	PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String maHD = resultSet.getString("MaHD");
                String maNV = resultSet.getString("MANV");
                String maKH = resultSet.getString("maKhachHang");
                String ngaySX = resultSet.getString("NgayLap");

                Object[] hoaDon = {maHD, maNV, maKH, ngaySX};
                List.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List;
    }
}
