package dao;

import entity.LoaiNhanVien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoLoaiNhanVien extends Remote {
    ArrayList<LoaiNhanVien> getALLLoaiNhanVien()throws RemoteException;
    LoaiNhanVien getLoaiNhanVienTheoMa(String ma)throws RemoteException;
    LoaiNhanVien getLoaiNhanVienTheoTen(String ten)throws RemoteException;
    boolean addLoaiNhanVien(LoaiNhanVien loaiNhanVien)throws RemoteException;
    boolean deleteLoaiNVTheoMa(String maLNV)throws RemoteException;
    boolean updateLoaiNV(LoaiNhanVien loaiNhanVien)throws RemoteException;
    ArrayList<LoaiNhanVien> getDSLoaiNVTheoTenLNV(String tenLoaiNV)throws RemoteException;
    ArrayList<LoaiNhanVien> getDSLoaiNVTheoHSL(String hsl)throws RemoteException;
    ArrayList<LoaiNhanVien> gettimKiemDSLNV(String maLoaiNV, String tenLNV, String hsl)throws RemoteException;
}
