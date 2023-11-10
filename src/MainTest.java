import java.util.*;

public class MainTest {
    public static void main(String[] args) {
        int[] sizes = {10_000, 50_000, 100_000, 500_000}; // Différentes tailles d'arbres à tester

        // Mesures pour les ABR
        System.out.println("Mesures pour les ABR :");
        for (int size : sizes) {
            ABR<Integer> abr = new ABR<>();
            long debut = System.nanoTime();
            Random random = new Random();

            for (int i = 0; i < size; i++) {
                abr.add(random.nextInt(size));
            }

            long fin = System.nanoTime();
            long tempsAjout = (fin - debut) / 1_000_000; // Convertir en millisecondes
            System.out.println("ARN : " + size + " - temps d'ajout : " + tempsAjout + " ms");

            debut = System.nanoTime();
            for (int i = 0; i < 2 * size; i++) {
                abr.contains(i);
            }
            fin = System.nanoTime();
            long tempRech = (fin - debut) / 1_000_000; // Convertir en millisecondes
            System.out.println("ABR : " + size + " - Recherche : " + tempRech + " ms");
        }

        // Mesures pour les ARN
        System.out.println("\nMesures pour les ARN :");
        for (int size : sizes) {
            ARN<Integer> arn = new ARN<>();
            long debut = System.nanoTime();
            Random random = new Random();

            for (int i = 0; i < size; i++) {
                arn.add(random.nextInt(size));
            }

            long fin = System.nanoTime();
            long tempsAjout = (fin - debut) / 1_000_000; // Convertir en millisecondes
            System.out.println("ARN : " + size + " - temps d'ajout : " + tempsAjout + " ms");

            debut = System.nanoTime();
            for (int i = 0; i < 2 * size; i++) {
                arn.contains(i);
            }
            fin = System.nanoTime();
            long tempRech = (fin - debut) / 1_000_000; // Convertir en millisecondes
            System.out.println("ARN : " + size + " - Recherche : " + tempRech + " ms");

            //A developper !
        }
    }
}
