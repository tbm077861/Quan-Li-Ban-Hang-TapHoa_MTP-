package entity;

import java.util.Objects;

public class SanPham {
	private String maHang;
	private String tenHang;
	private String moTa;
	private double donGia;
	private int soLuong;
	private String ngaySX;
	
	public SanPham(String maHang, String tenHang, String moTa, double donGia, int soLuong, String ngaySX) {
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.moTa = moTa;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.ngaySX = ngaySX;
	}

	public SanPham(String maHang) {
		this.maHang = maHang;
		this.tenHang = "";
		this.moTa = "";
		this.donGia = 0.0;
		this.soLuong = 0;
		this.ngaySX = "";
	}

	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getNgaySX() {
		return ngaySX;
	}

	public void setNgaySX(String ngaySX) {
		this.ngaySX = ngaySX;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maHang, other.maHang);
	}

	public String toString() {
        return "SanPham [maHang=" + maHang + ", tenHang=" + tenHang + ", moTa=" + moTa + ", donGia=" + donGia
                + ", nhaSX=" + soLuong + ", ngaySX=" + ngaySX + "]";
    }
	
}
