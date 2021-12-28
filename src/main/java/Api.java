import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Api {

    static String path = "C:\\Users\\Стас\\Desktop\\IBS_HW1_git\\ibs_homeWork1_git\\Potter.txt";
//    static String path = null;

    public static void main(String[] args) {

        fileTextStat(path);

    }
    // метод к ДЗ:
    public static void fileTextStat(String path) {
        try {
            Path filePath = Paths.get(path);
            String absolutePath = filePath.toAbsolutePath().toString();
            countingWordToMap(readTextFromFile(absolutePath));
        } catch (NullPointerException e) {
            System.out.println("В качестве пути к файлу было передано Null.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не обнаружен.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//      Вспомогательные методы, можно и без них, но так красивее)):

    private static String readTextFromFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String text = "";
            String inputText;
            while ((inputText = reader.readLine()) != null) {
                if (!inputText.equals("")) {
                    text = text.concat(inputText + " ");
                }
            }
            return text;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private static void countingWordToMap(String text) {
        if (text.equals("")) {
            System.out.println("Файл пуст");
            return;
        }

        int countWords = 0;
        text = text.replaceAll("[\\W&&[^'а-яА-Я]]", " ");

        if (text.split("\\s++").length == 0) {
            System.out.println("В файле нет слов.");
            return;
        }

        Map<String, Integer> map = new TreeMap<>();
        for (String words : text.toLowerCase().split("\\s++")) {
            if (map.get(words) == null) {
                map.put(words, 1);
                countWords++;
            } else {
                map.put(words, (map.get(words) + 1));
                countWords++;
            }
        }
        System.out.println("-------------------------------------------------------");
        System.out.println(map.keySet());
        System.out.println("-------------------------------------------------------");

        Set<Integer> countSet = new TreeSet<>();
        for (Map.Entry<String, Integer> count : map.entrySet()) {
            System.out.println("Слово: " + "\"" + count.getKey() + "\"" + " встречается " + count.getValue() + " раз.");
            countSet.add(count.getValue());
        }
        System.out.println("-------------------------------------------------------");

        List<Integer> countList = new ArrayList<>(countSet); //делал через ArrayList сначала, но потом подумал, что больше памяти занимает и решил ввести дополнительно сет Set<Integer> countSet
        int maxCount = countList.get(countList.size() - 1);

        for (Map.Entry<String, Integer> count : map.entrySet()) {
            if (count.getValue() == maxCount) {
                System.out.println("Слово: " + "\"" + count.getKey() + "\"" + " встречается максимальное количество раз: " + maxCount + " с частотой: " + String.format("%.1f", maxCount * 100.0 / countWords) + " %.");
            }
        }
    }
}
