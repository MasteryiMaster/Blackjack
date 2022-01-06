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
public class MoneyC{
    public double betmoney;
    public double money;
    MoneyC(){
        money=5000;
	System.out.println("歡迎來到21點\n你還有"+money+"元");
    }
    public double bet(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("你想賭多少?");
        betmoney = scanner.nextInt();
        while(betmoney>money||betmoney<=0){
            if(betmoney<=0){
                System.out.println("這是賭博，不是慈善事業，把你的錢交出來不然就滾蛋!");
            }
            else if(betmoney>money){
                System.out.println("你這麼早睡?別再做白日夢拉!\n你沒有這麼多錢啦窮鬼!");
            }
            System.out.println("你想賭多少?");
            betmoney = scanner.nextInt();
        }
        return betmoney;
    }
    public void calMoney(int winLose){
        switch(winLose){ 
            case 0:
                money-=(betmoney/2);
                System.out.println("你還有"+money+"元");
                break;
            case 1:
                System.out.println("你現在有"+money+"元");
                break;
            case 2:
                money-=betmoney;
                System.out.println("你還有"+money+"元");
                break;
            case 3:
                money+=betmoney;
                System.out.println("你現在有"+money+"元");
                break;
            case 4:
                money+=3*betmoney;
                System.out.println("你現在有"+money+"元");
                break;
            case 5:
                money+=2*betmoney;
                System.out.println("你現在有"+money+"元");
                break;
            case 6:
                
            default:
        }
    }
}
