package com.example.sockswarehouse.controllers;

import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.model.Socks;
import com.example.sockswarehouse.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@Tag(name = "Cклад носков")
public class SocksController {

    private final SocksService socksService;


    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось добавить приход",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Socks.class))}),
            @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны",
                    content = @Content)})
    public ResponseEntity<Socks> addSocks(@Valid @RequestBody Socks socks) {
        Socks addSocks = socksService.addSocks(socks);
        return ResponseEntity.ok(addSocks);
    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Socks.class))}),
            @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны",
                    content = @Content)})
    public ResponseEntity<Socks> reduceSocks(@Valid @RequestBody Socks socks) {
        Socks addSocks = socksService.reduceSocks(socks);
        return ResponseEntity.ok(addSocks);
    }

    @GetMapping
    @Operation(summary = "Все носки")
    public Collection<Socks> allSocks() {
        return this.socksService.allSocks();
    }

    @GetMapping("/{цвет},{размер}")
    @Operation(summary = "Колличество носков")
    public Socks getSock(@PathVariable Color цвет, @PathVariable Size размер) {
        return this.socksService.showSocks(цвет,размер);
    }
}
