package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) 
	{
		int a=0,b=1,c=2,d=3,e=4,f=5,g=6,h=7;
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
		
		Kirjanpito kirjanpito      = ctx.getBean(Kirjanpito.class);
		Varasto varasto            = ctx.getBean(Varasto.class);
		Pankki pankki              = ctx.getBean(Pankki.class);
		Viitegeneraattori viitegen = ctx.getBean(Viitegeneraattori.class);
		Kauppa kauppa              = ctx.getBean(Kauppa.class);
				
//		Kirjanpito kirjanpito      = new Kirjanpito();
//		Varasto varasto            = new Varasto(kirjanpito);
//		Pankki pankki              = new Pankki(kirjanpito);
//		Viitegeneraattori viitegen = new Viitegeneraattori();
//		Kauppa kauppa              = new Kauppa(varasto, pankki, viitegen);

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
