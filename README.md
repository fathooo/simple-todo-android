"# simple-todo-android"

### Título del Proyecto
- **Nombre del Proyecto:** simple-todo-android

### Descripción
- **Breve Descripción:** Una aplicación de To-Do's básica que permite a los usuarios crear, leer, actualizar y eliminar tareas. La aplicación consume una API mock (`https://jsonplaceholder.typicode.com/todos`) y sigue patrones de diseño MVVM y Clean Architecture para mantener una estructura de código limpia y organizada.

### Tabla de Contenidos
1. [Características](#características)
2. [Instalación](#instalación)
3. [Uso](#uso)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Tecnologías Utilizadas](#tecnologías-utilizadas)
6. [Contacto](#contacto)

### Características
- **Lista de Tareas:** Muestra una lista de tareas utilizando RecyclerView.
- **Operaciones CRUD:** Crear, leer, actualizar y eliminar tareas.
- **Persistencia del Estado:** Mantiene el estado sobre cambios en la configuración del dispositivo.
- **Interfaz de Usuario:** Responsive y estéticamente agradable, siguiendo las guías de Material Design.
- **Búsqueda de Tareas:** Filtrar tareas por texto.
- **Ordenamiento de Tareas:** Ordenar tareas de manera ascendente o descendente por su ID.

### Prerrequisitos

- [Android Studio](https://developer.android.com/studio), versión 4.1 o superior.
- [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), versión 8 o superior.

### Instalación
1. **Clonar el Repositorio:**
    ```bash
    git clone https://github.com/fathooo/simple-todo-android.git
    ```
2. **Abrir en Android Studio:**
    - Abrir Android Studio.
    - Seleccionar "Open an existing Android Studio project".
    - Navegar hasta el directorio donde clonaste el repositorio y seleccionarlo.
3. **Configurar Dependencias:**
    - Android Studio se encargará de descargar todas las dependencias necesarias.
4. **Ejecutar la Aplicación:**
    - Conectar tu dispositivo o abrir un emulador.
    - Clic en el botón de "Run" en Android Studio.

### Uso
- **Interfaz Principal:** Al abrir la aplicación, verás una lista de tareas.
- **Añadir Tarea:** Clic en el botón flotante "+" para añadir una nueva tarea.
- **Editar Tarea:** Clic en el ícono de editar en la tarjeta de la tarea.
- **Eliminar Tarea:** Clic en el ícono de eliminar en la tarjeta de la tarea.
- **Completar Tarea:** Marcar la casilla de verificación junto a una tarea para marcarla como completada.
- **Filtrar Tareas:** En la barra de búsqueda, escribe el texto por el cual deseas filtrar las tareas. Las tareas se filtrarán automáticamente conforme escribes.
- **Ordenar Tareas:** Clic en el ícono de ordenación junto a la barra de búsqueda para alternar entre orden ascendente y descendente de las tareas.

### Estructura del Proyecto
```plaintext
+- AndroidManifest.xml
+- java/
|  +- com/
|  |  +- fathooo/
|  |  |  +- technicaltest/
|  |  |  |  +- data/
|  |  |  |  +- domain/
|  |  |  |  +- presentation/
|  |  |  |  +- di/
|  |  |  |  +- TechnicalTestApplication.kt
+- res/
|  +- drawable/
|  +- layout/
|  +- values/
|  +- xml/
```

### Tecnologías Utilizadas
- **Lenguaje:** Kotlin
- **Arquitectura:** MVVM y Clean Architecture
- **Inyección de Dependencias:** Koin
- **Networking:** Retrofit
- **Gestión de UI:** LiveData, ViewModel, Data Binding
- **Interfaz de Usuario:** Material Design Components

### Contacto
- **Nombre:** fathooo
- **Email:** ftorres@fathooo.com