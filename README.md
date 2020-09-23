
# Batch Processing
Batch Processing é um sistema que importa lotes de arquivos, lê, analisa os dados e gera um arquivo de relatório utilizando spring batch.
O arquivo de importação padrão utiliza "**ç**" como separador, extensão **.dat** e existem três tipos distintos de dados esperados no arquivo de entrada. 

### Dados do Vendedor
A linha com os dados do vendedor inicia-se com **001** e possui o seguinte padrão ***001çCPFçNameçSalary***.

### Dados do Cliente
A linha com os dados do cliente inicia-se com **002** e possui o seguinte padrão ***002çCNPJçNameçBusinessArea***.

### Dados de Vendas
A linha com os dados de vendas inicia-se com **003** e possui o seguinte padrão ***003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name***. Onde existe uma lista com os itens envolvidos na venda dentro dos colchetes [].

Exemplo arquivo de entrada:
```sh
001ç1234567891234çJoaoç50000
001ç3245678865434çJoseç40000.99
001ç5245678865434çJonasç42000.99
002ç2345675434544345çPaulo da SilvaçRural
002ç2345675433444345çAna PereiraçRural
003ç04ç[1-14-100,2-30-2.50,3-40-3.10]çJoao
003ç05ç[1-32-10,2-33-1.50,3-40-0.10]çJose
003ç06ç[1-31-10,2-33-1.50,3-40-0.10]çJonas
003ç07ç[1-78-10000,2-33-1.50,3-40-0.10]çLuan
```
O sistema lê os dados do diretório homepath do usuário **data/in** e gera o relatório no diretório homepath do usuário **data/out** com a extensão **.done.dat** com as seguintes informações: 
* Quantidade de clientes nos arquivos de entrada
*  Quantidade de vendedores nos arquivos de entrada
*  ID da venda mais cara
*  O pior vendedor

Exemplo arquivo saída:
```sh
7;6;4;Luma
```
### Premissa
* Requerido: JDK 11 <br />
* Executar com Docker
	* Requerido: Docker
	* Requerido: Docker Compose
	
### Executar Testes
Linux
```sh
./mvnw test
```
Windows
```sh
mvnw.cmd test
```
### Construir e Executar Aplicação sem Docker
Linux
```sh
./mvnw clean package -DskipTests && cd target && java -jar ./batch-processing-1.0.0.jar
```
Windows
```sh
mvnw.cmd clean package -DskipTests && cd target && java -jar .\batch-processing-1.0.0.jar
```
### Construir e Executar Aplicação com Docker
#### Construir Imagem
Linux
```sh
./mvnw clean package -DskipTests && docker build -t batch-processing .
```
Windows
```sh
mvnw.cmd clean package -DskipTests && docker build -t batch-processing .
```
#### Configurar Volume
Como descrito anteriormente a aplição faz a leitura dos arquivos a partir do diretório padrão do usuário nas pastas data/in. Sendo assim, é necessário mapear o volume para que funcione corretamente com o docker, as variáveis HOMEDRIVE HOMEPATH devem ser substituídas no arquivo docker-compose.yml para adequar-se ao seu ambiente. Caso sua variável HOMEPATH já possua o drive por exemplo **C:**\Users\luan.abc então você pode remover a variável HOMEDRIVE.
```sh
version: "3.7"
services:
    batch-processing:
        image: batch-processing:latest
        volumes:
            - ${HOMEDRIVE}${HOMEPATH}/data/in://root/data/in
            - ${HOMEDRIVE}${HOMEPATH}/data/out://root/data/out
```
#### Executar
```sh
docker-compose up
```
