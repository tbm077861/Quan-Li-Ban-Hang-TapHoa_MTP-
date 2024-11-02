USE [DB_QLBH]
GO

/****** Object:  Table [dbo].[TaiKhoanNV]    Script Date: 11/2/2024 2:58:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


