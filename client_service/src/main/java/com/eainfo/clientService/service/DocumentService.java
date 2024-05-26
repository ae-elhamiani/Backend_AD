//package com.eainfo.clientService.service;
//
//import com.eainfo.clientService.dto.ClientOcrResponse;
//import com.eainfo.clientService.dto.OcrRequest;
//import com.eainfo.clientService.dto.OcrResponse;
//import com.eainfo.clientService.model.Client;
//import com.eainfo.clientService.model.FileProcessingException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.util.Base64Utils;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DocumentService {
//    public ClientOcrResponse processDocuments(MultipartFile cinRecto, MultipartFile cinVerso) {
//        OcrRequest ocrRequest = new OcrRequest();
//        ocrRequest.setCodeClient("token_JWT");
//
//        List<OcrRequest.Document> documents = new ArrayList<>();
//
//        documents.add(new OcrRequest.Document(convertToBase64(cinRecto), 0, "jpg","",""));
//        documents.add(new OcrRequest.Document(convertToBase64(cinVerso), 1, "jpg","",""));
//
//        ocrRequest.setDocument(documents);
//
//        OcrResponse response = mockOcrProcess(ocrRequest);
//
//        ClientOcrResponse clientOcrResponse = new ClientOcrResponse();
//        clientOcrResponse.setNom(response.getDocumentDto().getNom());
//        clientOcrResponse.setPrenom(response.getDocumentDto().getPrenom());
//        clientOcrResponse.setAddress(response.getDocumentDto().getAddress());
//        clientOcrResponse.setNumeroDocIdentite(response.getDocumentDto().getNumeroDocIdentite());
//
//
//        return clientOcrResponse;
//    }
//
//    private OcrResponse mockOcrProcess(OcrRequest ocrRequest) {
//
//        OcrResponse response = new OcrResponse();
//        response.setCodeRetour("00000");
//        response.setMessageRetour("success");
//        response.setCodeClient("token_client");
//
//        OcrResponse.DocumentData documentData = new OcrResponse.DocumentData();
//        documentData.setTypeDocument("IDENTITY_CARD");
//        documentData.setFormatDocument("ID1");
//        documentData.setNomDocument("Mock Identity Document");
//        documentData.setAnneDelivrance("2010");
//        documentData.setCodePaysDelivrance("MAR");
//        documentData.setPaysDelivrance("Morocco");
//        documentData.setScore("100");
//        documentData.setDateNaissance("1990-01-01");
//        documentData.setNumeroDocument("1234567890");
//        documentData.setDateExpiration("2030-01-01");
//        documentData.setDateDelivrance("2010-01-01");
//        documentData.setLieuNaissance("Cityville");
//        documentData.setNom("elh");
//        documentData.setPrenom("ayman");
//        documentData.setNomMere("");
//        documentData.setNationalite("Moroccan");
//        documentData.setCodeNationalite("MAR");
//        documentData.setSexe("M");
//        documentData.setAddress("123 Main Street, Cityville, Morocco");
//        documentData.setAuthorite("Cityville Authority");
//        documentData.setNomPere("Maxwell");
//        documentData.setNumeroDocIdentite("CIN123456789");
//        documentData.setMoisPourExpire(60);
//
//        response.setDocumentDto(documentData);
//
//        return response;
//    }
//
//    private String convertToBase64(MultipartFile file) {
//        try {
//            byte[] fileContent = file.getBytes();
//            return Base64Utils.encodeToString(fileContent);
//        } catch (IOException e) {
//
//            throw new FileProcessingException("Failed to convert file to Base64", e);
//        }
//    }
//
//}
