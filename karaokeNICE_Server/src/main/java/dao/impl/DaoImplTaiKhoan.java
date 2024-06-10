package dao.impl;

import dao.DaoTaiKhoan;
import entity.TaiKhoan;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DaoImplTaiKhoan extends UnicastRemoteObject implements DaoTaiKhoan {
    @Serial
    private static final long serialVersionUID = 3298355178780516530L;
    //    DaoNhanVien dao_NhanVien = null;
    private EntityManager em = null;
    public DaoImplTaiKhoan() throws RemoteException {
//        DaoNhanVien dao_NhanVien = new DaoNhanVien();
        em = Init.Init().createEntityManager();
    }

    public ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException{
        return (ArrayList<TaiKhoan>) em.createQuery("select t from TaiKhoan t",TaiKhoan.class).getResultList();
    }

    public boolean addTaiKhoan(TaiKhoan taiKhoan) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(taiKhoan);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // lấy TT theo mã
    public TaiKhoan getTaiKhoanTheoMa(String maTim) throws RemoteException{
        return em.createQuery("select t from TaiKhoan t where t.id = :ma",TaiKhoan.class)
                .setParameter("ma",maTim).getSingleResult();
    }

    public boolean capNhapTaiKhoan(TaiKhoan taiKhoan) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            TaiKhoan tk = em.find(TaiKhoan.class,taiKhoan.getTenTaiKhoan());
            em.merge(tk);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhapMKTaiKhoan(TaiKhoan taiKhoan) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
//            TaiKhoan tk = em.find(TaiKhoan.class,taiKhoan.getTenTaiKhoan());
            em.merge(taiKhoan);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaTKTheoMa(String maXoa) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            TaiKhoan tk = em.find(TaiKhoan.class,maXoa);
            em.remove(tk);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<TaiKhoan> getDSTaiKhoanTheoTen(String ten) throws RemoteException{
        return (ArrayList<TaiKhoan>) em.createQuery("select t from TaiKhoan t where t.tenTaiKhoan=:ten",TaiKhoan.class)
                .setParameter("ten",ten).getResultList();
    }
    public ArrayList<TaiKhoan> getDSTaiKhoanTheoMa(String maChuTK) throws RemoteException{
        return (ArrayList<TaiKhoan>) em.createQuery("select t from TaiKhoan t where t.nhanVien.id= :ma",TaiKhoan.class)
                .setParameter("ma",maChuTK).getResultList();
    }
    public ArrayList<TaiKhoan> getTimKiemDSTK(String tenTK, String maChuTK) throws RemoteException{
        return (ArrayList<TaiKhoan>) em.createQuery("select t from TaiKhoan t where t.tenTaiKhoan like : ten and t.nhanVien.id like: ma",TaiKhoan.class)
                .setParameter("ten","%"+tenTK +"%")
                .setParameter("ma","%"+maChuTK+"%").getResultList();
    }

}

