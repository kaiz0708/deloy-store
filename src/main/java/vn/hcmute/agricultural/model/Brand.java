package vn.hcmute.agricultural.model;

import javax.persistence.*;

@Entity
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer brandId;
    private String brandName;

    public Brand() {
    }

    public Brand(Integer brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
