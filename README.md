Projet d'Intégration de Développement, IEPSCF Namur 2019-2020.

Projet "refuge" : gestion d'un refuge d'animaux à adopter.



Configuration commune sur les postes de développement :
=======================================================

- OpenJDK 11
- Tomcat 9
- IntelliJ Ultimate
- MariaDB (port 3306 !)
- Maven 3+



Organisation : 
==============

Répartition :

- be.iepscf.refuge.business : couche business/métier/application
- be.iepscf.refuge.dba : outils admin base de donnée
- be.iepscf.refuge.persistence : couche persistence
- webapp + WEB-INF/jsp : designers

Recommandations : 

- éviter de s'aventurer hors de sa couche pour minimiser les risques de conflits 
- essayer de travailler sur des branches de dev plutôt que directement dans le master 
- méditer et communiquer avant de modifier (surtout dans le master) les parties communes (pom.xml, web.xml, applicationContext.xml...) 



Historique :
============  

## Création application :

```bash
u@felix:~/refugexyz$ mvn --version
Apache Maven 3.6.0
Maven home: /usr/share/maven
Java version: 11.0.4, vendor: Debian, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: fr_BE, platform encoding: UTF-8
OS name: "linux", version: "4.19.0-6-amd64", arch: "amd64", family: "unix"
```

```bash
$ mvn archetype:generate -DgroupId=be.iepscf.refuge -DartifactId=refuge -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

- ajout fichiers .gitignore, README.md 
- remplacement du web.xml généré automatiquement, car version Servlet API 2.3 obsolète et problématique, par une version 3.0 
- ajout de dépendances dans le pom.xml (à "(ré)importer" dans IntelliJ : click droit sur projet > Maven > Reimport) 

## Base de données :

Avec droits d'admin sur le serveur :

```sql
CREATE DATABASE IF NOT EXISTS refuge;
GRANT ALL PRIVILEGES ON refuge.* TO refuge@localhost IDENTIFIED BY 'refuge';
```

Pour remplir avec le dump SQL, en ligne de commande :

```bash
$ mysql -u refuge -prefuge refuge < mysql-dump.sql
```

[mysql-dump.sql dans dépôt gestion projet](https://github.com/PID-2019-Refuge/refuge-gestion-projet/blob/master/mysql-dump.sql)

Mots de passe : prénom des utilisateurs (sauf Léon : wtf)