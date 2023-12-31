import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p>
 *     Implementation de l'interface Collection basée sur les Arbre Rouge-noire
 *     @auteur Mamadou Aliou Diallo
 *      @version 2 -- "fonctionnel"
 *      @date 05-12-2023 derniere mise à jour
 *
 * </p>
 */

/**
 *
 * @param <E> le type des clés stockées dans l'arbre
 */
public class ARN<E> extends AbstractCollection<E> {
    //Attributs
    private Noeud racine;
    private int taille;
    private Noeud sentinelle;
    private Comparator<? super E> cmp;

    //Classe Noeud
    private class Noeud {
        private E cle;
        private Noeud gauche;
        private Noeud droit;
        private Noeud pere;
        private char couleur;

        Noeud(E cle) {
            this.cle = cle;
            this.gauche = null;
            this.droit = null;
            this.pere = null;
            couleur = 'R';

        }

        Noeud(E cle, char color){
            this.cle = cle;
            this.gauche = null;
            this.droit = null;
            this.pere = null;
            couleur = color;
        }

        /**
         * Renvoie le noeud contenant la clé minimale du sous-arbre enraciné
         * dans ce noeud
         *
         * @return le noeud contenant la clé minimale du sous-arbre enraciné
         *         dans ce noeud
         */
        public Noeud minimum() {
            Noeud x = this;
            while (x.gauche != sentinelle) {
                x = x.gauche;
            }
            return x;
        }

        /**
         * Renvoie le successeur de ce noeud
         *
         * @return le noeud contenant la clé qui suit la clé de ce noeud dans
         *         l'ordre des clés, null si c'es le noeud contenant la plus
         *         grande clé
         */
        public Noeud suivant() {
            if (this.droit != sentinelle) {
                return this.droit.minimum();
            }
            Noeud y = pere;
            Noeud x = this;
            while (y != sentinelle && x == y.droit) {
                x = y;
                y = y.pere;
            }
            return y;
        }
    }



    //Constructeurs
    /**
     * Crée un arbre vide. Les éléments sont ordonnés selon l'ordre naturel
     */
    public ARN() {
        sentinelle = new Noeud(null, 'N');
        racine = this.sentinelle;
        taille = 0;
        cmp = (a, b) -> ((Comparable) a).compareTo(b);
    }

    /**
     * Crée un arbre vide. Les éléments sont comparés selon l'ordre imposé par
     * le comparateur
     *
     * @param cmp le comparateur utilisé pour définir l'ordre des éléments
     */
    public ARN(Comparator<? super E> cmp) {
        taille = 0;
        sentinelle = new Noeud(null, 'N');
        racine = this.sentinelle;
        this.cmp = cmp;
    }

    /**
     * Constructeur par recopie. Crée un arbre qui contient les mêmes éléments
     * que c. L'ordre des éléments est l'ordre naturel.
     *
     * @param c la collection à copier
     */
    public ARN(Collection<? extends E> c) {
        this();
        addAll(c);
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modif = false;
        for (E e : c) {
            if (add(e)) {
                modif = true;
            }
        }
        return modif;
    }


    /**
     * Fonction qui permet de nettoyer l'arbre, supprimer tous les éléments
     */
    @Override
    public void clear() {
        racine = sentinelle;
        taille = 0;
    }

    @Override
    public boolean isEmpty() {
        return taille == 0;
    }
    @Override
    public boolean add(E e) {
        Noeud z = new Noeud(e);
        inserer(z);
        return true;
    }

    /**
     * Insère un nouveau noeud dans l'arbre.
     *
     * @param z Le nouveau noeud à insérer.
     */
    private void inserer(Noeud z) {
        Noeud y = sentinelle;
        Noeud x = racine;
        while (x != sentinelle) {
            y = x;
            x = this.cmp.compare(z.cle, x.cle) < 0 ? x.gauche : x.droit;
        }
        z.pere = y;
        if (y == sentinelle) {
            racine = z;
        } else if (this.cmp.compare(z.cle, y.cle) < 0) {
            y.gauche = z;
        } else {
            y.droit = z;
        }
        z.gauche = z.droit = sentinelle;
        z.couleur = 'R';
        taille++;
        ajouterCorrection(z);
    }

