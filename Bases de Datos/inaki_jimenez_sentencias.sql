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

-- Usuarios desempleados
SELECT
	u.nombre AS nombre_usuario,
	ue.fecha_fin AS fecha_desempleo
FROM
	usuario AS u,
	empresa AS e,
	usuario_empresa AS ue
WHERE
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	ue.fecha_fin IS NOT NULL
GROUP BY u.id;

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
	
-- Dias desempleados
SELECT
	u.nombre AS nombre_usuario,
	ue.fecha_fin AS fecha_desempleo,
	DATEDIFF(NOW(), ue.fecha_fin) AS dias_desempleado
FROM
	usuario AS u,
	empresa AS e,
	usuario_empresa AS ue
WHERE
	u.id = ue.id_usuario AND
	e.id = ue.id_empresa AND
	ue.fecha_fin IS NOT NULL
GROUP BY u.id;

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
	