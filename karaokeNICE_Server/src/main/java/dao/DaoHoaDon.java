package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.HoaDon;


public interface DaoHoaDon extends Remote {
	
	ArrayList<HoaDon> getALLHoaDon()throws RemoteException;
	HoaDon getHDTheoMaHD(String maHD)throws RemoteException;
	ArrayList<HoaDon> getDSTheoMaNV(String maNVTim)throws RemoteException;
	ArrayList<HoaDon> getDSTheoMaKhach(String maKhachTim)throws RemoteException;
	boolean themHoaDon(HoaDon hoaDonMoi)throws RemoteException;
	public boolean xoaHoaDonTheoMa(String maHD)throws RemoteException;
	boolean capNhapHonDon(HoaDon hoaDonSua)throws RemoteException;
	public ArrayList<HoaDon> getDSTheoMaPhong(String maPhongTim)throws RemoteException;
	public ArrayList<HoaDon> getDSTheoTenKhachHangTuongDoi(String tenKH)throws RemoteException;
	public ArrayList<HoaDon> getDSTheoSDTKhachHangTuongDoi(String sdt)throws RemoteException;
	public ArrayList<HoaDon> getDSTheoTenNhanVienTuongDoi(String tenNV)throws RemoteException;
	public ArrayList<HoaDon> getHoaDonDaThanhToan()throws RemoteException;
}
