package main.gusev.java24;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import com.opencsv.bean.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String search = args[0];
        //String search = "Yandex";
        String path = "https://iss.moex.com/iss/securities.json?q=" + search + "&is_trading=1";
        //System.out.println(path);
        URI uri = new URI(path);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .build();
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println("Status: " + response.statusCode());
        String body = response.body();
        //System.out.println(body);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        InfoWrapper fileInfo = mapper.reader(InfoWrapper.class)
                .readValue(body);
        String[] columns = fileInfo.getInfoModel();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals("secid") || columns[i].equals("shortname") || columns[i].equals("regnumber") || columns[i].equals("name")
                    || columns[i].equals("emitent_title") || columns[i].equals("emitent_inn") || columns[i].equals("emitent_okpo")){
                positions.add(i);
            }
        }
        String[][] answer = fileInfo.getAnswer();
        List<CSV> fileCSV = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            CSV temp = new CSV(answer[i][positions.get(0)], answer[i][positions.get(1)], answer[i][positions.get(2)],
                    answer[i][positions.get(3)], answer[i][positions.get(4)], answer[i][positions.get(5)], answer[i][positions.get(6)]);
            fileCSV.add(temp);
        }
        String directory = System.getProperty("user.home");
        //System.out.println(directory);
        StringBuilder sb = new StringBuilder(directory);
        sb.append("\\MOEX securities");
        File dir = new File(sb.toString());
        if (!dir.exists())
            dir.mkdirs();
        sb.append("\\");
        sb.append(search);
        sb.append(".csv");
        try (Writer writer = new FileWriter(sb.toString())){
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder<CSV>(writer).withSeparator(';')
                    .build();
            beanToCsv.write(fileCSV);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}