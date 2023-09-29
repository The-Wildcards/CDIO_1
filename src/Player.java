public class Player {

    public String name;
    public int score;
    public int lastRoll;
    public boolean scoreMetRequirement;

    public Player (String name, int score){
        this.name = name;
        this.score = score;
    }

    public void AddScore(int amount){
        if(score < App.gameManager.scoreRequirement){
            this.score += amount;

            if(score >= App.gameManager.scoreRequirement){
                if(!App.expansionManager.expansions.get(3).enabled){
                    App.gameManager.gameState = GameState.LASTTURN;  
                }
            }
        }
        else{
            scoreMetRequirement = true;
        }
    }

    public void RemoveScore(int amount){
        this.score -= amount;
    }

    public void ResetScore(){
        this.score = 0;
        scoreMetRequirement = false;
    }
}
