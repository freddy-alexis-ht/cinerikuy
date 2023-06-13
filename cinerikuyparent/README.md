##READ-ME

### 1. DATABASE

Dependiendo del gestor de DB usado, es posible que si se ejecutan los scripts en uno solo, la data se inserte en la DB por defecto y no en 'cinerikuy'.  

El orden de ejecución es:
- Ejecutar a)
- Asegurarse que el script b) se ejecute en la DB 'cinerikuy'.

a) Script-Postgres-DB-only.sql  
b) Script-Postgres-Data.sql

### 2. ENDPOINTS

En cada MS los endpoints están dispuestos así:
- ```http://localhost:8080/cinemas/**``` .. para el usuario.
- ```http://localhost:8080/admin/cinemas/**``` .. para el administrador.  

De igual modo para los demás MS.