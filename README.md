# mbds-tp3

## Etapes pour lancer le project
Lancez les commandes suivantes en vous placant dans le dossier contenant le pom.xml:
1) "mvn install -DskipTests" (le "s" est bien minuscule)
2) "mvn tomee:run" pour déployer le serveur
3) l'url d'acueil est : "localhost:8080/mbds-tp3"

Par défaut vous pourriez vous connecter avec le username de personnes créé à l'initialisation:
- "admin" pour vous connecter en tant qu'admin
- "client" pour vous connecter en tant que client
Vous pourriez aussi créer vos propres personnes grace à l'admin déja créé

Finalement, l'instance de la base de donnée est créée avec le build et reste
disponible même si on refait un autre build.
Pour effecer la base de donnée, veillez lancer la commande "mvn clean"
avant le "mvn install -DskipTests"

## Paramètres DBeaver
- Nouvelle connexion HSQLDB / Embedded
- Path = Path absolu jusqu'à target/apache-tomee/proddb
- indentifiant: sa
- laisser le champs de mot de passe vide
