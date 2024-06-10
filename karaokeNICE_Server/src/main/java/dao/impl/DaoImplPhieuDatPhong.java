package dao.impl;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dao.DaoPhieuDatPhong;
import entity.PhieuDatPhong;
import entity.Phong;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class DaoImplPhieuDatPhong extends UnicastRemoteObject implements DaoPhieuDatPhong {

    @Serial
    private static final long serialVersionUID = 7970943455225306197L;
    private EntityManager em =null;
    public DaoImplPhieuDatPhong() throws RemoteException {
        em = Init.Init().createEntityManager();
    }
	
    
    public static LocalDateTime chuyenDateTimeSangLocal(String chuoiSQL) throws RemoteException{
		if (chuoiSQL == null)
			return null;
		String dateSQL = chuoiSQL.substring(0, 10);
		String timeSQL = chuoiSQL.substring(11, 19);
		LocalDate date = LocalDate.parse(dateSQL);
		LocalTime time = LocalTime.parse(timeSQL);
		return LocalDateTime.of(date, time);
	}
    public static String chuyenLocalSangDateTime(LocalDateTime chuoiJava) throws RemoteException{
		if (chuoiJava == null)
			return null;
		String str = chuoiJava.toString();
		return str.substring(0, 10) + " " + str.substring(11, str.length());
	}
    
    public ArrayList<PhieuDatPhong> getAllPhieu()throws RemoteException{
	    return (ArrayList<PhieuDatPhong>) em.createQuery("select l from PhieuDatPhong l",PhieuDatPhong.class).getResultList();
	 }
    
    public PhieuDatPhong getPhieuTheoMa(String maTim)throws RemoteException {
		return em.find(PhieuDatPhong.class,maTim);
	 }
    
    public ArrayList<PhieuDatPhong> getTheoMaPhong_TrangThai(String maPhong, String trangThai)throws RemoteException {
	    return (ArrayList<PhieuDatPhong>) em.createQuery("select l from PhieuDatPhong l where l.phong.maPhong = :maPhong and l.trangThai = :trangThai", PhieuDatPhong.class)
	            .setParameter("maPhong", maPhong)
	            .setParameter("trangThai", trangThai)
	            .getResultList();
	}
    public ArrayList<PhieuDatPhong> getDSTheoTrangThai(String trangThai)throws RemoteException {
	    return (ArrayList<PhieuDatPhong>) em.createQuery("select l from PhieuDatPhong l where l.trangThai = :trangThai", PhieuDatPhong.class)
	            .setParameter("trangThai", trangThai)
	            .getResultList();
	}
    
    public boolean nhanPhongCho(String maPhong)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Tìm phòng theo mã phòng
            Query query = em.createQuery("SELECT p FROM Phong p WHERE p.maPhong = :maPhong");
            query.setParameter("maPhong", maPhong);
            Phong phong = (Phong) query.getSingleResult();
            
            // Cập nhật trạng thái của phòng thành "Đã nhận"
            phong.setTrangThaiPhong("Đang sử dụng");
            
            // Merge để cập nhật thông tin phòng
            em.merge(phong);
            
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
    public boolean capNhatTrangThai(String maPhong) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Tìm phòng theo mã phòng
            Query query = em.createQuery("SELECT p FROM Phong p WHERE p.maPhong = :maPhong");
            query.setParameter("maPhong", maPhong);
            Phong phong = (Phong) query.getSingleResult();
            
            // Cập nhật trạng thái của phòng thành "Đã nhận"
            phong.setTrangThaiPhong("Đang trống");
            
            // Merge để cập nhật thông tin phòng
            em.merge(phong);
            
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

    
    public ArrayList<PhieuDatPhong> getDSPhieuTheoMa(String MaPhieu, String soDT)throws RemoteException {
	    return (ArrayList<PhieuDatPhong>) em.createQuery("select l from PhieuDatPhong l where l.maPhieuDatPhong = :maPhieuDatPhong and l.khachHang.soDienThoai = :soDT", PhieuDatPhong.class)
	            .setParameter("maPhieuDatPhong", MaPhieu)
	            .setParameter("soDT", soDT)
	            .getResultList();
	}
   
    public boolean themPhieuDat(PhieuDatPhong phieuMoi)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(phieuMoi);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean xoaPhieuTheoMa(String maXoa)throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            PhieuDatPhong pdp = em.find(PhieuDatPhong.class,maXoa);
            em.remove(pdp);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
 }
    public boolean capNhapPhieu(PhieuDatPhong phieu) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            PhieuDatPhong existingPhieu = em.find(PhieuDatPhong.class, phieu.getMaPhieuDatPhong());
            if (existingPhieu != null) {
                existingPhieu.setGioTaoPhieu(phieu.getGioTaoPhieu());
                existingPhieu.setGioVaoPhong(phieu.getGioVaoPhong());
                existingPhieu.setTongGioDat(phieu.getTongGioDat());
                existingPhieu.setKhachHang(phieu.getKhachHang());
                existingPhieu.setPhong(phieu.getPhong());
                existingPhieu.setNhanVien(phieu.getNhanVien());
                existingPhieu.setTrangThai(phieu.getTrangThai());
                em.merge(existingPhieu);
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
    public ArrayList<ArrayList<String>> getTKNhanVienTheoPhieuDatPhong(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        try {
            // Lấy danh sách phiếu đặt phòng có gioTaoPhieu nằm trong khoảng ngày từ ngayBatDau đến ngayKetThuc
            List<PhieuDatPhong> phieuDatPhongs = em.createQuery("SELECT p FROM PhieuDatPhong p WHERE p.gioTaoPhieu BETWEEN :ngayBatDau AND :ngayKetThuc", PhieuDatPhong.class)
                                                    .setParameter("ngayBatDau", ngayBatDau.atStartOfDay())
                                                    .setParameter("ngayKetThuc", ngayKetThuc.plusDays(1).atStartOfDay()) // Cộng thêm 1 ngày để bao gồm cả ngày cuối cùng
                                                    .getResultList();
            
            // Duyệt qua từng phiếu đặt phòng để lấy thông tin nhân viên
            for (PhieuDatPhong phieu : phieuDatPhongs) {
                ArrayList<String> nhanVienInfo = new ArrayList<>();
                nhanVienInfo.add(phieu.getNhanVien().getMaNhanVien());
                nhanVienInfo.add(phieu.getNhanVien().getTenNhanVien());
                nhanVienInfo.add(phieu.getNhanVien().getSoDienThoai());
                // Thêm thông tin nhân viên vào kết quả
                result.add(nhanVienInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

}
