import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameService {
    private Queue<Player> players;
    private List<Card> cards;
    private Map<Pair<Character, Integer>, Boolean> pickedMap;
    private Map<Character, Integer> cardValueMap;

    public GameService(List<Player> player) {
        players = new LinkedList<>();
        players.addAll(player);
        pickedMap = new HashMap<>();
        cardValueMap = new HashMap<>();
        initializeGame();
    }

    public void initializeGame() {
        for (int j = 1; j <= 4; j++) {
            for (int i = 1; i <= 13; i++) {
                Card card = new Card();
                if (i ==11){
                    card.setCardColor(j);
                    card.setCardFace('J');
                    cardValueMap.put('J', 10);
                }
                else if (i ==12){
                    card.setCardColor(j);
                    card.setCardFace('Q');
                    cardValueMap.put('Q', 10);
                }
                else if (i ==13){
                    card.setCardColor(j);
                    card.setCardFace('K');
                    cardValueMap.put('K', 10);
                }
                else{
                    card.setCardColor(j);
                    card.setCardFace((char) i);
                    cardValueMap.put((char) i, i);
                }
                pickedMap.put(new Pair<>(card.getCardFace(),i), false);
            }
        }
    }

    public void startGame(){

        Scanner sc = new Scanner(System.in);
        List<Player> gamers= new ArrayList<>();
        while(!players.isEmpty()){
            Player player =  players.peek();
            System.out.println("Your turn " + player.getName());
            String input = sc.nextLine();
            int option = Integer.parseInt(input);
            if(option == 1){
                Card card = pickCard();
                if(card.getCardFace() == 'A'){
                    player.setCountA(player.getCountA() + 1);
                }
                else{
                    player.setSumOfCards(player.getSumOfCards() + cardValueMap.get(card)) ;
                }

               player.getCards().add(card);
                pickedMap.put(new Pair<>(card.getCardFace(),card.getCardColor()), true);
                players.offer(player);
            }
            else{

                gamers.add( players.peek());

            }
        }

        getWinner(gamers);

    }

    public Card pickCard() {

        while(true) {
            Random rand = new Random();
            int max = 13, min = 1;
            int number = rand.nextInt(max - min + 1) + min;
            max = 4;
            int color = rand.nextInt(max - min + 1) + min;

            Card card = new Card();
            if (number == 1)
                card.setCardFace('A');
            else if (number == 11) {
                card.setCardFace('J');
            } else if (number == 12) {
                card.setCardFace('Q');
            } else if (number == 13) {
                card.setCardFace('K');
            } else {
                card.setCardFace((char) number);
            }
            card.setCardColor(color);

            if (!pickedMap.get(card)){
                return card;
            }

            // if all the cards are picked
        }


    }

    public void getWinner(List<Player> player){
        Player player1 = player.get(0);
        int sumOfPlayer1 = player1.getSumOfCards();
        Player player2 = player.get(1);
        int sumOfPlayer2 = player2.getSumOfCards();
        int countAPlayer1 = player1.getCountA();
        int countAPlayer2 = player2.getCountA();

        while(countAPlayer1 > 0){
            if(sumOfPlayer1 + 10 > 21)
                sumOfPlayer1 = sumOfPlayer1 + 1;
            else
                sumOfPlayer1 = sumOfPlayer1 + 10;
            countAPlayer1 = countAPlayer1-1;
        }

        while(countAPlayer2 > 0){
            if(sumOfPlayer2 + 10 > 21)
                sumOfPlayer2 = sumOfPlayer2 + 1;
            else
                sumOfPlayer2 = sumOfPlayer2 + 10;
            countAPlayer2 = countAPlayer2-1;
        }

        if(sumOfPlayer1 > sumOfPlayer2)
            System.out.println("Game won by " + player.get(0).getName());
        else if(sumOfPlayer1 < sumOfPlayer2)
            System.out.println("Game won by " + player.get(1).getName());
        else System.out.println("Game is a tie");
    }





}
