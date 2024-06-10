package entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DonVi")
public class DonVi implements Serializable {
    @Serial
    private static final long serialVersionUID = -7949447250174659345L;
    @Id
    @Column(columnDefinition = "nchar(6)")
    private String maDonVi;
    @Column(columnDefinition = "nvarchar(30)")
    private String tenDonVi;

    @OneToMany(mappedBy = "donVi")
    private List<DichVu> dichVus;

    public DonVi() {

    }

    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDoVi) {
        this.maDonVi = maDoVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public DonVi(String maDoVi, String tenDonVi) {
        this.setMaDonVi(maDoVi);
        this.setTenDonVi(tenDonVi);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maDonVi == null) ? 0 : maDonVi.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        DonVi other = (DonVi) obj;
        if (maDonVi == null) {
            if (other.maDonVi != null)
                return false;
        } else if (!maDonVi.equals(other.maDonVi))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DonVi{" +
                "maDonVi='" + maDonVi + '\'' +
                ", tenDonVi='" + tenDonVi + '\'' +
                '}';
    }
}
