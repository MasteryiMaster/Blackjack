/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author 88696
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Deck{
    public static int CardNumbers;
    private static List<Card> aCards;
    public Deck(){
        this.aCards = new ArrayList<>(52);
        shuffle();
        CardNumbers=52;
    }
    public final void shuffle(){
        CardNumbers=52;
        aCards.clear();
        aCards = new ArrayList<>(52);
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
