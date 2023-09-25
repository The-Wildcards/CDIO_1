import java.util.ArrayList;
import java.util.List;

public class ExpansionManager {
    public static List<Expansion> exspansions = new ArrayList<>();

    public static void GenerateExpansions(){
        Expansion exp1 = new Exp1(1, false);
        Expansion exp2 = new Exp2(2, false);
        Expansion exp3 = new Exp3(3, false);
        Expansion exp4 = new Exp4(4, false);

        exspansions.add(exp1);;
        exspansions.add(exp2);;
        exspansions.add(exp3);;
        exspansions.add(exp4);;
    }

    public static Expansion GetExpansionByID(int id) {
        Expansion exp = exspansions.stream().filter(x -> x.id == id).findFirst().orElse(null);
        return exp;
    }
    
}
