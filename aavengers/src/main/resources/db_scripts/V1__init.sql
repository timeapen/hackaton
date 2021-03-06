
CREATE TABLE ACHD_App (
     id bigint auto_increment,
     Bank_Cd_KEY   char (8) NOT NULL,
     Acct_Num_KEY   char (35) NOT NULL,
     Loc_Sec_Num_KEY   char (12) NOT NULL,
     Depository_KEY   char (4) NOT NULL,
     Registration_KEY   char (4) NOT NULL,
     Pos_Type_Rsn_KEY   char (4) NOT NULL,
     Loc_Num_Sys_KEY   char (2) NOT NULL,
     ACS_Short_Term_ID_KEY   char (9) NOT NULL,
     ACS_S_T_ID8   char (8) NULL,
     Account_Ccy   char (3) NULL,
     Account_Group   char (8) NULL,
     Account_Name   char (40) NULL,
     Accrued_Int_Amt   float  NULL,
     Acct_Mnem   char (8) NULL,
     Alpha_Face_Val   char (6) NULL,
     Asset_On_Loan   char (1) NULL,
     Asset_Type   char (40) NULL,
     As_Of_Date   datetime  NULL,
     Branch_Cd   char (3) NULL,
     Ccy_of_Issue   char (3) NULL,
     Client_Sec_Num   char (12) NULL,
     Cntry_Cd_Rpt   char (3) NULL,
     Daily_Loc_Ccy_Price   float  NULL,
     Daily_Price_Date   datetime  NULL,
     Date_Holding   datetime  NULL,
     Date_Last_Chg   datetime  NULL,
     Delete_Ind   char (1) NULL,
     Depository_Location_Desc   char (45) NULL,
     Hub_Product_Code   char (8) NULL,
     Hub_Related_Prod_Code   char (8) NULL,
     Inc_Ccy_Cd   char (3) NULL,
     Inc_Fre_Cd   char (2) NULL,
     Interest_Rate   float  NULL,
     ISIN_Num   char (12) NULL,
     Issuer_Industry   char (35) NULL,
     Issuer_Type   char (40) NULL,
     Issue_Cntry_Prov_Cd   char (3) NULL,
     Issue_Date   datetime  NULL,
     Maturity_Date   datetime  NULL,
     Nom_Face_Val   float  NULL,
     Non_Resident_Tax_Rate   float  NULL,
     Price_Ccy   char (3) NULL,
     Price_Source   char (25) NULL,
     Prop_Minor_Cd   char (40) NULL,
     Registration_Description   char (45) NULL,
     Rev_Col_Desc   char (40) NULL,
     Rpt_Type_C_H   char (1) NULL,
     Security_Cd   char (12) NULL,
     Security_Description   char (45) NULL,
     Base_Ccy   char (3) NULL,
     Stock_Exch_Cd   char (8) NULL,
     Tot_Shares_Outstanding   float  NULL,
     Txn_Type   char (6) NULL,
     Pos_Qty   float  NULL,
     Settled_Base_Ccy_Mkt_Val   float  NULL,
     Settled_Acct_Ccy_Mkt_Val   float  NULL,
     Settled_Base_Ccy_Book_Val   float  NULL,
     Settled_Acct_Ccy_Book_Val   float  NULL,
     Traded_Pos_Qty   float  NULL,
     Traded_Base_Ccy_Mkt_Val   float  NULL,
     Traded_Acct_Ccy_Mkt_Val   float  NULL,
     Traded_Base_Ccy_Book_Val   float  NULL,
     Traded_Acct_Ccy_Book_Val   float  NULL,
     Acct_Ccy_Desc   char (50) NULL,
     Bank_Desc   char (35) NULL,
     Settled_MarketVal_AcctCcy   char (3) NULL,
     Settled_MarketVal_BaseCcy   char (3) NULL,
     Settled_BookVal_AcctCcy   char (3) NULL,
     Settled_BookVal_BaseCcy   char (3) NULL,
     Traded_MarketVal_AcctCcy   char (3) NULL,
     Traded_MarketVal_BaseCcy   char (3) NULL,
     Traded_BookVal_AcctCcy   char (3) NULL,
     Traded_BookVal_BaseCcy   char (3) NULL,
     Message_Id   numeric (16, 0) NOT NULL,
     UserGroup   char (8) NOT NULL,
     Delete_Flag   char (1) NULL,
     DateLoaded   smalldatetime  NULL,
     ArchiveTimeStamp   datetime  NOT NULL
);

