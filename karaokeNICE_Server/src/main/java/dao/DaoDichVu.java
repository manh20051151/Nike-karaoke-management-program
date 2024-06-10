package dao;

import entity.DichVu;
import entity.LoaiDichVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DaoDichVu extends Remote {
    List<DichVu> getAllDichVu()throws RemoteException;
    boolean addDichVu(DichVu dichVu)throws RemoteException;
    boolean updateDichVu(DichVu dichVu, boolean capNhatAnh)throws RemoteException;
    boolean deleteDichVuTheoMa(String maDV)throws RemoteException;
    DichVu getDichVuTheoMa(String ma)throws RemoteException;
    List<DichVu> getDSDichVuTheoTenTuongDoi(String ten)throws RemoteException;
    List<DichVu> getDSDichVuTheoLoai(LoaiDichVu loai)throws RemoteException;
    byte[] getHinhAnhTheoMaDichVu(String maDV)throws RemoteException;
    List<DichVu> search(String giaTriTimKiem)throws RemoteException;
}
