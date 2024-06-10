package dao;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoTaiKhoan extends Remote {
    ArrayList<TaiKhoan> getAllTaiKhoan()throws RemoteException;
    boolean addTaiKhoan(TaiKhoan taiKhoan)throws RemoteException;
    TaiKhoan getTaiKhoanTheoMa(String maTim)throws RemoteException;
    boolean capNhapTaiKhoan(TaiKhoan taiKhoan)throws RemoteException;
    boolean capNhapMKTaiKhoan(TaiKhoan taiKhoan)throws RemoteException;
    boolean xoaTKTheoMa(String maXoa)throws RemoteException;
    ArrayList<TaiKhoan> getDSTaiKhoanTheoTen(String ten)throws RemoteException;
    ArrayList<TaiKhoan> getDSTaiKhoanTheoMa(String maChuTK)throws RemoteException;
    ArrayList<TaiKhoan> getTimKiemDSTK(String tenTK, String maChuTK)throws RemoteException;
}
