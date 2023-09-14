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
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * ProcedureGroup
 */


public class ProcedureGroup implements Serializable{
  private static final long serialVersionUID = 1L;
  @JsonProperty("hard_hash")
  private String hardHash = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("is_library")
  private Boolean isLibrary = null;

  @JsonProperty("yara_likely")
  private Boolean yaraLikely = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("names")
  private List<String> names = null;

  @JsonProperty("occurrence_count")
  private Integer occurrenceCount = null;

  @JsonProperty("block_counts")
  private List<Integer> blockCounts = new ArrayList<Integer>();

  @JsonProperty("instruction_counts")
  private List<Integer> instructionCounts = new ArrayList<Integer>();

  @JsonProperty("byte_counts")
  private List<Integer> byteCounts = new ArrayList<Integer>();

  @JsonProperty("signature")
  private List<List<String>> signature = null;

  @JsonProperty("tags")
  private List<String> tags = null;

  @JsonProperty("notes")
  private List<String> notes = null;

   /**
   * Get hardHash
   * @return hardHash
  **/
  @Schema(description = "")
  public String getHardHash() {
    return hardHash;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public String getStatus() {
    return status;
  }

   /**
   * Get isLibrary
   * @return isLibrary
  **/
  @Schema(description = "")
  public Boolean isIsLibrary() {
    return isLibrary;
  }

   /**
   * Get yaraLikely
   * @return yaraLikely
  **/
  @Schema(description = "")
  public Boolean isYaraLikely() {
    return yaraLikely;
  }

   /**
   * Get version
   * @return version
  **/
  @Schema(description = "")
  public String getVersion() {
    return version;
  }

   /**
   * Get names
   * @return names
  **/
  @Schema(description = "")
  public List<String> getNames() {
    return names;
  }

   /**
   * Get occurrenceCount
   * @return occurrenceCount
  **/
  @Schema(description = "")
  public Integer getOccurrenceCount() {
    return occurrenceCount;
  }

  public ProcedureGroup blockCounts(List<Integer> blockCounts) {
    this.blockCounts = blockCounts;
    return this;
  }

  public ProcedureGroup addBlockCountsItem(Integer blockCountsItem) {
    this.blockCounts.add(blockCountsItem);
    return this;
  }

   /**
   * Get blockCounts
   * @return blockCounts
  **/
  @Schema(required = true, description = "")
  public List<Integer> getBlockCounts() {
    return blockCounts;
  }

  public void setBlockCounts(List<Integer> blockCounts) {
    this.blockCounts = blockCounts;
  }

  public ProcedureGroup instructionCounts(List<Integer> instructionCounts) {
    this.instructionCounts = instructionCounts;
    return this;
  }

  public ProcedureGroup addInstructionCountsItem(Integer instructionCountsItem) {
    this.instructionCounts.add(instructionCountsItem);
    return this;
  }

   /**
   * Get instructionCounts
   * @return instructionCounts
  **/
  @Schema(required = true, description = "")
  public List<Integer> getInstructionCounts() {
    return instructionCounts;
  }

  public void setInstructionCounts(List<Integer> instructionCounts) {
    this.instructionCounts = instructionCounts;
  }

  public ProcedureGroup byteCounts(List<Integer> byteCounts) {
    this.byteCounts = byteCounts;
    return this;
  }

  public ProcedureGroup addByteCountsItem(Integer byteCountsItem) {
    this.byteCounts.add(byteCountsItem);
    return this;
  }

   /**
   * Get byteCounts
   * @return byteCounts
  **/
  @Schema(required = true, description = "")
  public List<Integer> getByteCounts() {
    return byteCounts;
  }

  public void setByteCounts(List<Integer> byteCounts) {
    this.byteCounts = byteCounts;
  }

   /**
   * Get signature
   * @return signature
  **/
  @Schema(description = "")
  public List<List<String>> getSignature() {
    return signature;
  }

   /**
   * Get tags
   * @return tags
  **/
  @Schema(description = "")
  public List<String> getTags() {
    return tags;
  }

   /**
   * self referral field
   * @return notes
  **/
  @Schema(description = "self referral field")
  public List<String> getNotes() {
    return notes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcedureGroup procedureGroup = (ProcedureGroup) o;
    return Objects.equals(this.hardHash, procedureGroup.hardHash) &&
        Objects.equals(this.status, procedureGroup.status) &&
        Objects.equals(this.isLibrary, procedureGroup.isLibrary) &&
        Objects.equals(this.yaraLikely, procedureGroup.yaraLikely) &&
        Objects.equals(this.version, procedureGroup.version) &&
        Objects.equals(this.names, procedureGroup.names) &&
        Objects.equals(this.occurrenceCount, procedureGroup.occurrenceCount) &&
        Objects.equals(this.blockCounts, procedureGroup.blockCounts) &&
        Objects.equals(this.instructionCounts, procedureGroup.instructionCounts) &&
        Objects.equals(this.byteCounts, procedureGroup.byteCounts) &&
        Objects.equals(this.signature, procedureGroup.signature) &&
        Objects.equals(this.tags, procedureGroup.tags) &&
        Objects.equals(this.notes, procedureGroup.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hardHash, status, isLibrary, yaraLikely, version, names, occurrenceCount, blockCounts, instructionCounts, byteCounts, signature, tags, notes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcedureGroup {\n");
    
    sb.append("    hardHash: ").append(toIndentedString(hardHash)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    isLibrary: ").append(toIndentedString(isLibrary)).append("\n");
    sb.append("    yaraLikely: ").append(toIndentedString(yaraLikely)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("    occurrenceCount: ").append(toIndentedString(occurrenceCount)).append("\n");
    sb.append("    blockCounts: ").append(toIndentedString(blockCounts)).append("\n");
    sb.append("    instructionCounts: ").append(toIndentedString(instructionCounts)).append("\n");
    sb.append("    byteCounts: ").append(toIndentedString(byteCounts)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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
