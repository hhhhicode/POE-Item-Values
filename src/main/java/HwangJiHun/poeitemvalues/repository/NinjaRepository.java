package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.model.ninja.DivinationCardOverview;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Repository
public class NinjaRepository {

    @Value("${game.league.name}")
    private String leagueName;
    private String type = "Currency";
    private String language = "ko";

    public CurrencyOverview getCurrencyOverview(String apiEndPoint, String typeName) throws IOException {

        String requestURL = getRequestURL(apiEndPoint, typeName);
        URL url = new URL(requestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        BufferedReader br = getBufferedReader(conn);
        StringBuilder sb = getStringBuilder(br);

        br.close();
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        /* JSON -> Map */
//        Map<String, Object> currencyMap = mapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});

        /* JSON -> VO, JSON이 스네이크 케이스이면 @JsonIgnoreProperties와 JsonProperty를 추가해줘야 한다. */
//        CurrencyOverview currencyOverview = mapper.readValue(sb.toString(), new TypeReference<CurrencyOverview>() {});

        return mapper.readValue(sb.toString(), new TypeReference<CurrencyOverview>() {});
    }
    public DivinationCardOverview getDivinationCardOverview(String apiEndPoint, String typeName) throws IOException {

        String requestURL = getRequestURL(apiEndPoint, typeName);
        URL url = new URL(requestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        BufferedReader br = getBufferedReader(conn);
        StringBuilder sb = getStringBuilder(br);

        br.close();
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(sb.toString(), new TypeReference<DivinationCardOverview>() {});
    }


    private StringBuilder getStringBuilder(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append(br.readLine());
        }

        return sb;
    }

    private BufferedReader getBufferedReader(HttpURLConnection conn) {
        BufferedReader br = null;
        try {
            InputStream inputStream;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }
            br = new BufferedReader(
                    new InputStreamReader(
                            inputStream,
                            StandardCharsets.UTF_8
                    )
            );
        } catch (Exception e) {
            //TODO 로그 남기는 체계적인 방법 적용하기.
            log.info("getCurrencyOverview ERROR", e);
        }

        return br;
    }

    private String getRequestURL(String apiEndPoint, String typeName) {

        return new StringBuilder()
                .append(apiEndPoint)
                .append("?league=" + leagueName)
                .append("&type=" + typeName)
                .toString();
    }
}
