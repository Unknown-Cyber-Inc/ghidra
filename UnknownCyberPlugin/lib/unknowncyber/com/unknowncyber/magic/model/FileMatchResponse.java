/*
 * MAGIC™ API
 * --- # The API for accessing Unknown Cyber MAGIC products and services.  ---  ## Authentication  **(Head to our [/auth](../auth/swagger) api to register, login, or generate a token)**  Supported Authentication Schemes:   * HTTP Basic Authentication  * API-KEY in the `X-API-KEY` request header  * JWT token in the `Authorization:\"Bearer {token}\"` request header  ---  ## Content Negotiation    There are two ways to specify the content type of the response. In order of precedence:     * The **Accept** request header can be set with the desired mime type. The most specific version will prevail. i.e. *application/json* > *application/\\**.       *Accept:\"application/json\"*     * The **format** query parameter. (MUST be in lower case)       *?format=json*    Supported Formats:     | query parameter | Accept Header            |         |    |-----------------|--------------------------|---------|    | **json**        | application/json         | Default |    | **xml**         | application/xml          |         |    | **csv**         | text/csv                 |         |    | **txt**         | text/plain               |         |  --- ## Requests  Supported HTTP Methods:   * **GET**  * **POST**  * **PATCH**  * **DELETE**  * **HEAD**  * **OPTIONS**  Every request supports the following query parameters:   * **explain** - (bool) - Returns a detailed explanation of what the endpoint does, as well as potential query parameters that can be used to customize the results    * **download** - (bool) - If set to a truthy value, acts as setting the 'Content-Disposition' header to *\"attachment;\"* and will download the response as a file.   * **filename** - (str) - The filename to use for a downloaded file. Ignored if no file is being downloaded.        * **format** - (str) - Used in a similar manner to the *Accept* Header. Use this to specify which format you want the response returned in. Defaults to *application/json*. Current acceptable values are:      * **json** - (application/json)     * **xml** - (application/xml)     * **csv** - (text/csv)     * **txt** - (text/plain)         * Custom type that returns a description of usage of the endpoint   * **no_links** - (bool) - If set to a truthy value, links will be disabled from the response   * **uri** - (bool) - If set to a truthy value, id lists will be returned as uris instead of id strings.  ---  ## GET Conventions ### Possible query parameters:   **(Check each endpoint description, or use *explain*, for a list of available values for each parameter)**    * **read_mask** - A list of values (keys) to return for the resource or each resource within the list     * Comma separated string of variables     * Leaving this field blank will return the default values.     * Setting this value equal to **`*`** will include **ALL** possible keys.     * Traversal is allowed with the **`.`** operator.     * There are three special keys that can be used with all endponts         * **`*`** - This will return all possible values available         * **`_self`** - This will include the resources uri         * **`_default`** - This will include all default values (Those given with an empty read_mask)           * This would typically be used in conjunction with other 'non-default' fields       * Ex:         * `_default,family,category,_self`    * **dynamic_mask** - A list of dynamically generated values to return about the resource or each resource within the list     * Comma separated string of variables     * Operates the same as read_mask, but each variable will incur a much greater time cost.     * *May* cause timeouts     * Leaving this field blank or empty will return no dynamic variables.    * **expand_mask** - A list of relational variables to *expand* upon and return more than just the ids     * Comma separated string of variables     * Leaving this field blank will cause all relational data to be returned as a list of ids     * Ex:         * The `children` field for a file may return a list of ids normally, but with `children` set in the           `expand_mask`, it can return a list of child File objects with greater details.  ---  ## POST Conventions  This will create a new resource.  The resource data shall be provided in the request body.  The response will be either a 200 or 201, along with a uri to the newly created resource in the `Location` header.  In the case of a long running job, or reprocess, the response will be a 202 along with a **job_id** and it's corresponding **job_uri** that can be used in the *_/jobs/_* endpoint to see the updated status  ---  ## PATCH Conventions   * The update data shall be provided in the request body.  ### Possible query parameters:   **(Check each endpoint description, or use *explain*, for a list of available values for each parameter)**    * **update_mask** - A list of values to update with this request.     * Comma separated string of variables     * This is required to be set for any and all **PATCH** requests to be processed.     * ONLY the specified variables in the update_mask will be updated regardless of the data in the request body.     * An empty or missing *update_mask* **WILL** result in a 400 Bad Request response  ---  ## DELETE Conventions  A successful response will return 204 No Content  ### Possible query parameters:   * **force** - Forces the deletion to go through     * This is required to be set as a truthy value for any and all **DELETE** requests to be processed.     * Not specifying this on a DELETE request (without *explain* set) **WILL** return a 400 Bad Request response   ---  ## *bulk* endpoints  **Bulk** endpoints are the ones that follow the  '*_/<resource\\>/bulk/_*' convention. They operate in the same fashion as the single resource endpoints ('*_/<resource\\>/<resource_id\\>/_*') except they can process multiple resources on a single call.  They **MUST** be a **POST** request along with the accompanying request body parameter to work:    * **ids** - A list of ids to operate on (For **GET**, **PATCH**, and **DELETE** bulk requests)   * **resources** - A list of resources to operate on (For **POST** bulk requests)  ### Possible query parameters:   **(Check each endpoint description, or use *explain*, for a list of available actions)**    * **action** - This is a string and can only be one of four values:      * **GET** - Returns a list of the resources, in the same order as provided in the request body.      * **POST** - Acts the same as a post on the pluralized resource endpoint.         * Instead of an **ids** request body parameter being provided in the request body, a **resources** list of new resources must be provided.      * **PATCH** - Acts the same as a patch on a single resource.          * Follows the same **PATCH** conventions from above*      * **DELETE** - Acts the same as a delete on a single resource.          * Follows the same **DELETE** conventions from above*    * **strict** - Causes the bulk endpoint to fail if a single provided id fails     * Boolean     * If set to True, the bulk call will ONLY operate if it is successful on ALL requested resources.     * If even a single resource is non-existent/forbidden, the call will fail and no side effects will take place.  ---  ## Pagination:  Pagination can be done in combination with sorting and filtering on most endpoints that deal with lists (including **PATCH** and **DELETE** calls)  ### Pagination query paramters:        * **page_size** - The number of results to return (default: 50)   * **page_count** - The page used in pagination (default: 1)   * **skip_count** - A specified number of values to skip before collecting values (default: 0)  ---  ## Sorting:  Sorting can be done in combination with filtering and pagination on most endpoints that deal with lists (including **PATCH** and **DELETE** calls)  ### Sorting query parameter:   **(Check each endpoint description, or use *explain*, for a list of available sorters)**    * **order_by** - A list of variables to sort the query on     * Comma separated string of variables     * Regex Pattern - `^(-?[\\w]+,?)*$`     * Variables are sorted in ascending order by default     * Prepend the variable with a `-` to change it to descending order     * Multiple sorters can be specified, with precedence matching the order of the parameter     * Ex:         * `-object_class,create_time`  ---  ## Filtering:  Filtering can be done in combination with pagination and sorting on most endpoints that deal with lists (including **PATCH** and **DELETE** calls)  ### Filters query parameter:   **(Check each endpoint description, or use *explain*, for a list of available filters)**    * **filters** - A string of filters used to narrow down the query results.     * Semi-colon separated string of variables     * Regex patterns:         * Single filter:             * `^\\ *(NOT\\ +)?[\\w]+__[a-z]+\\(.+\\)\\ *`              * `NOT variable__comparator(value)`          * Multiple Filters:             * `^{SINGLE_FILTER_REGEX}(\\ +(AND|OR|;)\\ +{SINGLE_FILTER_REGEX})*$`              * `NOT variable__comparator(value) AND NOT variable__comparator(value); variable__comparator(value)`      * Logical operator order of precedence:         * **AND**         * **OR**         * **;** **(Semi-colon separation denotes conjunction)**         * Example order of precedence:             * **exp1;exp2 AND exp3 OR exp4** is equivalent to **(exp1) AND ((exp2 AND exp3) OR (exp4))**      * Available Comparators:         * **eq** - Equal         * **ne** - Not Equal         * **lt** - Less than         * **lte** - Less than or equal         * **gt** - Greater than         * **gte** - Greater than or equal         * **in** - In (for list values)         * **nin** - Not In (for list values)         * **regex** - Regular Expression Match         * **iregex** - Case Insensitive Regular Expression Match      * The format for **in** and **nin** which operate on arrays is:         * **[]** - The list of values must be enclosed within brackets.         * **,** - The value separtion token is a comma.         * **<variable\\>__<comp\\>([<value1\\>,<value2\\>])**      * Examples:         * `create_time__gte(2022-01-01T13:11:02);object_class__regex(binary.*)`          * `create_time__gte(2022-01-01) AND create_time__lt(2022-02-01) AND NOT match_count__gt(10)`          * `create_time__gte(2022-01-01) AND create_time__lt(2022-02-01)`  ---  ## Responses  All responses **WILL** be of type `APIResponse` and contain the following fields:  * `success` | Boolean value indicating if the operation succeeded.  * `status` | Status code. Corresponds to the HTTP status code.   * `message` | A human readable message providing more details about the operation.  * `links` | A dictionary of `name`: `uri` links providing navigation and state-based actions on resources  * `errors` | Array of error objects. An error object contains the following properties:      * `reason` | Unique identifier for this error. Ex: \"FileNotFoundError\".      * `message`| Human readable error message.      * `parameter`| The parameter (if any) that caused the issue.  Successful operations **MUST** return a `SuccessResponse`, which extends `APIResponse` by adding:  * `success` | **MUST** equal True  * `resource` | Properties containing the response object.     * (In the case of a single entity being returned)  **OR**  * `resources` | A list of response objects.     * (In the case of a list of entities being returned)  Failed Operations **MUST** return an `ErrorResponse`, which extends `APIResponse` by adding:  * `success` | **MUST** equal False.  Common Failed Operations that you may hit on any of the endpoint operations:  * 400 - Bad Request - The request is malformed  * 401 - Unauthorized - All endpoints require authorization  * 403 - Forbidden - The endpoint (with the given parameters) is not available to you  * 404 - Not Found - The endpoint doesn't exist, or the resource being searched for doesn't exist  ---  ## Example Inputs  Here are some example inputs that can be used for testing the service:  * `binary_id`: **ff9790d7902fea4c910b182f6e0b00221a40d616**  * `proc_rva`: **0x1000**  * `search_query`: **ransomware**  --- 
 *
 * OpenAPI spec version: 2.0.0 (v2)
 * Contact: support@unknowncyber.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.unknowncyber.magic.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unknowncyber.magic.model.FileIndicator;
import com.unknowncyber.magic.model.FileProcedures;
import com.unknowncyber.magic.model.FileSimilarityObject;
import com.unknowncyber.magic.model.HashSchema;
import com.unknowncyber.magic.model.MatchSubtype;
import com.unknowncyber.magic.model.UploadTimesChild;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
/**
 * FileMatchResponse
 */


