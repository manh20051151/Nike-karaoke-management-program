package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "LoaiKhachHang")
public class LoaiKhachHang implements Serializable {
    @Serial
    private static final long serialVersionUID = -18489599312122580L;
    @Id
    @Column(name = "maLoaiKH", columnDefinition = "nchar(4)")
    private String maLoaiKhachHang;
    @Column(name = "tenLoaiKH", columnDefinition = "nvarchar(20)")
    private String tenLoaiKhachHang;

    @OneToMany(mappedBy = "loaiKhachHang")
    private List<KhachHang> khachHangs;

    public LoaiKhachHang() {

    }


    public String getMaLoaiKhachHang() {
        return maLoaiKhachHang;
    }

    public void setMaLoaiKhachHang(String maLoaiKhachHang) {
        this.maLoaiKhachHang = maLoaiKhachHang;
    }

    public String getTenLoaiKhachHang() {
        return tenLoaiKhachHang;
    }

    public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
        this.tenLoaiKhachHang = tenLoaiKhachHang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoaiKhachHang);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        LoaiKhachHang other = (LoaiKhachHang) obj;
        return Objects.equals(maLoaiKhachHang, other.maLoaiKhachHang);
    }

    public LoaiKhachHang(String maLoaiKhachHang, String tenLoaiKhachHang) {
        this.maLoaiKhachHang = maLoaiKhachHang;
        this.tenLoaiKhachHang = tenLoaiKhachHang;
    }

}
