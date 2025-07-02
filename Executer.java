package Jalon;

import java.util.Scanner;

public class Executer {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        afficherMenu();
    }
    // Afficher menu
    public static void afficherMenu() {
        System.out.println("Bienvenue dans l'inventaire de votre concession:");
        if (Methodes.voitures.isEmpty()) {
            System.out.println("Vous n'avez aucune voiture à vendre dans votre concession : ");
        } else {
            afficherToutesVoitures();
        }
        
        System.out.println("(1) Ajouter une voiture");
        System.out.println("(2) Supprimer une voiture");
        System.out.println("(3) Rechercher une voiture");
        System.out.println("(0) Quitter");
        
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        switch (choix) {
            case 1: ajouterVoiture(); break;
            case 2: supprimerVoiture(); break;
            case 3: rechercherVoiture(); break;
            case 0: System.exit(0); break;
            default: 
                System.out.println("Choix invalide, veuillez réessayer.");
                afficherMenu();
        }
    }
    // Ajouter une voiture
    private static void ajouterVoiture() {
        System.out.println("Voulez-vous ajouter une voiture, très bien");
        
        // choix de la marque
        int choixMarque = choisirMarque();
        String marque = Methodes.MARQUES[choixMarque];
        
        // choix du modèle
        int choixModele = choisirModele(choixMarque);
        String modele = Methodes.MODELES[choixMarque][choixModele];
        
        // Neuf ou occasion
        boolean estNeuf = choisirEtat();
        int kilometrage = 0;
        
        if (!estNeuf) {
            kilometrage = choisirKilometrage();
        }
        
        // choix de la couleur
        int choixCouleur = choisirCouleur();
        String couleur = Methodes.COULEURS[choixCouleur];
        
        // Calcul du prix et génération du code
        int prix = Methodes.calculerPrix(choixMarque, choixModele, choixCouleur, estNeuf, kilometrage);
        String code = Methodes.genererCode(marque, modele);
        
        // Ajout à la liste
        String[] voiture = {
            code, marque, modele, 
            String.valueOf(estNeuf), 
            String.valueOf(kilometrage), 
            couleur, 
            String.valueOf(prix)
        };
        Methodes.voitures.add(voiture);
        
        // Affichage formaté
        System.out.println(Methodes.formaterInfoVoiture(voiture));
        
        // Ajouter une autre voiture
        System.out.println("Voulez-vous ajouter une autre voiture (true/false)?");
        if (scanner.nextBoolean()) {
            scanner.nextLine();
            ajouterVoiture();
        } else {
            scanner.nextLine();
            afficherMenu();
        }
    }
    // Choix de la marque
    private static int choisirMarque() {
        System.out.println("Quelle est sa marque, choisissez son numéro [1-Wolskwagen, 2-Audi, 3-Porsche, 4-Lamborghini]");
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        if (!Methodes.choixValide(choix, Methodes.MARQUES.length)) {
            System.out.println("Choix invalide");
            return choisirMarque();
        }
        
        System.out.println("Vous avez choisi la marque: " + Methodes.MARQUES[choix-1]);
        return choix-1;
    }
    // Choix du modèle
    private static int choisirModele(int indexMarque) {
        System.out.print("Quel est son modèle, choisissez son numéro [");
        for (int i = 0; i < Methodes.MODELES[indexMarque].length; i++) {
            System.out.print((i+1) + "-" + Methodes.MODELES[indexMarque][i]);
            if (i < Methodes.MODELES[indexMarque].length-1) System.out.print(", ");
        }
        System.out.println("]");
        
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        if (!Methodes.choixValide(choix, Methodes.MODELES[indexMarque].length)) {
            System.out.println("Choix invalide");
            return choisirModele(indexMarque);
        }
        
        System.out.println("Vous avez choisi le modèle: " + Methodes.MODELES[indexMarque][choix-1]);
        return choix-1;
    }
    // Choix neuve ou d'occasion
    private static boolean choisirEtat() {
        System.out.println("Est-elle neuve ?(true/false)");
        boolean estNeuf = scanner.nextBoolean();
        scanner.nextLine();
        
        if (!estNeuf) {
            System.out.println("Cette voiture est en occasion (soit -10%)");
        }
        return estNeuf;
    }
    // Choix du kilométrage et calcul du prix
    private static int choisirKilometrage() {
        System.out.println("Quel est son kilométrage (en km) ?");
        int kilometrage = scanner.nextInt();
        scanner.nextLine();
        
        double remise = Methodes.calculerRemise(kilometrage);
        System.out.printf("La voiture a %dkm au compteur: (réduction de -%.0f%%)%n", 
                         kilometrage, remise * 100);
        return kilometrage;
    }
    // choix de la couleur et calcul du prix
    private static int choisirCouleur() {
        System.out.println("Quelle est sa couleur: ");
        for (int i = 0; i < Methodes.COULEURS.length; i++) {
            System.out.printf("(%d)-%s (%s)%n", 
                i+1, 
                Methodes.COULEURS[i], 
                Methodes.SUPPLEMENTS_COULEURS[i] > 0 ? 
                    "+" + Methodes.SUPPLEMENTS_COULEURS[i] + " euros" : "gratuit");
        }
        
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        if (!Methodes.choixValide(choix, Methodes.COULEURS.length)) {
            System.out.println("Choix invalide");
            return choisirCouleur();
        }
    
        System.out.printf("Vous avez choisi la couleur %s, %s%n",
            Methodes.COULEURS[choix-1].toLowerCase(),
            Methodes.SUPPLEMENTS_COULEURS[choix-1] > 0 ? 
                "+" + Methodes.SUPPLEMENTS_COULEURS[choix-1] + " euros sur le prix" : "pas de supplément");
        return choix-1;
    }
    // affichage formaté des voitures
    private static void afficherToutesVoitures() {
        for (String[] voiture : Methodes.voitures) {
            System.out.println(Methodes.formaterInfoVoiture(voiture));
        }
        System.out.println();
    }
    // Supprimer voiture
    private static void supprimerVoiture() {
        System.out.println("Entrez le code de la voiture à supprimer:");
        String code = scanner.nextLine();
        
        boolean supprime = Methodes.voitures.removeIf(v -> v[0].equals(code));
        
        if (supprime) {
            System.out.println("Voiture supprimée avec succès.");
        } else {
            System.out.println("Aucune voiture trouvée avec ce code.");
        }
        
        afficherMenu();
    }
    // Recherche de voiture avec code, marque ou modèle
    private static void rechercherVoiture() {
        System.out.println("Entrez le code, la marque ou le modèle de la voiture à rechercher:");
        String termeRecherche = scanner.nextLine().toLowerCase();
        
        boolean trouve = false;
        for (String[] voiture : Methodes.voitures) {
            if (voiture[0].toLowerCase().contains(termeRecherche) ||
                voiture[1].toLowerCase().contains(termeRecherche) ||
                voiture[2].toLowerCase().contains(termeRecherche)) {
                System.out.println(Methodes.formaterInfoVoiture(voiture));
                trouve = true;
            }
        }
        
        if (!trouve) {
            System.out.println("Aucune voiture trouvée.");
        }
        
        afficherMenu();
    }
}