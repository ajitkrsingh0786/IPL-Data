package com.company;

import implement.MatchesPlayedPerYear;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the question number to see the result");
        int i = sc.nextInt();
         switch (i) {
             case 1:
                 MatchesPlayedPerYear match_per_year = new MatchesPlayedPerYear();
                 match_per_year.matchPlayedPerYear();
                 break;
             default:
                 System.out.println("Question number is wrong");
         }
    }
}
