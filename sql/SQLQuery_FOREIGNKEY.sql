USE [DB_QLBH]
GO

-- 1. First drop existing foreign keys if they exist
IF EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ChiTietHD_DanhSachSanPham]') AND parent_object_id = OBJECT_ID(N'[dbo].[ChiTietHD]'))
    ALTER TABLE [dbo].[ChiTietHD] DROP CONSTRAINT [FK_ChiTietHD_DanhSachSanPham]
GO

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_HoaDon_TaiKhoanNV]') AND parent_object_id = OBJECT_ID(N'[dbo].[HoaDon]'))
    ALTER TABLE [dbo].[HoaDon] DROP CONSTRAINT [FK_HoaDon_TaiKhoanNV]
GO

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_HoaDon_KhachHang]') AND parent_object_id = OBJECT_ID(N'[dbo].[HoaDon]'))
    ALTER TABLE [dbo].[HoaDon] DROP CONSTRAINT [FK_HoaDon_KhachHang]
GO

IF EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ChiTietHD_HoaDon]') AND parent_object_id = OBJECT_ID(N'[dbo].[ChiTietHD]'))
    ALTER TABLE [dbo].[ChiTietHD] DROP CONSTRAINT [FK_ChiTietHD_HoaDon]
GO

-- 2. Drop and recreate primary keys
-- Drop existing primary key on HoaDon
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PK_HoaDon]') AND type = 'PK')
    ALTER TABLE [dbo].[HoaDon] DROP CONSTRAINT [PK_HoaDon]
GO

-- Recreate primary key on HoaDon with just MaHD
ALTER TABLE [dbo].[HoaDon]
ADD CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED ([MaHD])
GO

-- 3. Now add all foreign key constraints

-- Add Foreign Key for ChiTietHD.MaHang -> DanhSachSanPham.MaHang
ALTER TABLE [dbo].[ChiTietHD] 
ADD CONSTRAINT [FK_ChiTietHD_DanhSachSanPham] 
FOREIGN KEY ([MaHang]) REFERENCES [dbo].[DanhSachSanPham]([MaHang])
GO

-- Add Foreign Key for HoaDon.MANV -> TaiKhoanNV.MANV
ALTER TABLE [dbo].[HoaDon] 
ADD CONSTRAINT [FK_HoaDon_TaiKhoanNV] 
FOREIGN KEY ([MANV]) REFERENCES [dbo].[TaiKhoanNV]([MANV])
GO

-- Add Foreign Key for HoaDon.maKhachHang -> KhachHang.maKhachHang
ALTER TABLE [dbo].[HoaDon] 
ADD CONSTRAINT [FK_HoaDon_KhachHang] 
FOREIGN KEY ([maKhachHang]) REFERENCES [dbo].[KhachHang]([maKhachHang])
GO

-- Add Foreign Key for ChiTietHD.MaHD -> HoaDon.MaHD
ALTER TABLE [dbo].[ChiTietHD] 
ADD CONSTRAINT [FK_ChiTietHD_HoaDon] 
FOREIGN KEY ([MaHD]) REFERENCES [dbo].[HoaDon]([MaHD])
GO