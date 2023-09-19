// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer/customer.proto

package com.dailycodebuffer.customer.proto;

public interface CustomerPageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:customer.CustomerPageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 page = 1;</code>
   * @return Whether the page field is set.
   */
  boolean hasPage();
  /**
   * <code>optional int32 page = 1;</code>
   * @return The page.
   */
  int getPage();

  /**
   * <code>optional int32 size = 2;</code>
   * @return Whether the size field is set.
   */
  boolean hasSize();
  /**
   * <code>optional int32 size = 2;</code>
   * @return The size.
   */
  int getSize();

  /**
   * <code>optional string sort = 3;</code>
   * @return Whether the sort field is set.
   */
  boolean hasSort();
  /**
   * <code>optional string sort = 3;</code>
   * @return The sort.
   */
  java.lang.String getSort();
  /**
   * <code>optional string sort = 3;</code>
   * @return The bytes for sort.
   */
  com.google.protobuf.ByteString
      getSortBytes();

  /**
   * <code>optional string direction = 4;</code>
   * @return Whether the direction field is set.
   */
  boolean hasDirection();
  /**
   * <code>optional string direction = 4;</code>
   * @return The direction.
   */
  java.lang.String getDirection();
  /**
   * <code>optional string direction = 4;</code>
   * @return The bytes for direction.
   */
  com.google.protobuf.ByteString
      getDirectionBytes();
}
