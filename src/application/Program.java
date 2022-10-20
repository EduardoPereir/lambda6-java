package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        String path = "/home/eduardo/Documents/x.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            List<Product> list = new ArrayList<>();

            while (line != null){

               String[] vet = line.split(",");

               list.add(new Product(vet[0], Double.parseDouble(vet[1])));
               line = br.readLine();
            }

            Double average = list.stream().map(x -> x.getPrice()).reduce(0.0, (sum, x) -> sum + x) / list.size();
            System.out.printf("Average price: %.2f", average);

            System.out.println();

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> names = list.stream()
                    .filter(x -> x.getPrice() < average)
                    .map(x -> x.getName())
                    .sorted(comp.reversed())
                    .toList();

            for (String s: names){

                System.out.println(s);
            }

        }
        catch (IOException e ){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
