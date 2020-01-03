package io.pivotal.test.client.function;

import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

import java.math.BigDecimal;
import java.util.Set;

@OnRegion(region = "Trades")
public interface TradeFunctions {

  @FunctionId("UpdateTradeFunction")
  Object updateTrade(@Filter Set<?> keys, int shares, BigDecimal price);
}