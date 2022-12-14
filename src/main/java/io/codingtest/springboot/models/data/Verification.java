package io.codingtest.springboot.models.data;


import javax.persistence.*;

@Entity
public class Verification {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String pinNumber;
   @Lob
   private byte[] imageBytes;
   @Enumerated(EnumType.STRING)
   private EDataType dataType;
   @ManyToOne
   private Center center;
   private String merchantKey;

   public Verification(String pinNumber, byte[] imageBytes, EDataType dataType, Center center, String merchantKey) {
      this.pinNumber = pinNumber;
      this.imageBytes = imageBytes;
      this.dataType = dataType;
      this.center = center;
      this.merchantKey = merchantKey;
   }

   public Verification() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPinNumber() {
      return pinNumber;
   }

   public void setPinNumber(String pinNumber) {
      this.pinNumber = pinNumber;
   }

   public byte []getImage() {
      return imageBytes;
   }

   public void setImage(byte [] imageBytes) {
      this.imageBytes = imageBytes;
   }

   public EDataType getDataType() {
      return dataType;
   }

   public void setDataType(EDataType dataType) {
      this.dataType = dataType;
   }

   public Center getCenter() {
      return center;
   }

   public void setCenter(Center center) {
      this.center = center;
   }

   public String getMerchantKey() {
      return merchantKey;
   }

   public void setMerchantKey(String merchantKey) {
      this.merchantKey = merchantKey;
   }
}
