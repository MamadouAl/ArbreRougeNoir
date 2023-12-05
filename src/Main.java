import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
       //Test d'echauffement :)

       //ABR
        ABR<String> arbre = new ABR<>();
        arbre.add("d");
        arbre.add("g");
        arbre.add("b");
        arbre.add("c");
        arbre.add("a");
        arbre.add("e");
        arbre.add("f");
        System.out.println("ABR" +arbre);
        System.out.println("Trouvé ?  "+arbre.contains("a"));
        System.out.println("trouvé ? "+arbre.contains("m"));

        //ARN
        ARN<String> arbre1 = new ARN<>(arbre);
        System.out.println("ARN" + arbre1);

        ArrayList<Integer> liste = new ArrayList<>();
        liste.add(6);
        liste.add(5);
        liste.add(4);
        liste.add(3);
        liste.add(2);
        liste.add(1);
        liste.add(8);
        liste.add(9);
        liste.add(11);
        liste.add(10);

        ARN<Integer> arbre2 = new ARN<>(liste);
        System.out.println("ARN" + arbre2);

        ARN<Integer> arbre3 = new ARN<>();
        for(int i=0; i<20; i++)
            arbre3.add(i);
        

        System.out.println("Suppression ....");
        for(Iterator<Integer> it = arbre3.iterator(); it.hasNext(); ) {
            Integer i = it.next();
            if(i == 13)
                it.remove();
            else
                System.out.println(i);
        }
        System.out.println(arbre3);



    }
}
