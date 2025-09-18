package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller
public class ReproController {

  @Get
  HttpResponse<String> getLargeResponseBody(@QueryValue int size) {
    return HttpResponse.ok("A".repeat(size));
  }

}
