USE [DB_QLBH]
GO

/****** Object:  Table [dbo].[DanhSachSanPham]    Script Date: 11/6/2024 8:51:14 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DanhSachSanPham](
	[MaHang] [nvarchar](50) NULL,
	[TenHang] [nvarchar](50) NULL,
	[MoTa] [nvarchar](50) NULL,
	[DonGia] [int] NULL,
	[SoLuong] [int] NULL,
	[NgaySanXuat] [nvarchar](50) NULL
) ON [PRIMARY]
GO


