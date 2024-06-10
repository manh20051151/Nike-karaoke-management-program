package entity;


import jakarta.persistence.*;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "LoaiDichVu")
public class LoaiDichVu implements Serializable {
    @Serial
    private static final long serialVersionUID = -3687078423828952414L;
    @Id
    @Column(columnDefinition = "nchar(6)")
    private String maLoaiDV;
    @Column(columnDefinition = "nvarchar(20)")
    private String tenLoaiDV;

    @OneToMany(mappedBy = "loai")
    private List<DichVu> dichVus;

    public LoaiDichVu() {

    }

    public String getMaLoaiDV() {
        return maLoaiDV;
    }

    public void setMaLoaiDV(String maLoaiDV) {
        this.maLoaiDV = maLoaiDV;
    }

    public String getTenLoaiDV() {
        return tenLoaiDV;
    }

    public void setTenLoaiDV(String tenLoaiDV) {
        this.tenLoaiDV = tenLoaiDV;
    }

    public LoaiDichVu(String maLoaiDV, String tenLoaiDV) {
        this.setMaLoaiDV(maLoaiDV);
        this.setTenLoaiDV(tenLoaiDV);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((maLoaiDV == null) ? 0 : maLoaiDV.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        LoaiDichVu other = (LoaiDichVu) obj;
        if (maLoaiDV == null) {
            if (other.maLoaiDV != null)
                return false;
        } else if (!maLoaiDV.equals(other.maLoaiDV))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoaiDichVu{" +
                "maLoaiDV='" + maLoaiDV + '\'' +
                ", tenLoaiDV='" + tenLoaiDV + '\'' +
                '}';
    }
}
