// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: customer/customer.proto

package com.dailycodebuffer.customer.proto;

public interface ListCustomerResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:customer.ListCustomerResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool success = 1;</code>
   * @return The success.
   */
  boolean getSuccess();

  /**
   * <code>.customer.ListCustomerResponse.Data data = 2;</code>
   * @return Whether the data field is set.
   */
  boolean hasData();
  /**
   * <code>.customer.ListCustomerResponse.Data data = 2;</code>
   * @return The data.
   */
  com.dailycodebuffer.customer.proto.ListCustomerResponse.Data getData();
  /**
   * <code>.customer.ListCustomerResponse.Data data = 2;</code>
   */
  com.dailycodebuffer.customer.proto.ListCustomerResponse.DataOrBuilder getDataOrBuilder();

  /**
   * <code>.customer.CustomerGrpcError error = 3;</code>
   * @return Whether the error field is set.
   */
  boolean hasError();
  /**
   * <code>.customer.CustomerGrpcError error = 3;</code>
   * @return The error.
   */
  com.dailycodebuffer.customer.proto.CustomerGrpcError getError();
  /**
   * <code>.customer.CustomerGrpcError error = 3;</code>
   */
  com.dailycodebuffer.customer.proto.CustomerGrpcErrorOrBuilder getErrorOrBuilder();

  public com.dailycodebuffer.customer.proto.ListCustomerResponse.ResponseCase getResponseCase();
}
