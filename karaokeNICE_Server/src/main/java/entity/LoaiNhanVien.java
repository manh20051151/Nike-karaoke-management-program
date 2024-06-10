package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "LoaiNhanVien")
public class LoaiNhanVien implements Serializable {
    @Serial
    private static final long serialVersionUID = 1754044444786842085L;
    @Id
    @Column(name = "maLoaiNV",columnDefinition = "nchar(6)")
    private String maLoaiVN;
    @Column(name = "tenLoaiNV", columnDefinition = "nvarchar(30)")
    private String tenLoai;
    @Column(columnDefinition = "float")
    private Float heSoLuong;

    @OneToMany(mappedBy = "loaiNhanVien")
    private List<NhanVien> nhanViens;

    public LoaiNhanVien() {

    }


    public String getMaLoaiVN() {
        return maLoaiVN;
    }

    public void setMaLoaiVN(String maLoaiVN) {
        this.maLoaiVN = maLoaiVN;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(Float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoaiVN);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        LoaiNhanVien other = (LoaiNhanVien) obj;
        return Objects.equals(maLoaiVN, other.maLoaiVN);
    }

    public LoaiNhanVien(String maLoaiVN, String tenLoai, Float heSoLuong) {
        this.maLoaiVN = maLoaiVN;
        this.tenLoai = tenLoai;
        this.heSoLuong = heSoLuong;
    }

    @Override
    public String toString() {
        return "LoaiNhanVien [maLoaiVN=" + maLoaiVN + ", tenLoai=" + tenLoai + ", heSoLuong=" + heSoLuong + "]";
    }

}
