package metaheurystyka;

import java.util.*;





////////////UWAGA////////////////////////
//jednostki czasu: zadanie trwa jedna jednostke czasu i zaczyna sie w zerowej oznacza ze wykonuje sie w zerowej i trwa 0
//aby nie przeskakiwalo na nastepna jednostke czasu i wykonywalo sie w biezacej(tu zerowej) jednostce czasu (zrobione dla czytelniejszego zapisu i odczytu)
//trwa 2 jednostki czasu i zaczyna sie w jeden oznacza: zadanie robi sie w jednostce 1 i 2, trwa 1 (z 1 do 2)
public class Generator {
    public void Generator(){}

    public List<Zadanie> generatorLosowy(int [] zm, List<Zadanie> listaZadan, List<Przerwa> listaPrzerwan1, List<Przerwa> listaPrzerwan2) {
        //Generator Losowy
        int[] tabCheck = new int[zm[0]];
        for (int i = 0; i < zm[0]; i++) {
            tabCheck[i] = 2;
        }
        int count = 0;
        long start6 = System.currentTimeMillis();
        Random generator = new Random(start6);
        int taskNumber = 0;
        int czasTrwania0 = 0;
        int czasTrwania1 = 0;
        int nextM0 = 0;
        int nextM1 = 0;
        int trying = 0; // liczba prob
        int trying2 = 0; // liczba prob
        boolean next;
        boolean next2;

        while (count < zm[0]) {

            next = false;
            next2 = false;
            taskNumber = generator.nextInt(zm[0]);
            if (tabCheck[taskNumber] == 0)
                continue;
            if (tabCheck[taskNumber] == 2) // pierwsza czesc nie zostala jeszcze wykonana - robimy ja!
            {
                if (listaZadan.get(taskNumber).ktoraMaszyna == 0) // na pierwszej maszynie
                {

                    while (true) {
                        if ((listaZadan.get(taskNumber).czasTrwania < listaPrzerwan1.get(nextM0).czasGotowosci - czasTrwania0)) {

                            czasTrwania0 += listaZadan.get(taskNumber).czasTrwania;
                            listaZadan.get(taskNumber).czasZakonczenia = czasTrwania0;
                            tabCheck[taskNumber]--;
                            trying = 0;
                            break;
                        }
                        if (trying == zm[8]) {
                            nextM0++;
                            czasTrwania0 = listaPrzerwan1.get(nextM0 - 1).czasGotowosci + listaPrzerwan1.get(nextM0 - 1).czasTrwania;
                            trying = 0;
                            continue;
                        }
                        trying++;
                        next = true;
                        break;
                    }
                    if (next)
                        continue;
                } else // na drugiej maszynie
                {
                    while (true) {
                        if ((listaZadan.get(taskNumber).czasTrwania < listaPrzerwan2.get(nextM1).czasGotowosci - czasTrwania1)) {
                            czasTrwania1 += listaZadan.get(taskNumber).czasTrwania;
                            tabCheck[taskNumber]--;
                            trying2 = 0;
                            listaZadan.get(taskNumber).czasZakonczenia = czasTrwania1;
                            break;
                        }
                        if (trying2 == zm[8]) {
                            nextM1++;
                            czasTrwania1 = listaPrzerwan2.get(nextM1 - 1).czasGotowosci + listaPrzerwan2.get(nextM1 - 1).czasTrwania;
                            trying2 = 0;
                            continue;
                        }
                        trying2++;
                        next2 = true;
                        break;
                    }
                    if (next2)
                        continue;
                }
            } else {
                if (listaZadan.get(taskNumber).zadanie2.ktoraMaszyna == 0) // na pierwszej maszynie
                {
                    while (true) {
                        if (((listaZadan.get(taskNumber).zadanie2.czasTrwania < listaPrzerwan1.get(nextM0).czasGotowosci - czasTrwania0) && (listaZadan.get(taskNumber).czasZakonczenia <= czasTrwania0))) {

                            czasTrwania0 += listaZadan.get(taskNumber).zadanie2.czasTrwania;
                            listaZadan.get(taskNumber).zadanie2.czasZakonczenia = czasTrwania0;
                            tabCheck[taskNumber]--;
                            trying = 0;
                            count++;
                            break;
                        }
                        if ((listaZadan.get(taskNumber).zadanie2.czasTrwania < listaPrzerwan1.get(nextM0).czasGotowosci - listaZadan.get(taskNumber).czasZakonczenia) && (listaZadan.get(taskNumber).czasZakonczenia) >= czasTrwania0) {
                            czasTrwania0 += (listaZadan.get(taskNumber).czasZakonczenia - czasTrwania0) + listaZadan.get(taskNumber).zadanie2.czasTrwania;
                            listaZadan.get(taskNumber).zadanie2.czasZakonczenia = czasTrwania0;
                            tabCheck[taskNumber]--;

                            trying = 0;
                            count++;
                            break;
                        }
                        if (trying == zm[8]) {
                            nextM0++;
                            czasTrwania0 = listaPrzerwan1.get(nextM0 - 1).czasGotowosci + listaPrzerwan1.get(nextM0 - 1).czasTrwania;
                            trying = 0;
                            continue;
                        }
                        trying++;
                        next = true;
                        break;
                    }
                    if (next)
                        continue;
                } else // na drugiej maszynie
                {
                    while (true) {
                        if (((listaZadan.get(taskNumber).zadanie2.czasTrwania < listaPrzerwan2.get(nextM1).czasGotowosci - czasTrwania1) && (listaZadan.get(taskNumber).czasZakonczenia <= czasTrwania1))) {
                            czasTrwania1 += listaZadan.get(taskNumber).zadanie2.czasTrwania;
                            listaZadan.get(taskNumber).zadanie2.czasZakonczenia = czasTrwania1;
                            tabCheck[taskNumber]--;
                            trying2 = 0;
                            count++;
                            break;
                        }
                        if ((listaZadan.get(taskNumber).zadanie2.czasTrwania < listaPrzerwan2.get(nextM1).czasGotowosci - listaZadan.get(taskNumber).czasZakonczenia) && (listaZadan.get(taskNumber).czasZakonczenia >= czasTrwania1)) {
                            czasTrwania1 += (listaZadan.get(taskNumber).czasZakonczenia - czasTrwania1) + listaZadan.get(taskNumber).zadanie2.czasTrwania;
                            listaZadan.get(taskNumber).zadanie2.czasZakonczenia = czasTrwania1;
                            tabCheck[taskNumber]--;
                            trying2 = 0;
                            count++;
                            break;
                        }
                        if (trying2 == zm[8]) {
                            nextM1++;
                            czasTrwania1 = listaPrzerwan2.get(nextM1 - 1).czasGotowosci + listaPrzerwan2.get(nextM1 - 1).czasTrwania;
                            trying2 = 0;
                            continue;
                        }
                        trying2++;
                        next2 = true;
                        break;
                    }
                    if (next2)
                        continue;
                }
            }
        }


        for (int i = 0; i < listaZadan.size(); i++) {
            Collections.sort(listaZadan, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.czasZakonczenia < o2.czasZakonczenia ? -1 : (o1.czasZakonczenia > o2.czasZakonczenia) ? 1 : 0;
                }
            });
        }
