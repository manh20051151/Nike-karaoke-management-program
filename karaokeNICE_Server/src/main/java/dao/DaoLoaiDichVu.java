package dao;

import entity.LoaiDichVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoLoaiDichVu extends Remote {
    ArrayList<LoaiDichVu> getAllDichVu()throws RemoteException;
    LoaiDichVu getLoaiTheoMa(String maLoaiDV)throws RemoteException;
    LoaiDichVu getLoaiTheoTen(String tenLoaiDV)throws RemoteException;
    boolean addLoaiDichVu(LoaiDichVu loaiDV)throws RemoteException;
    boolean updateLoaiDichVu(LoaiDichVu loaiDV)throws RemoteException;
    boolean deleteLoaiDichVu(String maLoaiDV)throws RemoteException;

}
