USE ett;

-- Usuarios en activo
SELECT
	u.nombre AS nombre_usuario,
	e.nombre AS nombre_empresa,
	ue.fecha_inicio
FROM
	usuario AS u,
	empresa AS e,
	usuario_empresa AS ue
WHERE
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	ue.fecha_fin IS NULL
ORDER BY u.id LIMIT 500;

-- Dias en la ultima empresa
SELECT
	u.nombre AS nombre_usuario,
	e.nombre AS nombre_empresa,
	ue.fecha_inicio,
	DATEDIFF(NOW(), ue.fecha_inicio) AS dias_trabajando
FROM
	usuario AS u,
	empresa AS e,
	usuario_empresa AS ue
WHERE
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	ue.fecha_fin IS NULL;

-- Usuarios desempleados
SELECT 
	u.nombre AS nombre_usuario,
	e.nombre AS nombre_empresa,
	ue.fecha_fin AS fecha_desempleo

FROM 		
	usuario AS u,
	usuario_empresa AS ue,
	empresa AS e	

WHERE 
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	u.id NOT IN (
		SELECT ue.id_usuario FROM usuario_empresa AS ue WHERE ue.fecha_fin IS NULL
		) AND
	fecha_fin = (
		SELECT MAX(fecha_fin)
		FROM usuario_empresa as uee
		WHERE uee.id_usuario = u.id -- une esta select con la otra select
		);
	
-- Dias desempleados
SELECT 
	u.nombre AS nombre_usuario,
	e.nombre AS nombre_empresa,
	ue.fecha_fin AS fecha_desempleo,
	DATEDIFF(NOW(), ue.fecha_fin) AS dias_desempleado

FROM 		
	usuario AS u,
	usuario_empresa AS ue,
	empresa AS e	

WHERE 
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	u.id NOT IN (
		SELECT ue.id_usuario FROM usuario_empresa AS ue WHERE ue.fecha_fin IS NULL
		) AND
	fecha_fin = (
		SELECT MAX(fecha_fin)
		FROM usuario_empresa as uee
		WHERE uee.id_usuario = u.id
		);
		
-- Contratos de mas de un año
SELECT
	u.nombre AS nombre_usuario,
	e.nombre AS nombre_empresa,
	IF (ue.fecha_fin IS NULL, DATEDIFF(NOW(), ue.fecha_inicio), DATEDIFF(ue.fecha_fin, ue.fecha_inicio)) AS dias_contrato
	
FROM
	usuario AS u,
	empresa AS e,
	usuario_empresa AS ue
WHERE
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	IF (ue.fecha_fin IS NULL, DATEDIFF(NOW(), ue.fecha_inicio), DATEDIFF(ue.fecha_fin, ue.fecha_inicio)) > 365;
	
-- Empresas con trabajadores en activo
SELECT
	DISTINCT (e.nombre) AS nombre_empresa
FROM
	empresa AS e,
	usuario_empresa AS ue
WHERE
	e.id = ue.id_empresa AND
	ue.fecha_fin IS NULL;
	