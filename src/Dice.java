public class Dice {
    
    int rollValue;
    //Dice constructer
    public Dice (int rollValue){
        this.rollValue = rollValue;
    }
    // Rolling the actual dice
    public int rollDice(){
        //Using math.random to generate random number between 1 and 6.
        rollValue = (int) ((Math.random() * (7 - 1)) + 1) ;
        return rollValue;
    }

    public int getRollValue(){
        return rollValue;
    }
}
