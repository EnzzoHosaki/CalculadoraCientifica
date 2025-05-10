# Calculadora Científica para Android

## Descrição

Este é um projeto de uma calculadora científica desenvolvida para a plataforma Android utilizando Kotlin e XML. O objetivo é fornecer uma ferramenta com funcionalidades semelhantes às encontradas na calculadora científica padrão do Windows, evoluindo a partir de uma calculadora com operações básicas.

O projeto visa implementar uma interface intuitiva e funcional, permitindo ao usuário realizar desde cálculos simples até operações matemáticas e científicas mais complexas.

## Funcionalidades Implementadas

### Básicas:
* Operações aritméticas: Soma (`+`), Subtração (`-`), Multiplicação (`×`), Divisão (`÷`)
* Entrada de números decimais (`.`)
* Limpar Entrada Atual (`CE`): Limpa o número que está a ser digitado ou o último resultado.
* Limpar Tudo (`C`): Reinicia a calculadora para o estado inicial.
* Apagar Último Dígito (`⌫` - Backspace)
* Inverter Sinal (`±`)
* Cálculo sequencial com botão de Igual (`=`)

### Científicas:
* **Potenciação:**
    * Quadrado (`x²`)
    * X elevado a Y (`xʸ`)
    * 10 elevado a X (`10ˣ`)
* **Raízes:**
    * Raiz Quadrada (`√`)
* **Logaritmos:**
    * Logaritmo na base 10 (`log`)
* **Trigonométricas:**
    * Seno (`sin`), Cosseno (`cos`), Tangente (`tan`)
    * Suporte para modo de ângulo: Graus (`DEG`), Radianos (`RAD`), Gradianos (`GRAD`) - alternável pelo botão `DEG/RAD/GRAD`.
    * Suporte para funções Hiperbólicas (`HYP`) para `sin`, `cos`, `tan` - ativável pelo botão `HYP`.
* **Outras Funções:**
    * Fatorial (`n!`)
    * Constante Pi (`π`) - insere o valor de Pi.
    * Módulo / Resto da Divisão (`Mod`)

### Memória:
* `MC` (Memory Clear): Limpa o valor armazenado na memória.
* `MR` (Memory Recall): Recupera o valor da memória para o visor.
* `MS` (Memory Store): Armazena o valor atual do visor na memória.
* `M+` (Memory Add): Adiciona o valor do visor ao valor armazenado na memória.
* `M-` (Memory Subtract): Subtrai o valor do visor do valor armazenado na memória.

### Interface:
* Layout adaptado com botões dedicados para as funções básicas e científicas.
* Visor (TextView) para entrada de números e exibição de resultados.
* Feedback visual básico para modos (ex: `HYP`).

## Tecnologias Utilizadas
* **Linguagem:** Kotlin
* **Layout:** XML (com `TableLayout` para a grade de botões)
* **Plataforma:** Android
* **IDE:** Android Studio

## Funcionalidades Parciais ou Futuras (A Implementar / TODO)
* **Análise Completa de Expressões:**
    * Implementar a avaliação correta de expressões com múltiplos **parênteses `()`** e **ordem de precedência de operadores (PEMDAS/BODMAS)**. Atualmente, os parênteses são apenas anexados ao visor e as operações são, na sua maioria, sequenciais.
* **Funções Trigonométricas Inversas:**
    * Arcoseno (`asin` ou `sin⁻¹`)
    * Arcocosseno (`acos` ou `cos⁻¹`)
    * Arcotangente (`atan` ou `tan⁻¹`)
* **Logaritmo Natural (`ln`)**
* **Exponencial `eˣ`**
* **Constante de Euler (`e`)**
* **Função Recíproca (`1/x`)**
* **Funcionalidade Completa da Tecla `Shift`/`2nd` (`↑`):**
    * Atualmente, o botão existe no layout, mas a lógica para ativar funções secundárias nos outros botões (como as inversas trigonométricas, `ln`, `eˣ`) não está implementada.
* **Funcionalidade Completa da Tecla `Exp`:**
    * Atualmente, o botão apenas anexa 'E' ao visor. A lógica para permitir a entrada correta do expoente para notação científica precisa ser completada.
* **Modo `F-E`:**
    * Alternar a exibição de números entre notação decimal fixa e notação científica, conforme preferência do usuário.
* **Outras Raízes:**
    * Por exemplo, raiz y-ésima (`ʸ√x`).
* **Melhorias Gerais:**
    * Interface do usuário mais polida e personalizável.
    * Tratamento de erros mais detalhado e feedback visual para o usuário.
    * Histórico de cálculos (opcional).
    * Testes unitários e de interface.

## Como Compilar e Executar
1.  Clone este repositório (se estiver num sistema de controle de versão).
2.  Abra o projeto no Android Studio.
3.  Aguarde a sincronização do Gradle e o download das dependências.
4.  Conecte um dispositivo Android ou inicie um Emulador Android.
5.  Clique em "Run" (Executar) no Android Studio.
