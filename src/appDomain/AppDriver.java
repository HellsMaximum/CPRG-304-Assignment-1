package appDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Locale;

import products.*;
import utilities.*;

/**
 * Application driver for the product sorting assignment.
 * Reads product data, selects a comparator and sorting algorithm,
 * measures sorting time, and displays the sorted products.
 */
public class AppDriver {

    public static void main(String[] args) {

        String fileName = null;
        String compareType = null;
        String sortType = null;

        // Process command-line arguments in any order.
        for (String arg : args) {

            String lowerArg = arg.toLowerCase(Locale.ROOT);

            if (lowerArg.startsWith("-f")) {

                if (fileName != null) {
                    System.out.println("Error: Duplicate filename argument.");
                    return;
                }

                fileName = arg.substring(2);

                if (fileName.isEmpty()) {
                    System.out.println("Error: Filename cannot be empty.");
                    return;
                }

            } else if (lowerArg.startsWith("-t")) {

                if (compareType != null) {
                    System.out.println("Error: Duplicate comparison argument.");
                    return;
                }

                if (lowerArg.length() != 3) {
                    System.out.println("Error: Invalid comparison argument: " + arg);
                    printUsage();
                    return;
                }

                char type = lowerArg.charAt(2);

                if (type == 'o' || type == 'u' || type == 'd') {
                    compareType = String.valueOf(type);
                } else {
                    System.out.println("Error: Invalid comparison type: " + arg);
                    printUsage();
                    return;
                }

            } else if (lowerArg.startsWith("-s")) {

                if (sortType != null) {
                    System.out.println("Error: Duplicate sorting argument.");
                    return;
                }

                if (lowerArg.length() != 3) {
                    System.out.println("Error: Invalid sorting argument: " + arg);
                    printUsage();
                    return;
                }

                char type = lowerArg.charAt(2);

                if (type == 'b' || type == 's' || type == 'i'
                        || type == 'm' || type == 'q' || type == 'z') {
                    sortType = String.valueOf(type);
                } else {
                    System.out.println("Error: Invalid sorting type: " + arg);
                    printUsage();
                    return;
                }

            } else {
                System.out.println("Error: Invalid argument: " + arg);
                printUsage();
                return;
            }
        }

        // Ensure that all required arguments were supplied.
        if (fileName == null || compareType == null || sortType == null) {
            System.out.println("Error: Missing required arguments.");
            printUsage();
            return;
        }

        Product[] products;
        int count;

        // Read the product count and create the array.
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String firstLine = reader.readLine();

            if (firstLine == null || firstLine.trim().isEmpty()) {
                System.out.println(
                        "Error: File is empty or missing the product count.");
                return;
            }

            try {
                count = Integer.parseInt(firstLine.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid product count.");
                return;
            }

            if (count < 0) {
                System.out.println("Error: Product count cannot be negative.");
                return;
            }

            products = new Product[count];

            // Read and create each product.
            for (int i = 0; i < count; i++) {

                String line = reader.readLine();

                if (line == null) {
                    System.out.println(
                            "Error: Expected " + count
                            + " products, but found only " + i + ".");
                    return;
                }

                if (line.trim().isEmpty()) {
                    System.out.println(
                            "Error: Blank product record at record "
                            + (i + 1) + ".");
                    return;
                }

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 3) {
                    System.out.println(
                            "Error: Malformed product record at record "
                            + (i + 1) + ".");
                    return;
                }

                String type = parts[0];
                double originalPrice;
                double updatedPrice;

                try {
                    originalPrice = Double.parseDouble(parts[1]);
                    updatedPrice = Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Error: Invalid price at record "
                            + (i + 1) + ".");
                    return;
                }

                Product product =
                        createProduct(type, originalPrice, updatedPrice);

                if (product == null) {
                    System.out.println(
                            "Error: Unknown product type: " + type);
                    return;
                }

                products[i] = product;
            }

            // Reject unexpected additional product records.
            String extraLine;

