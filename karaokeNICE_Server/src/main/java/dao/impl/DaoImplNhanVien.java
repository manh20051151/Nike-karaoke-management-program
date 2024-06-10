package dao.impl;

import dao.DaoNhanVien;
import entity.NhanVien;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class DaoImplNhanVien extends UnicastRemoteObject implements DaoNhanVien {
	@Serial
	private static final long serialVersionUID = -3454657910208551467L;
	// private DAO_LoaiNhanVien dao_LoaiNhanVien = new DAO_LoaiNhanVien();
	private EntityManager em = null;

	public DaoImplNhanVien() throws RemoteException {
//        ConnectDB.getInstance().connect();
		em = Init.Init().createEntityManager();
	}

	public ArrayList<NhanVien> getALLNhanVien() throws RemoteException {
		return (ArrayList<NhanVien>) em.createQuery("select n from NhanVien n ", NhanVien.class).getResultList();
	}

	public ArrayList<NhanVien> getALLNhanVienKhongCoADMIN() throws RemoteException {
		return (ArrayList<NhanVien>) em
				.createQuery("select n from NhanVien n where n.maNhanVien not like 'admin'", NhanVien.class)
				.getResultList();
	}

	public NhanVien getNhanVienTheoMa(String ma) throws RemoteException {
		return (NhanVien) em.createQuery("select n from NhanVien n where n.maNhanVien=:ma").setParameter("ma", ma)
				.getSingleResult();
	}

	public boolean addNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nhanVien);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNhanVienTheoMa(String maNV) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			NhanVien nv = em.find(NhanVien.class, maNV);
			em.remove(nv);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
//            NhanVien nv = em.find(NhanVien.class,nhanVien.getMaNhanVien());
			em.merge(nhanVien);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateTTNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
//            NhanVien nv = em.find(NhanVien.class,nhanVien.getMaNhanVien());
			em.merge(nhanVien);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<NhanVien> getDSNhanVienTheoTenTuongDoi(String ten) throws RemoteException {
		return (ArrayList<NhanVien>) em
				.createQuery("select n from NhanVien n where n.tenNhanVien like :ten", NhanVien.class)
				.setParameter("ten", "%" + ten + "%").getResultList();
	}

	public ArrayList<NhanVien> getDsNhanVienTheoLoai(String ten) throws RemoteException {
		return (ArrayList<NhanVien>) em
				.createQuery("select n from NhanVien  n where n.loaiNhanVien.tenLoai = :ten", NhanVien.class)
				.setParameter("ten", ten).getResultList();
	}

	public ArrayList<NhanVien> getDSNhanVienTheoSDT(String SDT) throws RemoteException {
		return (ArrayList<NhanVien>) em
				.createQuery("select n from NhanVien n where n.soDienThoai = :sdt", NhanVien.class)
				.setParameter("sdt", SDT).getResultList();
	}

	// public static void main(String[] args) {
//		DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//
//		NhanVien nVien = new NhanVien("001", "SFGD", false, LocalDate.of(22, 8, 12), "54646", "54646", null,
//				new LoaiNhanVien("LNV001", "546", Float.valueOf(4)), null);
//		System.out.println(nVien);
//		dao_NhanVien.addNhanVien(nVien);
//	}
	public byte[] getHinh(String ma) throws RemoteException {
		return em.createQuery("select n.hinhAnh from NhanVien n where n.maNhanVien = :ma", byte[].class)
				.setParameter("ma", ma).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<NhanVien> getTimKiemDSNV(String tenLoaiNV, String maNV, String tenNV, String ngaySinh, String gt,
			String cmnd, String diaChi, String soDT) throws RemoteException {
// Chuyển đổi giới tính thành boolean
		String sql = "SELECT n.* FROM NhanVien n INNER JOIN LoaiNhanVien l ON n.maLoaiNV = l.maLoaiNV  WHERE l.tenLoaiNV LIKE N'%"
				+ tenLoaiNV + "%' " + " AND n.maNV LIKE N'%" + maNV + "%'" + "AND n.tenNV LIKE N'%"
				+ tenNV + "%' AND n.ngaySinh like N'%" + ngaySinh + "%' AND n.gioiTinh like N'%" + gt + "%' "
				+ "AND n.cmnd LIKE N'%" + cmnd + "%'" + " AND n.diaChi LIKE N'%" + diaChi
				+ "%' AND n.soDT LIKE N'%" + soDT + "%';";
		return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
	}
}
