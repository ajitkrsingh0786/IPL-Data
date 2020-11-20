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
        HashMap<String, Integer> match_played_per_year = new HashMap<String ,Integer>();
        ArrayList<Integer> years = new ArrayList<>();
        for(Match match:matches){
            String season =  match.getSeason();
            if(match_played_per_year.containsKey(season)){
                int curr_value = match_played_per_year.get(season)+1;
                match_played_per_year.replace(season, curr_value);
            }else{
                match_played_per_year.put(season, 1);
               years.add(Integer.parseInt(season));
            }
        }
        Collections.sort(years);
        for(int i=0; i< years.size();i++){
            System.out.println("Number of matches played in year "+
                    years.get(i)+" :"+
                    match_played_per_year.get(""+years.get(i)) );
        }
    }

    private static void findMatchesWonByEachTeam(List<Match> matches){
        TreeMap<String, Integer> match_won_by_each_teams = new TreeMap<String, Integer>();
        for(Match match:matches){
             String winner = match.getWinner();
            if(match_won_by_each_teams.containsKey(winner)){
                int curr_value = match_won_by_each_teams.get(winner)+1;
                match_won_by_each_teams.replace(winner, curr_value);
            }else{
                if(winner != null && !winner.equals("")) {
                    match_won_by_each_teams.put(winner, 1);
                }
            }
        }
        System.out.println("");
        for (String team: match_won_by_each_teams.keySet()){
            int match_won = match_won_by_each_teams.get(team);
            System.out.println("Total number of  matches won by "+ team + " is : " + match_won);
        }
    }

    private static void findExtraRunConcededByEachTeamIn2016(List<Match> matches, List<Delivery>deliveries){
        TreeMap<String, Integer> extra_run_conceded_in_2016 = new TreeMap<String ,Integer>();
        HashMap<String, Integer> match_id = new HashMap<String ,Integer>();
        for(Match match:matches){
            String matchId = match.getMatchId();
            String season = match.getSeason();
            if(!match_id.containsKey(matchId) && season.equals("2016")){
                match_id.put(matchId, Integer.parseInt(season));
            }
        }
        for(Delivery delivery:deliveries){
            String matchId = delivery.getMatch_id();
            if(match_id.containsKey(matchId)){
                int extra_runs = Integer.parseInt(delivery.getExtra_runs());
                String bowling_team =  delivery.getBowling_team();
                if(extra_run_conceded_in_2016.containsKey(bowling_team)){
                    int total_extra_runs = extra_run_conceded_in_2016.get(bowling_team)+extra_runs;
                    extra_run_conceded_in_2016.replace(bowling_team, total_extra_runs);
                }else {
                    extra_run_conceded_in_2016.put(bowling_team, extra_runs);
                }
            }
        }
        System.out.println("");
        for (String team: extra_run_conceded_in_2016.keySet()){
            int extra_run_conceded = extra_run_conceded_in_2016.get(team);
            System.out.println("Total extra runs conceded by "+
                    team + " in 2016 is : " + extra_run_conceded);
        }
    }

    private static void findEconomicalBowlersOf2015(List<Match> matches, List<Delivery> deliveries){
        HashMap<String, HashMap<String, Integer>> bowlers_total_balls_runs_in_2015 = new HashMap<String ,HashMap<String, Integer>>();
        HashMap<String, Float> bowlers_economy_in_2015 = new HashMap<String ,Float>();
        HashMap<String, Integer> match_id = new HashMap<String ,Integer>();
        for(Match match : matches){
             String matchId = match.getMatchId();
             String season = match.getSeason();
            if( !match_id.containsKey(matchId) && season.equals("2015") ){
                match_id.put(matchId, Integer.parseInt(matchId));
            }
        }
        for( Delivery delivery : deliveries){
            String matchId = delivery.getMatch_id();
            String is_super_over = delivery.getIs_super_over();
            if(match_id.containsKey(matchId) && is_super_over.equals("0")){
                int wide_runs = Integer.parseInt(delivery.getWide_runs());
                int no_ball_runs = Integer.parseInt(delivery.getNo_ball_runs());
                int batsman_runs = Integer.parseInt(delivery.getBatsman_runs());
                String bowler_name = delivery.getBowler();
                HashMap<String, Integer> ball_and_runs = new HashMap<String ,Integer>();
                if(bowlers_total_balls_runs_in_2015.containsKey(bowler_name)){
                    int pre_ball_count = bowlers_total_balls_runs_in_2015.get(bowler_name).get("balls");
                    int pre_run_count = bowlers_total_balls_runs_in_2015.get(bowler_name).get("runs");
                    if(wide_runs==0 && no_ball_runs==0){
                        ball_and_runs.put("balls", pre_ball_count + 1);
                        ball_and_runs.put("runs", pre_run_count+batsman_runs);
                    }else if(no_ball_runs!=0){
                        ball_and_runs.put("balls", pre_ball_count + 0);
                        ball_and_runs.put("runs", pre_run_count+batsman_runs+1);
                    }else {
                        ball_and_runs.put("balls", pre_ball_count + 0);
                        ball_and_runs.put("runs", pre_run_count + 1);
                    }
                    bowlers_total_balls_runs_in_2015.replace(bowler_name, ball_and_runs);

                }else {
                    if(wide_runs == 0 && no_ball_runs == 0){
                        ball_and_runs.put("balls", 1);
                        ball_and_runs.put("runs",batsman_runs);
                    }else if(no_ball_runs!=0){
                        ball_and_runs.put("balls", 0);
                        ball_and_runs.put("runs", batsman_runs+1);
                    }else {
                        ball_and_runs.put("balls", 0);
                        ball_and_runs.put("runs", 1);
                    }
                    bowlers_total_balls_runs_in_2015.put(bowler_name, ball_and_runs);
                }
            }
        }
        for (String bowler: bowlers_total_balls_runs_in_2015.keySet()){
            float bowler_total_ball = bowlers_total_balls_runs_in_2015.get(bowler).get("balls");
            float bowler_total_runs = bowlers_total_balls_runs_in_2015.get(bowler).get("runs");
            float bowler_total_over = bowler_total_ball/6;
            float bowler_economy = bowler_total_runs/bowler_total_over;
            bowlers_economy_in_2015.put(bowler, bowler_economy);
        }
        ArrayList<String> economy_list = new ArrayList<String>();
        for (String bowler: bowlers_economy_in_2015.keySet()){
            String  value2 = bowler+","+bowlers_economy_in_2015.get(bowler);
            economy_list.add(value2);
        }
        System.out.print("\nList of top 10 economical bowlers in 2015\n");
        for (int i=0;i<10;i++){
            for(int j=0;j<economy_list.size()-1;j++){
                float first_bowler_economy = Float.parseFloat(economy_list.get(j).split(",")[1]);
                float second_bowler_economy = Float.parseFloat(economy_list.get(j+1).split(",")[1]);
                String current_string1 = economy_list.get(j);
                String current_string2 = economy_list.get(j+1);
                if(first_bowler_economy < second_bowler_economy){
                    economy_list.set(j,current_string2 );
                    economy_list.set(j+1,current_string1 );
                }
            }
            System.out.println(economy_list.get(economy_list.size()-1-i).split(",")[0]+
                    " : "+ economy_list.get(economy_list.size()-1-i).split(",")[1]);
        }
    }

   private static void findHighestIndividualScoresIn2014(List<Match> matches, List<Delivery> deliveries){
       HashMap<String, Integer> batsman_total_runs = new HashMap<String , Integer>();
       HashMap<String, Integer> match_id_list = new HashMap<String ,Integer>();
       for( Match match : matches){
           String matchId = match.getMatchId();
           String season = match.getSeason();
           if(!match_id_list.containsKey(matchId) && season.equals(""+2014)){
               match_id_list.put(matchId, Integer.parseInt(matchId));
           }
       }
        for(Delivery delivery : deliveries){
           String matchId = delivery.getMatch_id();
           String is_super_over =  delivery.getIs_super_over();
           String batsman_name = delivery.getBatsman();
           int batsman_current_ball_runs = Integer.parseInt( delivery.getBatsman_runs());
           if(match_id_list.containsKey(matchId) && is_super_over.equals("0")){
               if(batsman_total_runs.containsKey(batsman_name)){
                   int current_total_runs = batsman_total_runs.get(batsman_name) + batsman_current_ball_runs;
                   batsman_total_runs.replace(batsman_name, current_total_runs);
               }else{
                   batsman_total_runs.put(batsman_name, batsman_current_ball_runs);
               }
           }
       }
       ArrayList<String> batsman_list = new ArrayList<String>();
       for (String batsman: batsman_total_runs.keySet()){
           String  value2 = batsman+","+batsman_total_runs.get(batsman);
           batsman_list.add(value2);
       }
       System.out.println("\nList of top 10 high scorers batsman in 2014");
       for (int i=0;i<10;i++){
           for(int j=0;j<batsman_list.size()-1;j++){
               float v1 = Float.parseFloat(batsman_list.get(j).split(",")[1]);
               float v2 = Float.parseFloat(batsman_list.get(j+1).split(",")[1]);
               String current_string1 = batsman_list.get(j);
               String current_string2 = batsman_list.get(j+1);
               if(v1>v2){
                   batsman_list.set(j,current_string2 );
                   batsman_list.set(j+1,current_string1 );
               }
           }
           System.out.println(batsman_list.get(batsman_list.size()-1-i).split(",")[0]+
                   " : "+ batsman_list.get(batsman_list.size()-1-i).split(",")[1]);
       }
   }

    private static List<Match> getMatchesData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String file_path = "./resources/matches.csv";
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        br.readLine();
        String each_row ="";
        while((each_row = br.readLine()) != null) {
            String row[] = each_row.split(",");
            Match match = new Match();
            match.setMatchId(""+row[MATCH_ID]);
            match.setSeason(""+row[SEASON]);
            match.setCity(""+row[CITY]);
            match.setDate(""+row[DATE]);
            match.setTeam1(""+row[TEAM1]);
            match.setTeam2(""+row[TEAM2]);
            match.setToss_winner(""+row[TOSS_WINNER]);
            match.setToss_decision(""+row[TOSS_DECISION]);
            match.setResult(""+row[MATCH_RESULT]);
            match.setDl_applied(""+row[DL_APPLIED]);
            match.setWinner(""+row[WINNER]);
            match.setWin_by_runs(""+row[WIN_BY_RUN]);
            match.setWin_by_wickets(""+row[WIN_BY_WICKETS]);
            match.setPlayer_of_match(""+row[PLAYER_OF_Match]);
            match.setVenue(""+row[VENUE]);
            matches.add(match);
        }
       return matches;
    }

    private static List<Delivery> getDeliveriesData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String file_path1 = "./resources/deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(file_path1));
        String each_row ="";
        br.readLine();
        while((each_row = br.readLine()) != null){
            String row[] = each_row.split(",");
            Delivery delivery = new Delivery();
            delivery.setMatch_id(row[MATCH_ID]);
            delivery.setInning(row[INNING]);
            delivery.setBatting_team(row[BATTING_TEAM]);
            delivery.setBowling_team(row[BOWLING_TEAM]);
            delivery.setOver(row[OVER]);
            delivery.setBall(row[BALL]);
            delivery.setBatsman(row[BATSMAN]);
            delivery.setNon_striker(row[NON_STRIKER]);
            delivery.setBowler(row[BOWLER]);
            delivery.setIs_super_over(row[IS_SUPPER_OVER]);
            delivery.setWide_runs(row[WIDE_RUNS]);
            delivery.setBye_runs(row[BYE_RUNS]);
            delivery.setLeg_bye_runs(row[LEG_BYE_RUNS]);
            delivery.setNo_ball_runs(row[NO_BALL_RUNS]);
            delivery.setPenalty_runs(row[PENALTY_RUNS]);
            delivery.setBatsman_runs(row[BATSMAN_RUNS]);
            delivery.setExtra_runs(row[EXTRA_RUNS]);
            delivery.setTotal_runs(row[TOTAL_RUNS]);
            deliveries.add(delivery);
        }
        return deliveries;
    }
}
