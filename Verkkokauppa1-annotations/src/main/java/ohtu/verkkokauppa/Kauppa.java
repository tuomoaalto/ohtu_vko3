package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.PankkiI;
import ohtu.verkkokauppa.interfaces.VarastoI;
import ohtu.verkkokauppa.interfaces.ViitegeneraattoriI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoI varasto;
    private PankkiI pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriI viitegeneraattori;
    private String kaupanTili;

	@Autowired
    public Kauppa(VarastoI varasto, PankkiI pankki, ViitegeneraattoriI viitegeneraattori) {
        this.varasto = varasto;
		this.pankki = pankki;
		this.viitegeneraattori = viitegeneraattori;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
