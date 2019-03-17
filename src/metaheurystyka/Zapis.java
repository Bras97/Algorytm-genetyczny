package metaheurystyka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    public class Zapis extends Main {

        final int MAX_INTEGER = 100000;
        int[] zm;

        Zapis(){}

        Zapis(int[] zm) {
            this.zm = zm;
        }

        public List<Przerwa> wczytajPrzerwy() throws FileNotFoundException
        {
            List <Przerwa> listaPrzerw = new ArrayList<>();
            File plik = new File("testowa.txt");
            Scanner in = new Scanner(plik);   
            int sizeP=Integer.parseInt(in.nextLine());
            for (int i = 0; i < sizeP; i++) 
            {
                String wszystko = in.nextLine();
		String[] splitedArray = null;
		splitedArray = wszystko.split(",");
                listaPrzerw.add(new Przerwa(Integer.parseInt(splitedArray[0]),Integer.parseInt(splitedArray[1]),Integer.parseInt(splitedArray[2])));
            }
            in.close();
            return listaPrzerw;
        }
        
        public List<Zadanie> wczytajZadania(int ile) throws FileNotFoundException
        {
            List <Zadanie> listaZadan = new ArrayList<>();
            File plik = new File("testowa.txt");
            Scanner in = new Scanner(plik);   
            for (int i = 0; i < ile+1; i++) 
            {
                String x= in.nextLine();
                System.out.println("");
            }
            int sizeZ=Integer.parseInt(in.nextLine());
            for (int i = 0; i < sizeZ; i++) 
            {
                String wszystko = in.nextLine();
		String[] splitedArray = null;
		splitedArray = wszystko.split(",");
                wszystko = in.nextLine();
		String[] splitedArray2 = null;
		splitedArray2 = wszystko.split(",");
                Zadanie zad2 = new Zadanie(Integer.parseInt(splitedArray2[0]),Integer.parseInt(splitedArray2[1]),Integer.parseInt(splitedArray2[2]),Integer.parseInt(splitedArray2[3]),Integer.parseInt(splitedArray2[4]));
                listaZadan.add(new Zadanie(Integer.parseInt(splitedArray[0]),Integer.parseInt(splitedArray[1]),Integer.parseInt(splitedArray[2]),Integer.parseInt(splitedArray[3]),Integer.parseInt(splitedArray[4]),zad2));
            }
            
            in.close();
            return listaZadan;
        }
        
        public void zapiszTest(List<Przerwa> listaPrzerwan, List <Zadanie> listaZadan)
        {
            String nazwa="testowa.txt";
            PrintWriter zapis = null;
            try {
                zapis = new PrintWriter(nazwa);
            } catch (IOException e) {
                System.out.println("Błąd I/O");
            }
            
            zapis.println(listaPrzerwan.size());
            for (int i = 0; i < listaPrzerwan.size(); i++)
            {
                zapis.println(listaPrzerwan.get(i).getKtoraMaszyna() + "," + listaPrzerwan.get(i).getCzasGotowosci() + "," + listaPrzerwan.get(i).getCzasTrwania());
            }
            
            zapis.println(listaZadan.size());
            for (int i = 0; i < listaZadan.size(); i++) 
            {
                zapis.println(listaZadan.get(i).getID() + "," + listaZadan.get(i).getCzesc() + "," + listaZadan.get(i).getKtoraMaszyna()+ "," + listaZadan.get(i).getCzasTrwania()+ "," + listaZadan.get(i).getCzasZakonczenia() );
                zapis.println(listaZadan.get(i).zadanie2.getID() + "," + listaZadan.get(i).zadanie2.getCzesc() + "," + listaZadan.get(i).zadanie2.getKtoraMaszyna()+ "," + listaZadan.get(i).zadanie2.getCzasTrwania()+ "," + listaZadan.get(i).zadanie2.getCzasZakonczenia() );
            }
            zapis.close();
        }
        
        public void zapisz(List<Przerwa> listaPrzerwan1,List<Przerwa> listaPrzerwan2, List<Zadanie> listaZadan1, List<Zadanie> listaZadan2, double rozwiazanie, int iteracja) throws FileNotFoundException
        {
            for (int i = 0; i < listaZadan1.size(); i++) 
            {
                System.out.print("(" + (listaZadan1.get(i).czasZakonczenia-listaZadan1.get(i).czasTrwania) + " " + listaZadan1.get(i).czasZakonczenia +  ") ");
            }System.out.println("");
            for (int i = 0; i < listaPrzerwan1.size(); i++) 
            {
                System.out.print("(" + listaPrzerwan1.get(i).czasGotowosci + " " + (listaPrzerwan1.get(i).czasGotowosci + listaPrzerwan1.get(i).czasTrwania) + ") ");
            }System.out.println("");
            for (int i = 0; i < listaZadan2.size(); i++) 
            {
                System.out.print("(" + (listaZadan2.get(i).czasZakonczenia-listaZadan2.get(i).czasTrwania) + " " + listaZadan2.get(i).czasZakonczenia +  ") ");
            }System.out.println("");
            for (int i = 0; i < listaPrzerwan2.size(); i++) 
            {
                System.out.print("(" + listaPrzerwan2.get(i).czasGotowosci + " " + (listaPrzerwan2.get(i).czasGotowosci + listaPrzerwan2.get(i).czasTrwania) + ") ");
            }System.out.println("");
            
            
            String nazwa="wynik_"+iteracja+".txt";
            PrintWriter zapis = null;
            try {
                zapis = new PrintWriter(nazwa);
            } catch (IOException e) {
                System.out.println("Błąd I/O");
            }
            int i = 0, j = 0, k = 0, l=0; //   Zadanie1    Zadanie2    Przerwanie
            String M1 = "M1: ", M2 = "M2: ";

            int x=0, y=0, z=0, z2=0, w1=0, w2=0, w3=0, w4=0;
            // 1kon 2kon  3mtg 4mtg  1nic  2nic  1mt   2mt
            int m1Nic=0, m2Nic=0, maint1Suma=0, maint2Suma=0;
            

            while (i < listaZadan1.size() || j < listaZadan2.size() || k < listaPrzerwan1.size() || l < listaPrzerwan2.size())
            {
                if (i < listaZadan1.size()) {
                    x = listaZadan1.get(i).getCzasZakonczenia();
                } else {
                    x = MAX_INTEGER;
                }
                if (j < listaZadan2.size()) {
                    y = listaZadan2.get(j).getCzasZakonczenia();
                } else {
                    y = MAX_INTEGER;
                }
                if (k < listaPrzerwan1.size()) {
                    z = listaPrzerwan1.get(k).getCzasGotowosci();
                } else {
                    z = MAX_INTEGER;
                }
                if (l < listaPrzerwan2.size()) {
                    z2 = listaPrzerwan2.get(l).getCzasGotowosci();
                } else {
                    z2 = MAX_INTEGER;
                }
                if(x<=z)
                {
                    M1= M1 +"op"+ listaZadan1.get(i).czesc +"_"+ listaZadan1.get(i).getID()+ ","
                            + (Integer.toString(x-listaZadan1.get(i).getCzasTrwania()))+ "," +
                            Integer.toString(listaZadan1.get(i).getCzasTrwania()) + "; ";
                    i++;
                }
                else
                {
                    if(i==0)
                    {
                        if(z!=0)  
                        {
                           w1++;
                           M1 = M1 + "idle" + w1 + "_M1,0," + z+ "; ";
                           m1Nic+=(z);
                        }
                    }
                    else if(listaZadan1.get(i-1).getCzasZakonczenia()<z)
                    {
                        w1++;
                        M1 = M1 + "idle" + w1 + "_M1," + listaZadan1.get(i-1).getCzasZakonczenia() + "," + (z-listaZadan1.get(i-1).getCzasZakonczenia()) + "; ";
                        m1Nic+=(z-listaZadan1.get(i-1).getCzasZakonczenia());
                    }
                        w3++;
                    M1= M1 +"maint" +w3+ "_M1" + ","
                            +  Integer.toString(z)+ "," +
                            Integer.toString(listaPrzerwan1.get(k).getCzasTrwania()) + ";";
                    maint1Suma+=listaPrzerwan1.get(k).getCzasTrwania();
                    k++;
                }

                if(y<=z2)
                {
                    M2= M2 +"op"+ listaZadan2.get(j).czesc +"_"+ listaZadan2.get(j).getID()+ ","
                            + (Integer.toString(y-listaZadan2.get(j).getCzasTrwania()))+ "," +
                            Integer.toString(listaZadan2.get(j).getCzasTrwania()) + "; ";
                    j++;
                }
                else
                {            
                    if(j==0)
                    {
                        if(z2!=0)  
                        {
                           w2++;
                           M2 = M2 + "idle" + w2 + "_M1,0," + z2 + "; ";
                           m2Nic+=(z2);
                        }
                    }
                    else if(listaZadan2.get(i-1).getCzasZakonczenia()<z)
                    {
                        w2++;
                        M1 = M1 + "idle" + w2 + "_M2," + listaZadan2.get(j-1).getCzasZakonczenia() + "," + (z2-listaZadan2.get(j-1).getCzasZakonczenia()) + "; ";
                        m2Nic+=(z2-listaZadan2.get(j-1).getCzasZakonczenia());
                    }
                    w4++;
                    M2= M2 +"maint" +w3+ "_M2" + "," +  Integer.toString(z2)+ "," +
                            Integer.toString(listaPrzerwan1.get(l).getCzasTrwania()) + ";";
                    maint2Suma+=listaPrzerwan2.get(l).getCzasTrwania();
                    l++;
                }
            }
            System.out.println(M1);
            System.out.println(M2);
            
            zapis.println("***"+iteracja+"***");
            zapis.println(rozwiazanie + "," + rozwiazanie
            );
            zapis.println(M1);
            zapis.println(M2);
            zapis.println(maint1Suma);
            zapis.println(maint2Suma);
            zapis.println(m1Nic);
            zapis.println(m2Nic);
            zapis.println("***EOF***");
            zapis.println("         -~-.___________,-~-");
            zapis.println("         \\__)___________(__/ ");
            zapis.println("          \\_____,___,____/");
            zapis.println("           |____a___a____|");
            zapis.println("           |_____(_)_____|");
            zapis.println("           \\____\\_|_/___/");
            zapis.println("            \\__________/");
            //<< endl << "         ,?_,?_`---?_`___`_" << endl << "        /___/_____: \___\ " << endl << "      ,?__/______:______\___`_" << endl <<
            //"    ,?_____|______:______|_____`_" << endl << "   |_____,?|______:______|`______|" << endl << "   `___,?__-\_____:_____/-__`___,?" << endl << "         /___`____:___,?___\ " << endl << "   ""-_,?______`__:_,?______`_,-""_" << endl <<
            //" /_,-__`_________)_(_________?_,-__\ " << endl << "(_(___`________,?___`________,?___)_)" << endl << " \_\____\____,?_______`_____/____/_/" << endl << "  `_`__,?__/_____________\__`__,?,?" << endl << "    `____-?_______________`-___,?" << endl;      
            zapis.close();
        }
    }
