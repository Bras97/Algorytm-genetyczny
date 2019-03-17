/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheurystyka;

import java.util.ArrayList;
import java.util.List;

public class Paczuszka2 {
    int unacceptableResult = 0;
    int funCelAkt;
    int funCelBest;
    int funCelPrev;
    List<Zadanie> bestSolution = new ArrayList<>();
    List<Zadanie> aktSolutions = new ArrayList<>();
    List<List<Zadanie>>listDoubleZadanie = new ArrayList<>();


    public Paczuszka2(int unacceptableResult, int funCelAkt, int funCelBest, int funCelPrev, List<Zadanie> bestSolution, List<Zadanie> aktSolutions) {
        this.unacceptableResult = unacceptableResult;
        this.funCelAkt = funCelAkt;
        this.funCelBest = funCelBest;
        this.funCelPrev = funCelPrev;
        this.bestSolution = bestSolution;
        this.aktSolutions = aktSolutions;
    }

    public Paczuszka2(){}

    public int getUnacceptableResult() {
        return unacceptableResult;
    }

    public int getFunCelAkt() {
        return funCelAkt;
    }

    public int getFunCelBest() {
        return funCelBest;
    }

    public int getFunCelPrev() {
        return funCelPrev;
    }

    public List<Zadanie> getBestSolution() {
        return bestSolution;
    }

    public List<Zadanie> getAktSolutions() {
        return aktSolutions;
    }

    public void setUnacceptableResult(int unacceptableResult) {
        this.unacceptableResult = unacceptableResult;
    }

    public void setFunCelAkt(int funCelAkt) {
        this.funCelAkt = funCelAkt;
    }

    public void setFunCelBest(int funCelBest) {
        this.funCelBest = funCelBest;
    }

    public void setFunCelPrev(int funCelPrev) {
        this.funCelPrev = funCelPrev;
    }

    public void setBestSolution(List<Zadanie> bestSolution) {
        this.bestSolution = bestSolution;
    }

    public void setAktSolutions(List<Zadanie> aktSolutions) {
        this.aktSolutions = aktSolutions;
    }

    protected void finalize() throws Throwable
    {
        super.finalize();
    }

}

