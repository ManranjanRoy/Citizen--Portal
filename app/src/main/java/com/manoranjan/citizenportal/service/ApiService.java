package com.manoranjan.citizenportal.service;





import com.google.gson.JsonObject;
import com.manoranjan.citizenportal.Api.ApiLinks;
import com.manoranjan.citizenportal.Response.DocumentListResponse;
import com.manoranjan.citizenportal.Response.InsertTLResponse;
import com.manoranjan.citizenportal.Response.PgInvoiceResponse;
import com.manoranjan.citizenportal.Response.PrintdataResponse;
import com.manoranjan.citizenportal.Response.ProfileResponse;
import com.manoranjan.citizenportal.Response.RenewNatureoftradeREsponse;
import com.manoranjan.citizenportal.Response.RenewOwnerpartnerResponse;
import com.manoranjan.citizenportal.Response.SignupResponse;
import com.manoranjan.citizenportal.Response.SingleFollowupGraphResponse;
import com.manoranjan.citizenportal.Response.SingleMyTadeLicense;
import com.manoranjan.citizenportal.Response.SingleTradetypeResponse;
import com.manoranjan.citizenportal.Response.UpdateProfile;
import com.manoranjan.citizenportal.model.DocTypeListModel;
import com.manoranjan.citizenportal.model.Example;
import com.manoranjan.citizenportal.model.FeePaymentModel;
import com.manoranjan.citizenportal.model.FileUploadModel;
import com.manoranjan.citizenportal.model.FortheYearModel;
import com.manoranjan.citizenportal.model.GetprofileModel;
import com.manoranjan.citizenportal.model.MytradeLicenseModel;
import com.manoranjan.citizenportal.model.NatureoftradeModel;
import com.manoranjan.citizenportal.model.NotificatonModel;
import com.manoranjan.citizenportal.model.PendingPaymentModel;
import com.manoranjan.citizenportal.model.Profile;
import com.manoranjan.citizenportal.model.TypesOfBusinessModel;
import com.manoranjan.citizenportal.model.TypesOfLandModel;
import com.manoranjan.citizenportal.model.WardNoModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * This class represents the Countries API, all endpoints can stay here.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
        Call<List<Example>> createUser(
            @Body JsonObject body);


    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<Example>> login(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<GetprofileModel>> getprofile(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<UpdateProfile>> updateprofile(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<Profile>> profile(
            @Body JsonObject body);

  @POST
  @Multipart
  Call<List<FileUploadModel>> uploadfiles(@Url String url,
                                    @Part MultipartBody.Part image);
    @POST
    @Multipart
    Call<List<FileUploadModel>> uploadmultifiles(@Url String url,
                                            @Part MultipartBody.Part image[]);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<WardNoModel>> getwardno(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<TypesOfLandModel>> gettypesofland(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<TypesOfBusinessModel>> gettypeofbusiness(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<FortheYearModel>> getfortheyear(
            @Body JsonObject body);
    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<NatureoftradeModel>> getnatureoftrade(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<DocTypeListModel>> getdoctypeslist(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<InsertTLResponse>> inserttlform(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<PendingPaymentModel>> pendingpayments(
            @Body JsonObject body);

  @Headers("Content-Type: application/json")
  @POST(ApiLinks.insert_mul_json)
  Call<List<FeePaymentModel>> feepayments(
          @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<PgInvoiceResponse>> pginvoice(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<PgInvoiceResponse>> updatepginvoice(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<MytradeLicenseModel>> getmytradelicense(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<SingleMyTadeLicense>> getsinglemytradelicense(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<RenewOwnerpartnerResponse>> getrenewownerpartner(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<RenewNatureoftradeREsponse>> getrenewtradetype(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<SingleTradetypeResponse>> getsinglemytradetypes(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<DocumentListResponse>> getsingledocumentlist(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<SingleFollowupGraphResponse>> getsinglefollowupdetails(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<NotificatonModel>> notificationlist(
            @Body JsonObject body);
    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<NotificatonModel>> forwarddata(
            @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST(ApiLinks.insert_mul_json)
    Call<List<PrintdataResponse>> getprintdatafortradedetails(
            @Body JsonObject body);


}
