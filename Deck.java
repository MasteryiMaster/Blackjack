import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Deck{
    public static int CardNumbers;
    private List<Card> aCards = new ArrayList<>();
    public Deck(){
        shuffle();
        CardNumbers=52;
    }
    public void shuffle(){
        CardNumbers=52;
        aCards.clear();
        for( Suit suit : Suit.values() ){
            for( Rank rank : Rank.values() ){
                aCards.add( new Card( rank, suit ));
            }
        }
        Collections.shuffle(aCards);
    }
    public void push(Card pCard){
	assert pCard != null;
	aCards.add(pCard);
    }
    public int getCardsize(){
	return CardNumbers;
    }	
    public Card draw(){
	assert !isEmpty();
	CardNumbers--;
	return aCards.remove(aCards.size()-1);
    }
    public boolean isEmpty(){
	return aCards.isEmpty();
    }
    public List<Card> getCards(){
	return Collections.unmodifiableList(aCards);
    }
}
