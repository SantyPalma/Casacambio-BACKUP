# Casa de Cambio 💱

Sistema de Casa de Cambio desarrollado en Java + MySQL utilizando interfaces gráficas con Swing y conexión a base de datos mediante JDBC.

---

# 📌 Descripción del Proyecto

La aplicación permite gestionar operaciones de cambio de moneda mediante una interfaz gráfica amigable. El sistema incluye autenticación de usuarios, registro de transacciones, historial de operaciones y administración de tasas de cambio.

Este proyecto fue desarrollado como Proyecto Integrador para la asignatura Programación I.

---

# 🚀 Funcionalidades

✅ Inicio de sesión de usuarios  
✅ Registro de transacciones  
✅ Conversión de monedas  
✅ Historial de transacciones  
✅ Editar transacciones  
✅ Eliminar transacciones  
✅ Limpiar historial visual  
✅ Gestión de monedas  
✅ Actualización de tasas de cambio desde la interfaz  
✅ Conexión a MySQL mediante JDBC  

---

# 🛠️ Tecnologías Utilizadas

- Java
- Java Swing
- MySQL
- JDBC
- Visual Studio Code

---

# 📂 Estructura del Proyecto

```plaintext
src/
│
├── Conexion.java
├── UsuarioDAO.java
├── TransaccionDAO.java
├── MonedaDAO.java
├── Login.java
├── MenuPrincipal.java
├── VentanaTransaccion.java
├── HistorialTransacciones.java
├── GestionMonedas.java
```

---

# 🗄️ Base de Datos

Nombre de la base de datos:

```sql
casa_cambio
```

Tablas principales:

- Usuario
- Moneda
- Transaccion

---

# ⚙️ Configuración

## 1. Importar la Base de Datos

En MySQL Workbench:

```plaintext
Server → Data Import
```

Seleccionar la carpeta del dump SQL del proyecto.

---

## 2. Configurar la conexión

Editar el archivo:

```java
Conexion.java
```

Modificar:

```java
String user = "root";
String password = "";
```

según la configuración local de MySQL.

---

# ▶️ Ejecución del Proyecto

## Compilar

```bash
javac *.java
```

## Ejecutar

```bash
java Login
```

---

# 📌 Funcionalidades del Sistema

## 🔐 Login

Permite validar usuarios registrados en la base de datos.

---

## 💱 Registro de Transacciones

El usuario puede:

- ingresar monto
- seleccionar moneda origen
- seleccionar moneda destino
- calcular conversión
- guardar operación

---

## 📜 Historial

Permite:

- visualizar transacciones
- editar registros
- eliminar registros

---

## 💵 Gestión de Monedas

Permite modificar las tasas de cambio desde la interfaz gráfica sin necesidad de editar el código fuente.

---

# 📖 Conceptos Aplicados

- Programación Orientada a Objetos
- DAO (Data Access Object)
- CRUD
- JDBC
- Swing
- MySQL
- Encapsulamiento
- Interfaces gráficas

---

# 👨‍💻 Autor

Proyecto desarrollado por Santy para la asignatura Programación I.
