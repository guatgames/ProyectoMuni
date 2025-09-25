-- ---------------------------------------
-- BASE DE DATOS MUNICIPALIDAD
-- ---------------------------------------

drop database if exists DB_Municipalidad_JavaFX;
create database DB_Municipalidad_JavaFX;
use DB_Municipalidad_JavaFX;

-- ---------------------------------------
-- ENTIDADES
-- ---------------------------------------
create table login(
	id int primary key auto_increment,
    nombre varchar(30),
    contrasena varchar(30)
);

create table zonas(
	id_zona int primary key auto_increment,
    zona varchar(7)
);

create table ciudadanos(
	id_ciudadano int primary key auto_increment,
    id_zona int not null,
    nombre varchar(30) not null,
    apellidos varchar(30) not null,
    dpi varchar(13) not null,
    telefono varchar(9) not null,
    email varchar(30) not null,
    direccion varchar(30) not null,
    constraint fk_ciudadanos_zonas foreign key (id_zona) references zonas(id_zona)
);

create table conductores(
	id_conductores int primary key auto_increment,
    id_ciudadano int not null,
    tipoSangre varchar(10),
    constraint fk_conductores_ciudadanos foreign key (id_ciudadano) references ciudadanos(id_ciudadano)
);

create table licencias(
	id_licencia int primary key auto_increment,
    id_conductores int not null,
    categoria enum('A', 'B', 'C', 'M', 'T') not null,
    fechaEmision date not null,
    fechaVencimiento date not null,
    estado enum('vigente', 'vencida', 'suspendida') default 'vigente' not null,
    constraint fk_licencias_conductores foreign key (id_conductores) references conductores(id_conductores)
);

create table vehiculos(
	id_vehiculo int primary key auto_increment,
    placa varchar(10) unique not null,
    marca varchar(30) not null,
    modelo varchar(30) not null,
    anio int not null,
    color varchar(20) not null,
    id_ciudadano int not null,
    constraint fk_vehiculos_ciudadanos foreign key (id_ciudadano) references ciudadanos(id_ciudadano)
);

create table agenteTransito(
	id_agente int primary key auto_increment,
    codigo varchar(20) not null,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(9) not null,
    id_zona int not null,
    constraint fk_agenteTransito_zonas foreign key (id_zona) references zonas(id_zona)
);

create table infracciones(
	id_infracciones int primary key auto_increment,
    codigo varchar(10) unique not null,
    descripcion varchar(200) not null,
    montoBase decimal(10,2) not null
);

create table multas(
	id_multa int primary key auto_increment,
    id_infracciones int not null,
    id_conductores int not null,
    id_vehiculo int not null,
    id_agente int not null,
    fecha date not null,
    lugar varchar(120) not null,
    monto decimal(10,2) not null,
    estado enum('pendiente', 'pagada', 'anulada') default 'pendiente' not null,
    observaciones varchar(200) not null,
    constraint fk_multas_infracciones foreign key (id_infracciones) references infracciones(id_infracciones),
    constraint fk_multas_conductores foreign key (id_conductores) references conductores(id_conductores),
    constraint fk_multas_vehiculos foreign key (id_vehiculo) references vehiculos(id_vehiculo),
    constraint fk_multas_agenteTransito foreign key (id_agente) references agenteTransito(id_agente)
);

create table pagos(
	id_pago int primary key auto_increment,
    id_multa int not null,
    fechaPago date not null,
    montoPagado decimal(10,2) not null,	
    metodoPago decimal(10,2) not null,
    referencia varchar(40) not null,
    constraint fk_pagos_multas foreign key (id_multa) references multas(id_multa)
);

-- ---------------------------------------
-- DATOS
-- ---------------------------------------
-- 0) Login
insert into login (nombre, contrasena) values ("Angel","123");

-- 1) zonas
insert into zonas (zona) values
('Zona 1'),
('Zona 2'),
('Zona 3'),
('Zona 4'),
('Zona 5'),
('Zona 6'),
('Zona 7'),
('Zona 8'),
('Zona 9'),
('Zona 10');

