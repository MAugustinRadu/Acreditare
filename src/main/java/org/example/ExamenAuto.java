package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamenAuto {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        ExamenAuto obiect = new ExamenAuto();


        File file=new File("C:\\Users\\radum\\OneDrive\\Desktop\\Proiect Acreditare\\Intrebari si raspunsuri.txt");
        Scanner sc=new Scanner(file);
        List<String> textDocument = new ArrayList<>();
        sc.useDelimiter("/");
        List<Intrebare> intrebariDeInserat = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
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
        System.out.println(obiect.readAllQuestions());

        //List<Intrebare> exemplu2 = obiect.readAllQuestions();

        //System.out.println(exemplu);
        //System.out.println(exemplu2);
    }

    private boolean insert (Intrebare intrebarea) throws SQLException {
        final String URLDB = "jdbc:postgresql://localhost:5432/LucrareAcreditare";
        final String USERNAMEDB ="postgres";
        final String PWDDB ="admin";
        Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        PreparedStatement pSt = conn.prepareStatement("insert into chestionare(intrebare, optiuneaa, optiuneab, optiuneac, categoria, optiuneaabool, optiuneabbool, optiuneacbool) values(?, ?, ?, ?, ?, ?, ?, ?)");
        pSt.setString(1, intrebarea.getIntrebare());
        pSt.setString(2, intrebarea.optiuneaA.getTextulRaspunsului());
        pSt.setString(3, intrebarea.optiuneaB.getTextulRaspunsului());
        pSt.setString(4, intrebarea.optiuneaC.getTextulRaspunsului());
        pSt.setString(5, intrebarea.getCategorie());
        pSt.setBoolean(6,intrebarea.optiuneaA.isEsteAdevarat());
        pSt.setBoolean(7,intrebarea.optiuneaB.isEsteAdevarat());
        pSt.setBoolean(8,intrebarea.optiuneaC.isEsteAdevarat());
        int val = pSt.executeUpdate(); // 1

        boolean ok = false;
        if(val!=0)
            ok=true;
        return ok;
    }

    private List<Intrebare> readAllQuestions() throws SQLException {
        List<Intrebare> listaIntrebari =new ArrayList<>();

        final String URLDB = "jdbc:postgresql://localhost:5432/LucrareAcreditare";
        final String USERNAMEDB ="postgres";
        final String PWDDB ="admin";
        Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

        Statement pSt = conn.createStatement();

        ResultSet rs = pSt.executeQuery("select * from chestionare order by id asc");

        while(rs.next()) {

            String textIntrebare = rs.getString("intrebare").trim();
            String categoria = rs.getString("categoria").trim();
            String optiuneaA = rs.getString("optiuneaa");
            String optiuneaB = rs.getString("optiuneab");
            String optiuneaC = rs.getString("optiuneac");
            Boolean optiuneaAbool = rs.getBoolean("optiuneaabool");
            Boolean optiuneaBbool = rs.getBoolean("optiuneabbool");
            Boolean optiuneaCbool = rs.getBoolean("optiuneacbool");
            Raspuns rOptiuneaA = new Raspuns(optiuneaA,optiuneaAbool);
            Raspuns rOptiuneaB = new Raspuns(optiuneaB,optiuneaBbool);
            Raspuns rOptiuneaC = new Raspuns(optiuneaC,optiuneaCbool);

            Intrebare intrebarea = new Intrebare(textIntrebare,categoria,rOptiuneaA,rOptiuneaB,rOptiuneaC);
            listaIntrebari.add(intrebarea);

        }
        return listaIntrebari;

    }
}
