package dao;

import entity.LoaiKhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface DaoLoaiKhachHang extends Remote {
	public ArrayList<LoaiKhachHang> getALLLoaiKhachHang()throws RemoteException;
	public LoaiKhachHang getLoaiKhachHangTheoMa(String ma)throws RemoteException;
	public LoaiKhachHang getLoaiKhachHangTheoTen(String ten)throws RemoteException;
	public ArrayList<LoaiKhachHang> getDSLoaiKHTheoMa(String maLoaiKH)throws RemoteException;
	

	
	public boolean addLoaiKhachHang(LoaiKhachHang loaiKhachHang)throws RemoteException;
	public boolean deleteLoaiKHTheoMa(String maLKH)throws RemoteException;
	public boolean updateLoaiKH(LoaiKhachHang loaiKhachHang)throws RemoteException;
	public ArrayList<LoaiKhachHang> getTimKiemDSLKH(String maLKH ,String tenLoaiKH)throws RemoteException ;
}