-- 2) ciudadanos
insert into ciudadanos (id_zona,nombre,apellidos,dpi,telefono,email,direccion) values
(1,'Carlos','Ramirez','1234567890101','55551234','c.ramirez@mail.com','6a Av 10-25 Z1'),
(2,'Maria','Gonzalez','1234567890102','55551235','m.gonzalez@mail.com','12 C 4-55 Z2'),
(3,'Jose','Perez','1234567890103','55551236','j.perez@mail.com','Olivos B4 Z3'),
(4,'Ana','Lopez','1234567890104','55551237','ana.lopez@mail.com','2a C 4-10 Z4'),
(5,'Luis','Hernandez','1234567890105','55551238','l.hernandez@mail.com','Km30 Pacífico'),
(6,'Sofia','Martinez','1234567890106','55551239','sofia.mtz@mail.com','El Centro SMP'),
(7,'Jorge','Castillo','1234567890107','55551240','jorge.castillo@mail.com','Jardines VC'),
(8,'Paola','Diaz','1234567890108','55551241','paola.dz@mail.com','Las Flores SCP'),
(9,'Ricardo','Mendez','1234567890109','55551242','ricardo.md@mail.com','Parcelas 3 SJP'),
(10,'Gabriela','Ortiz','1234567890110','55551243','g.ortiz@mail.com','Finca Arboleda');

-- 3) conductores
insert into conductores (id_ciudadano,tipoSangre) values
(1,'O+'),
(2,'A+'),
(3,'B+'),
(4,'O-'),
(5,'A-'),
(6,'AB+'),
(7,'O+'),
(8,'B-'),
(9,'A+'),
(10,'O+');

-- 4) licencias
insert into licencias (id_conductores,categoria,fechaEmision,fechaVencimiento,estado) values
(1,'B','2023-01-15','2027-01-14','vigente'),
(2,'C','2022-05-10','2026-05-09','vigente'),
(3,'M','2021-08-20','2025-08-19','vigente'),
(4,'B','2020-02-01','2024-01-31','vencida'),
(5,'A','2023-03-05','2027-03-04','vigente'),
(6,'B','2022-11-12','2026-11-11','vigente'),
(7,'C','2021-06-25','2025-06-24','vigente'),
(8,'M','2020-09-30','2024-09-29','vencida'),
(9,'T','2023-07-07','2027-07-06','vigente'),
(10,'B','2024-01-10','2028-01-09','vigente');

-- 5) vehiculos
insert into vehiculos (placa,marca,modelo,anio,color,id_ciudadano) values
('P123FBN','Toyota','Corolla',2018,'Blanco',1),
('P456GHT','Honda','Civic',2020,'Negro',2),
('P789JKL','Nissan','Versa',2019,'Gris',3),
('P321MNO','Kia','Rio',2017,'Azul',4),
('P654PQR','Hyundai','Accent',2021,'Rojo',5),
('P987STU','Mazda','3',2016,'Plata',6),
('P741VWX','Chevrolet','Sail',2015,'Blanco',7),
('P852YZA','Ford','Focus',2018,'Negro',8),
('P963BCD','Volkswagen','Jetta',2022,'Azul',9),
('C111EFG','Isuzu','NQR',2019,'Blanco',10);

-- 6) agenteTransito
insert into agenteTransito (codigo,nombre,apellido,telefono,id_zona) values
('AG001','Pedro','Santos','55552001',1),
('AG002','Lucia','Garcia','55552002',2),
('AG003','Mario','Chacon','55552003',3),
('AG004','Rita','Velasquez','55552004',4),
('AG005','Hugo','Morales','55552005',5),
('AG006','Claudia','Pineda','55552006',6),
('AG007','Diego','Sierra','55552007',7),
('AG008','Roxana','Alvarado','55552008',8),
('AG009','Kevin','Lima','55552009',9),
('AG010','Estela','Juarez','55552010',10);

-- 7) infracciones
insert into infracciones (codigo,descripcion,montoBase) values
('I001','Exceso de velocidad',500.00),
('I002','Estacionamiento en línea roja',300.00),
('I003','No portar licencia',400.00),
('I004','Semáforo en rojo',600.00),
('I005','Uso de celular al conducir',350.00),
('I006','Sin placa',800.00),
('I007','Sin seguro',700.00),
('I008','Circular en sentido contrario',900.00),
('I009','Evasión de control',1000.00),
('I010','Ruido excesivo (escape)',250.00);

