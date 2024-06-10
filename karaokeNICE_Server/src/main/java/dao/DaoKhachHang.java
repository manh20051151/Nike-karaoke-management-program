package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.KhachHang;
public interface DaoKhachHang extends Remote {
	
	public ArrayList<KhachHang> getALLKhachHang()throws RemoteException;
	public KhachHang getKhachHangTheoMa(String ma)throws RemoteException;
	
	public ArrayList<KhachHang> getDSKhachHangTheoSDT(String SDT)throws RemoteException;
	public KhachHang getKhachHangTheoSoDT(String soDT)throws RemoteException;
	
	public ArrayList<KhachHang> getDSKhachHangTheoTenTuongDoi(String tenKH)throws RemoteException;
	public ArrayList<KhachHang> getDSKhachHangTheoLoai(String ten)throws RemoteException;

	ArrayList<KhachHang> getTimKiemDSKH(String maKH, String tenLoaiKH, String tenKH, String sdt, Integer Diem, String ghiChu) throws RemoteException;
	public boolean addKhachHang(KhachHang khachHang)throws RemoteException;
	public boolean deleteKhachHangTheoMa(String maKH)throws RemoteException;
	public boolean updateKhachHang(KhachHang khachHang)throws RemoteException;
}
