package org.shelajev.primes.primes;

import org.springframework.web.bind.annotation.*;
// hello Nicolai
// why would I?
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController()
@RequestMapping("/primes")
public class PrimesController {

  private Random r = new Random();

  @GetMapping("/random/{upperbound}")
  @ResponseBody
  public List<Long> upperbound(@PathVariable int upperbound) {
    int to = 2 + r.nextInt(upperbound - 2);
    int from = 1 + r.nextInt(to - 1);

    return primeSequence(from, to);
  }

  private boolean isPrime(long n) {
    return LongStream.rangeClosed(2, (long) Math.sqrt(n))
      .allMatch(i -> n % i != 0);
  }

  private List<Long> primeSequence(long min, long max) {
    return LongStream.range(min, max)
      .filter(this::isPrime)
      .boxed()
      .collect(Collectors.toList());
  }
}