public class FileMatchResponse implements Serializable{
  private static final long serialVersionUID = 1L;
  @JsonProperty("public")
  private Boolean _public = null;

  @JsonProperty("sha1")
  private String sha1 = null;

  @JsonProperty("md5")
  private String md5 = null;

  @JsonProperty("sha256")
  private String sha256 = null;

  @JsonProperty("sha512")
  private String sha512 = null;

  @JsonProperty("filetype")
  private String filetype = null;

  @JsonProperty("object_class")
  private String objectClass = null;

  @JsonProperty("filenames")
  private List<String> filenames = null;

  @JsonProperty("create_time")
  private Date createTime = null;

  @JsonProperty("match_count")
  private Integer matchCount = null;

  @JsonProperty("upload_time")
  private Date uploadTime = null;

  @JsonProperty("upload_times")
  private List<UploadTimesChild> uploadTimes = null;

  @JsonProperty("children")
  private OneOfFileMatchResponseChildren children = null;

  @JsonProperty("parents")
  private List<String> parents = null;

  @JsonProperty("owned")
  private Boolean owned = null;

  @JsonProperty("notes")
  private OneOfFileMatchResponseNotes notes = null;

  @JsonProperty("tags")
  private List<String> tags = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("pipeline")
  private Map<String, String> pipeline = null;

