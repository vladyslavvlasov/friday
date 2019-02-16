package com.vladyslavvlasov.app;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class AddressJSONConverter {

    static String houseNum;
    static String streetName;
    static String endRegex = "(No ?|# ?)?\\d+ *[a-zA-Z]?$";
    static String startRegex = "^(No|#)? ?\\d+ *([a-zA-Z]? )?";


    public static void main(String[] args) {
        returnJSONAdress("54");
    }


    public static JSONObject returnJSONAdress(String address) {
        JSONObject jsonAdr = new JSONObject();

        Pattern pattern = Pattern.compile(endRegex);
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            houseNum = matcher.group().trim();
            streetName = address.replaceAll(endRegex, "").replace(",", "").trim();
            jsonAdr.put("street", streetName);
            jsonAdr.put("housenumber", houseNum);
        } else {
            pattern = Pattern.compile(startRegex);
            matcher = pattern.matcher(address);
            if (matcher.find()) {
                houseNum = matcher.group().trim();
                streetName = address.replaceAll(startRegex, "").replace(",", "").trim();
                jsonAdr.put("street", streetName);
                jsonAdr.put("housenumber", houseNum);
            }
            /**
             * I've decided to add a case handler for incorrect input, e.g. street without house number.
             * We would get to this point only if the program can't match house number both in the beginning and end of address.
             * If we can't get a house number, we return json with input address as street and "not recognized" in street field
             */
            else{
                jsonAdr.put("street",address);
                jsonAdr.put("housenumber", "not recognized");
            }
        }

        System.out.println(jsonAdr.toString());
        return jsonAdr;
    }
}
