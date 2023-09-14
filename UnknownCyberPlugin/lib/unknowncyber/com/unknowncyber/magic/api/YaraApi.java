package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedFileList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedYara200;
import com.unknowncyber.magic.model.ForbiddenResponse;
import com.unknowncyber.magic.model.NotFoundResponse;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YaraApi {
  private ApiClient apiClient;

  public YaraApi() {
    this(Configuration.getDefaultApiClient());
  }

  public YaraApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create Yara Rule based on specific file hashes
   *    Create Yara Rule based on specific file hashes         
   * @param files  (required)
   * @param name  (required)
   * @param unpacked  (required)
   * @param config  (required)
   * @param includeAll  (required)
   * @param maxSignatures  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @param strict Used for bulk sets of resources. If true, every resource must pass validation in order for any to be operated on (optional)
   * @return EnvelopedYara200
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public EnvelopedYara200 createYara(List<String> files, String name, Boolean unpacked, String config, Boolean includeAll, Integer maxSignatures, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun, Boolean strict) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'files' is set
    if (files == null) {
      throw new ApiException(400, "Missing the required parameter 'files' when calling createYara");
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(400, "Missing the required parameter 'name' when calling createYara");
    }
    // verify the required parameter 'unpacked' is set
    if (unpacked == null) {
      throw new ApiException(400, "Missing the required parameter 'unpacked' when calling createYara");
    }
    // verify the required parameter 'config' is set
    if (config == null) {
      throw new ApiException(400, "Missing the required parameter 'config' when calling createYara");
    }
    // verify the required parameter 'includeAll' is set
    if (includeAll == null) {
      throw new ApiException(400, "Missing the required parameter 'includeAll' when calling createYara");
    }
    // verify the required parameter 'maxSignatures' is set
    if (maxSignatures == null) {
      throw new ApiException(400, "Missing the required parameter 'maxSignatures' when calling createYara");
    }
    // create path and map variables
    String localVarPath = "/yara/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "strict", strict));

    if (files != null)
      localVarFormParams.put("files", files);
    if (name != null)
      localVarFormParams.put("name", name);
    if (unpacked != null)
      localVarFormParams.put("unpacked", unpacked);
    if (config != null)
      localVarFormParams.put("config", config);
    if (includeAll != null)
      localVarFormParams.put("include_all", includeAll);
    if (maxSignatures != null)
      localVarFormParams.put("max_signatures", maxSignatures);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedYara200> localVarReturnType = new GenericType<EnvelopedYara200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * 
   * Get similar binaries based off of potential yara procedure matches
   * @param binaryId  (required)
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
   * @deprecated
   */
  @Deprecated
  public EnvelopedFileList200EnvelopedIdList200 yaraProcedureMatches(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling yaraProcedureMatches");
    }
    // create path and map variables
    String localVarPath = "/yara/{binary_id}/matches/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()));

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
}
