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

public class ReportsApi {
  private ApiClient apiClient;

  public ReportsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ReportsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retrieves the report info of a container like file
   *    Retrieves the report info of a container like file         
   * @param binaryId  (required)
   * @param format  (optional)
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public void getArchiveReport(String binaryId, String format) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getArchiveReport");
    }
    // create path and map variables
    String localVarPath = "/reports/{binary_id}/archive/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "format", format));


    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Retrieves the genomic features of a file
   *    Retrieves the genomic features of a file         
   * @param binaryId  (required)
   * @param format  (optional)
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public void getReportFeatures(String binaryId, String format) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getReportFeatures");
    }
    // create path and map variables
    String localVarPath = "/reports/{binary_id}/features/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "format", format));


    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