CREATE TABLE ACHS_App (
    id bigint auto_increment,
     Bank_Cd_KEY   char (8) NOT NULL,
     Acct_Num_KEY   char (35) NOT NULL,
     Loc_Sec_Num_KEY   char (12) NOT NULL,
     Loc_Num_Sys_KEY   char (2) NOT NULL,
     ACS_Short_Term_ID_KEY   char (9) NOT NULL,
     Account_Ccy   char (3) NULL,
     Account_Group   char (8) NULL,
     Account_Name   char (40) NULL,
     Accrued_Int_Amt   float  NULL,
     Acct_Mnem   char (8) NULL,
     ACS_S_T_ID8   char (8) NOT NULL,
     Alpha_Face_Val   char (6) NULL,
     Asset_On_Loan   char (1) NULL,
     Asset_Type   char (40) NULL,
     As_Of_Date   datetime  NULL,
     Branch_Cd   char (3) NULL,
     Ccy_of_Issue   char (3) NULL,
     Client_Sec_Num   char (12) NULL,
     Cntry_Cd_Rpt   char (3) NULL,
     Daily_Loc_Ccy_Price   float  NULL,
     Daily_Price_Date   datetime  NULL,
     Date_Holding   datetime  NULL,
     Date_Last_Chg   datetime  NULL,
     Depository_KEY   char (4) NULL,
     Depository_Location_Desc   char (45) NULL,
     Hub_Product_Code   char (8) NULL,
     Hub_Related_Prod_Code   char (8) NULL,
     Interest_Rate   float  NULL,
     ISIN_Num   char (12) NULL,
     Issuer_Industry   char (35) NULL,
     Issuer_Type   char (40) NULL,
     Issue_Cntry_Prov_Cd   char (3) NULL,
     Issue_Date   datetime  NULL,
     Maturity_Date   datetime  NULL,
     Nom_Face_Val   float  NULL,
     Non_Resident_Tax_Rate   float  NULL,
     Pos_Type_Rsn_KEY   char (4) NOT NULL,
     Price_Ccy   char (3) NULL,
     Price_Source   char (25) NULL,
     Prop_Minor_Cd   char (40) NULL,
     Registration_Description   char (45) NULL,
     Registration_KEY   char (4) NULL,
     Rev_Col_Desc   char (40) NULL,
     Rpt_Type_C_H   char (1) NULL,
     Security_Cd   char (12) NULL,
     Security_Description   char (45) NULL,
     Base_Ccy   char (3) NULL,
     Stock_Exch_Cd   char (8) NULL,
     Tot_Shares_Outstanding   float  NULL,
     Txn_Type   char (6) NULL,
     Pos_Qty   float  NULL,
     Settled_Base_Ccy_Mkt_Val   float  NULL,
     Settled_Acct_Ccy_Mkt_Val   float  NULL,
     Settled_Base_Ccy_Book_Val   float  NULL,
     Settled_Acct_Ccy_Book_Val   float  NULL,
     Traded_Pos_Qty   float  NULL,
     Traded_Base_Ccy_Mkt_Val   float  NULL,
     Traded_Acct_Ccy_Mkt_Val   float  NULL,
     Traded_Base_Ccy_Book_Val   float  NULL,
     Traded_Acct_Ccy_Book_Val   float  NULL,
     Master_KEY   char (70) NULL,
     Acct_Ccy_Desc   char (50) NULL,
     Bank_Desc   char (35) NULL,
     Settled_Accrued_Income   float  NULL,
     Traded_Accrued_Income   float  NULL,
     Settled_MarketVal_AcctCcy   char (3) NULL,
     Settled_MarketVal_BaseCcy   char (3) NULL,
     Settled_BookVal_AcctCcy   char (3) NULL,
     Settled_BookVal_BaseCcy   char (3) NULL,
     Traded_MarketVal_AcctCcy   char (3) NULL,
     Traded_MarketVal_BaseCcy   char (3) NULL,
     Traded_BookVal_AcctCcy   char (3) NULL,
     Traded_BookVal_BaseCcy   char (3) NULL,
     Message_Id   decimal (16, 0) NOT NULL,
     UserGroup   char (8) NOT NULL,
     Delete_Flag   char (1) NOT NULL,
     DateLoaded   smalldatetime  NULL,
     ArchiveTimeStamp   datetime  NOT NULL
);

SET MODE MSSQLServer;

