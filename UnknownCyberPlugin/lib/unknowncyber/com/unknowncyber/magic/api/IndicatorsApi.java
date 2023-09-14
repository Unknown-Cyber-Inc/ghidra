package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedIndicatorFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedIndicatorResponseList200;
import java.io.File;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndicatorsApi {
  private ApiClient apiClient;

  public IndicatorsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public IndicatorsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Lists all indicators associated with a user account
   *    Lists all indicators associated with a user account         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedIndicatorResponseList200
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public EnvelopedIndicatorResponseList200 listIocs(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/indicators/";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "format", format));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "explain", explain));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "download", download));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filename", filename));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "no_links", noLinks));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "uri", uri));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedIndicatorResponseList200> localVarReturnType = new GenericType<EnvelopedIndicatorResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Upload a text or csv file for IoC Extraction
   *    Upload a text or csv file for IoC Extraction         
   * @param filedata  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedIndicatorFileUploadResponse200
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public EnvelopedIndicatorFileUploadResponse200 uploadIocFile(File filedata, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'filedata' is set
    if (filedata == null) {
      throw new ApiException(400, "Missing the required parameter 'filedata' when calling uploadIocFile");
    }
    // create path and map variables
    String localVarPath = "/indicators/files/";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "format", format));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "explain", explain));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "download", download));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filename", filename));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "no_links", noLinks));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "uri", uri));

    if (filedata != null)
      localVarFormParams.put("filedata", filedata);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedIndicatorFileUploadResponse200> localVarReturnType = new GenericType<EnvelopedIndicatorFileUploadResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
