package com.unknowncyber.magic.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import javax.ws.rs.core.GenericType;

import com.unknowncyber.magic.model.BadRequestResponse;
import com.unknowncyber.magic.model.EnvelopedBulkProcedureResponseList200;
import com.unknowncyber.magic.model.EnvelopedFileList200;
import com.unknowncyber.magic.model.EnvelopedNamelessNoteList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedNote200;
import com.unknowncyber.magic.model.EnvelopedNote201;
import com.unknowncyber.magic.model.EnvelopedProcedureGroup200;
import com.unknowncyber.magic.model.EnvelopedProcedureSimilarityResponse200;
import com.unknowncyber.magic.model.EnvelopedProcedureTagCreatedResponse201;
import com.unknowncyber.magic.model.EnvelopedTag200;
import com.unknowncyber.magic.model.EnvelopedTagList200EnvelopedIdList200;
import com.unknowncyber.magic.model.EnvelopedYaraProcedure200;
import com.unknowncyber.magic.model.ForbiddenResponse;
import com.unknowncyber.magic.model.NotFoundResponse;
import com.unknowncyber.magic.model.UnauthenticatedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProceduresApi {
  private ApiClient apiClient;

  public ProceduresApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ProceduresApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Adds a tag to a procedure
   *    Adds a tag to a procedure         
   * @param procHash  (required)
   * @param name  (optional)
   * @param color  (optional)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedProcedureTagCreatedResponse201
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureTagCreatedResponse201 addProcedureTag(String procHash, String name, String color, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling addProcedureTag");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/tags/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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

    GenericType<EnvelopedProcedureTagCreatedResponse201> localVarReturnType = new GenericType<EnvelopedProcedureTagCreatedResponse201>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Get procedures in bulk
   *    Get procedures in bulk         
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
   * @param action Used in bulk queries. Bulk queries are always POST, so &#x27;action&#x27; allows the user to set the desired method (optional)
   * @return EnvelopedBulkProcedureResponseList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedBulkProcedureResponseList200 bulkProcedureOperation(List<String> ids, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String action) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'ids' is set
    if (ids == null) {
      throw new ApiException(400, "Missing the required parameter 'ids' when calling bulkProcedureOperation");
    }
    // create path and map variables
    String localVarPath = "/procedures/bulk/";

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

    GenericType<EnvelopedBulkProcedureResponseList200> localVarReturnType = new GenericType<EnvelopedBulkProcedureResponseList200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Adds a note to a procedure
   *    Adds a note to a procedure         
   * @param note  (required)
   * @param _public  (required)
   * @param procHash  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedNote200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNote200 createProcedureNote(String note, Boolean _public, String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'note' is set
    if (note == null) {
      throw new ApiException(400, "Missing the required parameter 'note' when calling createProcedureNote");
    }
    // verify the required parameter '_public' is set
    if (_public == null) {
      throw new ApiException(400, "Missing the required parameter '_public' when calling createProcedureNote");
    }
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling createProcedureNote");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/notes/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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

    GenericType<EnvelopedNote200> localVarReturnType = new GenericType<EnvelopedNote200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Create Yara Rule based on given multiple procedures
   *    Create Yara Rule based on given multiple procedures         
   * @param procHashes  (required)
   * @param name  (required)
   * @param condition  (required)
   * @param includeAll  (required)
   * @param maxSignatures  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param dryrun If True, don&#x27;t cause any side effects.(Useful to check that an endpoint will work as constructed) (optional)
   * @return EnvelopedYaraProcedure200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedYaraProcedure200 createProceduresYara(List<String> procHashes, String name, String condition, Boolean includeAll, Integer maxSignatures, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean dryrun) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHashes' is set
    if (procHashes == null) {
      throw new ApiException(400, "Missing the required parameter 'procHashes' when calling createProceduresYara");
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      throw new ApiException(400, "Missing the required parameter 'name' when calling createProceduresYara");
    }
    // verify the required parameter 'condition' is set
    if (condition == null) {
      throw new ApiException(400, "Missing the required parameter 'condition' when calling createProceduresYara");
    }
    // verify the required parameter 'includeAll' is set
    if (includeAll == null) {
      throw new ApiException(400, "Missing the required parameter 'includeAll' when calling createProceduresYara");
    }
    // verify the required parameter 'maxSignatures' is set
    if (maxSignatures == null) {
      throw new ApiException(400, "Missing the required parameter 'maxSignatures' when calling createProceduresYara");
    }
    // create path and map variables
    String localVarPath = "/procedures/yara/";

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

    if (procHashes != null)
      localVarFormParams.put("proc_hashes", procHashes);
    if (name != null)
      localVarFormParams.put("name", name);
    if (condition != null)
      localVarFormParams.put("condition", condition);
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

    GenericType<EnvelopedYaraProcedure200> localVarReturnType = new GenericType<EnvelopedYaraProcedure200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Deletes a procedure&#x27;s note
   *    Deletes a procedure&#x27;s note         
   * @param procHash  (required)
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
  public void deleteProcedureNote(String procHash, String noteId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling deleteProcedureNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling deleteProcedureNote");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/notes/{note_id}/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()))
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
   * Deletes a procedure&#x27;s tag
   *    Deletes a procedure&#x27;s tag         
   * @param procHash  (required)
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
  public void deleteProcedureTag(String procHash, String tagId, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Boolean force) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling deleteProcedureTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling deleteProcedureTag");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/tags/{tag_id}/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()))
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
   * Get a single procedure
   *    Get a single procedure         
   * @param procHash  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedProcedureGroup200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureGroup200 getProcedure(String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String readMask, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling getProcedure");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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

    GenericType<EnvelopedProcedureGroup200> localVarReturnType = new GenericType<EnvelopedProcedureGroup200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retrieves a procedure&#x27;s files
   *    Retrieves a procedure&#x27;s files         
   * @param procHash  (required)
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
   * @return EnvelopedFileList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedFileList200 listProcedureFiles(String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String readMask, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling listProcedureFiles");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/files/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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
   * Lists a procedure&#x27;s notes
   *    Lists a procedure&#x27;s notes         
   * @param procHash  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @return EnvelopedNamelessNoteList200EnvelopedIdList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedNamelessNoteList200EnvelopedIdList200 listProcedureNotes(String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String expandMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling listProcedureNotes");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/notes/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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

    GenericType<EnvelopedNamelessNoteList200EnvelopedIdList200> localVarReturnType = new GenericType<EnvelopedNamelessNoteList200EnvelopedIdList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists a procedure&#x27;s similarities
   *    Lists a procedure&#x27;s similarities         
   * @param procHash  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @return EnvelopedProcedureSimilarityResponse200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedProcedureSimilarityResponse200 listProcedureSimilarities2(String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling listProcedureSimilarities2");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/similarities/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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

    GenericType<EnvelopedProcedureSimilarityResponse200> localVarReturnType = new GenericType<EnvelopedProcedureSimilarityResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Lists a procedure&#x27;s tags
   *    Lists a procedure&#x27;s tags         
   * @param procHash  (required)
   * @param format Format of the response from this endpoint (optional, default to json)
   * @param explain Shows the explain for this endpoint (optional)
   * @param download Determines whether to download the response.(Content-Disposition:\&quot;attachment\&quot; vs \&quot;inline\&quot;) (optional)
   * @param filename If download is True, this sets the name of the file. (Content-Disposition:\&quot;attachment; filename&#x3D;&#x60;filename&#x60;\&quot;) (optional)
   * @param noLinks Removes the &#x27;links&#x27; key (optional)
   * @param uri Use resource uri&#x27;s in place of string ids (optional)
   * @param expandMask Comma separated string containing a list of relation keys to &#x60;expand&#x60; and show the entire object inline.   REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60; (optional)
   * @param readMask  Comma separated string containing a list of keys to include in the response. &#x60;*&#x60; returns all keys.  REGEX: &#x60;^(([\\w]+,?)*|\\*)$&#x60;  (optional)
   * @return EnvelopedTagList200EnvelopedIdList200
   * @throws ApiException if fails to make API call
   */
  public EnvelopedTagList200EnvelopedIdList200 listProcedureTags(String procHash, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String expandMask, String readMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling listProcedureTags");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/tags/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()));

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "read_mask", readMask));


    final String[] localVarAccepts = {
      "application/json", "application/xml", "text/csv", "application/taxii+json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Api Key Header Authentication", "Api Key Query Authentication", "Basic Authentication", "JWT Access Token Authentication" };

    GenericType<EnvelopedTagList200EnvelopedIdList200> localVarReturnType = new GenericType<EnvelopedTagList200EnvelopedIdList200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Get all procedures
   *    Get all procedures         
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
   * @param filters  Semi-colon separated string of filters. Each filter has a pattern of &#x60;(not)? &lt;var&gt;__&lt;comp&gt;(value)&#x60;   REGEX: &#x60;^(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\)(\\ +(AND|OR|;)\\ +(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\))*$&#x60;,  (optional)
   * @param unpacked Whether to use unpacked or original binaries (optional)
   * @param files A comma separated list of file hashes (optional)
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public void listProcedures(String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, Integer pageCount, Integer pageSize, Integer skipCount, String readMask, String filters, Boolean unpacked, List files) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/procedures/";

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
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filters", filters));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "unpacked", unpacked));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "files", files));


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
   * Updates a note from a procedure group
   *    Updates a note from a procedure group         
   * @param procHash  (required)
   * @param noteId  (required)
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
  public EnvelopedNote200 updateProcedureNote(String procHash, String noteId, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling updateProcedureNote");
    }
    // verify the required parameter 'noteId' is set
    if (noteId == null) {
      throw new ApiException(400, "Missing the required parameter 'noteId' when calling updateProcedureNote");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/notes/{note_id}/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()))
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
   * Updates a tag from a procedure group
   *    Updates a tag from a procedure group         
   * @param procHash  (required)
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
  public EnvelopedTag200 updateProcedureTag(String procHash, String tagId, Boolean _public, String format, Boolean explain, Boolean download, String filename, Boolean noLinks, Boolean uri, String updateMask) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'procHash' is set
    if (procHash == null) {
      throw new ApiException(400, "Missing the required parameter 'procHash' when calling updateProcedureTag");
    }
    // verify the required parameter 'tagId' is set
    if (tagId == null) {
      throw new ApiException(400, "Missing the required parameter 'tagId' when calling updateProcedureTag");
    }
    // create path and map variables
    String localVarPath = "/procedures/{proc_hash}/tags/{tag_id}/"
      .replaceAll("\\{" + "proc_hash" + "\\}", apiClient.escapeString(procHash.toString()))
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
}
