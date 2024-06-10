package dao.impl;

import dao.DaoChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_DichVu;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DaoImplChiTietHoaDon_DichVu extends UnicastRemoteObject implements DaoChiTietHoaDon_DichVu {


    @Serial
    private static final long serialVersionUID = 63122776074488838L;
    private EntityManager em;
    public DaoImplChiTietHoaDon_DichVu() throws RemoteException {
        em = Init.Init().createEntityManager();
    }

    public List<ChiTietHoaDon_DichVu> getAllDanhSach() throws RemoteException{
        List<ChiTietHoaDon_DichVu> danhSach = new ArrayList<>();
        try {
            TypedQuery<ChiTietHoaDon_DichVu> query = em.createQuery("SELECT c FROM ChiTietHoaDon_DichVu c", ChiTietHoaDon_DichVu.class);
            danhSach = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public List<ChiTietHoaDon_DichVu> getDSTheoMaPhong(String maPhongTim) throws RemoteException {
        List<ChiTietHoaDon_DichVu> ds = new ArrayList<>();
        try {
            TypedQuery<ChiTietHoaDon_DichVu> query = em.createQuery("SELECT c FROM ChiTietHoaDon_DichVu c WHERE c.phong.maPhong = :maPhong", ChiTietHoaDon_DichVu.class);
            query.setParameter("maPhong", maPhongTim);
            ds = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<ChiTietHoaDon_DichVu> getDSTheoMaDV(String maHD) throws RemoteException{
        List<ChiTietHoaDon_DichVu> ds = new ArrayList<>();
        try {
            TypedQuery<ChiTietHoaDon_DichVu> query = em.createQuery("SELECT c FROM ChiTietHoaDon_DichVu c WHERE c.hoaDon.maHoaDon = :maHD", ChiTietHoaDon_DichVu.class);
            query.setParameter("maHD", maHD);
            ds = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<ChiTietHoaDon_DichVu> getDSTheoMaHD(String maHD) throws RemoteException{
        List<ChiTietHoaDon_DichVu> ds = new ArrayList<>();
        try {
            TypedQuery<ChiTietHoaDon_DichVu> query = em.createQuery("SELECT c FROM ChiTietHoaDon_DichVu c WHERE c.hoaDon.maHoaDon = :maHD", ChiTietHoaDon_DichVu.class);
            query.setParameter("maHD", maHD);
            ds = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ChiTietHoaDon_DichVu getCTHD_DV(String maDV, String maHD, String maPhong) throws RemoteException{
        try {
            TypedQuery<ChiTietHoaDon_DichVu> query = em.createQuery("SELECT c FROM ChiTietHoaDon_DichVu c WHERE c.dichVu.maDV = :maDV AND c.hoaDon.maHoaDon = :maHD AND c.phong.maPhong = :maPhong", ChiTietHoaDon_DichVu.class);
            query.setParameter("maDV", maDV);
            query.setParameter("maHD", maHD);
            query.setParameter("maPhong", maPhong);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean themCTHD_DVMoi(ChiTietHoaDon_DichVu cthd_dv) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(cthd_dv);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean capNhap(ChiTietHoaDon_DichVu cTHD_DV) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.merge(cTHD_DV);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaCTHD_DV(ChiTietHoaDon_DichVu cTHD_DV) throws RemoteException{
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM ChiTietHoaDon_DichVu WHERE hoaDon.maHoaDon = :maHD AND phong.maPhong = :maPhong AND dichVu.maDV = :maDV");
            query.setParameter("maHD", cTHD_DV.getHoaDon().getMaHoaDon());
            query.setParameter("maPhong", cTHD_DV.getPhong().getMaPhong());
            query.setParameter("maDV", cTHD_DV.getDichVu().getMaDV());
            int n = query.executeUpdate();
            em.getTransaction().commit();
            return n > 0;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean thayDoiHoaDon(String maHDMoi, List<String> chuoiMaHDCu) throws RemoteException{
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE ChiTietHoaDon_DichVu SET hoaDon.maHoaDon = :maHDMoi WHERE hoaDon.maHoaDon IN :chuoiMaHDCu");
            query.setParameter("maHDMoi", maHDMoi);
            query.setParameter("chuoiMaHDCu", chuoiMaHDCu);
            int n = query.executeUpdate();
            em.getTransaction().commit();
            return n > 0;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }



}
