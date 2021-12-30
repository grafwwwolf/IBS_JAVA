import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.google.common.io.Resources;

public class Main {

        private static String path = "F:\\JavaProjects\\IBS_JAVA\\src\\main\\java\\shares.json";
    private static String date = "01.06.70";


    public static void main(String[] args) throws IOException {

        List<Company> companies = getCompanies(path);


        showInfo(companies);

        System.out.println("----------------------------------");

        printSecuritiesInfo(companies);

        System.out.println("----------------------------------");

        showCompaniesCreatedAfterDeterminedDate(companies, date);

        System.out.println("----------------------------------");

        showSecuritiesByCurrency(companies, "RUB");

        System.out.println("----------------------------------");

    }

    public static List<Company> getCompanies(String path) {
        try {
            Path filePath = Paths.get(path);
            String absolutePath = filePath.toAbsolutePath().toString();
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(absolutePath);
            CompanyList companiesEx = objectMapper.readValue(inputStream, new TypeReference<CompanyList>() {
            });
            return companiesEx.getCompanies();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//      Сначала делал через файл в ресурсах:
//    public static List<Company> getCompanies() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        InputStream inputStream = Resources.getResource("shares.json").openStream();
//        CompanyList companiesEx = objectMapper.readValue(inputStream, new TypeReference<CompanyList>() {
//        });
//        return companiesEx.getCompanies();
//    }


    public static void showInfo(List<Company> companyList) {
        try {
            if (companyList.size() == 0) {
                System.out.println("Список пуст");
                return;
            }
            companyList.stream()
                    .forEach(company -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");  //Можно было и через стринг сделать, но раз уж мы прошли LocalDate, то сделал через него
                        LocalDate date = LocalDate.parse(company.getFounded(), formatter);
                        System.out.println(company.getName() + " - Дата основания " + date.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
                    });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void printSecuritiesInfo(List<Company> companyList) {
        try {
            if (companyList.size() == 0) {
                System.out.println("Список пуст");
                return;
            }
            companyList.stream().forEach(company -> company.showSecurities());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void showCompaniesCreatedAfterDeterminedDate(List<Company> companyList, String strDate) {

        try {
            if (companyList.size() == 0) {
                System.out.println("Список пуст");
                return;
            }
            String[] strDateArr = strDate.split("\\W");
            if (strDate.length() == 8) {
                if (Integer.parseInt(strDateArr[2]) > 69) {
                    strDateArr[2] = "19" + strDateArr[2];
                    strDate = strDateArr[0] + "/" + strDateArr[1] + "/" + strDateArr[2];
                }
            }
            strDate = strDate.replaceAll("[.]", "/");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDate.length() == 10 ? "dd/MM/yyyy" : "dd/MM/yy");
            LocalDate determinedDate = LocalDate.parse(strDate, formatter);
            companyList.stream()
                    .filter(company -> {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate date = LocalDate.parse(company.getFounded(), format);
                        return determinedDate.isBefore(date);
                    }).forEach(company -> System.out.println(company.getName() + " основана " + company.getFounded()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат ввода даты.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSecuritiesByCurrency(List<Company> companyList, String currencyCode) {
        try {
            if (companyList.size() == 0) {
                System.out.println("Список пуст");
                return;
            }
            companyList.stream().forEach(company -> company.showSecuritiesByCurrency(currencyCode));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
