package JeuPendu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Pendu {
    private String mot_a_trouve;
    private StringBuilder mot_troue;
    private final ArrayList<String> mot_test = new ArrayList<>();
    private final ArrayList<String> lettre_test = new ArrayList<>();
    private int coup = 7;
    private String joueur1;
    private String joueur2;

    public void setMot_a_trouve(String mot_a_trouve){
        mot_a_trouve = mot_a_trouve.toLowerCase(Locale.ROOT);
        this.mot_a_trouve = mot_a_trouve;
        this.mot_troue = new StringBuilder(this.mot_a_trouve);
        for (int i = 1; i < this.mot_troue.length()-1; i++){
            if (this.mot_a_trouve.charAt(i) == '-'){
                this.mot_troue.setCharAt(i, '-');
            } else {
                this.mot_troue.setCharAt(i,'_');
            }
        }
    }

    public void test_mot(String mot){
        mot = mot.toLowerCase(Locale.ROOT);
        if (!this.mot_test.contains(mot)){
            this.mot_test.add(mot);
            if (!this.mot_a_trouve.equals(mot)){
                this.coup -= 1;
            } else {
                this.mot_troue = new StringBuilder(mot);
            }
        } else {
            this.coup -= 1;
        }
    }
    public void test_lettre(String lettre){
        boolean l = false;
        lettre = lettre.toLowerCase(Locale.ROOT);
        if (!this.lettre_test.contains(lettre)){
            for (int i = 1; i < this.mot_troue.length()-1; i++){
                if (this.mot_a_trouve.charAt(i) == lettre.charAt(0)){
                    this.mot_troue.setCharAt(i, lettre.charAt(0));
                    l = true;
                }
            }
        }
        if (!l){
            this.coup -= 1;
        }
        this.lettre_test.add(lettre);
    }

    public int getNblettre(){
        int m = 0;
        for (int i = 0; i < this.mot_troue.length(); i++){
            if (this.mot_troue.charAt(i) == '_'){
                m++;
            }
        }
        return m;
    }

    public StringBuilder getMot_test(){
        StringBuilder str = new StringBuilder();
        for (String s : this.mot_test){
            assert false;
            str.append(s).append("; ");
        }
        return str;
    }
    public StringBuilder getLettre_test(){
        StringBuilder str = new StringBuilder();
        for (String s : this.lettre_test){
            assert false;
            str.append(s).append("; ");
        }
        return str;
    }

    public void stockerPartie() throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("INSERT INTO partie (mot_a_trouve, mot_troue, mot_test, lettre_test, coup) VALUES (?,?,?,?,?)");
        req.setString(1, this.mot_a_trouve);
        req.setString(2, String.valueOf(this.mot_troue));
        req.setString(3, getMot_test().toString());
        req.setString(4, getLettre_test().toString());
        req.setInt(5, this.coup);
        req.executeUpdate();
    }

    public String getRandomMot() throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("SELECT ortho FROM lexique WHERE id_mot = ?");
        Random random = new Random();
        int nbrandom = random.nextInt(1, 137199);
        req.setInt(1, nbrandom);
        ResultSet monResultat = req.executeQuery();
        String str = null;
        while (monResultat.next()) {
            str = monResultat.getString(1);
        }
        this.joueur1 = "Ordinateur";
        return str;
    }

    public String getMot_a_trouve() {
        return this.mot_a_trouve;
    }

    public StringBuilder getMot_troue() {
        return this.mot_troue;
    }

    public int getCoup() {
        return this.coup;
    }
    public void setCoup(int coup) {
        this.coup = coup;
    }

    public void afficher(){
        switch (this.coup) {
            case (0) -> {
                System.out.println(" _____   ");
                System.out.println(" |/  |   ");
                System.out.println(" |   O   ");
                System.out.println(" |  /|\\ ");
                System.out.println(" |  / \\ ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (1) -> {
                System.out.println(" _____   ");
                System.out.println(" |/  |   ");
                System.out.println(" |   O   ");
                System.out.println(" |  /|\\ ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (2) -> {
                System.out.println(" _____   ");
                System.out.println(" |/  |   ");
                System.out.println(" |   O   ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (3) -> {
                System.out.println(" _____   ");
                System.out.println(" |/  |   ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (4) -> {
                System.out.println(" _____   ");
                System.out.println(" |/      ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (5) -> {
                System.out.println("         ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println(" |       ");
                System.out.println("_|___    ");
            }
            case (6) -> {
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("____    ");
            }
            case (7) -> System.out.println();
        }
    }
}