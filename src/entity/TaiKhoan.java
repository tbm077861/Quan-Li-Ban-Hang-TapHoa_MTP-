package entity;

import java.util.Objects;

public class TaiKhoan {
    private String manv;
    private String hoTen;
    private String ngaySinh;
    private String email;
    private String gioiTinh;
    private String cCCD;
    private String matKhau;
    private String chucVu;

    public TaiKhoan(String manv, String hoTen, String ngaySinh, String email, String gioiTinh, String cCCD, String matKhau, String chucVu) {
        this.manv = manv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.cCCD = cCCD;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }
    
    public TaiKhoan() {
		super();
		this.manv = "";
        this.hoTen = "";
        this.ngaySinh = "";
        this.email = "";
        this.gioiTinh = "";
        this.cCCD = "";
        this.matKhau = "";
        this.chucVu = "";
	}

	public String getManv() { return manv; }
    public void setManv(String manv) { this.manv = manv; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCCCD() { return cCCD; }
    public void setCCCD(String cCCD) { this.cCCD = cCCD; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

	@Override
	public int hashCode() {
		return Objects.hash(cCCD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(cCCD, other.cCCD);
	}
    
    
}
