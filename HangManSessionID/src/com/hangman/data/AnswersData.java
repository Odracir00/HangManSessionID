package com.hangman.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.hangman.elements.Answer;

/**
This is an utility class responsible for loading and retrieving “Answers' for
 * the HangMan game. In a more complex project, this class would be replaced by
 * importing data from an external resource (e: database, web service, ...)
 */
public class AnswersData {
	
    private AnswersData() {
    }
        
    final private static Map<Integer, Answer> answers = new HashMap<>();

	static {
		// it stays here by now
		DAOAnswersManager daoManager = new DAOAnswersManager();

		List<Answer> ansList = daoManager.listAnswers();
		
		for(Answer ans: ansList){
			answers.put(ans.getId(), ans);
		}
	}
    
//    static {
//        String[] countries = {
//            "afghanistan",
//            "albania",
//            "algeria",
//            "andorra",
//            "angola",
//            // "antigua and barbuda", // Only one word countries are supported
//            "argentina",
//            "armenia",
//            "australia",
//            "austria",
//            "azerbaijan",
//            "bahamas",
//            "bahrain",
//            "bangladesh",
//            "barbados",
//            "belarus",
//            "belgium",
//            "belize",
//            "benin",
//            "bhutan",
//            "bolivia",
//            // "bosnia and herzegovina",
//            "botswana",
//            "brazil",
//            "brunei",
//            "bulgaria",
//            // "burkina faso",
//            "myanmar",
//            "burundi",
//            "cambodia",
//            "cameroon",
//            "canada",
//            // "cape verde",
//            // "central african republic",
//            "chad",
//            "chile",
//            "china",
//            "colombia",
//            "comoros",
//            "congo",
//            // "democratic republic of the congo",
//            // "costa rica",
//            // "cote d ivoire",
//            "croatia",
//            "cuba",
//            "cyprus",
//            // "czech republic",
//            "denmark",
//            "djibouti",
//            "dominica",
//            // "dominican republic",
//            "ecuador",
//            // "east timor",
//            "egypt",
//            // "el salvador",
//            "england",
//            // "equatorial guinea",
//            "eritrea",
//            "estonia",
//            "ethiopia",
//            "fiji",
//            "finland",
//            "france",
//            "gabon",
//            "gambia",
//            "georgia",
//            "germany",
//            "ghana",
//            // "great britain",
//            "greece",
//            "grenada",
//            "guatemala",
//            "guinea",
//            // "guinea bissau",
//            "guyana",
//            "haiti",
//            "honduras",
//            "hungary",
//            "iceland",
//            "india",
//            "indonesia",
//            "iran",
//            "iraq",
//            "ireland",
//            "israel",
//            "italy",
//            "jamaica",
//            "japan",
//            "jordan",
//            "kazakhstan",
//            "kenya",
//            "kiribati",
//            // "north korea",
//            // "south korea",
//            "kosovo",
//            "kuwait",
//            "kyrgyzstan",
//            "laos",
//            "latvia",
//            "lebanon",
//            "lesotho",
//            "liberia",
//            "libya",
//            "liechtenstein",
//            "lithuania",
//            "luxembourg",
//            "macedonia",
//            "madagascar",
//            "malawi",
//            "malaysia",
//            "maldives",
//            "mali",
//            "malta",
//            // "marshall islands",
//            "mauritania",
//            "mauritius",
//            "mexico",
//            "micronesia",
//            "moldova",
//            "monaco",
//            "mongolia",
//            "montenegro",
//            "morocco",
//            "mozambique",
//            "myanmar",
//            "namibia",
//            "nauru",
//            "nepal",
//            "netherlands",
//            // "new zealand",
//            "nicaragua",
//            "niger",
//            "nigeria",
//            "norway",
//            // "northern ireland",
//            "oman",
//            "pakistan",
//            "palau",
//            "palestinian",
//            "panama",
//            // "papua new guinea",
//            "paraguay",
//            "peru",
//            "philippines",
//            "poland",
//            "portugal",
//            "qatar",
//            "romania",
//            "russia",
//            "rwanda",
//            // "st kitts and nevis",
//            // "st lucia",
//            // "st vincent and the grenadines",
//            "samoa",
//            // "san marino",
//            // "sao tome and principe",
//            // "saudi arabia",
//            "scotland",
//            "senegal",
//            "serbia",
//            "seychelles",
//            // "sierra leone",
//            "singapore",
//            "slovakia",
//            "slovenia",
//            // "solomon islands",
//            "somalia",
//            // "south africa",
//            "spain",
//            // "sri lanka",
//            "sudan",
//            "suriname",
//            "swaziland",
//            "sweden",
//            "switzerland",
//            "syria",
//            "taiwan",
//            "tajikistan",
//            "tanzania",
//            "thailand",
//            "togo",
//            "tonga",
//            // "trinidad and tobago",
//            "tunisia",
//            "turkey",
//            "turkmenistan",
//            "tuvalu",
//            "uganda",
//            "ukraine",
//            // "united arab emirates",
//            // "united kingdom",
//            // "united states",
//            "uruguay",
//            "uzbekistan",
//            "vanuatu",
//            // "vatican city",
//            "venezuela",
//            "vietnam",
//            // "western sahara",
//            "wales",
//            "yemen",
//            "zaire",
//            "zambia",
//            "zimbabwe"
//        };
//        for (int i = 0; i < countries.length; i++) {
//            answers.put(i + 1, new Answer(AnswerType.COUNTRY ,countries[i]));
//        }
//    }

    public static int getRandomAnswerId() {
        int randomIndex = new Random().nextInt(answers.keySet().size()) + 1;
        return randomIndex;
    }

    public static Answer getAnswerFromId(Integer id) {
        return answers.get(id);
    }
}
