# 🚀 Mooby 
O aplicativo foi desenvolvido com foco na educação financeira e no controle de gastos pessoais,   
tendo como objetivo auxiliar jovens e adultos a compreender e aprimorar sua relação com o dinheiro.

Integrantes do Projeto:
- Luiz André Hoffmmann Leineker
- Marcus Vinicius Morais de Sousa
- Matheus Belniak Mendes
- Ricardo Polato Bernaski   
  
Para rodar este projeto em seu computador, siga as etapas descritas abaixo.   

## 📦 Download do Projeto
#### .ZIP
#### Na página principal do repositório, clique em "Code" → "Download ZIP".
#### Extraia o arquivo `.zip` em uma pasta de sua preferência.
#### CLI - Terminal
#### Para clonar o repositório via CLI, instale o [GIT](https://git-scm.com/). 
#### Execute o terminal em uma pasta de sua preferência:
```bash
git clone https://github.com/LuizLeineker/ProjetoMobile-Mooby.git
```

## 🛠️ Ferramentas 
Para visualização e execução do projeto instale em sua máquina a seguinte ferramenta: 
- [Android Studio](https://developer.android.com/studio?hl=pt-br)

## ▶ RUN
Entre no projeto pelo android studio, espere o Gradle carregar...   
Rode o emulador (device manager), em seguida execute o projeto. 

## ❯❯❯❯ Fluxo de Navegação
Tela de Splash → Login/Cadastro → Tela Principal      
Tela Principal → Transações → Detalhes.         
Tela Principal → Metas        
Tela Principal → Dicas       
Tela Principal → Gráficos → Detalhes.          

## 📝 Tabelas
### User
Id - Int(PK)   
name - String    
email - String    
valueInitial - Double   
### Transaction
Id - Int(PK)   
userId - Int(FK)
tipo - String   
category - String     
value - Double   
data - String    
description - String     
### META
Id - Int(PK)   
userId - Int(FK)   
valueMeta - Double   
progess - Double   
term - Double     

## 🤖 Arquitetura 
- Model
  - DAO                   (acesso ao banco local)  
  - DTO                   (modelo para trafegar dados)
  - Entity                (estrutura usada no Firebase)
  - Firebase              (fonte de dados remota)
  - Mapper                (Converter valores: DTO - Entity)
  - Repository            (coordena Firebase + DAO)
- Navigation    
- ui.theme
  - Screens
  - ViewModel     
