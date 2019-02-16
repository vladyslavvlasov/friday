package com.vladyslavvlasov.app;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Unit test for simple AddressJSONConverter.
 */
public class JSONConverterTest {

    @Test(dataProvider = "Address list")
    public void getAddressAsJSON(String address, JSONObject expectedResult) {
        Assert.assertEquals(AddressJSONConverter.returnJSONAdress(address).toString(), expectedResult.toString(), "Expected JSON address to be equal to " + expectedResult.toString());
    }

    @DataProvider(name = "Address list")

    public static Object[][] addressList() {

        return new Object[][]{
                {"Winterallee 3", new JSONObject("{\"street\": \"Winterallee\", \"housenumber\": \"3\"}")},
                {"Musterstrasse 45", new JSONObject("{\"street\": \"Musterstrasse\", \"housenumber\": \"45\"}")},
                {"Blaufeldweg 123B", new JSONObject("{\"street\": \"Blaufeldweg\", \"housenumber\": \"123B\"}")},
                {"Am Bächle 23", new JSONObject("{\"street\": \"Am Bächle\", \"housenumber\": \"23\"}")},
                {"Auf der Vogelwiese 23 b", new JSONObject("{\"street\": \"Auf der Vogelwiese\", \"housenumber\": \"23 b\"}")},
                {"4, rue de la revolution", new JSONObject("{\"street\": \"rue de la revolution\", \"housenumber\": \"4\"}")},
                {"200 Broadway Av", new JSONObject("{\"street\": \"Broadway Av\", \"housenumber\": \"200\"}")},
                {"Calle Aduana, 29", new JSONObject("{\"street\": \"Calle Aduana\", \"housenumber\": \"29\"}")},
                {"Calle 39 No 1540", new JSONObject("{\"street\": \"Calle 39\", \"housenumber\": \"No 1540\"}")},
                //Adding one more case when street name starts with a number
                {"3 Stroiteley, 3", new JSONObject("{\"street\": \"3 Stroiteley\", \"housenumber\": \"3\"}")},
                //Adding a test case with street with no number
                {"Abrakadbra Street", new JSONObject("{\"street\": \"Abrakadbra Street\", \"housenumber\": \"not recognized\"}")}
        };

    }
}
