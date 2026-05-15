# Conversor de Moedas 💱

Aplicativo Android de conversor de moedas com design escuro, desenvolvido em Kotlin.

## 📋 Requisitos
- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 17
- Android SDK 34

## 🚀 Como rodar

### Opção 1 — Pelo Android Studio (recomendado)
1. Abra o Android Studio
2. Clique em **File → Open**
3. Selecione a pasta `conversor/`
4. Aguarde o Gradle sincronizar (pode levar alguns minutos na primeira vez)
5. Clique em **Run ▶** ou pressione `Shift + F10`

> ⚠️ Se aparecer erro sobre `gradle-wrapper.jar`, vá em:
> **Help → Find Action → "Gradle Wrapper"** e clique em "Regenerate Gradle Wrapper"
> Ou execute no terminal: `gradle wrapper --gradle-version 8.2`

### Opção 2 — Pelo terminal (se tiver Gradle instalado globalmente)
```bash
cd conversor
gradle wrapper --gradle-version 8.2
./gradlew assembleDebug
```

## 🌐 API utilizada
- **open.er-api.com** — Gratuita, sem necessidade de chave de API
- Atualização diária das taxas de câmbio
- 20 moedas suportadas

## 💱 Moedas suportadas
BRL, USD, EUR, GBP, JPY, ARS, CLP, CAD, AUD, CHF, CNY, MXN, COP, PEN, UYU, INR, KRW, ZAR, SEK, NOK

## 🏗️ Estrutura do projeto
```
app/src/main/
├── java/com/seupacote/conversor/
│   ├── MainActivity.kt          ← Tela principal
│   ├── api/
│   │   ├── ExchangeApiService.kt ← Interface Retrofit
│   │   └── RetrofitClient.kt     ← Cliente HTTP
│   ├── model/
│   │   └── ExchangeResponse.kt   ← Modelo de dados
│   └── viewmodel/
│       └── ConversorViewModel.kt ← Lógica de negócio
└── res/
    ├── layout/activity_main.xml  ← Layout da tela
    ├── drawable/                 ← Botões e fundos
    └── values/                   ← Cores, strings, tema
```

## 🎨 Design
- Tema escuro (`#0D0D1A` fundo, `#1A1A2E` cards)
- Accent laranja (`#FF6B35`)
- Arquitetura MVVM com LiveData e Coroutines
