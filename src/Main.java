import JeuPendu.Pendu;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        String rep = "oui", mot = "", rep2;

        System.out.println("Bienvenue dans le jeu du pendu!!!");
        while (rep.equals("oui")){
            Pendu monPendu = new Pendu();
            rep = "non";
            rep2 = "";

            while (!(rep2.equals("oui") || rep2.equals("non"))){
                System.out.println("Voulez vous jouer avec un mot aléatoire ? (oui/non)");
                rep2 = sc.nextLine();
                if (!(rep2.equals("oui") || rep2.equals("non"))){
                    System.out.println("Réponse non reconnue");
                }
            }
            if (rep2.equals("oui")){
                monPendu.setMot_a_trouve(monPendu.getRandomMot());
            } else {
                while (!rep.equals("oui")) {
                    System.out.print("Entrer le mot à faire deviner : ");
                    mot = sc.nextLine();
                    System.out.println("Le mot à trouver est " + mot+ ".");
                    System.out.println("Le mot est-il-correct ? (oui/non)");
                    rep = sc.nextLine();
                    while (!(rep.equals("oui") || rep.equals("non"))){
                        System.out.println("Réponse non reconnue");
                        System.out.println("Le mot est-il-correct ? (oui/non)");
                        rep = sc.nextLine();
                    }
                }
                monPendu.setMot_a_trouve(mot);
            }
            System.out.println("La partie commence!");
            while (monPendu.getCoup() != 0){
                System.out.println("\n<=====================>");
                System.out.println("Il reste "+ monPendu.getCoup() +" coup.");
                System.out.println("Il manque "+ monPendu.getNblettre() +" lettres.");
                System.out.println("Les mot déjà testé sont : "+ monPendu.getMot_test());
                System.out.println("Les lettres déjà testé sont : "+ monPendu.getLettre_test());
                System.out.println(monPendu.getMot_troue());
                System.out.println("Entrer une lettre ou tenter de deviner le mot entier.");
                System.out.println("Pour tenter de deviner le mot entier, entrer le même nombre de caractère que le mot à deviner.");
                mot = sc.nextLine();
                if (mot.length() == monPendu.getMot_a_trouve().length()){
                    monPendu.test_mot(mot);
                } else if (mot.length() != 1){
                    System.out.println("Le nombre de caractère ne correspond pas au mot à deviner.");
                } else {
                    monPendu.test_lettre(mot);
                }
                monPendu.afficher();
                if (monPendu.getNblettre() == 0){
                    System.out.println("Bravo tu as trouvé!!!");
                    System.out.println("Le mot était "+ monPendu.getMot_a_trouve());
                    monPendu.stockerPartie();
                    monPendu.setCoup(0);
                }
            }
            if (monPendu.getNblettre() != 0){
                System.out.println("Dommage tu as perdu...");
                System.out.println("Le mot était "+ monPendu.getMot_a_trouve());
                monPendu.stockerPartie();
            }
            System.out.println("Voulez vous recommencer à jouer?");
            rep = sc.nextLine();
            while (!(rep.equals("oui") || rep.equals("non"))){
                System.out.println("Réponse non reconnue");
                System.out.println("Voulez vous recommencer à jouer?");
                rep = sc.nextLine();
            }
        }
        System.out.println("Fin du jeu");
    }
}