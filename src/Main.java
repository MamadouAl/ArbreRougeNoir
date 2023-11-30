import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        ARN<String> arbre = new ARN<String>();
        arbre.add("g");
        arbre.add("b");
        arbre.add("a");
        arbre.add("c");
        arbre.add("d");
        arbre.add("e");
        arbre.add("f");
        System.out.println(arbre);
        System.out.println("Trouvé ?  "+arbre.contains("a"));
        System.out.println("trouvé ? "+arbre.contains("m"));

        ARN<Integer> arbre2 = new ARN<Integer>();
        arbre2.add(7);
        arbre2.add(6);
        arbre2.add(5);
        arbre2.add(4);
        arbre2.add(3);
        arbre2.add(2);
        arbre2.add(1);
        arbre2.add(8);
        arbre2.add(9);
        arbre2.add(11);
        arbre2.add(10);
        System.out.println(arbre2);




        ARN<Integer> arbre3 = new ARN<>();
        for(int i=0; i<50; i++)
            arbre3.add(i);


        System.out.println("Suppression ....");
        for(Iterator<Integer> it = arbre3.iterator(); it.hasNext(); ) {
            Integer i = it.next();
            if(i == 23)
                it.remove();
            else
                System.out.println(i);
        }
        System.out.println(arbre3);



    }
}
