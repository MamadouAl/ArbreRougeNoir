import java.util.Random;

public class MainTest {
    public static void main(String[] args) {
        // Taille_max des arbres
        int taille = 1_000_000;
        //stocke les valeurs à chercher
        int [] stockeValeur = new int[2*taille + 1];


        // Test du cas moyen où les clefs sont ajoutées en ordre aléatoire
       System.out.println("***** Cas moyen : clefs aléatoires *************\n");

        System.out.println("Ajout dans les arbres binaires -----------------------");
        // Construction de l'ABR
        ABR<Integer> abr1 = new ABR<>();

        // Construction de l'ARN
        ARN<Integer> arn1 = new ARN<>();
        for (int n = 0; n <= taille; n += 10_000) {
            Random random = new Random();
            int valeur = random.nextInt(taille);

            stockeValeur[n] = valeur; //stocke les valeurs à chercher
            long t0 = System.nanoTime();
            abr1.add(valeur);
            long t1 = System.nanoTime();

            long t2 = System.nanoTime();
            arn1.add(valeur);
            long t3 = System.nanoTime();

            // Mesure du temps de construction
            System.out.println("AJOUT -> TAILLE = " + n + ", ABR_1 = " + (t1 - t0) / 1e9 + " s  |  ARN_1 = " + (t3 - t2) / 1e9 + " s");
        }

        System.out.println("Recherche des clefs_________________________________________________");
        //Recherche de clefs existantes et non existantes
        for (int n = 0; n <= 2*taille; n += 10_000) {
            long tempsABR = rechercherABR(abr1, stockeValeur[n]);
            long tempsARN = rechercherARN(arn1, stockeValeur[n]);
            System.out.println("RECHERCHE -> TAILLE = " + n + ", ABR_1("+abr1.contains(stockeValeur[n])+") : " +  tempsABR/1e6 + " ms  " +
                                                                "|  ARN_1("+arn1.contains(stockeValeur[n])+") "  + tempsARN/1e6 + " ms");
        }



        /**
         * Test du cas le plus défavorable pour les ABR où les clefs sont ajoutées
         *  de la plus petite à la plus grande. Mesurez le temps de construction des arbres.
         */
        
        System.out.println("\n***** Cas Défavorable : clefs croissants *************");
        System.out.println("Ajout dans les arbres binaires -----------------------");
        // Construction de l'ABR
        ABR<Integer> abr2 = new ABR<>();

        // Construction de l'ARN
        ARN<Integer> arn2 = new ARN<>();
        for (int n =0; n < taille; n+=10_000){

            // Construction de l'ABR
            long t0 = System.nanoTime();
            abr2.add(n);
            long t1 = System.nanoTime();

            // Construction de l'ARN
            long t2 = System.nanoTime();
            arn2.add(n);
            long t3 = System.nanoTime();

            // Mesure du temps de construction
            System.out.println("AJOUT -> TAILLE = " + n + ", ABR_2 = " + (t1 - t0) / 1e9 + " s  |  ARN_2 = " + (t3 - t2) / 1e9 + " s");
        }

        System.out.println("Recherche des clefs_________________________________________________");
        //Recherche de clefs existantes et non existantes
        for (int n =0; n <2* taille; n+=10_000){
            long tempsABR = rechercherABR(abr2, n);
            long tempsARN = rechercherARN(arn2, n);
            System.out.println("RECHERCHE -> TAILLE = " + n + ", ABR_2("+abr2.contains(n)+") : " +  tempsABR/1e6 + " ms  |  ARN_2("+arn2.contains(n)+") "  + tempsARN/1e6 + " ms");
        } 
    }
    /**
     *  Fonction qui mesure le temps de recherche d'une clef dans un arbre
     * 
     */
    public static long rechercherARN(ARN<Integer> arbre, Integer cle) {
        long debut = System.nanoTime();
        arbre.contains(cle);
        long fin = System.nanoTime();
        return fin - debut;
    }
   
    public static long rechercherABR(ABR<Integer> arbre, Integer cle) {
        long debut = System.nanoTime();
        arbre.contains(cle);
        long fin = System.nanoTime();
        return fin - debut;
    }
    
}