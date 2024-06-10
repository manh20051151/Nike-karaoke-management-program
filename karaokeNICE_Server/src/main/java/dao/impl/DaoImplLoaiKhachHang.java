package dao.impl;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.DaoLoaiKhachHang;
import entity.LoaiKhachHang;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class DaoImplLoaiKhachHang extends UnicastRemoteObject implements DaoLoaiKhachHang {

	@Serial
	private static final long serialVersionUID = -5472382601968630840L;
	private EntityManager em =null;
	    public DaoImplLoaiKhachHang()throws RemoteException {
	        em = Init.Init().createEntityManager();
	    }

	
	    public ArrayList<LoaiKhachHang> getALLLoaiKhachHang()throws RemoteException {
	    return (ArrayList<LoaiKhachHang>) em.createQuery("select l from LoaiKhachHang l",LoaiKhachHang.class).getResultList();
	 }

	 public LoaiKhachHang getLoaiKhachHangTheoMa(String ma) throws RemoteException{
		return em.find(LoaiKhachHang.class,ma);
	 }
	 public LoaiKhachHang getLoaiKhachHangTheoTen(String ten)throws RemoteException {
			return em.find(LoaiKhachHang.class,ten);
		 }

	 public ArrayList<LoaiKhachHang> getDSLoaiKHTheoMa(String maLoaiKH)throws RemoteException{
		    return (ArrayList<LoaiKhachHang>) em.createQuery("select l from LoaiKhachHang l where l.maLoaiKhachHang like :maLoaiKH", LoaiKhachHang.class)
		            .setParameter("maLoaiKH", maLoaiKH)
		            .getResultList();
		}
	 public boolean addLoaiKhachHang(LoaiKhachHang loaiKhachHang) throws RemoteException{
	        EntityTransaction tx = em.getTransaction();
	        try{
	            tx.begin();
	            em.persist(loaiKhachHang);
	            tx.commit();
	            return true;
	        }catch (Exception e){
	            tx.rollback();
	            e.printStackTrace();
	        }
	        return false;
	 }
	 public boolean deleteLoaiKHTheoMa(String maLKH)throws RemoteException {
	        EntityTransaction tx = em.getTransaction();
	        try{
	            tx.begin();
	            LoaiKhachHang kh = em.find(LoaiKhachHang.class,maLKH);
	            em.remove(kh);
	            tx.commit();
	            return true;
	        }catch (Exception e){
	            tx.rollback();
	            e.printStackTrace();
	        }
	        return false;
	 }
	 
	 public boolean updateLoaiKH(LoaiKhachHang loaiKhachHang)throws RemoteException {
	        EntityTransaction tx = em.getTransaction();
	        try{
	            tx.begin();
	            LoaiKhachHang nv = em.find(LoaiKhachHang.class,loaiKhachHang.getMaLoaiKhachHang());
	            em.merge(nv);
	            tx.commit();
	            return true;
	        }catch (Exception e){
	            tx.rollback();
	            e.printStackTrace();
	        }
	        return false;
	 }
	 
	 public ArrayList<LoaiKhachHang> getTimKiemDSLKH(String maLKH ,String tenLoaiKH)throws RemoteException  {
	        return (ArrayList<LoaiKhachHang>) em.createQuery("select l from LoaiKhachHang l where l.maLoaiKhachHang like :ma and " +
	                "l.tenLoaiKhachHang like :ten ",LoaiKhachHang.class)
	                .setParameter("ma",maLKH)
	                .setParameter("ten",tenLoaiKH)
	                .getResultList();
	    }
}
