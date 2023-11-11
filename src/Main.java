public class Main {
    public static void main(String[] args) {
        ARN<String> arbre = new ARN<String>();
        arbre.add("a");
        arbre.add("b");
        arbre.add("c");
        arbre.add("d");
        arbre.add("e");
        arbre.add("f");
        System.out.println(arbre);

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
        

    }
}

