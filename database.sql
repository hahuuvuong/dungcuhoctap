USE [STUDY_SHOP]
GO
ALTER TABLE [dbo].[Products] DROP CONSTRAINT [FK__Products__CateId__15502E78]
GO
ALTER TABLE [dbo].[Carts] DROP CONSTRAINT [FK__Carts__UserId__182C9B23]
GO
ALTER TABLE [dbo].[CartItems] DROP CONSTRAINT [FK__CartItems__ProId__1B0907CE]
GO
ALTER TABLE [dbo].[CartItems] DROP CONSTRAINT [FK__CartItems__CartI__1BFD2C07]
GO
/****** Object:  Index [UQ__Users__536C85E4A2F8190E]    Script Date: 3/20/2020 3:36:27 PM ******/
ALTER TABLE [dbo].[Users] DROP CONSTRAINT [UQ__Users__536C85E4A2F8190E]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP TABLE [dbo].[Users]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP TABLE [dbo].[Products]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP TABLE [dbo].[Categories]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP TABLE [dbo].[Carts]
GO
/****** Object:  Table [dbo].[CartItems]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP TABLE [dbo].[CartItems]
GO
USE [master]
GO
/****** Object:  Database [STUDY_SHOP]    Script Date: 3/20/2020 3:36:27 PM ******/
DROP DATABASE [STUDY_SHOP]
GO
/****** Object:  Database [STUDY_SHOP]    Script Date: 3/20/2020 3:36:27 PM ******/
CREATE DATABASE [STUDY_SHOP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'STUDY_SHOP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\STUDY_SHOP.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'STUDY_SHOP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\STUDY_SHOP_log.ldf' , SIZE = 1088KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [STUDY_SHOP] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [STUDY_SHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [STUDY_SHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [STUDY_SHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [STUDY_SHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [STUDY_SHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET  ENABLE_BROKER 
GO
ALTER DATABASE [STUDY_SHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [STUDY_SHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET RECOVERY FULL 
GO
ALTER DATABASE [STUDY_SHOP] SET  MULTI_USER 
GO
ALTER DATABASE [STUDY_SHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [STUDY_SHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [STUDY_SHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [STUDY_SHOP] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'STUDY_SHOP', N'ON'
GO
USE [STUDY_SHOP]
GO
/****** Object:  Table [dbo].[CartItems]    Script Date: 3/20/2020 3:36:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartItems](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Quantity] [int] NULL,
	[UnitPrice] [float] NULL,
	[ProId] [int] NOT NULL,
	[CartId] [int] NOT NULL,
 CONSTRAINT [PK__CartItem__3214EC0704141BF6] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Carts]    Script Date: 3/20/2020 3:36:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[BuyDate] [date] NULL,
	[IsPaid] [int] NULL,
 CONSTRAINT [PK__Carts__3214EC0718967049] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Categories]    Script Date: 3/20/2020 3:36:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CateId] [int] IDENTITY(1,1) NOT NULL,
	[CateName] [nvarchar](255) NOT NULL,
	[CateImage] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[CateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 3/20/2020 3:36:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Price] [float] NULL,
	[Discount] [int] NULL,
	[CateId] [int] NOT NULL,
	[Description] [nvarchar](2000) NULL,
	[Information] [nvarchar](2000) NULL,
	[Image] [nvarchar](500) NULL,
 CONSTRAINT [PK__Products__3214EC078D14CE25] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 3/20/2020 3:36:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Phone] [nchar](11) NULL,
	[Address] [nvarchar](150) NULL,
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](36) NOT NULL,
	[Avatar] [nvarchar](50) NULL,
	[RoleId] [int] NOT NULL,
 CONSTRAINT [PK__Users__3214EC072ADE063C] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[CartItems] ON 

INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1, 3, 1, 3, 1)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (3, 1, 1, 22, 1)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1029, 2, NULL, 7, 1009)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1030, 1, NULL, 49, 1009)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1031, 1, NULL, 17, 1009)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1032, 3, NULL, 11, 1010)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1033, 2, NULL, 34, 1011)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1034, 2, NULL, 11, 1011)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1035, 1, NULL, 40, 1011)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1036, 4, NULL, 11, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1037, 4, NULL, 7, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (1038, 1, NULL, 58, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2036, 2, NULL, 11, 1013)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2037, 1, NULL, 43, 1013)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2039, 3, NULL, 56, 1013)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2041, 1, NULL, 26, 1013)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2042, 1, NULL, 12, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2045, 4, NULL, 11, 1015)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2046, 3, NULL, 10, 1012)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2047, 3, NULL, 11, 1014)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2048, 1, NULL, 49, 1014)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2050, 1, NULL, 18, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2051, 2, NULL, 79, 3)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2052, 3, NULL, 44, 1019)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2053, 2, NULL, 48, 1019)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2054, 3, NULL, 34, 1020)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2055, 2, NULL, 49, 1020)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2056, 1, NULL, 7, 1020)
INSERT [dbo].[CartItems] ([Id], [Quantity], [UnitPrice], [ProId], [CartId]) VALUES (2057, 2, NULL, 52, 1020)
SET IDENTITY_INSERT [dbo].[CartItems] OFF
SET IDENTITY_INSERT [dbo].[Carts] ON 

INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1, 2, CAST(0x9D3F0B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (3, 2, CAST(0xC43F0B00 AS Date), 2)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1009, 2040, CAST(0x5F400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1010, 2040, CAST(0x5F400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1011, 2040, CAST(0x5F400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1012, 2040, CAST(0x5F400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1013, 2041, CAST(0x60400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1014, 2041, CAST(0x60400B00 AS Date), 3)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1015, 2042, CAST(0x66400B00 AS Date), 3)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1016, 2042, CAST(0x69400B00 AS Date), 0)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1018, 2041, CAST(0x82400B00 AS Date), 0)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1019, 2040, CAST(0x82400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1020, 2040, CAST(0x82400B00 AS Date), 1)
INSERT [dbo].[Carts] ([Id], [UserId], [BuyDate], [IsPaid]) VALUES (1021, 2040, CAST(0x85400B00 AS Date), 0)
SET IDENTITY_INSERT [dbo].[Carts] OFF
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CateId], [CateName], [CateImage]) VALUES (1, N'Sổ Vở', NULL)
INSERT [dbo].[Categories] ([CateId], [CateName], [CateImage]) VALUES (2, N'Bút', NULL)
INSERT [dbo].[Categories] ([CateId], [CateName], [CateImage]) VALUES (3, N'Hộp Bút', NULL)
INSERT [dbo].[Categories] ([CateId], [CateName], [CateImage]) VALUES (4, N'Thước', NULL)
SET IDENTITY_INSERT [dbo].[Categories] OFF
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (3, N'Sổ Vở  Tyranosauras', 20000, 30, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092231_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (4, N'Sổ Vở Pink Feather Softfy', 50000, 30, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092125_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (7, N'Sổ Vở Bơ Baby', 30000, 15, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19090239_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (8, N'Sổ vở Summer', 25000, 0, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092173_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (9, N'Sổ vở Hello Cat', 25000, 5, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19091049_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (10, N'Sổ vở Halo Mouse', 17000, 0, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19091048_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (11, N'Sổ vở Cherry', 15000, 20, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080411_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (12, N'Sổ vở Mili Mouse', 21000, 0, 1, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Sổ Vở Study được thiết kế từ nguyên liệu thân thiện với môi trường, an toàn cho trẻ em. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080371_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (17, N'Bút viết bấm Shushi', 16000, 15, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19091196_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (18, N'Bút Narwal Mập', 12000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092222_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (21, N'Bút Thỏ Ngọc', 15000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19090051__thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (22, N'Bút Cầu vồng', 16000, 10, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19081132_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (23, N'Bút nhớ dòng Cloud Puppy', 18000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080340_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (24, N'Bút Xóa Khô ', 19000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19091002_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (25, N'Bút Dumbo Elephan', 12000, 10, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080181_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (26, N'Bút Hoa Anh Đào', 15000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19072052_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (28, N'Bút Unicon Trong Suốt', 18000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070016_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (29, N'Bút Carrot', 15000, 15, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19051167_CA_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (30, N'Bút Chì Kim', 18000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19062164_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (31, N'Bút Treo Thỏ Carrot ', 10000, 0, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19050471_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (32, N'Bút Meow', 18000, 10, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Bút Viết Study với những mẫu mã hot nhất thị trường dành cho các bạn tuổi teen. Study Shop, đồng hành cùng tương lai Việt!', N'images/19062032_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (33, N'Thước Kẻ Chân Mèo ', 10000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080364_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (34, N'Thước Kẻ Trái Bơ', 14000, 10, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092276_GN_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (35, N'Thước Kẻ Meow Flower', 15000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092277_BU_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (36, N'Thước Kẻ Hoa Anh Đào ', 13000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080363_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (37, N'Thước Kẻ Scream', 12000, 15, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070119_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (38, N'Thước Kẻ Carrot', 10000, 20, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/1805468_rd_thumb_600x600.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (39, N'Thước Kẻ Xương Rồng', 19000, 10, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/1806506_xx_thumb_600x600.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (40, N'Thước Kẻ Carrot Cartoon', 20000, 10, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/1805467_ca_thumb_600x600.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (41, N'Thước Kẻ Bơ', 12000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070242_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (42, N'Thước Kẻ Cartoon idol', 19000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070117_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (43, N'Thước Kẻ Hoa Xương Rồng ', 25000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/m_c105_no_1804757_xx_thumb_600x600.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (44, N'Thước Kẻ Gấu Mèo', 20000, 25, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092277_BU_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (45, N'Thước Kẻ Hải Cẩu', 15000, 20, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19062032_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (46, N'Thước Kẻ Strawberry', 15000, 0, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/19092275_PK_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (47, N'Thước Kẻ Fruit Pola', 10000, 30, 4, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Thước Kẻ Study trân trọng những giá trị của tuổi thơ con em bạn. Study Shop, đồng hành cùng tương lai Việt!', N'images/1805219_xx_thumb_600x600.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (48, N'Hộp Bút Bơ', 30000, 0, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19091263_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (49, N'Hộp Bút Magic Moon', 60000, 10, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19081101_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (51, N'Hộp Bút Infinity', 60000, 20, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19081100_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (52, N'Hộp Bút Tyrannosaurus', 45000, 15, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19081094_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (53, N'Hộp Bút Pig', 50000, 25, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19081099_XX_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (54, N'Hộp Bút Pig Planet', 40000, 0, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070339_NV_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (55, N'Hộp Bút Art Color', 30000, 0, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19072220_XX1_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (56, N'Hộp Bút Peach Flower', 35000, 10, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19080156_HOc_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (57, N'Hộp Bút Vegetable', 40000, 0, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19082038_XX1_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (58, N'Hộp Bút Cute', 25000, 5, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19070298_HOc_thumb_350x350.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (62, N'Hộp Bút Color ', 54000, 20, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Hộp Bút Study chứa tất cả yêu thương. Study Shop, đồng hành cùng tương lai Việt!', N'images/19072224_XX_1000x1000.jpg')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (68, N'Bút Bơ ', 25000, 10, 2, N'Sản Phẩm Yêu Thích', N'Nhà Cung Cấp: Bút Moji', N'//cdn.nhanh.vn/cdn/store/7534/ps/20191014/19102055_GN_thumb_350x350.JPG')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (78, N'Bút Love Kitten', 25000, 10, 2, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Nhà Cung Cấp: Bút Moji', N'//cdn.nhanh.vn/cdn/store/7534/ps/20191111/19112006_XX_thumb_350x350.JPG')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (79, N'Pencil Case', 50000, 10, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Nhà Cung Cấp: Hộp Bút Moji', N'//cdn.nhanh.vn/cdn/store/7534/ps/20191204/19110363_XX_thumb_350x350.JPG')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Discount], [CateId], [Description], [Information], [Image]) VALUES (80, N'Hộp Bút', 10000, 10, 3, N'Sản phẩm thuộc bộ sưu tập thiết kế riêng của Study Shop. Đi học luôn vui vì đã có Study đây rồi!', N'Nhà Cung Cấp: Hộp Bút Moji', N'//cdn.nhanh.vn/cdn/store/7534/ps/20191204/19110363_XX_thumb_350x350.JPG')
SET IDENTITY_INSERT [dbo].[Products] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Address], [Username], [Password], [Avatar], [RoleId]) VALUES (1, N'Nguyễn Văn A', N'nguyenvana@gmail.com', N'034589612  ', N'Bến Thành, Quận 1', N'nguyenvana', N'123456', NULL, 1)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Address], [Username], [Password], [Avatar], [RoleId]) VALUES (2, N'Nguyễn Thị Bình', N'nguyenthibinh@gmail.com', N'021489566  ', N'Trần Bình Trọng, quận 5', N'nguyenthibinh', N'125', NULL, 0)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Address], [Username], [Password], [Avatar], [RoleId]) VALUES (2040, N'Nguyễn Trần Hải Âu ', N'nguyentranhaiau5502@gmail.com', N'0147823596 ', N'Bảo Lâm, Lâm Đồng', N'nguyentranhaiyen223@gmail.com', N'1258', NULL, 0)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Address], [Username], [Password], [Avatar], [RoleId]) VALUES (2041, N'Trần Nhật Anh Thư', N'anhthu@gmail.com', N'0147823596 ', N'Đức Linh, Bình Thuận', N'anhthu45', N'1258', NULL, 0)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Address], [Username], [Password], [Avatar], [RoleId]) VALUES (2042, N'dobatoan', N'dobatoant28@gmail.com', N'01647392111', N'97-ManThien', N'toando', N'87939898Tt', NULL, 0)
SET IDENTITY_INSERT [dbo].[Users] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Users__536C85E4A2F8190E]    Script Date: 3/20/2020 3:36:27 PM ******/
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [UQ__Users__536C85E4A2F8190E] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CartItems]  WITH CHECK ADD  CONSTRAINT [FK__CartItems__CartI__1BFD2C07] FOREIGN KEY([CartId])
REFERENCES [dbo].[Carts] ([Id])
GO
ALTER TABLE [dbo].[CartItems] CHECK CONSTRAINT [FK__CartItems__CartI__1BFD2C07]
GO
ALTER TABLE [dbo].[CartItems]  WITH CHECK ADD  CONSTRAINT [FK__CartItems__ProId__1B0907CE] FOREIGN KEY([ProId])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[CartItems] CHECK CONSTRAINT [FK__CartItems__ProId__1B0907CE]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK__Carts__UserId__182C9B23] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK__Carts__UserId__182C9B23]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK__Products__CateId__15502E78] FOREIGN KEY([CateId])
REFERENCES [dbo].[Categories] ([CateId])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK__Products__CateId__15502E78]
GO
USE [master]
GO
ALTER DATABASE [STUDY_SHOP] SET  READ_WRITE 
GO
