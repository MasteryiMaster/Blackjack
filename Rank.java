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
public enum Rank{
    A(11),二(2),三(3),四(4),五(5),六(6),七(7),
    八(8),九(9),十(10),J(10),Q(10),K(10);
    private int value;
    private Rank(int value){
	this.value=value;
    }
    public int getValue(){
	return value;
    }
}