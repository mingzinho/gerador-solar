# Projeto de Automação de Geradores Solares

## Visão Geral

Este projeto automatiza a configuração de geradores de energia solar com base nos produtos disponíveis em estoque (conforme Anexo A) e nas regras de configuração definidas (Anexo B). O sistema gera, semanalmente, dois relatórios: um CSV detalhando a composição de cada gerador (modelo do Anexo C) e um PDF com um resumo informativo da configuração semanal.

- AO RODAR O CÓDIGO LOCALMENTE UTILIZE A PORTA 9090.
```bash
   http://localhost:9090/
```
## Arquitetura e Design

- **Estrutura Modular:**
    - **Modelos:** Entidades de domínio, como Produto e Gerador, que representam os dados do estoque e a composição dos geradores.
    - **Serviço:** O `GeneratorService` recupera os dados via HTTP, categoriza os produtos (Painel, Inversor, Controlador) e gera combinações válidas aplicando as regras de negócio (ex.: inversor e controlador devem ter a mesma potência e a potência do inversor deve ser múltipla da do painel).
    - **Utilitários:** Classes auxiliares responsáveis pela geração dos arquivos CSV e PDF.
    - **Agendamento:** Um job configurado com Spring Scheduling executa o processo automaticamente toda segunda-feira às 8h.

- **Tecnologias Utilizadas:**
    - Spring Boot (para desenvolvimento, agendamento e REST).
    - RestTemplate para consumo de dados via HTTP.
    - Apache PDFBox para geração de relatórios em PDF.
    - Maven para gerenciamento de dependências e build.
    - Docker para funcionalidade em diferentes sistemas.

## Principais Destaques da Implementação

- **Configuração Dinâmica:**  
  Os produtos são obtidos em tempo real de um endpoint remoto. O serviço filtra e combina os produtos para formar geradores válidos, onde a quantidade de painéis é calculada dinamicamente (n = potência do inversor / potência do painel) e a potência total do gerador é definida como a soma dos componentes.

- **Geração de Relatórios:**
    - **CSV:** Relatório detalhado com informações de cada gerador, incluindo ID, potência e composição (produto, quantidade, etc.).
    - **PDF:** Relatório resumido, simulando o conteúdo de um e-mail, informando o número de geradores configurados na semana.

- **Agendamento:**  
  A execução do processo é automatizada via um job agendado que roda semanalmente, garantindo a atualização dos relatórios sem intervenção manual.

## Testes e Implantação

- **Testes Manuais:**  
  Endpoints REST (por exemplo, `/generate/csv` e `/generate/pdf`) permitem a geração sob demanda dos relatórios para verificação.

- **Deploy:**  
  O projeto é empacotado como uma aplicação Spring Boot autônoma e pode ser implantado em ambientes compatíveis com Java.

## Geração dos arquivos

- Ao rodar o código diretamente no Java os arquivos CSV e PDF são salvos no diretorio do projeto.
- Ao rodar pelo Docker, os arquivos são salvos dentro do cointainer. Utilize o codigo:
   ```bash
   docker build -t gerador-solar .
   docker run -p 9090:8080 gerador-solar
   ```

## Considerações para Manutenção

- **Extensibilidade:**  
  A arquitetura modular facilita a atualização das regras de negócio e a integração de novas funcionalidades, como o envio automatizado de e-mails, sem impactar o núcleo da aplicação.

- **Adaptabilidade:**  
  A lógica dinâmica de obtenção e processamento dos dados assegura que o sistema se adapte a alterações no estoque, mantendo a robustez do processo de configuração.

- **Monitoramento e Logging:**  
  A aplicação utiliza logging padrão para informar a execução do job e a geração dos relatórios, facilitando a identificação de problemas e a manutenção contínua.


---

Este é um projeto feito para a Neosolar, desenvolvido e documentado por Ming Nut Tan. 
