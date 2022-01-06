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
public class Card{
    private final Rank aRank;
    private final Suit aSuit;
    public Card(Rank pRank, Suit pSuit){
        assert pRank != null && pSuit != null;
        aRank = pRank;
        aSuit = pSuit;
    }
    public int getValue(){
        return aRank.getValue();
    }
    @Override
    public String toString(){
        return aSuit+""+aRank;
    }
}