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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * FriendRequests
 */

 @Data
public class FriendRequestsDTO {
  @SerializedName("users")
  private List<FriendRequestDTO> users = null;

  public FriendRequestsDTO users(List<FriendRequestDTO> users) {
    this.users = users;
    return this;
  }

  public FriendRequestsDTO addUsersItem(FriendRequestDTO usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<FriendRequestDTO>();
    }
    this.users.add(usersItem);
    return this;
  }

   /**
   * Get users
   * @return users
  **/
  @Schema(description = "")
  public List<FriendRequestDTO> getUsers() {
    return users;
  }

  public void setUsers(List<FriendRequestDTO> users) {
    this.users = users;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendRequestsDTO friendRequests = (FriendRequestsDTO) o;
    return Objects.equals(this.users, friendRequests.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendRequests {\n");
    
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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
