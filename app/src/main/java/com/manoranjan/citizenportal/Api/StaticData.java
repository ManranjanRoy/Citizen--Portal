package com.manoranjan.citizenportal.Api;



import android.graphics.Bitmap;
import android.net.Uri;

import com.manoranjan.citizenportal.Response.InsertTLResponse;
import com.manoranjan.citizenportal.model.FileListModel;
import com.manoranjan.citizenportal.model.FilesNamepathListModel;
import com.manoranjan.citizenportal.model.NatureoftradeModel;
import com.manoranjan.citizenportal.model.Profile;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;

import java.io.File;
import java.util.List;

public class StaticData {

    public static Profile profile=null;
    public static String citizen_id="",emailid="";

    public static int notificationcount=0,notificationsize=0;
    //formdata
    public static String fcompanytype,fname,fcompanyname,fnumber,femail,fdob,fgender="MALE";
    public static String froadname,flocalityname,fdistrict,fstate,fpincode;
    public static String fcardtype="PAN CARD",fcardnumber,ffileurl;
    public static  boolean prevstat=false;

    public static String tapplytype="New",tholdingno,tholding_req="Y",townername,tstreetaddress,tlocality,twardno="1",ttypeoftax="Property Tax",tammountoftax,
            typeofrelation="1";
    public static String ttypeofbusinessid="1",tfortheyearid="1",tnameoffirm,tdateofcommenence,tinvestmentcapital,
            ttradetype="Small",tgstin,tcompanypancard,tcontactnofirst,tcontactaddress,tworkshopaddress,tgodownaddress;
   public static List<TradeNatureModel> tradeNatureModels=null;
    public static List<TypeofBusinessListModel> typeofBusinessListModelList=null;
    public static List<File> files=null;
    public static List<FileListModel> fileslist=null;
    public static List<FilesNamepathListModel> finalfilesNamepathListModelList=null;
    public static Uri uri1,uri2,uri3,uri4,uri5;
    public static String path1="",path2="",path3="",path4="",path5="";
    public static Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5;

    public static  InsertTLResponse insertTLResponse=null;
//forsingletradedetails
    public static String form_id="Form No";
    public static String regx_no="Regx id";
    public static String tl_id="1 id";

}
