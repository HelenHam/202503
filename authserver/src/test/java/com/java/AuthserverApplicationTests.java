package com.java;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthserverApplicationTests {

	private static final String GET_ACCESS_TOKEN_ENDPOINT = "/oauth2/token";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testAccessTokenFail() throws Exception {
		mockMvc.perform(post(GET_ACCESS_TOKEN_ENDPOINT)
				.param("client_id", "oidc-client")
				.param("client_secret", "secret")
				.param("grant_type", "client_credentials")
		)
		.andExpect(status().isUnauthorized())
		.andDo(print())
		.andExpect(jsonPath("$.error", is("invalid_client")));
	}

	@Test
	void testAccessTokenSuccess() throws Exception {
		mockMvc.perform(post(GET_ACCESS_TOKEN_ENDPOINT)
				.param("client_id", "oidc-client")
				.param("client_secret", "secret")
				.param("grant_type", "client_credentials")
		)
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.token_type", is("Bearer")))
		.andExpect(jsonPath("$.access_token").isString())
		.andExpect(jsonPath("$.expires_in").isNumber());
	}

}
