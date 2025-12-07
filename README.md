# 🧙‍♂️ Harry Potter API App

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple?logo=kotlin)
![Android](https://img.shields.io/badge/Android-API%2028+-green?logo=android)
![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-blue)
![Glide](https://img.shields.io/badge/Glide-4.16.0-orange)

Aplicativo Android desenvolvido para consumir a [HP-API](https://hp-api.onrender.com/) utilizando corrotinas e web services, demonstrando conhecimentos em desenvolvimento mobile moderno.

## 👥 Equipe

- **Celso**
- **Isabelle**
- **Rodrigo**

## 📱 Sobre o Projeto

Este projeto foi desenvolvido como trabalho da disciplina **Desenvolvimento Mobile II** com o objetivo de demonstrar o uso de:
- Corrotinas Kotlin para operações assíncronas
- Consumo de APIs REST com Retrofit
- Arquitetura Android moderna
- Material Design e boas práticas de UI/UX

O aplicativo consome a Harry Potter API para exibir informações sobre personagens, professores e estudantes do universo de Harry Potter.

## ✨ Funcionalidades

### 🏠 Dashboard Principal
Tela inicial com navegação para todas as funcionalidades do app:
- Buscar personagem por ID
- Listar professores de Hogwarts
- Listar estudantes por casa
- Sair do aplicativo

### 🔍 Buscar Personagem por ID
- Permite buscar um personagem específico informando seu ID
- Exibe informações detalhadas:
  - Nome
  - Casa (Gryffindor, Slytherin, etc)
  - Espécie
  - Data de nascimento
  - Ator/Atriz
  - Imagem do personagem
- Feedback visual com ProgressBar durante o carregamento
- Tratamento de erros com mensagens amigáveis

### 👨‍🏫 Listar Professores
- Exibe lista completa dos professores de Hogwarts
- Carregamento assíncrono com corrotinas
- Interface limpa e organizada

### 🏰 Listar Estudantes por Casa
- Seleção de casa através de RadioButtons:
  - Gryffindor
  - Slytherin
  - Hufflepuff
  - Ravenclaw
- Validação de seleção antes da busca
- Exibição da lista de estudantes da casa escolhida

## 🛠️ Tecnologias Utilizadas

### Linguagem e Framework
- **Kotlin** - Linguagem principal
- **Android SDK** - API Level 28 (Android 9.0 Pie) ou superior

### Bibliotecas Principais
- **Retrofit 2.9.0** - Cliente HTTP para consumo de APIs REST
- **Gson Converter** - Serialização/deserialização JSON
- **Kotlin Coroutines** - Programação assíncrona
- **Glide 4.16.0** - Carregamento e cache de imagens
- **Glide OkHttp3 Integration** - Integração customizada para HTTPS
- **Material Design Components** - Componentes de UI modernos
- **CardView** - Layouts com elevação e cantos arredondados
- **ConstraintLayout** - Layouts responsivos e flexíveis

### Arquitetura
- **MVVM Pattern** - Separação de responsabilidades
- **Lifecycle-aware Components** - lifecycleScope para corrotinas
- **Retrofit + OkHttp** - Cliente HTTP customizado com SSL bypass
- **Custom Glide Module** - Configuração personalizada para carregamento de imagens

## 🚀 Setup e Instalação

### Pré-requisitos
- **Android Studio** Hedgehog (2023.1.1) ou superior
- **JDK** 11 ou superior
- **Android SDK** com API Level 28 ou superior
- **Gradle** 8.0 ou superior (incluído no projeto)

### Passos para Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/rodrigojarr/trabalhoMOB2.git
cd trabalhoMOB2
```

2. **Abra o projeto no Android Studio**
   - File → Open → Selecione a pasta do projeto
   - Aguarde o Gradle sincronizar as dependências

3. **Configure um dispositivo**
   - **Emulador**: Crie um AVD com API 28+ no AVD Manager
   - **Dispositivo físico**: Ative o modo desenvolvedor e depuração USB

4. **Execute o aplicativo**
   - Clique em "Run" (▶️) ou pressione `Shift + F10`
   - Selecione o dispositivo de destino

### Troubleshooting

#### Erro de SSL/HTTPS
O projeto já inclui configurações para bypass de SSL (necessário para a HP-API):
- `network_security_config.xml` configurado
- `ApiClient` com `unsafeOkHttpClient`
- `MyGlideModule` usando o mesmo cliente HTTP

#### Erro de Build
```bash
# Limpe e reconstrua o projeto
./gradlew clean
./gradlew build
```

#### Dependências não resolvidas
```bash
# Force a atualização das dependências
./gradlew --refresh-dependencies
```

## 🌐 API Utilizada

### HP-API (Harry Potter API)
- **URL Base**: https://hp-api.onrender.com/api/
- **Documentação**: https://hp-api.onrender.com/

### Endpoints Consumidos

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/character/{id}` | GET | Busca personagem específico por ID |
| `/characters/staff` | GET | Lista todos os professores de Hogwarts |
| `/characters/house/{house}` | GET | Lista estudantes de uma casa específica |

### Exemplo de Resposta
```json
[
  {
    "id": "1",
    "name": "Harry Potter",
    "house": "Gryffindor",
    "dateOfBirth": "31-07-1980",
    "actor": "Daniel Radcliffe",
    "image": "https://ik.imagekit.io/hpapi/harry.jpg",
    "species": "human"
  }
]
```

## 📂 Estrutura do Projeto

```
app/src/main/
├── java/br/ufpr/trabmob2/
│   ├── MainActivity.kt                 # Dashboard principal
│   ├── FindCharacterActivity.kt        # Busca por ID
│   ├── TeachersActivity.kt             # Lista de professores
│   ├── StudentsActivity.kt             # Seleção de casa
│   ├── DetailsStudentsActivity.kt      # Lista de estudantes
│   ├── ApiClient.kt                    # Configuração Retrofit
│   ├── CharacterFactApi.kt             # Interface da API
│   ├── CharacterFact.kt                # Modelo de dados
│   └── MyGlideModule.kt                # Configuração Glide
├── res/
│   ├── layout/                         # Layouts XML
│   ├── values/                         # Cores, strings, temas
│   ├── drawable/                       # Recursos gráficos
│   └── xml/                            # Configurações
└── AndroidManifest.xml                 # Manifesto do app
```

### Activities Principais

- **MainActivity**: Dashboard com navegação para todas as funcionalidades
- **FindCharacterActivity**: Busca e exibe detalhes de um personagem
- **TeachersActivity**: Lista todos os professores
- **StudentsActivity**: Seleção de casa com RadioButtons
- **DetailsStudentsActivity**: Exibe estudantes da casa selecionada

## ✅ Requisitos Atendidos

### Requisitos Obrigatórios
- ✅ Activity Principal (Dashboard) com navegação
- ✅ Buscar personagem específico por ID
- ✅ Listar professores da escola
- ✅ Listar estudantes de uma casa (com RadioButtons)
- ✅ Botão sair (fecha o aplicativo)
- ✅ Uso de corrotinas (lifecycleScope + suspend functions)
- ✅ Consumo de web services (Retrofit + Gson)
- ✅ API 28 do Android (minSdk = 28)
- ✅ Validações de entrada
- ✅ Tratamento de erros

### Funcionalidades Extras Implementadas
- ✨ UI temática Harry Potter com Material Design
- ✨ Carregamento de imagens dos personagens
- ✨ ProgressBars para feedback visual
- ✨ CardViews com elevação e design moderno
- ✨ Informações adicionais dos personagens (espécie, data nascimento, ator)
- ✨ Network security config para HTTPS
- ✨ Edge-to-edge UI
- ✨ Tratamento robusto de erros com logs

## 📝 Notas de Desenvolvimento

### Decisões Técnicas

1. **Custom OkHttpClient**: Implementado para bypass de SSL devido às características da HP-API
2. **Custom Glide Module**: Necessário para que o Glide use o mesmo cliente HTTP do Retrofit
3. **Coroutines com lifecycleScope**: Garante cancelamento automático ao destruir a Activity
4. **Material Design 3**: Uso de componentes modernos e tema customizado
5. **Validações**: Implementadas em todas as entradas do usuário

### Boas Práticas Aplicadas

- Separação de responsabilidades (API client, models, views)
- Uso de suspend functions para operações de rede
- Tratamento de exceções com try-catch
- Feedback visual para o usuário (ProgressBar, Toast)
- Logs para debugging (Log.e)
- Recursos externalizados (strings, colors, themes)
- Layouts responsivos com ConstraintLayout

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da disciplina de Desenvolvimento Mobile II.

---

**Desenvolvido com ⚡ por Celso, Isabelle e Rodrigo**
