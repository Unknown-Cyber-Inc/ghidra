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
import com.unknowncyber.magic.model.BlockEA;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * FileProcedureResponse
 */


public class FileProcedureResponse implements Serializable{
  private static final long serialVersionUID = 1L;
  @JsonProperty("hard_hash")
  private String hardHash = "ecb50f092ebeeac4cf7804f8273d90e1";

  @JsonProperty("example_startEA")
  private String exampleStartEA = "0x1000";

  @JsonProperty("example_endEA")
  private String exampleEndEA = "0x101d";

  @JsonProperty("is_library")
  private Boolean isLibrary = false;

  @JsonProperty("example_procedure")
  private List<String> exampleProcedure = null;

  @JsonProperty("example_procedure_id")
  private String exampleProcedureId = "ce46741bf67591f5f60b7a74dec3ef8d648ca9c6/0x1000";

  @JsonProperty("status")
  private String status = "clone";

  @JsonProperty("example_blockEAs")
  private List<BlockEA> exampleBlockEAs = null;

  @JsonProperty("block_counts")
  private List<Integer> blockCounts = null;

  @JsonProperty("instr_counts")
  private List<Integer> instrCounts = null;

  @JsonProperty("byte_counts")
  private List<Integer> byteCounts = null;

  @JsonProperty("occurrence_counts")
  private Integer occurrenceCounts = 3;

  @JsonProperty("name_counts")
  private Integer nameCounts = 3;

  @JsonProperty("procedure_names")
  private List<String> procedureNames = null;

  @JsonProperty("coverage")
  private Integer coverage = 3;

  @JsonProperty("signature")
  private List<List<String>> signature = new ArrayList<List<String>>();

  @JsonProperty("signature_count")
  private Integer signatureCount = 3;

  @JsonProperty("example_procedure_count")
  private Integer exampleProcedureCount = 3;

  public FileProcedureResponse hardHash(String hardHash) {
    this.hardHash = hardHash;
    return this;
  }

   /**
   * Get hardHash
   * @return hardHash
  **/
  @Schema(description = "")
  public String getHardHash() {
    return hardHash;
  }

  public void setHardHash(String hardHash) {
    this.hardHash = hardHash;
  }

  public FileProcedureResponse exampleStartEA(String exampleStartEA) {
    this.exampleStartEA = exampleStartEA;
    return this;
  }

   /**
   * Get exampleStartEA
   * @return exampleStartEA
  **/
  @Schema(description = "")
  public String getExampleStartEA() {
    return exampleStartEA;
  }

  public void setExampleStartEA(String exampleStartEA) {
    this.exampleStartEA = exampleStartEA;
  }

  public FileProcedureResponse exampleEndEA(String exampleEndEA) {
    this.exampleEndEA = exampleEndEA;
    return this;
  }

   /**
   * Get exampleEndEA
   * @return exampleEndEA
  **/
  @Schema(description = "")
  public String getExampleEndEA() {
    return exampleEndEA;
  }

  public void setExampleEndEA(String exampleEndEA) {
    this.exampleEndEA = exampleEndEA;
  }

  public FileProcedureResponse isLibrary(Boolean isLibrary) {
    this.isLibrary = isLibrary;
    return this;
  }

   /**
   * Get isLibrary
   * @return isLibrary
  **/
  @Schema(description = "")
  public Boolean isIsLibrary() {
    return isLibrary;
  }

  public void setIsLibrary(Boolean isLibrary) {
    this.isLibrary = isLibrary;
  }

  public FileProcedureResponse exampleProcedure(List<String> exampleProcedure) {
    this.exampleProcedure = exampleProcedure;
    return this;
  }

  public FileProcedureResponse addExampleProcedureItem(String exampleProcedureItem) {
    if (this.exampleProcedure == null) {
      this.exampleProcedure = new ArrayList<String>();
    }
    this.exampleProcedure.add(exampleProcedureItem);
    return this;
  }

   /**
   * Get exampleProcedure
   * @return exampleProcedure
  **/
  @Schema(description = "")
  public List<String> getExampleProcedure() {
    return exampleProcedure;
  }

  public void setExampleProcedure(List<String> exampleProcedure) {
    this.exampleProcedure = exampleProcedure;
  }

  public FileProcedureResponse exampleProcedureId(String exampleProcedureId) {
    this.exampleProcedureId = exampleProcedureId;
    return this;
  }

