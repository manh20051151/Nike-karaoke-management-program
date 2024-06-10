package dao;

import entity.LoaiPhong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DaoLoaiPhong extends Remote {
    ArrayList<LoaiPhong> getALLLoaiPhong()throws RemoteException;
    LoaiPhong getLoaiTheoMa(String ma)throws RemoteException;
    LoaiPhong getLoaiTheoTen(String ten)throws RemoteException;
    boolean addLoaiPhong(LoaiPhong loaiPhong)throws RemoteException;
    boolean updateLoaiPhong(LoaiPhong loaiPhong)throws RemoteException;
    boolean deleteLoaiPhong(String maLoaiPhong)throws RemoteException;

}
