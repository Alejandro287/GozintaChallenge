package main;

import algorithm.GozintaChains;
import java.util.Scanner;


public class Main {

    public static void main (String[] args) {
        while (true) {
            System.out.println("Enter the Gozinta chain number:");
            Scanner scanner = new Scanner(System.in);
            long n = scanner.nextLong();

            long startTime = System.currentTimeMillis();

            GozintaChains gozintaChains = new GozintaChains(n);
            System.out.println(gozintaChains.getGozintaChain().toString());
            System.out.println("g(" + n + ") = " + gozintaChains.getNumberOfChains());

            long endTime = System.currentTimeMillis();

            long duration = (endTime - startTime);
            System.out.println("Time: " + duration);
        }
    }
}
