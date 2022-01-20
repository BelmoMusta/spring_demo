# Spring

Pour répondre à ce devoir suivre les instructions suivantes : 
Forker le repository suivant https://github.com/BelmoMusta/spring_demo (un guide de fork est en PJ)
Merger la branche 'annotations_and_jdbc' sur la branche 'develop' [résoudre les conflits éventuels].
L'application est dotée d'une pseudo-interface utilisateur, réduite à des interactions sur le terminal, le travail demandé consistera à compléter ce menu : 
L'existant : 
L'application demande au démarrage un code pays, elle cherche le pays dont le code est entré par l'utilisateur, ensuite elle en affiche certaines informations. 


A compléter : 
- Aspects fonctionnels: 10 pts
Le menu devra être interactif : 
1. Pour l'ajout d'un nouveau pays tapper 1, L'utilisateur sera amené à saisir les information du pays une par une
    Bonus : si vous le souhaitez, vous pouvez faire l'ajout par une seule ligne avec un séparateur, exemple : FR,france,EURO,Bonjour!
2. Pour lister les informations d'un pays, tapper 2, (le code pays sera saisi par l'utilisateur).
3. Pour supprimer un pays, tapper 3, (le code pays sera saisi par l'utilisateur).
4. Pour modifier des informations d'un pays, tapper 4, (le code pays sera saisi par l'utilisateur).
5. Pour lister tous les pays d'un continent, tapper 5, (le code du continent sera saisi par l'utilisateur).
6. Pour sortir de l'application tapper 0;


- Aspects techniques : 10 pts
1. Le travail sera transmis en tant que Pull Request sur github, la branche cible est develop, la démarche est décrite durant les séances du cours, toutefois vous pouvez revenir sur le document en PJ pour toute illustration. 
Tout commit doit être fonctionnel, i.e un commit contenant des erreurs de compilation/démarrage de l'application n'est pas accepté.

2. Concevoir une table Continent qui regroupera les pays d'un même continent, elle aura un code, et un nom, ainsi, une relation devra être faite avec la table Country, adapter les scripts SQL nécessaires.
3. Intégrer Hibernate, injecter les jar nécessaires ( passage par maven est interdit).
4. Prévoir des tests unitaires utilisant Junit. ( à tester les aspects fonctionnels uniquement)
5. Convertir toute la configuration XML en configuration en annotations.
6. Respecter la décomposition des couches.


Bonus : Pour une organisation de travail, vous pouvez créer des branches intermédiaires au choix, par exemple, pour tout aspect fonctionnel, créer la branche qui s'appelle aspect-fonctionnel-01 ...  Ces branches devront être pushées sur votre repo github.
Si le travail sur l'aspect fonctionnel aspect-fonctionnel-01 est achevé il faut la merger sur votre branche develop,  et ainsi de suite jusqu'à terminaison de tous les aspects.


Tout commit après le délai convenu sera rejeté.
NB : Ceci est un travail individuel, toute soupçon de copier/coller risquera d'annuler tous travaux ayant des similarités.
