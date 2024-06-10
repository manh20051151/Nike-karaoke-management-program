package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DichVu")
public class DichVu implements Serializable {

    @Serial
    private static final long serialVersionUID = -3833198553938148625L;
    @Id
    @Column(name = "maDichVu",columnDefinition = "nchar(5)")

    private String maDV;
    @Column(columnDefinition = "nvarchar(30)")

    private String tenDV;
    @Column(columnDefinition = "float")

    private long gia;
    @Column(columnDefinition = "nvarchar(50)")

    private String ghiChu;
    @Column(columnDefinition = "int")

    private int soLuong;

    @ManyToOne
    @JoinColumn(name="maDonVi")
    private DonVi donVi;

    @ManyToOne
    @JoinColumn(name="maLoaiDV")
    private LoaiDichVu loai;

    @OneToMany(mappedBy = "dichVu")
    private  List<ChiTietHoaDon_DichVu> chiTietHoaDon_dichVus;

    @Column(columnDefinition = "image")
    private byte[] hinhAnh;

    public DichVu() {

    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public DonVi getDonVi() {
        return donVi;
    }

    public void setDonVi(DonVi donVi) {
        this.donVi = donVi;
    }

    public LoaiDichVu getLoai() {
        return loai;
    }

    public void setLoai(LoaiDichVu loai) {
        this.loai = loai;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDV);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        DichVu other = (DichVu) obj;
        return Objects.equals(maDV, other.maDV);
    }

    public DichVu(String maDV, String tenDV, long gia, String ghiChu, int soLuong, DonVi donVi, LoaiDichVu loai,
                  byte[] hinhAnh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.gia = gia;
        this.ghiChu = ghiChu;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.loai = loai;
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "maDV='" + maDV + '\'' +
                ", tenDV='" + tenDV + '\'' +
                ", gia=" + gia +
                ", ghiChu='" + ghiChu + '\'' +
                ", soLuong=" + soLuong +
                ", donVi=" + donVi +
                ", loai=" + loai +
                '}';
    }
}
