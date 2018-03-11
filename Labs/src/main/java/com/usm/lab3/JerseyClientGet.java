package com.usm.lab3;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

public class JerseyClientGet {

    public static void main(String[] args) {

        Client client = Client.create();

        String[] dates = parseCsvDates();

        Workbook wb = new HSSFWorkbook();


        for (int i = 0; i < dates.length; i++) {

            WebResource webResource = client.resource("https://bnm.md/en/official_exchange_rates?get_xml=1&date=" + dates[i]);

            ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);

            if(response.getStatus() != 200){
                throw  new RuntimeException("Failed: HTTP error code " + response.getStatus());
            }

            String output = response.getEntity(String.class);

            XmlMapper xmlMapper = new XmlMapper();

            ValCurs currencyRate = null;

            try {
               currencyRate = xmlMapper.readValue(output, ValCurs.class);
            } catch (IOException e) { e.printStackTrace();
            }
            String[] columns = {"numCode", "charCode", "nominal", "name", "value"};
            Sheet sheet = wb.createSheet("Date " + dates[i]);

            Valute[] currencies = currencyRate.getCurrencies();
            for (int j = 0; j < columns.length; j++) {
                Row headerRow = sheet.createRow(0);

                Cell cell = headerRow.createCell(j);
                cell.setCellValue(columns[j]);

                for(int k = 0; k < currencies.length; k++) {
                    Row row  = sheet.createRow(k);
                    row.createCell(0).setCellValue(currencies[k].getName());
                    row.createCell(1).setCellValue(currencies[k].getNumCode());
                    row.createCell(2).setCellValue(currencies[k].getCharCode());
                    row.createCell(3).setCellValue(currencies[k].getNominal());
                    row.createCell(4).setCellValue(currencies[k].getValue());
                }

            }

            System.out.println("Output from server");
            System.out.println(output);
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Labs/src/main/java/com/usm/lab3/workbook.xls");
            wb.write(fileOut);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String[] parseCsvDates() {

        String csvFile = "Labs/src/main/java/com/usm/lab3/dates.csv";
        BufferedReader br = null;
        String line = "";
        String[] dates = null;

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while((line = br.readLine()) != null){
                dates = line.split(",");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dates;
    }
}
