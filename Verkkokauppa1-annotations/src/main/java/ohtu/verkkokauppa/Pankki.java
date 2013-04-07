package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.KirjanpitoI;
import ohtu.verkkokauppa.interfaces.PankkiI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements PankkiI {

    private KirjanpitoI kirjanpito;

	@Autowired
    public Pankki(KirjanpitoI kirjanpito) {
        this.kirjanpito = kirjanpito;
	}

	@Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