-- 8) multas
insert into multas (id_infracciones,id_conductores,id_vehiculo,id_agente,fecha,lugar,monto,estado,observaciones) values
(1,1,1,1,'2025-06-01','Calz. Aguilar Batres',500.00,'pendiente','Radar 70 km/h en zona 50'),
(2,2,2,2,'2025-06-05','Zona 1 12 calle',300.00,'pendiente','Estacionado en línea roja'),
(3,3,3,3,'2025-06-10','Anillo Periférico',400.00,'pagada','Mostró DPI sin licencia'),
(4,4,4,4,'2025-06-12','El Obelisco',600.00,'pendiente','Cruce con luz roja'),
(5,5,5,5,'2025-06-15','Calz. Roosevelt',350.00,'pagada','Uso de celular'),
(6,6,6,6,'2025-06-18','Bv. Liberación',800.00,'anulada','Vehículo recién comprado'),
(7,7,7,7,'2025-06-20','Zona 10',700.00,'pendiente','Sin seguro'),
(8,8,8,8,'2025-06-22','Ruta al Atlántico',900.00,'pendiente','Maniobra peligrosa'),
(9,9,9,9,'2025-06-25','Carretera a El Salvador',1000.00,'pendiente','Se dio a la fuga y retornó'),
(10,10,10,10,'2025-06-28','Zona 4',250.00,'pagada','Operativo de ruido');

-- 9) pagos
insert into pagos (id_multa,fechaPago,montoPagado,metodoPago,referencia) values
(1,'2025-08-01',200.00,1.00,'ABONO-200'),
(2,'2025-07-02',300.00,2.00,'POS-300'),
(3,'2025-06-11',400.00,1.00,'CAJA-0001'),
(4,'2025-07-05',100.00,1.00,'AB-100'),
(5,'2025-06-16',350.00,2.00,'POS-12345'),
(6,'2025-06-19',0.00,1.00,'ANUL-0'),
(7,'2025-07-25',700.00,2.00,'POS-77777'),
(8,'2025-07-10',200.00,1.00,'AB-200'),
(9,'2025-07-30',1000.00,3.00,'TRX-00009'),
(10,'2025-06-29',250.00,3.00,'TRX-98765');

-- =======================================
-- PROCEDIMIENTOS ALMACENADOS
-- =======================================

-- ========== LOGIN ==========
delimiter $$
	create procedure sp_ValidarLogin(in p_nombre varchar(30), in p_contrasena varchar(30))
    
	begin
	  select id, nombre
	  from login
	  where nombre = p_nombre and contrasena = p_contrasena
	  limit 1;
	end $$
delimiter ;

delimiter $$
	create procedure sp_login_create(in nom varchar(30), in cont varchar(30))
    
    begin
		insert into login(nombre,contrasena)
			value (nom, cont);
	end$$
delimite ;

-- ========== ZONAS ==========
delimiter $$
create procedure sp_zonas_create(in p_zona varchar(7))
begin
  insert into zonas(zona) values (p_zona);
  select last_insert_id() as id_zona;
end$$
delimiter ;

delimiter $$
create procedure sp_zonas_read_all()
begin
  select * from zonas order by id_zona;
end$$
delimiter ;

delimiter $$
create procedure sp_zonas_read_by_id(in p_id int)
begin
  select * from zonas where id_zona = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_zonas_update(in p_id int, in p_zona varchar(7))
begin
  update zonas set zona = p_zona where id_zona = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_zonas_delete(in p_id int)
begin
  delete from zonas where id_zona = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== CIUDADANOS ==========
delimiter $$
create procedure sp_ciudadanos_create(
  in p_id_zona int, in p_nombre varchar(30), in p_apellidos varchar(30),
  in p_dpi varchar(13), in p_telefono varchar(9), in p_email varchar(30),
  in p_direccion varchar(30)
)
begin
  insert into ciudadanos(id_zona,nombre,apellidos,dpi,telefono,email,direccion)
  values(p_id_zona,p_nombre,p_apellidos,p_dpi,p_telefono,p_email,p_direccion);
  select last_insert_id() as id_ciudadano;
end$$
delimiter ;

delimiter $$
create procedure sp_ciudadanos_read_all()
begin
  select * from ciudadanos order by id_ciudadano;
end$$
delimiter ;

