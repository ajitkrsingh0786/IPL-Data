package com.company;

import implement.ExtraRunsConceded;
import implement.MatchesPlayedPerYear;
import implement.MatchesWonByEachTeam;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to see the total matches played per year\n" +
                "Enter 2 to see the total number of matches won by each\n" +
                "Enter 3 to see the total extra runs conceded by each team in 2016");

        int i = sc.nextInt();
         switch (i) {
             case 1:
                 MatchesPlayedPerYear match_per_year = new MatchesPlayedPerYear();
                 match_per_year.matchPlayedPerYear();
                 break;
             case 2:
                 MatchesWonByEachTeam match_played = new MatchesWonByEachTeam();
                 match_played.matchesWonByEachTeam();
                 break;
             case 3:
                 ExtraRunsConceded extra_runs = new ExtraRunsConceded();
                 extra_runs.extraRunConcededByEachTeam();
                 break;
             default:
                 System.out.println("Question number is wrong\n Enter correct the number");
         }
    }
}
