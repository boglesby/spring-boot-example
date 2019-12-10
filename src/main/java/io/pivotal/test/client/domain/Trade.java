package io.pivotal.test.client.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.math.BigDecimal;

@Data
@Getter
@ToString
@Region("Trades")
@RequiredArgsConstructor
public class Trade {

  @Id
  @NonNull
  private final String id;

  @NonNull
  private final String cusip;

  @NonNull
  private final int shares;

  @NonNull
  private final BigDecimal price;
}
