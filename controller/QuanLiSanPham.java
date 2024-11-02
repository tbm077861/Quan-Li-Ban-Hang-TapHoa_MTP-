package controller;
import java.util.ArrayList;

import model.SanPham;
public class QuanLiSanPham {
	private ArrayList<SanPham> listSanPham;
	
	public QuanLiSanPham() {
		listSanPham = new ArrayList<SanPham>();
	}

	public Boolean addSanPham(SanPham sanPham) {
		if(listSanPham.contains(sanPham))
			return false;
		return listSanPham.add(sanPham);
	}
	
	public Boolean removeSanPham(String maSanPham) {
		SanPham sanPham = new SanPham(maSanPham);
		if (!listSanPham.contains(sanPham))
			return false;
		return listSanPham.remove(sanPham);
	}
	
	public SanPham findSanPham(String maSanPham) {
		SanPham sanPham = new SanPham(maSanPham);
		if (!listSanPham.contains(sanPham))
			return null;
		return listSanPham.get(listSanPham.indexOf(sanPham));
	}
	
	public SanPham getSanPham(int i) {
		if (i >= 0 && i < listSanPham.size())
			return listSanPham.get(i);
		return null;
	}

	public ArrayList<SanPham> getList() {
		return listSanPham;
	}

	public int tong() {
		return listSanPham.size();
	}
	public String toString() {
		String s = "";
		for (SanPham sp : listSanPham) {
			s += sp.toString() + "\n";
		}
		return s;
	}
	
}
