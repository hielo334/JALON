
package JALON;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Programme de gestion des vols pour AIRMESS
 * 
 * Ce programme permet :
 * - L'authentification sécurisée
 * - La création de vols avec calcul automatique des prix
 * - L'affichage de tous les vols
 */
public class Vol {
    // Constantes pour l'authentification
    private static final String LOGIN = "hutt";
    private static final String PASSWORD = "david";
    
    // Liste pour stocker tous les vols
    private static List<Vol> listeVols = new ArrayList<>();
    
    // Attributs d'un vol
    private String departVille;
    private String departPays;
    private String arriveeVille;
    private String arriveePays;
    private LocalDateTime dateHeureDepart;
    private int dureeHeures;
    private int dureeMinutes;
    private int nombrePlaces;
    private double prixInitial;

    // Constructeur
    public Vol(String departVille, String departPays, String arriveeVille, String arriveePays,
              LocalDateTime dateHeureDepart, int dureeHeures, int dureeMinutes, 
              int nombrePlaces, double prixInitial) {
        this.departVille = departVille;
        this.departPays = departPays;
        this.arriveeVille = arriveeVille;
        this.arriveePays = arriveePays;
        this.dateHeureDepart = dateHeureDepart;
        this.dureeHeures = dureeHeures;
        this.dureeMinutes = dureeMinutes;
        this.nombrePlaces = nombrePlaces;
        this.prixInitial = prixInitial;
    }

    /**
     * Calcule l'heure d'arrivée en ajoutant la durée du vol
     */
    public LocalDateTime getDateHeureArrivee() {
        return dateHeureDepart.plusHours(dureeHeures).plusMinutes(dureeMinutes);
    }

    /**
     * Prix ajusté selon les règles métier :
     * - +40% si départ dans moins d'1 semaine
     * - -40% si départ dans plus de 6 mois
     * - -10% si ≥150 places
     * - +10% si <100 places
     */
    public double getPrixAjuste() {
        double prix = prixInitial;
        LocalDateTime aujourdHui = LocalDateTime.now();

        // Ajustement basé sur la date
        long joursAvantDepart = ChronoUnit.DAYS.between(aujourdHui, dateHeureDepart);
        if (joursAvantDepart < 7) {
            prix *= 1.40;
        } else if (joursAvantDepart > 180) {
            prix *= 0.60;
        }

        // Ajustement basé sur les places
        if (nombrePlaces >= 150) {
            prix *= 0.90;
        } else if (nombrePlaces < 100) {
            prix *= 1.10;
        }

        return prix;
    }

    /**
     * Affichage formaté d'un vol
     */
    @Override
    public String toString() {
        return String.format(
            "Vol de %s (%s) à %s (%s)\n" +
            "Départ: %s\nArrivée: %s\n" +
            "Durée: %dh %dmin\nPlaces: %d\n" +
            "Prix initial: %.2f€\nPrix ajusté: %.2f€\n",
            departVille, departPays, arriveeVille, arriveePays,
            dateHeureDepart, getDateHeureArrivee(),
            dureeHeures, dureeMinutes, nombrePlaces,
            prixInitial, getPrixAjuste()
        );
    }

    /**
     * Point d'entrée du programme
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Authentification
        System.out.println("=== CONNEXION AIRMESS ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String mdp = scanner.nextLine();
        
        if (!login.equals(LOGIN) || !mdp.equals(PASSWORD)) {
            System.out.println("Accès refusé !");
            scanner.close();
            return;
        }
        
        System.out.println("\nBienvenue dans le système de gestion des vols !");
        
        // Menu principal
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Ajouter un vol");
            System.out.println("2. Lister tous les vols");
            System.out.println("3. Quitter");
            System.out.print("Votre choix: ");
            
            int choix = scanner.nextInt();
            scanner.nextLine();  // Nettoie le buffer
            
            switch (choix) {
                case 1:
                    ajouterVol(scanner);
                    break;
                case 2:
                    listerVols();
                    break;
                case 3:
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide !");
            }
        }
        
        System.out.println("Merci d'avoir utilisé notre système !");
        scanner.close();
    }
    
    /**
     * Ajoute un nouveau vol après saisie des informations
     */
    private static void ajouterVol(Scanner scanner) {
        System.out.println("\n--- NOUVEAU VOL ---");
        
        System.out.print("Ville de départ: ");
        String villeDepart = scanner.nextLine();
        System.out.print("Pays de départ: ");
        String paysDepart = scanner.nextLine();
        
        System.out.print("Ville d'arrivée: ");
        String villeArrivee = scanner.nextLine();
        System.out.print("Pays d'arrivée: ");
        String paysArrivee = scanner.nextLine();
        
        System.out.println("\nDate et heure de départ:");
        System.out.print("Année (ex: 2023): ");
        int annee = scanner.nextInt();
        System.out.print("Mois (1-12): ");
        int mois = scanner.nextInt();
        System.out.print("Jour (1-31): ");
        int jour = scanner.nextInt();
        System.out.print("Heure (0-23): ");
        int heure = scanner.nextInt();
        System.out.print("Minutes (0-59): ");
        int minutes = scanner.nextInt();
        
        LocalDateTime dateDepart = LocalDateTime.of(annee, mois, jour, heure, minutes);
        
        System.out.println("\nDurée du vol:");
        System.out.print("Heures: ");
        int dureeH = scanner.nextInt();
        System.out.print("Minutes: ");
        int dureeM = scanner.nextInt();
        
        System.out.print("\nNombre de places (80-200): ");
        int places = scanner.nextInt();
        if (places < 80 || places > 200) {
            System.out.println("Erreur: le nombre de places doit être entre 80 et 200 !");
            return;
        }
        
        System.out.print("Prix initial: ");
        double prix = scanner.nextDouble();
        
        // Création et ajout du vol
        Vol nouveauVol = new Vol(
            villeDepart, paysDepart, 
            villeArrivee, paysArrivee,
            dateDepart, dureeH, dureeM,
            places, prix
        );
        
        listeVols.add(nouveauVol);
        System.out.println("\nVol ajouté avec succès !");
    }
    
    /**
     * Affiche la liste de tous les vols
     */
    private static void listerVols() {
        if (listeVols.isEmpty()) {
            System.out.println("Aucun vol enregistré.");
            return;
        }
        
        System.out.println("\n=== LISTE DES VOLS ===");
        for (Vol vol : listeVols) {
            System.out.println(vol);
        }
    }
}