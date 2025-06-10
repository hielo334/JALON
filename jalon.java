package JALON;

// Importations des bibliothèques nécessaires
import java.time.LocalDate;         // Pour manipuler les dates
import java.time.format.DateTimeFormatter;  // Pour formater les dates
import java.util.Scanner;           // Pour lire les entrées clavier

public class jalon {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Variable booléenne pour la boucle du menu
        boolean continuer = true;

        // Boucle principale
        while (continuer) {
            
            // Affichage du menu
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Afficher les nombres de 1 à 10");
            System.out.println("2. Afficher la date du jour (JJ/MM/AA)");
            System.out.println("3. Tester la division par zéro");
            System.out.println("4. Quitter");
            System.out.print("Votre choix (1-4) : ");

            // Gestion des erreurs si l'utilisateur n'entre pas un nombre
            try {
                // Lecture du choix utilisateur
                int choix = scanner.nextInt();
                scanner.nextLine(); // Nettoie le buffer d'entrée

                // Structure "switch" pour exécuter l'action choisie
                switch (choix) {
                    // Cas 1 : Afficher les nombres de 1 à 10
                    case 1:
                        System.out.println("\nCompte de 1 à 10 :");
                        for (int i = 1; i <= 10; i++) {
                            System.out.println(i);
                        }
                        break;
                    // Cas 2 : Afficher la date du jour
                    case 2:
                        System.out.println("\nDate d'aujourd'hui :");
                        LocalDate aujourdhui = LocalDate.now(); // Récupère la date actuelle
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy"); // Format JJ/MM/AA
                        System.out.println(aujourdhui.format(formatter)); // Affiche la date formatée
                        break;

                    // Cas 3 : Tester une division par zéro avec gestion d'erreur
                    case 3:
                        System.out.println("\nTest division par zéro :");
                        try {
                            int result = 10 / 0; // Division impossible → déclenche une erreur
                            System.out.println(result);
                        } catch (ArithmeticException e) {
                            System.out.println("Erreur : Division par zéro"); // Message d'erreur
                        }
                        break;
                    // Cas 4 : Quitter le programme
                    case 4:
                        continuer = false;
                        System.out.println("\nAu revoir !");
                        break;

                    // Si l'utilisateur entre un nombre hors menu
                    default:
                        System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 4.");
                        break;
                }
            } catch (Exception e) {
                // Si l'utilisateur entre autre chose qu'un nombre
                System.out.println("Erreur : Veuillez entrer un nombre valide (1-4).");
                scanner.nextLine(); // Nettoie le buffer pour éviter une boucle infinie
            }
        }
    
        scanner.close();
    }
}