# projeto-coin-mestre


# Instruções para rodar o projeto 

# Front

Usado React JS usando como base React Admin https://marmelab.com/react-admin/ 
Usei ele pois é uma referência para backoffice. 
Para rodar é preciso do Node v12.22.12.
Utilizado o Visual studio Code.

## Comandos para rodar o projeto na parte do front
npm install para instalação dos pacotes.
npm start pra rodar o servidor.

# Back

Usado a linguagem Java, os framework que foi utilizado foi o spring boot versão 2.7.14
versão de java utilizado foi a 17.
Utilizado o Intellij.

## Comandos para rodar o projeto na parte back
Só rodar na IDE.


# Banco de Dados

Utilizado o Mysql, tem que ter uma estância para rodar o mesmo, as tabelas serão criadas automaticamente assim que rodar o back, pois existe uma configuração de ddl-auto como update.
Para rodar o abco você precisa das seguintes variáveis de ambiente:
 1. DATABASE_HOST
 2. DATABASE_PORT
 3. DATABASE_NAME
 4. DATABASE_USERNAME
 5. DATABASE_PASSWORD
![image](https://github.com/karolineconceicaodasilva/projeto-coin-mestre/assets/112582500/2407f0b5-aa26-489b-8c3a-229ee87ec608)


### Mais informações

Ao rodar o projeto, vai ser inicializado uma aplicação para verificação de usuários.
Será inicializado um metódo chamado de onApplicationEvent, ele irá verificar se existe no banco algum usuário ativo, não havendo ele vai criar um admin com as seguintes credenciais.
Usuário: admin@gmail.com 
Senha: 1234
Entrando no sistema, você pode criar um usuário de acordo com suas preferências, esse usuário foi criado para poder ter mais usuários utilizando o sistema.

### Visualização das credencias que o admin
O admin tem o poder de criar mais usuários para a utilização do sistema, e só ele pode deletar e editar as credenciais do usuário.
![image](https://github.com/karolineconceicaodasilva/projeto-coin-mestre/assets/112582500/c64e704f-1f09-43a8-a655-07369de720fd)


 
