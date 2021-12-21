public class Card{
    private Rank aRank;
    private Suit aSuit;
    public Card(Rank pRank, Suit pSuit){
        assert pRank != null && pSuit != null;
        aRank = pRank;
        aSuit = pSuit;
    }
    public int getValue(){
        return aRank.getValue();
    }
    public String toString(){
        return aSuit+""+aRank;
    }
}