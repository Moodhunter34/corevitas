package com.generic.retailer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public final class Cli implements AutoCloseable {

  public static Cli create(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
    requireNonNull(prompt);
    requireNonNull(reader);
    requireNonNull(writer);
    return new Cli(prompt, reader, writer, date);
  }

  public static Cli create(BufferedReader reader, BufferedWriter writer) {
    return new Cli(">", reader, writer, LocalDate.now());
  }

  private static final Predicate<String> WHITESPACE = Pattern.compile("^\\s{0,}$").asPredicate();

  private final String prompt;
  private final BufferedReader reader;
  private final BufferedWriter writer;
  private final LocalDate date;

  private Cli(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
    this.prompt = prompt;
    this.reader = reader;
    this.writer = writer;
    this.date = date;
  }

  private void prompt() throws IOException {
    writeLine(prompt);
  }

  private Optional<String> readLine() throws IOException {
    String line = reader.readLine();
    return line == null || WHITESPACE.test(line) ? Optional.empty() : Optional.of(line);
  }

  private void writeLine(String line) throws IOException {
    writer.write(line);
    writer.newLine();
    writer.flush();
  }

  public void run() throws IOException {
    writeLine("What would you like to buy?");
    prompt();
    Optional<String> line = readLine();
    Map<String, Integer> products = new HashMap<>();
    products.put("books", 0);
    products.put("cd", 0);
    products.put("dvd", 0);

    while (line.isPresent()) {
      String user_input = line.get();
      if ("exit".equalsIgnoreCase(line.get())) {
        break;
      }
      int value = products.get(user_input);
      products.put(user_input, value + 1);
      writeLine("Would you like anything else?");
      prompt();
      line = readLine();
    }
    System.out.println(products);
    double costs = 0;
    for (Map.Entry<String, Integer> entry : products.entrySet()) {
      String key = entry.getKey();
      Integer quantity = entry.getValue();
      if (key == "books") {
        int book_cost = quantity * 5;
        costs = costs + book_cost;
        System.out.println("cost of books=" + book_cost);

      } else if (key == "dvd") {
        int number_of_dvs = quantity / 2 + quantity % 2;
        double dvd_cost = (double) number_of_dvs * 15;
        if (quantity % 2 == 1 && date.getDayOfWeek() == DayOfWeek.THURSDAY) {
          dvd_cost = dvd_cost - 0.2 * 15;

        }
        costs = costs + dvd_cost;

        System.out.println("cost of dvd=" + dvd_cost);
      } else if (key == "cd") {
        int cd_cost = quantity * 10;

        costs = costs + cd_cost;
        System.out.println("cost of cd=" + cd_cost);
      }
    }

    writeLine(String.format("Thank you for visiting Generic Retailer, your total // is %s", costs));
  }

  @Override
  public void close() throws Exception {
    reader.close();
    writer.close();
  }

}
