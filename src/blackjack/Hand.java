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
import java.util.List;
public class Hand{
    Deck deck = new Deck();
    private int sum;
    private int HasAce;
    public static ArrayList<Card> MyHand = new ArrayList();
    public static ArrayList<Card> DealerHand = new ArrayList();
    public void ReceiveCard(List<Card> Hand){
        Hand.add(deck.draw());
    }
    public int Sum(List<Card> Hand){
        sum=0;
        HasAce=0;
        for(int i=0;i<Hand.size();i++){
            sum+=Hand.get(i).getValue();
            if(Hand.get(i).getValue()==11){
                HasAce+=1;
            }
            if(sum>21&&HasAce>0){
                sum-=10;
                HasAce-=1;
            }
        }
        return sum;
    }
    public void ShowHand(List<Card> Hand){
        for(int i=0;i<Hand.size();i++){
            if((Hand.get(i).getValue()==11&&sum>21)||HasAce>=2){
        	System.out.print("["+Hand.get(i)+"("+1+")"+"]");
            }
            else{
        	System.out.print("["+Hand.get(i)+"("+Hand.get(i).getValue()+")"+"]");
            }
        }
        System.out.print("總共:"+Sum(Hand)+"點");
        System.out.println();
    }
    public void NextRound(){
        MyHand.clear(); 
        DealerHand.clear();
    }
}