package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedFile200;
import com.unknowncyber.magic.model.EnvelopedFileGenomicsResponse200;
import com.unknowncyber.magic.model.EnvelopedFileIndicatorResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileLabelCreateResponse201;
import com.unknowncyber.magic.model.EnvelopedFileLabelsList200;
import com.unknowncyber.magic.model.EnvelopedFileList200;
import com.unknowncyber.magic.model.EnvelopedFileList200EnvelopedFileChildList200;
import com.unknowncyber.magic.model.EnvelopedFileList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedFileMatchResponseList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedFileProcedureResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileReputationResponse200;
import com.unknowncyber.magic.model.EnvelopedFileResponse200;
import com.unknowncyber.magic.model.EnvelopedFileResponse201;
import com.unknowncyber.magic.model.EnvelopedFileResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileSearchResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileSimilarityResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileStringsResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileUpdateResponse206;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse201;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList201;
import com.unknowncyber.magic.model.EnvelopedIdList201;
import com.unknowncyber.magic.model.EnvelopedJobResponse201;
import com.unknowncyber.magic.model.EnvelopedNote200;
import com.unknowncyber.magic.model.EnvelopedNote201;
import com.unknowncyber.magic.model.EnvelopedNoteList200;
import com.unknowncyber.magic.model.EnvelopedPayloadCreateResponse201;
import com.unknowncyber.magic.model.EnvelopedProcedureList200;
import com.unknowncyber.magic.model.EnvelopedProcedureResponse200;
import com.unknowncyber.magic.model.EnvelopedTag200;
import com.unknowncyber.magic.model.EnvelopedTagCreatedResponse200;
import com.unknowncyber.magic.model.EnvelopedTagCreatedResponse201;
import com.unknowncyber.magic.model.EnvelopedTagResponseList200;
import com.unknowncyber.magic.model.EnvelopedYara200;
import java.io.File;
import com.unknowncyber.magic.model.ForbiddenResponse;
import com.unknowncyber.magic.model.NotFoundResponse;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesApi {
  private ApiClient apiClient;

  public FilesApi() {
    this(Configuration.getDefaultApiClient());
  }

  public FilesApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Adds a publicly accessible file to your account
   *    Adds a publicly accessible file to your account         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedFileResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileResponse200 addFile(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling addFile");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dryrun", dryrun));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileResponse200> localVarReturnType = new GenericType<EnvelopedFileResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Associate an existing tag with a file
   *    Associate an existing tag with a file         
   * @param binaryId  (required)
   * @param tagId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedIdList201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedIdList201 addFileTag(String binaryId, String tagId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling addFileTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling addFileTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/tags/{tag_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "tag_id" + "\\}", apiClient.escapeString(tagId.toString()));

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

    GenericType<EnvelopedIdList201> localVarReturnType = new GenericType<EnvelopedIdList201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Manually add a payload connection to a file
   *    Manually add a payload connection to a file         
   * @param binaryId  (required)
   * @param payloadId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force Forces a payload to be added, even if one already exists (optional)
   * @return EnvelopedPayloadCreateResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedPayloadCreateResponse201 addPayload(String binaryId, String payloadId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling addPayload");
    }
    // verify the required parameter 'payloadId' is set
    if (payloadId == null) {
      throw new ApiException(400, "Missing the required parameter 'payloadId' when calling addPayload");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/payload/{payload_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "payload_id" + "\\}", apiClient.escapeString(payloadId.toString()));

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

    GenericType<EnvelopedPayloadCreateResponse201> localVarReturnType = new GenericType<EnvelopedPayloadCreateResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Allows for actions to be carried out on bulk sets of files
   *    Allows for actions to be carried out on bulk sets of files         
   * @param ids  (required)
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
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @param dynamicMask Comma separated string containing a list of dynamically created fields to return.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param action Used in bulk queries. Bulk queries are always POST, so &#x27;action&#x27; allows the user to set the desired method (optional)
   * @return EnvelopedFileList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileList200 bulkFileOperation(List<String> ids, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String filters, String orderBy, String readMask, String expandMask, Boolean dryrun, Boolean force, String dynamicMask, String action) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'ids' is set
    if (ids == null) {
      throw new ApiException(400, "Missing the required parameter 'ids' when calling bulkFileOperation");
    }
    // create path and map variables
    String localVarPath = "/files/bulk/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand_mask", expandMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dryrun", dryrun));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dynamic_mask", dynamicMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "action", action));

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

    GenericType<EnvelopedFileList200> localVarReturnType = new GenericType<EnvelopedFileList200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Creates a new custom category label for a file
   *    Creates a new custom category label for a file         
   * @param label  (required)
   * @param source  (required)
   * @param score  (required)
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileLabelCreateResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileLabelCreateResponse201 createFileCategory(String label, String source, Integer score, String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'label' is set
    if (label == null) {
      throw new ApiException(400, "Missing the required parameter 'label' when calling createFileCategory");
    }
    // verify the required parameter 'source' is set
    if (source == null) {
      throw new ApiException(400, "Missing the required parameter 'source' when calling createFileCategory");
    }
    // verify the required parameter 'score' is set
    if (score == null) {
      throw new ApiException(400, "Missing the required parameter 'score' when calling createFileCategory");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createFileCategory");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/categories/"
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

    if (label != null)
      localVarFormParams.put("label", label);
    if (source != null)
      localVarFormParams.put("source", source);
    if (score != null)
      localVarFormParams.put("score", score);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileLabelCreateResponse201> localVarReturnType = new GenericType<EnvelopedFileLabelCreateResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Creates a new custom family label for a file
   *    Creates a new custom family label for a file         
   * @param label  (required)
   * @param source  (required)
   * @param score  (required)
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileLabelCreateResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileLabelCreateResponse201 createFileFamily(String label, String source, Integer score, String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'label' is set
    if (label == null) {
      throw new ApiException(400, "Missing the required parameter 'label' when calling createFileFamily");
    }
    // verify the required parameter 'source' is set
    if (source == null) {
      throw new ApiException(400, "Missing the required parameter 'source' when calling createFileFamily");
    }
    // verify the required parameter 'score' is set
    if (score == null) {
      throw new ApiException(400, "Missing the required parameter 'score' when calling createFileFamily");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createFileFamily");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/families/"
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

    if (label != null)
      localVarFormParams.put("label", label);
    if (source != null)
      localVarFormParams.put("source", source);
    if (score != null)
      localVarFormParams.put("score", score);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileLabelCreateResponse201> localVarReturnType = new GenericType<EnvelopedFileLabelCreateResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Send a file for reprocessing
   *    Send a file for reprocessing         
   * @param binaryId  (required)
   * @param job The job to reprocess for this file (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force Forces a job to be run, even if previously successful (optional)
   * @return EnvelopedJobResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedJobResponse201 createFileJob(String binaryId, String job, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createFileJob");
    }
    // verify the required parameter 'job' is set
    if (job == null) {
      throw new ApiException(400, "Missing the required parameter 'job' when calling createFileJob");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/jobs/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "job", job));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedJobResponse201> localVarReturnType = new GenericType<EnvelopedJobResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Attaches a note to a file
   *    Attaches a note to a file         
   * @param note  (required)
   * @param _public  (required)
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedNote201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote201 createFileNote(String note, Boolean _public, String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'note' is set
    if (note == null) {
      throw new ApiException(400, "Missing the required parameter 'note' when calling createFileNote");
    }
    // verify the required parameter '_public' is set
    if (_public == null) {
      throw new ApiException(400, "Missing the required parameter '_public' when calling createFileNote");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createFileNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/notes/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dryrun", dryrun));

    if (note != null)
      localVarFormParams.put("note", note);
    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedNote201> localVarReturnType = new GenericType<EnvelopedNote201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Create tag and bind to a file
   *    Create tag and bind to a file         
   * @param binaryId  (required)
   * @param name  (optional)
   * @param color  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedTagCreatedResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagCreatedResponse200 createFileTag(String binaryId, String name, String color, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createFileTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/tags/"
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

    GenericType<EnvelopedTagCreatedResponse200> localVarReturnType = new GenericType<EnvelopedTagCreatedResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Create Yara Rule based on multiple file hashes
   *    Create Yara Rule based on multiple file hashes         
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
   */
  public EnvelopedYara200 createFilesYara(List<String> files, String name, Boolean unpacked, String config, Boolean includeAll, Integer maxSignatures, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun, Boolean strict) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'files' is set
    if (files == null) {
      throw new ApiException(400, "Missing the required parameter 'files' when calling createFilesYara");
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(400, "Missing the required parameter 'name' when calling createFilesYara");
    }
    // verify the required parameter 'unpacked' is set
    if (unpacked == null) {
      throw new ApiException(400, "Missing the required parameter 'unpacked' when calling createFilesYara");
    }
    // verify the required parameter 'config' is set
    if (config == null) {
      throw new ApiException(400, "Missing the required parameter 'config' when calling createFilesYara");
    }
    // verify the required parameter 'includeAll' is set
    if (includeAll == null) {
      throw new ApiException(400, "Missing the required parameter 'includeAll' when calling createFilesYara");
    }
    // verify the required parameter 'maxSignatures' is set
    if (maxSignatures == null) {
      throw new ApiException(400, "Missing the required parameter 'maxSignatures' when calling createFilesYara");
    }
    // create path and map variables
    String localVarPath = "/files/yara/";

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
   * Attaches a note to a procedure&#x27;s genomics
   *    Attaches a note to a procedure&#x27;s genomics         
   * @param note  (required)
   * @param _public  (required)
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 createProcedureGenomicsNote(String note, Boolean _public, String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'note' is set
    if (note == null) {
      throw new ApiException(400, "Missing the required parameter 'note' when calling createProcedureGenomicsNote");
    }
    // verify the required parameter '_public' is set
    if (_public == null) {
      throw new ApiException(400, "Missing the required parameter '_public' when calling createProcedureGenomicsNote");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createProcedureGenomicsNote");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling createProcedureGenomicsNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/notes/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    if (note != null)
      localVarFormParams.put("note", note);
    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Attaches a tag to a procedure&#x27;s genomics
   *    Attaches a tag to a procedure&#x27;s genomics         
   * @param name  (required)
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedTagCreatedResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagCreatedResponse200 createProcedureGenomicsTag(String name, String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(400, "Missing the required parameter 'name' when calling createProcedureGenomicsTag");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling createProcedureGenomicsTag");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling createProcedureGenomicsTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/tags/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagCreatedResponse200> localVarReturnType = new GenericType<EnvelopedTagCreatedResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Deletes a specified user note attached to a file
   *    Deletes a specified user note attached to a file         
   * @param binaryId  (required)
   * @param noteId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteFileNote(String binaryId, String noteId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling deleteFileNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling deleteFileNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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
   * Manually remove a payload connection from a file
   *    Manually remove a payload connection from a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deletePayloadRelationship(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling deletePayloadRelationship");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/payload/"
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
   * Removes a note from a procedure&#x27;s genomics
   *    Removes a note from a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param noteId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteProcedureGenomicsNote(String binaryId, String rva, String noteId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling deleteProcedureGenomicsNote");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling deleteProcedureGenomicsNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling deleteProcedureGenomicsNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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
   * Removes a tag from a procedure&#x27;s genomics
   *    Removes a tag from a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public void deleteProcedureGenomicsTag(String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling deleteProcedureGenomicsTag");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling deleteProcedureGenomicsTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/tags/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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
   * Removes a tag from a procedure&#x27;s genomics
   *    Removes a tag from a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param tagId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteProcedureGenomicsTagById(String binaryId, String rva, String tagId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling deleteProcedureGenomicsTagById");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling deleteProcedureGenomicsTagById");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling deleteProcedureGenomicsTagById");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/tags/{tag_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()))
      .replaceAll("\\{" + "tag_id" + "\\}", apiClient.escapeString(tagId.toString()));

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
   * Download file
   *    Download file         
   * @param binaryId  (required)
   * @param format  (optional)
   * @param zipped If true, the returned download will be in an encrypted zip file (password&#x3D;infected) (optional)
   * @throws ApiException if fails to make API call
   */
  public void downloadFile(String binaryId, String format, Boolean zipped) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling downloadFile");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/download/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "format", format));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "zipped", zipped));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Retrieves information for a single file
   *    Retrieves information for a single file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param dynamicMask Comma separated string containing a list of dynamically created fields to return.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedFile200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFile200 getFile(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask, String expandMask, String dynamicMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getFile");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dynamic_mask", dynamicMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFile200> localVarReturnType = new GenericType<EnvelopedFile200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a file&#x27;s campaign information
   *    Retrieves a file&#x27;s campaign information         
   * @param binaryId  (required)
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
  public void getFileCampaign(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getFileCampaign");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/campaign/"
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
   * Retrieves a single note attached to a file
   *    Retrieves a single note attached to a file         
   * @param binaryId  (required)
   * @param noteId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 getFileNote(String binaryId, String noteId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getFileNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling getFileNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves the reputation status of the file
   *    Retrieves the reputation status of the file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param verbose Whether to include all files that infer reputation (optional)
   * @return EnvelopedFileReputationResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileReputationResponse200 getFileReputation(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean verbose) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getFileReputation");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/reputation/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "verbose", verbose));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileReputationResponse200> localVarReturnType = new GenericType<EnvelopedFileReputationResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Returns a yara rule for the given file
   *    Returns a yara rule for the given file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param config The config of parameters to use when generating a yara rule (optional, default to relaxed)
   * @param includeAll Whether to include all procedures (optional)
   * @param unpacked Whether to use unpacked or original binaries (optional)
   * @param name The name of the yara rule (optional)
   * @return EnvelopedYara200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedYara200 getFileYara(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String config, Boolean includeAll, Boolean unpacked, String name) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getFileYara");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/yara/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "config", config));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "include_all", includeAll));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "unpacked", unpacked));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedYara200> localVarReturnType = new GenericType<EnvelopedYara200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a note on a procedure
   *    Retrieves a note on a procedure         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param noteId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 getProcedureGenomicsNote(String binaryId, String rva, String noteId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling getProcedureGenomicsNote");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling getProcedureGenomicsNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling getProcedureGenomicsNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a file&#x27;s category labels
   *    Retrieves a file&#x27;s category labels         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileLabelsList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileLabelsList200 listFileCategories(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileCategories");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/categories/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileLabelsList200> localVarReturnType = new GenericType<EnvelopedFileLabelsList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists all files that were extracted as children
   *    Lists all files that were extracted as children         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileList200EnvelopedFileChildList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileList200EnvelopedFileChildList200 listFileChildren(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileChildren");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/children/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileList200EnvelopedFileChildList200> localVarReturnType = new GenericType<EnvelopedFileList200EnvelopedFileChildList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a file&#x27;s family labels
   *    Retrieves a file&#x27;s family labels         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileLabelsList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileLabelsList200 listFileFamilies(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileFamilies");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/families/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileLabelsList200> localVarReturnType = new GenericType<EnvelopedFileLabelsList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a file&#x27;s genomics
   *    Retrieves a file&#x27;s genomics         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param orderBy  Comma separated string containing a list of keys to sort on. Prepend with a &#x60;-&#x60; for descending.   REGEX: &#x60;^(-?[\\w]+,?)*$&#x60;  (optional)
   * @param noLibs Whether to include library procedures (optional)
   * @return EnvelopedFileGenomicsResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileGenomicsResponse200 listFileGenomics(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String readMask, String orderBy, Boolean noLibs) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileGenomics");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_count", pageCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_count", skipCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "order_by", orderBy));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "no_libs", noLibs));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileGenomicsResponse200> localVarReturnType = new GenericType<EnvelopedFileGenomicsResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists the Indicators of Compromise associated with a file
   *    Lists the Indicators of Compromise associated with a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param malicious Whether to only show malicious indicators (optional, default to true)
   * @return EnvelopedFileIndicatorResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileIndicatorResponseList200 listFileIndicators(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean malicious) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileIndicators");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/indicators/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "malicious", malicious));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileIndicatorResponseList200> localVarReturnType = new GenericType<EnvelopedFileIndicatorResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Gets labels for a file
   *    Gets labels for a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileLabelsList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileLabelsList200 listFileLabels(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileLabels");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/labels/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileLabelsList200> localVarReturnType = new GenericType<EnvelopedFileLabelsList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Gets matches for a file
   *    Gets matches for a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param maxThreshold Only similarity matches at value equal or below max_threshold will be considered (optional)
   * @param minThreshold Only similarity matches at value equal or above min_threshold will be considered (optional)
   * @return EnvelopedFileMatchResponseList200EnvelopedIdList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileMatchResponseList200EnvelopedIdList200 listFileMatches(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String readMask, String expandMask, Float maxThreshold, Float minThreshold) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileMatches");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/matches/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_count", pageCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_count", skipCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand_mask", expandMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "max_threshold", maxThreshold));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "min_threshold", minThreshold));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileMatchResponseList200EnvelopedIdList200> localVarReturnType = new GenericType<EnvelopedFileMatchResponseList200EnvelopedIdList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves all user generated notes for file
   *    Retrieves all user generated notes for file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedNoteList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNoteList200 listFileNotes(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileNotes");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/notes/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedNoteList200> localVarReturnType = new GenericType<EnvelopedNoteList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a file&#x27;s parent files
   *    Retrieves a file&#x27;s parent files         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileResponseList200 listFileParents(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileParents");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/parents/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileResponseList200> localVarReturnType = new GenericType<EnvelopedFileResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a procedure&#x27;s genomics
   *    Retrieves a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedProcedureResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureResponse200 listFileProcedureGenomics(String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileProcedureGenomics");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling listFileProcedureGenomics");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    GenericType<EnvelopedProcedureResponse200> localVarReturnType = new GenericType<EnvelopedProcedureResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists all procedures and their information for the given file
   *    Lists all procedures and their information for the given file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param unpacked Whether to use unpacked or original binaries (optional)
   * @return EnvelopedFileProcedureResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileProcedureResponseList200 listFileProcedures(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String readMask, Boolean unpacked) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileProcedures");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/procedures/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_count", pageCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_count", skipCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "unpacked", unpacked));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileProcedureResponseList200> localVarReturnType = new GenericType<EnvelopedFileProcedureResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves similar file matches for the specified file
   *    Retrieves similar file matches for the specified file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param maxThreshold Only similarity matches at value equal or below max_threshold will be considered (optional)
   * @param method Method to use to find similarities (optional, default to semantic_similarity)
   * @param minThreshold Only similarity matches at value equal or above min_threshold will be considered (optional)
   * @return EnvelopedFileSimilarityResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileSimilarityResponseList200 listFileSimilarities(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, Float maxThreshold, String method, Float minThreshold) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileSimilarities");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/similarities/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_count", pageCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page_size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_count", skipCount));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "max_threshold", maxThreshold));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "method", method));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "min_threshold", minThreshold));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileSimilarityResponseList200> localVarReturnType = new GenericType<EnvelopedFileSimilarityResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Gets strings for a file
   *    Gets strings for a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedFileStringsResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileStringsResponseList200 listFileStrings(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileStrings");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/strings/"
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


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileStringsResponseList200> localVarReturnType = new GenericType<EnvelopedFileStringsResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * List all user tags associated with a file
   *    List all user tags associated with a file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedTagResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagResponseList200 listFileTags(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileTags");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/tags/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand_mask", expandMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagResponseList200> localVarReturnType = new GenericType<EnvelopedTagResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
   */
  public EnvelopedFileList200EnvelopedIdList200 listFileYaraMatches(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listFileYaraMatches");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/yara/matches/"
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
  /**
   * List user files based on the parameters specified
   * List user files based on the parameters specified
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
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param dynamicMask Comma separated string containing a list of dynamically created fields to return.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedFileList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileList200 listFiles(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String filters, String orderBy, String readMask, String expandMask, String dynamicMask) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/files/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand_mask", expandMask));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dynamic_mask", dynamicMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileList200> localVarReturnType = new GenericType<EnvelopedFileList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a procedure genomics&#x27; notes
   *    Retrieves a procedure genomics&#x27; notes         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedNoteList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNoteList200 listProcedureGenomicsNotes(String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listProcedureGenomicsNotes");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling listProcedureGenomicsNotes");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/notes/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    GenericType<EnvelopedNoteList200> localVarReturnType = new GenericType<EnvelopedNoteList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a procedure&#x27;s genomics
   *    Retrieves a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedTagResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagResponseList200 listProcedureGenomicsTags(String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listProcedureGenomicsTags");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling listProcedureGenomicsTags");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/tags/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    GenericType<EnvelopedTagResponseList200> localVarReturnType = new GenericType<EnvelopedTagResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves similar procedures to the specified procedure
   *    Retrieves similar procedures to the specified procedure         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @param maxThreshold Only similarity matches at value equal or below max_threshold will be considered (optional)
   * @param method Method to use to find similarities (optional, default to semantic_similarity)
   * @param minThreshold Only similarity matches at value equal or above min_threshold will be considered (optional)
   * @return EnvelopedProcedureList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureList200 listProcedureSimilarities(String binaryId, String rva, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, Float maxThreshold, String method, Float minThreshold) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling listProcedureSimilarities");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling listProcedureSimilarities");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/similarities/{rva}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "max_threshold", maxThreshold));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "method", method));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "min_threshold", minThreshold));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedProcedureList200> localVarReturnType = new GenericType<EnvelopedProcedureList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Removes a user&#x27;s ownership from a single file
   *    Removes a user&#x27;s ownership from a single file         
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void removeFile(String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling removeFile");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/"
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
   * Remove an existing tag from a file
   *    Remove an existing tag from a file         
   * @param binaryId  (required)
   * @param tagId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param force MUST be true for any &#x60;DELETE&#x60; method to take place (optional)
   * @throws ApiException if fails to make API call
   */
  public void removeFileTag(String binaryId, String tagId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling removeFileTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling removeFileTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/tags/{tag_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "tag_id" + "\\}", apiClient.escapeString(tagId.toString()));

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
   * Search for files based on given parameters
   *    Search for files based on given parameters         
   * @param query Search query to look for (required)
   * @param type Value type with which to search (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param pageCount  (optional, default to 1)
   * @param pageSize  (optional, default to 50)
   * @param skipCount  (optional, default to 0)
   * @return EnvelopedFileSearchResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileSearchResponseList200 searchForFile(String query, String type, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'query' is set
    if (query == null) {
      throw new ApiException(400, "Missing the required parameter 'query' when calling searchForFile");
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      throw new ApiException(400, "Missing the required parameter 'type' when calling searchForFile");
    }
    // create path and map variables
    String localVarPath = "/files/search/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "type", type));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileSearchResponseList200> localVarReturnType = new GenericType<EnvelopedFileSearchResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Updates a single file
   *    Updates a single file         
   * @param binaryId  (required)
   * @param _public  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedFileUpdateResponse206
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileUpdateResponse206 updateFile(String binaryId, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateFile");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "update_mask", updateMask));

    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileUpdateResponse206> localVarReturnType = new GenericType<EnvelopedFileUpdateResponse206>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * 
   * Updates a single specific note attached to a file for user
   * @param binaryId  (required)
   * @param noteId  (required)
   * @param note  (optional)
   * @param _public  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 updateFileNote(String binaryId, String noteId, String note, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateFileNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling updateFileNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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

    if (note != null)
      localVarFormParams.put("note", note);
    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Edits a procedure&#x27;s genomics
   *    Edits a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param procedureName  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedProcedureResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureResponse200 updateFileProcedureGenomics(String binaryId, String rva, String procedureName, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateFileProcedureGenomics");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling updateFileProcedureGenomics");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()));

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

    if (procedureName != null)
      localVarFormParams.put("procedure_name", procedureName);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedProcedureResponse200> localVarReturnType = new GenericType<EnvelopedProcedureResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Update a pre-existing file tag
   *    Update a pre-existing file tag         
   * @param binaryId  (required)
   * @param tagId  (required)
   * @param _public  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedIdList201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedIdList201 updateFileTag(String binaryId, String tagId, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateFileTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling updateFileTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/tags/{tag_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "tag_id" + "\\}", apiClient.escapeString(tagId.toString()));

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

    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedIdList201> localVarReturnType = new GenericType<EnvelopedIdList201>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Edits a note on a procedure
   *    Edits a note on a procedure         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param noteId  (required)
   * @param note  (optional)
   * @param _public  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param updateMask REQUIRED for &#x60;PATCH&#x60; methods. Comma separated string containing a list of keys to update based on the request body.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 updateProcedureGenomicsNote(String binaryId, String rva, String noteId, String note, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateProcedureGenomicsNote");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling updateProcedureGenomicsNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling updateProcedureGenomicsNote");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/notes/{note_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()))
      .replaceAll("\\{" + "note_id" + "\\}", apiClient.escapeString(noteId.toString()));

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

    if (note != null)
      localVarFormParams.put("note", note);
    if (_public != null)
      localVarFormParams.put("public", _public);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Updates a tag from a procedure&#x27;s genomics
   *    Updates a tag from a procedure&#x27;s genomics         
   * @param binaryId  (required)
   * @param rva  (required)
   * @param tagId  (required)
   * @param _public  (optional)
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
  public EnvelopedTag200 updateProcedureGenomicsTag(String binaryId, String rva, String tagId, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling updateProcedureGenomicsTag");
    }
    // verify the required parameter 'rva' is set
    if (rva == null) {
      throw new ApiException(400, "Missing the required parameter 'rva' when calling updateProcedureGenomicsTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling updateProcedureGenomicsTag");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/genomics/{rva}/tags/{tag_id}/"
      .replaceAll("\\{" + "binary_id" + "\\}", apiClient.escapeString(binaryId.toString()))
      .replaceAll("\\{" + "rva" + "\\}", apiClient.escapeString(rva.toString()))
      .replaceAll("\\{" + "tag_id" + "\\}", apiClient.escapeString(tagId.toString()));

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

    if (_public != null)
      localVarFormParams.put("public", _public);

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
   * Upload an archive containing extracted data to be juiced
   *    Upload an archive containing extracted data to be juiced         
   * @param filedata  (required)
   * @param filetype  (required)
   * @param binaryId  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedFileUploadResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileUploadResponse200 uploadDisassembly(File filedata, String filetype, String binaryId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'filedata' is set
    if (filedata == null) {
      throw new ApiException(400, "Missing the required parameter 'filedata' when calling uploadDisassembly");
    }
    // verify the required parameter 'filetype' is set
    if (filetype == null) {
      throw new ApiException(400, "Missing the required parameter 'filetype' when calling uploadDisassembly");
    }
    // verify the required parameter 'binaryId' is set
    if (binaryId == null) {
      throw new ApiException(400, "Missing the required parameter 'binaryId' when calling uploadDisassembly");
    }
    // create path and map variables
    String localVarPath = "/files/{binary_id}/disassembly/"
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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dryrun", dryrun));

    if (filedata != null)
      localVarFormParams.put("filedata", filedata);
    if (filetype != null)
      localVarFormParams.put("filetype", filetype);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileUploadResponse200> localVarReturnType = new GenericType<EnvelopedFileUploadResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Upload new files for processing
   *    Multiple files may be uploaded at once up to 100MB.    Tags and notes can also be attached at the time of upload.         
   * @param filedata  (required)
   * @param password  (required)
   * @param tags  (required)
   * @param notes  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @param extract If true, all extracted files from an archive will be top level and the archive thrown away (optional)
   * @param recursive If true, all archives found in the upload will be stripped and thrown (optional)
   * @param retainWrapper If true with extract, then the archive will not be thrown away (optional, default to true)
   * @param skipUnpack If true, Unknown Cyber&#x27;s default unpacker stage will be skipped (optional)
   * @param b64 If true, treat the incoming filedata as a base64 encoded string (optional)
   * @return EnvelopedFileUploadResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileUploadResponseList200 uploadFile(List<File> filedata, String password, List<String> tags, List<String> notes, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun, Boolean extract, Boolean recursive, Boolean retainWrapper, Boolean skipUnpack, Boolean b64) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'filedata' is set
    if (filedata == null) {
      throw new ApiException(400, "Missing the required parameter 'filedata' when calling uploadFile");
    }
    // verify the required parameter 'password' is set
    if (password == null) {
      throw new ApiException(400, "Missing the required parameter 'password' when calling uploadFile");
    }
    // verify the required parameter 'tags' is set
    if (tags == null) {
      throw new ApiException(400, "Missing the required parameter 'tags' when calling uploadFile");
    }
    // verify the required parameter 'notes' is set
    if (notes == null) {
      throw new ApiException(400, "Missing the required parameter 'notes' when calling uploadFile");
    }
    // create path and map variables
    String localVarPath = "/files/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "extract", extract));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "recursive", recursive));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "retain_wrapper", retainWrapper));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "skip_unpack", skipUnpack));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "b64", b64));

    if (filedata != null)
      localVarFormParams.put("filedata", filedata);
    if (password != null)
      localVarFormParams.put("password", password);
    if (tags != null)
      localVarFormParams.put("tags", tags);
    if (notes != null)
      localVarFormParams.put("notes", notes);

    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedFileUploadResponseList200> localVarReturnType = new GenericType<EnvelopedFileUploadResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
