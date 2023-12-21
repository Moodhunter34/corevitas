package com.generic.retailer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public final class Cli implements AutoCloseable {

    Receipt receipt = new Receipt();

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
        writeLine("What would you like to buy? Please enter a product (book, cd, dvd) or type 'exit' to finish.\"");
        prompt();

        Trolley shoppingCart = new Trolley();
        Optional<String> productLine = readLine();

        while (productLine.isPresent()) {
            if ("exit".equalsIgnoreCase(productLine.get())) {
                break;
            }

            String product = productLine.get().trim().toLowerCase();

            if (!isValidProduct(product)) {
                writeLine("Invalid product. Please enter a valid product (e.g., book, cd, dvd) or type 'exit' to finish.");
            } else {
                writeLine("How many would you like?");
                prompt();
                Optional<String> quantityLine = readLine();

                if (quantityLine.isPresent()) {
                    try {
                        int quantity = Integer.parseInt(quantityLine.get().trim());
                        shoppingCart.ordersProducts(product, quantity);
                        receipt.addItem(product, quantity);
                    } catch (NumberFormatException e) {
                        writeLine("Invalid quantity. Please enter a valid number.");
                    }
                } else {
                    break;  // User entered nothing for quantity, exit the loop
                }
            }

            writeLine("Would you like anything else?");
            prompt();
            productLine = readLine();
        }

        // Calculate and display the total
        double total = shoppingCart.calculateTotal();
        receipt.setTotal(total);
        writeLine(String.format("Thank you for visiting Generic Retailer, your total is £%.2f", total));

        writeLine(receipt.printReceipt());
    }

    private boolean isValidProduct(String product) {
        // Add more products as needed
        return "book".equalsIgnoreCase(product) || "cd".equalsIgnoreCase(product) || "dvd".equalsIgnoreCase(product);
    }


    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }

}
