📚 SGE - Système de Gestion des Étudiants
📌 Présentation

SGE (Système de Gestion des Étudiants) est une application web développée avec Spring Boot + Thymeleaf permettant la gestion des étudiants, des filières, des modules, des notes, ainsi que la génération automatique des bulletins scolaires.

Le système inclut un module d’authentification (ADMIN / USER) basé sur JWT.

🔐 Système d’authentification

L’application utilise Spring Security + JWT pour sécuriser l’accès.

👮 Rôles
🟢 ADMIN
Gestion complète du système
Gestion des étudiants
Gestion des filières et modules
Gestion des notes
Accès à tous les bulletins
🔵 USER
Consultation des notes
Consultation des bulletins
Accès limité (lecture seule)
🚀 Fonctionnalités
👨‍🎓 Gestion des étudiants
Ajouter / modifier / supprimer un étudiant
Affecter une filière
📊 Gestion des notes
Ajout des notes par module
Gestion des coefficients
Calcul automatique des moyennes
📄 Système de bulletins
Génération automatique des bulletins
Moyenne générale
Mention automatique
Résultat final (ADMIS / AJOURNÉ)
🔐 Authentification
Login sécurisé avec JWT
Séparation des rôles ADMIN / USER
Accès protégé aux endpoints
🎨 Interface Web
Thymeleaf pour le rendu des pages
Bootstrap pour le design
Interface simple et académique
Système de fragments pour réutilisation UI
🛠️ Technologies utilisées
Java 17+
Spring Boot
Spring MVC
Spring Data JPA
Spring Security (JWT)
Thymeleaf
MySQL
Hibernate
Maven
🧱 Architecture du projet
com.example.sge

├── model /
│   ├── Filiere.java
│   ├── Module.java
│   ├── Utilisateur.java
│   ├── Etudiant.java
│   ├── Note.java

├── repository /
│   ├── FiliereRepository.java
│   ├── ModuleRepository.java
│   ├── EtudiantRepository.java
│   ├── UtilisateurRepository.java
│   ├── NoteRepository.java

├── service /
│   ├── FiliereService.java
│   ├── ModuleService.java
│   ├── EtudiantService.java
│   ├── NoteService.java

├── controller /
│   ├── FiliereController.java
│   ├── ModuleController.java
│   ├── EtudiantController.java
│   ├── AuthController.java
│   ├── NoteController.java
│   ├── BulletinController.java

├── dto /
│   ├── LoginRequest.java
│   ├── AuthResponse.java
│   ├── BulletinDTO.java

├── security /
│   ├── JwtService.java
│   ├── JwtAuthFilter.java
│   ├── UserDetailsServiceImpl.java

├── config /
│   ├── SecurityConfig.java

├── templates /
│   ├── login.html
│
│   ├── bulletins /
│   │   ├── liste.html
│   │   ├── detail.html
│
│   ├── fragments /
│   │   ├── header.html
│   │   ├── footer.html
🎨 Fragments Thymeleaf
📌 header.html

Contient :

Navbar
Menu principal
Navigation dynamique
📌 footer.html

Contient :

Copyright
Infos système
Liens secondaires
📄 Pages principales
Page	Description
login.html	Page de connexion
/bulletins/liste.html	Liste des bulletins
/bulletins/detail.html	Détail d’un bulletin
⚙️ Installation & configuration
1️⃣ Cloner le projet
git clone https://github.com/ghofrane-2005/sge-system.git
cd sge-system
2️⃣ Configurer la base de données
spring.datasource.url=jdbc:mysql://localhost:3306/sge_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
3️⃣ Lancer le projet
mvn spring-boot:run
🔐 Accès par défaut
Rôle	Accès
ADMIN	accès complet
USER	accès lecture
📊 Endpoints principaux
URL	Description
/auth	Authentification
/filieres Gestion filieres
/modules Gestion Modules
/etudiants	Gestion étudiants
/notes	Gestion notes
/dashboard Dashboard
/bulletins	Liste bulletins
🧠 Notes importantes
Une filière doit exister avant un étudiant
Un module doit exister avant une note
Respect des contraintes MySQL
Sécurité basée sur JWT
🧑‍💻 Auteur

Ghofrane hbila
Projet académique - Système de gestion des étudiants (DSI)
