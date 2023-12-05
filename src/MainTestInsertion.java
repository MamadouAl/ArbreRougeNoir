import java.util.Random;

public class MainTestInsertion {
    public static void main(String[] args) {
        // Taille_max des arbres
        int taille = 100_000;
        int incremente = 10_000;
        //stocke les valeurs à chercher
        int[] stockeValeur = new int[taille];

        // Test du cas moyen où les clefs sont ajoutées en ordre aléatoire
        System.out.println("***** Cas moyen : Ajout de clefs aléatoires *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_1(ms)", "ARN_1(ms)");
        // Construction de l'ABR et l'ARN
        ABR<Integer> abr1 = new ABR<>();
        ARN<Integer> arn1 = new ARN<>();
        Random random = new Random();

        for (int i = incremente; i < taille; i += incremente) {

            for (int k = 0; k < i; k++) {
                stockeValeur[k] = random.nextInt(1000);
            }

            //Ajout dans ABR
            long t0 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                abr1.add(stockeValeur[indice]);
            }
            long t1 = System.currentTimeMillis() - t0;

            //Ajout dans ARN
            long t2 = System.currentTimeMillis();
            for (int indice = 0; indice < i; indice++) {
                arn1.add(stockeValeur[indice]);
            }
            long t3 = System.currentTimeMillis() - t2;

            System.out.printf("%12d%12d%12d%n", i, t1, t3);
        }


        // Test du cas moyen où les clefs sont ajoutées en ordre aléatoire
        System.out.println("\n***** Cas Defavorable : Ajout de clefs dans l'ordre croissant *************");
        System.out.printf("%12s%12s%12s%n", "TAILLE", "ABR_2(ms)", "ARN_2(ms)");
        // Construction de l'ABR et l'ARN
        ABR<Integer> abr2 = new ABR<>();
        ARN<Integer> arn2 = new ARN<>();

        for (int indice = 0; indice < taille; indice += incremente) {
            for (int k = 0; k < indice; k++) {
                stockeValeur[k] = k;
            }

            //Ajout dans ABR
            long t0 = System.currentTimeMillis();
            for (int j = 0; j < indice; j++) {
                abr2.add(stockeValeur[j]);
            }
            long t1 = System.currentTimeMillis() - t0;

            //Ajout dans ARN
            long t2 = System.currentTimeMillis();
            for (int j = 0; j < indice; j++) {
                arn2.add(stockeValeur[j]);
            }
            long t3 = System.currentTimeMillis() - t2;
            System.out.printf("%12d%12d%12d%n", indice, t1, t3);
        }

    }

}