/*
        System.out.println("nowa iteracja!!!!!");
        for (int i = 0; i < listaZadan.size(); i++) {
            System.out.print(listaZadan.get(i).zadanie2.czasZakonczenia + " ");
        }*/
      
        return listaZadan;
    }

    public long obliczFunkcjeCelu(List<Zadanie> list) {
        long sum = 0;
        for(int i = 0; i < list.size(); i++) {
            sum += list.get(i).zadanie2.czasZakonczenia;
            //System.out.println("end czasTrwania wynosi: " + list.get(i).zadanie2.czasZakonczenia );
        }
        return sum;
    }

   
    
    public List<Zadanie> rozwiazanie(List<List<Zadanie>> listaRozwiazan)
    {
        int x = 0;
        double mini = 1000000;
        double cel=0;
        for (int i = 0; i < listaRozwiazan.size(); i++)
        {
            cel=obliczFunkcjeCelu(listaRozwiazan.get(i));
            if(cel< mini)
            {
                x=i;
                mini=cel;
            }
        }
        
        return listaRozwiazan.get(x);
    }
    
    public double[] turniej(List<List<Zadanie>> list, int [] zm) {
        boolean [] lostTab = new boolean[list.size()];
        double [] winTab = new double[list.size()];
        int loser = list.size() - zm[10];
        int player1;
        int player2;
        Random generator = new Random();

        for (int i = 0; i < list.size(); i++) {
            lostTab[i] = true;
            winTab[i] = obliczFunkcjeCelu(list.get(i));
        }

        for (int j = 0; j < loser; j++) {
            double flo = generator.nextInt(32000);
            player1 = (int) (flo / 32000 * list.size());
            flo = generator.nextInt(32000);
            player2 = (int) (flo / 32000 * list.size());

            if((player1 == player2) || (!lostTab[player1]) || (!lostTab[player2]))
                j--;
            else
                {
                    if(winTab[player1]<winTab[player2])
                        lostTab[player2] = false;
                    else
                        lostTab[player1] = false;
                }
        }
        for (int j = list.size() - 1; j >= 0 ; j--) {
            if(!lostTab[j])
                list.remove(j);
        }

        double [] winTab2 = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            winTab2[i] = obliczFunkcjeCelu(list.get(i));
        }
        return winTab2;

        }
    }
