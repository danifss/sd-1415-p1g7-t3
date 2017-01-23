### Sistemas Distribuídos (Distributed Systems)
> Ano lectivo de 2014 / 2015 - Universidade de Aveiro

# Artesanato de Aveiro 
### (Java RMI)

Construa uma simulação das actividades na empresa de artesanato baseada no modelo cliente-servidor, com replicação de servidor, em que a dona da empresa, os artesãos e os clientes são os clientes e as regiões de interacção que tenha estabelecido representam os serviços que lhes são prestados pelos servidores.
A solução deve ser implementada em Java, ser passível de execução em Linux sobre Java RMI, poder ser executada de uma forma concentrada (numa única máquina), ou de uma forma distribuída (até oito máquinas diferentes), e terminar (deve contemplar a possibilidade de shutdown do serviço). Deve ser ainda instalado um mecanismo de relógios lógicos vectoriais entre os clientes que possibilite a ordenação causal de operações sobre o Repositório Geral (descrição detalhada do estado interno do problema).
As operações que correspondiam, na versão concorrente já implementada, a actividades realizadas sobre a(s) estrutura(s) de dados partilhada(s), devem agora corresponder a operações efectuadas sobre objectos remotos.
