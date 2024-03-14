import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Driver {

    public static void main(String agrs[]){
        List<Player> playerList = new ArrayList<>();

        Scanner scanner =  new Scanner(System.in);
        String player1 = scanner.nextLine();
        playerList.add(Player.builder()
                .name(player1)
                .cards(new ArrayList<>())
                .countA(0)
                .id(UUID.randomUUID().toString())
                .build());

        String player2 = scanner.nextLine();
        playerList.add(Player.builder()
                .name(player2)
                .cards(new ArrayList<>())
                .countA(0)
                .id(UUID.randomUUID().toString())
                .build());

        GameService gameService = new GameService(playerList);
        gameService.startGame();
    }
}
