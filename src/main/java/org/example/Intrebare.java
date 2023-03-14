package org.example;

public class Intrebare {
    private String intrebare;
    private String categorie;
    public Raspuns optiuneaA;
    public Raspuns optiuneaB;
    public Raspuns optiuneaC;


    public Intrebare(String intrebare, String categorie, Raspuns optiuneaA, Raspuns optiuneaB, Raspuns optiuneaC) {
        this.intrebare = intrebare;
        this.categorie = categorie;
        this.optiuneaA = optiuneaA;
        this.optiuneaB = optiuneaB;
        this.optiuneaC = optiuneaC;
    }

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Raspuns getOptiuneaA() {
        return optiuneaA;
    }

    public void setOptiuneaA(Raspuns optiuneaA) {
        this.optiuneaA = optiuneaA;
    }

    public Raspuns getOptiuneaB() {
        return optiuneaB;
    }

    public void setOptiuneaB(Raspuns optiuneaB) {
        optiuneaB = optiuneaB;
    }

    public Raspuns getOptiuneaC() {
        return optiuneaC;
    }

    public void setOptiuneaC(Raspuns optiuneaC) {
        optiuneaC = optiuneaC;
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "intrebare='" + intrebare + '\'' +
                ", categorie='" + categorie + '\'' +
                ", optiuneaA=" + optiuneaA +
                ", optiuneaB=" + optiuneaB +
                ", optiuneaC=" + optiuneaC +
                '}' + '\n';
    }
}
