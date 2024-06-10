package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LoaiPhong")
public class LoaiPhong implements Serializable {
    @Serial
    private static final long serialVersionUID = 4877857460520601868L;
    @Id
    @Column(columnDefinition = "nchar(5)")
    private String maLoaiPhong;
    @Column(columnDefinition = "nvarchar(15)")
    private String tenLoaiPhong;
    @Column(columnDefinition = "nvarchar(50)")
    private String mieuTa;

    private double gia;
    @OneToMany(mappedBy = "loaiPhong")
    private List<Phong> phongs;

    public LoaiPhong() {

    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoaiPhong);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        LoaiPhong other = (LoaiPhong) obj;
        return Objects.equals(maLoaiPhong, other.maLoaiPhong);
    }

    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, String mieuTa, double gia) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.mieuTa = mieuTa;
        this.gia = gia;
    }

}
