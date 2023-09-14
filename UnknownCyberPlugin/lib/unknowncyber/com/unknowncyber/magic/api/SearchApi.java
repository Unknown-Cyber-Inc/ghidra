package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedSearchCountResponseList200;
import com.unknowncyber.magic.model.EnvelopedSearchResponseList200;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchApi {
  private ApiClient apiClient;

  public SearchApi() {
    this(Configuration.getDefaultApiClient());
  }

  public SearchApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Returns total count of results via search
   *    Returns total count of results via search         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param query Search query to look for (optional)
   * @return EnvelopedSearchCountResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedSearchCountResponseList200 countSearchResults(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String query) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/search/count/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedSearchCountResponseList200> localVarReturnType = new GenericType<EnvelopedSearchCountResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Returns files found via search
   *    Returns files found via search         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param searchExact Whether an exact match is required in a search (optional)
   * @param searchQuery Search query to look for (optional)
   * @param searchType Value type with which to search (optional)
   * @return EnvelopedSearchResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedSearchResponseList200 searchFiles(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, Boolean searchExact, String searchQuery, String searchType) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/search/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_count", pageCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_count", skipCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "search_exact", searchExact));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "search_query", searchQuery));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "search_type", searchType));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedSearchResponseList200> localVarReturnType = new GenericType<EnvelopedSearchResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
