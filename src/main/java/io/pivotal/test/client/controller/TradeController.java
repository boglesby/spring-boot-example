package io.pivotal.test.client.controller;

import io.pivotal.test.client.domain.Trade;
import io.pivotal.test.client.service.OperationResponse;
import io.pivotal.test.client.service.TradeService;
import org.apache.geode.cache.client.internal.Op;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

  @PostMapping(TRADES_PATH + PUT_FOREVER + ENTRIES_PARAMETER + SIZE_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putForever(@PathVariable int entries, @PathVariable int size) {
    return this.service.putForeverThreads(entries, size, 1);
  }

  @GetMapping(TRADES_PATH + GET_FOREVER + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse getForever(@PathVariable int entries) {
    return this.service.getForeverThreads(entries, 1);
  }

  @DeleteMapping(TRADES_PATH + DESTROY_FOREVER + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse destroyForever(@PathVariable int entries) {
    return this.service.destroyForeverThreads(entries, 1);
  }

  @GetMapping(TRADES_PATH + QUERY_BY_CUSIP_FOREVER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse queryByCusipForever() {
    return this.service.queryByCusipForeverThreads(1);
  }

  @PostMapping(TRADES_PATH + FUNCTION_UPDATE_FOREVER + ENTRIES_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse functionUpdateForever(@PathVariable int entries) {
    return this.service.functionUpdateForeverThreads(entries, 1);
  }

  @PostMapping(TRADES_PATH + PUT_FOREVER_THREADS + ENTRIES_PARAMETER + SIZE_PARAMETER + THREADS_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse putForeverThreads(@PathVariable int entries, @PathVariable int size, @PathVariable int threads) {
    return this.service.putForeverThreads(entries, size, threads);
  }

  @GetMapping(TRADES_PATH + GET_FOREVER_THREADS + ENTRIES_PARAMETER + THREADS_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse getForeverThreads(@PathVariable int entries, @PathVariable int threads) {
    return this.service.getForeverThreads(entries, threads);
  }

  @DeleteMapping(TRADES_PATH + DESTROY_FOREVER_THREADS + ENTRIES_PARAMETER + THREADS_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse destroyForeverThreads(@PathVariable int entries, @PathVariable int threads) {
    return this.service.destroyForeverThreads(entries, threads);
  }

  @GetMapping(TRADES_PATH + QUERY_BY_CUSIP_FOREVER_THREADS + THREADS_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse queryByCusipForeverThreads(@PathVariable int threads) {
    return this.service.queryByCusipForeverThreads(threads);
  }

  @PostMapping(TRADES_PATH + FUNCTION_UPDATE_FOREVER_THREADS + ENTRIES_PARAMETER + THREADS_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse functionUpdateForeverThreads(@PathVariable int entries, @PathVariable int threads) {
    return this.service.functionUpdateForeverThreads(entries, threads);
  }
  @PostMapping(TRADES_PATH + START_SIMPLE_TEST + ENTRIES_PARAMETER + SIZE_PARAMETER)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse[] startSimpleTest(@PathVariable int entries, @PathVariable int size) {
    OperationResponse putResponse = this.service.putForeverThreads(entries, size, 1);
    OperationResponse getResponse = this.service.getForeverThreads(entries, 1);
    OperationResponse queryResponse = this.service.queryByCusipForeverThreads(1);
    OperationResponse functionResponse = this.service.functionUpdateForeverThreads(entries, 1);
    return new OperationResponse[] {putResponse, getResponse, queryResponse, functionResponse};
  }

  @PostMapping(TRADES_PATH + STOP_OPERATIONS)
  @ResponseStatus(HttpStatus.OK)
  public OperationResponse stopOperations() {
    return this.service.stopOperations();
  }
}
