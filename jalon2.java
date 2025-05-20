import java.util.Scanner;   

public class Caspratique2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // On crée le scanner
        
        // On demande les soldes
        System.out.println("Combien d'argent a le compte A ?");
        int compteA = sc.nextInt(); // On lit le montant du compte A
        
        System.out.println("Combien d'argent a le compte B ?");
        int compteB = sc.nextInt(); // On lit le montant du compte B
        
        // On affiche les soldes 
        System.out.println("Avant transfert :");
        System.out.println("Compte A : " + compteA + " euros");
        System.out.println("Compte B : " + compteB + " euros");
        
        
        // On demande le montant à transférer
        System.out.println("Combien voulez-vous transférer du compte A vers le compte B ?");
        int transfert = sc.nextInt(); // On lit le montant à transférer
        
        // On fait le transfert
        compteA = compteA - transfert; // On enlève l'argent du compte A
        compteB = compteB + transfert; // On ajoute l'argent au compte B
        
        // On affiche les nouveaux soldes
        System.out.println("Après transfert :");
        System.out.println("Compte A : " + compteA + " euros");
        System.out.println("Compte B : " + compteB + " euros");
        
        sc.close(); // On ferme le scanner
       
     
    }
}
