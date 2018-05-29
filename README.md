# Spécification et implémentation d'une version 2D du jeu Dungeon Master dans le cadre du projet de l'ue CPS Master1 STL Sorbonne université.

## Manuel d'utilisation 

Afin de lancer le jeux sur vos machines, un build.xml a été intégré. Trois commande ANT sont ncéssaires pour lancer l'interface principale. (les commandes sont à exécuter à la racine du projet ou se trouve le build.xml)  
```
- ant clean

- ant build

- ant run
```

Si vous souhaitez lancer l'édition de map utiliser la commande suivant. 
```
- ant MainEditMap

```
Pour ce qui est des tests utilisez la suite de commande suivante 
```
- ant clean

- ant build

- ant RunTests (permet de lancer tous les tests.)

- ant junitreport (crée un fichier all-test.html dans le dossier junit. En ouvrant ce fichier sur un navigateur vous avez les résultats des tests.)

```

## Auteurs 
- Lachachi Charaf
- Naim Choullit
