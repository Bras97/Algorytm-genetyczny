package metaheurystyka;

import java.util.List;
import java.util.Random;

public class Zadanie implements Cloneable {
    int ID; // nick zadania
    int czesc; // Numer części zadania
    int ktoraMaszyna; // Przydział zadania do maszyny
    int czasTrwania; // Długość zadania
    Integer czasZakonczenia; // Moment zakończenia
    Zadanie zadanie2;

    public Zadanie(int ID)
    {
        this.ID = ID;
    }
    
    public Zadanie(int ID, int czesc, int ktoraMaszyna, int czasTrwania, int czasZakonczenia, Zadanie zadanie2) {
        this.ID = ID;
        this.czesc = czesc;
        this.ktoraMaszyna = ktoraMaszyna;
        this.czasTrwania = czasTrwania;
        this.czasZakonczenia = czasZakonczenia;
        this.zadanie2 = zadanie2;
    }

    public Zadanie(int ID, int czesc, int ktoraMaszyna, int czasTrwania, int czasZakonczenia) {
        this.ID = ID;
        this.czesc = czesc;
        this.ktoraMaszyna = ktoraMaszyna;
        this.czasTrwania = czasTrwania;
        this.czasZakonczenia = czasZakonczenia;
    }

    public Zadanie(){}

    public Zadanie(Zadanie other)
    {
        this.ID = other.ID;
        this.czesc = other.czesc;
        this.ktoraMaszyna = other.ktoraMaszyna;
        this.czasTrwania = other.czasTrwania;
        this.czasZakonczenia = other.czasZakonczenia;
        this.zadanie2 = other.zadanie2;
    }

    public void taskAssignment(List<Zadanie> lista, int i, int k)
    {
        Random generator = new Random();
        lista.get(i).ID = i;
        lista.get(i).czasZakonczenia = 0;
        int whichMachine = generator.nextInt(2);
        lista.get(i).czesc = 0;
        lista.get(i).ktoraMaszyna = 1 - whichMachine;
        int czasTrwania2 = generator.nextInt(k)+1;
        lista.get(i).czasTrwania = generator.nextInt(k)+1;
        lista.get(i).zadanie2 = new Zadanie(i,1,whichMachine, czasTrwania2, 0, lista.get(i));
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCzesc() {
        return czesc;
    }

    public void setCzesc(int czesc) {
        this.czesc = czesc;
    }

    public int getKtoraMaszyna() {
        return ktoraMaszyna;
    }

    public void setKtoraMaszyna(int ktoraMaszyna) {
        this.ktoraMaszyna = ktoraMaszyna;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public Integer getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public void setCzasZakonczenia(Integer czasZakonczenia) {
        this.czasZakonczenia = czasZakonczenia;
    }

    public Zadanie getZadanie2() {
        return zadanie2;
    }

    public void setZadanie2(Zadanie zadanie2) {
        this.zadanie2 = zadanie2;
    }


    public Zadanie clone(){
        Zadanie t = new Zadanie();
        t.ID = this.ID;
        t.czasTrwania = this.czasTrwania;
        t.czasZakonczenia = this.czasZakonczenia;
        t.ktoraMaszyna = this.ktoraMaszyna;
        t.czesc = this.czesc;
        t.zadanie2 = new Zadanie();
        t.zadanie2.ID = this.zadanie2.ID;
        t.zadanie2.czasTrwania = this.zadanie2.czasTrwania;
        t.zadanie2.czasZakonczenia = this.zadanie2.czasZakonczenia;
        t.zadanie2.ktoraMaszyna = this.zadanie2.ktoraMaszyna;
        t.zadanie2.czesc = this.zadanie2.czesc;
        return t;
    }

    public Zadanie clone2(){
        Zadanie t = new Zadanie();
        t.ID = this.zadanie2.ID;
        t.czasTrwania = this.zadanie2.czasTrwania;
        t.czasZakonczenia = this.zadanie2.czasZakonczenia;
        t.ktoraMaszyna = this.zadanie2.ktoraMaszyna;
        t.czesc = this.zadanie2.czesc;
        t.zadanie2 = new Zadanie();
        t.zadanie2.ID = this.ID;
        t.zadanie2.czasTrwania = this.czasTrwania;
        t.zadanie2.czasZakonczenia = this.czasZakonczenia;
        t.zadanie2.ktoraMaszyna = this.ktoraMaszyna;
        t.zadanie2.czesc = this.czesc;
        return t;
    }


}
