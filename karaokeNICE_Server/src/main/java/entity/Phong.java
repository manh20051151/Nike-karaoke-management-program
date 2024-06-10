package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Phong")
public class Phong implements Serializable {
    @Serial
    private static final long serialVersionUID = -1786630618806435044L;
    @Id
    @Column(columnDefinition = "nchar(4)")
    private String maPhong;
    @Column(columnDefinition = "nvarchar(20)")
    private String tenPhong;
    @Column(columnDefinition = "nvarchar(60)")
    private String viTri;
    @Column(columnDefinition = "nvarchar(20)")
    private String trangThaiPhong;
    @ManyToOne
    @JoinColumn(name = "maLoaiPhong")
    private LoaiPhong loaiPhong;

    @Column(nullable = true)
    private Integer sucChua;

    @OneToMany(mappedBy = "phong")
    private List<ChiTietHoaDon_Phong> chiTietHoaDon_phongs;

    @OneToMany(mappedBy = "phong")
    private  List<ChiTietHoaDon_DichVu> chiTietHoaDon_dichVus;

    @OneToMany(mappedBy = "phong")
    private List<PhieuDatPhong> phieuDatPhongs;


    public Phong() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getTrangThaiPhong() {
        return trangThaiPhong;
    }

    public void setTrangThaiPhong(String trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhong);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Phong other = (Phong) obj;
        return Objects.equals(maPhong, other.maPhong);
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    public Phong(String maPhong, String tenPhong, String viTri, String trangThaiPhong, LoaiPhong loaiPhong,
                 Integer sucChua) {
        super();
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.viTri = viTri;
        this.trangThaiPhong = trangThaiPhong;
        this.loaiPhong = loaiPhong;
        this.sucChua = sucChua;
    }

}
