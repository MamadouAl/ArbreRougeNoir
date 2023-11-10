# Arbre Rouge Noire

Le but de ce TP est de faire une implémentation de l'interface Collection basée sur les arbres rouge-noir. Testez largement votre implantation afin de vous assurer que toutes les méthodes fonctionnent comme prévu dans tous les cas possibles et imaginables.

Faites également une étude expérimentale comparant les performances des arbres binaires de recherche classiques (ABR) et des arbres rouge-noir (ARN). Pour différentes valeurs de n, construisez un ABR et un ARN contenant n clefs (les nombres entre 0 et n-1 par exemple). Testez le cas moyen où les clefs sont ajoutées en ordre aléatoire et le cas le plus défavorable pour les ABR où les clefs sont ajoutées de la plus petite à la plus grande. Mesurez le temps de construction des arbres. Mesurez ensuite le temps de recherche des clefs 0,...,2n-1 (n clefs qui sont dans l'arbre et n clefs qui ne le sont pas). Tracez les courbes du temps d'exécution en fonction de la taille de l'arbre n.

Votre travail doit être accompagné d'un rapport. Dans un premier temps, celui-ci doit faire une présentation générale des ARN et de leur fonctionnement tout en les comparant aux structures linéaires et aux ABR classiques. Dans un deuxième temps, présentez et justifiez les choix spécifiques de votre implantation. Enfin, présentez et commentez les résultats de l'étude expérimentale.

Le code peut être réalisé en binôme (dans ce cas précisez-le clairement) mais le rapport doit être individuel.

Déposez une archive unique contenant

    - vos fichiers source (implantation des ABR et ANR, tests, programmes ayant servi pour l'étude expérimentale) ;
    - le rapport en format pdf.

