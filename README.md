# mbds-tp3

## Etapes pour lancer le project
Lancez les commandes suivantes en vous plaçant dans le dossier contenant le pom.xml:
1) `mvn install -DskipTests` (le "s" est bien minuscule)
2) `mvn tomee:run` pour déployer le serveur
3) l'url d'accueil est : "localhost:8080/mbds-tp3"

Par défaut, vous pouvez vous connecter avec le username des personnes créées à l'initialisation:
- "admin" pour vous connecter en tant qu'admin
- "advisor" pour vous connecter en tant que conseiller
- "client" pour vous connecter en tant que client
Vous pouvez créer vos propres personnes grâce à l'admin, et les utiliser par la suite pour vous connecter.

La base de données n'est pas réinitialisée après chaque build du projet. Il convient donc de l'effacer de temps en temps afin d'éviter d'avoir trop de doublons des 3 utilisateurs cités ci-dessus.<br/>
Pour ce faire, il suffit d'exécuter la commande `mvn clean` avant le `mvn install -DskipTests`.

## Paramètres DBeaver
- Nouvelle connexion HSQLDB / Embedded
- Path = Path absolu jusqu'à target/apache-tomee/proddb
- indentifiant: sa
- laisser le champs de mot de passe vide
