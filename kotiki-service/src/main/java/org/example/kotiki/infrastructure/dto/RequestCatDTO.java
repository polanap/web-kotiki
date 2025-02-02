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
import lombok.Data;

import java.util.Objects;

/**
 * RequestCat
 */

 @Data
public class RequestCatDTO {
  @SerializedName("name")
  private String name = null;

  @SerializedName("breed")
  private String breed = null;

  public RequestCatDTO name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(example = "Prince", required = true, description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RequestCatDTO breed(String breed) {
    this.breed = breed;
    return this;
  }

   /**
   * Get breed
   * @return breed
  **/
  @Schema(example = "siam", required = true, description = "")
  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestCatDTO requestCat = (RequestCatDTO) o;
    return Objects.equals(this.name, requestCat.name) &&
        Objects.equals(this.breed, requestCat.breed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, breed);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestCat {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    breed: ").append(toIndentedString(breed)).append("\n");
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
