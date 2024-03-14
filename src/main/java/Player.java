import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Player {

    private String id;
    private String name;
    private List<Card> cards;
    private int sumOfCards;
    private int countA;
    private boolean isPlaying;

}
