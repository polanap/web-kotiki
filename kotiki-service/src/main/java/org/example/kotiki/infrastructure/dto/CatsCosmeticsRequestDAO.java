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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * CatsCosmeticsRequest
 */
public class CatsCosmeticsRequestDAO {
  @SerializedName("cat-id")
  private Integer catId = null;

  @SerializedName("cosmetics")
  private List<Integer> cosmetics = null;

  public CatsCosmeticsRequestDAO catId(Integer catId) {
    this.catId = catId;
    return this;
  }

   /**
   * Get catId
   * @return catId
  **/
  @Schema(example = "124", required = true, description = "")
  public Integer getCatId() {
    return catId;
  }

  public void setCatId(Integer catId) {
    this.catId = catId;
  }

  public CatsCosmeticsRequestDAO cosmetics(List<Integer> cosmetics) {
    this.cosmetics = cosmetics;
    return this;
  }

  public CatsCosmeticsRequestDAO addCosmeticsItem(Integer cosmeticsItem) {
    if (this.cosmetics == null) {
      this.cosmetics = new ArrayList<Integer>();
    }
    this.cosmetics.add(cosmeticsItem);
    return this;
  }

   /**
   * Get cosmetics
   * @return cosmetics
  **/
  @Schema(description = "")
  public List<Integer> getCosmetics() {
    return cosmetics;
  }

  public void setCosmetics(List<Integer> cosmetics) {
    this.cosmetics = cosmetics;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatsCosmeticsRequestDAO catsCosmeticsRequest = (CatsCosmeticsRequestDAO) o;
    return Objects.equals(this.catId, catsCosmeticsRequest.catId) &&
        Objects.equals(this.cosmetics, catsCosmeticsRequest.cosmetics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catId, cosmetics);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatsCosmeticsRequest {\n");
    
    sb.append("    catId: ").append(toIndentedString(catId)).append("\n");
    sb.append("    cosmetics: ").append(toIndentedString(cosmetics)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}