delimiter $$
create procedure sp_ciudadanos_read_by_id(in p_id int)
begin
  select * from ciudadanos where id_ciudadano = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_ciudadanos_update(
  in p_id int, in p_id_zona int, in p_nombre varchar(30), in p_apellidos varchar(30),
  in p_telefono varchar(9), in p_email varchar(30), in p_direccion varchar(30)
)
begin
  update ciudadanos
  set id_zona = p_id_zona,
      nombre = p_nombre,
      apellidos = p_apellidos,
      telefono = p_telefono,
      email = p_email,
      direccion = p_direccion
  where id_ciudadano = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_ciudadanos_delete(in p_id int)
begin
  delete from ciudadanos where id_ciudadano = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== CONDUCTORES ==========
delimiter $$
create procedure sp_conductores_create(
  in p_id_ciudadano int, in p_tipoSangre varchar(10)
)
begin
  insert into conductores(id_ciudadano,tipoSangre) values(p_id_ciudadano,p_tipoSangre);
  select last_insert_id() as id_conductores;
end$$
delimiter ;

delimiter $$
create procedure sp_conductores_read_all()
begin
  select * from conductores order by id_conductores;
end$$
delimiter ;

delimiter $$
create procedure sp_conductores_read_by_id(in p_id int)
begin
  select * from conductores where id_conductores = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_conductores_update(
  in p_id int, in p_tipoSangre varchar(10)
)
begin
  update conductores set tipoSangre = p_tipoSangre where id_conductores = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_conductores_delete(in p_id int)
begin
  delete from conductores where id_conductores = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== LICENCIAS ==========
delimiter $$
create procedure sp_licencias_create(
  in p_id_conductores int,
  in p_categoria char(1),
  in p_fechaEmision date,
  in p_fechaVencimiento date,
  in p_estado varchar(10)
)
begin
  if p_categoria not in ('A','B','C','M','T') then
    signal sqlstate '45000' set message_text = 'categoria invalida';
  end if;

  if p_estado not in ('vigente','vencida','suspendida') then
    signal sqlstate '45000' set message_text = 'estado invalido';
  end if;

  if p_fechaEmision is null or p_fechaVencimiento is null or p_fechaEmision >= p_fechaVencimiento then
    signal sqlstate '45000' set message_text = 'rango de fechas invalido';
  end if;

  if (select count(*) from conductores where id_conductores = p_id_conductores) = 0 then
    signal sqlstate '45000' set message_text = 'conductor no existe';
  end if;

  insert into licencias(id_conductores,categoria,fechaEmision,fechaVencimiento,estado)
  values(p_id_conductores,p_categoria,p_fechaEmision,p_fechaVencimiento,p_estado);

  select last_insert_id() as id_licencia;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_read_all()
begin
  select * from licencias order by id_licencia;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_read_by_id(in p_id int)
begin
  select * from licencias where id_licencia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_read_by_conductor(in p_id_conductores int)
begin
  select * from licencias
  where id_conductores = p_id_conductores
  order by fechaVencimiento desc, id_licencia desc;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_update(
  in p_id int,
  in p_id_conductores int,
  in p_categoria char(1),
  in p_fechaEmision date,
  in p_fechaVencimiento date,
  in p_estado varchar(10)
)
begin
  if p_categoria not in ('A','B','C','M','T') then
    signal sqlstate '45000' set message_text = 'categoria invalida';
  end if;

  if p_estado not in ('vigente','vencida','suspendida') then
    signal sqlstate '45000' set message_text = 'estado invalido';
  end if;

  if p_fechaEmision is null or p_fechaVencimiento is null or p_fechaEmision >= p_fechaVencimiento then
    signal sqlstate '45000' set message_text = 'rango de fechas invalido';
  end if;

  if (select count(*) from conductores where id_conductores = p_id_conductores) = 0 then
    signal sqlstate '45000' set message_text = 'conductor no existe';
  end if;

  update licencias
  set id_conductores   = p_id_conductores,
      categoria        = p_categoria,
      fechaEmision     = p_fechaEmision,
      fechaVencimiento = p_fechaVencimiento,
      estado           = p_estado
  where id_licencia = p_id;

  select row_count() as filas_afectadas;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_delete(in p_id int)
