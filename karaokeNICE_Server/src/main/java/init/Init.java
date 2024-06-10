package init;

import dao.*;
import dao.impl.*;
import entity.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;


public class Init {
    public  static EntityManagerFactory Init(){
       return Persistence.createEntityManagerFactory("MSSQL");
    }

    public static void main(String[] args) throws RemoteException {
        DaoLoaiDichVu daoLoaiDichVu = new DaoImplLoaiDichVu();
        DaoDichVu daoDichVu = new DaoImplDichVu();
        DaoDonVi daoDonVi = new DaoImplDonVi();
        DaoChiTietHoaDon_DichVu daoChiTietHoaDon_dichVu = new DaoImplChiTietHoaDon_DichVu();

//        DaoTaiKhoan daoTaiKhoan = new DaoImplTaiKhoan();
//        DaoKhachHang daoKhachHang = new DaoImplKhachHang();
//        System.out.println(daoKhachHang.getKhachHangTheoSoDT("0345678912"));

//        DaoChiTietHoaDon_Phong daoChiTietHoaDon_phong = new DaoImplChiTietHoaDon_Phong();
//        daoChiTietHoaDon_phong.getALLDanhSach()

//        System.out.println(daoTaiKhoan.getTaiKhoanTheoMa("NV001"));

//        daoChiTietHoaDon_dichVu.getAllDanhSach().forEach(System.out::println);

//        HoaDon hoaDon = new HoaDon();
//        hoaDon.setMaHoaDon("HD002");
//
//        DichVu dichVu = new DichVu();
//        dichVu.setMaDV("DV001");
//
//        Phong  phong = new Phong();
//        phong.setMaPhong("P007");
//        ChiTietHoaDon_DichVu chiTietHoaDon_dichVu = new ChiTietHoaDon_DichVu( hoaDon, dichVu,phong, 10);
//
//        boolean b = daoChiTietHoaDon_dichVu.themCTHD_DVMoi(chiTietHoaDon_dichVu);
//
//        System.out.println("them: "+ b);






        //-----

//        daoDonVi.getALLDonVi().forEach(System.out::println);

//        System.out.println(daoDonVi.getDonViTheoMa("MDV001"));
//        System.out.println(daoDonVi.getDonViTheoTen("Đĩa"));

//       DonVi donVi = new DonVi("MDV007", "test");
//
//        boolean b = daoDonVi.addDonVi(donVi);
//        System.out.println("them: " + b);

//        DonVi donVi = new DonVi("MDV007", "update");
//        boolean b = daoDonVi.updateDonVi(donVi);
//        System.out.println("sua: " + b);

//        boolean b = daoDonVi.deleteDonVi("MDV007");
//        System.out.println("xoa: " + b);




       //------
//        daoLoaiDichVu.getAllDichVu().forEach(System.out::println);
//        System.out.println(daoLoaiDichVu.getLoaiTheoMa("LDV002"));

//        System.out.println(daoLoaiDichVu.getLoaiTheoTen("Đồ uống"));

//        LoaiDichVu loaiDichVu = new LoaiDichVu("LDV003", "TesT");
//        boolean b = daoLoaiDichVu.addLoaiDichVu(loaiDichVu);
//        System.out.println("them: " + b);

//            LoaiDichVu loaiDichVu = new LoaiDichVu("LDV003", "update");
//
//        boolean b = daoLoaiDichVu.updateLoaiDichVu(loaiDichVu);
//        System.out.println("sua: " + b);


//        boolean b = daoLoaiDichVu.deleteLoaiDichVu("LDV003");
//        System.out.println("xoa: " + b);
// ---------
//        daoDichVu.getAllDichVu().forEach(System.out::println);

//        DichVu dichVu = new DichVu();
//        dichVu.setMaDV("DV018");
//        dichVu.setTenDV("Dưa hấu");
//        dichVu.setGia(20000); // Giá dịch vụ
//        dichVu.setGhiChu("Trái cây uớp lạnh");
//        dichVu.setSoLuong(0); // Số lượng dịch vụ
//
//        DonVi donVi = new DonVi();
//        donVi.setMaDonVi("MDV001");
//        dichVu.setDonVi(donVi);
//        LoaiDichVu loaiDichVu = new LoaiDichVu();
//        loaiDichVu.setMaLoaiDV("LDV001");
//        dichVu.setLoai(loaiDichVu);
//        boolean b =  daoDichVu.addDichVu(dichVu);
//        System.out.println("them: " + b);

//        DichVu dichVu = new DichVu();
//        dichVu.setMaDV("DV018");
//        dichVu.setTenDV("test");
//        dichVu.setGia(20000); // Giá dịch vụ
//        dichVu.setGhiChu("test");
//        dichVu.setSoLuong(0); // Số lượng dịch vụ
//
//        DonVi donVi = new DonVi();
//        donVi.setMaDonVi("MDV001");
//        dichVu.setDonVi(donVi);
//        LoaiDichVu loaiDichVu = new LoaiDichVu();
//        loaiDichVu.setMaLoaiDV("LDV001");
//        boolean b =  daoDichVu.updateDichVu(dichVu,false);
//        System.out.println("sua: " + b);


//        boolean b =  daoDichVu.deleteDichVuTheoMa("DV018");
//        System.out.println("xoa: " + b);

//        System.out.println(daoDichVu.getDichVuTheoMa("DV002"));

//        System.out.println(daoDichVu.getDSDichVuTheoTenTuongDoi("Trà"));

//        LoaiDichVu loaiDichVu = new LoaiDichVu();
//        loaiDichVu.setMaLoaiDV("LDV001");
//
//        System.out.println(daoDichVu.getDSDichVuTheoLoai(loaiDichVu));

//        System.out.println( daoDichVu.getHinhAnhTheoMaDichVu("DV009"));

//        System.out.println(daoDichVu.search("Trà"));
        // -------------


    }
}
