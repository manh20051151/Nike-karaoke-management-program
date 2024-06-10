package server;

import dao.*;
import dao.impl.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static final String URL = "rmi://26.173.247.75:7878/";

    public static void main(String[] args) {
        try {
            Context context = new InitialContext();

            DaoChiTietHoaDon_DichVu daoChiTietHoaDon_dichVu = new DaoImplChiTietHoaDon_DichVu();
            DaoChiTietHoaDon_Phong daoChiTietHoaDon_phong = new DaoImplChiTietHoaDon_Phong();
            DaoDichVu daoDichVu = new DaoImplDichVu();
            DaoDonVi daoDonVi = new DaoImplDonVi();
            DaoHoaDon hoaDon = new DaoImplHoaDon();
            DaoKhachHang daoKhachHang = new DaoImplKhachHang();
            DaoLoaiDichVu daoLoaiDichVu = new DaoImplLoaiDichVu();
            DaoLoaiKhachHang daoLoaiKhachHang = new DaoImplLoaiKhachHang();
            DaoLoaiNhanVien daoLoaiNhanVien = new DaoImplLoaiNhanVien();
            DaoLoaiPhong daoLoaiPhong = new DaoImplLoaiPhong();
            DaoNhanVien daoNhanVien = new DaoImplNhanVien();
            DaoPhieuDatPhong daoPhieuDatPhong = new DaoImplPhieuDatPhong();
            DaoPhong daoPhong = new DaoImplPhong();
            DaoTaiKhoan daoTaiKhoan = new DaoImplTaiKhoan();


            LocateRegistry.createRegistry(7878);
            context.bind(URL + "daoChiTietHoaDon_dichVu", daoChiTietHoaDon_dichVu);
            context.bind(URL + "daoChiTietHoaDon_phong", daoChiTietHoaDon_phong);
            context.bind(URL + "daoDichVu", daoDichVu);
            context.bind(URL + "daoDonVi", daoDonVi);
            context.bind(URL + "hoaDon", hoaDon);
            context.bind(URL + "daoKhachHang", daoKhachHang);
            context.bind(URL + "daoLoaiDichVu", daoLoaiDichVu);
            context.bind(URL + "daoLoaiKhachHang", daoLoaiKhachHang);
            context.bind(URL + "daoLoaiNhanVien",daoLoaiNhanVien);
            context.bind(URL + "daoLoaiPhong", daoLoaiPhong);
            context.bind(URL + "daoNhanVien", daoNhanVien);
            context.bind(URL + "daoPhieuDatPhong", daoPhieuDatPhong);
            context.bind(URL + "daoPhong", daoPhong);
            context.bind(URL + "daoTaiKhoan", daoTaiKhoan);


            System.out.println("Server is running...");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
