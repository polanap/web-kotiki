/*
 * Simple Inventory API
 * This is a simple API
 *
 * OpenAPI spec version: 1.0.0
 * Contact: you@your-company.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.example.kotiki.infrastructure.dto;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;
/**
 * Stat
 */
@Data
@AllArgsConstructor
public class StatDTO {
  @SerializedName("cat-id")
  private Integer catId = null;

  @SerializedName("satiety-degree")
  private Double satietyDegree = null;

  @SerializedName("happy-degree")
  private Double happyDegree = null;


}
