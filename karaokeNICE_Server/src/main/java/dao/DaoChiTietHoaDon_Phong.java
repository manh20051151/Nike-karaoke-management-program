package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.ChiTietHoaDon_Phong;


public interface DaoChiTietHoaDon_Phong extends Remote {
	
	public LocalDateTime chuyenDateTimeSangLocal(String chuoiSQL)throws RemoteException;
	public String chuyenLocalSangDateTime(LocalDateTime chuoiJava)throws RemoteException;
	
	
	public ArrayList<ChiTietHoaDon_Phong> getALLDanhSach()throws RemoteException;
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaHD(String maHD)throws RemoteException;
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaPhong(String maPhong)throws RemoteException;
	public ChiTietHoaDon_Phong getCTHD_Phong(String maPhong, String maHD)throws RemoteException;
	
	public boolean themCTHD_PMoi(ChiTietHoaDon_Phong cTHD_P)throws RemoteException;
	public boolean xoaPhongTheoMa(String maPhongXoa, String maHD)throws RemoteException;
	public boolean capNhap(ChiTietHoaDon_Phong cTHD_P)throws RemoteException;
	public boolean thayDoiHoaDon(LocalDateTime gioRa, String maHDMoi, ArrayList<String> chuoiMaHDCu) throws RemoteException;
}
