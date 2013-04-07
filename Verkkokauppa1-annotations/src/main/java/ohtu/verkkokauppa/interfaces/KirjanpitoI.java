/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa.interfaces;

import java.util.ArrayList;

/**
 *
 * @author aaltotuo
 */
public interface KirjanpitoI {

	ArrayList<String> getTapahtumat();

	void lisaaTapahtuma(String tapahtuma);
	
}
