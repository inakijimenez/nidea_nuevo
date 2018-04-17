USE `nidea`;
-- SELECT id, nombre, precio FROM `material`;

-- materiales por nombre asc
-- SELECT `id`, `nombre`, `precio` FROM `material` ORDER BY `nombre` ASC;

-- materiales por nombre de A-Z
-- SELECT `id`, `nombre`, `precio` FROM `material` ORDER BY `nombre` ASC;

-- materiales por precio de menor a mayor y al reves
-- SELECT `id`, `nombre`, `precio` FROM `material` ORDER BY `precio` DESC;
-- SELECT `id`, `nombre`, `precio` FROM `material` ORDER BY `precio` ASC;ç

-- contar todos los materiales
-- SELECT COUNT(*) as `total` from `material`;

-- contar materiales del usuario administrador
-- SELECT COUNT(*) as `total` from `material` WHERE `id_usuario`=1;

-- contar los materiales con precio superior a 2€
-- SELECT COUNT(*) as `total` from `material` WHERE `precio`>2;

-- contar el numero de usuarios que han creado materiales
-- SELECT COUNT(DISTINCT `id_usuario`) as `total_usuarios` from `material`;

-- materiales de mayor a menor cuyo precio sea mayor a 2 y < 25
-- SELECT `id`, `nombre`, `precio` from `material` WHERE `precio`>2 AND `precio`<25 ORDER BY `precio` DESC;

-- añadir nuevos materiales que han sido creado por espinete pass 123456
-- gomaespuma 13.69
-- velcro 9.99

-- INSERT INTO `usuario` (`nombre`, `password`, `id_rol`) VALUES ('espinete', 123456, 2);
-- INSERT INTO `material` (`nombre` , `precio`, `id_usuario`) VALUES ('gomaespuma', 13.69, 3);
-- INSERT INTO `material` (`nombre` , `precio`, `id_usuario`) VALUES ('velcro', 9.99, 3);

-- modificar todos los precios redondeados a .99
-- UPDATE `material` SET `precio` = (SELECT FLOOR(`precio`)) + 0.99;

-- update para subir el precio 10% a todos los materiales
-- UPDATE `material` SET `precio` = ROUND (`precio` + ((`precio` * 10) / 100) , 2);

-- crear usuario_eliminar y luego eliminarlo por id
-- INSERT INTO `usuario` (`nombre`, `password`, `id_rol`) VALUES ('usuario_eliminar', 123456, 2);
-- DELETE FROM `usuario` WHERE `id` = (SELECT MAX(id) FROM usuario);

-- eliminar todos los materiales con valor<=2
-- DELETE FROM `material` WHERE `precio` <= 2;

SELECT 
 	m.nombre as material, 
	m.precio as precio, 
	u.nombre as usuario, 
	r.nombre as rol 
FROM 
	material as m, 
	usuario as u, 
	rol as r
WHERE 
	m.id_usuario = u.id AND 
	u.id_rol = r.id;

-- listado de todos los usuarios ordenados de forma asc por nombre y que se vea el rol
SELECT 
	u.nombre as usuario,
	r.nombre as rol
FROM 
	usuario as u,
	rol as r
WHERE
	u.id_rol = r.id
ORDER BY u.nombre ASC;

-- listado de todos los materiales con nombre, precio y el nombre del usuario
SELECT
	m.nombre as material,
	m.precio,
	u.nombre as usuario
FROM
	material as m,
	usuario as u
WHERE 
	m.id_usuario = u.id;

