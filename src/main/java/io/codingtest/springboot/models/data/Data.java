package io.codingtest.springboot.models.data;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class Data {
   private Long id;

   private String pinNumber;
   private String image;

   @Enumerated(EnumType.STRING)
   private EData dataType;

   private String center;
   private String merchantKey;
}
