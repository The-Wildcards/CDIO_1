public class Player {

    public String name;
    public int score;
    public int lastRoll;

    public Player (String name, int score){
        this.name = name;
        this.score = score;
    }

    public void AddScore(int amount){
        if(score < 40)
            this.score += amount;
    }

    public void RemoveScore(int amount){
        this.score -= amount;
    }

    public void ResetScore(){
        this.score = 0;
    }
}
