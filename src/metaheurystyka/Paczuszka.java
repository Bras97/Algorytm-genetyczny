package metaheurystyka;
import java.util.ArrayList;
import java.util.List;

public class Paczuszka {
    int ktory;
    int [] zm;
    List<Zadanie> bestSolution = new ArrayList<>();
    double bestFunCel;
    List<Zadanie> randomSolution = new ArrayList<>();
    double randomFunCel;

    public Paczuszka(){
        ktory = 0;
    }

}
