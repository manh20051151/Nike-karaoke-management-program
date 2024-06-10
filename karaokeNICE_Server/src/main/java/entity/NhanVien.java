package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {
    @Serial
    private static final long serialVersionUID = -162003571003452356L;
    @Id
    @Column(name = "maNV", columnDefinition = "nchar(5)")
    private String maNhanVien;
    @Column(name = "tenNV", columnDefinition = "nvarchar(50)")
    private String tenNhanVien;
    private boolean gioiTinh;
    @Column(columnDefinition = "date")
    private LocalDate ngaySinh;
    @Column(columnDefinition = "nchar(10)")
    private String cmnd;
    @Column(columnDefinition = "nvarchar(100)")
    private String diaChi;

    @Column(name = "soDT",columnDefinition = "nchar(10)")
    private String soDienThoai;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maLoaiNV")
    private LoaiNhanVien loaiNhanVien;

    @OneToMany(mappedBy = "nhanVien")
    private List<PhieuDatPhong> phieuDatPhongs;


    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons;

    @OneToMany(mappedBy = "nhanVien")
    private List<TaiKhoan> taiKhoans;
    @Column(columnDefinition = "image")
    private byte[] hinhAnh;

    @Column(name = "gmail", columnDefinition = "varchar(80)")
    private String email;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public LoaiNhanVien getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhanVien);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(maNhanVien, other.maNhanVien);
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public NhanVien() {
        super();
        // TODO Auto-generated constructor stub
    }
    //tạo nhân viên mới không có email
    public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, LocalDate ngaySinh, String cmnd,
                    String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, byte[] hinhAnh) {
        super();
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.loaiNhanVien = loaiNhanVien;
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh
                + ", ngaySinh=" + ngaySinh + ", cmnd=" + cmnd + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai
                + ", loaiNhanVien=" + loaiNhanVien + ", hinhAnh=" + Arrays.toString(hinhAnh) + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, LocalDate ngaySinh, String cmnd,
                    String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, byte[] hinhAnh, String email) {
        super();
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.loaiNhanVien = loaiNhanVien;
        this.hinhAnh = hinhAnh;
        this.email = email;
    }

}
