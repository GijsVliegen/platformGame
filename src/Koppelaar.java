import game.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Koppelaar {

    List<Team> teams = new ArrayList<Team>();
    public Koppelaar(Player player, String firstOption, String secondOption){
        if (!findTeam(player, firstOption)){
            if (!findTeam(player, secondOption)) {
                makeTeam(player, firstOption);
            }
        }
    }

    public boolean findTeam(Player player, String option){
        Iterator i = teams.iterator();
        while (i.hasNext()){
            Team team = (Team)i.next();
            if (!team.fullFor(option)){
                team.fillWith(player, option);
                return true;
            }
        }
        return false;
    }
    public void makeTeam(Player player, String option){
        //best die shizzle doen met instanceof, zodat ik meerdere initialisers kan hebben
        Team team = new Team(this, player, option);
        teams.add(team);
    }
    public void remove(Team team){
        teams.remove(team);
    }
}
