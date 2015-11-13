# simulador_fogo
Trabalho de programação concorrente

Universidade de Caxias do Sul
Centro de Ciências Exatas e da Tecnologia
Disciplina: Programação Concorrente - SIS0564A
Professor: André Luis Martinotto

Simulador de combate ao fogo e resgate de vítimas
O trabalho consiste em implementar um sistema de simulação de combate ao fogo e resgate de vítimas. O
ambiente será composto por indivíduos (threads) com diferentes objetivos:

Bombeiro: deslocam-se aleatoriamente procurando por vítimas ou por fogo. Caso perceba fogo em seu
campo de percepção, vai até ele e o apaga. Quando um bombeiro localiza uma vítima, ele trata de conduzi-
la imediatamente para uma ambulância.
Ambulância: simulam um grupo de paramédicos que podem prestar socorro às vitimas menos graves. Eles
ficam aguardando que os bombeiros se encarreguem de trazer as vítimas.
Refugiado: deslocam-se aleatoriamente pelo ambiente.
Vítimas: quando um refugiado aproxima-se do fogo ele transforma-se em uma vítima. A vítima precisa ser
resgatada e tratada o mais rápido possível (entre 50 e 100 unidades de tempo por exemplo), caso contrário
ela morre. Quando uma vítima é atendida, ela se volta a ser um refugiado.
Fogo: surge aleatoriamente no ambiente.
Os indivíduos compartilham um ambiente (como uma matriz, por exemplo) podendo se deslocar de
acordo com seus objetivos. Cada classe de indivíduos deve, no início da simulação, estar distribuída
espacialmente no ambiente.
A interface gráfica deve apresentar o ambiente e indivíduos (threads) posicionados em cada célula. A
cada passo da simulação a interface deve apresentar informações como: tempo transcorrido (em unidades de
tempo), o número de vítimas salvas até o momento, o número de vítimas fatais, bombeiros e ambulâncias, além
de outras informações julgadas necessárias.

Observações:
Linguagens permitidas: Java, C#, C/C++, Python
