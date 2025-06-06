# Projeto Montagem de Computadores

Este projeto é uma aplicação Java que permite que um **cliente monte seu próprio computador** escolhendo peças compatíveis, enquanto a **loja pode cadastrar novos componentes**.


## Diagrama UML do projeto:

![Diagrama UML do projeto](assets/uml.png)

## 💡 Visão Geral

A aplicação é dividida logicamente em duas áreas:

- **Cliente**: pode montar um computador com base nas peças disponíveis.
- **Loja**: pode cadastrar, editar e gerenciar componentes.

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   ├── componentes/
│   │   │   ├── Componente.java
│   │   │   ├── PlacaMae.java
│   │   │   ├── Processador.java
│   │   │   ├── PlacaVideo.java
│   │   │   ├── MemoriaRAM.java
│   │   │   ├── Fonte.java
│   │   │   ├── Cooler.java
│   │   │   └── Gabinete.java
│   │   ├── computador/
│   │   │   ├── Computador.java
│   │   │   └── ComputadorBuilder.java
│   │   ├── fabrica/
│   │   │   └── ComponenteFactory.java
│   │   ├── usuario/
│   │   │   ├── Usuario.java
│   │   └── App.java
└── test/
```

## 🛠️ Funcionalidades Implementadas

- Representação de componentes como classes que herdam de `Componente`.
- Compatibilidade entre peças (ex: socket do processador e placa-mãe).
- Builder Pattern para montagem de computadores.
- Factory Pattern para criação de componentes na interface da loja.
- Classe `Usuario` com tipoUsuario para diferenciar qual interface abrir.

## 🧱 Design Patterns Utilizados

- **Builder**: permite construir objetos `Computador` passo a passo.
- **Factory**: abstrai a criação de diferentes componentes.
- **Strategy** (futuramente recomendado): pode ser usado para validar compatibilidade entre componentes.
- **Observer** (futuramente recomendado): para notificar a interface de atualizações (ex: quando uma peça é adicionada ao PC).

## ▶️ Como Executar

1. Clone o projeto:

```bash
git clone https://github.com/josepaliares/MonteSeuPc-ProjetoMC322.git
cd MonteSeuPc-ProjetoMC322
```

2. Compile e execute com Gradle:

```bash
./gradlew build
./gradlew run
```