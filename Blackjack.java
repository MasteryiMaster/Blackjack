import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Blackjack{
    private Hand hand = new Hand();
    private Deck deck = new Deck();
    private int KeepDraw;
    private int DCardNumber;
    private int PCardNumber;
    public int Playercard;
    public int winLose;
    public int insurance;
    private static Scanner scanner = new Scanner(System.in);
    public Blackjack(){}
    public Blackjack(Hand hand,Deck deck){
        this.hand=hand;
        this.deck=deck;
    }
    public int FillMyHand(List<Card> Hand){
        int enter;
        KeepDraw=1;
        PCardNumber=2;
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
        return Playercard;
    }
    public void FillDealerHand(List<Card> DealerHand,List<Card> MyHand,int Playercard){
        DCardNumber=2;       
        System.out.println("莊家的牌面:");
        hand.ShowHand(DealerHand);
    	//SpacialCardFace();
        while(DCardNumber<5&&hand.Sum(DealerHand)<18&&(hand.Sum(DealerHand)-10)<Playercard){
            DCardNumber++;
            System.out.println("莊家加了一張牌");
            hand.ReceiveCard(DealerHand);
            System.out.println("莊家的牌面:");
            hand.ShowHand(DealerHand);
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
//    	if(DCardNumber==2&&hand.Sum(Hand.DealerHand)==21){
//            winLose=1;
//            return "莊家通吃\n";
//        }
	if(hand.Sum(Hand.MyHand)==21&&hand.Sum(Hand.DealerHand)==21){
            System.out.println("流局\n");
            winLose=1;
            return winLose;
        }
        else if(Overstep(Hand.MyHand) || DCardNumber==5 || (hand.Sum(Hand.DealerHand)<=21&&hand.Sum(Hand.DealerHand)>=hand.Sum(Hand.MyHand))) {
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
    	if(Hand.DealerHand.get(1).getValue()==11){
            System.out.println("要不要買保險?\n1.要 2.不要\n");
            insurance=scanner.nextInt();
            while(insurance!=1&&insurance!=2){
		System.out.println("我是在問你要不要買保險?歸剛欸");
		System.out.println("1.要 2.不要\n");
		insurance=scanner.nextInt();
            }
            if(insurance==1){
                insurance=0;
            }
            else{
                System.out.println("您選擇不買，希望你不後悔\n");
                insurance=-1;
            }
    	}
        return insurance;
    }
}