# 💱 Conversor de Moedas em Java

Este é um projeto simples de **conversor de moedas** feito em **Java**, que utiliza a **API ExchangeRate** para obter taxas de câmbio em tempo real. O programa é executado no **console** e permite converter valores entre diferentes moedas.

---

## 🚀 Funcionalidades

- Conversão entre moedas com base em dados atualizados da API.
- Suporte a pelo menos 6 pares de moedas.
- Interação direta via terminal.
- Integração com a biblioteca **Gson** para tratamento de JSON.

---

## 🛠️ Tecnologias utilizadas

- **Java 17+**
- **Gson (Google JSON)**
- **ExchangeRate API**
- **IntelliJ IDEA** (ou qualquer IDE de sua preferência)

---

## 🔗 Exemplo de Requisição à API

```http
GET https://v6.exchangerate-api.com/v6/SUA_API_KEY/pair/BRL/USD/100
