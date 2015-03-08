package fr.eisti.pau.cdiscount.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eisti.pau.cdiscount.domain.Recipe;
import fr.eisti.pau.cdiscount.domain.Wine;
import fr.eisti.pau.cdiscount.dto.WineDto;
import fr.eisti.pau.cdiscount.services.WineService;
import fr.eisti.pau.cdiscount.util.CDiscountResponse;

@Path("/wine")
public class WineRestService {
	private final static String[] banWords = {"facile", "rapide", "aux", ",", "[?'*]\\s", "\\A(a-zA-Z)+"};
	private final WineService wineService = new WineService(); 

	/**
	 * Fonction de recherche dans l'api de CDiscount
	 * @param keyword
	 * @return Liste des vins Cdiscount disponibles
	 */
	@GET
	@Path("/find/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("keyword") String keyword){
		List<Wine> tmp = wineService.find(formatKeywords(keyword, 10));
		return CDiscountResponse.build("wines", setDto(tmp), 0);
	}

	/**
	 * Fonction de recherche dans l'api PlatsNatVins puis dans Cdiscount afin de trouver les vins selectionnes
	 * @param keyword
	 * @return Liste des vins Cdiscount disponibles
	 */
	@GET
	@Path("/associated/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAssociated(@PathParam("keyword") String keyword){
		Recipe test = new Recipe();
		test.setTitle(keyword);
		test.setKeyword(formatKeywords(keyword, 3));
		
		List<Wine> restRes = wineService.findAssociated(test);

		return  CDiscountResponse.build("ok", setDto(restRes), 0);
	}

	/**
	 * Transforme une liste de Wine en WineDto pour le transfert
	 * @param in
	 * @return
	 */
	private static List<WineDto> setDto(List<Wine> in){
		List<WineDto> out = new ArrayList<>();
		for(Wine w : in){out.add(WineDto.build(w));}
		// eraseDoublon(out);
		return out;
	}
	
	public static List<WineDto> eraseDoublon(List<WineDto> in){
		Set<WineDto> tmp = new HashSet<>();
		tmp.addAll(in);
		return new ArrayList<>(tmp);
	}
	
	/**
	 * Enleve les mots inutiles à une recherche 
	 * 	- mot < 4 lettres
	 *  - mots != de la liste de ban
	 * @param str string de mots cles
	 * @param nbKeywordMax max de mots valide a retourner
	 * @return
	 */
	private static String formatKeywords(String str, int nbKeywordMax){
		String[] copy = str.split(" ");;
		ArrayList<String> tmp = new ArrayList(Arrays.asList(copy));
		String retour = "";
		int j = 0;
		
		// elevement des mots < 3 lettres
		while(j<tmp.size()){
			if(tmp.get(j).length() < 4) tmp.remove(j);
			else ++j;	
		}
		
		// met sous forme de string
		int indice = (tmp.size() < nbKeywordMax) ? tmp.size() : nbKeywordMax;
		for(int i=0;i<indice;++i){ retour += tmp.get(i)+" ";}
		
		// enleve les mots ban
		String s = retour;
		for(String b : banWords){
			Pattern p = Pattern.compile(b, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(s);
			s = m.replaceAll("");
		}
		System.out.println("keywords : "+s+" ("+str+")");
		return s;
	}
}
