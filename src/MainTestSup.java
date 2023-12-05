import java.util.Iterator;
import java.util.Random;

public class MainTestSup {
    public static void main(String[] args) {
        int TAILLE = 40_000;
        int incremente = 4000;
        int[] stockeValeur = new int[TAILLE];

        ARN<Integer> arn1 = new ARN<>();
        ARN<Integer> arn2 = new ARN<>();
        ABR<Integer> abr1 = new ABR<>();
        ABR<Integer> abr2 = new ABR<>();

        Random random = new Random();

        System.out.println("***** Suppression de clefs aléatoires *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_2(ms)", "ARN_2(ms)");
        for (int i = incremente; i < TAILLE; i += incremente) {
            for (int k = 0; k < i; k++) {
                stockeValeur[k] = random.nextInt(1000);
            }

            //Ajout dans ABR
            for (int indice = 0; indice < i; indice++) {
                abr1.add(stockeValeur[indice]);
                arn1.add(stockeValeur[indice]);
            }

            //Suppression dans ABR
            long t0 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                abr1.remove(stockeValeur[indice]);
            }
            long t1 = System.currentTimeMillis() - t0;

            //Suppression dans ARN
            long t2 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                arn1.remove(stockeValeur[indice]);
            }
            long t3 = System.currentTimeMillis() - t2;
            System.out.printf("%12d%12d%12d%n", i, t1, t3);
        }

        System.out.println("***** Suppression de clefs croissantes *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_2(ms)", "ARN_2(ms)");
        for (int indice = incremente; indice < TAILLE; indice += incremente) {
            for (int i = 0; i < indice; i++) {
                abr2.add(i);
                arn2.add(i);
            }

            // Suppression avec ABR
            long t0 = System.currentTimeMillis();
            Iterator<Integer> iteratorABR = abr2.iterator();
            while (iteratorABR.hasNext()) {
                int value = iteratorABR.next();
                iteratorABR.remove();
            }
            long t1 = System.currentTimeMillis() - t0;

            // Suppression avec ARN
            long t2 = System.currentTimeMillis();
            Iterator<Integer> iteratorARN = arn2.iterator();
            while (iteratorARN.hasNext()) {
                int value = iteratorARN.next();
                iteratorARN.remove();
            }
            long t3 = System.currentTimeMillis() - t2;

            System.out.printf("%12d%12d%12d%n", indice, t1, t3);
        }


    }
}
