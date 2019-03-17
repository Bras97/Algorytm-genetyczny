package metaheurystyka;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.util.*;


public class Main  {
    public static void main (String[] args) throws InterruptedException, FileNotFoundException  {

        //         0  1  2  3  4   5    6  7    8
        //int[] zm ={20,30,15,15,100,3000,50,1700,10};
        //0-liczba zadań, 1- liczba przerwań, 2-ile przerwań na 1 maszynie, 3-ile przerwań na 2 maszynie
        //4-max długość trwania przerwy, 5- max czas pojawienia się maintanance'a
        //6- max czas trwania zadania, 7 - max czas zaczecia sie przerwy
        //8- max prob wejscia przed przerwą
        //         0   1  2  3  4  5         6   7   8  9   10  11  12 13  14
        int[] zm ={10, 8, 4, 4, 5, 10000000, 10, 60, 0, 10, 10, 20, 1, 35, 5};
        /*
        0 - liczba zadan; 1 - liczba przerwan; 2 - liczba przerwan na pierwszej maszynie; 3 - liczba przerwan na drugiej
        maszynie; 4 - max trwania maintance; 5 - dlugosc tabeli przerwan; 6 - max trwania zadania; 7 - max time po jakim
        moze zaczac sie przerwa (4*dwa*3); 8 - max prob wejsc zadania przed maintancem ((int)0/dziesiec);
        9 - zapetlenie wyboru wartosci z macierzy; 10 - max liczba rozwiazan; 11 - max randomowych rozwiazan
        12 - spadek prawdopodobieństwa losowych; zadań 13 - procent zanikania,
        14 - procent wygladzania
         */

        Random generator = new Random();
        //Stworz maintance
        List<Przerwa> listaPrzerwan = new ArrayList<>();
        boolean[] maintaceBoolTab = new boolean[zm[5]];
        List<Przerwa> listaPrzerwan1 = new ArrayList<>();
        List<Przerwa> listaPrzerwan2 = new ArrayList<>();
        //MacierzFeromonowa macierzFeromonowa = new MacierzFeromonowa(zm[0]);
        Paczuszka2 paczuszka2 = new Paczuszka2();

        for (int i = 0; i < zm[5]; i++) {
            maintaceBoolTab[i] = false;
        }
        int firstmain = zm[2];
        int secondmain = zm[3];
        int maindown = 0;
        for (int i = 0; i < zm[1]; i++) {
            listaPrzerwan.add(new Przerwa());
            maindown = listaPrzerwan.get(i).maintanceAssignment(listaPrzerwan, i, firstmain, secondmain);
            if (maindown == 1)
                firstmain--;
            else
                secondmain--;
            if (listaPrzerwan.get(i).ktoraMaszyna == 0) {
                //tabM0 = listaPrzerwan.get(i).separate(listaPrzerwan.get(i), tabM0); //przerwy na pierwszej maszynie
                listaPrzerwan1.add(listaPrzerwan.get(i));
            } else {
                //tabM1 = listaPrzerwan.get(i).separate(listaPrzerwan.get(i), tabM1); //przerwy na drugiej maszynie
                listaPrzerwan2.add(listaPrzerwan.get(i));
            }
            maintaceBoolTab = listaPrzerwan.get(i).maintanceImplementation(listaPrzerwan, i, zm[4], zm[7], maintaceBoolTab);
        }
        listaPrzerwan1.add(new Przerwa(0, 1000000));
        listaPrzerwan2.add(new Przerwa(0, 1000000));

        /*
        Collections.sort(listaAdama, new Komparator());
        */


        //Stworz Zadanie
        List<Zadanie> listaZadan = new ArrayList<>();
        for (int i = 0; i < zm[0]; i++) {
            listaZadan.add(new Zadanie());
            listaZadan.get(i).taskAssignment(listaZadan, i, zm[6]);
        }

/*

        Collections.sort(listaPrzerwan1, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });

        Collections.sort(listaPrzerwan2, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });

        Collections.sort(listaPrzerwan, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });





        List<List<Zadanie>> listaRozwiazan = new ArrayList<>();
        Generator gen = new Generator();
        List<Zadanie> [] tabtaskow = new List[zm[11]];
        List<Zadanie> listRozwiazanek = new ArrayList<>();


        for (int i = 0; i < zm[11]; i++) {
            listRozwiazanek = gen.generatorLosowy(zm, listaZadan, listaPrzerwan1, listaPrzerwan2);
            listaRozwiazan.add(new ArrayList<Zadanie>());
            for (Zadanie l : listRozwiazanek) {
                listaRozwiazan.get(i).add(l.clone());
            }
        }

        for (int i = 0; i < listaRozwiazan.size(); i++) {
            Collections.sort(listaRozwiazan.get(i), new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.zadanie2.czasZakonczenia > o2.zadanie2.czasZakonczenia ? -1 : (o1.zadanie2.czasZakonczenia < o2.zadanie2.czasZakonczenia) ? 1 : 0;
                    //return o1.czasZakonczenia.compareTo(o2.czasZakonczenia);
                }
            });
        }
        
        Paczuszka paczuszka = new Paczuszka(zm);
        Krzyzowanie krzyz = new Krzyzowanie();
        
        krzyz.mutacja(paczuszka, listaZadan);
        
        for (int i = 0; i < listaRozwiazan.size(); i++) {
            System.out.println(i+ ". " + gen.obliczFunkcjeCelu(listaRozwiazan.get(i)));
        }
        
        List <List <Zadanie>> czteryListy = krzyz.krzyzuj(listaRozwiazan, zm[0]);
        List <Zadanie> poKrzyzowce = krzyz.uzupelnij(zm, czteryListy.get(0), czteryListy.get(1), listaPrzerwan1, listaPrzerwan2);
        System.out.println(gen.obliczFunkcjeCelu(poKrzyzowce) + " ;");
        
/*
        double [] tabFunCel = new double [zm[10]];
        tabFunCel = gen.turniej(listaRozwiazan,zm);
        for (int i = 0; i < zm[10]; i++) {
            System.out.println(tabFunCel[i]);
        }
        */
/*
       //List<List<Zadanie>>listDoubleZadanie = new ArrayList<>();
        for (int i = 0; i < listaRozwiazan.size(); i++) {
            paczuszka2.listDoubleZadanie.add(new ArrayList<>());
            for (Zadanie l : listaRozwiazan.get(i)) {
                paczuszka2.listDoubleZadanie.get(i).add(l.clone());
                paczuszka2.listDoubleZadanie.get(i).add(l.clone2());
            }
        }
        for (int i = 0; i < paczuszka2.listDoubleZadanie.size(); i++) {
            Collections.sort(paczuszka2.listDoubleZadanie.get(i), new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.zadanie2.czasZakonczenia < o2.zadanie2.czasZakonczenia ? -1 : (o1.zadanie2.czasZakonczenia > o2.zadanie2.czasZakonczenia) ? 1 : 0;
                }
            });
        }

        for (int i = 0; i < listaRozwiazan.size(); i++) {
            Collections.sort(listaRozwiazan.get(i), new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.zadanie2.czasZakonczenia < o2.zadanie2.czasZakonczenia ? -1 : (o1.zadanie2.czasZakonczenia > o2.zadanie2.czasZakonczenia) ? 1 : 0;
                }
            });
        }



        /*
        System.out.println("IDIDIDIDIDID");
        for (int i = 0; i < listDoubleZadanie.size(); i++) {
            for (int k = 0; k < listDoubleZadanie.get(i).size(); k++) {
                System.out.print(listDoubleZadanie.get(i).get(k).ID + " ");
            }
            System.out.println();
        }
        */

//Sortuj listy przerwan
        Collections.sort(listaPrzerwan1, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });

