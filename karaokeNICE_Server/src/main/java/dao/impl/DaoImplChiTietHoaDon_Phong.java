package dao.impl;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.DaoChiTietHoaDon_Phong;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class DaoImplChiTietHoaDon_Phong extends UnicastRemoteObject implements DaoChiTietHoaDon_Phong{

    @Serial
    private static final long serialVersionUID = 2582984167481310619L;
    private EntityManager em =null;
    public DaoImplChiTietHoaDon_Phong()throws RemoteException {
        em = Init.Init().createEntityManager();
    }
    
    public  LocalDateTime chuyenDateTimeSangLocal(String chuoiSQL) throws RemoteException{
		if (chuoiSQL == null)
			return null;
		String dateSQL = chuoiSQL.substring(0, 10);
		String timeSQL = chuoiSQL.substring(11, 19);
		LocalDate date = LocalDate.parse(dateSQL);
		LocalTime time = LocalTime.parse(timeSQL);
		return LocalDateTime.of(date, time);
	}
    public String chuyenLocalSangDateTime(LocalDateTime chuoiJava) throws RemoteException{
		if (chuoiJava == null)
			return null;
		String str = chuoiJava.toString();
		return str.substring(0, 10) + " " + str.substring(11, 19);
	}
    
    public ArrayList<ChiTietHoaDon_Phong> getALLDanhSach() throws RemoteException{
	    return (ArrayList<ChiTietHoaDon_Phong>) em.createQuery("select l from ChiTietHoaDon_Phong l",ChiTietHoaDon_Phong.class).getResultList();
	 }
  
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaHD(String maHD) throws RemoteException{
	    return (ArrayList<ChiTietHoaDon_Phong>) em.createQuery("select l from ChiTietHoaDon_Phong l where l.hoaDon.maHoaDon = :maHD", ChiTietHoaDon_Phong.class)
	            .setParameter("maHD", maHD)
	            .getResultList();
	}
	
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaPhong(String maPhong)throws RemoteException{
	    return (ArrayList<ChiTietHoaDon_Phong>) em.createQuery("select l from ChiTietHoaDon_Phong l where l.phong.maPhong = :maPhong", ChiTietHoaDon_Phong.class)
	            .setParameter("maPhong", maPhong)
	            .getResultList();
	}
	
	public ChiTietHoaDon_Phong getCTHD_Phong(String maPhong, String maHD) throws RemoteException{
	        return em.createQuery("SELECT c FROM ChiTietHoaDon_Phong c WHERE c.phong.maPhong = :maPhong AND c.hoaDon.maHoaDon = :maHD", ChiTietHoaDon_Phong.class)
	                 .setParameter("maPhong", maPhong)
	                 .setParameter("maHD", maHD)
	                 .getSingleResult();
	}
	public boolean themCTHD_PMoi(ChiTietHoaDon_Phong cTHD_P) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(cTHD_P);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
	public boolean xoaPhongTheoMa(String maPhongXoa, String maHD) throws RemoteException{
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        Query query = em.createQuery("DELETE FROM ChiTietHoaDon_Phong c WHERE c.phong.maPhong = :maPhong AND c.hoaDon.maHoaDon = :maHoaDon");
	        query.setParameter("maPhong", maPhongXoa);
	        query.setParameter("maHoaDon", maHD);
	        int deletedCount = query.executeUpdate();
	        tx.commit();
	        return deletedCount > 0;
	    } catch (Exception e) {
	        if (tx != null && tx.isActive()) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	    return false;
	}

    public boolean capNhap(ChiTietHoaDon_Phong cTHD_P) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cTHD_P);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean thayDoiHoaDon(LocalDateTime gioRa, String maHDMoi, ArrayList<String> chuoiMaHDCu) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Lấy danh sách các chi tiết hóa đơn phòng cần cập nhật
            List<ChiTietHoaDon_Phong> chiTietHoaDonPhongs = new ArrayList<>();
            for (String maHDCu : chuoiMaHDCu) {
                Query query = em.createQuery("SELECT c FROM ChiTietHoaDon_Phong c WHERE c.hoaDon.maHoaDon = :maHDCu");
                query.setParameter("maHDCu", maHDCu);
                List<ChiTietHoaDon_Phong> resultList = query.getResultList();
                chiTietHoaDonPhongs.addAll(resultList);
            }
            
            // Cập nhật thông tin cho từng chi tiết hóa đơn phòng
            for (ChiTietHoaDon_Phong chiTiet : chiTietHoaDonPhongs) {
                chiTiet.getHoaDon().setMaHoaDon(maHDMoi); // Thay đổi mã hóa đơn mới
                chiTiet.setGioRa(gioRa); // Cập nhật giờ ra mới
                em.merge(chiTiet); // Cập nhật chi tiết hóa đơn phòng
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

}
