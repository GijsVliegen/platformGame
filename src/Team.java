import game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    List<Fighter> fighters = new ArrayList<Fighter>();
    List<Defender> defenders = new ArrayList<Defender>();
    Koppelaar koppelaar;
    int NrFighters = 0;
    int NrDefenders = 0;

    public Team(Koppelaar koppelaar, Player player, String option){
        fillWith(player, option);
        this.koppelaar = koppelaar;
    }
    public boolean fullFor(String option){
        if (option.equals("fighter")){
            if (NrFighters >= 2){
                return true;
            }
        }
        if (option.equals("defender")){
            if (NrDefenders >= 1){
                return true;
            }
        }
        return false;
    }
    public void fillWith(Player player, String option){
        if (option.equals("fighter")){
            NrFighters++;
            fighters.add((Fighter)player);
        }
        else if (option.equals("defender")) {
            NrDefenders++;
            defenders.add((Defender) player);
        }
        checkTeamFull();
    }
    public void checkTeamFull(){
        if (NrDefenders >= 2 && NrFighters >= 3){
            //Game.startTeam();
        }
    }
    public void StartTeam() {
        koppelaar.remove(this);
        //Game.startTeam(this);
    }
}
