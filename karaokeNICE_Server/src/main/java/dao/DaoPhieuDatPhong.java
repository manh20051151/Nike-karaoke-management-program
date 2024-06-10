package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.PhieuDatPhong;

public interface DaoPhieuDatPhong extends Remote {

	public ArrayList<PhieuDatPhong> getAllPhieu()throws RemoteException;
	public PhieuDatPhong getPhieuTheoMa(String maTim)throws RemoteException;
	public ArrayList<PhieuDatPhong> getTheoMaPhong_TrangThai(String maPhong, String trangThai)throws RemoteException;
	public ArrayList<PhieuDatPhong> getDSTheoTrangThai(String TrangThai)throws RemoteException;
	public ArrayList<PhieuDatPhong> getDSPhieuTheoMa(String MaPhieu, String soDT)throws RemoteException;
	public boolean nhanPhongCho(String maPhong)throws RemoteException;
	public boolean capNhatTrangThai(String maPhong)throws RemoteException;
	public ArrayList<ArrayList<String>> getTKNhanVienTheoPhieuDatPhong(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	
	public boolean themPhieuDat(PhieuDatPhong phieuMoi)throws RemoteException;
	public boolean xoaPhieuTheoMa(String maXoa)throws RemoteException;
	public boolean capNhapPhieu(PhieuDatPhong phieu)throws RemoteException;
}
