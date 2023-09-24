public class Exp3 {
    
    //int previousRoll;
    //int currentRoll;

    //public Exp3(int previousRoll, int currentRoll) {
    //    this.previousRoll = previousRoll;
    //    this.currentRoll = currentRoll;
    //}

    //Spilleren kan vinde spillet ved at slå to 6'ere, hvis spilleren også i forrige kast slog to 6'ere uanset om det er på ekstrakast eller i forrige tur.
    public static boolean onDoubleRoll(int currentRoll, int previousRoll){
        var winner = currentRoll == 12 && currentRoll == previousRoll ? true : false; 

        return winner;
    }
}
