import java.util.*;
import java.util.concurrent.TimeUnit;

public class black_Jack {

    public static void main(String[] args) {

        System.out.println ("Welcome to a simplified version of Black Jack. No bets, just fun.");
        System.out.println("Dealer hits S17 - stands H17" + "\n");

        Scanner scan = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());

        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i < 11; i++){
            deck.add(i);
        }
        deck.add(10);deck.add(10);deck.add(10);

        /*
         deck ==> [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10]
         J = 10 Q = 10 K = 10 so mathematically we don't care about court cards
         - neither for suits, suppose we have infinite decks
        */

        int sum = 0;
        boolean bj = false;
        boolean play_again = true;
        List<Integer> player_cards = new ArrayList<>();

        while (play_again) {
            play_again = false;

            int sumd = 0;
            boolean bjd = false;
            List<Integer> dealer_cards = new ArrayList<>();
            dealer_cards.clear();
            bjd = false;
            dealer_cards.add(deck.get(rand.nextInt(13)));
            dealer_cards.add(deck.get(rand.nextInt(13)));
            for (int i = 0; i < dealer_cards.size(); i++) {
                sumd += dealer_cards.get(i);
            }
            System.out.print("Dealer cards: ");

            //only one of dealers cards is face-up
            System.out.print(dealer_cards.get(0) + " ? " + "\n");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            bj = false;
            sum = 0;
            player_cards.clear();
            boolean Aces = false;
            System.out.print("Your cards: ");
            player_cards.add(deck.get(rand.nextInt(13)));
            player_cards.add(deck.get(rand.nextInt(13)));
            for (int i = 0; i < player_cards.size(); i++) {
                sum += player_cards.get(i);
                if (player_cards.get(i) == 1){
                    Aces = true;
                }
            }
            for (int i = 0; i < player_cards.size(); i++) {
                System.out.print(player_cards.get(i) + " ");
            }
            if ((player_cards.get(0) == 10 && player_cards.get(1) == 1) || (player_cards.get(1) == 10 && player_cards.get(0) == 1)) {
                System.out.println(" = BJ ");
                System.out.println("BlackJack!");
                bj = true;
                sum = 21;
            } else if (Aces) {
                System.out.println (" = " + sum + " / "+ (sum + 10));
            } else {
                System.out.println(" = " + sum);
            }

            if ((sum < 21) && (!bj)){
                System.out.print("Hit or Stand?: ");
                char answer = scan.next().charAt(0);
                if (answer == 's' && Aces) {
                    sum += 10;
                }
                while (answer == 'h') {
                    player_cards.add(deck.get(rand.nextInt(13)));
                    if (player_cards.get(player_cards.size() - 1) == 1){
                        Aces = true;
                    }
                    System.out.println("You got: " + player_cards.get(player_cards.size() - 1));
                    System.out.print("Your cards: ");
                    sum += player_cards.get(player_cards.size() - 1);
                    for (int i = 0; i < player_cards.size(); i++) {
                        System.out.print( player_cards.get(i) + " ");
                    }
                    if (Aces && (sum + 10) < 22) {
                        System.out.println (" = " + sum + " / "+ (sum + 10));
                        if (sum + 10 == 21){
                            sum += 10;
                            break;
                        }
                    } else {
                        System.out.println(" = " + sum);
                    }
                    if (sum < 21) {
                        System.out.print("Hit or Stand?: ");
                        answer = scan.next().charAt(0);
                        if (answer == 's' && Aces && (sum + 10) < 22) {
                            sum += 10;
                        }
                        continue;
                    } else {
                        break;
                    }
                }

                if (sum == 21) {
                    System.out.println("21!");
                }
                if (sum > 21) {
                    System.out.println("Bust");
                }
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            Aces = false;
            System.out.print("Dealer cards: ");
            for (int i = 0; i < dealer_cards.size(); i++) {
                System.out.print(dealer_cards.get(i) + " ");
                if (dealer_cards.get(i) == 1){
                    Aces = true;
                }
            }
            if ((dealer_cards.get(0) == 10 && dealer_cards.get(1) == 1) || (dealer_cards.get(1) == 10 && dealer_cards.get(0) == 1)) {
                bjd = true;
                System.out.println(" = BJ ");
            }else if (Aces) {
                System.out.println (" = " + sumd + " / "+ (sumd + 10));
            }else {
                System.out.println(" = " + sumd);
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            if (Aces && !bjd) {
                if (sum > 22 || sumd + 10 < 22 && sumd + 10 > 17 || bj) {
                    sumd += 10;
                }
            }

            if (sum<22 && !bjd && !bj) {
                while (!(sumd > 16)) {

                    /*
                     if player has BJ dealer must also have BJ or does not hit
                     dealers does not hit if player cards go over 21
                     dealer must hit until her cards total up to 17 points and
                     hits on soft 17 (sum 17 with an Ace) but stands on hard 17 (sum 17 with no Ace)
                    */

                    dealer_cards.add(deck.get(rand.nextInt(13)));
                    if (dealer_cards.get(dealer_cards.size() - 1) == 1){
                        Aces = true;
                    }
                    sumd += dealer_cards.get(dealer_cards.size() - 1);
                    System.out.print("Dealer cards: ");
                    for (int i = 0; i < dealer_cards.size(); i++) {
                        System.out.print(dealer_cards.get(i) + " ");
                    }
                    if (Aces && (sumd + 10) < 22) {
                        System.out.println (" = " + sumd + " / "+ (sumd + 10));
                        if (sumd > 7 || sumd + 10 > 17) {
                            sumd += 10;
                            break;
                        }
                    } else {
                        System.out.println(" = " + sumd);
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }

                }
            }

            if (!bj) {
                System.out.println("Your score: " + sum);
            } else {
                System.out.println("Your score: BJ");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            if (!bjd) {
                System.out.println ("Dealers score: " + sumd);
            } else {
                System.out.println("Dealers score: BJ");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            if (((sum > sumd && sum < 22 ) || (sum < 22 && sumd > 21) || bj) && !bjd) {
                System.out.println("You win!");
            } else if ((sum == sumd) && (sumd == 21) || (sum == sumd && !bjd) || (bjd && bj)) {
                System.out.println("Tie. No one wins.");
            }  else {
                System.out.println("Dealer wins.");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            System.out.print("Wanna play again?(y/n): ");
            char play = scan.next().charAt(0);
            if (play == 'y') {
                play_again = true;
            } else {
                System.out.println("Thanks for playing! xD");
                break;
            }
        }
    }
}

/*
 Just for Fun
 --eimon9j6--
 Last Modified 21/5/2020 v1.19
*/
