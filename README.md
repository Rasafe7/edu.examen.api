- El POST devuelve un 201 CREATED porque indica que se ha creado un producto nuevo. El DELETE devuelve un 204 No Content porque indica que el producto se ha eliminado correctamente y no necesita devolver ningún contenido.

- Recomendaría el modelo iterativo incremental, porque permite desarrollar la aplicación poco a poco e ir añadiendo nuevas funciones según se vayan definiendo los requisitos.

- Spring Boot detecta las dependencias y la configuración del proyecto para preparar automáticamente la conexión con la base de datos. Si cambiamos de PostgreSQL a MySQL, tendríamos que cambiar el driver en el pom.xml y los datos de conexión en el properties.
