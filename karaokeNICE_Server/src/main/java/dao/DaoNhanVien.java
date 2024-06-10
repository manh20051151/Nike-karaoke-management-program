package dao;

import entity.NhanVien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoNhanVien extends Remote {
    ArrayList<NhanVien> getALLNhanVien()throws RemoteException;
    ArrayList<NhanVien> getALLNhanVienKhongCoADMIN()throws RemoteException;
    NhanVien getNhanVienTheoMa(String ma)throws RemoteException;
    boolean addNhanVien(NhanVien nhanVien)throws RemoteException;
    boolean deleteNhanVienTheoMa(String maNV)throws RemoteException;
    boolean updateNhanVien(NhanVien nhanVien)throws RemoteException;
    boolean updateTTNhanVien(NhanVien nhanVien)throws RemoteException;
    ArrayList<NhanVien> getDSNhanVienTheoTenTuongDoi(String ten)throws RemoteException;
    ArrayList<NhanVien> getDsNhanVienTheoLoai(String ten)throws RemoteException;
    ArrayList<NhanVien> getDSNhanVienTheoSDT(String SDT)throws RemoteException;
    byte[] getHinh(String ma)throws RemoteException;
    ArrayList<NhanVien> getTimKiemDSNV(String tenLoaiNV, String maNV, String tenNV, String ngaySinh, String gt,
                                       String cmnd, String diaChi, String soDT)throws RemoteException;
}
