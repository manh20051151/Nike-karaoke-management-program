package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {
    @Serial
    private static final long serialVersionUID = -7136973729402090574L;
    @Id
    @Column(name = "maKH", columnDefinition = "nchar(5)")
    private String maKhachHang;
    @Column(name = "tenKH", columnDefinition = "nvarchar(30)")
    private String tenKhachHang;

    @Column(name = "soDT", columnDefinition = "nchar(10)")
    private String soDienThoai;
    private int diem;
    @Column(columnDefinition = "nvarchar(50)")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "maLoaiKH")
    private LoaiKhachHang loaiKhachHang;

    @OneToMany(mappedBy = "khachHang")
    private List<PhieuDatPhong> phieuDatPhongs;

    @OneToMany(mappedBy = "khachHang")
    private List<HoaDon> hoaDons;


    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LoaiKhachHang getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKhachHang);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        KhachHang other = (KhachHang) obj;
        return Objects.equals(maKhachHang, other.maKhachHang);
    }

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, int diem, String ghiChu,
                     LoaiKhachHang loaiKhachHang) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diem = diem;
        this.ghiChu = ghiChu;
        this.loaiKhachHang = loaiKhachHang;
    }

    public KhachHang() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", soDienThoai="
                + soDienThoai + ", diem=" + diem + ", ghiChu=" + ghiChu + ", loaiKhachHang=" + loaiKhachHang + "]";
    }

}