    /**
     * Effectue la correction de l'arbre après une insertion.
     *
     * @param z Le noeud à partir duquel commencer la correction.
     */
    private void ajouterCorrection(Noeud z) {
        while (z.pere.couleur == 'R') {
            if (z.pere == z.pere.pere.gauche) {
                Noeud y = z.pere.pere.droit; // Oncle de z
                if (y.couleur == 'R') {
                    // Cas 1
                    z.pere.couleur = 'N';
                    y.couleur = 'N';
                    z.pere.pere.couleur = 'R';
                    z = z.pere.pere;
                } else {
                    if (z == z.pere.droit) {
                        // Cas 2
                        z = z.pere;
                        rotationGauche(z);
                    }
                    // Cas 3
                    z.pere.couleur = 'N';
                    z.pere.pere.couleur = 'R';
                    rotationDroite(z.pere.pere);
                }
            } else {
                Noeud y = z.pere.pere.gauche; // Oncle de z
                if (y.couleur == 'R') {
                    // Cas 1 (miroir)
                    z.pere.couleur = 'N';
                    y.couleur = 'N';
                    z.pere.pere.couleur = 'R';
                    z = z.pere.pere;
                } else {
                    if (z == z.pere.gauche) {
                        // Cas 2 (miroir)
                        z = z.pere;
                        rotationDroite(z);
                    }
                    // Cas 3 (miroir)
                    z.pere.couleur = 'N';
                    z.pere.pere.couleur = 'R';
                    rotationGauche(z.pere.pere);
                }
            }
        }
        racine.couleur = 'N';
    }

    /**
     * Effectue une rotation gauche sur le noeud donné.
     *
     * @param n Le noeud sur lequel effectuer la rotation gauche.
     */
    private void rotationGauche(Noeud n) {
        Noeud y = n.droit;
        n.droit = y.gauche;
        if (y.gauche != sentinelle) y.gauche.pere = n;
        y.pere = n.pere;
        if (n.pere == sentinelle) racine = y;
        else if (n == n.pere.gauche) n.pere.gauche = y;
        else n.pere.droit = y;
        y.gauche = n;
        n.pere = y;
    }

    /**
     * Effectue une rotation droite sur le noeud donné.
     *
     * @param n Le noeud sur lequel effectuer la rotation droite.
     */
    private void rotationDroite(Noeud n) {
        Noeud y = n.gauche;
        n.gauche = y.droit;
        if (y.droit != sentinelle) y.droit.pere = n;
        y.pere = n.pere;
        if (n.pere == sentinelle) racine = y;
        else if (n == n.pere.droit) n.pere.droit = y;
        else n.pere.gauche = y;
        y.droit = n;
        n.pere = y;
    }



    /**
     * Renvoie un itérateur sur les éléments de l'arbre rouge-noir, permettant de
     * parcourir les éléments dans l'ordre croissant.
     *
     * @return Un itérateur sur les éléments de l'arbre rouge-noir.
     */
    @Override
    public Iterator<E> iterator() {
        return new ARNIterator();
    }

    /**
     * Renvoie le nombre d'éléments contenus dans l'arbre rouge-noir.
     *
     * @return Le nombre d'éléments dans l'arbre rouge-noir.
     */
    @Override
    public int size() {
        return taille;
    }

    // methode de recherche
    /**
     * Recherche une clé. Cette méthode peut être utilisée par
     * {@link #contains(Object)} et {@link #remove(Object)}
     *
     * @param o la clé à chercher
     * @return le noeud qui contient la clé ou null si la clé n'est pas trouvée.
     */
    private Noeud rechercher(Object o) {
        Noeud x = racine;
        while (x != sentinelle && cmp.compare((E) o, x.cle) != 0) {
            x = cmp.compare((E) o, x.cle) < 0 ? x.gauche : x.droit;
        }
        return x;
    }

    /**
     * Vérifie si l'arbre rouge-noir contient l'objet spécifié.
     *
     * @param o L'objet à rechercher dans l'arbre rouge-noir.
     * @return  true si l'objet est présent, false sinon.
     */
    @Override
    public boolean contains(Object o) {
        return rechercher(o) != sentinelle;
    }

    /**
     * Supprime une collection d'éléments de l'arbre rouge-noir.
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c){
        for (Object o : c){
            if (!contains(o)) return false;
        }
        return true;
    }

    /**
     * Supprime le noeud z. Cette méthode peut être utilisée dans
     * {@link #remove(Object)} et {@link Iterator#remove()}
     *
     * @param z le noeud à supprimer
     * @return le noeud contenant la clé qui suit celle de z dans l'ordre des
     *         clés. Cette valeur de retour peut être utile dans
     *         {@link Iterator#remove()}
     */
    private Noeud supprimer(Noeud z) {
        // TODO
        Noeud y;
        Noeud tmp;
        if (z.gauche == sentinelle || z.droit == sentinelle)
            y = z;
        else
            y = z.suivant();
        tmp = z.suivant();
        Noeud x;
        if (y.gauche != sentinelle)
            x = y.gauche;
        else
            x = y.droit;

        x.pere = y.pere;
        if (y.pere == sentinelle) { // cas de la racine
            racine = x;
        } else {
            if (y == y.pere.gauche)
                y.pere.gauche = x;
            else
                y.pere.droit = x;
        }

        if (y != z) {
            z.cle = y.cle;
        }
        if (y.couleur == 'N')
            supprimerCorrection(x);
		//Recyclage :)
        if (y != z){
            z.cle = y.cle;
            tmp = z;
        }
        y.pere = null;
        y.droit = null;
        y.gauche = null;
        return tmp;
    }

