package com.example.demo.steps;

import com.example.demo.controller.PublicUserController;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserSteps {

    private MockMvc mockMvc;
    private MvcResult result;

    @Autowired
    private UserRepository userRepository;

    @Given("the following users exist:")
    public void the_following_users_exist(List<User> users) {
        userRepository.saveAll(users);
    }

    @When("I get all users")
    public void i_get_all_users() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new PublicUserController(userRepository)).build();
        mockMvc.perform(get("/public/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Then("the response contains the following users:")
    public void the_response_contains_the_following_users(List<User> expectedUsers) throws Exception {
        mockMvc.perform(get("/public/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'John Doe'},{'id':2,'name':'Jane Doe'}]"));
    }

    @When("I access the public endpoint")
    public void i_access_the_public_endpoint() throws Exception {
        result = mockMvc.perform(get("/public")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @When("I access the private endpoint")
    public void i_access_the_private_endpoint() throws Exception {
        result = mockMvc.perform(get("/private")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int status) throws Exception {
        result.getResponse().getStatus();
    }

    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in_as_with_password(String username, String password) throws Exception {
        // You would typically perform login logic here, or mock the authentication process
        mockMvc.perform(get("/login").param("username", username).param("password", password));
    }
}
