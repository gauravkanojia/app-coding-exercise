/**
 * 
 */
package com.gauravk.app.coding.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gaurav Kanojia
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreApiRequestCommonArguments implements Serializable {

  private static final long serialVersionUID = 1930587676015633460L;

  @JsonProperty(value = "uid", required = true, defaultValue = "0")
  private long uid;

  @JsonProperty(value = "token", required = true, defaultValue = "")
  private String token;

  @JsonProperty(value = "api-token", required = true, defaultValue = "")
  private String apiToken;

  @JsonProperty(value = "json-strict-mode", required = true, defaultValue = "false")
  private boolean jsonStrictMode;

  @JsonProperty(value = "json-verbose-response", required = true, defaultValue = "false")
  private boolean jsonVerboseResponse;

  /**
   * @return the uid
   */
  public long getUid() {
    return uid;
  }

  /**
   * @param uid the uid to set
   */
  public void setUid(long uid) {
    this.uid = uid;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token the token to set
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * @return the apiToken
   */
  public String getApiToken() {
    return apiToken;
  }

  /**
   * @param apiToken the apiToken to set
   */
  public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
  }

  /**
   * @return the jsonStrictMode
   */
  public boolean isJsonStrictMode() {
    return jsonStrictMode;
  }

  /**
   * @param jsonStrictMode the jsonStrictMode to set
   */
  public void setJsonStrictMode(boolean jsonStrictMode) {
    this.jsonStrictMode = jsonStrictMode;
  }

  /**
   * @return the jsonVerboseResponse
   */
  public boolean isJsonVerboseResponse() {
    return jsonVerboseResponse;
  }

  /**
   * @param jsonVerboseResponse the jsonVerboseResponse to set
   */
  public void setJsonVerboseResponse(boolean jsonVerboseResponse) {
    this.jsonVerboseResponse = jsonVerboseResponse;
  }

  @Override
  public String toString() {
    return "CoreApiRequestCommonArguments [uid=" + uid + ", token=" + token + ", apiToken="
        + apiToken + ", jsonStrictMode=" + jsonStrictMode + ", jsonVerboseResponse="
        + jsonVerboseResponse + "]";
  }


}
