package org.generation.italy.springdemo.models.searchcriteria;

public class CustomerFilterCriteria {
    private Integer custId;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String city;
    private String region;
    private String country;

    public CustomerFilterCriteria(Integer custId, String companyName, String contactName,
                                  String contactTitle, String city, String region, String country) {
        this.custId = custId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public Integer getCustId() {
        return custId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }
}
