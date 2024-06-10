package dao.impl;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.DaoKhachHang;
import entity.KhachHang;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DaoImplKhachHang extends UnicastRemoteObject implements DaoKhachHang {

    @Serial
    private static final long serialVersionUID = -374822318813831627L;
    private EntityManager em =null;
    public DaoImplKhachHang()throws RemoteException {
        em = Init.Init().createEntityManager();
    }
			
    public ArrayList<KhachHang> getALLKhachHang() throws RemoteException{
	    return (ArrayList<KhachHang>) em.createQuery("select l from KhachHang l",KhachHang.class).getResultList();
	 }
    
    public KhachHang getKhachHangTheoMa(String ma) throws RemoteException {
		return em.find(KhachHang.class,ma);
	 }
    public KhachHang getKhachHangTheoSoDT(String soDT) {
        try {
            TypedQuery<KhachHang> query = em.createQuery("SELECT k FROM KhachHang k WHERE k.soDienThoai = :soDT", KhachHang.class);
            query.setParameter("soDT", soDT);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy khách hàng với số điện thoại " + soDT);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<KhachHang> getDSKhachHangTheoSDT(String sdt)throws RemoteException{
	    return (ArrayList<KhachHang>) em.createQuery("select l from KhachHang l where l.soDienThoai = :sdt", KhachHang.class)
	            .setParameter("sdt", sdt)
	            .getResultList();
	}

    public ArrayList<KhachHang> getDSKhachHangTheoLoai(String ten)throws RemoteException {
	    return (ArrayList<KhachHang>) em.createQuery("select l from KhachHang l where l.loaiKhachHang.tenLoaiKhachHang = :tenKH", KhachHang.class)
	            .setParameter("tenKH", ten)
	            .getResultList();
	}

    public ArrayList<KhachHang> getDSKhachHangTheoTenTuongDoi(String tenKH)throws RemoteException {
	    return (ArrayList<KhachHang>) em.createQuery("select l from KhachHang l where l.tenKhachHang = :tenKH", KhachHang.class)
	            .setParameter("tenKH", tenKH)
	            .getResultList();
	}
    public ArrayList<KhachHang> getTimKiemDSKH(String maKH, String tenLoaiKH, String tenKH, String sdt, Integer Diem, String ghiChu) {
        return (ArrayList<KhachHang>) em.createQuery("select l from KhachHang l where l.maKhachHang like :maKH and l.loaiKhachHang.tenLoaiKhachHang = :tenLoaiKH and l.tenKhachHang like :tenKH and l.soDienThoai like :sdt and l.diem = :diem and l.ghiChu like :ghiChu",KhachHang.class)
                .setParameter("maKH",maKH)
                .setParameter("tenLoaiKH",tenLoaiKH)
                .setParameter("tenKH",tenKH)
                .setParameter("sdt",sdt)
                .setParameter("diem",Diem)
                .setParameter("ghiChu",ghiChu)
                .getResultList();
    }
    public boolean addKhachHang(KhachHang khachHang) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(khachHang);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean deleteKhachHangTheoMa(String maKH)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            KhachHang pdp = em.find(KhachHang.class,maKH);
            em.remove(pdp);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean updateKhachHang(KhachHang khachHang) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            KhachHang kh = em.find(KhachHang.class,khachHang.getMaKhachHang());
            em.merge(kh);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }

}
