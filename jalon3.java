import java.util.Scanner;

public class Caspratique3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // On crée un scanner
        
        // Etape 1 : Demander le prix HT
        System.out.println("Entrez le prix HT :");
        double prixHT = sc.nextDouble(); // On lit le prix HT
        
        // Etape 2 : Demander le taux de TVA en %
        System.out.println("Entrez le taux de TVA en % (ex: 20 pour 20%) :");
        double tauxTVA = sc.nextDouble(); // On lit le taux TVA
        
        // Etape 3 : Calculer le montant de la TVA
        double montantTVA = (prixHT * tauxTVA) / 100;
        
        // Etape 4 : Calculer le prix TTC
        double prixTTC = prixHT + montantTVA;
        
        // Etape 5 : Afficher les résultats
       System.out.println("Prix TTC : " + prixTTC + " euros");
        
        sc.close(); // ferme le scanner
          
    }
}