        Collections.sort(listaPrzerwan2, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });

        Collections.sort(listaPrzerwan, new Comparator<Przerwa>() {
            public int compare(Przerwa o1, Przerwa o2) {
                return o1.czasGotowosci.compareTo(o2.czasGotowosci);
            }
        });





        List<List<Zadanie>> listaRozwiazan = new ArrayList<>();
        Generator gen = new Generator();
        List<Zadanie> [] tabtaskow = new List[zm[11]];
        List<Zadanie> listRozwiazanek = new ArrayList<>();


        for (int i = 0; i < zm[11]; i++) { // max randomowych rozwiazan
            listRozwiazanek = gen.generatorLosowy(zm, listaZadan, listaPrzerwan1, listaPrzerwan2);
            listaRozwiazan.add(new ArrayList<Zadanie>());
            for (Zadanie l : listRozwiazanek) {
                listaRozwiazan.get(i).add(l.clone());
            }
        }

        for (int i = 0; i < listaRozwiazan.size(); i++) {
            Collections.sort(listaRozwiazan.get(i), new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.zadanie2.czasZakonczenia > o2.zadanie2.czasZakonczenia ? -1 : (o1.zadanie2.czasZakonczenia < o2.zadanie2.czasZakonczenia) ? 1 : 0;
                }
            });
        }

        double [] tabFunCel = new double [zm[10]];
        tabFunCel = gen.turniej(listaRozwiazan,zm);
        System.out.println("FUNKCJA CELU");
        for (int i = 0; i < zm[10]; i++) { //max liczba rozwiazan po turnieju
            System.out.print(tabFunCel[i] + " ");
        }

        //szukamy najlepszego rozwiazania
        double min = 1000000000;
        int maxi = 0;
        for (int i = 0; i < listaRozwiazan.size(); i++) {
            if(tabFunCel[i]<min)
            {
                min = tabFunCel[i];
                maxi = i;
            }
        }

        Paczuszka paczuszka = new Paczuszka();
        paczuszka.bestSolution = listaRozwiazan.get(maxi);
        paczuszka.bestFunCel = tabFunCel[maxi];
        System.out.println("Najlepsza fun celu: " + paczuszka.bestFunCel);
        paczuszka.randomSolution = listaRozwiazan.get(maxi);
        paczuszka.randomFunCel = paczuszka.bestFunCel = tabFunCel[maxi];
        paczuszka.zm=zm;


        for (int a = 0; a < listaRozwiazan.size(); a++) {
            for (int i = 0; i < zm[0]; i++) {
                if(listaRozwiazan.get(a).get(i).czesc == 0)
                    listaRozwiazan.get(a).add(listaRozwiazan.get(a).get(i).clone2());
                else
                    listaRozwiazan.get(a).add(listaRozwiazan.get(a).get(i).clone());
            }
        }
        
        Krzyzowanie krzyz = new Krzyzowanie();
        krzyz.mutacja(paczuszka, listaRozwiazan.get(0));

        
        Zapis zapis = new Zapis();
       
        List<Zadanie> listaZadan1 = new ArrayList<>();
        List<Zadanie> listaZadan2 = new ArrayList<>();


        for (int i = 0; i < listaZadan.size(); i++) {
            if (listaZadan.get(i).ktoraMaszyna == 0) {
                listaZadan1.add(listaZadan.get(i));
                listaZadan2.add(listaZadan.get(i).zadanie2);
            } else {
                listaZadan2.add(listaZadan.get(i));
                listaZadan1.add(listaZadan.get(i).zadanie2);
            }
        }
        
       Collections.sort(listaZadan1, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.czasZakonczenia < o2.czasZakonczenia ? -1 : 0;
                }
            });
        
            Collections.sort(listaZadan2, new Comparator<Zadanie>() 
            {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.czasZakonczenia < o2.czasZakonczenia ? -1 : 0;
                }
            });
        listaPrzerwan1.remove(listaPrzerwan1.size()-1);
        listaPrzerwan2.remove(listaPrzerwan2.size()-1);
        
       zapis.zapisz(listaPrzerwan1, listaPrzerwan2, listaZadan1, listaZadan2, min,1);
       
    }
}
