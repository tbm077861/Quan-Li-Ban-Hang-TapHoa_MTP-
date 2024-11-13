package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPhamDAO {
    public boolean addSanPhamList(List<SanPham> sanPhamList) {
        String sql = "INSERT INTO DanhSachSanPham (MaHang, TenHang, MoTa, DonGia, SoLuong, NgaySanXuat) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (SanPham sp : sanPhamList) {
                pstmt.setString(1, sp.getMaHang());
                pstmt.setString(2, sp.getTenHang());
                pstmt.setString(3, sp.getMoTa());
                pstmt.setDouble(4, sp.getDonGia());
                pstmt.setInt(5, sp.getSoLuong());
                pstmt.setString(6, sp.getNgaySX());
                pstmt.addBatch();
            }
            int[] affectedRows = pstmt.executeBatch();
            return affectedRows.length == sanPhamList.size();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<SanPham> getAllSanPham() {
    	List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM DanhSachSanPham ORDER BY MaHang";

        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String maHang = rs.getString("MaHang");
                String tenHang = rs.getString("TenHang");
                String moTa = rs.getString("MoTa");
                double donGia = rs.getDouble("DonGia");
                int soLuong = rs.getInt("SoLuong");
                String ngaySX = rs.getString("NgaySanXuat");

                SanPham sp = new SanPham(maHang, tenHang, moTa, donGia, soLuong, ngaySX);
                sanPhamList.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
 
    public String getNextMaHang() {
        String sql = "SELECT TOP 1 MaHang FROM DanhSachSanPham ORDER BY MaHang DESC";
        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String lastMaHang = rs.getString("MaHang");
                int nextMaHang = Integer.parseInt(lastMaHang.substring(2)) + 1;
                return String.format("SP%d", nextMaHang); // Format to 4 digits
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "SP1"; // Default value if no records found
    }
    
    public List<SanPham> searchSanPham(String tenSanPham) {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM DanhSachSanPham WHERE TenHang LIKE ?";

        try (Connection conn = ConnectDB.getConnection("DB_QLBH");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + tenSanPham + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	String maHang = rs.getString("MaHang");
                String tenHang = rs.getString("TenHang");
                String moTa = rs.getString("MoTa");
                double donGia = rs.getDouble("DonGia");
                int soLuong = rs.getInt("SoLuong");
                String ngaySX = rs.getString("NgaySanXuat");

                SanPham sanPham = new SanPham(maHang, tenHang, moTa, donGia, soLuong, ngaySX);
                sanPhamList.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
}