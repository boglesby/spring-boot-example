package io.pivotal.test.client.repository;

import io.pivotal.test.client.domain.Trade;
import org.springframework.data.gemfire.repository.GemfireRepository;

import java.util.Collection;

public interface TradeRepository extends GemfireRepository<Trade, String> {

  Collection<Trade> findByCusip(String cusip);
}
