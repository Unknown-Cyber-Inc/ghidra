package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedFileList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedTag200;
import com.unknowncyber.magic.model.EnvelopedTagCreatedResponse201;
import com.unknowncyber.magic.model.EnvelopedTagList200;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagsApi {
  private ApiClient apiClient;

  public TagsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TagsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Lists information on a bulk selection of tags
   *    Lists information on a bulk selection of tags         
   * @param color  (required)
   * @param ids  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @param strict Used for bulk sets of resources. If true, every resource must pass validation in order for any to be operated on (optional)
   * @param action Used in bulk queries. Bulk queries are always POST, so &#x27;action&#x27; allows the user to set the desired method (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @return EnvelopedTagList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagList200 bulkTagOperation(String color, List<String> ids, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask, Boolean force, Boolean strict, String action, String readMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'color' is set
    if (color == null) {
      throw new ApiException(400, "Missing the required parameter 'color' when calling bulkTagOperation");
    }
    // verify the required parameter 'ids' is set
    if (ids == null) {
      throw new ApiException(400, "Missing the required parameter 'ids' when calling bulkTagOperation");
    }
    // create path and map variables
    String localVarPath = "/tags/bulk/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "update_mask", updateMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "strict", strict));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "action", action));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));

    if (color != null)
      localVarFormParams.put("color", color);
    if (ids != null)
      localVarFormParams.put("ids", ids);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagList200> localVarReturnType = new GenericType<EnvelopedTagList200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Create a new Project for tagging files
   *    Create a new Project for tagging files         
   * @param name  (optional)
   * @param color  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedTagCreatedResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagCreatedResponse201 createTag(String name, String color, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/tags/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dryrun", dryrun));

    if (name != null)
      localVarFormParams.put("name", name);
    if (color != null)
      localVarFormParams.put("color", color);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagCreatedResponse201> localVarReturnType = new GenericType<EnvelopedTagCreatedResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Deletes a tag
   *    Deletes a tag         
   * @param id  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteTag(String id, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling deleteTag");
    }
    // create path and map variables
    String localVarPath = "/tags/{id}/"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Delete tags in your collection
   *    Delete all tags in your collection         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param filters  Semi-colon separated string of filters. Each filter has a pattern of &#x60;(not)? &lt;var&gt;__&lt;comp&gt;(value)&#x60;   REGEX: &#x60;^(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\)(\\ +(AND|OR|;)\\ +(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\))*$&#x60;,  (optional)
   * @param orderBy  Comma separated string containing a list of keys to sort on. Prepend with a &#x60;-&#x60; for descending.   REGEX: &#x60;^(-?[\\w]+,?)*$&#x60;  (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteTags(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String filters, String orderBy, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/tags/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filters", filters));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "order_by", orderBy));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Retrieves detailed information on a single tag
   *    Retrieves detailed information on a single tag         
   * @param id  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @return EnvelopedTag200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTag200 getTag(String id, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling getTag");
    }
    // create path and map variables
    String localVarPath = "/tags/{id}/"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTag200> localVarReturnType = new GenericType<EnvelopedTag200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists all files associated with this tag
   *    Lists all files associated with this tag         
   * @param id  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedFileList200EnvelopedIdList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileList200EnvelopedIdList200 listTaggedFiles(String id, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling listTaggedFiles");
    }
    // create path and map variables
    String localVarPath = "/tags/{id}/files/"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand_mask", expandMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileList200EnvelopedIdList200> localVarReturnType = new GenericType<EnvelopedFileList200EnvelopedIdList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * List all tags
   *    List all tags         
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param filters  Semi-colon separated string of filters. Each filter has a pattern of &#x60;(not)? &lt;var&gt;__&lt;comp&gt;(value)&#x60;   REGEX: &#x60;^(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\)(\\ +(AND|OR|;)\\ +(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\))*$&#x60;,  (optional)
   * @param orderBy  Comma separated string containing a list of keys to sort on. Prepend with a &#x60;-&#x60; for descending.   REGEX: &#x60;^(-?[\\w]+,?)*$&#x60;  (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @return EnvelopedTagList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagList200 listTags(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String filters, String orderBy, String readMask) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/tags/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filters", filters));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "order_by", orderBy));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagList200> localVarReturnType = new GenericType<EnvelopedTagList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Removes a tag from all files
   *    Removes a tag from all files         
   * @param id  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void removeTags(String id, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling removeTags");
    }
    // create path and map variables
    String localVarPath = "/tags/{id}/files/"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Updates a tag
   *    Updates a tag         
   * @param id  (required)
   * @param name  (optional)
   * @param color  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedTag200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTag200 updateTag(String id, String name, String color, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling updateTag");
    }
    // create path and map variables
    String localVarPath = "/tags/{id}/"
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "update_mask", updateMask));

    if (name != null)
      localVarFormParams.put("name", name);
    if (color != null)
      localVarFormParams.put("color", color);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTag200> localVarReturnType = new GenericType<EnvelopedTag200>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Updates all tags in your collection
   *    Updates all tags in your collection         
   * @param color  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param filters  Semi-colon separated string of filters. Each filter has a pattern of &#x60;(not)? &lt;var&gt;__&lt;comp&gt;(value)&#x60;   REGEX: &#x60;^(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\)(\\ +(AND|OR|;)\\ +(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\))*$&#x60;,  (optional)
   * @param orderBy  Comma separated string containing a list of keys to sort on. Prepend with a &#x60;-&#x60; for descending.   REGEX: &#x60;^(-?[\\w]+,?)*$&#x60;  (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedTagList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagList200 updateTags(String color, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String filters, String orderBy, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/tags/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filters", filters));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "order_by", orderBy));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "update_mask", updateMask));

    if (color != null)
      localVarFormParams.put("color", color);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagList200> localVarReturnType = new GenericType<EnvelopedTagList200>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
