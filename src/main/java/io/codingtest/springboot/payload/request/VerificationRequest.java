package io.codingtest.springboot.payload.request;


public class VerificationRequest {
    private String pinNumber;
    private String image;
    private String dataType;
    private String center;
    private String merchantKey;

    public VerificationRequest(String pinNumber, String image, String dataType, String center, String merchantKey) {
        this.pinNumber = pinNumber;
        this.image = image;
        this.dataType = dataType;
        this.center = center;
        this.merchantKey = merchantKey;
    }

    public VerificationRequest() {
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }
}
