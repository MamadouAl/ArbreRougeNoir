import java.util.Random;

public class MainTestRech {
    public static void main(String[] args) {
        //Taille des arbres
        int taille = 70000;
        int incremente = 7000;
        ABR<Integer> abr = new ABR<>();
        ARN<Integer> arn = new ARN<>();

        //stocke les valeurs à chercher
        int[] stockeValeur = new int[taille];

        // Test du cas moyen où les clefs sont ajoutées en ordre aléatoire
        System.out.println("***** Recherche de clefs aléatoires *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_1(ms)", "ARN_1(ms)");
        // Construction de l'ABR et l'ARN
        Random random = new Random();
        for (int i = incremente; i < taille; i += incremente) {
            for (int k = 0; k < i; k++) {
                stockeValeur[k] = random.nextInt(1000);
            }

            //Ajout dans ABR ET L'ARN
            for (int indice = 0; indice < i; indice++) {
                abr.add(stockeValeur[indice]);
                 arn.add(stockeValeur[indice]);
            }

            //Recherche dans ABR
            long t0 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                abr.contains(stockeValeur[indice]);
            }
            long t1 = System.currentTimeMillis() - t0;

            //Recherche dans ARN
            long t2 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                arn.contains(stockeValeur[indice]);
            }
            long t3 = System.currentTimeMillis() - t2;

            System.out.printf("%12d%12d%12d%n", i, t1, t3);
        }


        ABR<Integer> abr2 = new ABR<>();
        ARN<Integer> arn2 = new ARN<>();


        System.out.println("***** Recherche de clefs croissantes *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_2(ms)", "ARN_2(ms)");
        for(int indice = incremente; indice < taille; indice += incremente) {
            for (int i = 0; i < indice; i++) {
                abr2.add(i);
                arn2.add(i);
            }
            long t0 = System.currentTimeMillis();
            for (int i = 0; i <2* indice; i++) {
                abr2.contains(i);
            }
            long t1 = System.currentTimeMillis() - t0;

            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 2*indice; i++) {
                arn2.contains(i);
            }
            long t3 = System.currentTimeMillis() - t2;
            System.out.printf("%12d%12d%12d%n", indice, t1, t3);
        }

    }
}
