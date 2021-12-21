import java.util.Scanner;
public class Game {
    private Hand hand = new Hand();
    private Deck deck = new Deck();
    private Blackjack blackjack=new Blackjack(hand,deck);
    private MoneyC myMoney=new MoneyC();
    private int betmoney;
    private int DCardNumber;
    public int Playercard;
    public int winLose;
    public int insurance;
    private static Scanner scanner = new Scanner(System.in);
    public void play(){
        int KeepPlay;
        do{   
            betmoney=myMoney.bet();
            hand.ReceiveCard(Hand.DealerHand);
            hand.ReceiveCard(Hand.DealerHand);
            System.out.println("莊家的底牌:\t莊家的明牌:\n未掀開\t\t["
                +Hand.DealerHand.get(1)
                +"("+Hand.DealerHand.get(1).getValue()
                +")]")
            ;
            Playercard=blackjack.FillMyHand(Hand.MyHand);
            if(blackjack.Overstep(Hand.MyHand)){
                myMoney.calMoney(blackjack.WinLose());
            }
            else{
                blackjack.FillDealerHand(Hand.DealerHand,Hand.MyHand,Playercard);
                myMoney.calMoney(blackjack.WinLose());
            }
            System.out.println();
            hand.NextRound();
            if(deck.getCardsize()<10){
                deck.shuffle();
                System.out.println("已重新洗牌");
            }
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
                System.out.println("此回合結束\n=======================================");
                System.out.print("還剩"+deck.getCardsize()+"張牌\n");
            }
        }while(KeepPlay==1);
    }
}