package infsus.dz3.dkbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import infsus.dz3.dkbackend.controller.MedicationController;
import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.service.MedicationService;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MedicationController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})

public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetmedications() throws Exception {
        List<MedicationDto> lmd = new ArrayList<>();
        lmd.add(new MedicationDto());
        when(medicationService.getMedications(new ArrayList<>())).thenReturn(lmd);
        mockMvc.perform(post("/getMedications").content(objectMapper.writeValueAsString(new ArrayList<>()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }


}
