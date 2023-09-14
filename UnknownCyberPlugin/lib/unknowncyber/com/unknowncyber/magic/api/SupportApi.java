package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportApi {
  private ApiClient apiClient;

  public SupportApi() {
    this(Configuration.getDefaultApiClient());
  }

  public SupportApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Allows users to report a bug within the system
   *    Allows users to report a bug within the system         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public void reportBug(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/support/bug/";

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
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
