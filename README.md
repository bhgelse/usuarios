src/
└── main/
    ├── java/com/gelse/app/
    │   ├── MainApp.java                  ← Lanza JavaFX y Spring
    │   ├── config/                       ← JWT, SecurityConfig, etc.
    │   ├── controller/                   ← API REST controllers
    │   ├── service/                      ← Servicios de negocio
    │   ├── repository/                   ← Interfaces de JPA
    │   ├── model/                        ← Entidades JPA (ej. Usuario)
    │   ├── dto/                          ← DTOs para requests/responses
    │   ├── exception/                    ← Errores personalizados
    │   └── javafx/
    │       ├── view/                     ← Pantallas (FXML o Java)
    │       ├── controller/               ← Controladores de UI
    │       ├── service/                  ← Lógica que consume la API
    │       └── util/                     ← Utilidades (Scene loader, JWT utils, etc.)
    └── resources/
        ├── application.properties        ← Configuraciones de Spring Boot
        ├── fxml/                         ← Archivos FXML de JavaFX
        └── db/migration/                 ← Scripts Flyway (ej. V1__init.sql)
