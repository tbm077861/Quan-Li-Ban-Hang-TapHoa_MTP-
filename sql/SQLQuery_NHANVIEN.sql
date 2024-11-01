USE [master]
GO

/****** Object:  Database [DB_QLBH]    Script Date: 02/11/2024 10:55:36 SA ******/
CREATE DATABASE [DB_QLBH]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DB_QLBH', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DB_QLBH.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DB_QLBH_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DB_QLBH_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DB_QLBH].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [DB_QLBH] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [DB_QLBH] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [DB_QLBH] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [DB_QLBH] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [DB_QLBH] SET ARITHABORT OFF 
GO

ALTER DATABASE [DB_QLBH] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [DB_QLBH] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [DB_QLBH] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [DB_QLBH] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [DB_QLBH] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [DB_QLBH] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [DB_QLBH] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [DB_QLBH] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [DB_QLBH] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [DB_QLBH] SET  DISABLE_BROKER 
GO

ALTER DATABASE [DB_QLBH] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [DB_QLBH] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [DB_QLBH] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [DB_QLBH] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [DB_QLBH] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [DB_QLBH] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [DB_QLBH] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [DB_QLBH] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [DB_QLBH] SET  MULTI_USER 
GO

ALTER DATABASE [DB_QLBH] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [DB_QLBH] SET DB_CHAINING OFF 
GO

ALTER DATABASE [DB_QLBH] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [DB_QLBH] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [DB_QLBH] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [DB_QLBH] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO

ALTER DATABASE [DB_QLBH] SET QUERY_STORE = ON
GO

ALTER DATABASE [DB_QLBH] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO

ALTER DATABASE [DB_QLBH] SET  READ_WRITE 
GO


USE [DB_QLBH]
GO

/****** Object:  Table [dbo].[TaiKhoanNV]    Script Date: 02/11/2024 10:55:51 SA ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TaiKhoanNV](
	[MANV] [varchar](10) NOT NULL,
	[HoTen] [nvarchar](100) NOT NULL,
	[NgaySinh] [varchar](10) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[GioiTinh] [nvarchar](10) NOT NULL,
	[CCCD] [nvarchar](200) NOT NULL,
	[MatKhau] [varchar](100) NOT NULL,
	[ChucVu] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

