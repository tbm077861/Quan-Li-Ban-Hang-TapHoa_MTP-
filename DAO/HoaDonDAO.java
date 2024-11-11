
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import model.SanPham;

public class HoaDonDAO {

    public List<SanPham> getAllSanPham() {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM DanhSachSanPham";
        try( Connection connection = ConnectDB.getConnection("DB_QLBH");
        	PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String maHang = resultSet.getString("MaHang");
                String tenHang = resultSet.getString("TenHang");
                String moTa = resultSet.getString("MoTa");
                double donGia = resultSet.getDouble("DonGia");
                int soLuong = resultSet.getInt("SoLuong");
                String ngaySX = resultSet.getString("NgaySX");

                SanPham sanPham = new SanPham(maHang, tenHang, moTa, donGia, soLuong, ngaySX);
                sanPhamList.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
}
