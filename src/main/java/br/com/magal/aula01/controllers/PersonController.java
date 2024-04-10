package br.com.magal.aula01.controllers;


import br.com.magal.aula01.data.vo.versions.PersonVO;
import br.com.magal.aula01.data.vo.versions.PersonVOv2;
import br.com.magal.aula01.mapper.custom.PersonMapper;
import br.com.magal.aula01.services.PersonServices;
import br.com.magal.aula01.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for managing People")
public class PersonController {
    @Autowired
    private PersonServices personServices;


    @GetMapping(produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People", tags ={"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content),
    })
    public List<PersonVO> findAll() {
        return personServices.findAll();
    }
    @GetMapping(value = "/{id}", produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person", tags ={"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content (schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content),
    })
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return personServices.findById(id);
    }

    @PostMapping(consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person", tags ={"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
            @Content (schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content),
    })
    public PersonVO create( @RequestBody PersonVO person) {
        return personServices.create(person);
    }

    @PostMapping(value = "/v2", consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVOv2 createV2( @RequestBody PersonVOv2 person) {
        return personServices.createV2(person);
    }

    @PutMapping(consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Person", description = "Updates a Person", tags ={"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
            @Content (schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content),
    })
    public PersonVO update( @RequestBody PersonVO person) throws Exception {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person", description = "Deletes a Person", tags ={"People"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content =
            @Content (schema = @Schema(implementation = PersonVO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         personServices.delete(id);
         return ResponseEntity.noContent().build();
    }
}
