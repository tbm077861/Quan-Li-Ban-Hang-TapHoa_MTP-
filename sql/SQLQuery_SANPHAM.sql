USE [DB_QLBH]
GO

/****** Object:  Table [dbo].[DanhSachSanPham]    Script Date: 11/13/2024 8:19:44 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DanhSachSanPham](
	[MaHang] [nvarchar](50) NOT NULL,
	[TenHang] [nvarchar](50) NULL,
	[MoTa] [nvarchar](50) NULL,
	[DonGia] [int] NULL,
	[SoLuong] [int] NULL,
	[NgaySanXuat] [date] NULL,
 CONSTRAINT [PK_DanhSachSanPham] PRIMARY KEY CLUSTERED 
(
	[MaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


