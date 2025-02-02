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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.validation.ValidationException;

import org.example.kotiki.infrastructure.domain.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
  private Integer id = null;

  private Integer userId = null;

  private String firstname = null;

  private String lastname = null;

  private LocalDateTime birth = null;

  private String aboutMe = null;

  private String favoriteCatBreed = null;

  public ProfileDTO(Profile profile){
    this.id = profile.getId();
    this.userId = profile.getUserId();
    this.firstname = profile.getFirstname();
    this.lastname = profile.getLastname();
    this.birth = profile.getBirth();
    this.aboutMe = profile.getAboutMe();
    this.favoriteCatBreed = profile.getFavoriteCatBreed();
  }

  public void validate() throws ValidationException {
    if (firstname != null && firstname.length() > 30)
      throw new ValidationException("firstname must be less than or equal to 30");
    if (lastname != null && lastname.length() > 30)
      throw new ValidationException("lastname must be less than or equal to 30");
    if (birth != null && (birth.isBefore(LocalDateTime.now()) && birth.getYear()>=1900 ))
      throw new ValidationException("birth must be before now and past then 1900-01-01");
    if (favoriteCatBreed != null && favoriteCatBreed.length() > 30)
      throw new ValidationException("favorite-cat-breed must be less than or equal to 30");
  }
}
