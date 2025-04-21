CREATE TABLE usuario (
    id VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_modificacion DATETIME,
    ultimo_login DATETIME NOT NULL,
    token VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL
);

-- Tabla de teléfonos para MySQL
CREATE TABLE telefono (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(50),
    usuario_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);