begin
  delete from licencias where id_licencia = p_id;
  select row_count() as filas_afectadas;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_renovar(
  in p_id int,
  in p_nuevaEmision date,
  in p_nuevaVenc date
)
begin
  if p_nuevaEmision is null or p_nuevaVenc is null or p_nuevaEmision >= p_nuevaVenc then
    signal sqlstate '45000' set message_text = 'rango de fechas invalido';
  end if;

  update licencias
  set fechaEmision = p_nuevaEmision,
      fechaVencimiento = p_nuevaVenc,
      estado = 'vigente'
  where id_licencia = p_id;

  select row_count() as filas_afectadas;
end $$
delimiter ;

delimiter $$
create procedure sp_licencias_set_estado(
  in p_id int,
  in p_estado varchar(10)
)
begin
  if p_estado not in ('vigente','vencida','suspendida') then
    signal sqlstate '45000' set message_text = 'estado invalido';
  end if;

  update licencias
  set estado = p_estado
  where id_licencia = p_id;

  select row_count() as filas_afectadas;
end $$
delimiter ;

-- ========== AGENTES ==========
delimiter $$
create procedure sp_agentes_create(
  in p_codigo varchar(20), in p_nombre varchar(30), in p_apellido varchar(30),
  in p_telefono varchar(9), in p_id_zona int
)
begin
  insert into agenteTransito(codigo,nombre,apellido,telefono,id_zona)
  values(p_codigo,p_nombre,p_apellido,p_telefono,p_id_zona);
  select last_insert_id() as id_agente;
end$$
delimiter ;

delimiter $$
create procedure sp_agentes_read_all()
begin
  select * from agenteTransito order by id_agente;
end$$
delimiter ;

delimiter $$
create procedure sp_agentes_read_by_id(in p_id int)
begin
  select * from agenteTransito where id_agente = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_agentes_update(
  in p_id int, in p_codigo varchar(20), in p_nombre varchar(30), in p_apellido varchar(30),
  in p_telefono varchar(9), in p_id_zona int
)
begin
  update agenteTransito
  set codigo = p_codigo,
      nombre = p_nombre,
      apellido = p_apellido,
      telefono = p_telefono,
      id_zona = p_id_zona
  where id_agente = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_agentes_delete(in p_id int)
begin
  delete from agenteTransito where id_agente = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== INFRACCIONES ==========
delimiter $$
create procedure sp_infracciones_create(
  in p_codigo varchar(10), in p_descripcion varchar(200), in p_montoBase decimal(10,2)
)
begin
  insert into infracciones(codigo,descripcion,montoBase)
  values(p_codigo,p_descripcion,p_montoBase);
  select last_insert_id() as id_infracciones;
end$$
delimiter ;

delimiter $$
create procedure sp_infracciones_read_all()
begin
  select * from infracciones order by id_infracciones;
end$$
delimiter ;

delimiter $$
create procedure sp_infracciones_read_by_id(in p_id int)
begin
  select * from infracciones where id_infracciones = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_infracciones_update(
  in p_id int, in p_codigo varchar(10), in p_descripcion varchar(200), in p_montoBase decimal(10,2)
)
begin
  update infracciones
  set codigo = p_codigo,
      descripcion = p_descripcion,
      montoBase = p_montoBase
  where id_infracciones = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_infracciones_delete(in p_id int)
begin
  delete from infracciones where id_infracciones = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== MULTAS ==========
delimiter $$
create procedure sp_multas_create(
  in p_id_infracciones int, in p_id_conductores int, in p_id_vehiculo int,
  in p_id_agente int, in p_fecha date, in p_lugar varchar(120),
  in p_monto decimal(10,2), in p_estado enum('pendiente','pagada','anulada'),
  in p_observaciones varchar(200)
)
begin
  declare v_monto decimal(10,2);
  set v_monto = p_monto;
  if v_monto is null then
    select montoBase into v_monto from infracciones where id_infracciones = p_id_infracciones;
  end if;

  insert into multas(id_infracciones,id_conductores,id_vehiculo,id_agente,fecha,lugar,monto,estado,observaciones)
  values(p_id_infracciones,p_id_conductores,p_id_vehiculo,p_id_agente,p_fecha,p_lugar,v_monto,p_estado,p_observaciones);

  select last_insert_id() as id_multa;
