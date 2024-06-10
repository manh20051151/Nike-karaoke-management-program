package dao;

import entity.ChiTietHoaDon_DichVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DaoChiTietHoaDon_DichVu extends Remote {
    List<ChiTietHoaDon_DichVu> getAllDanhSach() throws RemoteException;
    List<ChiTietHoaDon_DichVu> getDSTheoMaPhong(String maPhongTim)throws RemoteException;
    List<ChiTietHoaDon_DichVu> getDSTheoMaDV(String maHD)throws RemoteException;
    List<ChiTietHoaDon_DichVu> getDSTheoMaHD(String maHD)throws RemoteException;
    ChiTietHoaDon_DichVu getCTHD_DV(String maDV, String maHD, String maPhong)throws RemoteException;
    boolean themCTHD_DVMoi(ChiTietHoaDon_DichVu cthd_dv)throws RemoteException;
    boolean capNhap(ChiTietHoaDon_DichVu cTHD_DV)throws RemoteException;
    boolean xoaCTHD_DV(ChiTietHoaDon_DichVu cTHD_DV)throws RemoteException;
    boolean thayDoiHoaDon(String maHDMoi, List<String> chuoiMaHDCu)throws RemoteException;




}
