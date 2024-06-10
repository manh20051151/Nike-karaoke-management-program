package client;

import dao.*;
import dao.impl.*;
import gui.FrmDangNhap;

import javax.swing.*;
import java.rmi.Naming;

public class Client {
    private static final String URL = "rmi://DESKTOP-A7DCGMB:7878/";

    public static void main(String[] args) {
        try {
            DaoChiTietHoaDon_DichVu daoChiTietHoaDon_dichVu = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");
            DaoChiTietHoaDon_Phong daoChiTietHoaDon_phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
            DaoDichVu daoDichVu = (DaoDichVu) Naming.lookup(URL + "daoDichVu");
            DaoDonVi daoDonVi = (DaoDonVi) Naming.lookup(URL + "daoDonVi");
            DaoHoaDon hoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
            DaoKhachHang daoKhachHang = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");
            DaoLoaiDichVu daoLoaiDichVu = (DaoLoaiDichVu) Naming.lookup(URL + "daoLoaiDichVu");
            DaoLoaiKhachHang daoLoaiKhachHang = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");
            DaoLoaiNhanVien daoLoaiNhanVien = (DaoLoaiNhanVien) Naming.lookup(URL + "daoLoaiNhanVien");
            DaoLoaiPhong daoLoaiPhong = (DaoLoaiPhong) Naming.lookup(URL + "daoLoaiPhong");
            DaoNhanVien daoNhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
            DaoPhieuDatPhong daoPhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");
            DaoPhong daoPhong = (DaoPhong) Naming.lookup(URL + "daoPhong");
            DaoTaiKhoan daoTaiKhoan = (DaoTaiKhoan) Naming.lookup(URL + "daoTaiKhoan");


            daoChiTietHoaDon_dichVu.getAllDanhSach().forEach(System.out::println);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
