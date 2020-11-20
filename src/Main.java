import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUN = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_Match =13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 =16;
    public static final int UMPIRE3 = 17;

    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEG_BYE_RUNS = 12;
    public static final int NO_BALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS =15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;

    public static void main(String[] args) throws IOException {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();

        findTheNumberOfMatchesPlayedEachYear(matches);
        findMatchesWonByEachTeam(matches);
        findExtraRunConcededByEachTeamIn2016(matches, deliveries);
        findEconomicalBowlersOf2015(matches, deliveries);
        findHighestIndividualScoresIn2014(matches, deliveries);
    }

    private static void findTheNumberOfMatchesPlayedEachYear(List<Match> matches){
        HashMap<String, Integer> matchPlayedPerYear = new HashMap<String ,Integer>();
        ArrayList<Integer> years = new ArrayList<>();
        for(Match match:matches){
            String season =  match.getSeason();
            if(matchPlayedPerYear.containsKey(season)){
                int currValue = matchPlayedPerYear.get(season)+1;
                matchPlayedPerYear.replace(season, currValue);
            }else{
                matchPlayedPerYear.put(season, 1);
                years.add(Integer.parseInt(season));
            }
        }
        Collections.sort(years);
        for(int i=0; i< years.size();i++){
            System.out.println("Number of matches played in year "+
                    years.get(i)+" :"+
                    matchPlayedPerYear.get(""+years.get(i)) );
        }
    }

    private static void findMatchesWonByEachTeam(List<Match> matches){
        TreeMap<String, Integer> matchWonByEachTeams = new TreeMap<String, Integer>();
        for(Match match:matches){
             String winner = match.getWinner();
            if(matchWonByEachTeams.containsKey(winner)){
                int currValue = matchWonByEachTeams.get(winner)+1;
                matchWonByEachTeams.replace(winner, currValue);
            }else{
                if(winner != null && !winner.equals("")) {
                    matchWonByEachTeams.put(winner, 1);
                }
            }
        }
        System.out.println("");
        for (String team: matchWonByEachTeams.keySet()){
            int matchWon = matchWonByEachTeams.get(team);
            System.out.println("Total number of  matches won by "+ team + " is : " + matchWon);
        }
    }

    private static void findExtraRunConcededByEachTeamIn2016(List<Match> matches, List<Delivery>deliveries){
        TreeMap<String, Integer> extraRunConcededIn2016 = new TreeMap<String ,Integer>();
        HashMap<String, Integer> matchIdList = new HashMap<String ,Integer>();
        for(Match match:matches){
            String matchId = match.getMatchId();
            String season = match.getSeason();
            if(!matchIdList.containsKey(matchId) && season.equals("2016")){
                matchIdList.put(matchId, Integer.parseInt(season));
            }
        }
        for(Delivery delivery:deliveries){
            String matchId = delivery.getMatchId();
            if(matchIdList.containsKey(matchId)){
                int extraRuns = Integer.parseInt(delivery.getExtraRuns());
                String bowling_team =  delivery.getBowlingTeam();
                if(extraRunConcededIn2016.containsKey(bowling_team)){
                    int total_extra_runs = extraRunConcededIn2016.get(bowling_team)+extraRuns;
                    extraRunConcededIn2016.replace(bowling_team, total_extra_runs);
                }else {
                    extraRunConcededIn2016.put(bowling_team, extraRuns);
                }
            }
        }
        System.out.println("");
        for (String team: extraRunConcededIn2016.keySet()){
            int extraRunConceded = extraRunConcededIn2016.get(team);
            System.out.println("Total extra runs conceded by "+
                    team + " in 2016 is : " + extraRunConceded);
        }
    }

    private static void findEconomicalBowlersOf2015(List<Match> matches, List<Delivery> deliveries){
        HashMap<String, HashMap<String, Integer>> bowlersTotalBallsRunsIn2015 = new HashMap<String ,HashMap<String, Integer>>();
        HashMap<String, Float> bowlersEconomyIn2015 = new HashMap<String ,Float>();
        HashMap<String, Integer> matchIdList = new HashMap<String ,Integer>();
        for(Match match : matches){
             String matchId = match.getMatchId();
             String season = match.getSeason();
            if( !matchIdList.containsKey(matchId) && season.equals("2015") ){
                matchIdList.put(matchId, Integer.parseInt(matchId));
            }
        }
        for( Delivery delivery : deliveries){
            String matchId = delivery.getMatchId();
            String isSuperOver = delivery.getIsSuperOver();
            if(matchIdList.containsKey(matchId) && isSuperOver.equals("0")){
                int wideRuns = Integer.parseInt(delivery.getWideRuns());
                int noBallRuns = Integer.parseInt(delivery.getNoBallRuns());
                int batsManRuns = Integer.parseInt(delivery.getBatsmanRuns());
                String bowlerName = delivery.getBowler();
                HashMap<String, Integer> ballAndRuns = new HashMap<String ,Integer>();
                if(bowlersTotalBallsRunsIn2015.containsKey(bowlerName)){
                    int preBallCount = bowlersTotalBallsRunsIn2015.get(bowlerName).get("balls");
                    int preRunCount = bowlersTotalBallsRunsIn2015.get(bowlerName).get("runs");
                    if(wideRuns==0 && noBallRuns==0){
                        ballAndRuns.put("balls", preBallCount + 1);
                        ballAndRuns.put("runs", preRunCount + batsManRuns);
                    }else if(noBallRuns!=0){
                        ballAndRuns.put("balls", preBallCount + 0);
                        ballAndRuns.put("runs", preRunCount + batsManRuns + 1);
                    }else {
                        ballAndRuns.put("balls", preBallCount + 0);
                        ballAndRuns.put("runs", preRunCount + 1);
                    }
                    bowlersTotalBallsRunsIn2015.replace(bowlerName, ballAndRuns);

                }else {
                    if(wideRuns == 0 && noBallRuns == 0){
                        ballAndRuns.put("balls", 1);
                        ballAndRuns.put("runs", batsManRuns);
                    }else if(noBallRuns!=0){
                        ballAndRuns.put("balls", 0);
                        ballAndRuns.put("runs", batsManRuns + 1);
                    }else {
                        ballAndRuns.put("balls", 0);
                        ballAndRuns.put("runs", 1);
                    }
                    bowlersTotalBallsRunsIn2015.put(bowlerName, ballAndRuns);
                }
            }
        }
        for (String bowler: bowlersTotalBallsRunsIn2015.keySet()){
            float bowlerTotalBall = bowlersTotalBallsRunsIn2015.get(bowler).get("balls");
            float bowlerTotalRuns = bowlersTotalBallsRunsIn2015.get(bowler).get("runs");
            float bowlerTotalOver = bowlerTotalBall/6;
            float bowlerEconomy = bowlerTotalRuns/bowlerTotalOver;
            bowlersEconomyIn2015.put(bowler, bowlerEconomy);
        }
        ArrayList<String> economyList = new ArrayList<String>();
        for (String bowler: bowlersEconomyIn2015.keySet()){
            String  value2 = bowler+","+ bowlersEconomyIn2015.get(bowler);
            economyList.add(value2);
        }
        System.out.print("\nList of top 10 economical bowlers in 2015\n");
        for (int i=0;i<10;i++){
            for(int j=0;j<economyList.size()-1;j++){
                float firstBowlerEconomy = Float.parseFloat(economyList.get(j).split(",")[1]);
                float secondBowlerEconomy = Float.parseFloat(economyList.get(j+1).split(",")[1]);
                String currentString1 = economyList.get(j);
                String currentString2 = economyList.get(j+1);
                if(firstBowlerEconomy < secondBowlerEconomy){
                    economyList.set(j,currentString2 );
                    economyList.set(j+1,currentString1 );
                }
            }
            System.out.println(economyList.get(economyList.size()-1-i).split(",")[0]+
                    " : "+ economyList.get(economyList.size()-1-i).split(",")[1]);
        }
    }

   private static void findHighestIndividualScoresIn2014(List<Match> matches, List<Delivery> deliveries){
       HashMap<String, Integer> batsmanTotalRuns = new HashMap<String , Integer>();
       HashMap<String, Integer> matchIdList = new HashMap<String ,Integer>();
       for( Match match : matches){
           String matchId = match.getMatchId();
           String season = match.getSeason();
           if(!matchIdList.containsKey(matchId) && season.equals(""+2014)){
               matchIdList.put(matchId, Integer.parseInt(matchId));
           }
       }
        for(Delivery delivery : deliveries){
           String matchId = delivery.getMatchId();
           String isSuperOver =  delivery.getIsSuperOver();
           String batsmanName = delivery.getBatsman();
           int batsmanCurrentBallRuns = Integer.parseInt( delivery.getBatsmanRuns());
           if(matchIdList.containsKey(matchId) && isSuperOver.equals("0")){
               if(batsmanTotalRuns.containsKey(batsmanName)){
                   int currentTotalRuns = batsmanTotalRuns.get(batsmanName) + batsmanCurrentBallRuns;
                   batsmanTotalRuns.replace(batsmanName, currentTotalRuns);
               }else{
                   batsmanTotalRuns.put(batsmanName, batsmanCurrentBallRuns);
               }
           }
       }
       ArrayList<String> batsmanList = new ArrayList<String>();
       for (String batsman: batsmanTotalRuns.keySet()){
           String  value2 = batsman+","+ batsmanTotalRuns.get(batsman);
           batsmanList.add(value2);
       }
       System.out.println("\nList of top 10 high scorers batsman in 2014");
       for (int i=0;i<10;i++){
           for(int j=0;j<batsmanList.size()-1;j++){
               float firstBatsmanRuns = Float.parseFloat(batsmanList.get(j).split(",")[1]);
               float secondBatsManRuns = Float.parseFloat(batsmanList.get(j+1).split(",")[1]);
               String currentString1 = batsmanList.get(j);
               String currentString2 = batsmanList.get(j+1);
               if(firstBatsmanRuns > secondBatsManRuns){
                   batsmanList.set(j, currentString2 );
                   batsmanList.set(j+1, currentString1 );
               }
           }
           System.out.println(batsmanList.get(batsmanList.size()-1-i).split(",")[0]+
                   " : "+ batsmanList.get(batsmanList.size()-1-i).split(",")[1]);
       }
   }

    private static List<Match> getMatchesData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String filePath = "./resources/matches.csv";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        br.readLine();
        String eachRow ="";
        while((eachRow = br.readLine()) != null) {
            String row[] = eachRow.split(",");
            Match match = new Match();
            match.setMatchId(""+row[MATCH_ID]);
            match.setSeason(""+row[SEASON]);
            match.setCity(""+row[CITY]);
            match.setDate(""+row[DATE]);
            match.setTeam1(""+row[TEAM1]);
            match.setTeam2(""+row[TEAM2]);
            match.setTossWinner(""+row[TOSS_WINNER]);
            match.setToss_decision(""+row[TOSS_DECISION]);
            match.setResult(""+row[MATCH_RESULT]);
            match.setDlApplied(""+row[DL_APPLIED]);
            match.setWinner(""+row[WINNER]);
            match.setWinByRuns(""+row[WIN_BY_RUN]);
            match.setWinByWickets(""+row[WIN_BY_WICKETS]);
            match.setPlayer_of_match(""+row[PLAYER_OF_Match]);
            match.setVenue(""+row[VENUE]);
            matches.add(match);
        }
       return matches;
    }

    private static List<Delivery> getDeliveriesData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String filePath1 = "./resources/deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(filePath1));
        String eachRow ="";
        br.readLine();
        while((eachRow = br.readLine()) != null){
            String row[] = eachRow.split(",");
            Delivery delivery = new Delivery();
            delivery.setMatchId(row[MATCH_ID]);
            delivery.setInning(row[INNING]);
            delivery.setBattingTeam(row[BATTING_TEAM]);
            delivery.setBowlingTeam(row[BOWLING_TEAM]);
            delivery.setOver(row[OVER]);
            delivery.setBall(row[BALL]);
            delivery.setBatsman(row[BATSMAN]);
            delivery.setNonStriker(row[NON_STRIKER]);
            delivery.setBowler(row[BOWLER]);
            delivery.setIsSuperOver(row[IS_SUPPER_OVER]);
            delivery.setWideRuns(row[WIDE_RUNS]);
            delivery.setByeRuns(row[BYE_RUNS]);
            delivery.setLegByeRuns(row[LEG_BYE_RUNS]);
            delivery.setNoBallRuns(row[NO_BALL_RUNS]);
            delivery.setPenalty_runs(row[PENALTY_RUNS]);
            delivery.setBatsmanRuns(row[BATSMAN_RUNS]);
            delivery.setExtraRuns(row[EXTRA_RUNS]);
            delivery.setTotalRuns(row[TOTAL_RUNS]);
            deliveries.add(delivery);
        }
        return deliveries;
    }
}
