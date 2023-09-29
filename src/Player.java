public class Player {

    public int id;
    public String name;
    public int score;
    public int lastRoll;
    public boolean scoreMetRequirement;

    public Player (int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void AddScore(int amount){
        if(score < App.gameManager.scoreRequirement){
            this.score += amount;

            if(score >= App.gameManager.scoreRequirement){
                if(!App.expansionManager.expansions.get(3).enabled){
                    if(id == 0){
                        App.gameManager.gameState = GameState.LASTTURN;  
                    } else {
                        App.gameManager.gameState = GameState.ENDED;
                    }
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
