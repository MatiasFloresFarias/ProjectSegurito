-- Generado por Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   en:        2020-07-02 17:46:54 CLT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE actividadesmejora (
    idactividadmejora           INTEGER NOT NULL,
    nombre                      NVARCHAR2(50),
    fechainicio                 DATE,
    fechatermino                DATE,
    estado                      NVARCHAR2(50),
    detalle                     NVARCHAR2(100),
    profesional_id_profesional  INTEGER NOT NULL,
    cliente_id_cliente          INTEGER NOT NULL
);

ALTER TABLE actividadesmejora ADD CONSTRAINT actividadesmejora_pk PRIMARY KEY ( idactividadmejora );

CREATE TABLE asesorias (
    id_asesoria                 INTEGER NOT NULL,
    fechayhora                  DATE,
    motivo                      NVARCHAR2(50),
    detalle                     NVARCHAR2(100),
    profesional_id_profesional  INTEGER NOT NULL,
    cliente_id_cliente          INTEGER NOT NULL
);

ALTER TABLE asesorias ADD CONSTRAINT asesorias_pk PRIMARY KEY ( id_asesoria );

CREATE TABLE capacitaciones (
    id_capacitacion             INTEGER NOT NULL,
    fechayhora                  DATE,
    tema                        NVARCHAR2(50),
    contenido                   NVARCHAR2(100),
    profesional_id_profesional  INTEGER NOT NULL,
    cliente_id_cliente          INTEGER NOT NULL
);

ALTER TABLE capacitaciones ADD CONSTRAINT capacitaciones_pk PRIMARY KEY ( id_capacitacion );

CREATE TABLE cliente (
    id_cliente     INTEGER NOT NULL,
    nombreempresa  NVARCHAR2(50),
    rutempresa     NVARCHAR2(50),
    fecharegistro  DATE
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE detallefactura (
    id_detallefactura   INTEGER NOT NULL,
    nombre              NVARCHAR2(50),
    precio              NUMBER(38,0),
    cantidad            NUMBER(38,0),
    factura_id_factura  INTEGER NOT NULL
);

ALTER TABLE detallefactura ADD CONSTRAINT detallefactura_pk PRIMARY KEY ( id_detallefactura );

CREATE TABLE factura (
    id_factura          INTEGER NOT NULL,
    fechacobro          DATE,
    fechavencimiento    DATE,
    extras              NUMBER(38,0),
    impuestos           NUMBER(38,0),
    subtotal            NUMBER(38,0),
    total               NUMBER(38,0),
    cliente_id_cliente  INTEGER NOT NULL
);

ALTER TABLE factura ADD CONSTRAINT factura_pk PRIMARY KEY ( id_factura );

CREATE TABLE profesional (
    id_profesional  INTEGER NOT NULL,
    nombre          NVARCHAR2(50),
    apellido        NVARCHAR2(50),
    correo          NVARCHAR2(50),
    telefono        INTEGER,
    cargo           NVARCHAR2(50)
);

ALTER TABLE profesional ADD CONSTRAINT profesional_pk PRIMARY KEY ( id_profesional );

CREATE TABLE reporteaccidente (
    id_reporte          INTEGER NOT NULL,
    fecha               DATE,
    direccion           NVARCHAR2(50),
    labor               NVARCHAR2(50),
    descripcion         NVARCHAR2(50),
    cliente_id_cliente  INTEGER NOT NULL
);

ALTER TABLE reporteaccidente ADD CONSTRAINT reporteaccidente_pk PRIMARY KEY ( id_reporte );


CREATE TABLE usuario (
    id        INTEGER NOT NULL,
    password  NVARCHAR2(50) NOT NULL,
    nickname  NVARCHAR2(50) NOT NULL,
    rol       NVARCHAR2(50) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id, nickname );



ALTER TABLE actividadesmejora
    ADD CONSTRAINT actividadesmejora_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );

ALTER TABLE asesorias
    ADD CONSTRAINT asesorias_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );

ALTER TABLE asesorias
    ADD CONSTRAINT asesorias_profesional_fk FOREIGN KEY ( profesional_id_profesional )
        REFERENCES profesional ( id_profesional );

ALTER TABLE capacitaciones
    ADD CONSTRAINT capacitaciones_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );

ALTER TABLE capacitaciones
    ADD CONSTRAINT capacitaciones_profesional_fk FOREIGN KEY ( profesional_id_profesional )
        REFERENCES profesional ( id_profesional );

ALTER TABLE detallefactura
    ADD CONSTRAINT detallefactura_factura_fk FOREIGN KEY ( factura_id_factura )
        REFERENCES factura ( id_factura );

ALTER TABLE factura
    ADD CONSTRAINT factura_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );

ALTER TABLE actividadesmejora
    ADD CONSTRAINT mejora_profesional_fk FOREIGN KEY ( profesional_id_profesional )
        REFERENCES profesional ( id_profesional );