INSERT INTO ACHS_App (Bank_Cd_KEY, Acct_Num_KEY, Loc_Sec_Num_KEY, Loc_Num_Sys_KEY, ACS_Short_Term_ID_KEY, Account_Ccy, Account_Group, Account_Name,Accrued_Int_Amt, Acct_Mnem, ACS_S_T_ID8, Alpha_Face_Val, Asset_On_Loan, Asset_Type, As_Of_Date, Branch_Cd, Ccy_of_Issue, Client_Sec_Num, Cntry_Cd_Rpt,Daily_Loc_Ccy_Price, Daily_Price_Date, Date_Holding, Date_Last_Chg, Depository_KEY, Depository_Location_Desc, Hub_Related_Prod_Code, Hub_Product_Code,Interest_Rate, ISIN_Num, Issuer_Industry, Issuer_Type, Issue_Cntry_Prov_Cd, Issue_Date, Maturity_Date, Nom_Face_Val, Non_Resident_Tax_Rate,
Pos_Type_Rsn_KEY, Price_Ccy, Price_Source, Prop_Minor_Cd, Registration_Description, Registration_KEY, Rev_Col_Desc, Rpt_Type_C_H, Security_Cd,Security_Description, Base_Ccy, Stock_Exch_Cd, Tot_Shares_Outstanding, Txn_Type, Pos_Qty, Settled_Base_Ccy_Mkt_Val, Settled_Acct_Ccy_Mkt_Val,Settled_Base_Ccy_Book_Val, Settled_Acct_Ccy_Book_Val, Traded_Pos_Qty, Traded_Base_Ccy_Mkt_Val, Traded_Acct_Ccy_Mkt_Val, Traded_Base_Ccy_Book_Val,
Traded_Acct_Ccy_Book_Val, Master_KEY, Acct_Ccy_Desc, Bank_Desc, Settled_Accrued_Income, Traded_Accrued_Income, Settled_MarketVal_AcctCcy,
Settled_MarketVal_BaseCcy, Settled_BookVal_AcctCcy, Settled_BookVal_BaseCcy, Traded_MarketVal_AcctCcy, Traded_MarketVal_BaseCcy,Traded_BookVal_AcctCcy, Traded_BookVal_BaseCcy, Message_Id, UserGroup, Delete_Flag, DateLoaded, ArchiveTimeStamp) 
select *, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() from CSVREAD( 'classpath:db_scripts/ACHS.csv', 'Bank_Cd_KEY, Acct_Num_KEY, Loc_Sec_Num_KEY, Loc_Num_Sys_KEY, ACS_Short_Term_ID_KEY, Account_Ccy, Account_Group, Account_Name,
Accrued_Int_Amt, Acct_Mnem, ACS_S_T_ID8, Alpha_Face_Val, Asset_On_Loan, Asset_Type, As_Of_Date, Branch_Cd, Ccy_of_Issue, Client_Sec_Num, Cntry_Cd_Rpt,
Daily_Loc_Ccy_Price, Daily_Price_Date, Date_Holding, Date_Last_Chg, Depository_KEY, Depository_Location_Desc, Hub_Related_Prod_Code, Hub_Product_Code,
Interest_Rate, ISIN_Num, Issuer_Industry, Issuer_Type, Issue_Cntry_Prov_Cd, Issue_Date, Maturity_Date, Nom_Face_Val, Non_Resident_Tax_Rate,
Pos_Type_Rsn_KEY, Price_Ccy, Price_Source, Prop_Minor_Cd, Registration_Description, Registration_KEY, Rev_Col_Desc, Rpt_Type_C_H, Security_Cd,
Security_Description, Base_Ccy, Stock_Exch_Cd, Tot_Shares_Outstanding, Txn_Type, Pos_Qty, Settled_Base_Ccy_Mkt_Val, Settled_Acct_Ccy_Mkt_Val,
Settled_Base_Ccy_Book_Val, Settled_Acct_Ccy_Book_Val, Traded_Pos_Qty, Traded_Base_Ccy_Mkt_Val, Traded_Acct_Ccy_Mkt_Val, Traded_Base_Ccy_Book_Val,
Traded_Acct_Ccy_Book_Val, Master_KEY, Acct_Ccy_Desc, Bank_Desc, Settled_Accrued_Income, Traded_Accrued_Income, Settled_MarketVal_AcctCcy,
Settled_MarketVal_BaseCcy, Settled_BookVal_AcctCcy, Settled_BookVal_BaseCcy, Traded_MarketVal_AcctCcy, Traded_MarketVal_BaseCcy,
Traded_BookVal_AcctCcy, Traded_BookVal_BaseCcy, Message_Id, UserGroup, Delete_Flag', null );


INSERT INTO ACHD_App (Bank_Cd_KEY,Acct_Num_KEY,Loc_Sec_Num_KEY,Depository_KEY,Registration_KEY,Pos_Type_Rsn_KEY,Loc_Num_Sys_KEY,ACS_Short_Term_ID_KEY,ACS_S_T_ID8,Account_Ccy,Account_Group,Account_Name,Accrued_Int_Amt,Acct_Mnem,Alpha_Face_Val,Asset_On_Loan,Asset_Type,As_Of_Date,Branch_Cd,Ccy_of_Issue,Client_Sec_Num,Cntry_Cd_Rpt,Daily_Loc_Ccy_Price,Daily_Price_Date,Date_Holding,Date_Last_Chg,Delete_Ind,Depository_Location_Desc,Hub_Product_Code,Hub_Related_Prod_Code,Inc_Ccy_Cd,Inc_Fre_Cd,Interest_Rate,ISIN_Num,Issuer_Industry,Issuer_Type,Issue_Cntry_Prov_Cd,Issue_Date,Maturity_Date,Nom_Face_Val,Non_Resident_Tax_Rate,Price_Ccy,Price_Source,Prop_Minor_Cd,Registration_Description,Rev_Col_Desc,Rpt_Type_C_H,Security_Cd,Security_Description,Base_Ccy,Stock_Exch_Cd,Tot_Shares_Outstanding,Txn_Type,Pos_Qty,Settled_Base_Ccy_Mkt_Val,Settled_Acct_Ccy_Mkt_Val,Settled_Base_Ccy_Book_Val,Settled_Acct_Ccy_Book_Val,Traded_Pos_Qty,Traded_Base_Ccy_Mkt_Val,Traded_Acct_Ccy_Mkt_Val,Traded_Base_Ccy_Book_Val,Traded_Acct_Ccy_Book_Val,Acct_Ccy_Desc,Bank_Desc,Settled_MarketVal_AcctCcy,Settled_MarketVal_BaseCcy,Settled_BookVal_AcctCcy,Settled_BookVal_BaseCcy,Traded_MarketVal_AcctCcy,Traded_MarketVal_BaseCcy,Traded_BookVal_AcctCcy,Traded_BookVal_BaseCcy,Message_Id,UserGroup,Delete_Flag, DateLoaded, ArchiveTimeStamp)
select *, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() from CSVREAD( 'classpath:db_scripts/ACHD.csv', 'Bank_Cd_KEY,Acct_Num_KEY,Loc_Sec_Num_KEY,Depository_KEY,Registration_KEY,Pos_Type_Rsn_KEY,Loc_Num_Sys_KEY,ACS_Short_Term_ID_KEY,ACS_S_T_ID8,Account_Ccy,Account_Group,Account_Name,Accrued_Int_Amt,Acct_Mnem,Alpha_Face_Val,Asset_On_Loan,Asset_Type,As_Of_Date,Branch_Cd,Ccy_of_Issue,Client_Sec_Num,Cntry_Cd_Rpt,Daily_Loc_Ccy_Price,Daily_Price_Date,Date_Holding,Date_Last_Chg,Delete_Ind,Depository_Location_Desc,Hub_Product_Code,Hub_Related_Prod_Code,Inc_Ccy_Cd,Inc_Fre_Cd,Interest_Rate,ISIN_Num,Issuer_Industry,Issuer_Type,Issue_Cntry_Prov_Cd,Issue_Date,Maturity_Date,Nom_Face_Val,Non_Resident_Tax_Rate,Price_Ccy,Price_Source,Prop_Minor_Cd,Registration_Description,Rev_Col_Desc,Rpt_Type_C_H,Security_Cd,Security_Description,Base_Ccy,Stock_Exch_Cd,Tot_Shares_Outstanding,Txn_Type,Pos_Qty,Settled_Base_Ccy_Mkt_Val,Settled_Acct_Ccy_Mkt_Val,Settled_Base_Ccy_Book_Val,Settled_Acct_Ccy_Book_Val,Traded_Pos_Qty,Traded_Base_Ccy_Mkt_Val,Traded_Acct_Ccy_Mkt_Val,Traded_Base_Ccy_Book_Val,Traded_Acct_Ccy_Book_Val,Acct_Ccy_Desc,Bank_Desc,Settled_MarketVal_AcctCcy,Settled_MarketVal_BaseCcy,Settled_BookVal_AcctCcy,Settled_BookVal_BaseCcy,Traded_MarketVal_AcctCcy,Traded_MarketVal_BaseCcy,Traded_BookVal_AcctCcy,Traded_BookVal_BaseCcy,Message_Id,UserGroup,Delete_Flag', null );