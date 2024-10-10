/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.contoller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkon.model.UsersDTO;
import com.thinkon.services.UsersService;
import com.thinkon.web.controller.UsersController;

import ch.qos.logback.core.testUtil.RandomUtil;


/**
 * 
 */
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

	@Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UsersService UsersService;

    @Test
    void getUsersById() throws Exception {

        given(UsersService.getUserById(any())).willReturn(getValidUsersDto());

        mockMvc.perform(get("/api/v1/users/" + RandomUtil.getPositiveInt()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewUsers() throws Exception {

    	UsersDTO UsersDto = getValidUsersDto();
        String UsersDtoJson = objectMapper.writeValueAsString(UsersDto);

        given(UsersService.saveUsers(any())).willReturn(getValidUsersDto());

        mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UsersDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUsersById() throws Exception {
        given(UsersService.updateUserDetails(any(), any())).willReturn(getValidUsersDto());

        UsersDTO UsersDto = getValidUsersDto();
        String UsersDtoJson = objectMapper.writeValueAsString(UsersDto);

        mockMvc.perform(put("/api/v1/users/" + RandomUtil.getPositiveInt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(UsersDtoJson))
                .andExpect(status().isNoContent());
    }

    UsersDTO getValidUsersDto(){
        return UsersDTO.builder().firstName("Test"+RandomUtil.getPositiveInt()).
        		lastName("Sample Test")
        		.email("test"+RandomUtil.getPositiveInt()+"@test.com")
        		.userName("test"+RandomUtil.getPositiveInt())
        		.phoneNo("9999999999")
                .build();
    }
	
}
