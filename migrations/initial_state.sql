USE master
GO

CREATE DATABASE FinanFX
GO

USE [FinanFX]
GO
/****** Object:  Table [dbo].[Cuentas_Financieras]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cuentas_Financieras](
	[ID_Cuenta] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NULL,
	[Tipo_Cuenta] [varchar](50) NULL,
	[Numero_Cuenta] [varbinary](500) NOT NULL,
	[Nombre_Banco] [varchar](100) NULL,
	[Saldo_Inicial] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Cuenta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Numero_Cuenta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notas_Transacciones]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notas_Transacciones](
	[ID_Nota] [int] IDENTITY(1,1) NOT NULL,
	[ID_Transaccion] [int] NULL,
	[Nota] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Nota] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Presupuestos]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Presupuestos](
	[ID_Presupuesto] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NULL,
	[Periodo] [varchar](20) NULL,
	[Categoria] [varchar](50) NULL,
	[Monto_Presupuestado] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Presupuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transacciones]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transacciones](
	[ID_Transaccion] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NULL,
	[Tipo_Transaccion] [varchar](20) NULL,
	[Cantidad] [decimal](18, 2) NULL,
	[Fecha] [date] NULL,
	[Categoria] [varchar](50) NULL,
	[Descripcion] [varchar](100) NULL,
	[Forma_Pago] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Transaccion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[ID_Usuario] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NULL,
	[Apellido] [varchar](50) NULL,
	[Fecha_Nacimiento] [date] NULL,
	[Email] [varchar](100) NOT NULL,
	[Password] [varbinary](500) NULL,
	[Telefono] [varbinary](500) NULL,
	[Estado] [char](1) NOT NULL,
	[Fecha_Registro] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Usuarios] ADD  DEFAULT (getdate()) FOR [Fecha_Registro]
GO
ALTER TABLE [dbo].[Cuentas_Financieras]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Cuentas_Financieras] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuarios] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Cuentas_Financieras] CHECK CONSTRAINT [FK_Usuarios_Cuentas_Financieras]
GO
ALTER TABLE [dbo].[Notas_Transacciones]  WITH CHECK ADD  CONSTRAINT [FK_Transacciones_Notas_Transacciones] FOREIGN KEY([ID_Transaccion])
REFERENCES [dbo].[Transacciones] ([ID_Transaccion])
GO
ALTER TABLE [dbo].[Notas_Transacciones] CHECK CONSTRAINT [FK_Transacciones_Notas_Transacciones]
GO
ALTER TABLE [dbo].[Presupuestos]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Presupuestos] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuarios] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Presupuestos] CHECK CONSTRAINT [FK_Usuarios_Presupuestos]
GO
ALTER TABLE [dbo].[Transacciones]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Transacciones] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuarios] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Transacciones] CHECK CONSTRAINT [FK_Usuarios_Transacciones]
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarCuentaFinanciera]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para actualizar los detalles de una cuenta financiera existente
CREATE PROCEDURE [dbo].[SP_ActualizarCuentaFinanciera]
    @ID_Cuenta INT,
    @Tipo_Cuenta VARCHAR(50),
    @Numero_Cuenta VARCHAR(50),
    @Nombre_Banco VARCHAR(100),
    @Saldo_Inicial DECIMAL(18, 2),
	@Patron VARCHAR(100)
AS
BEGIN
	DECLARE @CuentaEncriptada VARBINARY(500);		
	SET @CuentaEncriptada = ENCRYPTBYPASSPHRASE(@Patron, @Numero_Cuenta);

    UPDATE Cuentas_Financieras
    SET Tipo_Cuenta = @Tipo_Cuenta,
        Numero_Cuenta = @CuentaEncriptada,
        Nombre_Banco = @Nombre_Banco,
        Saldo_Inicial = @Saldo_Inicial
    WHERE ID_Cuenta = @ID_Cuenta;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarNotaTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para actualizar los detalles de una nota de transacción existente
CREATE PROCEDURE [dbo].[SP_ActualizarNotaTransaccion]
    @ID_Nota INT,
    @Nota TEXT
AS
BEGIN
    UPDATE Notas_Transacciones
    SET Nota = @Nota
    WHERE ID_Nota = @ID_Nota;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarPresupuesto]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para actualizar los detalles de un presupuesto existente
CREATE PROCEDURE [dbo].[SP_ActualizarPresupuesto]
    @ID_Presupuesto INT,
    @Periodo VARCHAR(20),
    @Categoria VARCHAR(50),
    @Monto_Presupuestado DECIMAL(18, 2)
AS
BEGIN
    UPDATE Presupuestos
    SET Periodo = @Periodo,
        Categoria = @Categoria,
        Monto_Presupuestado = @Monto_Presupuestado
    WHERE ID_Presupuesto = @ID_Presupuesto;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para actualizar los detalles de una transacción existente
CREATE PROCEDURE [dbo].[SP_ActualizarTransaccion]
    @ID_Transaccion INT,
    @Tipo_Transaccion VARCHAR(20),
    @Cantidad DECIMAL(18, 2),
    @Fecha DATE,
    @Categoria VARCHAR(50),
    @Descripcion VARCHAR(100),
    @Forma_Pago VARCHAR(50)
AS
BEGIN
    UPDATE Transacciones
    SET Tipo_Transaccion = @Tipo_Transaccion,
        Cantidad = @Cantidad,
        Fecha = @Fecha,
        Categoria = @Categoria,
        Descripcion = @Descripcion,
        Forma_Pago = @Forma_Pago
    WHERE ID_Transaccion = @ID_Transaccion;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ActualizarUsuario]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- SP para modificar usuario
CREATE PROCEDURE [dbo].[SP_ActualizarUsuario]
    @ID_Usuario INT,
    @Nombre VARCHAR(50),
    @Apellido VARCHAR(50),
    @FechaNacimiento DATE,
    @Telefono VARCHAR(25),
	@Estado CHAR(1),
    @Patron VARCHAR(100)
AS
BEGIN
    DECLARE @TelefonoEncriptado VARBINARY(500);

    SET @TelefonoEncriptado = ENCRYPTBYPASSPHRASE(@Patron, @Telefono);

    UPDATE Usuarios 
    SET Nombre = @Nombre,
        Apellido = @Apellido,
        Fecha_Nacimiento = @FechaNacimiento,
        Telefono = @TelefonoEncriptado,
		Estado = @Estado
    WHERE ID_Usuario = @ID_Usuario;

    IF @@ROWCOUNT > 0
        SELECT 1 AS Modificado;    -- Indicar que se realizó la modificación
    ELSE
        SELECT 0 AS Modificado;    -- Indicar que no se encontró el usuario para modificar
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_CrearCuentaFinanciera]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_CrearCuentaFinanciera]
    @ID_Usuario INT,
    @Tipo_Cuenta VARCHAR(50),
    @Numero_Cuenta VARCHAR(50),
    @Nombre_Banco VARCHAR(100),
    @Saldo_Inicial DECIMAL(18, 2),
	@Patron VARCHAR(100)
AS
BEGIN
	DECLARE @CuentaEncriptada VARBINARY(500);		
	SET @CuentaEncriptada = ENCRYPTBYPASSPHRASE(@Patron, @Numero_Cuenta);

    INSERT INTO Cuentas_Financieras (ID_Usuario, Tipo_Cuenta, Numero_Cuenta, Nombre_Banco, Saldo_Inicial)
    VALUES (@ID_Usuario, @Tipo_Cuenta, @CuentaEncriptada, @Nombre_Banco, @Saldo_Inicial);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_CrearNotaTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para insertar una nueva nota asociada a una transacción
CREATE PROCEDURE [dbo].[SP_CrearNotaTransaccion]
    @ID_Transaccion INT,
    @Nota TEXT
AS
BEGIN
    INSERT INTO Notas_Transacciones (ID_Transaccion, Nota)
    VALUES (@ID_Transaccion, @Nota);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_CrearTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- SP para crear una nueva transacción
CREATE PROCEDURE [dbo].[SP_CrearTransaccion]
    @ID_Usuario INT,
    @Tipo_Transaccion VARCHAR(20),
    @Cantidad DECIMAL(18, 2),
    @Fecha DATE,
    @Categoria VARCHAR(50),
    @Descripcion VARCHAR(100),
    @Forma_Pago VARCHAR(50)
AS
BEGIN
    INSERT INTO Transacciones (ID_Usuario, Tipo_Transaccion, Cantidad, Fecha, Categoria, Descripcion, Forma_Pago)
    VALUES (@ID_Usuario, @Tipo_Transaccion, @Cantidad, @Fecha, @Categoria, @Descripcion, @Forma_Pago);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_CrearUsuario]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


	-- SP para insertar usuario
	CREATE PROCEDURE [dbo].[SP_CrearUsuario]
		@Nombre VARCHAR(50),
		@Apellido VARCHAR(50),
		@FechaNacimiento DATE,
		@Email VARCHAR(100),
		@Password VARCHAR(25),
		@Telefono VARCHAR(25),
		@Estado CHAR(1),
		@Patron VARCHAR(100)
	AS
	BEGIN
		DECLARE @PasswordEncriptado VARBINARY(500);
		DECLARE @TelefonoEncriptado VARBINARY(500);
		DECLARE @new_id INT;
		
		SET @PasswordEncriptado = ENCRYPTBYPASSPHRASE(@Patron, @Password);
		SET @TelefonoEncriptado = ENCRYPTBYPASSPHRASE(@Patron, @Telefono);

		INSERT INTO Usuarios (Nombre, Apellido, Fecha_Nacimiento, Email, Password, Telefono, Estado)
		VALUES (
			@Nombre, 
			@Apellido,
			@FechaNacimiento, 
			@Email, 
			@PasswordEncriptado, 
			@TelefonoEncriptado,
			@Estado
		);

		SET @new_id = SCOPE_IDENTITY();
		
		IF @new_id IS NOT NULL
			SELECT @new_id AS ID_Usuario;			
		ELSE
			SELECT 0 AS ID_Usuario;
	END;
GO
/****** Object:  StoredProcedure [dbo].[SP_EliminarCuentaFinanciera]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para eliminar una cuenta financiera por su ID
CREATE PROCEDURE [dbo].[SP_EliminarCuentaFinanciera]
    @ID_Cuenta INT
AS
BEGIN
    DELETE FROM Cuentas_Financieras 
    WHERE ID_Cuenta = @ID_Cuenta;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_EliminarNotaTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para eliminar una nota de transacción por su ID
CREATE PROCEDURE [dbo].[SP_EliminarNotaTransaccion]
    @ID_Nota INT
AS
BEGIN
    DELETE FROM Notas_Transacciones 
    WHERE ID_Nota = @ID_Nota;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_EliminarPresupuesto]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para eliminar un presupuesto por su ID
CREATE PROCEDURE [dbo].[SP_EliminarPresupuesto]
    @ID_Presupuesto INT
AS
BEGIN
    DELETE FROM Presupuestos 
    WHERE ID_Presupuesto = @ID_Presupuesto;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_EliminarTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para eliminar una transacción por su ID
CREATE PROCEDURE [dbo].[SP_EliminarTransaccion]
    @ID_Transaccion INT
AS
BEGIN
    DELETE FROM Transacciones 
    WHERE ID_Transaccion = @ID_Transaccion;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_InsertarPresupuesto]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- SP para insertar un nuevo presupuesto
CREATE PROCEDURE [dbo].[SP_InsertarPresupuesto]
    @ID_Usuario INT,
    @Periodo VARCHAR(20),
    @Categoria VARCHAR(50),
    @Monto_Presupuestado DECIMAL(18, 2)
AS
BEGIN
    INSERT INTO Presupuestos (ID_Usuario, Periodo, Categoria, Monto_Presupuestado)
    VALUES (@ID_Usuario, @Periodo, @Categoria, @Monto_Presupuestado);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_LeerUsuario]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para leer usuario
CREATE PROCEDURE [dbo].[SP_LeerUsuario]
    @ID_Usuario INT,
    @Patron VARCHAR(100)
AS
BEGIN
    SELECT 
        ID_Usuario,
        Nombre,
        Apellido,
        Fecha_Nacimiento,
        Email,
		CONVERT(VARCHAR(25), DECRYPTBYPASSPHRASE(@Patron, Telefono)) AS Telefono,
        Estado,
        Fecha_Registro
    FROM 
        Usuarios
    WHERE 
        ID_Usuario = @ID_Usuario;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ListarNotasTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para listar todas las notas asociadas a una transacción
CREATE PROCEDURE [dbo].[SP_ListarNotasTransaccion]
    @ID_Transaccion INT
AS
BEGIN
    SELECT *
    FROM Notas_Transacciones
    WHERE ID_Transaccion = @ID_Transaccion;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ListarPresupuestosUsuario]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para listar todos los presupuestos asociados a un usuario
CREATE PROCEDURE [dbo].[SP_ListarPresupuestosUsuario]
    @ID_Usuario INT
AS
BEGIN
    SELECT *
    FROM Presupuestos
    WHERE ID_Usuario = @ID_Usuario;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ListarTransacciones]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para listar transacciones por ID de usuario
CREATE PROCEDURE [dbo].[SP_ListarTransacciones]
    @ID_Usuario INT
AS
BEGIN
    SELECT *
    FROM Transacciones
    WHERE ID_Usuario = @ID_Usuario;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ObtenerCuentaFinanciera]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para obtener los detalles de una cuenta financiera por su ID
CREATE PROCEDURE [dbo].[SP_ObtenerCuentaFinanciera]
    @ID_Cuenta INT,
	@Patron VARCHAR(100)
AS
BEGIN
    SELECT 
		ID_Usuario, 
		Tipo_Cuenta, 
		CONVERT(VARCHAR(25), DECRYPTBYPASSPHRASE(@Patron, Numero_Cuenta)) AS Numero_Cuenta,
		Nombre_Banco, Saldo_Inicial 
    FROM Cuentas_Financieras 
    WHERE ID_Cuenta = @ID_Cuenta;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ObtenerNotaTransaccion]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


-- SP para obtener los detalles de una nota de transacción por su ID
CREATE PROCEDURE [dbo].[SP_ObtenerNotaTransaccion]
    @ID_Nota INT
AS
BEGIN
    SELECT * 
    FROM Notas_Transacciones 
    WHERE ID_Nota = @ID_Nota;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ObtenerPresupuesto]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para obtener los detalles de un presupuesto por su ID
CREATE PROCEDURE [dbo].[SP_ObtenerPresupuesto]
    @ID_Presupuesto INT
AS
BEGIN
    SELECT * 
    FROM Presupuestos 
    WHERE ID_Presupuesto = @ID_Presupuesto;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ObtenerTransaccionPorId]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para obtener los detalles de una transacción por su ID
CREATE PROCEDURE [dbo].[SP_ObtenerTransaccionPorId]
    @ID_Transaccion INT
AS
BEGIN
    SELECT * 
    FROM Transacciones 
    WHERE ID_Transaccion = @ID_Transaccion;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ResetearContrasena]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ResetearContrasena]
    @Email VARCHAR(100),
    @PasswordActual VARCHAR(25),
    @NuevaContrasena VARCHAR(25),
    @VerificarContrasena VARCHAR(25),
    @Patron VARCHAR(100)
AS
BEGIN
	DECLARE @ID_Usuario INT;
	IF @NuevaContrasena = @VerificarContrasena
		BEGIN
			SELECT @ID_Usuario = ID_Usuario
			FROM Usuarios
			WHERE Email = @Email AND CONVERT(VARCHAR(100), DECRYPTBYPASSPHRASE(@Patron, Password)) = @PasswordActual;
    
			IF @ID_Usuario IS NOT NULL
				BEGIN
					DECLARE @NuevaContrasenaEncriptada VARBINARY(500);
					SET @NuevaContrasenaEncriptada = ENCRYPTBYPASSPHRASE(@Patron, @NuevaContrasena);

					UPDATE Usuarios
					SET Password = @NuevaContrasenaEncriptada
					WHERE ID_Usuario = @ID_Usuario;

					SELECT 'Contraseña actualizada exitosamente.' AS message, 'exito' as tipo;
				END
		ELSE
			BEGIN
				SELECT 'Las contraseñas no coinciden.' AS message, 'error' as tipo; 
			END;
		END
	ELSE
		BEGIN 
			SELECT 'Contraseña actual inválida.' AS message, 'error' as tipo;
		END;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_ValidarUsuario]    Script Date: 4/15/2024 12:09:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- SP para validar usuario
CREATE PROCEDURE [dbo].[SP_ValidarUsuario]
    @Email VARCHAR(100),
    @Password VARCHAR(25),
    @Patron VARCHAR(100)
AS
BEGIN
    DECLARE @ID_Usuario INT;

    SELECT @ID_Usuario = ID_Usuario
	FROM Usuarios
    WHERE Email = @Email AND CONVERT(VARCHAR(25), DECRYPTBYPASSPHRASE(@Patron, Password)) = @Password;

    IF @ID_Usuario IS NOT NULL
		BEGIN
			SELECT @ID_Usuario AS ID_Usuario;
		END
    ELSE
		BEGIN
			SELECT 0 AS ID_Usuario;
		END;
END;
GO
