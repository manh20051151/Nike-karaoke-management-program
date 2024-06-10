package dao.impl;

import dao.DaoLoaiPhong;
import entity.LoaiPhong;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DaoImplLoaiPhong extends UnicastRemoteObject implements DaoLoaiPhong {
    @Serial
    private static final long serialVersionUID = -846928803958252605L;
    private EntityManager em =null;
    public DaoImplLoaiPhong() throws RemoteException {
        em = Init.Init().createEntityManager();
    }

    public ArrayList<LoaiPhong> getALLLoaiPhong() throws RemoteException{
        return (ArrayList<LoaiPhong>) em.createQuery("select l from LoaiPhong l",LoaiPhong.class).getResultList();
    }

    public LoaiPhong getLoaiTheoMa(String ma) throws RemoteException{
        return em.find(LoaiPhong.class,ma);
    }

    public LoaiPhong getLoaiTheoTen(String ten) throws RemoteException{
        return (LoaiPhong) em.createQuery("select l from LoaiPhong l where l.tenLoaiPhong =:ten")
                .setParameter("ten",ten).getSingleResult();
    }

    public boolean addLoaiPhong(LoaiPhong loaiPhong) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(loaiPhong);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLoaiPhong(LoaiPhong loaiPhong) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
//            LoaiPhong nv = em.find(LoaiPhong.class,loaiPhong.getMaLoaiPhong());
            em.merge(loaiPhong);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLoaiPhong(String maLoaiPhong) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            LoaiPhong nv = em.find(LoaiPhong.class,maLoaiPhong);
            em.remove(nv);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
