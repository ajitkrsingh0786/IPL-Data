package com.company;

import implement.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to see the total matches played per year\n" +
                "Enter 2 to see the total number of matches won by each\n" +
                "Enter 3 to see the total extra runs conceded by each team in 2016\n" +
                "Enter 4 to see the top 10 economical bowlers in 2015\n" +
                "Enter 5 to see the top 10 high scorers batsman in the given year");
        
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
             case 4:
                 EconomicalBowlersOf2015 economical_bowlers = new EconomicalBowlersOf2015();
                 economical_bowlers.allEconomicalBowlersOf2015();
                 break;
             case 5:
                 System.out.println("Enter the year between 2008-2017");
                 int year = sc.nextInt();
                 if(year>=2008 && year<=2017) {
                     HighestIndividualScoresEachYear scores = new HighestIndividualScoresEachYear();
                     scores.highestIndividualScoresEachYear(year);
                 }else{
                     System.out.println("Enter the year between 2008-2017 only");
                 }
                 break;
             default:
                 System.out.println("Question number is wrong\n Enter correct the number");
         }
    }
}
