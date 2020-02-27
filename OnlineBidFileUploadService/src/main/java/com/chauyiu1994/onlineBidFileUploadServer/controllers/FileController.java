package com.chauyiu1994.onlineBidFileUploadServer.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    @PostMapping (value = "/upload", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Mono<String> uploadImage(@RequestPart("image") FilePart filePart) throws IOException {

        if (!(filePart.filename().endsWith(".jpg") || filePart.filename().endsWith(".png"))) {
            return Mono.just("");
        }

        System.out.println(filePart.filename());
        Path tempFile = Files.createFile(Paths.get("/Users/yiuleungchau/static/" + filePart.filename()));
        System.out.println(tempFile);

        // Method 1
//        AsynchronousFileChannel channel = AsynchronousFileChannel.open(temFile, StandardOpenOption.WRITE);
//        DataBufferUtils.write(filePart.content(), channel, 0)
//            .doOnComplete(() -> System.out.println("finish")).subscribe();

        // Method 2
        filePart.transferTo(tempFile.toFile());
        return Mono.just(filePart.filename());
    }

    @GetMapping("/download/{filename}")
    public Mono<Void> downloadImage(@PathVariable("filename") String filename, ServerHttpResponse response) throws IOException {

        Path path = Paths.get("/Users/yiuleungchau/static/" + filename);

        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        return zeroCopyResponse.writeWith(path, 0, Files.size(path));

    }
}
