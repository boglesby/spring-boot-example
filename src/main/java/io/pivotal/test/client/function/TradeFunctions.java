package io.pivotal.test.client.function;

import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

import java.util.Set;

@OnRegion(region = "Trades")
public interface TradeFunctions {

  @FunctionId("CreateTradeFunction")
  Object createTrade(@Filter Set<?> keys);
}