package me.tomasztopola.competition;

import me.tomasztopola.api.BattleShipClient;
import me.tomasztopola.api.ClientData;
import me.tomasztopola.api.Contest;
import me.tomasztopola.rules.Rules;

/**
 * Runs 1 vs 1 battle between given clients
 */
public class OneVsOne implements Contest {

    private BattleShipClient client1;
    private BattleShipClient client2;
    private BattleShipClient winner;

    public void run() {
        // Init logs
        System.out.println("Starting One vs One battle");
        System.out.println(client1.getBotName() + "     vs      " + client2.getBotName() + "\n");

        //check if clients follow given rules;
        Rules.assertClientRules(client1);
        Rules.assertClientRules(client2);

        // run battle
        int countRounds = 1;

        while (true) { //this sets while as infinite (unless condition was met)
            System.out.println("Ships left after round " + countRounds + ":");

            client2.attack(new ClientData(client1));
            client1.attack(new ClientData(client2));

            System.out.println(client1.getBotName() + ": " + client1.getFloatingShips());
            System.out.println(client2.getBotName() + ": " + client2.getFloatingShips());

            if(client1.getFloatingShips() == 0 || client2.getFloatingShips() == 0) break;
            countRounds += 1;

            System.out.println();
        }

        // get floating ships
        int floatingShips1 = client1.getFloatingShips();
        int floatingShips2 = client2.getFloatingShips();

        // print win message
        if(floatingShips1== 0 && floatingShips2 > 0) {
            System.out.println(client2.getBotName() + " wins in " + countRounds + " rounds!");
            winner = client2;
        }
        else if( floatingShips2 == 0 && floatingShips1 > 0) {
            System.out.println(client1.getBotName() + " wins in " + countRounds + " rounds!");
            winner = client1;
        }
        else if( floatingShips1 == 0 && floatingShips2 == 0) {
            System.out.println("It's a draw! They somehow killed each other at the same time.");
            winner = null;
        }
        else
            System.out.println("Something went wrong. Battle stopped before bots lost all their ships.");
    }

    @Override
    public void addCompetitor(BattleShipClient client) {
        if(client1 == null) client1 = client;
        else client2 = client;
    }

    public BattleShipClient getWinner(){
        return winner;
    }
}
