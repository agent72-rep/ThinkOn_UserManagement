package com.thinkon.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Phanindra on 2024-10-09.
 */
public class UserPagedList extends PageImpl<UsersDTO> implements Serializable {

	static final long serialVersionUID = 1114715135625836949L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public UserPagedList(@JsonProperty("content") List<UsersDTO> content, @JsonProperty("number") int number,
			@JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
			@JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
			@JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
			@JsonProperty("first") boolean first, @JsonProperty("numberOfElements") int numberOfElements) {

		super(content, PageRequest.of(number, size), totalElements);
	}

	public UserPagedList(List<UsersDTO> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public UserPagedList(List<UsersDTO> content) {
		super(content);
	}
}
