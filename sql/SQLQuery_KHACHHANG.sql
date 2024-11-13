USE [DB_QLBH]
GO

/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/13/2024 8:20:12 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](50) NOT NULL,
	[tenKhachHang] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
	[dienThoai] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[ngaySinh] [date] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


