package implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MatchesPlayedPerYear {
    public  void matchPlayedPerYear() throws IOException {
        HashMap<String, Integer> match_played_per_year = new HashMap<String ,Integer>();
        ArrayList<Integer> years = new ArrayList<>();
        String line = "";
        String file_path = "./resources/matches.csv";// write your code here
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        br.readLine();
        while((line = br.readLine()) != null){
            String values[] = line.split(",");

            if(match_played_per_year.containsKey(values[1])){
                int curr_value = match_played_per_year.get(values[1])+1;
                match_played_per_year.replace(values[1], curr_value);
            }else{
                match_played_per_year.put(values[1], 1);
                years.add(Integer.parseInt(values[1]));
            }
        }
        Collections.sort(years);
        for(int i=0; i< years.size();i++){
            System.out.println("Number of matches played in year "+years.get(i)+" :"+ match_played_per_year.get(""+years.get(i)) );
        }
    }
}
