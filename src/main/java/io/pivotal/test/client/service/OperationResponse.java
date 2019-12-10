package io.pivotal.test.client.service;

import lombok.Getter;

@Getter
public class OperationResponse {

  private Status status;

  private long completionTime;

  public OperationResponse(Status status, long completionTime) {
    this.status = status;
    this.completionTime = completionTime;
  }
}
