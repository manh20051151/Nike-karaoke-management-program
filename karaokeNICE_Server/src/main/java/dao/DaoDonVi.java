package dao;

import entity.DonVi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DaoDonVi extends Remote {
    List<DonVi> getALLDonVi()throws RemoteException;
    DonVi getDonViTheoMa(String maDonVi)throws RemoteException;
    DonVi getDonViTheoTen(String tenDonVi)throws RemoteException;
    boolean addDonVi(DonVi donVi)throws RemoteException;
    boolean updateDonVi(DonVi donVi)throws RemoteException;
    boolean deleteDonVi(String maDonVi)throws RemoteException;
}