  @JsonProperty("campaign")
  private AllOfFileMatchResponseCampaign campaign = null;

  @JsonProperty("matches")
  private String matches = null;

  @JsonProperty("av_names")
  private List<String> avNames = null;

  @JsonProperty("scanner_count")
  private Integer scannerCount = null;

  @JsonProperty("detection_count")
  private Integer detectionCount = null;

  @JsonProperty("evasiveness")
  private Double evasiveness = null;

  @JsonProperty("scan_date")
  private Date scanDate = null;

  @JsonProperty("token_list")
  private List<String> tokenList = null;

  @JsonProperty("threat")
  private String threat = null;

  @JsonProperty("labels")
  private AllOfFileMatchResponseLabels labels = null;

  @JsonProperty("unmapped")
  private AllOfFileMatchResponseUnmapped unmapped = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("categories")
  private AllOfFileMatchResponseCategories categories = null;

  @JsonProperty("family")
  private String family = null;

  @JsonProperty("families")
  private AllOfFileMatchResponseFamilies families = null;

  @JsonProperty("avscan")
  private AllOfFileMatchResponseAvscan avscan = null;

  @JsonProperty("indicators")
  private List<FileIndicator> indicators = null;

  @JsonProperty("reputation")
  private AllOfFileMatchResponseReputation reputation = null;