   /**
   * Get exampleProcedureId
   * @return exampleProcedureId
  **/
  @Schema(description = "")
  public String getExampleProcedureId() {
    return exampleProcedureId;
  }

  public void setExampleProcedureId(String exampleProcedureId) {
    this.exampleProcedureId = exampleProcedureId;
  }

  public FileProcedureResponse status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

   /**
   * Get exampleBlockEAs
   * @return exampleBlockEAs
  **/
  @Schema(description = "")
  public List<BlockEA> getExampleBlockEAs() {
    return exampleBlockEAs;
  }

  public FileProcedureResponse blockCounts(List<Integer> blockCounts) {
    this.blockCounts = blockCounts;
    return this;
  }

  public FileProcedureResponse addBlockCountsItem(Integer blockCountsItem) {
    if (this.blockCounts == null) {
      this.blockCounts = new ArrayList<Integer>();
    }
    this.blockCounts.add(blockCountsItem);
    return this;
  }

   /**
   * Get blockCounts
   * @return blockCounts
  **/
  @Schema(description = "")
  public List<Integer> getBlockCounts() {
    return blockCounts;
  }

  public void setBlockCounts(List<Integer> blockCounts) {
    this.blockCounts = blockCounts;
  }

  public FileProcedureResponse instrCounts(List<Integer> instrCounts) {
    this.instrCounts = instrCounts;
    return this;
  }

  public FileProcedureResponse addInstrCountsItem(Integer instrCountsItem) {
    if (this.instrCounts == null) {
      this.instrCounts = new ArrayList<Integer>();
    }
    this.instrCounts.add(instrCountsItem);
    return this;
  }

   /**
   * Get instrCounts
   * @return instrCounts
  **/
  @Schema(description = "")
  public List<Integer> getInstrCounts() {
    return instrCounts;
  }

  public void setInstrCounts(List<Integer> instrCounts) {
    this.instrCounts = instrCounts;
  }

  public FileProcedureResponse byteCounts(List<Integer> byteCounts) {
    this.byteCounts = byteCounts;
    return this;
  }

  public FileProcedureResponse addByteCountsItem(Integer byteCountsItem) {
    if (this.byteCounts == null) {
      this.byteCounts = new ArrayList<Integer>();
    }
    this.byteCounts.add(byteCountsItem);
    return this;
  }

   /**
   * Get byteCounts
   * @return byteCounts
  **/
  @Schema(description = "")
  public List<Integer> getByteCounts() {
    return byteCounts;
  }

  public void setByteCounts(List<Integer> byteCounts) {
    this.byteCounts = byteCounts;
  }

  public FileProcedureResponse occurrenceCounts(Integer occurrenceCounts) {
    this.occurrenceCounts = occurrenceCounts;
    return this;
  }

   /**
   * Get occurrenceCounts
   * @return occurrenceCounts
  **/
  @Schema(description = "")
  public Integer getOccurrenceCounts() {
    return occurrenceCounts;
  }

  public void setOccurrenceCounts(Integer occurrenceCounts) {
    this.occurrenceCounts = occurrenceCounts;
  }

  public FileProcedureResponse nameCounts(Integer nameCounts) {
    this.nameCounts = nameCounts;
    return this;
  }

   /**
   * Get nameCounts
   * @return nameCounts
  **/
  @Schema(description = "")
  public Integer getNameCounts() {
    return nameCounts;
  }

  public void setNameCounts(Integer nameCounts) {
    this.nameCounts = nameCounts;
  }

   /**
   * Get procedureNames
   * @return procedureNames
  **/
  @Schema(description = "")
  public List<String> getProcedureNames() {
    return procedureNames;
  }

  public FileProcedureResponse coverage(Integer coverage) {
    this.coverage = coverage;
    return this;
  }

   /**
   * Get coverage
   * @return coverage
  **/
  @Schema(description = "")
  public Integer getCoverage() {
    return coverage;
  }

  public void setCoverage(Integer coverage) {
    this.coverage = coverage;
  }

  public FileProcedureResponse signature(List<List<String>> signature) {
    this.signature = signature;
    return this;
  }

  public FileProcedureResponse addSignatureItem(List<String> signatureItem) {
    this.signature.add(signatureItem);
    return this;
  }

   /**
   * Get signature
   * @return signature
  **/
  @Schema(required = true, description = "")
  public List<List<String>> getSignature() {
    return signature;
  }

  public void setSignature(List<List<String>> signature) {
    this.signature = signature;
  }

