package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.CommentController;
import com.mycompany.roadtripplanner.dtos.comment.CommentDTO;
import com.mycompany.roadtripplanner.dtos.comment.CommentUpdateDTO;
import com.mycompany.roadtripplanner.dtos.user.UserCommentDTO;
import com.mycompany.roadtripplanner.services.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
public class CommentControllerTest {
    //Injection de dependance
    @Autowired
    private MockMvc mockMvc;

    //On copie le service
    @MockBean
    private CommentService service;

    //On test le controller findAll
    @Test
    public void testFindAllComment() throws Exception{
        this.mockMvc.perform(get("/comment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    //On test si l'utilisatuer n'existe pas
    @Test
    public void testFindOneCommentWhereWrongId() throws Exception{
        //On execute la requete
        this.mockMvc.perform(get("/comment/dvdcsdf5fs5sf"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindOne() throws Exception{
        //On crée un commentaire
        CommentDTO commentDTO = this.commentDto();
        //On appel la methode given pour moker notre service
        //en parametre le type de requete
        BDDMockito.given(service.find("1"))
                .willReturn (Optional.of(commentDTO));
        MvcResult result = this.mockMvc.perform(get("/comment/1"))
                .andExpect(status().isOk())
                .andReturn();
        //On initialisz l'objet Gson pour la transformation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        CommentDTO body = json.fromJson(result.getResponse().getContentAsString(), CommentDTO.class);
        Assertions.assertEquals(body.getTitle(),this.commentDto().getTitle());

    }

    @Test
    public void testSaveComment() throws Exception {
        // on creer notre commentaire
        CommentDTO commentDTO= this.commentDto();
        //On initialise notre json pour la creation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        // on transforme notre obj en json
        String body = json.toJson(commentDTO);
        this.mockMvc.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteComment() throws Exception{
        //On Initialise l'objet Gson pour la transformation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String body = json.toJson(this.commentDto());
        this.mockMvc.perform(delete("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateComment() throws Exception{
        //1 On recupère le commentaire
        CommentDTO commentDTO = this.commentDto();
        CommentDTO commentUpdateDTo = this.commentUPDATEDTO();
        BDDMockito.given(service.find("1"))
                .willReturn(Optional.of(commentDTO));
        MvcResult result = this.mockMvc.perform(get("/comment/1"))
                .andExpect((status().isOk()))
                .andReturn();
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        CommentUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(),CommentUpdateDTO.class);

        BDDMockito.when(service.update(any(CommentUpdateDTO.class)))
                .thenReturn(commentUpdateDTo);
        //2 On fait la modification
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();

        //2.5 On transforme le résultat en objet
        CommentDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), CommentDTO.class);

        //3 On verifie si il a bien été modifié
        Assertions.assertEquals(finalBody.getTitle(),this.commentUPDATEDTO().getTitle());
    }

    private CommentDTO commentDto(){
        return new CommentDTO(
                "1",
                "Mon titre",
                "Voici ma super description",
                new Date(),
                2,
                new UserCommentDTO()
        );
    }


    private CommentDTO commentUPDATEDTO(){
        return new CommentDTO(
              "1",
                "Mon titre2",
                "Voici ma super description",
                new Date(),
                2,
                new UserCommentDTO()
        );
    }
}
