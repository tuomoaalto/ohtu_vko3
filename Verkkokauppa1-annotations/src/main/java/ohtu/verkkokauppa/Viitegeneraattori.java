package ohtu.verkkokauppa;

import ohtu.verkkokauppa.interfaces.ViitegeneraattoriI;
import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements ViitegeneraattoriI {
    
    private int seuraava;

    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
	@Override
    public int uusi(){
        return seuraava++;
    }
}
