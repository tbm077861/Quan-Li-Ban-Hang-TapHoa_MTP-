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

import connectDB.ConnectDB;

public class NhanVienDAO {

    public List<Object[]> loadDataToTable() {
        List<Object[]> data = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT MANV, HoTen, GioiTinh, Email, NgaySinh, CCCD, MatKhau, ChucVu FROM TaiKhoanNV")) {

            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                String ngaySinh = sdf.format(rs.getDate("NgaySinh"));
                Object[] row = { rs.getString("MANV"), rs.getString("HoTen"), rs.getString("GioiTinh"),
                        rs.getString("Email"), ngaySinh, rs.getString("CCCD"), rs.getString("MatKhau"),
                        rs.getString("ChucVu") };
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
        return data;
    }


    public Map<String, String> getNhanVienData() {
        Map<String, String> nhanVienData = new HashMap<>();
        String sql = "SELECT MANV, HoTen FROM TaiKhoanNV";
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                nhanVienData.put(rs.getString("MANV"), rs.getString("HoTen"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienData;
    }
	public boolean isDuplicateCCCD(String cccd, String maNV) {
	    String sql = "SELECT COUNT(*) FROM TaiKhoanNV WHERE CCCD = ?";
	    if (maNV != null) {
	        sql += " AND MANV != ?";
	    }
	    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, cccd);
	        if (maNV != null) {
	            pstmt.setString(2, maNV);
	        }
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra CCCD: " + e.getMessage());
	    }
	    return false;
	}
	

	public boolean isDuplicateEmail(String email, String maNV) {
	    String sql = "SELECT COUNT(*) FROM TaiKhoanNV WHERE Email = ?";
	    if (maNV != null) {
	        sql += " AND MANV != ?";
	    }
	    try (Connection conn = ConnectDB.getConnection("DB_QLBH");
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, email);
	        if (maNV != null) {
	            pstmt.setString(2, maNV);
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



    public void saveNhanVien(boolean isEditing, String maNV, String hoTen, String ngaySinhStr, String email, String gioiTinh, String cccd, String matKhau, String chucVu) {
        try (Connection conn = ConnectDB.getConnection("DB_QLBH")) {
            String sql;
            if (isEditing) {
                sql = "UPDATE TaiKhoanNV SET HoTen=?, NgaySinh=?, Email=?, GioiTinh=?, CCCD=?, MatKhau=?, ChucVu=? WHERE MANV=?";
            } else {
                sql = "INSERT INTO TaiKhoanNV (MANV, HoTen, NgaySinh, Email, GioiTinh, CCCD, MatKhau, ChucVu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (isEditing) {
                    pstmt.setString(1, hoTen);
                    pstmt.setString(2, ngaySinhStr);
                    pstmt.setString(3, email);
                    pstmt.setString(4, gioiTinh);
                    pstmt.setString(5, cccd);
                    pstmt.setString(6, matKhau);
                    pstmt.setString(7, chucVu);
                    pstmt.setString(8, maNV);
                } else {
                    pstmt.setString(1, maNV);
                    pstmt.setString(2, hoTen);
                    pstmt.setString(3, ngaySinhStr);
                    pstmt.setString(4, email);
                    pstmt.setString(5, gioiTinh);
                    pstmt.setString(6, cccd);
                    pstmt.setString(7, matKhau);
                    pstmt.setString(8, chucVu);
                }

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, isEditing ? "Cập nhật thành công!" : "Thêm mới thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }

    public void deleteNhanVien(String maNV) {
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM TaiKhoanNV WHERE MANV = ?")) {

            pstmt.setString(1, maNV);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Xóa thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa: " + e.getMessage());
        }
    }

    public List<Object[]> searchNhanVien(String maNV, String hoTen, String cccd, String gioiTinh) {
        List<Object[]> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT MANV, HoTen, GioiTinh, Email, NgaySinh, CCCD, MatKhau, ChucVu FROM TaiKhoanNV WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (!maNV.isEmpty()) {
            sql.append(" AND MANV LIKE ?");
            params.add("%" + maNV + "%");
        }
        if (!hoTen.isEmpty()) {
            sql.append(" AND HoTen LIKE ?");
            params.add("%" + hoTen + "%");
        }
        if (!cccd.isEmpty()) {
            sql.append(" AND CCCD LIKE ?");
            params.add("%" + cccd + "%");
        }
        if (!gioiTinh.isEmpty()) {
            sql.append(" AND GioiTinh = ?");
            params.add(gioiTinh);
        }

        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = { rs.getString("MANV"), rs.getString("HoTen"), rs.getString("GioiTinh"),
                        rs.getString("Email"), rs.getString("NgaySinh"), rs.getString("CCCD"),
                        rs.getString("MatKhau"), rs.getString("ChucVu") };
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return data;
    }
    
    public List<Object[]> getAllNhanVien() {
        List<Object[]> List = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoanNV";
        try( Connection connection = ConnectDB.getConnection("DB_QLBH");
        	PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String maNV = resultSet.getString("MANV");
                String hoTen = resultSet.getString("HoTen");
                String ngaySinh = resultSet.getString("NgaySinh");
                String email = resultSet.getString("Email");
                String gioiTinh = resultSet.getString("GioiTinh");
                String cccd = resultSet.getString("CCCD");
//                String matKhau = resultSet.getString("MatKhau");
                // không lấy mật khẩu
                String chucVu = resultSet.getString("ChucVu");

                Object[] nv = {maNV, hoTen, ngaySinh, email, gioiTinh, cccd, chucVu};
                List.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List;
    }
}
