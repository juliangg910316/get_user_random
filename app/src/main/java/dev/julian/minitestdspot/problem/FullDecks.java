package dev.julian.minitestdspot.problem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FullDecks {

    public static int countFullDecks(String input){
        int result = 0;

        try {
            JSONArray inputArray = new JSONArray(input);
            JSONArray resultArray = new JSONArray();
            JSONArray remaiderArray = new JSONArray();
            boolean fullDecks = false;

            while (!fullDecks) {
                for (int i = 0; i < inputArray.length(); i++) {
                    JSONObject jsonObject = inputArray.getJSONObject(i);
                    if (compareJSONObject(resultArray, jsonObject))
                        remaiderArray.put(jsonObject);
                    else
                        resultArray.put(jsonObject);
                }
                if (resultArray.length() == 52) {
                    result++;
                    inputArray = remaiderArray;
                    resultArray = new JSONArray();
                } else
                    fullDecks = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }

    private static boolean compareJSONObject(JSONArray resultArray, JSONObject jsonObjectInput) throws JSONException {
        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject jsonObject = resultArray.getJSONObject(i);
            String suit = jsonObject.getString("suit");
            String value = getValue(jsonObject);
            if (suit.equals(jsonObjectInput.getString("suit")) && value.equals(getValue(jsonObjectInput)))
                return true;

        }
        return false;
    }

    private static String getValue(JSONObject jsonObject) throws JSONException {
        String value;
        try{
            int valueInt = jsonObject.getInt("value");
            value = valueInt + "";
        } catch (Exception e){
            value = jsonObject.getString("value");
        }
        return value;
    }
}
