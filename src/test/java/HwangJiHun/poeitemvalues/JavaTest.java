package HwangJiHun.poeitemvalues;

import org.junit.jupiter.api.Test;

public class JavaTest {

    @Test
    public void javaTest() {
        String string = "ID\n" +
                "CURRENCY_TYPE_NAME\n" +
                "PAY_ID\n" +
                "PAY_LEAGUE_ID\n" +
                "PAY_PAY_CURRENCY_ID\n" +
                "PAY_GET_CURRENCY_ID\n" +
                "PAY_SAMPLE_TIME_UTC\n" +
                "PAY_COUNT\n" +
                "PAY_VALUE\n" +
                "PAY_DATA_POINT_COUNT\n" +
                "PAY_INCLUDES_SECONDARY\n" +
                "PAY_LISTING_COUNT\n" +
                "RECEIVE_ID\n" +
                "RECEIVE_LEAGUE_ID\n" +
                "RECEIVE_PAY_CURRENCY_ID\n" +
                "RECEIVE_GET_CURRENCY_ID\n" +
                "RECEIVE_SAMPLE_TIME_UTC\n" +
                "RECEIVE_COUNT\n" +
                "RECEIVE_VALUE\n" +
                "RECEIVE_DATA_POINT_COUNT\n" +
                "RECEIVE_INCLUDES_SECONDARY\n" +
                "RECEIVE_LISTING_COUNT\n" +
                "PAY_SPARK_LINE_DATA_0\n" +
                "PAY_SPARK_LINE_DATA_1\n" +
                "PAY_SPARK_LINE_DATA_2\n" +
                "PAY_SPARK_LINE_DATA_3\n" +
                "PAY_SPARK_LINE_DATA_4\n" +
                "PAY_SPARK_LINE_DATA_5\n" +
                "PAY_SPARK_LINE_DATA_6\n" +
                "PAY_SPARK_LINE_TOTAL_CHANGE\n" +
                "RECEIVE_SPARK_LINE_DATA_0\n" +
                "RECEIVE_SPARK_LINE_DATA_1\n" +
                "RECEIVE_SPARK_LINE_DATA_2\n" +
                "RECEIVE_SPARK_LINE_DATA_3\n" +
                "RECEIVE_SPARK_LINE_DATA_4\n" +
                "RECEIVE_SPARK_LINE_DATA_5\n" +
                "RECEIVE_SPARK_LINE_DATA_6\n" +
                "RECEIVE_SPARK_LINE_TOTAL_CHANGE\n" +
                "CHAOS_EQUIVALENT\n" +
                "DETAILS_ID\n";
        String[] s = string.split(",\n");
        for (String s1 : s) {
            String[] s3 = s1.split("_");
            String result = "";
            for (String s2 : s3) {
                String suffix = s2.substring(1).toLowerCase();
                result += s2.charAt(0) + suffix;
            }
            System.out.println("result = " + result);
        }
    }
}
