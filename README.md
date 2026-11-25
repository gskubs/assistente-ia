# Assistente IA — API REST com Java e Spring AI

API REST desenvolvida em Java 21 e Spring Boot integrada ao modelo **Google Gemini** por meio do ecossistema **Spring AI**. O projeto demonstra o consumo de modelos de linguagem generativa, engenharia de prompt via instruções de sistema (`system prompt`) e mapeamento de respostas estruturadas em JSON utilizando Java `Records`.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** (Spring Web)
- **Spring AI** (Google GenAI Starter)
- **Maven**

---

## Arquitetura do Projeto

O código foi estruturado separando responsabilidades entre a camada de exposição web, a regra de integração com a IA e o contrato de dados:

- **`AssistenteController`**: Camada REST responsável por expor os endpoints HTTP e receber os parâmetros das requisições.
- **`TriagemService`**: Camada de serviço (`@Service`) que encapsula a lógica de chamada ao `ChatClient`, aplica as instruções de sistema e converte o retorno da IA.
- **`TriagemChamado`**: Java `Record` utilizado para estruturar a saída da IA em um contrato tipado e serializado automaticamente em JSON.

---

## Endpoints Disponíveis

### 1. Pergunta Geral
Envia uma pergunta aberta para o modelo e retorna a resposta em texto simples.

- **Requisição:** `GET /perguntar?mensagem={texto}`
- **Exemplo:**
  ```http
  GET http://localhost:8080/perguntar?mensagem=Explique em duas frases o que faz uma API REST
  ```

### 2. Tradução para Português
Utiliza uma instrução de sistema fixa para atuar como tradutor e devolver apenas o texto traduzido.

- **Requisição:** `GET /traduzir?mensagem={texto}`
- **Exemplo:**
  ```http
  GET http://localhost:8080/traduzir?mensagem=When this began I had nothing to say
  ```

### 3. Triagem Estruturada de Chamados (JSON)
Analisa a descrição de um problema técnico e devolve um objeto estruturado contendo o departamento responsável, o nível de urgência e a ação recomendada.

- **Requisição:** `GET /triagem?problema={descricao}`
- **Exemplo:**
  ```http
  GET http://localhost:8080/triagem?problema=O servidor de arquivos não responde desde as 8h
  ```
- **Resposta esperada (`200 OK`):**
  ```json
  {
    "departamento": "Infraestrutura",
    "urgente": true,
    "acaoRecomendada": "Verificar conectividade de rede e status do servidor"
  }
  ```

---

## Como Executar Localmente

### Pré-requisitos
- JDK 21 instalado
- Chave de API gratuita gerada no [Google AI Studio](https://aistudio.google.com)

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/gskubs/assistente-ia.git](https://github.com/gskubs/assistente-ia.git)
   cd assistente-ia
   ```

2. **Configure a chave da API como variável de ambiente:**
   - **Windows (PowerShell):**
     ```powershell
     $env:GEMINI_API_KEY="SUA_CHAVE_AQUI"
     ```
   - **Linux / macOS:**
     ```bash
     export GEMINI_API_KEY="SUA_CHAVE_AQUI"
     ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   A API ficará disponível em `http://localhost:8080`.