  public FileProcedureResponse signatureCount(Integer signatureCount) {
    this.signatureCount = signatureCount;
    return this;
  }

   /**
   * Get signatureCount
   * @return signatureCount
  **/
  @Schema(description = "")
  public Integer getSignatureCount() {
    return signatureCount;
  }

  public void setSignatureCount(Integer signatureCount) {
    this.signatureCount = signatureCount;
  }

  public FileProcedureResponse exampleProcedureCount(Integer exampleProcedureCount) {
    this.exampleProcedureCount = exampleProcedureCount;
    return this;
  }

   /**
   * Get exampleProcedureCount
   * @return exampleProcedureCount
  **/
  @Schema(description = "")
  public Integer getExampleProcedureCount() {
    return exampleProcedureCount;
  }

  public void setExampleProcedureCount(Integer exampleProcedureCount) {
    this.exampleProcedureCount = exampleProcedureCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileProcedureResponse fileProcedureResponse = (FileProcedureResponse) o;
    return Objects.equals(this.hardHash, fileProcedureResponse.hardHash) &&
        Objects.equals(this.exampleStartEA, fileProcedureResponse.exampleStartEA) &&
        Objects.equals(this.exampleEndEA, fileProcedureResponse.exampleEndEA) &&
        Objects.equals(this.isLibrary, fileProcedureResponse.isLibrary) &&
        Objects.equals(this.exampleProcedure, fileProcedureResponse.exampleProcedure) &&
        Objects.equals(this.exampleProcedureId, fileProcedureResponse.exampleProcedureId) &&
        Objects.equals(this.status, fileProcedureResponse.status) &&
        Objects.equals(this.exampleBlockEAs, fileProcedureResponse.exampleBlockEAs) &&
        Objects.equals(this.blockCounts, fileProcedureResponse.blockCounts) &&
        Objects.equals(this.instrCounts, fileProcedureResponse.instrCounts) &&
        Objects.equals(this.byteCounts, fileProcedureResponse.byteCounts) &&
        Objects.equals(this.occurrenceCounts, fileProcedureResponse.occurrenceCounts) &&
        Objects.equals(this.nameCounts, fileProcedureResponse.nameCounts) &&
        Objects.equals(this.procedureNames, fileProcedureResponse.procedureNames) &&
        Objects.equals(this.coverage, fileProcedureResponse.coverage) &&
        Objects.equals(this.signature, fileProcedureResponse.signature) &&
        Objects.equals(this.signatureCount, fileProcedureResponse.signatureCount) &&
        Objects.equals(this.exampleProcedureCount, fileProcedureResponse.exampleProcedureCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hardHash, exampleStartEA, exampleEndEA, isLibrary, exampleProcedure, exampleProcedureId, status, exampleBlockEAs, blockCounts, instrCounts, byteCounts, occurrenceCounts, nameCounts, procedureNames, coverage, signature, signatureCount, exampleProcedureCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileProcedureResponse {\n");
    
    sb.append("    hardHash: ").append(toIndentedString(hardHash)).append("\n");
    sb.append("    exampleStartEA: ").append(toIndentedString(exampleStartEA)).append("\n");
    sb.append("    exampleEndEA: ").append(toIndentedString(exampleEndEA)).append("\n");
    sb.append("    isLibrary: ").append(toIndentedString(isLibrary)).append("\n");
    sb.append("    exampleProcedure: ").append(toIndentedString(exampleProcedure)).append("\n");
    sb.append("    exampleProcedureId: ").append(toIndentedString(exampleProcedureId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    exampleBlockEAs: ").append(toIndentedString(exampleBlockEAs)).append("\n");
    sb.append("    blockCounts: ").append(toIndentedString(blockCounts)).append("\n");
    sb.append("    instrCounts: ").append(toIndentedString(instrCounts)).append("\n");
    sb.append("    byteCounts: ").append(toIndentedString(byteCounts)).append("\n");
    sb.append("    occurrenceCounts: ").append(toIndentedString(occurrenceCounts)).append("\n");
    sb.append("    nameCounts: ").append(toIndentedString(nameCounts)).append("\n");
    sb.append("    procedureNames: ").append(toIndentedString(procedureNames)).append("\n");
    sb.append("    coverage: ").append(toIndentedString(coverage)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    signatureCount: ").append(toIndentedString(signatureCount)).append("\n");
    sb.append("    exampleProcedureCount: ").append(toIndentedString(exampleProcedureCount)).append("\n");
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
