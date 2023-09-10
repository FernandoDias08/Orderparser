# Orderparser
Sistema para conversão de arquivo txt em formato json

## Autores

- [@fernandoDias08](https://github.com/FernandoDias08)

## Features
- JAVA 17
- JUnit5
- Gradle


## Padrão de Commits
- Por padrão, o Orderparser adota a convenção de commits descrita no link:
 https://www.conventionalcommits.org/en/v1.0.0/

## EXECUTANDO O SISTEMA LOCAL - OPÇÃO 1
- Para executar o projeto localmente, será necessário ter instalado o JAVA versão 17 ou superior e o Gradle (para realizar o build)
- Com o JAVA e o Gradle já instalados, basta entrar na pasta /lib do projeto e executar o comando:
  ```gradle clean shadowJar``` para que seja gerado o fatjar da aplicação.
- Após realizar o build do projeto, entrar na pasta "lib/build/libs" e executar o comando ```java -jar lib-all.jar```.
- Com isso, o sistema irá realizar a leitura dos arquivos, converter para json e printar no console o resultado, além de criar o arquivo "pedidos.json".
  
## EXECUTANDO O SISTEMA LOCAL - OPÇÃO 2
- É possível executar o sistema localmente através do Docker. Basta entrar na pasta raiz do projeto e executar o comando ```docker build -t fernandodias08/orderparser:lastest .``` . Após criação da imagem, executar o comando ```docker run -p 8080:8080 fernandodias08/orderparser:lastest```
- Em ambas as opções, o sistema parmenece em execução, rodando o processo de hora em hora.

## CI - CD
- Para os processos de CI - CD foi utilizado o Github Actions, criando a pipeline com 3 stages: test -> build -> deploy.
- O estágio de build depende da execução bem sucedida do stage de test. E para execução do estágio de deploy, o estágio de build precisa ser bem sucedido.
- O estágio de test irá executar todos os testes unitários do sistema.

## Infraestrutura
- Ao executar o stage de build, uma imagem Docker com o sistema é gerada e armazenada no meu repositório Dockerhub.
- Em seguida, ao executar o stage de Deploy, o sistema irá buscar esta imagem no Dockerhub e executá-la em um cluster Kubernetes hospedado na Digital Ocean, onde permanecerá em execução. 
