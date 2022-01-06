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
import java.util.Scanner;
public class Blackjack{
    private Hand hand;
    private Deck deck;
    private MoneyC myMoney;
    private int KeepDraw;
    private int DCardNumber;
    private int PCardNumber;
    public int Playercard;
    public int winLose;
    public int insurance;
    public int Blackjack;
    private static Scanner scanner = new Scanner(System.in);
    public Blackjack(){}
    public Blackjack(Hand hand,Deck deck,MoneyC myMoney){
        this.hand=hand;
        this.deck=deck;
        this.myMoney=myMoney;
    }
    public int FillMyHand(List<Card> Hand){
        KeepDraw=1;
        PCardNumber=2;
        hand.Sum(Hand);
        hand.ReceiveCard(Hand);
        hand.ReceiveCard(Hand);
        System.out.println("你的底牌:\t你的明牌:\n["
            +Hand.get(0)+"("
            +Hand.get(0).getValue()
            +")]\t"
            +"["+Hand.get(1)+"("
            +Hand.get(1).getValue()
            +")]")
        ;
        Playercard=hand.Sum(Hand)-Hand.get(0).getValue();
        System.out.println("你的牌面:");
        hand.ShowHand(Hand);
        //SpacialCardFace();
        while(KeepDraw==1&&PCardNumber<5&&hand.Sum(Hand)<21){
            if(PCardNumber==2){
                System.out.println("請問是否要投降?(輸一半賭注)\n1.繼續 2.認輸");
                KeepDraw=scanner.nextInt();
                while(KeepDraw!=1&&KeepDraw!=2){
                    System.out.println("我是在問你要不要認輸?歸剛欸");
                    System.out.println("1.繼續 2.認輸");
                    KeepDraw=scanner.nextInt();
                }
                if(KeepDraw==2){
                    System.out.println("您選擇認輸!");
                    myMoney.calMoney(0);
                    Playercard=0;
                    break;
                }
            }
            System.out.print("1.加牌 2.不加\n");
            KeepDraw=scanner.nextInt();
            while(KeepDraw!=1&&KeepDraw!=2){
		System.out.println("我是在問你加還是不加?歸剛欸");
		System.out.println("1.加牌 2.不加\n");
		KeepDraw=scanner.nextInt();
            }
            if(KeepDraw==2){
                break;
            }
            PCardNumber++;
            System.out.println("你抽了一張卡");
            hand.ReceiveCard(Hand);
            System.out.println("你的牌面:");
            hand.ShowHand(Hand);
            Playercard=hand.Sum(Hand)-Hand.get(0).getValue();
        }
        if(PCardNumber==5&&hand.Sum(Hand)<=21){
            if(hand.Sum(Hand)==21){
                System.out.print("過五關21點!贏得三倍下注金額");
                myMoney.calMoney(4);
                Playercard=0;
            }
            else{
                System.out.print("過五關!贏得兩倍下注金額");
                myMoney.calMoney(5);
                Playercard=0;
            }
        }
        return Playercard;
    }
    public void FillDealerHand(List<Card> DealerHand,List<Card> MyHand,int Playercard){
        DCardNumber=2;       
        System.out.println("莊家的牌面:");
        hand.Sum(DealerHand);
        hand.ShowHand(DealerHand);
        while(hand.Sum(DealerHand)<18||(hand.Sum(DealerHand)<21&&DCardNumber<5&&(hand.Sum(DealerHand)-10)<Playercard&&hand.Sum(DealerHand)<19)){
            DCardNumber++;
            System.out.println("莊家加了一張牌");
            hand.ReceiveCard(DealerHand);
            System.out.println("莊家的牌面:");
            hand.ShowHand(DealerHand);
        }
        if(DCardNumber==5&&hand.Sum(DealerHand)<=21){
            System.out.println("莊家過五關");
        }
    }
    public boolean Overstep(ArrayList<Card> card){
    	if(hand.Sum(card)>21){
            return true;
    	}
    	else{
            return false;
    	}
    }
    public int WinLose(){
	if(hand.Sum(Hand.MyHand)==21&&hand.Sum(Hand.DealerHand)==21){
            System.out.println("流局\n");
            winLose=1;
            return winLose;
        }
        else if(Overstep(Hand.MyHand) || ((hand.Sum(Hand.DealerHand)<=21&&hand.Sum(Hand.DealerHand)>=hand.Sum(Hand.MyHand)))) {
            if(hand.Sum(Hand.DealerHand)==hand.Sum(Hand.MyHand)){
                System.out.print("莊家通吃!");
            }
            System.out.println("你輸了\n");
            winLose=2;
            return winLose;
        }
        else{
            System.out.println("你贏了\n");
            winLose=3;
            return winLose;
        }
    } 
    public int Insurance(){
        insurance=-1;
        if(Hand.DealerHand.get(1).getValue()==11){
            System.out.println("要不要買保險?\n1.要 2.不要");
            insurance=scanner.nextInt();
            while(insurance!=1&&insurance!=2){
		System.out.println("我是在問你要不要買保險?歸剛欸");
		System.out.println("1.要 2.不要");
		insurance=scanner.nextInt();
            }
            if(insurance==1&&Hand.DealerHand.get(0).getValue()==10){
                insurance=3;
                System.out.println("莊家的底牌:\t莊家的明牌:\n["
                +Hand.DealerHand.get(0)
                +"("+Hand.DealerHand.get(0).getValue()
                +")]\t["
                +Hand.DealerHand.get(1)
                +"("+Hand.DealerHand.get(1).getValue()
                +")]")
            ;
                System.out.println("您買的保險中了!您獲得兩倍的保險金");
            }
            else if(insurance==1&&Hand.DealerHand.get(0).getValue()!=10){
                insurance=0;
                System.out.println("您買的保險沒中!保險金被莊家收走了");
            }
            else{
                System.out.println("您選擇不買，希望你不後悔\n");
                if(Hand.DealerHand.get(0).getValue()==10){
                    insurance=2;
                    System.out.println("活該死好!各人做業各人擔!");
                }
                else{
                    insurance=-1;
                    System.out.println("明智的選擇!莊家不是Blackjack!\n遊戲繼續");
                }
            }
    	}
        if(Hand.DealerHand.get(1).getValue()==10){
            if(Hand.DealerHand.get(0).getValue()==11){
                insurance=2;
                System.out.println("莊家Blackjack!\n您輸了!");
            }
            else{
                System.out.println("莊家不是Blackjack!");
            }
        }
        return insurance;
    }
    public int Blackjack(){
        Blackjack=-1;
        if(Hand.DealerHand.get(1).getValue()==11){
            System.out.println("要不要買保險?\n1.要 2.不要");
            Blackjack=scanner.nextInt();
            while(Blackjack!=1&&Blackjack!=2){
		System.out.println("我是在問你要不要買保險?歸剛欸");
		System.out.println("1.要 2.不要");
		Blackjack=scanner.nextInt();
            }
            if(Blackjack==1&&Hand.DealerHand.get(0).getValue()==10){
                Blackjack=3;
                System.out.println("莊家的底牌:\t莊家的明牌:\n["
                +Hand.DealerHand.get(0)
                +"("+Hand.DealerHand.get(0).getValue()
                +")]\t["
                +Hand.DealerHand.get(1)
                +"("+Hand.DealerHand.get(1).getValue()
                +")]")
            ;
                System.out.println("您買的保險中了!您獲得兩倍的保險金");
            }
            else if(Blackjack==1&&Hand.DealerHand.get(0).getValue()!=10){
                Blackjack=0;
                System.out.println("您買的保險沒中!保險金被莊家收走了");
            }
            else{
                System.out.println("您選擇不買，希望你不後悔\n");
                if(Hand.DealerHand.get(0).getValue()==10){
                    Blackjack=2;
                    System.out.println("活該死好!各人做業各人擔!");
                }
                else{
                    Blackjack=-1;
                    System.out.println("明智的選擇!莊家不是Blackjack!\n遊戲繼續");
                }
            }
    	}
        if(Hand.DealerHand.get(1).getValue()==10){
            if(Hand.DealerHand.get(0).getValue()==11){
                Blackjack=2;
                System.out.println("莊家Blackjack!\n您輸了!");
            }
            else{
                System.out.println("莊家不是Blackjack!");
            }
        }
        return Blackjack;
    }
}
