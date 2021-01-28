package com.manoranjan.citizenportal.Api;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manoranjan.citizenportal.model.FilesNamepathListModel;
import com.manoranjan.citizenportal.model.TradeNatureModel;
import com.manoranjan.citizenportal.model.TypeofBusinessListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RequestData {

    public RequestData() {
    }

    public static JsonObject login(String email, String password) {
        JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("username", email);
            paramObject.put("password", password);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(paramObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "signin");
            jsonObject.put("spname", "USP_LOGIN");
            jsonObject.put("json", jsonArray);
            Log.d("jsondata", jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject signup(String fname, String companyname, String mobileno, String email, String password, String otp, String ctype) throws JSONException {

        final JSONObject paramObject = new JSONObject();
        paramObject.put("User_Type", ctype);
        paramObject.put("Name", fname);
        paramObject.put("CompanyName", companyname);
        paramObject.put("EmailId", email);
        paramObject.put("MobileNo", mobileno);
        paramObject.put("HashMap", "");
        paramObject.put("Password", password);
        paramObject.put("OTP", otp);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(paramObject);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", "registeruser");
        jsonObject.put("spname", "USP_LOGIN");
        jsonObject.put("json", jsonArray);

        Log.d("jsondata", jsonObject.toString());
        JsonObject obj = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        obj = (JsonObject) jsonParser.parse(jsonObject.toString());

        return obj;
    }

    public static JsonObject getprofile(String email) {
        JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("email", email);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(paramObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "getProfile");
            jsonObject.put("spname", "USP_LOGIN");
            jsonObject.put("json", jsonArray);
            Log.d("jsondata", jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject updateprofile() {
        JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("dob", StaticData.fdob);
            paramObject.put("Gender", StaticData.fgender);
            paramObject.put("Road_Name", StaticData.froadname);
            paramObject.put("Locality_Name", StaticData.flocalityname);
            paramObject.put("District", StaticData.fdistrict);
            paramObject.put("State", StaticData.fstate);
            paramObject.put("Pin_Code", StaticData.fpincode);
            paramObject.put("IDProof_Name", StaticData.fcardtype);
            paramObject.put("IDProof_Number", StaticData.fcardnumber);
            paramObject.put("IDProofFile_Path", StaticData.ffileurl);
            paramObject.put("email", StaticData.femail);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(paramObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "updateProfile");
            jsonObject.put("spname", "USP_LOGIN");
            jsonObject.put("json", jsonArray);
            Log.d("jsondata", jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getward() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getWard");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject gettypesofland() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getLandNature");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject gettypeofbusiness() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTradeNature");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getfortheyear() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getFINYR");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getnatureoftrade() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTradeType");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getdocumentlist(String yesorno) {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getDoctypes");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("TL_Type", yesorno);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject inserttradeform(List<FilesNamepathListModel> filesNamepathListModelsnew) {
        JsonObject obj = new JsonObject();
        final JSONObject paramObject = new JSONObject();
        final JSONObject jsonTLAppForm=new JSONObject();
        final JSONObject jsonTLAppFollowupEmp=new JSONObject();
        try {
            //
            paramObject.put("TL_ID", 1);
            paramObject.put("Assessee_Holding_No", StaticData.tholdingno);
            paramObject.put("Property_Owner_Name", StaticData.townername);
            paramObject.put("Ward", StaticData.twardno);
            paramObject.put("Street_Address", StaticData.tstreetaddress);
            paramObject.put("Locality_Name", StaticData.tlocality);
            paramObject.put("Tax_Paid_Type", StaticData.ttypeoftax);
            paramObject.put("Tax_Paid_Amount", StaticData.tammountoftax);
            paramObject.put("Land_Nature_Id", StaticData.typeofrelation);
            paramObject.put("Trade_Nature_Id", StaticData.ttypeofbusinessid);
            paramObject.put("Name_Org", StaticData.tnameoffirm);
            paramObject.put("GST_IN", StaticData.tgstin);
            paramObject.put("Company_PAN", StaticData.tcompanypancard);
            paramObject.put("Capital", StaticData.tinvestmentcapital);
            paramObject.put("Con_add", StaticData.tcontactaddress);
            paramObject.put("Con_No", StaticData.tcontactnofirst);
            paramObject.put("Workshop", StaticData.tworkshopaddress);
            paramObject.put("Godown", StaticData.tgodownaddress);
            paramObject.put("Regnx", null);
            paramObject.put("Ptax_no", null);
            paramObject.put("Remarks", null);
            paramObject.put("E_Cityzen_ID", StaticData.citizen_id);
            paramObject.put("User_ID", 0);
            paramObject.put("Appl_By", StaticData.emailid);
            paramObject.put("regn_118", null);
            paramObject.put("regn_201", null);

            jsonTLAppForm.put("TL_Form_Id",1);
            jsonTLAppForm.put("TL_ID",1);
            jsonTLAppForm.put("Form_No",null);
            jsonTLAppForm.put("Fin_Year_ID",StaticData.tfortheyearid);
            jsonTLAppForm.put("TL_Type","N");
            jsonTLAppForm.put("Ward_ID",StaticData.twardno);
            jsonTLAppForm.put("Street",StaticData.tstreetaddress);
            jsonTLAppForm.put("Holding_No",StaticData.tholdingno);
            jsonTLAppForm.put("Tax_Status",null);
            jsonTLAppForm.put("Land_Nature_Id",StaticData.typeofrelation);//typesofland
            jsonTLAppForm.put("Applied_On",StaticData.tdateofcommenence);
            jsonTLAppForm.put("Status","1");

            jsonTLAppFollowupEmp.put("Followup_Id",1);
            jsonTLAppFollowupEmp.put("TL_Form_Id",0);
            jsonTLAppFollowupEmp.put("Form_Status","PAYMENT PENDING");
            jsonTLAppFollowupEmp.put("Followup_Date",null);
            jsonTLAppFollowupEmp.put("Sent_To_Date",null);
            jsonTLAppFollowupEmp.put("Remarks","PAYMENT PENDING");
            jsonTLAppFollowupEmp.put("Followup_By_E_Cityzen_ID",StaticData.citizen_id);
            jsonTLAppFollowupEmp.put("Followup_By_User_ID",0);
            jsonTLAppFollowupEmp.put("Followup_By_User_Type","Cityzen");
            jsonTLAppFollowupEmp.put("Next_Followup_E_Cityzen_ID",StaticData.citizen_id);//typesofland
            jsonTLAppFollowupEmp.put("Next_Followup_User_ID",0);
            jsonTLAppFollowupEmp.put("Next_Followup_User_Type","Cityzen");
            jsonTLAppForm.put("Is_Used","N");

            JSONArray jsonArrayfortradenaturetype = new JSONArray();
            if (StaticData.tradeNatureModels.size()>0) {
                for (int i = 0; i < StaticData.tradeNatureModels.size(); i++) {
                    final JSONObject jsonTLAppTradeType = new JSONObject();
                    TradeNatureModel tradeNatureModel = StaticData.tradeNatureModels.get(i);
                    jsonTLAppTradeType.put("Txn_Id", 1);
                    jsonTLAppTradeType.put("TL_ID", 1);
                    jsonTLAppTradeType.put("Trade_id", tradeNatureModel.getName());
                    jsonTLAppTradeType.put("Trade_Size", tradeNatureModel.getType());
                    jsonArrayfortradenaturetype.put(jsonTLAppTradeType);
                }
            }else{
                    Log.d("jsondatainsert","no datanature");

            }
            JSONArray jsonTLAppOwnerarray = new JSONArray();
            if (StaticData.typeofBusinessListModelList.size()>0) {
            for(int i=0;i<StaticData.typeofBusinessListModelList.size();i++){
                final  JSONObject jsonTLAppOwner=new JSONObject();
                TypeofBusinessListModel typeofBusinessListModel=StaticData.typeofBusinessListModelList.get(i);
                jsonTLAppOwner.put("Owner_Partner_Id",1);
                jsonTLAppOwner.put("TL_ID",1);
                jsonTLAppOwner.put("Owner_Name",typeofBusinessListModel.getName());
                jsonTLAppOwner.put("Pan",typeofBusinessListModel.getIdproofno());
                jsonTLAppOwner.put("Contact_Mob",typeofBusinessListModel.getContactno());
                jsonTLAppOwner.put("So_Do_Wo",typeofBusinessListModel.getFathername());
                jsonTLAppOwner.put("Address",typeofBusinessListModel.getAddress());
                jsonTLAppOwnerarray.put(jsonTLAppOwner);
            }
            }else{
                Log.d("jsondatainsert","no dataowner");

            }
            JSONArray jsonTlAppDocsarray = new JSONArray();
            if (filesNamepathListModelsnew.size()>0) {
            for(int i=0;i<filesNamepathListModelsnew.size();i++) {
                final JSONObject jsonTlAppDocs = new JSONObject();
                FilesNamepathListModel filesNamepathListModel = filesNamepathListModelsnew.get(i);
                jsonTlAppDocs.put("Document_Id", filesNamepathListModel.getName());
                jsonTlAppDocs.put("Doc_path", filesNamepathListModel.getPath());
                jsonTlAppDocs.put("Remarks", "N");
                jsonTlAppDocs.put("Is_Required", "N");
                jsonTlAppDocs.put("Re_Upload", "N");
                jsonTlAppDocsarray.put(jsonTlAppDocs);
            }
        }else{
            Log.d("jsondatainsert","no datadoc");

        }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "insertTL_Cityzen");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("jsonTLApp", paramObject);
            jsonObject.put("jsonTLAppForm", jsonTLAppForm);
            jsonObject.put("jsonTLAppTradeType", jsonArrayfortradenaturetype);
            jsonObject.put("jsonTLAppOwner", jsonTLAppOwnerarray);
            jsonObject.put("jsonTLAppFollowupEmp", jsonTLAppFollowupEmp);
            jsonObject.put("jsonTlAppDocs", jsonTlAppDocsarray);

            Log.d("jsondatainsert", jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            Log.d("jsondatainsert", e.toString());
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject renewinserttradeform(List<FilesNamepathListModel> filesNamepathListModelsnew) {
        JsonObject obj = new JsonObject();

        final JSONObject jsonTLAppForm=new JSONObject();
        final JSONObject jsonTLAppFollowupEmp=new JSONObject();
        try {
            //
            jsonTLAppForm.put("TL_Form_Id",1);
            jsonTLAppForm.put("TL_ID",StaticData.tl_id);
            jsonTLAppForm.put("Form_No",null);
            jsonTLAppForm.put("Fin_Year_ID",StaticData.tfortheyearid);
            jsonTLAppForm.put("TL_Type","N");
            jsonTLAppForm.put("Ward_ID",StaticData.twardno);
            jsonTLAppForm.put("Street",StaticData.tstreetaddress);
            jsonTLAppForm.put("Holding_No",StaticData.tholdingno);
            jsonTLAppForm.put("Tax_Status",null);
            jsonTLAppForm.put("Land_Nature_Id",StaticData.typeofrelation);//typesofland
            jsonTLAppForm.put("Applied_On",StaticData.tdateofcommenence);
            jsonTLAppForm.put("Status","1");

            jsonTLAppFollowupEmp.put("Followup_Id",1);
            jsonTLAppFollowupEmp.put("TL_Form_Id",0);
            jsonTLAppFollowupEmp.put("Form_Status","PAYMENT PENDING");
            jsonTLAppFollowupEmp.put("Followup_Date",null);
            jsonTLAppFollowupEmp.put("Sent_To_Date",null);
            jsonTLAppFollowupEmp.put("Remarks","PAYMENT PENDING");
            jsonTLAppFollowupEmp.put("Followup_By_E_Cityzen_ID",StaticData.citizen_id);
            jsonTLAppFollowupEmp.put("Followup_By_User_ID",0);
            jsonTLAppFollowupEmp.put("Followup_By_User_Type","Cityzen");
            jsonTLAppFollowupEmp.put("Next_Followup_E_Cityzen_ID",StaticData.citizen_id);//typesofland
            jsonTLAppFollowupEmp.put("Next_Followup_User_ID",0);
            jsonTLAppFollowupEmp.put("Next_Followup_User_Type","Cityzen");
            jsonTLAppForm.put("Is_Used","N");

            JSONArray jsonArrayfortradenaturetype = new JSONArray();
            if (StaticData.tradeNatureModels.size()>0) {
                for (int i = 0; i < StaticData.tradeNatureModels.size(); i++) {
                    final JSONObject jsonTLAppTradeType = new JSONObject();
                    TradeNatureModel tradeNatureModel = StaticData.tradeNatureModels.get(i);
                    jsonTLAppTradeType.put("Txn_Id", 1);
                    jsonTLAppTradeType.put("TL_ID", StaticData.tl_id);
                    jsonTLAppTradeType.put("Trade_id", tradeNatureModel.getName());
                    jsonTLAppTradeType.put("Trade_Size", tradeNatureModel.getType());
                    jsonArrayfortradenaturetype.put(jsonTLAppTradeType);
                }
            }else{
                Log.d("jsondatainsert","no datanature");

            }
            JSONArray jsonTLAppOwnerarray = new JSONArray();
            if (StaticData.typeofBusinessListModelList.size()>0) {
                for(int i=0;i<StaticData.typeofBusinessListModelList.size();i++){
                    final  JSONObject jsonTLAppOwner=new JSONObject();
                    TypeofBusinessListModel typeofBusinessListModel=StaticData.typeofBusinessListModelList.get(i);
                    jsonTLAppOwner.put("Owner_Partner_Id",1);
                    jsonTLAppOwner.put("TL_ID",1);
                    jsonTLAppOwner.put("Owner_Name",typeofBusinessListModel.getName());
                    jsonTLAppOwner.put("Pan",typeofBusinessListModel.getIdproofno());
                    jsonTLAppOwner.put("Contact_Mob",typeofBusinessListModel.getContactno());
                    jsonTLAppOwner.put("So_Do_Wo",typeofBusinessListModel.getFathername());
                    jsonTLAppOwner.put("Address",typeofBusinessListModel.getAddress());
                    jsonTLAppOwnerarray.put(jsonTLAppOwner);
                }
            }else{
                Log.d("jsondatainsert","no dataowner");

            }
            JSONArray jsonTlAppDocsarray = new JSONArray();
            if (filesNamepathListModelsnew.size()>0) {
                for(int i=0;i<filesNamepathListModelsnew.size();i++) {
                    final JSONObject jsonTlAppDocs = new JSONObject();
                    FilesNamepathListModel filesNamepathListModel = filesNamepathListModelsnew.get(i);
                    jsonTlAppDocs.put("Document_name", filesNamepathListModel.getName());
                    jsonTlAppDocs.put("Doc_path", filesNamepathListModel.getPath());
                    jsonTlAppDocsarray.put(jsonTlAppDocs);
                }
            }else{
                Log.d("jsondatainsert","no datadoc");

            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "insertTL_CityzenRenew");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("TL_ID", StaticData.tl_id);//tl_id
            jsonObject.put("jsonTLAppForm", jsonTLAppForm);
            jsonObject.put("jsonTLAppTradeType", jsonArrayfortradenaturetype);
            jsonObject.put("jsonTLAppOwner", jsonTLAppOwnerarray);
            jsonObject.put("jsonTLAppFollowupEmp", jsonTLAppFollowupEmp);
            jsonObject.put("jsonTlAppDocs", jsonTlAppDocsarray);

            Log.d("jsondatarenew", jsonObject.toString());

            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            Log.d("jsondatainsert", e.toString());
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getpendingpayment() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "Payment_Pending_Form");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getfeepayment() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "Payment_Pending_Form");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getpginvoice(String tl_form_id) {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "TL_Application_Payment_PG_Invoice");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("TL_Form_Id", tl_form_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject updatepginvoice(String status, String txn_id, String pg_invoice_id, String TL_Form_Id) {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "TL_Application_Payment_PG_Invoice_Return");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("PG_Status", status);
            jsonObject.put("PG_Txn_ID", txn_id);
            jsonObject.put("PG_Bank_Txn_ID", "NA");
            jsonObject.put("PG_Invoice_ID", pg_invoice_id);
            jsonObject.put("TL_Form_Id", TL_Form_Id);
            jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getmytradelicense() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "Get_My_TL");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("E_Cityzen_ID", 78);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getsinglemytradelicense() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTradeDetails_Cityzen");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("Form_No", StaticData.form_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getsinglemytradetype(String app_TL_ID) {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "get_TradetypeDetails");
            jsonObject.put("spname", "USP_TRADE_APPLICATION_EMP");
            jsonObject.put("app_TL_ID", app_TL_ID);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getsingledocumentlist() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getDocumentDetials");
            jsonObject.put("spname", "USP_TRADE_APPLICATION_EMP");
            jsonObject.put("Form_No", StaticData.form_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getsinglefollwupchart() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "get_application_followup_Cityzen");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("Form_No", StaticData.form_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getnotificationlist() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "Remarks_Cityzen");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JsonObject getrenewmytradelicense() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTradeDetailsRenewSelect");
            jsonObject.put("spname", "USP_TRADE_APPLICATION_EMP");
            jsonObject.put("regn_118", StaticData.regx_no);
           // jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getRenewSelect_Owner_Partner() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTDRenewSelect_Owner_Partner");
            jsonObject.put("spname", "USP_TRADE_APPLICATION_EMP");
            jsonObject.put("regn_118", StaticData.regx_no);
            // jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getRenewSelect_tradetype() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "getTDRenewSelect_Nature_Of_Trade");
            jsonObject.put("spname", "USP_TRADE_APPLICATION_EMP_PART2");
            jsonObject.put("regn_118", StaticData.regx_no);
            // jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getfarwarddata(String tl_form_id, String followup_by_user_id) {
        int status=0;
        if (followup_by_user_id.equals("6")){
            status=16;
        }else if (followup_by_user_id.equals("8")){
            status=17;
        }else if (followup_by_user_id.equals("9")){
            status=18;
        }
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "Insert_Update_Followup_cityzen");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("TL_Form_Id",tl_form_id);
            jsonObject.put("E_Cityzen_ID", StaticData.citizen_id);
            jsonObject.put("Next_User_Id",followup_by_user_id);
            jsonObject.put("Status_Id",status);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static JsonObject getprintdatafortradelicense() {
        JsonObject obj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("flag", "get_PrintReceived");
            jsonObject.put("spname", "USP_TRADE_APPLICATION");
            jsonObject.put("Form_No", StaticData.form_id);
            Log.d("jsondata", jsonObject.toString());
            JsonParser jsonParser = new JsonParser();
            obj = (JsonObject) jsonParser.parse(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
