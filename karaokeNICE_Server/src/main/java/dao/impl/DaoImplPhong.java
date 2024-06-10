package dao.impl;

import dao.DaoPhong;
import entity.Phong;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DaoImplPhong extends UnicastRemoteObject implements DaoPhong {
    @Serial
    private static final long serialVersionUID = -5383864534545507518L;
    EntityManager em ;
     public DaoImplPhong()throws RemoteException {
         em = Init.Init().createEntityManager();
     }

    public ArrayList<Phong> getDanhSachPhong()throws RemoteException {
         return (ArrayList<Phong>) em.createQuery("select p from Phong p",Phong.class).getResultList();
    }

    // lấy TT theo mã
    public Phong getPhongTheoMa(String maPhongTim)throws RemoteException {
        return em.find(Phong.class,maPhongTim);
    }

    /**
     * Tìm tương đối theo tên phòng
     *
     * @param ten: tên phòng truyền vào có thể là tên đầy đủ hoặc không.
     * @return Danh sách phòng tìm được
     */
    public ArrayList<Phong> getDSPhongTheoTenTuongDoi(String ten)throws RemoteException{
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.tenPhong like :ten",Phong.class)
                .setParameter("ten","%"+ten+"%")
                .getResultList();
    }

    // lấy danh sách theo trạng thái Phòng
    public ArrayList<Phong> getDSTheoTTphong(String trangThaiTim)throws RemoteException {
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.trangThaiPhong =:tt",Phong.class)
                .setParameter("tt",trangThaiTim)
                .getResultList();
    }

    // lấy danh sách theo trạng thái Phòng
    public ArrayList<Phong> getDSTheoTTphong(String trangThai1, String trangThai2) throws RemoteException{
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.trangThaiPhong = :tt1 or p.trangThaiPhong =:tt2 ",Phong.class)
                .setParameter("tt1",trangThai1)
                .setParameter("tt2",trangThai2)
                .getResultList();
    }

    // lấy danh sách theo loại Phòng
    public ArrayList<Phong> getDSTheoLoaiPhong(String maLoaiPhong)throws RemoteException {
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.loaiPhong.maLoaiPhong =: ma",Phong.class)
                .setParameter("ma",maLoaiPhong)
                .getResultList();
    }

    // lấy danh sách theo loại Phòng và trạng thái phòng
    public ArrayList<Phong> getDSTheoLP_TTP(String maLoaiPhong, String trangThaiPhong) throws RemoteException{
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.maPhong =: ma and p.trangThaiPhong = :tt",Phong.class)
                .setParameter("ma",maLoaiPhong)
                .setParameter("tt",trangThaiPhong)
                .getResultList();
    }

    // lấy danh sách phòng theo mã hoá đơn
    public ArrayList<Phong> getDSTheoMaHD(String maHD) throws RemoteException{
        return (ArrayList<Phong>) em.createQuery("select p.phong from ChiTietHoaDon_Phong p where p.hoaDon.maHoaDon=:ma",Phong.class)
                .setParameter("ma",maHD)
                .getResultList();
    }

    // lấy danh sách phòng có phiếu đặt phòng
    public ArrayList<Phong> getDSCoPhieuDat() {
        ArrayList<Phong> dsPhong = new ArrayList<>();
        try {
            TypedQuery<Phong> query = em.createQuery(
                    "SELECT p FROM Phong p WHERE p.maPhong IN (SELECT pdp.phong.maPhong FROM PhieuDatPhong pdp WHERE pdp.trangThai = :trangThai)", Phong.class);
            query.setParameter("trangThai", "Đang chờ");
            dsPhong.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    // thêm phòng mới
    public boolean addPhong(Phong phongMoi)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(phongMoi);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // cập nhập phòng
    public boolean capNhapPhong(Phong phongMoi)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
//            Phong p = em.find(Phong.class,phongMoi.getMaPhong());
            em.merge(phongMoi);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // xoá phòng
    public boolean xoaPhongTheoMa(String maPhongXoa)throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Phong p = em.find(Phong.class,maPhongXoa);
            em.remove(p);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Phong> search(String giaTriTimKiem) throws RemoteException{
        return (ArrayList<Phong>) em.createQuery("select p from Phong p where p.maPhong like :tim " +
                "or p.trangThaiPhong like :tim or p.loaiPhong.tenLoaiPhong like :tim ",Phong.class)
                .setParameter("tim","%"+giaTriTimKiem+"%").getResultList();
    }
}
