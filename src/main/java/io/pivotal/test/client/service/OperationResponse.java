package io.pivotal.test.client.service;

import lombok.Getter;

@Getter
public class OperationResponse {

  private String operation;

  private Status status;

  private long completionTime;

  public OperationResponse(String operation, Status status, long completionTime) {
    this.operation = operation;
    this.status = status;
    this.completionTime = completionTime;
  }
}
