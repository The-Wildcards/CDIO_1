public class Exp (1){

    // Checks if the expansion is activated  
    if(exp(1) == true){
        ExpansionUtils.GetExpantion(1).CheckRules(1, 4)
    }
    //Checks the result of both dice rolls
    public static bool CheckRules(int[] dices){
        if(dices[0] == dices[1]){
            return true
        }
        else {
            return false;
        }
    
    }

}