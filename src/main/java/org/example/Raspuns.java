package org.example;

public class Raspuns {
    private String textulRaspunsului;
    private boolean esteAdevarat;

    public Raspuns(){}

    public Raspuns(String textulRaspunsului, boolean esteAdevarat) {
        this.textulRaspunsului = textulRaspunsului;
        this.esteAdevarat = esteAdevarat;
    }

    public String getTextulRaspunsului() {
        return textulRaspunsului;
    }

    public void setTextulRaspunsului(String textulRaspunsului) {
        this.textulRaspunsului = textulRaspunsului;
    }

    public boolean isEsteAdevarat() {
        return esteAdevarat;
    }

    public void setEsteAdevarat(boolean esteAdevarat) {
        this.esteAdevarat = esteAdevarat;
    }

    @Override
    public String toString() {
        return "Raspuns{" +
                "textulRaspunsului='" + textulRaspunsului + '\'' +
                ", esteAdevarat=" + esteAdevarat +
                '}';
    }
}
