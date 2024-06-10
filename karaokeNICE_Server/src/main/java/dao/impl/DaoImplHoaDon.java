package dao.impl;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.DaoHoaDon;
import entity.HoaDon;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DaoImplHoaDon extends UnicastRemoteObject implements DaoHoaDon{

    @Serial
    private static final long serialVersionUID = 219133881682444364L;
    private EntityManager em =null;
    public DaoImplHoaDon()throws RemoteException {
        em = Init.Init().createEntityManager();
    }
    public ArrayList<HoaDon> getALLHoaDon()throws RemoteException{
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l",HoaDon.class).getResultList();
	 }
    
    public HoaDon getHDTheoMaHD(String maDH) throws RemoteException{
		return em.find(HoaDon.class,maDH);
	 }

    public ArrayList<HoaDon> getDSTheoMaNV(String maNVTim) throws RemoteException{
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.nhanVien.maNhanVien = :maNVTim", HoaDon.class)
	            .setParameter("maNVTim", maNVTim)
	            .getResultList();
	}
    public ArrayList<HoaDon> getDSTheoMaKhach(String maKhachTim) throws RemoteException{
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.khachHang.maKhachHang = :maKhachTim", HoaDon.class)
	            .setParameter("maKhachTim", maKhachTim)
	            .getResultList();
	}
    
    public ArrayList<HoaDon> getDSTheoMaPhong(String maPhongTim)throws RemoteException {
	    return (ArrayList<HoaDon>) em.createQuery("select p.hoaDon from ChiTietHoaDon_Phong p where p.phong.maPhong = :maPhongTim", HoaDon.class)
	            .setParameter("maPhongTim", maPhongTim)
	            .getResultList();
	}
    
    public boolean themHoaDon(HoaDon hoaDonMoi)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(hoaDonMoi);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean xoaHoaDonTheoMa(String maHD) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            HoaDon hd = em.find(HoaDon.class,maHD);
            em.remove(hd);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean capNhapHonDon(HoaDon hoaDonSua) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            HoaDon existingHoaDon = em.find(HoaDon.class, hoaDonSua.getMaHoaDon());
            if (existingHoaDon != null) {
                existingHoaDon.setChietKhau(hoaDonSua.getChietKhau());
                existingHoaDon.setNhanVien(hoaDonSua.getNhanVien());
                existingHoaDon.setKhachHang(hoaDonSua.getKhachHang());
                existingHoaDon.setTienNhan(hoaDonSua.getTienNhan());
                em.merge(existingHoaDon);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<HoaDon> getDSTheoTenKhachHangTuongDoi(String tenKH)throws RemoteException{
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.khachHang.tenKhachHang = :tenKH", HoaDon.class)
	            .setParameter("tenKH", tenKH)
	            .getResultList();
	}
    public ArrayList<HoaDon> getDSTheoSDTKhachHangTuongDoi(String sdt)throws RemoteException {
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.khachHang.soDienThoai = :sdt", HoaDon.class)
	            .setParameter("sdt", sdt)
	            .getResultList();
	}
    public ArrayList<HoaDon> getDSTheoTenNhanVienTuongDoi(String tenNV)throws RemoteException {
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.nhanVien.tenNhanVien = :tenNV", HoaDon.class)
	            .setParameter("tenNV", tenNV)
	            .getResultList();
	}
    public ArrayList<HoaDon> getHoaDonDaThanhToan() throws RemoteException{
	    return (ArrayList<HoaDon>) em.createQuery("select l from HoaDon l where l.tienNhan > 0",HoaDon.class).getResultList();
	 }
    
}
