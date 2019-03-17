/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheurystyka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Elater
 */
public class Krzyzowanie {
   
    public List<Zadanie> uzupelnij(int [] zm, List<Zadanie> listaA1, List<Zadanie> listaA2, List<Przerwa> listaPrzerwan1, List<Przerwa> listaPrzerwan2) {
        //Generator Losowy
        int czasTrwania0 = 0;
        int czasTrwania1 = 0;
        int nextM0 = 0;
        int nextM1 = 0;
        

        for (int taskNumber = 0; taskNumber < zm[0];) {


            if ((listaA1.get(taskNumber).czasTrwania < listaPrzerwan1.get(nextM0).czasGotowosci - czasTrwania0)) 
            {
                czasTrwania0 += listaA1.get(taskNumber).czasTrwania;
                listaA1.get(taskNumber).czasZakonczenia = czasTrwania0;
                taskNumber++;
            }
            else 
            {
                nextM0++;
                czasTrwania0 = listaPrzerwan1.get(nextM0 - 1).czasGotowosci + listaPrzerwan1.get(nextM0 - 1).czasTrwania;
            }
        }
        
        for (int taskNumber = 0; taskNumber < zm[0];) 
        {
            for (int i = 0; i < zm[0]; i++) 
            {
                if (listaA2.get(taskNumber).ID ==listaA1.get(i).ID)
                {
                    if(listaA1.get(i).czasZakonczenia>czasTrwania1) 
                    {
                        for (int j = 0; j < listaPrzerwan2.size(); j++) 
                        {
                            if (listaPrzerwan2.get(nextM1).czasGotowosci<listaA1.get(i).czasZakonczenia)
                                    {
                                        nextM1++;
                                    }
                            else break;
                        }
                        czasTrwania1=listaA1.get(i).czasZakonczenia;
                    }
                    break;
                    
                }
            }
            //if(listaA2.get(taskNumber).zadanie2.czasZakonczenia>czasTrwania1) czasTrwania1=listaA2.get(taskNumber).zadanie2.czasZakonczenia;
            if ((listaA2.get(taskNumber).czasTrwania < listaPrzerwan2.get(nextM1).czasGotowosci - czasTrwania1)) 
            {
                czasTrwania1 += listaA2.get(taskNumber).czasTrwania;
                listaA2.get(taskNumber).czasZakonczenia = czasTrwania1;
                taskNumber++;
            }
            else 
            {
                nextM1++;
                czasTrwania1 = listaPrzerwan2.get(nextM1 - 1).czasGotowosci + listaPrzerwan2.get(nextM1 - 1).czasTrwania;
            }
        }
             
        
            Collections.sort(listaA1, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.ID < o2.ID ? -1 :  0;
                    }
                });
            
            Collections.sort(listaA2, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.ID < o2.ID ? -1 :  0;
                    }
                });
        
        
        List<Zadanie> listaRozwiazan = new ArrayList<>();    
        for (int i = 0; i < zm[0]; i++) 
        {
            listaA2.get(i).zadanie2=null;
            Zadanie zad = new Zadanie(listaA1.get(i).ID,listaA1.get(i).czesc,listaA1.get(i).ktoraMaszyna,listaA1.get(i).czasTrwania,listaA1.get(i).czasZakonczenia, listaA2.get(i));
            listaRozwiazan.add(zad);
        }
                Collections.sort(listaRozwiazan, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.czasZakonczenia < o2.czasZakonczenia ? -1 :  0;
                    }
                });
            
            
        return listaRozwiazan;
       /*
        for (Zadanie l : listaZadan.get(los))    
        {
          listaZadan1a.add(l.clone()); 
          listaZadan1b.add(l.clone2()); 
        }
        /*
        for (int i = 0; i < listaZadan.size(); i++) {
            Collections.sort(listaZadan, new Comparator<Zadanie>() {
                public int compare(Zadanie o1, Zadanie o2) {
                    return o1.czasZakonczenia < o2.czasZakonczenia ? -1 : (o1.czasZakonczenia > o2.czasZakonczenia) ? 1 : 0;
                    }
                });
            }*/

                        
