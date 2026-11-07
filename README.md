# llanos-post1-u4
Post-contenido — Patrones de Comportamiento aplicados al backend de Compras


Cómo abordar cada necesidad
Para cada una de las cuatro necesidades de este laboratorio, antes de escribir código se recomienda responder tres preguntas por escrito (borrador que luego se traslada al README): (1) ¿cuál es el síntoma de diseño exacto que describe el enunciado?, (2) ¿qué patrón(es) de los vistos en la guía de la unidad podrían encajar y por qué?, (3) ¿cuál de ellos se descarta y con qué argumento técnico, no solo intuitivo? Dos necesidades comparten el mismo dominio en cada parte precisamente para que ese análisis sea necesario: no basta con "reconocer" un patrón por su forma, hay que distinguirlo de uno cercano que aparece al lado.


Punto de decisión 1 

La respuesta correcta es el patrón Chain of Responsibility (Cadena de Responsabilidad), porque el problema descrito consiste en hacer que una solicitud recorra una secuencia de decisores independientes, donde cada uno puede resolverla o delegarla al siguiente, sin que el código que inicia la solicitud conozca toda la cadena.
1. Síntoma de diseño
El síntoma exacto es que una solicitud debe pasar por diferentes responsables o decisores, cada uno con la posibilidad de atenderla o transferirla al siguiente. El problema aparece cuando la lógica de evaluación depende de una secuencia fija de condiciones o responsables, haciendo difícil agregar, eliminar o cambiar el orden de los participantes sin modificar el código principal. Se necesita una estructura flexible que permita ampliar o reorganizar la cadena de decisión.
2. Patrón que encaja: Chain of Responsibility
El patrón Chain of Responsibility es el más adecuado porque permite organizar varios objetos independientes en una cadena de atención. Cada objeto recibe la solicitud, evalúa si puede resolverla y, si no le corresponde, la pasa al siguiente responsable. De esta manera, el emisor de la solicitud no necesita conocer quién la atenderá finalmente. Además, permite agregar nuevos decisores o modificar el orden de la cadena sin alterar el código que inicia el proceso.
3. Patrón que se descarta: Command
Se descarta el patrón Command porque su propósito es encapsular una operación o solicitud dentro de un objeto independiente, permitiendo ejecutarla, registrarla en un historial, almacenarla o incluso deshacerla cuando sea necesario. En este escenario, el problema no consiste en representar decisiones como acciones reversibles, sino en establecer una secuencia de responsables que puedan atender o delegar una solicitud. Por tanto, Command no representa naturalmente el recorrido de una petición entre varios decisores, mientras que Chain of Responsibility sí permite modelar ese comportamiento.

Decisión de diseño: Se selecciona Chain of Responsibility porque permite que una solicitud avance por una cadena de decisores independientes hasta encontrar quién pueda resolverla. No se utiliza Command porque el objetivo no es encapsular operaciones reversibles ni mantener un historial de acciones, sino distribuir la responsabilidad de atender una solicitud de manera flexible.

Punto de Desición 2

La respuesta correcta es el patrón Command (Comando), porque el problema consiste en encapsular operaciones como objetos independientes que puedan ejecutarse, registrarse en un historial y deshacerse cuando sea necesario.
1. Síntoma de diseño
El síntoma exacto es que el equipo de Compras necesita ejecutar diferentes operaciones de manera independiente, conservar un registro ordenado de las acciones realizadas y tener la posibilidad de deshacerlas cuando sea necesario. El problema no consiste en distribuir solicitudes entre diferentes responsables, sino en administrar acciones que deben poder ejecutarse, consultarse y revertirse sin depender directamente de su implementación.
2. Patrón que encaja: Command
El patrón Command es el más adecuado porque permite encapsular cada operación en un objeto independiente que representa una acción. De esta manera, el equipo de Compras puede ejecutar diferentes comandos, almacenarlos en un historial y consultar el orden en que fueron realizados. Además, cada comando puede implementar la funcionalidad necesaria para deshacer su propia operación, facilitando la reversión de acciones sin modificar el código que las solicita.
3. Patrón que se descarta: Chain of Responsibility
Se descarta el patrón Chain of Responsibility porque su propósito es establecer una cadena de responsables donde cada uno evalúa una solicitud y decide si la resuelve o la delega al siguiente. En este escenario no existen diferentes decisores que deban evaluar condiciones para atender una petición, sino un mismo actor (el equipo de Compras) que decide ejecutar operaciones específicas, conservarlas en un historial y eventualmente deshacerlas. Por tanto, Chain of Responsibility no proporciona naturalmente mecanismos para registrar y revertir acciones, mientras que Command sí permite encapsularlas y administrarlas individualmente.

Decisión de diseño: Se selecciona Command porque permite representar cada operación como un objeto independiente, facilitando su ejecución, almacenamiento en un historial y reversión. No se utiliza Chain of Responsibility porque no se necesita una secuencia de decisores que reciban y deleguen solicitudes, sino administrar acciones ejecutadas por un mismo actor.

Comparación con la Necesidad 1: Aunque ambas necesidades trabajan con solicitudes y objetos independientes, sus propósitos son diferentes. La Necesidad 1 utiliza Chain of Responsibility porque una petición avanza entre varios decisores hasta que alguno la resuelve. En cambio, la Necesidad 2 utiliza Command porque las operaciones deben ejecutarse, registrarse y eventualmente deshacerse. Intercambiar los patrones no resolvería los problemas planteados, ya que Chain of Responsibility no administra naturalmente historiales ni reversiones, y Command no modela la delegación de una solicitud entre varios responsables.


