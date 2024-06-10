package entity;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ChiTietHoaDon_DichVu")
public class ChiTietHoaDon_DichVu implements Serializable {

    @Serial
    private static final long serialVersionUID = -4769527052194420833L;
    @Id
    @ManyToOne
    @JoinColumn(name = "maHD")
    private HoaDon hoaDon;
    @Id
    @ManyToOne
    @JoinColumn(name="maDichVu")
    private DichVu dichVu;
    @Id
    @ManyToOne
    @JoinColumn(name="maPhong")
    private Phong phong;
    private int soLuong;

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon_DichVu(HoaDon hoaDon, DichVu dichVu, Phong phong, int soLuong) {
        this.hoaDon = hoaDon;
        this.dichVu = dichVu;
        this.phong = phong;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon_DichVu() {
        super();
    }

    public long tinhTienDichVu() {
        return soLuong * dichVu.getGia();
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon [hoaDon=" + hoaDon + ", dichVu=" + dichVu + ", phong=" + phong + ", soLuong=" + soLuong
                + "]";
    }

}
