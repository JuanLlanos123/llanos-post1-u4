# llanos-post1-u4
Post-contenido — Patrones de Comportamiento aplicados al backend de Compras
# Post-contenido — Unidad 4: Patrones de Comportamiento en ComprasUDES

## Descripción
Repositorio del post-contenido de la Unidad 4 de Patrones de Diseño
de Software. Un único proyecto Spring Boot (compras-comportamiento)
que resuelve cuatro necesidades reales del backend de ComprasUDES,
el sistema interno de solicitudes de compra corporativas: aprobación
por niveles jerárquicos, ejecución reversible de solicitudes
aprobadas, notificaciones ante cambios de estado y reglas de
transición según el estado actual de la solicitud.

## Cómo ejecutar
```
$ mvn clean package
$ mvn spring-boot:run
$ mvn test
```



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

Necesidad 3. Notificación de cambios de estado – Patrón Observer
1. ¿Cuál es el síntoma de diseño exacto?

Cuando una solicitud cambia de estado, diferentes módulos externos necesitan conocer ese acontecimiento para ejecutar sus propias acciones. Por ejemplo, el módulo de correo debe enviar una notificación, contabilidad debe actualizar su información y auditoría debe registrar el cambio. El problema aparece cuando la solicitud tendría que conocer directamente todos esos módulos para informarles, generando dependencias y dificultando la incorporación de nuevos interesados.

2. ¿Qué patrones podrían encajar y por qué?

El patrón Observer es el más adecuado porque permite que diferentes objetos se suscriban a los cambios de un objeto observado. Cuando la solicitud cambia de estado, puede notificar automáticamente a sus observadores para que cada uno ejecute la acción correspondiente. Así, la solicitud no necesita conocer las implementaciones concretas de los módulos interesados.

También podría considerarse State, porque está relacionado con los cambios de estado de un objeto. Sin embargo, ambos patrones resuelven problemas diferentes: Observer comunica los cambios a otros objetos, mientras que State modifica el comportamiento del propio objeto según su estado.

3. ¿Qué patrón se descarta y con qué argumento técnico?

Se descarta State porque la necesidad no consiste en modificar las operaciones permitidas sobre la solicitud según su estado actual. La solicitud simplemente debe comunicar que ocurrió un cambio para que otros componentes reaccionen. State no proporciona por sí mismo un mecanismo de suscripción y notificación entre objetos independientes.


Necesidad 4. Comportamiento dependiente del estado – Patrón State
1. ¿Cuál es el síntoma de diseño exacto?

Las operaciones que se pueden realizar sobre una solicitud dependen de su estado actual. Por ejemplo, una solicitud pendiente puede permitir su aprobación o cancelación, mientras que una solicitud aprobada puede tener restricciones diferentes. Si estas condiciones se implementan mediante múltiples estructuras condicionales distribuidas por las clases, la lógica se vuelve difícil de mantener y ampliar.

2. ¿Qué patrones podrían encajar y por qué?

El patrón State es el más adecuado porque permite representar cada estado mediante un objeto independiente que define el comportamiento correspondiente. De esta forma, la solicitud delega las operaciones al objeto que representa su estado actual, evitando concentrar toda la lógica en condicionales y facilitando la incorporación de nuevos estados.

También podría considerarse Strategy, porque permite encapsular comportamientos diferentes e intercambiarlos dinámicamente. Sin embargo, State está orientado específicamente a representar los estados por los que atraviesa un objeto y las variaciones de comportamiento asociadas a ellos.

3. ¿Qué patrón se descarta y con qué argumento técnico?

Se descarta Observer porque, aunque también puede intervenir cuando ocurre un cambio de estado, su propósito es notificar a otros objetos interesados. No determina qué operaciones puede realizar la solicitud ni cómo debe comportarse según su estado. Por tanto, utilizar Observer como solución principal no resolvería el problema de las restricciones y comportamientos internos de la solicitud.

En la implementación, `Solicitud.ejecutar()` delega el comportamiento a su `EstadoSolicitud` actual. Los estados concretos definen la respuesta y la transición correspondiente; por ejemplo, `EstadoAprobada` cambia la solicitud a `EJECUTADA`. El getter y setter existentes conservan el estado como texto para mantener compatibles los demás módulos.






Reflexión breve – Otros tres patrones de diseño
1. Recorrido secuencial de solicitudes – Patrón Iterator

El patrón Iterator sería adecuado porque permite recorrer secuencialmente todas las solicitudes de un centro de costo sin exponer cómo están almacenadas internamente. De esta manera, el reporte puede acceder a los elementos sin depender de si se utiliza una lista, un mapa u otra estructura de datos.

2. Impresión de comprobantes – Patrón Template Method

El patrón Template Method encajaría porque permite definir una estructura general para la impresión de los comprobantes, manteniendo los pasos de encabezado, cuerpo y pie. Cada tipo de comprobante implementaría específicamente la forma de construir su cuerpo, reutilizando el procedimiento general sin duplicar toda la lógica de impresión.

3. Almacenamiento y restauración de instantáneas – Patrón Memento

El patrón Memento sería apropiado porque permite guardar y restaurar instantáneas completas del estado de una solicitud sin exponer sus atributos internos al objeto encargado de conservarlas. A diferencia del mecanismo undo de la Necesidad 2, basado en deshacer operaciones ejecutadas mediante comandos, Memento permite recuperar un estado anterior completo, incluso sin tener que reconstruir las operaciones realizadas posteriormente.

