package vn.com.blowjob.bean;

import java.io.Serializable;

/**
 * Product Bean
 *
 * @version 1.0
 * @author ToanNDD
 */
@SuppressWarnings("serial")
public class Product implements Serializable  {

    private String productCode;
    private String branchName;
    private String title;
    private Double price;
    private String key1;
    private String key2;
    private String description;

    public Product() {
    }

    public Product(String productCode, String branchName, String title, Double price, String key1, String key2, String description) {
        this.productCode = productCode;
        this.branchName = branchName;
        this.title = title;
        this.price = price;
        this.key1 = key1;
        this.key2 = key2;
        this.description = description;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the key1
     */
    public String getKey1() {
        return key1;
    }

    /**
     * @param key1 the key1 to set
     */
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    /**
     * @return the key2
     */
    public String getKey2() {
        return key2;
    }

    /**
     * @param key2 the key2 to set
     */
    public void setKey2(String key2) {
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return "product{" + "code=" + productCode + ", Full Name=" + branchName + " " + title + ", description=" + description + '}';
    }
}
