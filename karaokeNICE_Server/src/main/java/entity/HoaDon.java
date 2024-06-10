package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
    @Serial
    private static final long serialVersionUID = 2208869241689981918L;
    @Id
    @Column(name = "maHD", columnDefinition = "nchar(6)")
    private String maHoaDon;
    private int chietKhau;

    @ManyToOne
    @JoinColumn(name="maNV")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name="maKH")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDon")
    private List<ChiTietHoaDon_Phong> chiTietHoaDon_phongs;

    @OneToMany(mappedBy = "hoaDon")
    private  List<ChiTietHoaDon_DichVu> chiTietHoaDon_dichVus;
    @Column(columnDefinition = "float")
    private Long tienNhan;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public HoaDon() {
        super();
    }

    public HoaDon(String maHoaDon, int chietKhau, NhanVien nhanVien, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.chietKhau = chietKhau;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tienNhan = Long.valueOf(0);
    }

    public HoaDon(String maHoaDon, int chietKhau, NhanVien nhanVien, KhachHang khachHang, Long tienNhan) {
        this.maHoaDon = maHoaDon;
        this.chietKhau = chietKhau;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tienNhan = tienNhan;
    }

    @Override
    public String toString() {
        return "HoaDon [maHoaDon=" + maHoaDon + ", chietKhau=" + chietKhau + ", nhanVien=" + nhanVien + ", khachHang="
                + khachHang + ", ]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + chietKhau;
        return result;
    }

    public Long getTienNhan() {
        return tienNhan;
    }

    public void setTienNhan(Long tienNhan) {
        this.tienNhan = tienNhan;
    }

    /**
     * Kiểm tra hóa đơn đã có giờ ra chưa
     *
     * @param dsPhong
     * @return true: hóa đơn đã có giờ ra, false: hóa đơn chưa có giờ ra
     */
    public boolean kiemTraGioRa(ArrayList<ChiTietHoaDon_Phong> dsPhong) {
        for (int i = 0; i < dsPhong.size(); i++) {
            if (dsPhong.get(i).getGioRa() == null) {
                return false;
            }
        }
        return true;
    }

    public LocalDateTime getGioVao(ArrayList<ChiTietHoaDon_Phong> dsPhong) {
        LocalDateTime gioVao = dsPhong.get(0).getGioVao();
        for (int i = 0; i < dsPhong.size(); i++) {
            if (gioVao.isAfter(dsPhong.get(i).getGioVao())) {
                gioVao = dsPhong.get(i).getGioVao();
            }
        }
        return gioVao;
    }

    public LocalDateTime getGioRa(ArrayList<ChiTietHoaDon_Phong> dsPhong) {
        LocalDateTime gioRa = dsPhong.get(0).getGioRa();
        for (int i = 0; i < dsPhong.size(); i++) {
            if (gioRa.isBefore(dsPhong.get(i).getGioRa())) {
                gioRa = dsPhong.get(i).getGioRa();
            }
        }
        return gioRa;
    }

    public Long tinhTongTienPhong(ArrayList<ChiTietHoaDon_Phong> dsPhong) {
        Long tongTienPhong = Long.valueOf(0);
        for (int i = 0; i < dsPhong.size(); i++) {
            tongTienPhong += dsPhong.get(i).tinhTienPhong();
        }
        return tongTienPhong;
    }

    public Long tinhTongTienDichVu(ArrayList<ChiTietHoaDon_DichVu> dsDichVu) {
        Long tongTienDichVu = Long.valueOf(0);
        for (int i = 0; i < dsDichVu.size(); i++) {
            tongTienDichVu += dsDichVu.get(i).tinhTienDichVu();
        }
        return tongTienDichVu;
    }

    public Long tinhTienThanhToan(ArrayList<ChiTietHoaDon_Phong> dsPhong, ArrayList<ChiTietHoaDon_DichVu> dsDichVu) {
        Long tongTien = tinhTongTienPhong(dsPhong) + tinhTongTienDichVu(dsDichVu);
        return tongTien - tongTien * chietKhau / 100;
    }

}
