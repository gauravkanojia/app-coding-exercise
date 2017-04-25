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
public class CoreApiRequestBody implements Serializable {

  private static final long serialVersionUID = -75668698153998670L;

  @JsonProperty(value = "args", required = true)
  private CoreApiRequestCommonArguments args;

  /**
   * @return the args
   */
  public CoreApiRequestCommonArguments getArgs() {
    return args;
  }

  /**
   * @param args the args to set
   */
  public void setArgs(CoreApiRequestCommonArguments args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return "CoreApiRequestBody [args=" + args + "]";
  }
}
