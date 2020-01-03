package io.pivotal.test.client.controller;

import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.service.OperationResponse;
import io.pivotal.test.client.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

import static io.pivotal.test.client.Constants.*;

@RestController
public class TradeController {

  private final TradeService service;

  public TradeController(TradeService service) {
    this.service = service;
  }

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public String index() {
    return "Spring Data Geode Example";
  }

  @GetMapping(TRADES_PATH + QUERY_ONE_BY_CUSIP + CUSIP_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public Collection<Trade> queryOneByCusip(@PathVariable String cusip) {
    return this.service.queryOneByCusip(
      cusip);
  }

  @GetMapping(TRADES_PATH + GET_ONE + KEY_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public Trade getOne(@PathVariable int key) {
    return this.service.getOne(key);
  }

  @GetMapping(TRADES_PATH + GET + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse get(@PathVariable int entries) {
    return this.service.get(entries);
  }

  @PostMapping(TRADES_PATH + PUT + ENTRIES_PARAMETER + SIZE_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse put(@PathVariable int entries, @PathVariable int size) {
    return this.service.put(entries, size);
  }

  @DeleteMapping(TRADES_PATH + DESTROY + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse destroy(@PathVariable int entries) {
    return this.service.destroy(entries);
  }

  @GetMapping(TRADES_PATH + QUERY_BY_CUSIP + QUERIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse queryByCusip(@PathVariable int queries) {
    return this.service.queryByCusip(queries);
  }

  @PostMapping(TRADES_PATH + FUNCTION_UPDATE + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse functionUpdate(@PathVariable int entries) {
    return this.service.functionUpdate(entries);
  }

  @PostMapping(value = {
    TRADES_PATH + PUT_FOREVER + ENTRIES_PARAMETER + SIZE_PARAMETER,
    TRADES_PATH + PUT_FOREVER + ENTRIES_PARAMETER + SIZE_PARAMETER + THREADS_PARAMETER })
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putForever(@PathVariable int entries, @PathVariable int size, @PathVariable Optional<Integer> threads) {
    return this.service.putForever(entries, size, threads);
  }

  @GetMapping(value = {
    TRADES_PATH + GET_FOREVER + ENTRIES_PARAMETER,
    TRADES_PATH + GET_FOREVER + ENTRIES_PARAMETER + THREADS_PARAMETER })
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse getForever(@PathVariable int entries, @PathVariable Optional<Integer> threads) {
    return this.service.getForever(entries, threads);
  }

  @DeleteMapping(value = {
    TRADES_PATH + DESTROY_FOREVER + ENTRIES_PARAMETER,
    TRADES_PATH + DESTROY_FOREVER + ENTRIES_PARAMETER + THREADS_PARAMETER })
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse destroyForever(@PathVariable int entries, @PathVariable Optional<Integer> threads) {
    return this.service.destroyForever(entries, threads);
  }

  @GetMapping(value = {
    TRADES_PATH + QUERY_BY_CUSIP_FOREVER,
    TRADES_PATH + QUERY_BY_CUSIP_FOREVER + THREADS_PARAMETER})
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse queryByCusipForever(@PathVariable Optional<Integer> threads) {
    return this.service.queryByCusipForever(threads);
  }

  @PostMapping(value = {
    TRADES_PATH + FUNCTION_UPDATE_FOREVER + ENTRIES_PARAMETER,
    TRADES_PATH + FUNCTION_UPDATE_FOREVER + ENTRIES_PARAMETER + THREADS_PARAMETER})
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse functionUpdateForever(@PathVariable int entries, @PathVariable Optional<Integer> threads) {
    return this.service.functionUpdateForever(entries, threads);
  }

  @PostMapping(value = {
    TRADES_PATH + START_TEST + ENTRIES_PARAMETER + SIZE_PARAMETER,
    TRADES_PATH + START_TEST + ENTRIES_PARAMETER + SIZE_PARAMETER + THREADS_PARAMETER
  })
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse[] startTest(@PathVariable int entries, @PathVariable int size, @PathVariable Optional<Integer> threads) {
    return this.service.startTest(entries, size, threads);
  }

  @PostMapping(TRADES_PATH + STOP_OPERATIONS)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse stopOperations() {
    return this.service.stopOperations();
  }

  @GetMapping(METRICS_PATH + METRICS_TYPE_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public Object getMetrics(@PathVariable String type) {
    return this.service.getMetrics(type);
  }
}
