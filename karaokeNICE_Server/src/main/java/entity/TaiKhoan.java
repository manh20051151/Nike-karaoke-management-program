package entity;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {
    @Serial
    private static final long serialVersionUID = -3989011859536241031L;
    @Id
    @Column(name = "tenTK", columnDefinition = "nchar(30)")
    private String tenTaiKhoan;
    @Column(columnDefinition = "nchar(24)")
    private String matKhau;


    @ManyToOne
    @JoinColumn(name = "maChuTK")
    private NhanVien nhanVien;

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matKhau == null) ? 0 : matKhau.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaiKhoan other = (TaiKhoan) obj;
        if (matKhau == null) {
            if (other.matKhau != null)
                return false;
        } else if (!matKhau.equals(other.matKhau))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TaiKhoan [tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, NhanVien nhanVien) {
        super();
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.nhanVien = nhanVien;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau) {
        super();
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public TaiKhoan() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

}