end$$
delimiter ;

delimiter $$
create procedure sp_multas_read_all()
begin
  select * from multas order by id_multa desc;
end$$
delimiter ;

delimiter $$
create procedure sp_multas_read_by_id(in p_id int)
begin
  select * from multas where id_multa = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_multas_update(
  in p_id int,
  in p_fecha date, in p_lugar varchar(120),
  in p_monto decimal(10,2),
  in p_estado enum('pendiente','pagada','anulada'),
  in p_observaciones varchar(200)
)
begin
  update multas
  set fecha = p_fecha,
      lugar = p_lugar,
      monto = p_monto,
      estado = p_estado,
      observaciones = p_observaciones
  where id_multa = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_multas_delete(in p_id int)
begin
  delete from multas where id_multa = p_id;
  select row_count() as filas_afectadas;
end$$
delimiter ;

-- ========== PAGOS ==========
delimiter $$
create procedure sp_pagos_create(
  in p_id_multa int, in p_fechaPago date,
  in p_montoPagado decimal(10,2), in p_metodoPago decimal(10,2), in p_referencia varchar(40)
)
begin
  insert into pagos(id_multa,fechaPago,montoPagado,metodoPago,referencia)
  values(p_id_multa,p_fechaPago,p_montoPagado,p_metodoPago,p_referencia);
  select last_insert_id() as id_pago;
end$$
delimiter ;

delimiter $$
create procedure sp_pagos_read_all()
begin
  select * from pagos order by id_pago desc;
end$$
delimiter ;

delimiter $$
create procedure sp_pagos_read_by_id(in p_id int)
begin
  select * from pagos where id_pago = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_pagos_read_by_multa(in p_id_multa int)
begin
  select * from pagos where id_multa = p_id_multa order by fechaPago desc, id_pago desc;
end$$
delimiter ;

delimiter $$
create procedure sp_pagos_read_by_rango(in p_ini date, in p_fin date)
begin
  select * from pagos
  where fechaPago between p_ini and p_fin
  order by fechaPago desc, id_pago desc;
end$$
delimiter ;

-- ---------------------------------------
-- VISTAS
-- ---------------------------------------

-- 1) ciudadanos con su zona
create or replace view v_ciudadanos_zona as
select
  c.id_ciudadano, c.nombre, c.apellidos, c.dpi, c.telefono, c.email, c.direccion,
  z.id_zona, z.zona as nombre_zona
from ciudadanos c
join zonas z on z.id_zona = c.id_zona;

-- 2) conductores con datos del ciudadano y zona
create or replace view v_conductores_detalle as
select
  d.id_conductores,
  c.id_ciudadano, concat(c.nombre,' ',c.apellidos) as ciudadano,
  c.dpi, c.telefono, c.email,
  z.zona as nombre_zona,
  d.tipoSangre
from conductores d
join ciudadanos c on c.id_ciudadano = d.id_ciudadano
join zonas z on z.id_zona = c.id_zona;

-- 3) licencias con conductor/ciudadano
create or replace view v_licencias_detalle as
select
  l.id_licencia, l.categoria, l.fechaEmision, l.fechaVencimiento, l.estado,
  d.id_conductores,
  c.id_ciudadano, concat(c.nombre,' ',c.apellidos) as ciudadano, c.dpi
from licencias l
join conductores d on d.id_conductores = l.id_conductores
join ciudadanos c on c.id_ciudadano = d.id_ciudadano;

-- 4) licencias vigentes
create or replace view v_licencias_vigentes as
select * from v_licencias_detalle
where estado = 'vigente';

-- 5) vehiculos con propietario (ciudadano)
create or replace view v_vehiculos_propietarios as
select
  v.id_vehiculo, v.placa, v.marca, v.modelo, v.anio, v.color,
  c.id_ciudadano, concat(c.nombre,' ',c.apellidos) as propietario, c.dpi, c.telefono, c.email
from vehiculos v
join ciudadanos c on c.id_ciudadano = v.id_ciudadano;

-- 6) agentes con su zona
create or replace view v_agentes_zona as
select
  a.id_agente, a.codigo, a.nombre, a.apellido, a.telefono,
  z.id_zona, z.zona as nombre_zona
from agenteTransito a
join zonas z on z.id_zona = a.id_zona;

