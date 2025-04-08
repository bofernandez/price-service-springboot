# 💰 Price Service API

Este proyecto es una API REST construida con Spring Boot que permite consultar el precio aplicable de un producto en una fecha determinada, según el identificador del producto, la marca y una lógica de prioridad definida.

---

## 🧾 Descripción del problema

En el sistema core del ecommerce de Inditex, se mantienen registros de precios por marca, producto y fecha. Esta API consulta la base de datos y devuelve el precio correcto aplicable según:

- Marca (`brandId`)
- Producto (`productId`)
- Fecha de aplicación (`applicationDate`)

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (en memoria)
- Maven
- JUnit 5 y MockMvc (para testing)

---

## ⚙️ Cómo ejecutar el proyecto

### 🧪 Requisitos

- Java 17 o superior
- Maven instalado

### 🚀 Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/price-service-springboot.git
   cd price-service-springboot
2. Ejecuta el proyecto con Maven:
   mvn spring-boot:run
3. Accede a la API en:
   http://localhost:8080/api/prices
---
### 🔎 Endpoint disponible
GET /api/prices
Consulta el precio aplicable.
---
### 🔸 Parámetros:
Parámetro	    Tipo	Descripción
applicationDate	String	Fecha y hora del formato yyyy-MM-dd-HH.mm.ss
productId	    Long	ID del producto
brandId	        Long	ID de la marca

---
### 📥 Ejemplo de solicitud:

GET /api/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1
### 📤 Ejemplo de respuesta:
{
"productId": 35455,
"brandId": 1,
"priceList": 1,
"startDate": "2020-06-14T00:00:00",
"endDate": "2020-12-31T23:59:59",
"price": 35.5,
"currency": "EUR"
}

---
### 🧪 Pruebas incluidas
El proyecto incluye pruebas para los siguientes casos:

2020-06-14 10:00 → Tarifa 1
2020-06-14 16:00 → Tarifa 2 (mayor prioridad)
2020-06-14 21:00 → Tarifa 1
2020-06-15 10:00 → Tarifa 3
2020-06-16 21:00 → Tarifa 4

Se ejecutan automáticamente con:
mvn test
---
### 🧠 Lógica de negocio
Cuando hay múltiples precios que aplican a un producto en la misma fecha, se aplica la siguiente lógica:

Se filtran los precios donde:
- La fecha de consulta esté entre startDate y endDate
- Coincidan productId y brandId

Se selecciona el precio con mayor prioridad
---
### 📚 Base de datos en memoria
Usa H2 para pruebas. Puedes acceder a la consola en:

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vacío)

Los datos se cargan automáticamente desde data.sql.
---
### 🧹 Mejoras posibles
- Documentación Swagger/OpenAPI
- Manejo avanzado de errores (custom exceptions)
- Dockerización
- Caching para mejorar rendimiento

🧑‍💻 Autor
Desarrollado por Borja Fernández
GitHub: @bofernandez