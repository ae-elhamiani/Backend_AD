//package com.eainfo.clientService.controller;
//
//import com.eainfo.clientService.dto.ClientOcrResponse;
//import com.eainfo.clientService.service.DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/ocr")
//public class DocumentController {
//
//    @Autowired
//    private DocumentService documentService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<ClientOcrResponse> uploadAndProcessDocument(
//            @RequestParam("cinRecto") MultipartFile cinRecto,
//            @RequestParam("cinVerso") MultipartFile cinVerso,
//            @RequestParam("selfie") MultipartFile selfie) {
//
//        ClientOcrResponse response = documentService.processDocuments(cinRecto, cinVerso);
//        return ResponseEntity.ok(response);
//    }
//}
//
