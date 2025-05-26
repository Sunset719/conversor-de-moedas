import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorDeMoedas {

    private static final String API_KEY = "e9e7d05959bb46f21fa51186";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== CONVERSOR DE MOEDAS ===");
            System.out.println("1. BRL -> USD");
            System.out.println("2. BRL -> EUR");
            System.out.println("3. BRL -> GBP");
            System.out.println("4. USD -> BRL");
            System.out.println("5. EUR -> BRL");
            System.out.println("6. BRL -> JPY");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção (1-7): ");

            int opcao = scanner.nextInt();
            String de = "", para = "";

            switch (opcao) {
                case 1 -> { de = "BRL"; para = "USD"; }
                case 2 -> { de = "BRL"; para = "EUR"; }
                case 3 -> { de = "BRL"; para = "GBP"; }
                case 4 -> { de = "USD"; para = "BRL"; }
                case 5 -> { de = "EUR"; para = "BRL"; }
                case 6 -> { de = "BRL"; para = "JPY"; }
                case 7 -> {
                    System.out.println("Encerrando o conversor...");
                    continuar = false;
                    continue;
                }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double resultado = obterConversaoDireta(de, para, valor);

            if (resultado >= 0) {
                System.out.printf(">> %.2f %s = %.2f %s%n", valor, de, resultado, para);
            } else {
                System.out.println("Erro ao realizar a conversão.");
            }
        }

        scanner.close();
    }

    public static double obterConversaoDireta(String de, String para, double valor) {
        try {
            String urlStr = API_URL + de + "/" + para + "/" + valor;
            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = in.readLine()) != null) {
                resposta.append(linha);
            }
            in.close();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(resposta.toString(), JsonObject.class);

            if (json.get("result").getAsString().equals("success")) {
                return json.get("conversion_result").getAsDouble();
            } else {
                System.out.println("Erro na API: " + json);
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return -1;
        }
    }
}
