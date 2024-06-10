package dao;

import entity.Phong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoPhong extends Remote {
    public ArrayList<Phong> getDanhSachPhong()throws RemoteException;
    Phong getPhongTheoMa(String maPhongTim)throws RemoteException;
    ArrayList<Phong> getDSPhongTheoTenTuongDoi(String ten)throws RemoteException;
    ArrayList<Phong> getDSTheoTTphong(String trangThaiTim)throws RemoteException;
    ArrayList<Phong> getDSTheoTTphong(String trangThai1, String trangThai2)throws RemoteException;
    ArrayList<Phong> getDSTheoLoaiPhong(String maLoaiPhong)throws RemoteException;
    ArrayList<Phong> getDSTheoLP_TTP(String maLoaiPhong, String trangThaiPhong)throws RemoteException;
    ArrayList<Phong> getDSTheoMaHD(String maHD)throws RemoteException;
    ArrayList<Phong> getDSCoPhieuDat()throws RemoteException;
    boolean addPhong(Phong phongMoi)throws RemoteException;
    boolean capNhapPhong(Phong phongMoi)throws RemoteException;
    boolean xoaPhongTheoMa(String maPhongXoa)throws RemoteException;
    ArrayList<Phong> search(String giaTriTimKiem)throws RemoteException;
}
