CREATE TABLE USUARIO
        (
        id VARCHAR2(200) NOT NULL,
        nombre VARCHAR2(200) NOT NULL,
        correo VARCHAR2(200)  NULL,
        activo NUMBER(1) NOT NULL,
        contrasena VARCHAR2(200) NULL,
        dia_creado date   NULL,
        dia_modificado date   NULL,
        dia_ultimo_logueado date   NULL,  
        PRIMARY KEY(id)
        );

CREATE TABLE TELEFONO
        (
        id VARCHAR2(200) NOT NULL,
        id_usuario VARCHAR2(200) NOT NULL,
        numero_telefono VARCHAR2(200) NOT NULL,
        codigo_ciudad VARCHAR2(200)  NULL,
        codigo_pais VARCHAR2(200)  NULL,
        activo NUMBER(1) NOT NULL,     
        dia_creado date   NULL,
        dia_modificado date   NULL,
        dia_ultimo_logueado date NULL,        
        PRIMARY KEY(id),
        FOREIGN KEY(id_usuario) REFERENCES USUARIO(id)
        );