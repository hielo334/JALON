
    import java.util.Scanner; 

public class Caspratique1 {
    public static void main(String[] args) {
      
       // On crée un scanner pour demander l'âge

        Scanner scanner = new Scanner(System.in);
        
         // On demande l'âge

        System.out.println("Quel est votre âge ?"); 
        int age = scanner.nextInt();                                              
        
        // On vérifie si mineur ou majeur
        if (age < 18) {                                                                             // Si moins de 18
            System.out.println("Vous êtes mineur !");
        } else {                                                                                        // Sinon (18 ou plus)
            System.out.println("Vous êtes majeur !");
        }
        
        scanner.close();                               // On ferme le scanner 
    }
}