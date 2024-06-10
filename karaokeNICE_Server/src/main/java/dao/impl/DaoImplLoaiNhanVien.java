package dao.impl;

import dao.DaoLoaiNhanVien;
import entity.LoaiNhanVien;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DaoImplLoaiNhanVien extends UnicastRemoteObject implements DaoLoaiNhanVien {
    @Serial
    private static final long serialVersionUID = 6890317391499089063L;
    private EntityManager em;
    public DaoImplLoaiNhanVien() throws RemoteException {
        em = Init.Init().createEntityManager();
    }
    public ArrayList<LoaiNhanVien> getALLLoaiNhanVien() throws RemoteException{
        return (ArrayList<LoaiNhanVien>) em.createQuery("select l from LoaiNhanVien l",LoaiNhanVien.class).getResultList();
    }

    public LoaiNhanVien getLoaiNhanVienTheoMa(String ma) throws RemoteException{
        return em.find(LoaiNhanVien.class,ma);
    }

    public LoaiNhanVien getLoaiNhanVienTheoTen(String ten) throws RemoteException{
        return em.createQuery("select l from LoaiNhanVien l where l.tenLoai =:ten",LoaiNhanVien.class)
                .setParameter("ten",ten)
                .getSingleResult();
    }
    public boolean addLoaiNhanVien(LoaiNhanVien loaiNhanVien) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(loaiNhanVien);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteLoaiNVTheoMa(String maLNV) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            LoaiNhanVien nv = em.find(LoaiNhanVien.class,maLNV);
            em.remove(nv);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateLoaiNV(LoaiNhanVien loaiNhanVien) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
//            LoaiNhanVien nv = em.find(LoaiNhanVien.class,loaiNhanVien.getMaLoaiVN());
            em.merge(loaiNhanVien);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<LoaiNhanVien> getDSLoaiNVTheoTenLNV(String tenLoaiNV) throws RemoteException{
        return (ArrayList<LoaiNhanVien>) em.createQuery("select l from LoaiNhanVien l where l.tenLoai like :tenLoaiNV",LoaiNhanVien.class)
                .setParameter("tenLoaiNV","%"+tenLoaiNV+"%")
                .getResultList();
    }
    public ArrayList<LoaiNhanVien> getDSLoaiNVTheoHSL(String hsl) throws RemoteException{
        return (ArrayList<LoaiNhanVien>) em.createQuery("select l from LoaiNhanVien l where l.heSoLuong = :hsl",LoaiNhanVien.class)
                .setParameter("hsl",hsl).getResultList();
    }

    public ArrayList<LoaiNhanVien> gettimKiemDSLNV(String maLoaiNV, String tenLNV, String hsl) throws RemoteException{
        return (ArrayList<LoaiNhanVien>) em.createQuery("select l from LoaiNhanVien l where l.maLoaiVN like :ma or " +
                "l.tenLoai like :ten or l.heSoLuong =:hsl",LoaiNhanVien.class)
                .setParameter("ma","%"+maLoaiNV+"%")
                .setParameter("ten","%"+tenLNV+"%")
                .setParameter("hsl",hsl)
                .getResultList();
    }
}
