package fr.eisti.pau.cdiscount.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private final static String[] banWords = {"facile", "rapide", "aux", ",", "[?'*]"};
	private final WineService wineService = new WineService(); 

	@GET
	@Path("/find/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("keyword") String keyword){
		List<Wine> tmp = wineService.find(formatKeywords(keyword, 10));
		//List<WineDto> ttmp = WineDto.setTransport(tmp);
		return CDiscountResponse.build("wines", tmp, 0);
	}

	@GET
	@Path("/associated/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAssociated(@PathParam("keyword") String keyword){
		Recipe test = new Recipe();
		test.setTitle(keyword);
		test.setKeyword(formatKeywords(keyword, 3));
		List<Wine> tmp = wineService.findAssociated(test); //WineDto.setTransport(tmp)
		return  CDiscountResponse.build("ok", tmp, 0);//CDiscountResponse.build("", wineService.findAssociated(test), 0);
	}

	
	private static String formatKeywords(String str, int nbKeywordMax){
		String[] copy = str.split(" ");;
		ArrayList<String> tmp = new ArrayList(Arrays.asList(copy));
		String retour = "";
		int j = 0;
		
		// elevement des mots < 3 lettres
		while(j<tmp.size()){
			if(tmp.get(j).length() < 4){
				tmp.remove(j);
			}else ++j;	
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