    /**
     * Effectue la correction de l'arbre après une suppression.
     *
     * @param x Le noeud à partir duquel commencer la correction.
     */
    private void supprimerCorrection(Noeud x) {
        Noeud w;
        while (x != racine && x.couleur == 'N') {
            // (*) est vérifié ici
            if (x == x.pere.gauche) {
                w = x.pere.droit; // le frère de x
                if (w.couleur == 'R') {
                    // cas 1
                    w.couleur = 'N';
                    x.pere.couleur = 'R';
                    rotationGauche(x.pere);
                    w = x.pere.droit;
                }
                if (w.gauche.couleur == 'N' && w.droit.couleur == 'N') {
                    // cas 2
                    w.couleur = 'R';
                    x = x.pere;
                } else {
                    if (w.droit.couleur == 'N') {
                        // cas 3
                        w.gauche.couleur = 'N';
                        w.couleur = 'R';
                        rotationDroite(w);
                        w = x.pere.droit;
                    }
                    // cas 4
                    w.couleur = x.pere.couleur;
                    x.pere.couleur = 'N';
                    w.droit.couleur = 'N';
                    rotationGauche(x.pere);
                    x = racine;
                }
            } else {
                // idem en miroir, gauche <-> droite
                // cas 1', 2', 3', 4'
                w = x.pere.gauche; // le frère de x
                if (w.couleur == 'R') {
                    // cas 1'
                    w.couleur = 'N';
                    x.pere.couleur = 'R';
                    rotationDroite(x.pere);
                    w = x.pere.gauche;
                }
                if (w.droit.couleur == 'N' && w.gauche.couleur == 'N') {
                    // cas 2'
                    w.couleur = 'R';
                    x = x.pere;
                } else {
                    if (w.gauche.couleur == 'N') {
                        // cas 3'
                        w.droit.couleur = 'N';
                        w.couleur = 'R';
                        rotationGauche(w);
                        w = x.pere.gauche;
                    }
                    // cas 4'
                    w.couleur = x.pere.couleur;
                    x.pere.couleur = 'N';
                    w.gauche.couleur = 'N';
                    rotationDroite(x.pere);
                    x = racine;
                }
            }
        }
        x.couleur = 'N';
    }

    /**
     * Classe interne implémentant l'interface Iterator pour parcourir les éléments
     * de l'arbre rouge-noir dans l'ordre croissant.
     */
    private class ARNIterator implements Iterator<E> {
        // TODO
        private Noeud precedent;
        private Noeud suivant;

        public ARNIterator() {
            precedent = sentinelle;
            if(racine == sentinelle) suivant=sentinelle;
            else suivant = racine.minimum();
        }


        public boolean hasNext() {
            // TODO
            return suivant != sentinelle;
        }

        public E next() throws IllegalStateException {
            // TODO
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            precedent = suivant;
            suivant = precedent.suivant();
            return precedent.cle;

        }

        public void remove() throws IllegalStateException {
            // TODO
            if (precedent == sentinelle) throw new IllegalStateException();
            suivant = supprimer(precedent);
            precedent = sentinelle;

        }
    }

    //Méthodes de suppression
    @Override
    public boolean remove(Object o) {
        Noeud z = rechercher(o);
        if (z == sentinelle) return false;
        supprimer(z);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean sup = false;
        for (Object o : c) {
            if (remove(o)) {
                sup = true;
            }
        }
        return sup;
    }


    // Pour un "joli" affichage

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        toString(racine, buf, "", maxStrLen(racine));
        return buf.toString();
    }

    private void toString(Noeud x, StringBuffer buf, String path, int len) {
        if (x == sentinelle)
            return;
        toString(x.droit, buf, path + "D", len);
        for (int i = 0; i < path.length(); i++) {
            for (int j = 0; j < len + 6; j++)
                buf.append(' ');
            char c = ' ';
            if (i == path.length() - 1)
                c = '+';
            else if (path.charAt(i) != path.charAt(i + 1))
                c = '|';
            buf.append(c);
        }
        buf.append("-- " + x.cle.toString() +"."+x.couleur);
        if (x.gauche != sentinelle || x.droit != sentinelle) {
            buf.append(" --");
            for (int j = x.cle.toString().length(); j < len; j++)
                buf.append('-');
            buf.append('|');
        }
        buf.append("\n");
        toString(x.gauche, buf, path + "G", len);
    }

    private int maxStrLen(Noeud x) {
        return x == sentinelle ? 0 : Math.max(x.cle.toString().length(),
                Math.max(maxStrLen(x.gauche), maxStrLen(x.droit)));
    }


}
