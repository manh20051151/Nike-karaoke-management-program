package dao.impl;

import dao.DaoLoaiDichVu;
import entity.LoaiDichVu;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DaoImplLoaiDichVu extends UnicastRemoteObject implements DaoLoaiDichVu {
    @Serial
    private static final long serialVersionUID = -1094347389231482875L;
    private EntityManager em;
    public DaoImplLoaiDichVu()throws RemoteException {
        em = Init.Init().createEntityManager();
    }

    public ArrayList<LoaiDichVu> getAllDichVu()throws RemoteException{
        return (ArrayList<LoaiDichVu>) em.createQuery("select l from LoaiDichVu l", LoaiDichVu.class).getResultList();
    }

    public LoaiDichVu getLoaiTheoMa(String maLoaiDV)throws RemoteException{
        return em.createQuery("select l from LoaiDichVu l where l.maLoaiDV = :maLoaiDV", LoaiDichVu.class)
                .setParameter("maLoaiDV", maLoaiDV)
                .getSingleResult();
    }

    public LoaiDichVu getLoaiTheoTen(String tenLoaiDV) throws RemoteException{
        return em.createQuery("select l from LoaiDichVu l where l.tenLoaiDV = :tenLoaiDV", LoaiDichVu.class)
                .setParameter("tenLoaiDV", tenLoaiDV)
                .getSingleResult();
    }

    public boolean addLoaiDichVu(LoaiDichVu loaiDV) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(loaiDV);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLoaiDichVu(LoaiDichVu loaiDV) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(loaiDV);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteLoaiDichVu(String maLoaiDV) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            LoaiDichVu loaiDV = em.find(LoaiDichVu.class, maLoaiDV);

            if (loaiDV != null) {
                em.remove(loaiDV);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }


}