  @JsonProperty("yara")
  private String yara = null;

  @JsonProperty("procedures")
  private List<FileProcedures> procedures = null;

  @JsonProperty("procedure_group")
  private String procedureGroup = null;

  @JsonProperty("unpacked_procedures")
  private List<FileProcedures> unpackedProcedures = null;

  @JsonProperty("genomics")
  private Map<String, HashSchema> genomics = null;

  @JsonProperty("unpacked_genomics")
  private Map<String, HashSchema> unpackedGenomics = null;

  @JsonProperty("similarities")
  private List<FileSimilarityObject> similarities = null;

  @JsonProperty("_self")
  private String _self = null;

  @JsonProperty("match_subtypes")
  private List<MatchSubtype> matchSubtypes = null;

  @JsonProperty("match_type")
  private String matchType = null;

  @JsonProperty("max_similarity")
  private Double maxSimilarity = null;

  public FileMatchResponse _public(Boolean _public) {
    this._public = _public;
    return this;
  }

   /**
   * Get _public
   * @return _public
  **/
  @Schema(description = "")
  public Boolean isPublic() {
    return _public;
  }

  public void setPublic(Boolean _public) {
    this._public = _public;
  }

   /**
   * Get sha1
   * @return sha1
  **/
  @Schema(description = "")
  public String getSha1() {
    return sha1;
  }

  public FileMatchResponse md5(String md5) {
    this.md5 = md5;
    return this;
  }

   /**
   * Get md5
   * @return md5
  **/
  @Schema(description = "")
  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public FileMatchResponse sha256(String sha256) {
    this.sha256 = sha256;
    return this;
  }

   /**
   * Get sha256
   * @return sha256
  **/
  @Schema(description = "")
  public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  public FileMatchResponse sha512(String sha512) {
    this.sha512 = sha512;
    return this;
  }

   /**
   * Get sha512
   * @return sha512
  **/
  @Schema(description = "")
  public String getSha512() {
    return sha512;
  }

  public void setSha512(String sha512) {
    this.sha512 = sha512;
  }

  public FileMatchResponse filetype(String filetype) {
    this.filetype = filetype;
    return this;
  }

   /**
   * Get filetype
   * @return filetype
  **/
  @Schema(description = "")
  public String getFiletype() {
    return filetype;
  }

  public void setFiletype(String filetype) {
    this.filetype = filetype;
  }

  public FileMatchResponse objectClass(String objectClass) {
    this.objectClass = objectClass;
    return this;
  }

   /**
   * Get objectClass
   * @return objectClass
  **/
  @Schema(description = "")
  public String getObjectClass() {
    return objectClass;
  }

  public void setObjectClass(String objectClass) {
    this.objectClass = objectClass;
  }

  public FileMatchResponse filenames(List<String> filenames) {
    this.filenames = filenames;
    return this;
  }

  public FileMatchResponse addFilenamesItem(String filenamesItem) {
    if (this.filenames == null) {
      this.filenames = new ArrayList<String>();
    }
    this.filenames.add(filenamesItem);
    return this;
  }

   /**
   * Get filenames
   * @return filenames
  **/
  @Schema(description = "")
  public List<String> getFilenames() {
    return filenames;
  }

  public void setFilenames(List<String> filenames) {
    this.filenames = filenames;
  }

  public FileMatchResponse createTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * Get createTime
   * @return createTime
  **/
  @Schema(description = "")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public FileMatchResponse matchCount(Integer matchCount) {
    this.matchCount = matchCount;
    return this;
  }

   /**
   * Get matchCount
   * @return matchCount
  **/
  @Schema(description = "")
  public Integer getMatchCount() {
    return matchCount;
  }

  public void setMatchCount(Integer matchCount) {
    this.matchCount = matchCount;
  }

   /**
   * Get uploadTime
   * @return uploadTime
  **/
  @Schema(description = "")
  public Date getUploadTime() {
    return uploadTime;
  }

