package entity;

public class KhachHang {
	private String maKhachHang, tenKhachHang, diaChi, dienThoai, email, ngaySinh;
	
	public KhachHang() {
	}
	
	public KhachHang(String maKhachHang, String tenKhachHang, String diaChi, String dienThoai, String email,
			String ngaySinh) {
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
		this.ngaySinh = ngaySinh;
	}

	public KhachHang(String tenKhachHang, String diaChi, String dienThoai, String email, String ngaySinh) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
		this.ngaySinh = ngaySinh;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", diaChi=" + diaChi
				+ ", dienThoai=" + dienThoai + ", email=" + email + ", ngaySinh=" + ngaySinh + "]";
	}
	
	
	
	
}
