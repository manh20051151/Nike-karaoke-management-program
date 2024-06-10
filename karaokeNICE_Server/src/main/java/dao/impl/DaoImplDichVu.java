package dao.impl;

import dao.DaoDichVu;
import entity.DichVu;
import entity.LoaiDichVu;
import init.Init;
import jakarta.persistence.*;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DaoImplDichVu extends UnicastRemoteObject implements DaoDichVu {
    @Serial
    private static final long serialVersionUID = 3403464058988315575L;
    private EntityManager em;
    public DaoImplDichVu() throws RemoteException {
        em = Init.Init().createEntityManager();
    }

    public List<DichVu> getAllDichVu() throws RemoteException{
        return (ArrayList<DichVu>) em.createQuery("SELECT d FROM DichVu d", DichVu.class).getResultList();
    }

    public boolean addDichVu(DichVu dichVu) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(dichVu);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateDichVu(DichVu dichVu, boolean capNhatAnh) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DichVu existingDichVu = em.find(DichVu.class, dichVu.getMaDV());
            if (existingDichVu != null) {
                existingDichVu.setTenDV(dichVu.getTenDV());
                existingDichVu.setGia(dichVu.getGia());
                existingDichVu.setGhiChu(dichVu.getGhiChu());
                existingDichVu.setSoLuong(dichVu.getSoLuong());
                existingDichVu.setDonVi(dichVu.getDonVi());
                existingDichVu.setLoai(dichVu.getLoai());
                if (capNhatAnh) {

                    existingDichVu.setHinhAnh(dichVu.getHinhAnh());
                }
                em.merge(existingDichVu);
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

    public boolean deleteDichVuTheoMa(String maDV) throws RemoteException{
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DichVu dichVu = em.find(DichVu.class, maDV);
            if (dichVu != null) {
                em.remove(dichVu);
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
    public DichVu getDichVuTheoMa(String ma) throws RemoteException{
        try {
            TypedQuery<DichVu> query = em.createQuery("SELECT d FROM DichVu d WHERE d.maDV = :ma", DichVu.class);
            query.setParameter("ma", ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy dịch vụ với mã " + ma);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<DichVu> getDSDichVuTheoTenTuongDoi(String ten) throws RemoteException{
        try {
            TypedQuery<DichVu> query = em.createQuery("SELECT d FROM DichVu d WHERE d.tenDV LIKE :ten", DichVu.class);
            query.setParameter("ten", "%" + ten + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<DichVu> getDSDichVuTheoLoai(LoaiDichVu loai) throws RemoteException{
        try {
            TypedQuery<DichVu> query = em.createQuery("SELECT d FROM DichVu d WHERE d.loai = :loai", DichVu.class);
            query.setParameter("loai", loai);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public byte[] getHinhAnhTheoMaDichVu(String maDV) throws RemoteException{
        try {
            TypedQuery<byte[]> query = em.createQuery("SELECT d.hinhAnh FROM DichVu d WHERE d.maDV = :maDV", byte[].class);
            query.setParameter("maDV", maDV);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DichVu> search(String giaTriTimKiem) throws RemoteException{
        try {
            TypedQuery<DichVu> query = em.createQuery(
                    "SELECT d FROM DichVu d " +
                            "JOIN d.donVi dv " +
                            "JOIN d.loai ld " +
                            "WHERE d.maDV LIKE :timKiem " +
                            "OR d.tenDV LIKE :timKiem " +
                            "OR d.gia = :gia " +
                            "OR d.ghiChu LIKE :timKiem " +
                            "OR d.soLuong = :soLuong " +
                            "OR dv.tenDonVi LIKE :timKiem " +
                            "OR ld.tenLoaiDV LIKE :timKiem",
                    DichVu.class);
            query.setParameter("timKiem", "%" + giaTriTimKiem + "%");

            // Kiểm tra xem `giaTriTimKiem` có phải là số không
            int gia = -1;
            if (giaTriTimKiem.matches("\\d+")) {
                gia = Integer.parseInt(giaTriTimKiem);
            }
            query.setParameter("gia", gia);
            query.setParameter("soLuong", gia);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