-- 7) detalle completo de multas
create or replace view v_multas_detalle as
select
  m.id_multa, m.fecha, m.lugar, m.monto, m.estado, m.observaciones,
  i.id_infracciones, i.codigo as codigo_infraccion, i.descripcion as infraccion, i.montoBase,
  v.id_vehiculo, v.placa, v.marca, v.modelo, v.anio, v.color,
  d.id_conductores,
  c.id_ciudadano, concat(c.nombre,' ',c.apellidos) as conductor, c.dpi,
  a.id_agente, a.codigo as codigo_agente, concat(a.nombre,' ',a.apellido) as agente
from multas m
join infracciones i on i.id_infracciones = m.id_infracciones
join vehiculos v on v.id_vehiculo = m.id_vehiculo
join conductores d on d.id_conductores = m.id_conductores
join ciudadanos c on c.id_ciudadano = d.id_ciudadano
join agenteTransito a on a.id_agente = m.id_agente;

-- 8) pagos con datos de la multa (y referencia de infracción/conductor)
create or replace view v_pagos_detalle as
select
  p.id_pago, p.id_multa, p.fechaPago, p.montoPagado, p.metodoPago, p.referencia,
  m.fecha as fecha_multa, m.monto as monto_multa, m.estado as estado_multa,
  i.codigo as codigo_infraccion, i.descripcion as infraccion
from pagos p
join multas m on m.id_multa = p.id_multa
join infracciones i on i.id_infracciones = m.id_infracciones;

-- 9) saldos por conductor (total multado, pagado y saldo)
create or replace view v_saldos_por_conductor as
select
  d.id_conductores,
  c.id_ciudadano, concat(c.nombre,' ',c.apellidos) as conductor, c.dpi,
  coalesce(sum(m.monto),0) as total_multas,
  coalesce((select sum(p2.montoPagado) from pagos p2 join multas m2 on m2.id_multa = p2.id_multa
            where m2.id_conductores = d.id_conductores),0) as total_pagado,
  coalesce(sum(m.monto),0) -
  coalesce((select sum(p2.montoPagado) from pagos p2 join multas m2 on m2.id_multa = p2.id_multa
            where m2.id_conductores = d.id_conductores),0) as saldo
from conductores d
join ciudadanos c on c.id_ciudadano = d.id_ciudadano
left join multas m on m.id_conductores = d.id_conductores
group by d.id_conductores, c.id_ciudadano, conductor, c.dpi;

-- 10) multas por zona del ciudadano (no por lugar textual)
create or replace view v_multas_por_zona as
select
  z.id_zona, z.zona as nombre_zona,
  count(m.id_multa) as cantidad_multas,
  coalesce(sum(m.monto),0) as total_monto
from multas m
join conductores d on d.id_conductores = m.id_conductores
join ciudadanos c on c.id_ciudadano = d.id_ciudadano
join zonas z on z.id_zona = c.id_zona
group by z.id_zona, z.zona;

-- 11) licencias por vencer en 60 días
create or replace view v_licencias_por_vencer_60d as
select *
from v_licencias_detalle
where estado = 'vigente'
  and datediff(fechaVencimiento, curdate()) between 0 and 60;

-- 12) resumen de pagos por multa
create or replace view v_resumen_pagos_por_multa as
select
  m.id_multa,
  count(p.id_pago) as pagos_realizados,
  coalesce(sum(p.montoPagado),0) as total_pagado,
  m.monto as monto_multa,
  (m.monto - coalesce(sum(p.montoPagado),0)) as saldo_pendiente
from multas m
left join pagos p on p.id_multa = m.id_multa
group by m.id_multa, m.monto;

-- ---------------------------------------
-- SELECT VIEWS
-- ---------------------------------------

-- select * from v_ciudadanos_zona;
-- select * from v_conductores_detalle;
-- select * from v_licencias_detalle;
-- select * from v_licencias_vigentes;
-- select * from v_vehiculos_propietarios;
-- select * from v_agentes_zona;
-- select * from v_multas_detalle;
-- select * from v_pagos_detalle;
-- select * from v_saldos_por_conductor;
-- select * from v_multas_por_zona;
-- select * from v_licencias_por_vencer_60d;
-- select * from v_resumen_pagos_por_multa;	