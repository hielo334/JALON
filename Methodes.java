package Jalon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Methodes {

    // Données contantes, respect de l'ordre
    public static final String[] MARQUES = {"Wolskwagen", "Audi", "Porsche", "Lamborghini"};
    public static final String[][] MODELES = {
        {"Polo", "Tiguan", "Golf"},
        {"A3", "Q5", "A4"},
        {"Macan", "Carrera"},
        {"Huracan", "Aventador"}
    };
    public static final String[] COULEURS = {"Blanc", "Noir", "Rouge", "Bleu"};
    public static final int[] SUPPLEMENTS_COULEURS = {500, 0, 1000, 2000};
    public static final int[][] PRIX = {
        {23000, 36000, 29000},   // Wolskwagen
        {34000, 54000, 43000},   // Audi
        {70000, 120000},         // Porsche
        {260000, 520000}         // Lamborghini
    };

    // Liste pour stocker les voitures
    public static ArrayList<String[]> voitures = new ArrayList<>();
    //Fonction pour generer le code de la voiture (marque, modele et date)
    public static String genererCode(String marque, String modele) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(formatter);
        return marque.substring(0, 2).toUpperCase() + 
               modele.substring(0, 2).toUpperCase() + 
               "-" + date;
    }
    //Fonction pour calculer remise sur une voiture d'occasion
    public static double calculerRemise(int kilometrage) {
        if (kilometrage >= 200000) return 0.5;
        if (kilometrage >= 100000) return 0.25;
        return 0.1;
    }
    //Fonction pour calculer le prix total (avec couleur)
    public static int calculerPrix(int indexMarque, int indexModele, int indexCouleur, boolean estNeuf, int kilometrage) {
        int prixBase = PRIX[indexMarque][indexModele];
        int supplementCouleur = SUPPLEMENTS_COULEURS[indexCouleur];
        double prix = prixBase + supplementCouleur;
        
        if (!estNeuf) {
            prix *= (1 - calculerRemise(kilometrage));
        }
        
        return (int) prix;
    }
    //Fonction pour formater les infos de la voiture
    public static String formaterInfoVoiture(String[] voiture) {
        return String.format(
            "[code: %s | marque: %s | modèle: %s | Neuf: %s%s | Couleur: %s | Prix total: %,d EUR ]",
            voiture[0], voiture[1], voiture[2], 
            voiture[3].equals("true") ? "oui" : "non",
            voiture[3].equals("true") ? "" : " | Kilométrage: " + voiture[4],
            voiture[5], Integer.parseInt(voiture[6])
        );
    }
    //Fonction pour choix valide
    public static boolean choixValide(int choix, int max) {
        return choix >= 1 && choix <= max;
    }
}