            while ((extraLine = reader.readLine()) != null) {
                if (!extraLine.trim().isEmpty()) {
                    System.out.println(
                            "Error: File contains more records than declared.");
                    return;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Select the comparator.
        Comparator<Product> comp;

        switch (compareType) {
            case "o":
                comp = new OriginalPriceComparator();
                break;
            case "u":
                comp = new UpdatedPriceComparator();
                break;
            case "d":
                comp = Comparator.naturalOrder();
                break;
            default:
                System.out.println("Error: Invalid comparison type.");
                return;
        }

        // Measure only the sorting operation.
        long startTime = System.nanoTime();

        switch (sortType) {
            case "b":
                BubbleSort.sort(products, comp);
                break;
            case "s":
                SelectionSort.sort(products, comp);
                break;
            case "i":
                InsertionSort.sort(products, comp);
                break;
            case "m":
                MergeSort.sort(products, comp);
                break;
            case "q":
                QuickSort.sort(products, comp);
                break;
            case "z":
                GnomeSort.sort(products, comp);
                break;
            default:
                System.out.println("Error: Invalid sorting type.");
                return;
        }

        long endTime = System.nanoTime();
        long elapsedMs = (endTime - startTime) / 1_000_000;

        // Display the sorted products and runtime.
        printProducts(products, compareType);

        System.out.println(
                getSortName(sortType) + " run time was: "
                + elapsedMs + " milliseconds");
    }

    /**
     * Creates the correct product subclass.
     *
     * @param type the product type
     * @param originalPrice the original price
     * @param updatedPrice the updated price
     * @return the created product, or null for an unknown type
     */
    private static Product createProduct(
            String type, double originalPrice, double updatedPrice) {

        switch (type.toLowerCase(Locale.ROOT)) {
            case "jeans":
                return new Jeans(originalPrice, updatedPrice);
            case "tshirts":
                return new TShirts(originalPrice, updatedPrice);
            case "skirts":
                return new Skirts(originalPrice, updatedPrice);
            case "dresses":
                return new Dresses(originalPrice, updatedPrice);
            case "shorts":
                return new Shorts(originalPrice, updatedPrice);
            case "jackets":
                return new Jackets(originalPrice, updatedPrice);
            case "shoes":
                return new Shoes(originalPrice, updatedPrice);
            default:
                return null;
        }
    }

    /**
     * Displays the sorted products according to the assignment's rules.
     *
     * @param products the sorted products
     * @param compareType the selected comparison type
     */
    private static void printProducts(
            Product[] products, String compareType) {

        if (products.length == 0) {
            System.out.println("No products to display.");
            return;
        }

        // Display every product when there are ten or fewer.
        if (products.length <= 10) {
            for (int i = 0; i < products.length; i++) {
                printProduct(
                        (i + 1) + ordinalSuffix(i + 1) + " element:",
                        products[i], compareType);
            }
            return;
        }

        // Use every hundredth or thousandth element as required.
        int interval = products.length <= 1000 ? 100 : 1000;

        printProduct("First element is:", products[0], compareType);

        for (int i = interval - 1; i < products.length - 1; i += interval) {
            printProduct(
                    (i + 1) + ordinalSuffix(i + 1) + " element:",
                    products[i], compareType);
        }

        printProduct(
                "Last element is:",
                products[products.length - 1], compareType);
    }

    /**
     * Displays one product with its position and selected value.
     *
     * @param position the label for the product's position
     * @param product the product to display
     * @param compareType the selected comparison type
     */
    private static void printProduct(
            String position, Product product, String compareType) {

        String valueLabel;
        double value;

        switch (compareType) {
            case "o":
                valueLabel = "Original Price: ";
                value = product.getOriginalPrice();
                break;
            case "u":
                valueLabel = "Updated Price: ";
                value = product.getUpdatedPrice();
                break;
            default:
                valueLabel = "Real Difference: ";
                value = product.calcRealDiff();
                break;
        }

        System.out.printf(
                "%-24s %-25s %s%.2f%n",
                position,
                product.getClass().getName(),
                valueLabel,
                value);
    }

    /**
     * Returns the ordinal suffix for a number.
     *
     * @param number the number
     * @return the ordinal suffix
     */
    private static String ordinalSuffix(int number) {

        int lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
            return "th";
        }

        switch (number % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * Returns the display name of the selected sorting algorithm.
     *
     * @param sortType the selected sorting type
     * @return the sorting algorithm name
     */
    private static String getSortName(String sortType) {

        switch (sortType) {
            case "b":
                return "Bubble Sort";
            case "s":
                return "Selection Sort";
            case "i":
                return "Insertion Sort";
            case "m":
                return "Merge Sort";
            case "q":
                return "Quick Sort";
            case "z":
                return "Gnome Sort";
            default:
                return "Unknown Sort";
        }
    }

    /**
     * Displays the command-line argument format.
     */
    private static void printUsage() {

        System.out.println(
                "Usage: java -jar Sort.jar "
                + "-f<filename> -t<o|u|d> -s<b|s|i|m|q|z>");

        System.out.println("  -f<filename>  Input file");
        System.out.println("  -to           Sort by original price");
        System.out.println("  -tu           Sort by updated price");
        System.out.println("  -td           Sort by real price difference");
        System.out.println("  -sb           Bubble Sort");
        System.out.println("  -ss           Selection Sort");
        System.out.println("  -si           Insertion Sort");
        System.out.println("  -sm           Merge Sort");
        System.out.println("  -sq           Quick Sort");
        System.out.println("  -sz           Gnome Sort");
    }
}