   /**
   * Get uploadTimes
   * @return uploadTimes
  **/
  @Schema(description = "")
  public List<UploadTimesChild> getUploadTimes() {
    return uploadTimes;
  }

   /**
   * self referral field
   * @return children
  **/
  @Schema(description = "self referral field")
  public OneOfFileMatchResponseChildren getChildren() {
    return children;
  }

   /**
   * self referral field
   * @return parents
  **/
  @Schema(description = "self referral field")
  public List<String> getParents() {
    return parents;
  }

   /**
   * Get owned
   * @return owned
  **/
  @Schema(description = "")
  public Boolean isOwned() {
    return owned;
  }

   /**
   * self referral field
   * @return notes
  **/
  @Schema(description = "self referral field")
  public OneOfFileMatchResponseNotes getNotes() {
    return notes;
  }

   /**
   * self referral field
   * @return tags
  **/
  @Schema(description = "self referral field")
  public List<String> getTags() {
    return tags;
  }

   /**
   * self referral field
   * @return status
  **/
  @Schema(description = "self referral field")
  public String getStatus() {
    return status;
  }

   /**
   * Get pipeline
   * @return pipeline
  **/
  @Schema(description = "")
  public Map<String, String> getPipeline() {
    return pipeline;
  }

   /**
   * Get campaign
   * @return campaign
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseCampaign getCampaign() {
    return campaign;
  }

   /**
   * Get matches
   * @return matches
  **/
  @Schema(description = "")
  public String getMatches() {
    return matches;
  }

   /**
   * Get avNames
   * @return avNames
  **/
  @Schema(description = "")
  public List<String> getAvNames() {
    return avNames;
  }

   /**
   * Get scannerCount
   * @return scannerCount
  **/
  @Schema(description = "")
  public Integer getScannerCount() {
    return scannerCount;
  }

   /**
   * Get detectionCount
   * @return detectionCount
  **/
  @Schema(description = "")
  public Integer getDetectionCount() {
    return detectionCount;
  }

   /**
   * Get evasiveness
   * @return evasiveness
  **/
  @Schema(description = "")
  public Double getEvasiveness() {
    return evasiveness;
  }

   /**
   * Get scanDate
   * @return scanDate
  **/
  @Schema(description = "")
  public Date getScanDate() {
    return scanDate;
  }

   /**
   * Get tokenList
   * @return tokenList
  **/
  @Schema(description = "")
  public List<String> getTokenList() {
    return tokenList;
  }

   /**
   * Get threat
   * @return threat
  **/
  @Schema(description = "")
  public String getThreat() {
    return threat;
  }

   /**
   * Get labels
   * @return labels
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseLabels getLabels() {
    return labels;
  }

   /**
   * Get unmapped
   * @return unmapped
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseUnmapped getUnmapped() {
    return unmapped;
  }

   /**
   * Get category
   * @return category
  **/
  @Schema(description = "")
  public String getCategory() {
    return category;
  }

   /**
   * Get categories
   * @return categories
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseCategories getCategories() {
    return categories;
  }

   /**
   * Get family
   * @return family
  **/
  @Schema(description = "")
  public String getFamily() {
    return family;
  }

   /**
   * Get families
   * @return families
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseFamilies getFamilies() {
    return families;
  }

   /**
   * Get avscan
   * @return avscan
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseAvscan getAvscan() {
    return avscan;
  }

   /**
   * Get indicators
   * @return indicators
  **/
  @Schema(description = "")
  public List<FileIndicator> getIndicators() {
    return indicators;
  }

   /**
   * Get reputation
   * @return reputation
  **/
  @Schema(description = "")
  public AllOfFileMatchResponseReputation getReputation() {
    return reputation;
  }

   /**
   * Get yara
   * @return yara
  **/
  @Schema(description = "")
  public String getYara() {
    return yara;
  }

   /**
   * Get procedures
   * @return procedures
  **/
  @Schema(description = "")
  public List<FileProcedures> getProcedures() {
    return procedures;
  }

   /**
   * Get procedureGroup
   * @return procedureGroup
  **/
  @Schema(description = "")
  public String getProcedureGroup() {
    return procedureGroup;
  }

   /**
   * Get unpackedProcedures
   * @return unpackedProcedures
  **/
  @Schema(description = "")
  public List<FileProcedures> getUnpackedProcedures() {
    return unpackedProcedures;
  }