ALTER TABLE reporteaccidente
    ADD CONSTRAINT reporteaccidente_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );


CREATE SEQUENCE idactividadmejora_seq START WITH 1;
CREATE TRIGGER idactividadmejora_bi BEFORE INSERT ON actividadesmejora FOR EACH ROW BEGIN SELECT idactividadmejora_seq.nextval INTO :new.idactividadmejora FROM dual; END;
/
CREATE SEQUENCE id_asesoria_seq START WITH 1;
CREATE TRIGGER id_asesoria_bi BEFORE INSERT ON asesorias FOR EACH ROW BEGIN SELECT id_asesoria_seq.nextval INTO :new.id_asesoria FROM dual; END;
/
CREATE SEQUENCE  id_capacitacion_seq START WITH 1;
CREATE TRIGGER  id_capacitacion_bi BEFORE INSERT ON capacitaciones FOR EACH ROW BEGIN SELECT  id_capacitacion_seq.nextval INTO :new.id_capacitacion FROM dual; END;
/
CREATE SEQUENCE  id_cliente_seq START WITH 1;
CREATE TRIGGER  id_cliente_bi BEFORE INSERT ON cliente FOR EACH ROW BEGIN SELECT  id_cliente_seq.nextval INTO :new.id_cliente FROM dual; END;
/
CREATE SEQUENCE  id_detallefactura_seq START WITH 1;
CREATE TRIGGER  id_detallefactura_bi BEFORE INSERT ON detallefactura FOR EACH ROW BEGIN SELECT  id_detallefactura_seq.nextval INTO :new.id_detallefactura FROM dual; END;
/
CREATE SEQUENCE  id_factura_seq START WITH 1;
CREATE TRIGGER  id_factura_bi BEFORE INSERT ON factura FOR EACH ROW BEGIN SELECT  id_factura_seq.nextval INTO :new.id_factura FROM dual; END;
/
CREATE SEQUENCE  id_profesional_seq START WITH 1;
CREATE TRIGGER  id_profesional_bi BEFORE INSERT ON profesional FOR EACH ROW BEGIN SELECT  id_profesional_seq.nextval INTO :new.id_profesional FROM dual; END;
/
CREATE SEQUENCE  id_reporte_seq START WITH 1;
CREATE TRIGGER  id_reportea_bi BEFORE INSERT ON reporteaccidente FOR EACH ROW BEGIN SELECT  id_reporte_seq.nextval INTO :new.id_reporte FROM dual; END;
/
CREATE SEQUENCE  id_seq START WITH 1;
CREATE TRIGGER  id_bi BEFORE INSERT ON usuario FOR EACH ROW BEGIN SELECT  id_seq.nextval INTO :new.id FROM dual; END;
/


