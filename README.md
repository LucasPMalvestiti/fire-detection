# fire-detection
Forest Fire Detection System usign threads in Java
Programação Paralela e Distribuída
Eng. De Software – 6º Semestre
Projeto Fire-Detection


Este projeto tem como objetivo simular um sistema de detecção de incêndio em uma floresta, utilizando sensores espalhados por uma matriz representando o ambiente. O sensores detectam focos de incêndio gerados, propaga alertas entre os sensores e apaga os incêndios quando detectados pelo centro de controle.

Estrutura do Programa
O programa é estruturado em diferentes classes, onde cada uma tem sua função específica:
1.	Classe Main.java
Ponto de entrada do programa, define a estrutura principal:
a.	Inicializa os principais objetos (floresta, centro de controle e sensores);
b.	Inicializa os sensores que monitoram a floresta continuamente;
c.	Inicia o gerador de incêndio em uma thread separada, que simula uma queimada de tempos em tempos;
d.	Periodicamente exibe o estado da floresta no console.
2.	Classe Forest.java
Esta classe contém a representação da floresta que é responsável por:
a.	Cada célula da matriz pode conter um valor que representa diferentes estados (árvores, incêndios, sensores,  etc...);
b.	Os sensores na floresta monitoram seus arredores para detectar incêndios.
3.	Classe SensorNode.java
A classe SensorNode representa um sensor, que é responsável por:
a.	Monitorar a floresta nos seus arredores;
b.	Detectar incêndios (representados por um símbolo ‘@’ na matriz);
c.	Propagar alertas de incêndio para seus sensores vizinhos;
d.	Receber e processar alertas de incêndio de outros sensores.
Cada sensor é executado em uma thread separada, permitindo que eles operem de forma independente e simultânea. Além disso, cada sensor armazena um estado booleano (hasReceivedFireAlert) que garante que ele não receba ou propague o mesmo alerta repetidamente.




4.	Classe FireGenerator.java
A classe FireGenerator é responsável por gerar incêndios de forma aleatória na floresta:
a.	Utiliza a classe Random para escolher coordenadas na matriz e marcar um incêndio no local escolhido
b.	É executada em uma thread separada, gerando incêndios periodicamente enquanto o programa está rodando
5.	Classe ControlCenter.java
A ControlCenter é responsável por apagar os incêndios:
a.	Recebe alertas dos sensores quando um incêndio é detectado;
b.	Apaga o incêndio na posição indicada e redefine o estado dos sensores que foram impactados, permitindo que eles voltem a monitorar a floresta para futuros incêndios.

Uso de Threads
O uso de threads é um aspecto fundamental deste projeto, garantindo que múltiplos componentes do sistema possam operar em parelho.
•	Cada sensor opera em sua própria thread, possibilitando o monitoramento contínuo de diferentes áreas da floresta ao mesmo tempo;
•	A geração de incêndios ocorre na classe FireGenerator, que também é executada paralelamente, gerando incêndios aleatórios de tempos em tempos, independentemente da execução dos sensores e do centro de controle;
•	O centro de controle pode responder aos incêndios enquanto os sensores continuam monitorando e propagando mensagens. O uso de threads permite uma resposta em tempo real, sem bloquear ou atrasar outras operações do sistema;
•	As threads também garantem que operações sensíveis sejam feitas de forma segura. Isso ocorre por meio de blocos synchronized, que evitam condições de corrida, garantindo que apenas uma thread de cada vez possa acessar e modificar o estado dos sensores ou da floresta.

Lógica
O primeira etapa do programa é a inicialização dos objetos e threads. Após esta etapa se inicia a geração de incêndios, e então, com os sensores já inicializados e monitorando suas respectivas áreas, incêndios são detectados e processados pelos SensorNodes, que decidem se devem propagar um alerta de incêndio para seus vizinhos (caso sejam nodes centrais) ou se devem contactar a ControlCenter (caso recebam uma mensagem, ou detectem fogo nas áreas próximas a borda da floresta). E por fim, a Central de Controle recebe os alertas vindos dos sensores, apaga o fogo, e reseta o estado dos sensores para continuarem detectando incêndios.

Conclusão
O sistema de detecção de incêndio utiliza de maneira eficiente o paralelismo oferecido pelas threads para simular um ambiente de monitoramento contínuo e resposta a incêndios. Sensores independentes operam simultaneamente, propagando alertas e acionando o centro de controle, que por sua vez apaga os incêndios e redefine o sistema para detectar novos eventos.

Este projeto demonstra como o uso de threads pode ser aplicado para resolver problemas complexos de monitoramento e comunicação distribuída, permitindo que diferentes partes do sistema operem de forma assíncrona e responsiva.
