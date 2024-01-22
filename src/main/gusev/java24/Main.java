package main.gusev.java24;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.net.URI;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        //String search = sc.nextLine();
        String search = "Yandex";
        sc.close();
        String path = "https://iss.moex.com/iss/securities.json?q=" + search + "&is_trading=1";
        System.out.println(path);
        URI uri = new URI(path);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .build();
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        String body = response.body();
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        InfoWrapper fileInfo = mapper.reader(InfoWrapper.class)
                .readValue(body);
        String[] columns = fileInfo.getInfoModel();
        for (int i = 0; i < columns.length; i++) {
            System.out.println(columns[i]);
        }
        Information[] answer = fileInfo.getAnswer();
        for (int i = 0; i < answer.length; i++){
            String[] temp = answer[i].getInfData();
            for (int j = 0; j < temp.length; j++){
                System.out.println(temp[j]);
            }
        }
    }
}