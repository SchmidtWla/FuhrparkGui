package fahrzeuge;

import arbeiter.Fahrer;
import arbeiter.Mitarbeiter;
import fuehrerschein.FuehrerscheinKlasse;

import java.util.Comparator;

public abstract class Kfz {

    private int anzahlSitze;
    private int aktuellErhaltenePersonen;
    private int tankGroesse;
    private double tankinhalt;
    private int aktuelleGeschwindigkeit;
    private int maximaleGeschwindigkeit;
    private float anschaffungskosten;
    private GPS aktuelleGPSPosition;
    private Fahrer fahrer;
    private boolean motorstand;
    private Mitarbeiter[] passagiere;
    protected FuehrerscheinKlasse benoetigteKlasse;

    public int getAnzahlSitze() {
        return anzahlSitze;
    }

    public void setAnzahlSitze(int anzahlSitze) {
        this.anzahlSitze = anzahlSitze;
    }

    public int getAktuellErhaltenePersonen() {
        return aktuellErhaltenePersonen;
    }

    public void setAktuellErhaltenePersonen(int aktuellErhaltenePersonen) {
        this.aktuellErhaltenePersonen = aktuellErhaltenePersonen;
    }

    public int getTankGroesse() {
        return tankGroesse;
    }

    public void setTankGroesse(int tankGroesse) {
        this.tankGroesse = tankGroesse;
    }

    public double getTankinhalt() {
        return tankinhalt;
    }

    public void setTankinhalt(double tankinhalt) {
        this.tankinhalt = tankinhalt;
    }

    public int getAktuelleGeschwindigkeit() {
        return aktuelleGeschwindigkeit;
    }

    public void setAktuelleGeschwindigkeit(int aktuelleGeschwindigkeit) {
        this.aktuelleGeschwindigkeit = aktuelleGeschwindigkeit;
    }

    public int getMaximaleGeschwindigkeit() {
        return maximaleGeschwindigkeit;
    }

    public void setMaximaleGeschwindigkeit(int maximaleGeschwindigkeit) {
        this.maximaleGeschwindigkeit = maximaleGeschwindigkeit;
    }

    public float getAnschaffungskosten() {
        return anschaffungskosten;
    }

    public void setAnschaffungskosten(float anschaffungskosten) {
        this.anschaffungskosten = anschaffungskosten;
    }

    public GPS getAktuelleGPSPosition() {
        return aktuelleGPSPosition;
    }

    public void setAktuelleGPSPosition(GPS aktuelleGPSPosition) {
        this.aktuelleGPSPosition = aktuelleGPSPosition;
    }

    public Fahrer getFahrer() {
        return fahrer;
    }

    public void setFahrer(Fahrer fahrer) {
        this.fahrer = fahrer;
    }

    public boolean isMotorstand() {
        return motorstand;
    }

    public void setMotorstand(boolean motorstand) {
        this.motorstand = motorstand;
    }

    public Mitarbeiter[] getPassagiere() {
        return passagiere;
    }

    public void setPassagiere(Mitarbeiter[] passagiere) {
        this.passagiere = passagiere;
    }

    public FuehrerscheinKlasse getBenoetigteKlasse() {
        return benoetigteKlasse;
    }

    public void setBenoetigteKlasse(FuehrerscheinKlasse benoetigteKlasse) {
        this.benoetigteKlasse = benoetigteKlasse;
    }

    public Kfz(int anzahlSitze, int tankGroesse, double tankinhalt, int maximaleGeschwindigkeit, float anschaffungskosten) {
        this.anzahlSitze = anzahlSitze;
        this.tankGroesse = tankGroesse;
        this.tankinhalt = tankinhalt;
        this.maximaleGeschwindigkeit = maximaleGeschwindigkeit;
        this.anschaffungskosten = anschaffungskosten;
        passagiere = new Mitarbeiter[this.anzahlSitze - 1];
        setPassagiere(passagiere);



    }

    public void bremsen(int geschwindigkeit) {

        if(geschwindigkeit < 0) {
            throw new IllegalArgumentException("Es kann nicht negativ gebremst werden");
        }

        if( this.aktuelleGeschwindigkeit - geschwindigkeit <= 0) {
            setAktuelleGeschwindigkeit(0);
            System.out.println("Es wurde auf 0 Km/h gebremst!");

        }
        else {
            setAktuelleGeschwindigkeit(aktuelleGeschwindigkeit - geschwindigkeit);
            System.out.println("Es wurde auf " + this.aktuelleGeschwindigkeit + " Km/h gebremst");
        }

    }

    public void beschleuinigen (int geschwindigkeit) {

        if(geschwindigkeit < 0) {
            throw new IllegalArgumentException("Es kann nicht negativ beschleunigt werden");
        }

        if(geschwindigkeit + this.aktuelleGeschwindigkeit >= this.maximaleGeschwindigkeit) {
            setAktuelleGeschwindigkeit(this.maximaleGeschwindigkeit);
            System.out.println("Es wurde auf " + this.maximaleGeschwindigkeit + " Km/h beschleunigt!");

        }
        else {
            setAktuelleGeschwindigkeit(aktuelleGeschwindigkeit + geschwindigkeit);
            System.out.println("Es wurde auf " + this.aktuelleGeschwindigkeit + " Km/h beschleunigt");
        }

    }

    public void fahren(GPS gpsPosition) {


    }

    public void aussteigen(int sitzplatzNr) {

        if(sitzplatzNr < 0 || sitzplatzNr > this.anzahlSitze) {
            throw new IllegalArgumentException("Sietzplatznummer existiert nicht!");
        }
        if(sitzplatzNr == 0) {
            this.fahrer = null;
        } else {
            passagiere[sitzplatzNr-1] = null;
        }

    }

    public void tanken () {

    }

    public void bewegungsdaten() {

    }

    public void einsteigenFahrer( Fahrer fahrer) {

        if(this.fahrer != null) {
            throw new IllegalArgumentException("Fahrer bereits vorhanden");
        }

        if(fahrer.getFuehrerschein().getFueherscheinKlassen().contains(this.benoetigteKlasse)) {
            setFahrer(fahrer);
        } else {
            throw new IllegalArgumentException("Benoetigte Fuehrerscheinklasse nicht verfuegbar");
        }

    }

    public void einsteigenPassagier (Mitarbeiter mitarbeiter) {

        for(int i = 0; i < passagiere.length; i++) {
            if(this.passagiere[i] == null) {
                this.passagiere[i] = mitarbeiter;
                return;
            }
        }
        throw new IllegalArgumentException("Der Wagen ist voll!");
    }

    public void parken() {

    }

    public abstract float auslastung();

    public static class AuslastungComparator implements Comparator<Kfz> {
        @Override
        public int compare(Kfz o1, Kfz o2) {
            return Double.compare(o1.auslastung(), o2.auslastung());
        }


    }
}
