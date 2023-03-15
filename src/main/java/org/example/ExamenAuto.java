package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamenAuto {
    public static void main(String[] args) throws SQLException {
        ExamenAuto obiect = new ExamenAuto();
        menu();


        //List<Intrebare> exemplu2 = obiect.readAllQuestions();

        //System.out.println(exemplu);
        //System.out.println(exemplu2);
    }

    private boolean insert(Intrebare intrebarea) throws SQLException {
        final String URLDB = "jdbc:postgresql://localhost:5432/LucrareAcreditare";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "admin";
        Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        PreparedStatement pSt = conn.prepareStatement("insert into chestionare(intrebare, optiuneaa, optiuneab, optiuneac, categoria, optiuneaabool, optiuneabbool, optiuneacbool) values(?, ?, ?, ?, ?, ?, ?, ?)");
        pSt.setString(1, intrebarea.getIntrebare());
        pSt.setString(2, intrebarea.optiuneaA.getTextulRaspunsului());
        pSt.setString(3, intrebarea.optiuneaB.getTextulRaspunsului());
        pSt.setString(4, intrebarea.optiuneaC.getTextulRaspunsului());
        pSt.setString(5, intrebarea.getCategorie());
        pSt.setBoolean(6, intrebarea.optiuneaA.isEsteAdevarat());
        pSt.setBoolean(7, intrebarea.optiuneaB.isEsteAdevarat());
        pSt.setBoolean(8, intrebarea.optiuneaC.isEsteAdevarat());
        int val = pSt.executeUpdate(); // 1

        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;
    }

    private List<Intrebare> readAllQuestions() throws SQLException {
        List<Intrebare> listaIntrebari = new ArrayList<>();

        final String URLDB = "jdbc:postgresql://localhost:5432/LucrareAcreditare";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "admin";
        Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        Statement pSt = conn.createStatement();

        ResultSet rs = pSt.executeQuery("select * from chestionare order by id asc");

        while (rs.next()) {

            String textIntrebare = rs.getString("intrebare").trim();
            String categoria = rs.getString("categoria").trim();
            String optiuneaA = rs.getString("optiuneaa");
            String optiuneaB = rs.getString("optiuneab");
            String optiuneaC = rs.getString("optiuneac");
            Boolean optiuneaAbool = rs.getBoolean("optiuneaabool");
            Boolean optiuneaBbool = rs.getBoolean("optiuneabbool");
            Boolean optiuneaCbool = rs.getBoolean("optiuneacbool");
            Raspuns rOptiuneaA = new Raspuns(optiuneaA, optiuneaAbool);
            Raspuns rOptiuneaB = new Raspuns(optiuneaB, optiuneaBbool);
            Raspuns rOptiuneaC = new Raspuns(optiuneaC, optiuneaCbool);

            Intrebare intrebarea = new Intrebare(textIntrebare, categoria, rOptiuneaA, rOptiuneaB, rOptiuneaC);
            listaIntrebari.add(intrebarea);

        }
        return listaIntrebari;

    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        boolean dontExit = true;
        do {
            System.out.println("Bun venit la simulatorul de examen auto");
            System.out.println("Selectati optiunea care o doriti sau scrieti exit pentru a iesi");
            System.out.println("1.Chestionare/Mediu de invatare");
            System.out.println("2.Simulare examen");
            System.out.println("3.Introducere intrebari noi");
            System.out.println("exit");
            String optiunea = input.nextLine();
            switch (optiunea) {
                case "1":
                    //to be implemented
                    break;
                case "2":
                    //to be implemented
                    break;
                case "3":
                    System.out.println("Documentul trebuie sa fie de forma:Textul Intrebarii/\n" +
                            "Optiunea A/\n" +
                            "true sau false optiunea A/\n" +
                            "Optiunea B/\n" +
                            "true sau false optiunea B/\n" +
                            "Optiunea C/\n" +
                            "true sau false optiunea C/\n" +
                            "Categoria/");
                    System.out.println("Va rog scrieti path-ul documentului (ex. C:\\Users\\radum\\OneDrive\\Desktop\\Proiect Acreditare\\Intrebari si raspunsuri.txt)");
                    String path = input.nextLine();
                    System.out.println("Va rog scrieti cate intrebari doriti ca programul sa citeasca");
                    int numberOfQuestions = input.nextInt();
                    try {
                        System.out.println("Acestea sunt intrebariile pe care doriti sa le inserati " + readNewQuestions(path,numberOfQuestions));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "exit":
                   dontExit = false;
                    break;
            }
        } while (dontExit);
    }
    public static List<Intrebare> readNewQuestions(String path,int numberOfQuestions) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("/");
        List<Intrebare> intrebariDeInserat = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            String textIntrebare = sc.next();
            String optiuneaA = sc.next();
            String temporar = sc.next();
            boolean optiuneaABoolean;
            if (temporar.equals("\r\nfalse")) {
                optiuneaABoolean = false;
            } else
                optiuneaABoolean = true;
            String optiuneaB = sc.next();
            temporar = sc.next();
            boolean optiuneaBBoolean;
            if (temporar.equals("\r\nfalse")) {
                optiuneaBBoolean = false;
            } else
                optiuneaBBoolean = true;
            String optiuneaC = sc.next();
            temporar = sc.next();
            boolean optiuneaCBoolean;
            if (temporar.equals("\r\nfalse")) {
                optiuneaCBoolean = false;
            } else
                optiuneaCBoolean = true;
            String categoria = sc.next();
            Raspuns raspuns1 = new Raspuns(optiuneaA, optiuneaABoolean);
            Raspuns raspuns2 = new Raspuns(optiuneaB, optiuneaBBoolean);
            Raspuns raspuns3 = new Raspuns(optiuneaC, optiuneaCBoolean);
            Intrebare deInserat = new Intrebare(textIntrebare, categoria, raspuns1, raspuns2, raspuns3);
            intrebariDeInserat.add(deInserat);
        }
        sc.close();
        return intrebariDeInserat;
    }
}
