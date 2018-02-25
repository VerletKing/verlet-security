package org.verlet.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.verlet.dto.UserDTO;

/**
 * @author verlet
 * @date 2017/12/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String contentAsString = mockMvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .param("name", "哈哈")
                        .param("password", "123")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "age,desc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void queryByName() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/name/天天")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("天天"));
    }

    @Test
    public void whenFileUpload() throws Exception {
        mockMvc.perform(
        MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file","test.text","multipart/form-data","hello word".getBytes("UTF-8"))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
