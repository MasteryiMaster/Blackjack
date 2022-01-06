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
import java.util.Scanner;
public class Game {
    private final Hand hand;
    private final Blackjack blackjack;
    private final MoneyC myMoney;
    private Deck deck;
    private double betmoney;
    public int Playercard;
    public int winLose;
    public int insurance;
    private static Scanner scanner = new Scanner(System.in);
    public Game() {
        this.myMoney = new MoneyC();
        this.hand = new Hand();
        this.deck = new Deck();
        this.blackjack = new Blackjack(hand,deck,myMoney);
    }
    public void play(){
        int KeepPlay;
        int judge;
        do{   
            betmoney=myMoney.bet();
            hand.ReceiveCard(Hand.DealerHand);
            hand.ReceiveCard(Hand.DealerHand);
            System.out.println("莊家的底牌:\t莊家的明牌:\n未掀開\t\t["
                +Hand.DealerHand.get(1)
                +"("+Hand.DealerHand.get(1).getValue()
                +")]")
            ;
            judge=blackjack.Insurance();
            myMoney.calMoney(judge);
            if(judge==3||judge==2){
                hand.Sum(Hand.MyHand);
                hand.ReceiveCard(Hand.MyHand);
                hand.ReceiveCard(Hand.MyHand);
                System.out.println("你的底牌:\t你的明牌:\n["
                    +Hand.MyHand.get(0)+"("
                    +Hand.MyHand.get(0).getValue()
                    +")]\t"
                    +"["+Hand.MyHand.get(1)+"("
                    +Hand.MyHand.get(1).getValue()
                    +")]")
                ;
                Playercard=hand.Sum(Hand.MyHand)-Hand.MyHand.get(0).getValue();
                System.out.println("你的牌面:");
                hand.ShowHand(Hand.MyHand);
            }
            if(hand.Sum(hand.DealerHand)==21){
                myMoney.calMoney(blackjack.WinLose());
            }
            else{
                Playercard=blackjack.FillMyHand(Hand.MyHand);
                if(blackjack.Overstep(Hand.MyHand)){
                    myMoney.calMoney(blackjack.WinLose());
                }
                else if(Playercard==0){}
                else{
                    blackjack.FillDealerHand(Hand.DealerHand,Hand.MyHand,Playercard);
                    myMoney.calMoney(blackjack.WinLose());
                }
            }
            System.out.println();
            hand.NextRound();
            System.out.println("再玩一次?\n1.再玩一次 2.離開");
            KeepPlay=scanner.nextInt();
            while(KeepPlay!=1&&KeepPlay!=2){
                System.out.println("我是在問你玩還是不玩?歸剛欸");
                System.out.println("再玩一次?\n1.再玩一次 2.離開");
                KeepPlay=scanner.nextInt();
            }
            if(myMoney.money<=0&&KeepPlay==1){
                System.out.println("Haiyaa!你沒錢啦死窮鬼!\n回去繼續當社畜賺錢啦!");
                KeepPlay=2;
            }
            else if(myMoney.money>=0&&KeepPlay==2){
                if(myMoney.money>5000)
                    System.out.println("恭喜!你現在有 "+myMoney.money+" 元\n你出運了!");
                else
                    System.out.println("你現在有 "+myMoney.money+" 元\n歡迎下次再來!");
            }
            else{
                if(deck.getCardsize()<10){
                    deck.shuffle();
                    System.out.println("\n已重新洗牌");
                }
                System.out.println("此回合結束\n=======================================");
                System.out.print("還剩"+deck.getCardsize()+"張牌\n");
            }
        }while(KeepPlay==1);
    }
}