   /**
   * Get genomics
   * @return genomics
  **/
  @Schema(description = "")
  public Map<String, HashSchema> getGenomics() {
    return genomics;
  }

   /**
   * Get unpackedGenomics
   * @return unpackedGenomics
  **/
  @Schema(description = "")
  public Map<String, HashSchema> getUnpackedGenomics() {
    return unpackedGenomics;
  }

   /**
   * Get similarities
   * @return similarities
  **/
  @Schema(description = "")
  public List<FileSimilarityObject> getSimilarities() {
    return similarities;
  }

   /**
   * Get _self
   * @return _self
  **/
  @Schema(description = "")
  public String getSelf() {
    return _self;
  }

   /**
   * Get matchSubtypes
   * @return matchSubtypes
  **/
  @Schema(description = "")
  public List<MatchSubtype> getMatchSubtypes() {
    return matchSubtypes;
  }

   /**
   * Get matchType
   * @return matchType
  **/
  @Schema(description = "")
  public String getMatchType() {
    return matchType;
  }

   /**
   * Get maxSimilarity
   * @return maxSimilarity
  **/
  @Schema(description = "")
  public Double getMaxSimilarity() {
    return maxSimilarity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileMatchResponse fileMatchResponse = (FileMatchResponse) o;
    return Objects.equals(this._public, fileMatchResponse._public) &&
        Objects.equals(this.sha1, fileMatchResponse.sha1) &&
        Objects.equals(this.md5, fileMatchResponse.md5) &&
        Objects.equals(this.sha256, fileMatchResponse.sha256) &&
        Objects.equals(this.sha512, fileMatchResponse.sha512) &&
        Objects.equals(this.filetype, fileMatchResponse.filetype) &&
        Objects.equals(this.objectClass, fileMatchResponse.objectClass) &&
        Objects.equals(this.filenames, fileMatchResponse.filenames) &&
        Objects.equals(this.createTime, fileMatchResponse.createTime) &&
        Objects.equals(this.matchCount, fileMatchResponse.matchCount) &&
        Objects.equals(this.uploadTime, fileMatchResponse.uploadTime) &&
        Objects.equals(this.uploadTimes, fileMatchResponse.uploadTimes) &&
        Objects.equals(this.children, fileMatchResponse.children) &&
        Objects.equals(this.parents, fileMatchResponse.parents) &&
        Objects.equals(this.owned, fileMatchResponse.owned) &&
        Objects.equals(this.notes, fileMatchResponse.notes) &&
        Objects.equals(this.tags, fileMatchResponse.tags) &&
        Objects.equals(this.status, fileMatchResponse.status) &&
        Objects.equals(this.pipeline, fileMatchResponse.pipeline) &&
        Objects.equals(this.campaign, fileMatchResponse.campaign) &&
        Objects.equals(this.matches, fileMatchResponse.matches) &&
        Objects.equals(this.avNames, fileMatchResponse.avNames) &&
        Objects.equals(this.scannerCount, fileMatchResponse.scannerCount) &&
        Objects.equals(this.detectionCount, fileMatchResponse.detectionCount) &&
        Objects.equals(this.evasiveness, fileMatchResponse.evasiveness) &&
        Objects.equals(this.scanDate, fileMatchResponse.scanDate) &&
        Objects.equals(this.tokenList, fileMatchResponse.tokenList) &&
        Objects.equals(this.threat, fileMatchResponse.threat) &&
        Objects.equals(this.labels, fileMatchResponse.labels) &&
        Objects.equals(this.unmapped, fileMatchResponse.unmapped) &&
        Objects.equals(this.category, fileMatchResponse.category) &&
        Objects.equals(this.categories, fileMatchResponse.categories) &&
        Objects.equals(this.family, fileMatchResponse.family) &&
        Objects.equals(this.families, fileMatchResponse.families) &&
        Objects.equals(this.avscan, fileMatchResponse.avscan) &&
        Objects.equals(this.indicators, fileMatchResponse.indicators) &&
        Objects.equals(this.reputation, fileMatchResponse.reputation) &&
        Objects.equals(this.yara, fileMatchResponse.yara) &&
        Objects.equals(this.procedures, fileMatchResponse.procedures) &&
        Objects.equals(this.procedureGroup, fileMatchResponse.procedureGroup) &&
        Objects.equals(this.unpackedProcedures, fileMatchResponse.unpackedProcedures) &&
        Objects.equals(this.genomics, fileMatchResponse.genomics) &&
        Objects.equals(this.unpackedGenomics, fileMatchResponse.unpackedGenomics) &&
        Objects.equals(this.similarities, fileMatchResponse.similarities) &&
        Objects.equals(this._self, fileMatchResponse._self) &&
        Objects.equals(this.matchSubtypes, fileMatchResponse.matchSubtypes) &&
        Objects.equals(this.matchType, fileMatchResponse.matchType) &&
        Objects.equals(this.maxSimilarity, fileMatchResponse.maxSimilarity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_public, sha1, md5, sha256, sha512, filetype, objectClass, filenames, createTime, matchCount, uploadTime, uploadTimes, children, parents, owned, notes, tags, status, pipeline, campaign, matches, avNames, scannerCount, detectionCount, evasiveness, scanDate, tokenList, threat, labels, unmapped, category, categories, family, families, avscan, indicators, reputation, yara, procedures, procedureGroup, unpackedProcedures, genomics, unpackedGenomics, similarities, _self, matchSubtypes, matchType, maxSimilarity);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileMatchResponse {\n");
    
    sb.append("    _public: ").append(toIndentedString(_public)).append("\n");
    sb.append("    sha1: ").append(toIndentedString(sha1)).append("\n");
    sb.append("    md5: ").append(toIndentedString(md5)).append("\n");
    sb.append("    sha256: ").append(toIndentedString(sha256)).append("\n");
    sb.append("    sha512: ").append(toIndentedString(sha512)).append("\n");
    sb.append("    filetype: ").append(toIndentedString(filetype)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    filenames: ").append(toIndentedString(filenames)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    matchCount: ").append(toIndentedString(matchCount)).append("\n");
    sb.append("    uploadTime: ").append(toIndentedString(uploadTime)).append("\n");
    sb.append("    uploadTimes: ").append(toIndentedString(uploadTimes)).append("\n");
    sb.append("    children: ").append(toIndentedString(children)).append("\n");
    sb.append("    parents: ").append(toIndentedString(parents)).append("\n");
    sb.append("    owned: ").append(toIndentedString(owned)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    pipeline: ").append(toIndentedString(pipeline)).append("\n");
    sb.append("    campaign: ").append(toIndentedString(campaign)).append("\n");
    sb.append("    matches: ").append(toIndentedString(matches)).append("\n");
    sb.append("    avNames: ").append(toIndentedString(avNames)).append("\n");
    sb.append("    scannerCount: ").append(toIndentedString(scannerCount)).append("\n");
    sb.append("    detectionCount: ").append(toIndentedString(detectionCount)).append("\n");
    sb.append("    evasiveness: ").append(toIndentedString(evasiveness)).append("\n");
    sb.append("    scanDate: ").append(toIndentedString(scanDate)).append("\n");
    sb.append("    tokenList: ").append(toIndentedString(tokenList)).append("\n");
    sb.append("    threat: ").append(toIndentedString(threat)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    unmapped: ").append(toIndentedString(unmapped)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    family: ").append(toIndentedString(family)).append("\n");
    sb.append("    families: ").append(toIndentedString(families)).append("\n");
    sb.append("    avscan: ").append(toIndentedString(avscan)).append("\n");
    sb.append("    indicators: ").append(toIndentedString(indicators)).append("\n");
    sb.append("    reputation: ").append(toIndentedString(reputation)).append("\n");
    sb.append("    yara: ").append(toIndentedString(yara)).append("\n");
    sb.append("    procedures: ").append(toIndentedString(procedures)).append("\n");
    sb.append("    procedureGroup: ").append(toIndentedString(procedureGroup)).append("\n");
    sb.append("    unpackedProcedures: ").append(toIndentedString(unpackedProcedures)).append("\n");
    sb.append("    genomics: ").append(toIndentedString(genomics)).append("\n");
    sb.append("    unpackedGenomics: ").append(toIndentedString(unpackedGenomics)).append("\n");
    sb.append("    similarities: ").append(toIndentedString(similarities)).append("\n");
    sb.append("    _self: ").append(toIndentedString(_self)).append("\n");
    sb.append("    matchSubtypes: ").append(toIndentedString(matchSubtypes)).append("\n");
    sb.append("    matchType: ").append(toIndentedString(matchType)).append("\n");
    sb.append("    maxSimilarity: ").append(toIndentedString(maxSimilarity)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