/*}
                        
        System.out.println("nowa iteracja!!!!!");
        for (int i = 0; i < listaZadan.size(); i++) {
            System.out.print(listaZadan.get(i).zadanie2.czasZakonczenia + " ");
        }*/
      
    }
     public List<List<Zadanie>> mutacja(Paczuszka paczuszka, List<Zadanie> listaZadan)
    {
        List<Zadanie> listaZadan2 = new ArrayList<>();
        Random generator = new Random();

        if(paczuszka.ktory == 0)
        {
            paczuszka.ktory = 1;
            int x1 = 0;
            int x2 = 0;
            while (x1 == x2) {
                x1 = generator.nextInt(paczuszka.zm[0]);
                x2 = generator.nextInt(paczuszka.zm[0]);
            }

            for (int i = 0; i < listaZadan.size(); i++) {
                if (i == x1) {
                    listaZadan2.add(listaZadan.get(x2).clone());
                } else if (i == x2) {
                    listaZadan2.add(listaZadan.get(x1).clone());
                } else {
                    listaZadan2.add(listaZadan.get(i).clone());
                }
            }
        }
        else
            {
                paczuszka.ktory = 0;
                int x1 = 0;
                int x2 = 0;
                while (x1 == x2) {
                    x1 = generator.nextInt(paczuszka.zm[0])+paczuszka.zm[0];
                    x2 = generator.nextInt(paczuszka.zm[0])+paczuszka.zm[0];
                }
                for (int i = 0; i < listaZadan.size(); i++) {
                    if (i == x1) {
                        listaZadan2.add(listaZadan.get(x2).clone());
                    } else if (i == x2) {
                        listaZadan2.add(listaZadan.get(x1).clone());
                    } else {
                        listaZadan2.add(listaZadan.get(i).clone());
                    }
                }
            }
        
        List<Zadanie> listaA = new ArrayList<>();
        List<Zadanie> listaB = new ArrayList<>();
        for (int i = 0; i < paczuszka.zm[0]; i++) 
        {
            listaA.add(listaZadan.get(i).clone());
        }
        for (int i = paczuszka.zm[0]; i < 2*paczuszka.zm[0]; i++) {
            listaB.add(listaZadan.get(i).clone2());
        }
        List<List<Zadanie>> dwieListy = new ArrayList<>();
        return dwieListy;
    }
    
    public void dodaj(List<Zadanie> ktora, List<Zadanie> doKtorej, int polowka, int zm)
    {
            for (int i = polowka; i < zm; i++) 
            {
                for (int j = 0; j < zm; j++) 
                {
                    int czyDodac=0;
                    for (int k = 0; k < i; k++)                         
                        if(ktora.get(j).ID!=doKtorej.get(k).ID) 
                            czyDodac++;                    
                    if(czyDodac==i) { doKtorej.add(ktora.get(j)); break;}
                    
                }
            }
    }
    
    public List<List <Zadanie>> krzyzuj(List<List <Zadanie>> listaZadan, int zm)
    {   
        //int zm=10;
        int podzial=3;
        
        int polowka=podzial;
        Random generator = new Random();
        int los,los2;
        
        List<Zadanie> listaZadan1a = new ArrayList<>();
        List<Zadanie> listaZadan1b = new ArrayList<>();
        List<Zadanie> listaZadan2a = new ArrayList<>();
        List<Zadanie> listaZadan2b = new ArrayList<>();

        
        List<Zadanie> listaA1 = new ArrayList<>();
        List<Zadanie> listaA2 = new ArrayList<>();
        List<Zadanie> listaB1 = new ArrayList<>();
        List<Zadanie> listaB2 = new ArrayList<>();
        do
        {
        los = generator.nextInt(listaZadan.size());
        los2 = generator.nextInt(listaZadan.size()); 
        
        /*for (int i = 0; i < 10; i++) 
        {
            System.out.print(listaZadan.get(los).get(i).getID() + " ");
        }
            System.out.println("");
        for (int i = 0; i < 10; i++) 
        {
            System.out.print(listaZadan.get(los2).get(i).getID() + " ");
        }
            System.out.println("");*/
        
            if(los!=los2)
            {
                System.out.println("los = " + los + "los2 = " +los2);
                
                for (Zadanie l : listaZadan.get(los))    
                {
                    listaZadan1a.add(l.clone()); 
                    listaZadan1b.add(l.clone2()); 
                }
                for (Zadanie l : listaZadan.get(los2))    
                {
                    listaZadan2a.add(l.clone()); 
                    listaZadan2b.add(l.clone2()); 
                } 
/*
                for (int i = 0; i < zm; i++) {
                System.out.print(listaZadan1a.get(i).ID + " ");            
                }System.out.println("");       
                for (int i = 0; i < zm; i++) 
                {
                    System.out.print(listaZadan1b.get(i).ID + " ");            
                }System.out.println("");
                for (int i = 0; i < zm; i++) 
                {
                    System.out.print(listaZadan2a.get(i).ID + " ");            
                }System.out.println("");
                for (int i = 0; i < zm; i++) 
                {
                    System.out.print(listaZadan2b.get(i).ID + " ");            
                }System.out.println("");
*/

                for (int i = 0; i < podzial; i++) 
                {
                    listaA1.add(listaZadan1a.get(i).clone());
                    listaA2.add(listaZadan1b.get(i).clone2());
                    listaB1.add(listaZadan2a.get(i).clone());   
                    listaB2.add(listaZadan2b.get(i).clone2());     
                }

                dodaj(listaZadan1a,listaB1,polowka, zm);
                dodaj(listaZadan1b,listaB2,polowka, zm);
                dodaj(listaZadan2a,listaA1,polowka, zm);
                dodaj(listaZadan2b,listaA2,polowka, zm);           
            }      
        
        }
        while(los==los2);
        /*
        for (int i = 0; i < zm; i++) 
        {
            System.out.print(listaA1.get(i).ID + " ");            
        }System.out.println("");       
        for (int i = 0; i < zm; i++) 
        {
            System.out.print(listaA2.get(i).ID + " ");            
        }System.out.println("");
        for (int i = 0; i < zm; i++) 
        {
            System.out.print(listaB1.get(i).ID + " ");            
        }System.out.println("");
        for (int i = 0; i < zm; i++) 
        {
            System.out.print(listaB2.get(i).ID + " ");            
        }System.out.println("");
        /*
        List <List <Zadanie>> dwieListy = new ArrayList<>();
        for (Zadanie z:listaA2)
        {
            listaA1.add(z.clone2());
        }
        for (Zadanie z:listaB2)
        {
            listaB1.add(z.clone2());
        }
        dwieListy.add(listaA1);
        dwieListy.add(listaB1);*/
                
        List <List <Zadanie>> czteryListy = new ArrayList<>();
        czteryListy.add(listaA1);
        czteryListy.add(listaA2);
        czteryListy.add(listaB1);
        czteryListy.add(listaB2);
        
        return czteryListy;
    }
}
