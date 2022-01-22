package com.gb.gbshop.rest.version.v1.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.gbshop.components.category.CategoryDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {

    private static final String API_URL = "/api/v1/category/";
    private static final CategoryDto categoryDto = createCategoryDto();

    private static UUID entityId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void findAllNoContentTest() throws Exception {
        mockMvc.perform(get(API_URL))
               .andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    public void saveCreatedTest() throws Exception {
        mockMvc.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON)
                                     .content(objectMapper.writeValueAsString(categoryDto)))
               .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void findAllOkTest() throws Exception {
        MvcResult result = mockMvc.perform(get(API_URL))
                                  .andExpect(status().isOk())
                                  .andExpect(jsonPath("$", hasSize(1)))
                                  .andExpect(jsonPath("$.[0].title").value("Category1"))
                                  .andExpect(jsonPath("$.[0].version").value(1))
                                  .andExpect(jsonPath("$.[0].createdBy").value("User1"))
                                  .andExpect(jsonPath("$.[0].lastModifiedBy").value("User2"))
                                  .andReturn();
        entityId = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryDto[].class)[0].getId();
    }

    @Test
    @Order(4)
    public void findByIdOkTest() throws Exception {
        mockMvc.perform(get(API_URL + entityId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(entityId.toString()))
               .andExpect(jsonPath("$.title").value("Category1"))
               .andExpect(jsonPath("$.version").value(1))
               .andExpect(jsonPath("$.createdBy").value("User1"))
               .andExpect(jsonPath("$.lastModifiedBy").value("User2"));
    }

    @Test
    @Order(6)
    public void findByIdNoContentTest() throws Exception {
        mockMvc.perform(get(API_URL + UUID.randomUUID()))
               .andExpect(status().isNoContent());
    }

    @Test
    @Order(7)
    public void updateExistedTest() throws Exception {
        categoryDto.setTitle("UpdatedCategory1");
        mockMvc.perform(put(API_URL + entityId).contentType(MediaType.APPLICATION_JSON)
                                                         .content(objectMapper.writeValueAsString(categoryDto.getId()))
                                                         .content(objectMapper.writeValueAsString(categoryDto)))
               .andExpect(status().isNoContent());
        mockMvc.perform(get(API_URL + entityId))
               .andExpect(jsonPath("$.title").value("UpdatedCategory1"));
    }

    @Test
    @Order(8)
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(API_URL + entityId).contentType(MediaType.APPLICATION_JSON)
                                                            .content(objectMapper.writeValueAsString(categoryDto.getId())))
                .andExpect(status().isNoContent());
        mockMvc.perform(get(API_URL + entityId))
               .andExpect(status().isNoContent());
    }

    private static CategoryDto createCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(UUID.randomUUID());
        categoryDto.setTitle("Category1");
        categoryDto.setVersion(1);
        categoryDto.setCreatedBy("User1");
        categoryDto.setCreatedDate(new Timestamp(new Date().getTime()));
        categoryDto.setLastModifiedBy("User2");
        categoryDto.setLastModifiedDate(new Timestamp(new Date().getTime()));
        return categoryDto;
    }

}