--CLIENTE

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Transportes S.A','77888999-9',TO_DATE('20/03/2020','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Minería La Plata','78999000-0',TO_DATE('15/01/2020','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Bodegas Plastic','88000333-9',TO_DATE('10/05/2019','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('O y R LTDA.','70654389-8',TO_DATE('24/08/2019','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Constructora Central','76604493-1',TO_DATE('30/09/2019','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Envasados Gonzalez','75600500-2',TO_DATE('03/02/2019','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Baxter','77564321-9',TO_DATE('02/04/2020','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('General Motors','79560489-4',TO_DATE('07/05/2020','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Grupo Armour','78005338-1',TO_DATE('19/10/2019','dd/mm/yyyy'));

INSERT INTO cliente (nombreempresa,rutempresa,fecharegistro)
values ('Quintino Express','76534765-2',TO_DATE('05/12/2019','dd/mm/yyyy'));


--PROFESIONAL

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('José','Mendoza','josemendoza@segurito.cl','977512400','Prevencionista de riesgo en terreno');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Lorena','Nunez','lorenanunez@segurito.cl','988877699','Coordinadora de calidad de vida laboral');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Cesar','Araneda','cesararaneda@segurito.cl','999800555','Jefe dpto. prevención de riesgos laborales');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Rodrigo','Vasquez','rodrigovasquez@segurito.cl','900033378','Prevencionista de riesgo en terreno');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Alicia','Martinez','aliciamartinez@segurito.cl','977744455','Asesora en Prevención de Riesgos');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Catalina','Rojas','catalinarojas@segurito.cl','966644433','Asesora en Prevención de Riesgos');

INSERT INTO profesional (nombre,apellido,correo,telefono,cargo)
values ('Daniel','Carrasco','danielcarrasco@segurito.cl','988900043','Prevencionista de riesgo en terreno');

--ACTIVIDADES MEJORA

INSERT INTO actividadesmejora (nombre,fechainicio,fechatermino,estado,detalle,profesional_id_profesional,cliente_id_cliente)
values ('Accidentes en superficies de trabajo',TO_DATE('01/02/2020','dd/mm/yyyy'),TO_DATE('02/03/2020','dd/mm/yyyy'),'Finalizado','Se reparan partes rotas del piso','4','2');

INSERT INTO actividadesmejora (nombre,fechainicio,fechatermino,estado,detalle,profesional_id_profesional,cliente_id_cliente)
values ('Riesgos en operaciones de maquinas',TO_DATE('20/06/2020','dd/mm/yyyy'),TO_DATE('20/07/2020','dd/mm/yyyy'),'Pendiente','Pintar maquinas según el código de colores','1','4');

--REPORTE ACCIDENTE

INSERT INTO reporteaccidente(fecha,direccion,labor,descripcion,cliente_id_cliente)
values (TO_DATE('02/05/2020','dd/mm/yyyy'),'Santa Marta 0344','Descarga de camion','Trabajador se dobla tobillo por suelo roto','3');

INSERT INTO reporteaccidente(fecha,direccion,labor,descripcion,cliente_id_cliente)
values (TO_DATE('20/06/2020','dd/mm/yyyy'),'Departamental 769','Amago de incendio','Derrame de productos inflamables','6');

--ASESORIAS

INSERT INTO asesorias(fechayhora,motivo,detalle,profesional_id_profesional,cliente_id_cliente)
values (TO_DATE('02/05/2020 13:00','dd/mm/yyyy HH24:mi'),'Fiscalizacion','Repaso de los temas de seguridad y chequeo de cumplimiento','1','3');

INSERT INTO asesorias(fechayhora,motivo,detalle,profesional_id_profesional,cliente_id_cliente)
values (TO_DATE('01/03/2019 10:00','dd/mm/yyyy HH24:mi'),'Accidente','Revision de compensaciones y multas a las que se puede ver afecta la empresa','2','1');

--CAPACITACIONES
INSERT INTO capacitaciones(fechayhora,tema,contenido,profesional_id_profesional,cliente_id_cliente)
values (TO_DATE('05/08/2020 12:00','dd/mm/yyyy HH24:mi'),'Seguridad en la oficina','Buenas practicas de seguridad','2','4');

INSERT INTO capacitaciones(fechayhora,tema,contenido,profesional_id_profesional,cliente_id_cliente)
values (TO_DATE('02/05/2020 11:00','dd/mm/yyyy HH24:mi'),'Seguridad en faena','Buenas practicas de seguridad','4','3');

--FACTURA
INSERT INTO factura(fechacobro,fechavencimiento,extras, impuestos, subtotal, total, cliente_id_cliente)
values (TO_DATE('04/04/2020','dd/mm/yyyy'), TO_DATE('24/04/2020','dd/mm/yyyy'),300000,475000,2200000,2975000, '1');

INSERT INTO factura(fechacobro,fechavencimiento,extras, impuestos,subtotal, total, cliente_id_cliente)
values (TO_DATE('03/03/2020','dd/mm/yyyy'), TO_DATE('23/03/2020','dd/mm/yyyy'),400000,399000,1700000,2499000, '2');

--DETALLE FACTURA
INSERT INTO detallefactura(nombre,precio, cantidad, factura_id_factura)
values ('capacitacion', 400000,2,'1');

INSERT INTO detallefactura(nombre,precio, cantidad, factura_id_factura)
values ('asesoria', 300000,4,'1');

INSERT INTO detallefactura(nombre,precio, cantidad, factura_id_factura)
values ('callcenter', 200000,1,'1');

INSERT INTO detallefactura(nombre,precio, cantidad, factura_id_factura)
values ('asesoria', 300000,3,'2');

INSERT INTO detallefactura(nombre,precio, cantidad, factura_id_factura)
values ('capacitacion', 400000,2,'2');

--USUARIO
INSERT INTO usuario(password,nickname, rol)
values ('5C3D63ED342437F2C603E5360181C1B5', 'profesional','profesional');
INSERT INTO usuario(password,nickname, rol)
values ('91F5167C34C400758115C2A6826EC2E3', 'administrador','administrador');
INSERT INTO usuario(password,nickname, rol)
values ('4983A0AB83ED86E0E7213C8783940193', 'cliente','cliente');
INSERT INTO usuario(password,nickname, rol)
values ('E0C826B1C1532015B386ADDB63147C5E', 'mlillo','profesional');
INSERT INTO usuario(password,nickname, rol)
values ('143D42165DFAA0E4870B787D8D0CB125', 'mflores','profesional');
INSERT INTO usuario(password,nickname, rol)
values ('048056AD6EFCA97D0B4CFF5F0114BB09', 'jtolorza','profesional');
INSERT INTO usuario(password,nickname, rol)
values ('6896FEB8E3499D6DC48E4DC5625B4020', 'clloncon','profesional');

