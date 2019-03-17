package metaheurystyka;

import java.util.List;
import java.util.Random;

public class Przerwa {
    int ktoraMaszyna; // Numer maszyny
    Integer czasGotowosci; // Czas gotowości (pojawienia się)
    int czasTrwania; // Czas trwania przerwania

    public Przerwa(int ktoraMaszyna, int czasGotowosci, int czasTrwania) {
        this.ktoraMaszyna = ktoraMaszyna;
        this.czasGotowosci = czasGotowosci;
        this.czasTrwania = czasTrwania;
    }

    public Przerwa(int czasTrwania, int czasGotowosci)
    {
        this.czasTrwania = czasTrwania;
        this.czasGotowosci = czasGotowosci;
    }

    public Przerwa() {
    }

    public int maintanceAssignment(List<Przerwa> lista, int i, int a, int b) {

        if (a == 0) {
            lista.get(i).ktoraMaszyna = 1;
            return 2;
        } else if (b == 0) {
            lista.get(i).ktoraMaszyna = 0;
            return 1;
        } else {
            Random generator = new Random();
            int x1 = generator.nextInt(2) + 1;
            lista.get(i).ktoraMaszyna = x1 - 1;
            return x1;
        }
    }

    public boolean[] maintanceImplementation(List<Przerwa> lista, int i, int limit1, int limit2, boolean[] tab) {
        Random generator = new Random();
        float flo = generator.nextInt(32000);
        int czasTrwania = (int) (flo/ 32000 * limit1);

        int czasGotowosci = 0;
        int iteracja = 0;
        int stopTime = 0;

        while (true) {
            flo = generator.nextInt(32000);
            czasGotowosci = (int)((flo/32000)*limit2 + 1);
            stopTime = czasGotowosci + czasTrwania;

            boolean repeatNeed = false;
            for (int j = czasGotowosci; j < stopTime; j++) {
                if (tab[j]) {
                    repeatNeed = true;
                    break;
                }
            }

            if (!repeatNeed) {
                break;
            }

            if (iteracja > 40) {
                iteracja = 0;
                flo = generator.nextInt(32000);
                czasTrwania = (int) (flo/ 32000 * limit1);
            }

            iteracja++;
        }
        for (int k = czasGotowosci; k < stopTime; k++) {
            tab[k] = true;
        }

        lista.get(i).czasTrwania = czasTrwania+1;
        lista.get(i).czasGotowosci = czasGotowosci;
        return tab;
    }

    /*
    public boolean [] separate(Przerwa maint, boolean [] tab)
    {
        for(Integer i = maint.czasGotowosci; i<maint.czasTrwania; i++)
        {
            tab[i] = true;
        }
        return tab;
    }
    */

    public int getKtoraMaszyna() {
        return ktoraMaszyna;
    }

    public void setKtoraMaszyna(int ktoraMaszyna) {
        this.ktoraMaszyna = ktoraMaszyna;
    }

    public Integer getCzasGotowosci() {
        return czasGotowosci;
    }

    public void setCzasGotowosci(Integer czasGotowosci) {
        this.czasGotowosci = czasGotowosci;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }


}
